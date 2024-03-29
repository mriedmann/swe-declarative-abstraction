package at.technikum.wien.mse.swe.filemapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FixedWidthField {

    int start();

    int length();

    String padding() default " ";

    FieldAlignment alignment() default FieldAlignment.LEFT;
}
