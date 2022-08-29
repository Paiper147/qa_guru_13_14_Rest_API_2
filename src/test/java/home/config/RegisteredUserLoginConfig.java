package home.config;

import org.aeonbits.owner.Config;
@Config.Sources("classpath:config/registeredUserLogin.properties")
public interface RegisteredUserLoginConfig extends Config {

    @Key("firstName")
    String firstName();

    @Key("lastName")
    String lastName();
    @Key("login")
    String login();

    @Key("password")
    String password();
}
