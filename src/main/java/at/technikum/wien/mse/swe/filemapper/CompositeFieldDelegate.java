package at.technikum.wien.mse.swe.filemapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeFieldDelegate<T> extends FieldDelegate<T> {
    private final FileMapperImpl<T> mapper;
    private final List<FieldDelegate> components;

    CompositeFieldDelegate(Class<T> fieldClass, String fieldName, List<FieldDelegate> components) {
        super(fieldClass, fieldName);
        this.components = components;
        this.mapper = new FileMapperImpl<>();
        this.mapper.setFields(components);
    }

    @Override
    public T getValue() {
        ArrayList<Object> args = new ArrayList<>();
        args.addAll(components.stream().map(FieldDelegate::getValue).collect(Collectors.toList()));
        T instance = ObjectCreator.buildObject(this.fieldClass, args.toArray());
        return mapper.map(instance);
    }
}
