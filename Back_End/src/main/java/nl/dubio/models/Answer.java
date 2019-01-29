package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer implements DatabaseObject<Answer> {

    @JsonProperty
    private int id;
    @JsonProperty
    private int dilemmaId;
    @JsonProperty
    private String extension;
    @JsonProperty
    private String text;

    public Answer (int dilemmaId, String extension, String text) {
        this.dilemmaId = dilemmaId;
        this.extension = extension;
        this.text = text;
    }

    @JsonCreator
    public Answer
    (
        @JsonProperty("id") int id,
        @JsonProperty("dilemmaId") int dilemmaId,
        @JsonProperty("extension") String extension,
        @JsonProperty("text") String text
    )
    {
        this.id = id;
        this.dilemmaId = dilemmaId;
        this.extension = extension;
        this.text = text;
    }

    public void setId(int id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setExtension(String extension) { this.extension = extension; }
    public void setDilemmaId(int dilemmaId) { this.dilemmaId = dilemmaId; }
    public int getId() { return id; }
    public String getText() { return text; }
    public String getExtension() { return extension; }
    public int getDilemmaId() { return dilemmaId; }
    public boolean hasImage() { return this.extension != null; }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", dilemmaId=" + dilemmaId +
                ", extension='" + extension + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
