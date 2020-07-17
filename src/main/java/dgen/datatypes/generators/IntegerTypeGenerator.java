package dgen.datatypes.generators;

import dgen.datatypes.IntegerType;
import dgen.datatypes.config.IntegerTypeConfig;
import dgen.datatypes.DataType;
import dgen.datatypes.NativeType;
import dgen.distributions.Distribution;
import dgen.distributions.GaussianDistribution;

import java.util.Random;

public class IntegerTypeGenerator implements DataTypeGenerator {

    private final NativeType nativeType = NativeType.INTEGER;

    private Integer defaultValue;
    private int lowerBoundDomain;
    private int upperBoundDomain;
    private int sizeInBytes;

    // TODO: we'll likely move all random to a new package so we can keep track of all seeds used to generate the data
    private Random rnd;

    public IntegerTypeGenerator(IntegerTypeConfig dtc) {
        this.defaultValue = dtc.getInt(IntegerTypeConfig.DEFAULT_VALUE);
        this.lowerBoundDomain = dtc.getInt(IntegerTypeConfig.LOWER_BOUND_DOMAIN);
        this.upperBoundDomain = dtc.getInt(IntegerTypeConfig.UPPER_BOUND_DOMAIN);
        this.sizeInBytes = dtc.getInt(IntegerTypeConfig.SIZE_IN_BYTES);

        rnd = new Random();
    }

    private IntegerTypeGenerator() {
        this.lowerBoundDomain = 0;
        this.upperBoundDomain = Integer.MAX_VALUE;
        this.sizeInBytes = Integer.SIZE;

        rnd = new Random();
    }

    public static IntegerTypeGenerator makeDefault() {
        return new IntegerTypeGenerator();
    }

    @Override
    public DataType drawWithReplacement(Distribution samplingDistribution) {
        // TODO: Maybe move this to another function since it doesn't depend on samplingDistribution
        if (defaultValue != null) { return new IntegerType(defaultValue); }

        // TODO: unless we need to know the specific distribution here, this switch should dissappear.
        switch(samplingDistribution.distributionType()) {
            case UNIFORM:
                return uniformSample();
            case GAUSSIAN:
                return gaussianSample((GaussianDistribution) samplingDistribution);
            case ZIPF:
                // TODO: to implement
                return null;
        }
        return null;
    }

    @Override
    public DataType drawWithoutReplacement(Distribution samplingDistribution) {
        return null;
    }

    private DataType uniformSample() {
        int value = rnd.nextInt(upperBoundDomain - lowerBoundDomain + 1) + lowerBoundDomain;
        return new IntegerType(value);
    }

    private DataType gaussianSample(GaussianDistribution gaussianDistribution) {
        float standardDeviation = gaussianDistribution.getStandardDeviation();
        float mean = gaussianDistribution.getMean();

        return new IntegerType((int) (rnd.nextGaussian() * standardDeviation + mean));
    }
}
