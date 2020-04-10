package es.jorgesanz.alemixer.model;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class AleFile {

    private Heading heading;
    private List<String> columnNames;
    private List<VideoCut> videoCuts;
}
