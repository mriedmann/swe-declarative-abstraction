package at.technikum.wien.mse.swe.filemapper.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(CompositeField.class)
public @interface ComponentField {
    String name();

    int start();

    int length();

    String padding() default " ";

    FieldAlignment alignment() default FieldAlignment.LEFT;
}
