package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticRequestModel {

    @JsonProperty
    private Integer[] couples;

    public StatisticRequestModel(@JsonProperty("dilemmas") Integer[] couples) {
        this.couples = couples;
    }

    public Integer[] getCouples() {
        return couples;
    }
}
