package at.technikum.wien.mse.swe.filemapper;

import org.apache.commons.lang.NotImplementedException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FieldDelegateFactory {

    <T> List<FieldDelegate> getFields(Class<T> model) {
        ArrayList<FieldDelegate> fields = new ArrayList<>();
        fields.addAll(processFieldAnnotation(model, FixedWidthField.class));
        fields.addAll(processFieldAnnotation(model, CompositeField.class));
        return fields;
    }

    private <T> List<FieldDelegate> processFieldAnnotation(Class<T> model, Class<? extends Annotation> fieldAnnotationClass) {
        return Arrays.stream(model.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(fieldAnnotationClass))
                .map(field -> createField(
                        field.getType(),
                        field.getName(),
                        fieldAnnotationClass,
                        field.getAnnotation(fieldAnnotationClass)))
                .collect(Collectors.toList());
    }

    private <T> FieldDelegate createField(Class<T> fieldClass, String fieldName, Class<? extends Annotation> fieldAnnotationClass, Annotation fieldAnnotation) {
        switch (fieldAnnotationClass.getSimpleName()) {
            case "FixedWidthField":
                return createField(fieldClass, fieldName, (FixedWidthField) fieldAnnotation);
            case "CompositeField":
                return createField(fieldClass, fieldName, (CompositeField) fieldAnnotation);
            default:
                throw new NotImplementedException();
        }
    }

    private <T> FieldDelegate createField(Class<T> fieldClass, String fieldName, FixedWidthField fieldAnnotation) {
        return new FixedWidthFieldDelegate<>(fieldClass, fieldName,
                fieldAnnotation.start(),
                fieldAnnotation.length(),
                fieldAnnotation.padding(),
                fieldAnnotation.alignment());
    }

    private <T> FieldDelegate createField(Class<T> fieldClass, String fieldName, CompositeField fieldAnnotation) {
        ArrayList<FieldDelegate> components = new ArrayList<>();
        ComponentField[] fieldAnnotations = fieldAnnotation.value();
        for (ComponentField a : fieldAnnotations) {
            try {
                Class componentFieldType = fieldClass.getDeclaredField(a.name()).getType();
                components.add(createField(componentFieldType, a));
            } catch (NoSuchFieldException e) {
                throw new RuntimeException();
            }
        }
        return new CompositeFieldDelegate(fieldClass, fieldName, components);
    }

    private <T> FieldDelegate createField(Class<T> fieldClass, ComponentField fieldAnnotation) {
        return new FixedWidthFieldDelegate<>(fieldClass, fieldAnnotation.name(),
                fieldAnnotation.start(),
                fieldAnnotation.length(),
                fieldAnnotation.padding(),
                fieldAnnotation.alignment()
        );
    }
}
