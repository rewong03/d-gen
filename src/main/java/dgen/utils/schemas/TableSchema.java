package dgen.utils.schemas;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({@JsonSubTypes.Type(value = GenTableSchema.class, name = "genTable"),
        @JsonSubTypes.Type(value = DefTableSchema.class, name = "defTable")})
@JsonTypeName("table")
public interface TableSchema {
    public String schemaType();
}