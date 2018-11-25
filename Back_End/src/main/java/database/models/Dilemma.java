package database.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Dilemma implements DatabaseObject<Dilemma> {

    @JsonProperty
    private int id;
    @JsonProperty
    private short weekNr;
    @JsonProperty
    private String theme;
    @JsonProperty
    private String feedback;

    @JsonCreator
    public Dilemma(@JsonProperty("weekNr") short weekNr,
                   @JsonProperty("theme") String theme,
                   @JsonProperty("feedback") String feedback) {
        this.weekNr = weekNr;
        this.theme = theme;
        this.feedback = feedback;
    }

    @JsonCreator
    public Dilemma(@JsonProperty("id")int id,
                   @JsonProperty("weekNr") short weekNr,
                   @JsonProperty("theme") String theme,
                   @JsonProperty("feedback") String feedback) {
        this.id = id;
        this.weekNr = weekNr;
        this.theme = theme;
        this.feedback = feedback;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public short getWeekNr() {
        return weekNr;
    }
    public void setWeekNr(short weekNr) {
        this.weekNr = weekNr;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    @Override
    public String toString() {
        return "Dilemma{" +
                "id=" + id +
                ", weekNr=" + weekNr +
                ", theme='" + theme + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
