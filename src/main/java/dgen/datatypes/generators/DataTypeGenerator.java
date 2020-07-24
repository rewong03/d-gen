package dgen.datatypes.generators;

import dgen.datatypes.DataType;
import dgen.datatypes.NativeType;
import dgen.distributions.Distribution;

import java.util.List;
import java.util.stream.Stream;

/**
 * A datatype generator is a generator of DataTypes, which are types defined by DGEN
 */
public interface DataTypeGenerator {

    /**
     * Sample one value of type DataType with replacement.
     * @return Generated DataType.
     */
    DataType drawWithReplacement();

    /**
     * Sample one value of type DataType without replacement.
     * @return Generated DataType.
     */
    DataType drawWithoutReplacement();

    NativeType getNativeType();

    DataTypeGenerator copy();
}
