package at.technikum.wien.mse.swe.filemapper;

public interface FieldConverter<T> {
    T convert(String content, FileMapper mapper);
}

