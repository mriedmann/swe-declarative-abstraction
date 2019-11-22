package at.technikum.wien.mse.swe.filemapper;

import java.math.BigDecimal;

public abstract class FieldType<T> {
    public static final FieldType<String> String = new FieldType<String>() {
        @Override
        public FieldConverter<String> getConverter() {
            return (c, m) -> c;
        }
    };
    public static final FieldType<BigDecimal> BigDecimal = new FieldType<BigDecimal>() {
        @Override
        public FieldConverter<BigDecimal> getConverter() {
            return (c, m) -> java.math.BigDecimal.valueOf(Double.valueOf(c));
        }
    };

    public abstract FieldConverter<T> getConverter();
}
