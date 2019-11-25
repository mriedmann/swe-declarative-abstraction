package at.technikum.wien.mse.swe.connector;

import at.technikum.wien.mse.swe.SecurityAccountOverviewConnector;
import at.technikum.wien.mse.swe.filemapper.FieldFactory;
import at.technikum.wien.mse.swe.filemapper.FileMapper;
import at.technikum.wien.mse.swe.filemapper.FileMapperFactoryImpl;
import at.technikum.wien.mse.swe.model.SecurityAccountOverview;

import java.nio.file.Path;

/**
 * @author MatthiasKreuzriegler
 */
public class SecurityAccountOverviewConnectorImpl implements
        SecurityAccountOverviewConnector {

    @Override
    public SecurityAccountOverview read(Path file) {
        FileMapper<SecurityAccountOverview> mapper = (new FileMapperFactoryImpl(file, new FieldFactory()))
                .createMapper(SecurityAccountOverview.class);
        SecurityAccountOverview instance = new SecurityAccountOverview();
        return mapper.map(instance);
    }
}
