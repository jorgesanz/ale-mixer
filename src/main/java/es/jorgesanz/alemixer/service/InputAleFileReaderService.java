package es.jorgesanz.alemixer.service;

import es.jorgesanz.alemixer.model.AleFile;
import es.jorgesanz.alemixer.model.Heading;
import es.jorgesanz.alemixer.model.VideoCut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
@Slf4j
public class InputAleFileReaderService {

    public static final String HEADING = "Heading";
    public static final String COLUMN = "Column";
    public static final String FIELD_DELIMITER = "\t";
    public static final String FIELD_DELIM = "FIELD_DELIM";
    public static final String VIDEO_FORMAT = "VIDEO_FORMAT";
    public static final String AUDIO_FORMAT = "AUDIO_FORMAT";
    public static final String FPS = "FPS";

    public AleFile read(String fileName) {
        AleFile aleFile = new AleFile();
        try {
            Resource resource = new ClassPathResource(fileName);
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            aleFile.setHeading(readHeading(reader));
            List<String> columnNames =readColumnNames(reader);
            aleFile.setVideoCuts(readVideoCuts(reader,columnNames));

        } catch (IOException e) {
            log.error(String.format("error loading file %s", fileName),e.getMessage());
        }
        return aleFile;
    }

    private List<VideoCut> readVideoCuts(BufferedReader reader, List<String> columnNames) throws IOException {
        while(reader.ready()) {
            String line = reader.readLine();
            log.info(line);
        }
        return null;
    }

    private List<String> readColumnNames(BufferedReader reader) throws IOException {
        return null;
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
            if(FIELD_DELIM.equals(fields[0])){
                heading.setFieldDelimiter(fields[1]);
            }
            else if(VIDEO_FORMAT.equals(fields[0])){
                heading.setVideoFormat(fields[1]);
            }
            else if(AUDIO_FORMAT.equals(fields[0])){
                heading.setAudioFormat(fields[1]);
            }
            else if(FPS.equals(fields[0])){
                heading.setFps(fields[1]);
            }
            line = reader.readLine();
        }
        return heading;
    }
}
