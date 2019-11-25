package at.technikum.wien.mse.swe.filemapper;

public class FieldDelegate<T> {
    protected final String fieldName;
    protected final Class<T> fieldClass;

    FieldDelegate(Class<T> fieldClass, String fieldName) {
        this.fieldClass = fieldClass;
        this.fieldName = fieldName;
    }

    public T getValue() {
        return ObjectCreator.buildObject(this.fieldClass, new Object[0]);
    }

    String getName() {
        return this.fieldName;
    }

    @Override
    public String toString() {
        return String.format("FD: %s %s", fieldClass.getCanonicalName(), fieldName);
    }
}
