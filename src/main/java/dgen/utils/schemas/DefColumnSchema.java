package dgen.utils.schemas;


import com.fasterxml.jackson.annotation.JsonTypeName;
import dgen.utils.schemas.datatypes.DataType;

@JsonTypeName("defColumn")
public class DefColumnSchema implements ColumnSchema {

    public static final String [] REQUIRED_PARAMETERS = {"columnID", "dataType"};
    public static final String [] OPTIONAL_PARAMETERS = {"columnName", "regexName", "randomName", "unique", "hasNull", "nullFrequency"};

    private int columnID;
    private DataType dataType; // Will change this to more specific type
    private String columnName;
    private String regexName;
    private boolean randomName = true;
    private boolean unique = false;
    private boolean hasNull = false;
    private float nullFrequency;
    
    @Override
    public String schemaType() { return "defined"; }

    public int getColumnID() {
        return columnID;
    }

    public void setColumnID(int columnID) {
        this.columnID = columnID;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
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

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isHasNull() {
        return hasNull;
    }

    public void setHasNull(boolean hasNull) {
        this.hasNull = hasNull;
    }

    public float getNullFrequency() {
        return nullFrequency;
    }

    public void setNullFrequency(float nullFrequency) {
        this.nullFrequency = nullFrequency;
    }

    @Override
    public String toString() {
        return "DefColumnSchema{" +
                "columnID=" + columnID +
                ", dataType=" + dataType +
                ", columnName='" + columnName + '\'' +
                ", regexName='" + regexName + '\'' +
                ", randomName=" + randomName +
                ", unique=" + unique +
                ", hasNull=" + hasNull +
                ", nullFrequency=" + nullFrequency +
                '}';
    }
}