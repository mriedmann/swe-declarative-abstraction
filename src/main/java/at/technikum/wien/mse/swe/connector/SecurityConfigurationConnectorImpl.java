package at.technikum.wien.mse.swe.connector;

import at.technikum.wien.mse.swe.SecurityConfigurationConnector;
import at.technikum.wien.mse.swe.filemapper.FieldType;
import at.technikum.wien.mse.swe.filemapper.FileMapper;
import at.technikum.wien.mse.swe.filemapper.FixedWidthFileMapperBuilder;
import at.technikum.wien.mse.swe.model.SecurityConfiguration;

import java.nio.file.Path;

/**
 * @author MatthiasKreuzriegler
 */
public class SecurityConfigurationConnectorImpl implements SecurityConfigurationConnector {

    private static final int ISIN_START_INDEX = 40;
    private static final int ISIN_LENGTH = 12;
    private static final int RISKCATEGORY_START_INDEX = 52;
    private static final int RISKCATEGORY_LENGTH = 2;
    private static final int NAME_START_INDEX = 54;
    private static final int NAME_LENGTH = 30;
    private static final int CURRENCY_START_INDEX = 84;
    private static final int CURRENCY_LENGTH = 3;
    private static final int HIGHEST_START_INDEX = 87;
    private static final int HIGHEST_LENGTH = 10;
    private static final int LOWEST_START_INDEX = 97;
    private static final int LOWEST_LENGTH = 10;


    private static final int ISIN = 0;
    private static final int RISKCATEGORY = 1;
    private static final int NAME = 2;
    private static final int CURRENCY = 3;
    private static final int HIGHEST_VALUE = 4;
    private static final int HIGHEST = 5;
    private static final int LOWEST_VALUE = 6;
    private static final int LOWEST = 7;


    @Override
    public SecurityConfiguration read(Path file) {
        FileMapper mapper = createMapper(file);
        return mapConficuration(mapper);
    }

    private FileMapper createMapper(Path file) {
        return (new FixedWidthFileMapperBuilder(file))
                .addField(ISIN, SecurityFieldType.ISIN, ISIN_START_INDEX, ISIN_LENGTH)
                .addField(RISKCATEGORY, SecurityFieldType.RiskCategory, RISKCATEGORY_START_INDEX, RISKCATEGORY_LENGTH)
                .addField(NAME, FieldType.String, NAME_START_INDEX, NAME_LENGTH)
                .addField(CURRENCY, FieldType.String, CURRENCY_START_INDEX, CURRENCY_LENGTH)
                .addField(HIGHEST_VALUE, FieldType.BigDecimal, HIGHEST_START_INDEX, HIGHEST_LENGTH)
                .addField(HIGHEST, SecurityFieldType.Amount(CURRENCY, HIGHEST_VALUE))
                .addField(LOWEST_VALUE, FieldType.BigDecimal, LOWEST_START_INDEX, LOWEST_LENGTH)
                .addField(LOWEST, SecurityFieldType.Amount(CURRENCY, LOWEST_VALUE))
                .build();
    }

    private SecurityConfiguration mapConficuration(FileMapper mapper) {
        SecurityConfiguration configuration = new SecurityConfiguration();

        configuration.setIsin(mapper.getFieldValue(ISIN));
        configuration.setRiskCategory(mapper.getFieldValue(RISKCATEGORY));
        configuration.setName(mapper.getFieldValue(NAME));
        configuration.setYearHighest(mapper.getFieldValue(HIGHEST));
        configuration.setYearLowest(mapper.getFieldValue(LOWEST));

        return configuration;
    }
}
