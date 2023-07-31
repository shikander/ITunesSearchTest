package com.apple.developer.itunes.search.util;

import com.apple.developer.itunes.search.api.environment.Environment;
import net.datafaker.Faker;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class CommonUtils {

    public static Properties configProperties = new Properties();
    public static FileReader configReader;
    public static Faker faker = new Faker();

    public static String getRandomNumber(int count) {
        return faker.number().digits(count);
    }

    public static String getConfigProperty(String configFile, String configValue) {
        try{
            configReader = new FileReader(  configFile);
            configProperties.load(configReader);
        }catch (IOException e){
            e.getStackTrace();
        }
        return configProperties.getProperty(configValue);
    }

    public static String getDataFromYamlFile(String file, String key) {
        String value = "";
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = new FileInputStream(file);
            HashMap yamlMap = yaml.load(inputStream);
            value = yamlMap.get(key).toString().replaceAll("\\[", "").replaceAll("\\]","");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return value;
    }
}
