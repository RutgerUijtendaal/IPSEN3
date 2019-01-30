package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Config implements DatabaseObject<Config>{
    @JsonProperty
    private String id;

    @JsonProperty
    private String value;

    @JsonCreator
    public Config(@JsonProperty("id") String id, @JsonProperty("value") String value) {
        this.id = id;
        this.value = value;
    }

    public String getName() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return 0;
    }
}
