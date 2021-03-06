package dgen.column;

import dgen.attributegenerators.AttributeNameGenerator;
import dgen.attributegenerators.DefaultAttributeNameGenerator;
import dgen.attributegenerators.RandomAttributeNameGenerator;
import dgen.attributegenerators.RegexAttributeNameGenerator;
import dgen.coreconfig.DGException;
import dgen.datatypes.DataType;
import dgen.datatypes.config.DataTypeConfig;
import dgen.datatypes.generators.DataTypeGenerator;
import dgen.datatypes.generators.NullValueGenerator;
import dgen.utils.parsers.RandomGenerator;
import dgen.utils.parsers.specs.SpecType;
import dgen.utils.parsers.specs.datatypespecs.DataTypeSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * A column generator is a combination of a datatype generator and a null value generator (TODO: and others)
 */
public class ColumnGenerator {

    private DataTypeGenerator dtg;
    private NullValueGenerator cng;
    private AttributeNameGenerator ang;

    private int columnID;
    private RandomGenerator rnd;
    private boolean unique = false;
    private ColumnConfig columnConfig;

    public ColumnGenerator(DataTypeGenerator dtg, AttributeNameGenerator ang) {
        this.dtg = dtg;
        this.ang = ang;
    }

    public ColumnGenerator(ColumnConfig columnConfig) {
        this.columnConfig = columnConfig;
        columnID = columnConfig.getInt(ColumnConfig.COLUMN_ID);
        rnd = new RandomGenerator(columnConfig.getLong(ColumnConfig.RANDOM_SEED));

        if (columnConfig.getString(ColumnConfig.COLUMN_NAME) != null) {
            this.ang = new DefaultAttributeNameGenerator(columnConfig.getString(ColumnConfig.COLUMN_NAME));
        } else if (columnConfig.getString(ColumnConfig.REGEX_NAME) != null) {
            this.ang = new RegexAttributeNameGenerator(rnd, columnConfig.getString(ColumnConfig.REGEX_NAME));
        } else if (columnConfig.getBoolean(ColumnConfig.RANDOM_NAME)) {
            this.ang = new RandomAttributeNameGenerator(rnd);
        }

        if (columnConfig.getObject(ColumnConfig.COLUMN_TYPE) == SpecType.DEFFOREIGNKEY) {
            this.dtg = null;
        } else {
            this.dtg = DataTypeConfig.specToGenerator((DataTypeSpec) columnConfig.getObject(ColumnConfig.DATATYPE));
        }

        //TODO: Add logic for nulls
        this.unique = columnConfig.getBoolean(ColumnConfig.UNIQUE);
    }

    public Column generateColumn(int numRecords) {
        if (dtg == null) {
            throw new DGException("Missing data generator");
        }

        List<DataType> values = new ArrayList<>();
        for(int i = 0; i < numRecords; i++) {
            DataType dt;

            if (unique) {
                dt = this.dtg.drawWithoutReplacement();
            } else {
                dt = this.dtg.drawWithReplacement();
            }
            values.add(dt);
        }

        String attributeName = ang.generateAttributeName();
        Column c = new Column(columnID, attributeName, values);

        return c;
    }

    public String generateName() {
        return ang.generateAttributeName();
    }

    public DataType generateData() {
        if (dtg == null) {
            throw new DGException("Missing data generator");
        }

        if (unique) {
            return this.dtg.drawWithoutReplacement();
        } else {
            return this.dtg.drawWithReplacement();
        }
    }

    public ArrayList<DataType> generateData(int numRecords) {
        if (dtg == null) {
            throw new DGException("Missing data generator");
        }

        ArrayList<DataType> data = new ArrayList<>();

        for (int i = 0; i < numRecords; i++) {
            if (unique) {
                data.add(this.dtg.drawWithoutReplacement());
            } else {
                data.add(this.dtg.drawWithReplacement());
            }
        }

        return data;
    }

    public ColumnGenerator copy() {
        ColumnGenerator columnGenerator = new ColumnGenerator(this.columnConfig);
        columnGenerator.setDtg(this.dtg.copy());
        return columnGenerator;
    }

    public int getColumnID() {
        return columnID;
    }

    public boolean isUnique() {
        return unique;
    }

    public RandomGenerator getRandomGenerator() { return rnd; }

    public DataTypeGenerator getDtg() {
        return dtg;
    }

    public void setDtg(DataTypeGenerator dtg) {
        this.dtg = dtg;
    }

    public ColumnConfig getColumnConfig() {
        return columnConfig;
    }

    @Override
    public String toString() {
        return "ColumnGenerator{" +
                "dtg=" + dtg +
                ", cng=" + cng +
                ", ang=" + ang +
                ", columnID=" + columnID +
                ", unique=" + unique +
                '}';
    }
}
