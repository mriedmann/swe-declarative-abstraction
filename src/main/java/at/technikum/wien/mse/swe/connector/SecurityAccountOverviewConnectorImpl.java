package at.technikum.wien.mse.swe.connector;

import at.technikum.wien.mse.swe.SecurityAccountOverviewConnector;
import at.technikum.wien.mse.swe.filemapper.FieldType;
import at.technikum.wien.mse.swe.filemapper.FileMapper;
import at.technikum.wien.mse.swe.filemapper.FixedWidthFileMapperBuilder;
import at.technikum.wien.mse.swe.model.SecurityAccountOverview;

import java.nio.file.Path;

/**
 * @author MatthiasKreuzriegler
 */
public class SecurityAccountOverviewConnectorImpl implements
        SecurityAccountOverviewConnector {

    private static final int ACCOUNTNUMBER_START_INDEX = 40;
    private static final int ACCOUNTNUMBER_LENGTH = 10;
    private static final String ACCOUNTNUMBER_PADDING_CHAR = "0";
    private static final int RISKCATEGORY_START_INDEX = 50;
    private static final int RISKCATEGORY_LENGTH = 2;
    private static final int LASTNAME_START_INDEX = 52;
    private static final int LASTNAME_LENGTH = 30;
    private static final int FIRSTNAME_START_INDEX = 82;
    private static final int FIRSTNAME_LENGTH = 30;
    private static final int CURRENCY_START_INDEX = 112;
    private static final int CURRENCY_LENGTH = 3;
    private static final int BALANCE_START_INDEX = 115;
    private static final int BALANCE_LENGTH = 17;

    private static final int ACCOUNTNUMBER = 0;
    private static final int RISKCATEGORY = 1;
    private static final int LASTNAME = 2;
    private static final int FIRSTNAME = 3;
    private static final int DEPOTOWNER = 4;
    private static final int CURRENCY = 5;
    private static final int BALANCE_VALUE = 6;
    private static final int BALANCE = 7;

    @Override
    public SecurityAccountOverview read(Path file) {
        FileMapper mapper = createMapper(file);
        return mapOverview(mapper);
    }

    private FileMapper createMapper(Path file) {
        return (new FixedWidthFileMapperBuilder(file))
                .addField(ACCOUNTNUMBER, FieldType.String, ACCOUNTNUMBER_START_INDEX, ACCOUNTNUMBER_LENGTH, ACCOUNTNUMBER_PADDING_CHAR)
                .addField(RISKCATEGORY, SecurityFieldType.RiskCategory, RISKCATEGORY_START_INDEX, RISKCATEGORY_LENGTH)
                .addField(LASTNAME, FieldType.String, LASTNAME_START_INDEX, LASTNAME_LENGTH)
                .addField(FIRSTNAME, FieldType.String, FIRSTNAME_START_INDEX, FIRSTNAME_LENGTH)
                .addField(DEPOTOWNER, SecurityFieldType.DepotOwner(LASTNAME, FIRSTNAME))
                .addField(CURRENCY, FieldType.String, CURRENCY_START_INDEX, CURRENCY_LENGTH)
                .addField(BALANCE_VALUE, FieldType.BigDecimal, BALANCE_START_INDEX, BALANCE_LENGTH)
                .addField(BALANCE, SecurityFieldType.Amount(CURRENCY, BALANCE_VALUE))
                .build();
    }

    private SecurityAccountOverview mapOverview(FileMapper mapper) {
        SecurityAccountOverview overview = new SecurityAccountOverview();

        overview.setAccountNumber(mapper.getFieldValue(0));
        overview.setRiskCategory(mapper.getFieldValue(1));
        overview.setDepotOwner(mapper.getFieldValue(4));
        overview.setBalance(mapper.getFieldValue(7));

        return overview;
    }


}
