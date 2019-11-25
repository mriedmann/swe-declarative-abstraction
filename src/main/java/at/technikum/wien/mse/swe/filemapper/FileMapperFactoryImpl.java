package at.technikum.wien.mse.swe.filemapper;

import at.technikum.wien.mse.swe.filemapper.annotations.CompositeField;
import at.technikum.wien.mse.swe.filemapper.annotations.FixedWidthField;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileMapperFactoryImpl implements FileMapperFactory {
    private final FixedWidthFile file;
    private final List<FieldDelegate> fields = new ArrayList<>();
    private final FieldFactory fieldFactory;

    public FileMapperFactoryImpl(Path path, FieldFactory fieldFactory) {
        this.file = new FixedWidthFile(path);
        this.fieldFactory = fieldFactory;
    }

    @Override
    public <T> FileMapper<T> createMapper(Class<T> model) {
        Arrays.stream(model.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(FixedWidthField.class))
                .forEach(field -> addField(fieldFactory.createField(
                        field.getType(),
                        field.getName(),
                        file,
                        field.getAnnotation(FixedWidthField.class))));

        Arrays.stream(model.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(CompositeField.class))
                .forEach(field -> addField(fieldFactory.createField(
                        field.getType(),
                        field.getName(),
                        file,
                        field.getAnnotation(CompositeField.class))));

        FileMapperImpl<T> mapper = new FileMapperImpl<>();
        mapper.setFields(fields);
        return mapper;
    }

    private FileMapperFactoryImpl addField(FieldDelegate field) {
        fields.add(field);
        return this;
    }
}
