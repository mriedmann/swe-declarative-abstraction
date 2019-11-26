package at.technikum.wien.mse.swe.filemapper;

interface FileMapperFactory {
    <T> FileMapper<T> createMapper(Class<T> model);
}
