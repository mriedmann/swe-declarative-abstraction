package at.technikum.wien.mse.swe.connector;

import at.technikum.wien.mse.swe.SecurityAccountOverviewConnector;
import at.technikum.wien.mse.swe.exception.SecurityAccountOverviewReadException;
import at.technikum.wien.mse.swe.filemapper.FileMapper;
import at.technikum.wien.mse.swe.filemapper.FileMapperFactoryImpl;
import at.technikum.wien.mse.swe.model.SecurityAccountOverview;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author MatthiasKreuzriegler
 */
public class SecurityAccountOverviewConnectorImpl implements
        SecurityAccountOverviewConnector {

    private final FileMapper<SecurityAccountOverview> mapper;

    public SecurityAccountOverviewConnectorImpl() {
        mapper = (new FileMapperFactoryImpl()).createMapper(SecurityAccountOverview.class);
    }

    @Override
    public SecurityAccountOverview read(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return mapper.map(new SecurityAccountOverview(), reader.readLine());
        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            throw new SecurityAccountOverviewReadException(e);
        }
    }
}
