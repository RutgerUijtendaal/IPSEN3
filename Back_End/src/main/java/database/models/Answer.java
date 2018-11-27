package database.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer implements DatabaseObject<Answer> {

    @JsonProperty
    private int id;
    @JsonProperty
    private int dilemmaId;
    @JsonProperty
    private String url;
    @JsonProperty
    private String text;



    public Answer (int dilemmaId, String url, String text) {
        this.dilemmaId = dilemmaId;
        this.url = url;
        this.text = text;
    }

    @JsonCreator
    public Answer
    (
        @JsonProperty("id") int id,
        @JsonProperty("dilemmaId") int dilemmaId,
        @JsonProperty("url") String url,
        @JsonProperty("text") String text
    )
    {
        this.id = id;
        this.dilemmaId = dilemmaId;
        this.url = url;
        this.text = text;
    }



    public void setId(int id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setUrl(String url) { this.url = url; }
    public void setDilemmaId(int dilemmaId) { this.dilemmaId = dilemmaId; }
    public int getId() { return id; }
    public String getText() { return text; }
    public String getUrl() { return url; }
    public int getDilemmaId() { return dilemmaId; }
    public boolean hasImage() { return this.url != null; }



    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", dilemmaId=" + dilemmaId +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
