package at.technikum.wien.mse.swe.filemapper;

abstract class FieldDelegate<T> {
    final Class<T> fieldClass;
    private final String fieldName;
    private T value;

    FieldDelegate(Class<T> fieldClass, String fieldName) {
        this.fieldClass = fieldClass;
        this.fieldName = fieldName;
    }

    T getValue(String lineContent) {
        if (value == null) {
            value = loadValue(lineContent);
        }
        return value;
    }

    String getName() {
        return this.fieldName;
    }

    abstract T loadValue(String lineContent);
}
