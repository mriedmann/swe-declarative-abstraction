package at.technikum.wien.mse.swe.filemapper;

public interface FileMapper<T> {
    T map(T instance, String lineContent) throws IllegalAccessException, NoSuchFieldException;
}
