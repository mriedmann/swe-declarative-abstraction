package at.technikum.wien.mse.swe.filemapper;

public interface FileMapperFactory {
    <T> FileMapper<T> createMapper(Class<T> model);
}
