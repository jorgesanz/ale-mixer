package es.jorgesanz.alemixer.service;

import es.jorgesanz.alemixer.model.AleFile;
import es.jorgesanz.alemixer.model.Heading;
import es.jorgesanz.alemixer.model.VideoCut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

import static es.jorgesanz.alemixer.service.InputAleFileReaderService.*;

@Service
@Slf4j
public class OutputAleFileWriterService {
    public void write(AleFile replacedAleFile, String fileLocation) {
        try{
            File file = new File(fileLocation);
            Writer output = new BufferedWriter(new FileWriter(file));
            writeHeading(output,replacedAleFile.getHeading());
            output.write(System.lineSeparator());
            writeColumns(output,replacedAleFile.getColumnNames());
            output.write(System.lineSeparator());
            writeData(output,replacedAleFile.getVideoCuts(),replacedAleFile.getColumnNames());
            output.close();

        }catch(Exception e){
            log.error(String.format("error loading file %s", fileLocation),e.getMessage());
        }
    }

    private void writeHeading(Writer output, Heading heading) throws IOException {
        output.write(HEADING+System.lineSeparator());
        output.write(FIELD_DELIM_HEADER_FIELD+" "+heading.getFieldDelimiter()+System.lineSeparator());
        output.write(VIDEO_FORMAT_HEADER_FIELD+"\t"+heading.getVideoFormat()+System.lineSeparator());
        output.write(AUDIO_FORMAT_HEADER_FIELD+"\t"+heading.getAudioFormat()+System.lineSeparator());
        output.write(FPS_HEADER_FIELD+" "+heading.getFps()+System.lineSeparator());
    }

    private void writeColumns(Writer output, List<String> columnNames) throws IOException {

        output.write(COLUMN+System.lineSeparator());
        for(String line: columnNames){
            output.write(line+"\t");
        }
        output.write(COLUMN+System.lineSeparator());

    }

    private void writeData(Writer output, List<VideoCut> videoCuts, List<String> columnNames) throws IOException {
        output.write(DATA+System.lineSeparator());
        for(VideoCut videoCut: videoCuts){
            for (String column :columnNames){
                String columnValue = videoCut.getColumns().get(column);
                output.write(columnValue+"\t");
            }
            output.write(System.lineSeparator());
        }
    }
}
