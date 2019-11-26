package at.technikum.wien.mse.swe.filemapper;

import java.util.List;

public class FileMapperFactoryImpl implements FileMapperFactory {
    private final FieldDelegateFactory fieldFactory;

    public FileMapperFactoryImpl() {
        this.fieldFactory = new FieldDelegateFactory();
    }

    @Override
    public <T> FileMapper<T> createMapper(Class<T> model) {
        List<FieldDelegate> fields = fieldFactory.getFields(model);
        FileMapperImpl<T> mapper = new FileMapperImpl<>();
        mapper.setFields(fields);
        return mapper;
    }
}
