package at.technikum.wien.mse.swe.model;

import at.technikum.wien.mse.swe.filemapper.annotations.ComponentField;
import at.technikum.wien.mse.swe.filemapper.annotations.FieldAlignment;
import at.technikum.wien.mse.swe.filemapper.annotations.FixedWidthField;

/**
 * @author MatthiasKreuzriegler
 */
public class SecurityAccountOverview {

    @FixedWidthField(start = 40, length = 10, padding = "0")
    private String accountNumber;

    @FixedWidthField(start = 50, length = 2, alignment = FieldAlignment.RIGHT)
    private RiskCategory riskCategory;

    @ComponentField(name = "lastname", start = 52, length = 30)
    @ComponentField(name = "firstname", start = 82, length = 30)
    private DepotOwner depotOwner;

    @ComponentField(name = "currency", start = 112, length = 3, alignment = FieldAlignment.RIGHT)
    @ComponentField(name = "value", start = 115, length = 17)
    private Amount balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
    }

    public DepotOwner getDepotOwner() {
        return depotOwner;
    }

    public void setDepotOwner(DepotOwner depotOwner) {
        this.depotOwner = depotOwner;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "SecurityAccountOverview{" +
                "accountNumber='" + accountNumber + '\'' +
                ", riskCategory=" + riskCategory +
                ", depotOwner=" + depotOwner +
                ", balance=" + balance +
                '}';
    }
}
