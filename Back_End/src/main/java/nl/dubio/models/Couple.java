package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Couple implements DatabaseObject<Couple> {

    @JsonProperty
    private int id;
    @JsonProperty
    private Date signupDate;
    @JsonProperty
    private int parent1Id;
    @JsonProperty
    private int parent2Id;

    public Couple (Date signupDate, int parent1Id, int parent2Id) {
        this.signupDate = signupDate;
        this.parent1Id = parent1Id;
        this.parent2Id = parent2Id;
    }

    @JsonCreator
    public Couple
    (
        @JsonProperty("id") int id,
        @JsonProperty("signupDate") Date signupDate,
        @JsonProperty("parent1Id") int parent1Id,
        @JsonProperty("parent2Id") int parent2Id
    )
    {
        this.id = id;
        this.signupDate = signupDate;
        this.parent1Id = parent1Id;
        this.parent2Id = parent2Id;
    }

    public Date getSignupDate() { return signupDate; }
    public void setSignupDate(Date signupDate) { this.signupDate = signupDate; }
    public int getParent1Id() { return parent1Id; }
    public void setParent1Id(int parent1Id) { this.parent1Id = parent1Id; }
    public int getParent2Id() { return parent2Id; }
    public void setParent2Id(int parent2Id) { this.parent2Id = parent2Id; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Couple{" +
                "id=" + id +
                ", signupDate=" + signupDate +
                ", parent1Id=" + parent1Id +
                ", parent2Id=" + parent2Id +
                '}';
    }
}
