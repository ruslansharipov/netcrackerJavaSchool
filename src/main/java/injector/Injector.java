package injector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    public static final String PATH_TO_PROPERTIES = "src//main//resources//injector.properties";

    public static void injectSorter(Object object) throws IllegalAccessException {
        Properties properties = new Properties();
        InputStream input = null;
        String sorterClassName = null;
        try {
            input = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(input);
            sorterClassName = properties.getProperty("Sorter");

            Field[] fields = object.getClass().getDeclaredFields();
            for (Field f : fields) {
                Annotation[] annotations = f.getAnnotations();
                for (Annotation a : annotations) {
                    if (a instanceof Sorter) {
                        f.setAccessible(true);
                        Class sorterClass = Class.forName(sorterClassName);
                        f.set(object, sorterClass.newInstance());
                        f.setAccessible(false);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

