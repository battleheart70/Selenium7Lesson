package config;

import org.aeonbits.owner.Config;
import org.example.Constants;

@Config.Sources({

        "classpath:${env}.properties"
        ,
        "classpath:default.properties"

})
public interface TestPropertiesConfig extends Config {
    @Key("baseUrl")
    @DefaultValue(Constants.BASE_URL)
    String getBaseUrl();
}