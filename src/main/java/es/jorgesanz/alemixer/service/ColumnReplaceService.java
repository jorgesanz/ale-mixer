package es.jorgesanz.alemixer.service;

import es.jorgesanz.alemixer.model.AleFile;
import es.jorgesanz.alemixer.model.VideoCut;
import org.springframework.stereotype.Service;

@Service
public class ColumnReplaceService {
    public static final String ASC_SOP = "ASC_SOP";
    public static final String ASC_SAT = "ASC_SAT";
    public static final String SOURCE_RESOLUTION = "Source Resolution";
    public static final String CAMERA_TYPE = "Camera Type";
    public static final String COLUMN_ID = "Name";

    public AleFile replace(AleFile baseAleFile, AleFile replacerAleFile) {

        baseAleFile.getVideoCuts().stream().forEach(videoCut -> {
            VideoCut replacerVideoCut =
                    replacerAleFile.getVideoCuts().stream()
                            .filter(replacer -> replacer.getColumns().get(COLUMN_ID).equals(videoCut.getColumns().get(COLUMN_ID)))
                            .findFirst().orElse(videoCut);

            videoCut.getColumns().put(ASC_SOP,replacerVideoCut.getColumns().get(ASC_SOP));
            videoCut.getColumns().put(ASC_SAT,replacerVideoCut.getColumns().get(ASC_SAT));
            videoCut.getColumns().put(SOURCE_RESOLUTION,replacerVideoCut.getColumns().get(SOURCE_RESOLUTION));
            videoCut.getColumns().put(CAMERA_TYPE,replacerVideoCut.getColumns().get(CAMERA_TYPE));

        });

        return baseAleFile;
    }
}
