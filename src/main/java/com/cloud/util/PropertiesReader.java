package com.cloud.util;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static Properties prop = new Properties();

    static {
        try {
            InputStream SystemIn = new ClassPathResource("com/Config/SysConf.properties").getInputStream();
            prop.load(SystemIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String name) {
        if (prop != null ) {
            return prop.get(name).toString();
        } else {
            return null;
        }
    }
}
