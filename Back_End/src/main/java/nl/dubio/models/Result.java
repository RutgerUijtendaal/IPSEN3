package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Result implements DatabaseObject<Result> {

    @JsonProperty
    private int id;
    @JsonProperty
    private int parentId;
    @JsonProperty
    private Integer answerId;
    @JsonProperty
    private Timestamp sentTime;
    @JsonProperty
    private Timestamp answeredTime;

    public Result (int parentId, Integer answerId, Timestamp sentTime, Timestamp answeredTime) {
        this.parentId = parentId;
        this.answerId = answerId;
        this.sentTime = sentTime;
        this.answeredTime = answeredTime;
    }

    @JsonCreator
    public Result
    (
        @JsonProperty("id") int id,
        @JsonProperty("parentId") int parentId,
        @JsonProperty("answerId") Integer answerId,
        @JsonProperty("sentTime") Timestamp sentTime,
        @JsonProperty("answeredTime") Timestamp answeredTime
    )
    {
        this.id = id;
        this.parentId = parentId;
        this.answerId = answerId;
        this.sentTime = sentTime;
        this.answeredTime = answeredTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getParentId() { return parentId; }
    public void setParentId(int parentId) { this.parentId = parentId; }
    public Integer getAnswerId() { return answerId; }
    public void setAnswerId(Integer answerId) { this.answerId = answerId; }
    public Timestamp getAnsweredTime() { return answeredTime; }
    public void setAnsweredTime(Timestamp answeredTime) { this.answeredTime = answeredTime; }
    public Timestamp getSentTime() { return sentTime; }
    public void setSentTime(Timestamp sentTime) { this.sentTime = sentTime; }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", answerId=" + answerId +
                ", sentTime=" + sentTime +
                ", answeredTime=" + answeredTime +
                '}';
    }
}
