package es.jorgesanz.alemixer.service;

import es.jorgesanz.alemixer.model.AleFile;
import es.jorgesanz.alemixer.model.Heading;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

import static es.jorgesanz.alemixer.service.InputAleFileReaderService.*;

@Service
@Slf4j
public class OutputAleFileWriterService {
    public void write(AleFile replacedAleFile, String fileLocation) {
        try{
            File file = new File(fileLocation);
            Writer output = new BufferedWriter(new FileWriter(file));
            writeHeading(output,replacedAleFile.getHeading());
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
        output.write(System.lineSeparator());
    }
}
