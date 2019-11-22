package at.technikum.wien.mse.swe.filemapper;

public interface FileMapper {
    <T> T getFieldValue(int index);
}
