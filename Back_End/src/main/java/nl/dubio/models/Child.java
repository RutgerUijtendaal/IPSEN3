package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.dubio.View;
import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.sql.Date;

public class Child implements DatabaseObject<Child> {

    @JsonProperty
    @JsonView(View.Public.class)
    private int id;
    @JsonProperty
    @JsonView(View.Public.class)
    private int coupleId;
    @JsonProperty
    @JsonView(View.Public.class)
    private Date date;
    @JsonProperty
    @JsonView(View.Public.class)
    private boolean isBorn;

    public Child (int coupleId, Date date, boolean isBorn) {
        this.coupleId = coupleId;
        this.date = date;
        this.isBorn = isBorn;
    }

    @JsonCreator
    public Child
    (
        @JsonProperty("id") int id,
        @JsonProperty("coupleId") int coupleId,
        @JsonProperty("date") Date date,
        @JsonProperty("isBorn") boolean isBorn
    )
    {
        this.id = id;
        this.coupleId = coupleId;
        this.date = date;
        this.isBorn = isBorn;
    }

    public void setId(int id){ this.id = id; }
    public void setCoupleId(int coupleId) { this.coupleId = coupleId; }
    public void setDate(Date date) { this.date = date; }
    public void setIsBorn(boolean isBorn) { this.isBorn = isBorn; }
    public int getId(){ return id; }
    public int getCoupleId() { return coupleId; }
    public Date getDate() { return date; }
    public boolean getIsBorn() { return isBorn; }

    public short getAgeInWeeks() {
        DateTime now = new DateTime();
        DateTime birthDate = new DateTime(date);

        Weeks weeks = Weeks.weeksBetween(birthDate, now);

        return (short) weeks.getWeeks();
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", coupleId=" + coupleId +
                ", date=" + date +
                ", isBorn=" + isBorn +
                '}';
    }
}
