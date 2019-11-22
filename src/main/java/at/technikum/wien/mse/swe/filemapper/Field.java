package at.technikum.wien.mse.swe.filemapper;

public class Field<T> {
    final FileMapper mapper;
    final FieldConverter<T> converter;

    Field(FileMapper mapper, FieldConverter<T> converter) {
        this.mapper = mapper;
        this.converter = converter;
    }

    public T getValue() {
        return this.converter.convert("", this.mapper);
    }
}
