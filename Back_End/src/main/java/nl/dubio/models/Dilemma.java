package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.dubio.View;

public class Dilemma implements DatabaseObject<Dilemma> {

    @JsonProperty
    @JsonView(View.Public.class)
    private int id;
    @JsonProperty
    @JsonView(View.Public.class)
    private short weekNr;
    @JsonProperty
    @JsonView(View.Public.class)
    private String theme;
    @JsonProperty
    @JsonView(View.Public.class)
    private String feedback;
    @JsonProperty
    @JsonView(View.Public.class)
    private String period;

    public Dilemma (short weekNr, String theme, String feedback, String period) {
        this.weekNr = weekNr;
        this.theme = theme;
        this.feedback = feedback;
        this.period = period;
    }

    @JsonCreator
    public Dilemma
    (
        @JsonProperty("id")int id,
        @JsonProperty("weekNr") short weekNr,
        @JsonProperty("theme") String theme,
        @JsonProperty("feedback") String feedback,
        @JsonProperty("period") String period
    )
    {
        this.id = id;
        this.weekNr = weekNr;
        this.theme = theme;
        this.feedback = feedback;
        this.period = period;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public short getWeekNr() { return weekNr; }
    public void setWeekNr(short weekNr) { this.weekNr = weekNr; }
    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    public String getPeriod() { return period; }
    public void setperiod(String period) { this.period = period; }

    @Override
    public String toString() {
        return "Dilemma{" +
                "id=" + id +
                ", weekNr=" + weekNr +
                ", theme='" + theme + '\'' +
                ", feedback='" + feedback + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}
