package config;

import org.aeonbits.owner.Config;
import org.example.Constants;

@Config.Sources({"classpath:${env}.properties", "classpath:default.properties"})
public interface TestPropertiesConfig extends Config {
  @Key("baseUrl")
  @DefaultValue(Constants.BASE_URL)
  String getBaseUrl();

  @Key("testKey")
  @DefaultValue((Constants.TEST_KEY))
  String getTestKey();

  @Key("testValue")
  @DefaultValue((Constants.TEST_VALUE))
  String getTestValue();

  @Key("username")
  String getUsername();

  @Key("password")
  String getPassword();
}
