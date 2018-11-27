package database.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Right implements DatabaseObject<Right> {

    @JsonProperty
    private int id;
    @JsonProperty
    private boolean canViewStatistics;
    @JsonProperty
    private boolean canEditDilemma;
    @JsonProperty
    private boolean canEditUserInfo;



    public Right (boolean canViewStatistics, boolean canEditDilemma, boolean canEditUserInfo) {
        this.canViewStatistics = canViewStatistics;
        this.canEditDilemma = canEditDilemma;
        this.canEditUserInfo = canEditUserInfo;
    }

    @JsonCreator
    public Right
    (
        @JsonProperty("id") int id,
        @JsonProperty("canViewStatistics") boolean canViewStatistics,
        @JsonProperty("canEditDilemma") boolean canEditDilemma,
        @JsonProperty("canEditUserInfo") boolean canEditUserInfo
    )
    {
        this.id = id;
        this.canViewStatistics = canViewStatistics;
        this.canEditDilemma = canEditDilemma;
        this.canEditUserInfo = canEditUserInfo;
    }



    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public boolean isCanViewStatistics() { return canViewStatistics; }
    public void setCanViewStatistics(boolean canViewStatistics) { this.canViewStatistics = canViewStatistics; }
    public boolean isCanEditDilemma() { return canEditDilemma; }
    public void setCanEditDilemma(boolean canEditDilemma) { this.canEditDilemma = canEditDilemma; }
    public boolean isCanEditUserInfo() { return canEditUserInfo; }
    public void setCanEditUserInfo(boolean canEditUserInfo) { this.canEditUserInfo = canEditUserInfo; }



    @Override
    public String toString() {
        return "Right{" +
                "id=" + id +
                ", canViewStatistics=" + canViewStatistics +
                ", canEditDilemma=" + canEditDilemma +
                ", canEditUserInfo=" + canEditUserInfo +
                '}';
    }
}
