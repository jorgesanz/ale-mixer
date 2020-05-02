package es.jorgesanz.alemixer.service;

import es.jorgesanz.alemixer.model.AleFile;
import es.jorgesanz.alemixer.model.Heading;
import es.jorgesanz.alemixer.model.VideoCut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@Slf4j
public class InputAleFileReaderService {

    public static final String HEADING = "Heading";
    public static final String COLUMN = "Column";
    public static final String FIELD_DELIMITER = "\t";
    public static final String FIELD_DELIM_HEADER_FIELD = "FIELD_DELIM";
    public static final String VIDEO_FORMAT_HEADER_FIELD = "VIDEO_FORMAT";
    public static final String AUDIO_FORMAT_HEADER_FIELD = "AUDIO_FORMAT";
    public static final String FPS_HEADER_FIELD = "FPS";
    public static final String DATA = "Data";

    public AleFile read(String fileLocation) {
        AleFile aleFile = new AleFile();
        try {
            InputStream inputStream = new FileInputStream(new File(fileLocation));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            aleFile.setHeading(readHeading(reader));
            List<String> columnNames =readColumnNames(reader);
            aleFile.setColumnNames(columnNames);
            aleFile.setVideoCuts(readVideoCuts(reader,columnNames));

        } catch (IOException e) {
            log.error(String.format("error loading file %s", fileLocation),e.getMessage());
        }
        return aleFile;
    }

    private List<VideoCut> readVideoCuts(BufferedReader reader, List<String> columnNames) throws IOException {

        String line = reader.readLine();
        while (!DATA.equals(line)){
            line = reader.readLine();
        }

        List<VideoCut> videoCuts = new ArrayList<>();
        while(reader.ready()) {
            line = reader.readLine();
            List<String> columnValues = Arrays.asList(line.split(FIELD_DELIMITER));
            if(columnNames.size() != columnValues.size()){
                log.warn("Different sizes in column names and values");
            }
            Map<String,String> columns = new HashMap();
            for (int i=0; i<columnNames.size(); i++){
                columns.put(columnNames.get(i),columnValues.get(i));
            }
            VideoCut videoCut = new VideoCut();
            videoCut.setColumns(columns);
            log.info(line);
            videoCuts.add(videoCut);
        }
        return videoCuts;
    }

    private Heading readHeading(BufferedReader reader) throws IOException {
        String headerIdentifier = reader.readLine();
        if(!HEADING.equals(headerIdentifier)){
            log.warn("Unexpected Header identifier "+headerIdentifier);
        }
        Heading heading = new Heading();
        String line = reader.readLine();
        while(!COLUMN.equals(line)){

            String[] fields = line.split(FIELD_DELIMITER);
            if(FIELD_DELIM_HEADER_FIELD.equals(fields[0])){
                heading.setFieldDelimiter(fields[1]);
            }
            else if(VIDEO_FORMAT_HEADER_FIELD.equals(fields[0])){
                heading.setVideoFormat(fields[1]);
            }
            else if(AUDIO_FORMAT_HEADER_FIELD.equals(fields[0])){
                heading.setAudioFormat(fields[1]);
            }
            else if(FPS_HEADER_FIELD.equals(fields[0])){
                heading.setFps(fields[1]);
            }
            line = reader.readLine();
        }
        return heading;
    }

    private List<String> readColumnNames(BufferedReader reader) throws IOException {
        String columnNamesLine = reader.readLine();
        List<String> columnNames = Arrays.asList(columnNamesLine.split(FIELD_DELIMITER));
        return columnNames;
    }
}
