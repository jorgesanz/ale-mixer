package es.jorgesanz.alemixer.model;

public class Heading {

    private String fieldDelimiter;
    private String videoFormat;
    private String audioFormat;
    private String fps;

    public String getFieldDelimiter() {
        return fieldDelimiter;
    }

    public void setFieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    public String getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    public String getFps() {
        return fps;
    }

    public void setFps(String fps) {
        this.fps = fps;
    }
}
