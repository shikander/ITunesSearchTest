package com.apple.developer.itunes.search.api.constants;

import com.apple.developer.itunes.search.api.environment.SetEnvironment;
import com.apple.developer.itunes.search.util.CommonUtils;

public class EndPoints {

    private static final String environment = SetEnvironment.getEnvironment();
    private static final String envFile = "src/main/resources/configfiles/"+ environment +"/config.properties";
    public static final String searchBaseUri = CommonUtils.getConfigProperty(envFile, "searchBaseUri");
    public static final String lookupBaseUri = CommonUtils.getConfigProperty(envFile, "lookupBaseUri");

}
