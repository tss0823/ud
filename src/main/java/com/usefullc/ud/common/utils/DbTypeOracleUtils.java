/**
 * 
 */
package com.usefullc.ud.common.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author tangss
 * @2013年9月2日 @上午9:53:22
 */
public class DbTypeOracleUtils {

    private static Properties prop;
    static {
        Resource resource = new ClassPathResource("db-type-oracle.properties");
        try {
            prop = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getJavaValue(String key, String len) {
        if (len != null) {
            if (len.indexOf(",") != -1) {
                key = key + "_d";
            } else if (key.toLowerCase().equals("number") && len.equals("20")) {
                key = key + "_" + len;
            } else if (len.equals("1")) {
                key = key + "_" + len;
            }
        }
        return prop.getProperty(key);
    }

    public static String getDbValue(String key, String len) {
        return prop.getProperty(key);
    }

}
