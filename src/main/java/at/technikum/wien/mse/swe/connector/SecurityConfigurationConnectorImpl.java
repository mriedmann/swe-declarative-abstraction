package at.technikum.wien.mse.swe.connector;

import at.technikum.wien.mse.swe.SecurityConfigurationConnector;
import at.technikum.wien.mse.swe.exception.SecurityAccountOverviewReadException;
import at.technikum.wien.mse.swe.filemapper.FileMapper;
import at.technikum.wien.mse.swe.filemapper.FileMapperFactoryImpl;
import at.technikum.wien.mse.swe.model.SecurityConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author MatthiasKreuzriegler
 */
public class SecurityConfigurationConnectorImpl implements SecurityConfigurationConnector {

    private final FileMapper<SecurityConfiguration> mapper;

    public SecurityConfigurationConnectorImpl() {
        mapper = (new FileMapperFactoryImpl()).createMapper(SecurityConfiguration.class);
    }

    @Override
    public SecurityConfiguration read(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return mapper.map(new SecurityConfiguration(), reader.readLine());
        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            throw new SecurityAccountOverviewReadException(e);
        }
    }
}
