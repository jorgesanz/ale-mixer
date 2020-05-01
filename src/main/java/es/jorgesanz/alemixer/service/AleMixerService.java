package es.jorgesanz.alemixer.service;

import es.jorgesanz.alemixer.model.AleFile;
import es.jorgesanz.alemixer.model.InputParams;
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

    public void mix(InputParams inputParams){
        AleFile baseAleFile = inputAleFileReaderService.read(inputParams.getInputFile1());
        log.info("base .ale file loaded");
        AleFile replacerAleFile = inputAleFileReaderService.read(inputParams.getInuptFile2());
        log.info("replacer .ale file loaded");
        AleFile  replacedAleFile = columnReplaceService.replace(baseAleFile, replacerAleFile);
        log.info("column replacement done");
        outputAleFileWriterService.write(replacedAleFile,inputParams.getOutputFile());
        log.info("output file written");
    }
}
