package database.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoupleRegistry {

    @JsonProperty
    private Parent parent1;
    @JsonProperty
    private Parent parent2;
    @JsonProperty
    private boolean isBorn;
    @JsonProperty
    private long date;



    public CoupleRegistry(
        @JsonProperty("parent1") Parent parent1,
        @JsonProperty("parent2") Parent parent2,
        @JsonProperty("isBorn") boolean isBorn,
        @JsonProperty("date") long date
    ) {
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.isBorn = isBorn;
        this.date = date;
    }



    public Parent getParent1() { return parent1; }
    public void setParent1(Parent parent1) { this.parent1 = parent1; }
    public Parent getParent2() { return parent2; }
    public void setParent2(Parent parent2) { this.parent2 = parent2; }
    public boolean isBorn() { return isBorn; }
    public void setBorn(boolean born) { isBorn = born; }
    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }



    @Override
    public String toString() {
        return "CoupleRegistry{" +
                "parent1=" + parent1 +
                ", parent2=" + parent2 +
                ", isBorn=" + isBorn +
                ", date='" + date + '\'' +
                '}';
    }
}
