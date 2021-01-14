package com.zhang.utils.config;
 
 
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
/**
 * @ClassName: SystemConfig
 * @create: 2020-08-12 00:04
 * @since： JDK1.8
 * @SystemConfig:
 **/
public class SystemConfig {
 
     private static final String CONFIG_PROPERTIES="config.properties";
 
     public static String getConfigResource(String key) throws IOException {
         ClassLoader loader = Thread.currentThread().getContextClassLoader();
         Properties properties = new Properties();
         InputStream in = loader.getResourceAsStream(CONFIG_PROPERTIES);
         properties.load(in);
         String value = properties.getProperty(key);
         in.close();
         return value;
     }
}
