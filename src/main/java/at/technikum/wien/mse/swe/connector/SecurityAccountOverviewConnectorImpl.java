package at.technikum.wien.mse.swe.connector;

import static org.apache.commons.lang.StringUtils.stripStart;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

import at.technikum.wien.mse.swe.SecurityAccountOverviewConnector;
import at.technikum.wien.mse.swe.exception.SecurityAccountOverviewReadException;
import at.technikum.wien.mse.swe.model.Amount;
import at.technikum.wien.mse.swe.model.DepotOwner;
import at.technikum.wien.mse.swe.model.RiskCategory;
import at.technikum.wien.mse.swe.model.SecurityAccountOverview;

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

    @Override
    public SecurityAccountOverview read(Path file) {
        String content = readFileContent(file);
        return mapOverview(content);
    }

    private SecurityAccountOverview mapOverview(String content) {
        SecurityAccountOverview overview = new SecurityAccountOverview();
        overview.setAccountNumber(getAccountNumber(content));
        overview.setRiskCategory(getRiskCategory(content));
        overview.setDepotOwner(getDepotOwner(content));
        overview.setBalance(getBalance(content));
        return overview;
    }

    private String readFileContent(Path file) {
        String content;
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            content = reader.readLine();
        } catch (IOException e) {
            throw new SecurityAccountOverviewReadException(e);
        }
        return content;
    }

    private Amount getBalance(String content) {
        String currency = getCurrency(content);
        BigDecimal balanceValue = getBalanceValue(content);
        return new Amount(currency, balanceValue);
    }

    private DepotOwner getDepotOwner(String content) {
        DepotOwner owner = new DepotOwner();
        owner.setLastname(getLastName(content));
        owner.setFirstname(getFirstname(content));
        return owner;
    }

    private BigDecimal getBalanceValue(String content) {
        return BigDecimal.valueOf(
                Double.valueOf(extract(content, BALANCE_START_INDEX, BALANCE_LENGTH)));
    }

    private String getCurrency(String content) {
        return extract(content, CURRENCY_START_INDEX, CURRENCY_LENGTH).trim();
    }

    private String getFirstname(String content) {
        return extract(content, FIRSTNAME_START_INDEX, FIRSTNAME_LENGTH).trim();
    }

    private String getLastName(String content) {
        return extract(content, LASTNAME_START_INDEX, LASTNAME_LENGTH).trim();
    }

    private String getAccountNumber(String content) {
        return stripStart(
                extract(content, ACCOUNTNUMBER_START_INDEX, ACCOUNTNUMBER_LENGTH),
                ACCOUNTNUMBER_PADDING_CHAR);
    }

    private String extract(String content, int startIndex, int length) {
        return content.substring(startIndex, startIndex + length);
    }

    private RiskCategory getRiskCategory(String content) {
        return RiskCategory.fromCode(
                extract(content, RISKCATEGORY_START_INDEX, RISKCATEGORY_LENGTH).trim())
                .orElseThrow(IllegalStateException::new);
    }

}
