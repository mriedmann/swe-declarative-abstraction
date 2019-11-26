package at.technikum.wien.mse.swe.filemapper;

import java.util.ArrayList;
import java.util.List;

class CompositeFieldDelegate<T> extends FieldDelegate<T> {
    private final FileMapperImpl<T> mapper;
    private final List<FieldDelegate> components;

    CompositeFieldDelegate(Class<T> fieldClass, String fieldName, List<FieldDelegate> components) {
        super(fieldClass, fieldName);
        this.components = components;
        this.mapper = new FileMapperImpl<>();
        this.mapper.setFields(components);
    }

    @Override
    protected T loadValue(String lineContent) {
        ArrayList<Object> args = new ArrayList<>();
        for (FieldDelegate delegate : components) {
            args.add(delegate.getValue(lineContent));
        }
        T instance = ObjectCreator.buildObject(this.fieldClass, args.toArray());

        try {
            return mapper.map(instance, lineContent);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException();
        }
    }
}
