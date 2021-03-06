package dgen.utils.parsers.specs.relationshipspecs.dependencyFunctions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import dgen.utils.parsers.specs.ColumnSpec;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({@JsonSubTypes.Type(value = JaccardSimilarity.class, name = "jaccardSimilarity"),
        @JsonSubTypes.Type(value = FunctionalDependency.class, name = "functionalDependency")})
@JsonTypeName("dependencyFunction")
public interface DependencyFunction {
    FunctionType dependencyName();
    void validate(ColumnSpec start, ColumnSpec end);
}
