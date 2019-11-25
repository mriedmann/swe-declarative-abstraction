package at.technikum.wien.mse.swe.connector;

import at.technikum.wien.mse.swe.SecurityConfigurationConnector;
import at.technikum.wien.mse.swe.filemapper.FieldFactory;
import at.technikum.wien.mse.swe.filemapper.FileMapper;
import at.technikum.wien.mse.swe.filemapper.FileMapperFactoryImpl;
import at.technikum.wien.mse.swe.model.SecurityConfiguration;

import java.nio.file.Path;

/**
 * @author MatthiasKreuzriegler
 */
public class SecurityConfigurationConnectorImpl implements SecurityConfigurationConnector {

    @Override
    public SecurityConfiguration read(Path file) {
        FileMapper<SecurityConfiguration> mapper = (new FileMapperFactoryImpl(file, new FieldFactory()))
                .createMapper(SecurityConfiguration.class);
        SecurityConfiguration instance = new SecurityConfiguration();
        return mapper.map(instance);
    }
}
