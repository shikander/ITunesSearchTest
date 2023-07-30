package com.apple.developer.itunes.search.util;

import com.apple.developer.itunes.search.api.environment.Environment;
import net.datafaker.Faker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
}
