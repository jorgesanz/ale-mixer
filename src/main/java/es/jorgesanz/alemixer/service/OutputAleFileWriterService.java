package es.jorgesanz.alemixer.service;

import es.jorgesanz.alemixer.model.AleFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

@Service
@Slf4j
public class OutputAleFileWriterService {
    public void write(AleFile replacedAleFile, String fileLocation) {
        try{
            File file = new File(fileLocation);
            Writer output = new BufferedWriter(new FileWriter(file));
            output.write("File Content");
            output.close();

        }catch(Exception e){
            log.error(String.format("error loading file %s", fileLocation),e.getMessage());
        }
    }
}
