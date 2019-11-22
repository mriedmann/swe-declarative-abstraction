package at.technikum.wien.mse.swe.connector;

import at.technikum.wien.mse.swe.filemapper.FieldConverter;
import at.technikum.wien.mse.swe.filemapper.FieldType;
import at.technikum.wien.mse.swe.model.Amount;
import at.technikum.wien.mse.swe.model.DepotOwner;
import at.technikum.wien.mse.swe.model.ISIN;
import at.technikum.wien.mse.swe.model.RiskCategory;

import java.math.BigDecimal;

/**
 * @author Michael Riedmann
 */
class SecurityFieldType {
    static final FieldType<at.technikum.wien.mse.swe.model.ISIN> ISIN = new FieldType<ISIN>() {
        @Override
        public FieldConverter<ISIN> getConverter() {
            return (c, m) -> new at.technikum.wien.mse.swe.model.ISIN(c);
        }
    };
    static final FieldType<at.technikum.wien.mse.swe.model.RiskCategory> RiskCategory = new FieldType<RiskCategory>() {
        @Override
        public FieldConverter<RiskCategory> getConverter() {
            return (c, f) -> at.technikum.wien.mse.swe.model.RiskCategory.fromCode(c).orElseThrow(IllegalStateException::new);
        }
    };

    private SecurityFieldType() {
    }

    static FieldType<Amount> Amount(int currencyFieldIndex, int valueFieldIndex) {
        return new FieldType<Amount>() {
            @Override
            public FieldConverter<Amount> getConverter() {
                return (c, m) -> new Amount(m.<String>getFieldValue(currencyFieldIndex), m.<BigDecimal>getFieldValue(valueFieldIndex));
            }
        };
    }

    static FieldType<DepotOwner> DepotOwner(int lastnameFieldIndex, int firstnameFieldIndex) {
        return new FieldType<DepotOwner>() {
            @Override
            public FieldConverter<DepotOwner> getConverter() {
                return (c, m) -> {
                    String lastname = m.getFieldValue(lastnameFieldIndex);
                    String firstname = m.getFieldValue(firstnameFieldIndex);
                    DepotOwner owner = new DepotOwner();
                    owner.setLastname(lastname);
                    owner.setFirstname(firstname);
                    return owner;
                };
            }
        };
    }
}
