package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticRequestModel {

    @JsonProperty
    private Integer[] couples;

    @JsonProperty
    private Integer[] dilemmas;

    public Integer[] getCouples() {
        return couples;
    }

    public Integer[] getDilemmas() {
        return dilemmas;
    }
}
