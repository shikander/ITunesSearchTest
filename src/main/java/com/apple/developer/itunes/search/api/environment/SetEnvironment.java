package com.apple.developer.itunes.search.api.environment;

import com.apple.developer.itunes.search.util.CommonUtils;

import java.util.Properties;

public class SetEnvironment {

    private static String configFile = "src/main/resources/configfiles/config.properties";
    private static String environment = CommonUtils.getConfigProperty(configFile, "env");
    public static String getEnvironment(){
        String env = "staging";
        if(environment.equalsIgnoreCase("dev"))
            env = Environment.DEV.name();
        else if (environment.equalsIgnoreCase("prod"))
            env = Environment.PROD.name();
        else if (environment.equalsIgnoreCase("staging"))
            env = Environment.STAGING.name();
        return env;
    }
}
