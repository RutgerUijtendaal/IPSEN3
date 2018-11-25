package api.factories;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class GenericDaoFactory {

    @NotNull
    @JsonProperty
    private String tableName;

    @NotNull
    @JsonProperty
    private String[] columnNames;


    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public String[] getColumnNames() { return columnNames; }
    public void setColumnNames(String[] columnNames) { this.columnNames = columnNames; }

}
