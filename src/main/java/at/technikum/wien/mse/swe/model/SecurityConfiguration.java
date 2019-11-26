package at.technikum.wien.mse.swe.model;

import at.technikum.wien.mse.swe.filemapper.ComponentField;
import at.technikum.wien.mse.swe.filemapper.FixedWidthField;

/**
 * @author MatthiasKreuzriegler
 */
public class SecurityConfiguration {
    @FixedWidthField(start = 40, length = 12)
    private ISIN isin;

    @FixedWidthField(start = 52, length = 2)
    private RiskCategory riskCategory;

    @FixedWidthField(start = 54, length = 30)
    private String name;

    @ComponentField(name = "currency", start = 84, length = 3)
    @ComponentField(name = "value", start = 87, length = 10)
    private Amount yearHighest;

    @ComponentField(name = "currency", start = 84, length = 3)
    @ComponentField(name = "value", start = 97, length = 10)
    private Amount yearLowest;

    public ISIN getIsin() {
        return isin;
    }

    public void setIsin(ISIN isin) {
        this.isin = isin;
    }

    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getYearHighest() {
        return yearHighest;
    }

    public void setYearHighest(Amount yearHighest) {
        this.yearHighest = yearHighest;
    }

    public Amount getYearLowest() {
        return yearLowest;
    }

    public void setYearLowest(Amount yearLowest) {
        this.yearLowest = yearLowest;
    }
}
