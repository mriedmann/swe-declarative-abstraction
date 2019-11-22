package at.technikum.wien.mse.swe.filemapper;

import java.nio.file.Path;
import java.util.ArrayList;


public class FixedWidthFileMapperBuilder implements FileMapperBuilder {
    private final FixedWidthFileMapper mapper;
    private final FixedWidthFile file;
    private final ArrayList<Field> fields;

    public FixedWidthFileMapperBuilder(Path path) {
        this.fields = new ArrayList<>();
        this.file = new FixedWidthFile(path);
        this.mapper = new FixedWidthFileMapper();
    }

    @Override
    public FileMapper build() {
        mapper.setFields(fields);
        return mapper;
    }

    public <T> FixedWidthFileMapperBuilder addField(int index, FieldType<T> fieldType, int startIndex, int length) {
        return this.addField(index, fieldType, startIndex, length, " ");
    }

    public <T> FixedWidthFileMapperBuilder addField(int index, FieldType<T> fieldType, int startIndex, int length, String paddingChar) {
        FixedWidthField field = new FixedWidthField<>(mapper, fieldType.getConverter(), file, startIndex, length, paddingChar);
        fields.set(index, field);
        return this;
    }

    public <T> FixedWidthFileMapperBuilder addField(int index, FieldType<T> fieldType) {
        Field field = new Field<T>(mapper, fieldType.getConverter());
        fields.set(index, field);
        return this;
    }
}
