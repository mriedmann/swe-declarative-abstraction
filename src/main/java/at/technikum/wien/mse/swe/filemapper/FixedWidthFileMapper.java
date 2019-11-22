package at.technikum.wien.mse.swe.filemapper;

import java.util.List;

public class FixedWidthFileMapper implements FileMapper {
    private List<Field> fields;

    @Override
    public <T> T getFieldValue(int index) {
        Field<T> field = fields.get(index);
        return field.getValue();
    }

    void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
