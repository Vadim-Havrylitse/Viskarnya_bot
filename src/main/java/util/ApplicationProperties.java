package util;

import controller.GoogleApi;
import lombok.SneakyThrows;
import java.util.Properties;
import java.io.InputStream;

public class ApplicationProperties {

    private static final String PROPERTIES_FILEPATH = "/application.properties";
    private static Properties appProperties;


    @SneakyThrows
    public static Properties getProperties() {
        if (appProperties != null) {
            return appProperties;
        }
        InputStream in = GoogleApi.class.getResourceAsStream(PROPERTIES_FILEPATH);
        appProperties = new Properties();
        appProperties.load(in);
        return appProperties;
    }
}
