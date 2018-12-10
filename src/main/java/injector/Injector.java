package injector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    /**
     * The path to properties file with configuration information for injector
     */
    public static final String PATH_TO_PROPERTIES = "src//main//resources//injector.properties";

    /**
     * Injects sorter which specified in injector.properties in the given objects
     *
     * @param object object in which sorter will be injected
     * @throws IllegalAccessException
     */
    public static void injectSorter(Object object) throws IllegalAccessException {
        Properties properties = new Properties();
        InputStream input = null;
        String sorterClassName = null;
        try {
            input = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(input);

            Field[] fields = object.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.getAnnotation(Sorter.class) != null) {
                    sorterClassName = properties.getProperty(f.getType().getName());
                    f.setAccessible(true);
                    Class sorterClass = Class.forName(sorterClassName);
                    f.set(object, sorterClass.newInstance());
                    f.setAccessible(false);
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

