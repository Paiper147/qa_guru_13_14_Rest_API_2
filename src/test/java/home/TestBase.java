package home;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import home.config.DemowebshopHomeTestsConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        RestAssured.baseURI = "http://demowebshop.tricentis.com";

        DemowebshopHomeTestsConfig webDriverConfig = ConfigFactory.create(DemowebshopHomeTestsConfig.class, System.getProperties());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        Configuration.baseUrl = webDriverConfig.getBaseUrl();
        Configuration.browser = webDriverConfig.getBrowser();
        Configuration.browserSize = webDriverConfig.getBrowserSize();
        Configuration.browserVersion = webDriverConfig.getBrowserVersion();

        String remoteUrl = System.getProperty("env");
        if (remoteUrl != null){
            if (remoteUrl.equals("localSelenoid") || remoteUrl.equals("remoteSelenoid")){
                Configuration.remote = webDriverConfig.getRemoteUrl();
            }
        }
    }

    @AfterEach
    void afterEach() {
        DemowebshopHomeTestsConfig webDriverConfig = ConfigFactory.create(DemowebshopHomeTestsConfig.class, System.getProperties());
        String videoUrlPath = webDriverConfig.getVideoUrlPath();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo(videoUrlPath);

        Selenide.closeWebDriver();
    }
}
