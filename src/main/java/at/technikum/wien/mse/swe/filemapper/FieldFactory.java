package at.technikum.wien.mse.swe.filemapper;

import at.technikum.wien.mse.swe.filemapper.annotations.ComponentField;
import at.technikum.wien.mse.swe.filemapper.annotations.CompositeField;
import at.technikum.wien.mse.swe.filemapper.annotations.FixedWidthField;

import java.util.ArrayList;

public class FieldFactory {

    <T> FieldDelegate createField(Class<T> fieldClass, String fieldName, FixedWidthFile file, FixedWidthField fieldAnnotation) {
        return new FixedWidthFieldDelegate<T>(fieldClass, fieldName, file,
                fieldAnnotation.start(),
                fieldAnnotation.length(),
                fieldAnnotation.padding(),
                fieldAnnotation.alignment());
    }

    <T> FieldDelegate createField(Class<T> fieldClass, String fieldName, FixedWidthFile file, CompositeField fieldAnnotation) {
        ArrayList<FieldDelegate> components = new ArrayList<>();
        ComponentField[] fieldAnnotations = fieldAnnotation.value();
        for (ComponentField a : fieldAnnotations) {
            try {
                Class componentFieldType = fieldClass.getDeclaredField(a.name()).getType();
                components.add(createField(componentFieldType, file, a));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return new CompositeFieldDelegate(fieldClass, fieldName, components);
    }

    private <T> FieldDelegate createField(Class<T> fieldClass, FixedWidthFile file, ComponentField fieldAnnotation) {
        return new FixedWidthFieldDelegate<T>(fieldClass, fieldAnnotation.name(), file,
                fieldAnnotation.start(),
                fieldAnnotation.length(),
                fieldAnnotation.padding(),
                fieldAnnotation.alignment()
        );
    }
}
