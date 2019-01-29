package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Rating implements DatabaseObject<Rating> {

    @JsonProperty
    private int id;
    @JsonProperty
    private int dilemmaId;
    @JsonProperty
    private int rating;
    @JsonProperty
    private Timestamp date;

    public Rating(int dilemmaId, int rating, Timestamp date) {
        this.id = id;
        this.dilemmaId = dilemmaId;
        this.rating = rating;
        this.date = date;
    }

    @JsonCreator
    public Rating(
            @JsonProperty("id") int id,
            @JsonProperty("dilemmaId") int dilemmaId,
            @JsonProperty("rating") int rating,
            @JsonProperty("date") Timestamp date
    ) {
        this.id = id;
        this.dilemmaId = dilemmaId;
        this.rating = rating;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDilemmaId() {
        return dilemmaId;
    }

    public void setDilemmaId(int dilemmaId) {
        this.dilemmaId = dilemmaId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", dilemmaId=" + dilemmaId +
                ", rating=" + rating +
                ", date=" + date +
                '}';
    }
}
