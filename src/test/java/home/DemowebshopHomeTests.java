package home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import home.testDatas.TestDataDemowebshopHome;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;
import static home.pages.AuthorisationPage.getAuthCookie;
import static home.pages.ProfilePage.profileEdit;
import static home.pages.RegistrationPage.getHeaderNewLocation;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DemowebshopHomeTests extends TestBase {

    @Test
    @Tag("registrationTest")
    void registrationTest() {
        TestDataDemowebshopHome data = new TestDataDemowebshopHome();
        String headerNewLocation = getHeaderNewLocation(data.nonAuthCookieName, data.nonAuthCookieValue, data.bodyRequest);

        step("Check relocation URL after registration", () -> {
            assertThat(headerNewLocation).isEqualTo("/registerresult/1");
        });

        String authCookieValue = getAuthCookie(data.authCookieName, data.email, data.password);

        step("Open minimal content, because cookie can be set when site is opened", () -> {
            open("/Themes/DefaultClean/Content/images/logo.png");
        });

        step("Set cookie to the browser", () -> {
            Cookie authCookie = new Cookie(data.authCookieName, authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });

        step("Open page after registration", () -> {
            open(headerNewLocation);
        });

        step("Check registration", () -> {
            $(".result").shouldHave(Condition.text("Your registration completed"));
            $(".account").shouldHave(Condition.text(data.email));
        });
    }

    @Test
    @Tag("profileEditTest")
    void profileEditTest() {
        TestDataDemowebshopHome data = new TestDataDemowebshopHome();

        String authCookieValue = getAuthCookie(
                data.authCookieName,
                data.loginOld,
                data.passwordOld
        );

        step("Open minimal content, because cookie can be set when site is opened", () -> {
            open("/Themes/DefaultClean/Content/images/logo.png");
        });

//        step("Set cookie to the browser", () -> {
            Cookie authCookie = new Cookie(data.authCookieName, authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
//        });

//        step("Check profile edit", () -> {
            open("/customer/info");
            profileEdit(data.authCookieName,
                    authCookieValue,
                    data.nonAuthCookieName,
                    data.nonAuthCookieValue,
                    data.bodyRequestNew);
            $(".account").shouldHave(Condition.text(data.loginNew));
//        });

//        step("Recovery of original data", () -> {
            profileEdit(data.authCookieName, authCookieValue, data.nonAuthCookieName, data.nonAuthCookieValue, data.bodyRequestOld);
//        });

        System.out.println("wasda");
    }
//
//    @Test
//    @Tag("profileEditTest1")
//    void profileEditTest1() {
//        TestDataDemowebshopHome data = new TestDataDemowebshopHome();
////        RegisteredUserLoginConfig registeredUserLoginConfig = ConfigFactory.create(RegisteredUserLoginConfig.class, System.getProperties());
//
//        String authCookieValue = getAuthCookie(
//                data.authCookieName,
//                data.loginOld,
//                data.passwordOld
//        );
//
////        profileEdit(data.authCookieName, authCookieValue, data.bodyRequestNew);
//
//        step("Редактируем пользователя через API", () -> {
//                given()
////                        .filter(withCustomTemplates())
//                        .cookie(data.authCookieName, authCookieValue)
//                        .cookie("__RequestVerificationToken", data.nonAuthCookieValue)
//                        .formParam("__RequestVerificationToken", data.authBodyCookieValue)
//                        .formParam("FirstName", data.firstNameOld + "New")
//                        .formParam("LastName", data.lastNameOld + "New")
//                        .formParam("Email", data.loginNew)
//                        .log().all()
//                        .when()
//                        .post("/customer/info")
//                        .then()
//                        .log().all();
//            });
//
//
//
//        step("Open minimal content, because cookie can be set when site is opened", () -> {
//            open("/Themes/DefaultClean/Content/images/logo.png");
//        });
//
//        step("Set cookie to the browser", () -> {
//            Cookie authCookie = new Cookie(data.authCookieName, authCookieValue);
//            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
//        });
//
//
//        step("Check profile edit", () -> {
//            open("/customer/info");
////            profileEdit(data.authCookieName, authCookieValue, data.nonAuthCookieName, data.nonAuthCookieValue, data.bodyRequestNew);
//            $(".account").shouldHave(Condition.text(data.loginNew));
//        });
//        System.out.println("wasda");
//
//        step("Recovery of original data", () -> {
//            profileEdit(data.authCookieName, authCookieValue, data.nonAuthCookieName, data.nonAuthCookieValue, data.bodyRequestOld);
//        });
//
//
//
//        System.out.println("wasda");
//    }
}
