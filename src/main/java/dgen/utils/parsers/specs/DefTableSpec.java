package dgen.utils.parsers.specs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import dgen.utils.parsers.SpecificationException;
import dgen.utils.parsers.specs.relationshipspecs.TableRelationshipSpec;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("defTable")
@JsonPropertyOrder({"tableID", "randomSeed", "tableName", "numRows", "regexName", "randomName", "columnSpecs", "tableRelationships"})
public class DefTableSpec implements TableSpec {

    private int tableID;
    private Integer numRows = null;
    @JsonProperty("columns")
    private List<ColumnSpec> columnSpecs;
    private String tableName;
    private String regexName;
    private boolean randomName = true;
    private List<TableRelationshipSpec> tableRelationships = new ArrayList<>();
    private Long randomSeed;

    @Override
    public SpecType specType() { return SpecType.DEFTABLE; }

    @Override
    public void validate() {
        if (numRows == null || numRows <= 0) {
            throw new SpecificationException("numRows must be greater than 0");
        }
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public Integer getNumRows() {
        return numRows;
    }

    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    public List<ColumnSpec> getColumnSpecs() {
        return columnSpecs;
    }

    public void setColumnSpecs(List<ColumnSpec> columnSpecs) {
        this.columnSpecs = columnSpecs;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRegexName() {
        return regexName;
    }

    public void setRegexName(String regexName) {
        this.regexName = regexName;
    }

    public boolean isRandomName() {
        return randomName;
    }

    public void setRandomName(boolean randomName) {
        this.randomName = randomName;
    }

    public List<TableRelationshipSpec> getTableRelationships() {
        return tableRelationships;
    }

    public void setTableRelationships(List<TableRelationshipSpec> tableRelationships) {
        this.tableRelationships = tableRelationships;
    }

    public Long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(Long randomSeed) {
        this.randomSeed = randomSeed;
    }

    @Override
    public String toString() {
        return "DefTableSchema{" +
                "tableID=" + tableID +
                ", numRows=" + numRows +
                ", columnSchemas=" + columnSpecs +
                ", tableName='" + tableName + '\'' +
                ", regexName='" + regexName + '\'' +
                ", randomName=" + randomName +
                ", tableRelationships=" + tableRelationships +
                '}';
    }
}
