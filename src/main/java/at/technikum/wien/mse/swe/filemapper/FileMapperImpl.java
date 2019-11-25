package at.technikum.wien.mse.swe.filemapper;

import java.lang.reflect.Field;
import java.util.List;

public class FileMapperImpl<T> implements FileMapper<T> {
    private List<FieldDelegate> fields;

    void setFields(List<FieldDelegate> fields) {
        this.fields = fields;
    }

    @Override
    public T map(T instance) {
        for (FieldDelegate field : fields) {
            String fieldName = field.getName();
            try {
                Field f1 = instance.getClass().getDeclaredField(fieldName);
                f1.setAccessible(true);
                f1.set(instance, field.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
