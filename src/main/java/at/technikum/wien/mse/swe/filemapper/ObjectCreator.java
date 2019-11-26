package at.technikum.wien.mse.swe.filemapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

class ObjectCreator {

    private ObjectCreator() {
    }

    static <T> T buildObject(Class<T> targetClass, Object[] args) {
        T result = null;

        try {
            result = buildFromCustomConstructor(targetClass, args);

            if (result == null) {
                result = buildFromSpecificConstructor(targetClass, args);
            }

            if (result == null) {
                result = buildFromDefaultConstructor(targetClass);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException();
        }

        return result;

    }

    private static <T> T buildFromCustomConstructor(Class<T> targetClass, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Method customConstructor = Arrays.stream(targetClass.getMethods())
                .filter(method -> method.isAnnotationPresent(FieldConstructor.class))
                .findFirst().orElse(null);

        if (customConstructor != null) {
            if (customConstructor.getReturnType().isAssignableFrom(Optional.class)) {
                return ((Optional<T>) customConstructor.invoke(null, args)).orElse(null);
            }
            return (T) customConstructor.invoke(null, args);
        }
        return null;
    }

    private static <T> T buildFromSpecificConstructor(Class<T> targetClass, Object[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] classes = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
        Constructor<T> constructor;
        try {
            constructor = targetClass.getConstructor(classes);
        } catch (NoSuchMethodException e) {
            return null;
        }
        return constructor.newInstance(args);
    }

    private static <T> T buildFromDefaultConstructor(Class<T> targetClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> defaultConstructor = (Constructor<T>) targetClass.getConstructors()[0];
        return defaultConstructor.newInstance();
    }
}
