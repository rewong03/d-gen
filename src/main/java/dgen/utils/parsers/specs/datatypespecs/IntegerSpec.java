package dgen.utils.parsers.specs.datatypespecs;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dgen.utils.parsers.SpecificationException;
import dgen.utils.parsers.specs.datatypespecs.distributionspecs.DistributionSpec;
import dgen.utils.parsers.specs.datatypespecs.distributionspecs.UniformDistributionSpec;

@JsonTypeName("int")
public class IntegerSpec implements DataTypeSpec {
    private Integer defaultValue = null;
    private Integer minValue = 0;
    private Integer maxValue = Integer.MAX_VALUE;
    private DistributionSpec distribution = new UniformDistributionSpec();
    private Long randomSeed;

    @Override
    public DataTypes type() { return DataTypes.INT; }

    @Override
    public void validate() {
        if (minValue != null && maxValue != null && minValue >= maxValue) {
            throw new SpecificationException("Int minValue of " + minValue.toString() + " greater than maxValue of "
                    + maxValue.toString());
        }
    }

    @Override
    public DataTypeSpec copy() {
        IntegerSpec integerSpec = new IntegerSpec();
        integerSpec.setDefaultValue(defaultValue);
        integerSpec.setMinValue(minValue);
        integerSpec.setMaxValue(maxValue);
        integerSpec.setDistribution(distribution);
        integerSpec.setRandomSeed(randomSeed);

        return integerSpec;
    }

    public Integer getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public DistributionSpec getDistribution() {
        return distribution;
    }

    public void setDistribution(DistributionSpec distribution) {
        this.distribution = distribution;
    }

    public Long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(Long randomSeed) {
        this.randomSeed = randomSeed;
    }

    @Override
    public java.lang.String toString() {
        return "Int{" +
                "defaultValue=" + defaultValue +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", distribution=" + distribution +
                '}';
    }
}
