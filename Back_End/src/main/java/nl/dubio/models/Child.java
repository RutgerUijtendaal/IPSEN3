package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.sql.Date;

public class Child implements DatabaseObject<Child> {

    @JsonProperty
    private int id;
    @JsonProperty
    private int coupleId;
    @JsonProperty
    private Date date;
    @JsonProperty
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

    public int getAgeInWeeks() {
        DateTime now = new DateTime();
        DateTime birthDate = new DateTime(date);

        Weeks weeks = Weeks.weeksBetween(birthDate, now);

        return weeks.getWeeks();
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
