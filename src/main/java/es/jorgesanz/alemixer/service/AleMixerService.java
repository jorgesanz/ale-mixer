package es.jorgesanz.alemixer.service;

import es.jorgesanz.alemixer.model.AleFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AleMixerService {

    @Autowired
    private InputAleFileReaderService inputAleFileReaderService;

    @Autowired
    private ColumnReplaceService columnReplaceService;

    @Autowired
    private OutputAleFileWriterService outputAleFileWriterService;

    public void mix(){
        AleFile baseAleFile = inputAleFileReaderService.read("A104.ale");
        log.info("base .ale file loaded");
        AleFile replacerAleFile = inputAleFileReaderService.read("A104.ale");
        log.info("replacer .ale file loaded");
        AleFile  replacedAleFile = columnReplaceService.replace(baseAleFile, replacerAleFile);
        log.info("column replacement done");
        outputAleFileWriterService.write(replacedAleFile,"output.ale");
        log.info("output file written");
    }
}
