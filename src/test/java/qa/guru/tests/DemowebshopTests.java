package qa.guru.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static helpers.CustomApiListner.withCustomTemplates;

public class DemowebshopTests {

    final String authCookieName = "NOPCOMMERCE.AUTH";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "http://demowebshop.tricentis.com";
        RestAssured.baseURI = "http://demowebshop.tricentis.com";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    void addToNewCartAsAnonymTest() {
        String bodyRequest = "product_attribute_72_5_18=53&" +
                "product_attribute_72_6_19=54&" +
                "product_attribute_72_3_20=57&" +
                "product_attribute_72_8_30=93&" +
                "product_attribute_72_8_30=95&" +
                "addtocart_72.EnteredQuantity=1";

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"));
    }

    @Test
    void addToOldCartAsAnonymTest() {
        /*
        curl 'http://demowebshop.tricentis.com/addproducttocart/details/72/1' \
        -H 'Accept: *\/*' \
        -H 'Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7' \
        -H 'Connection: keep-alive' \
        -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8' \
        -H 'Cookie: Nop.customer=fde830eb-795c-4371-b9b4-62bca189ded5; ARRAffinity=92eb765899e80d8de4d490df907547e5cb10de899e8b754a4d5fa1a7122fad69; __utma=78382081.1763431859.1659160161.1659160161.1659160161.1; __utmc=78382081; __utmz=78382081.1659160161.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; _ym_uid=1659160163861611148; _ym_d=1659160163; _ym_isad=2; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=72; __atuvc=1%7C30; __atuvs=62e4c6fad79a9524000; __utmb=78382081.2.10.1659160161' \
        -H 'Origin: http://demowebshop.tricentis.com' \
        -H 'Referer: http://demowebshop.tricentis.com/build-your-cheap-own-computer' \
        -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36' \
        -H 'X-Requested-With: XMLHttpRequest' \
        --data-raw 'product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1' \
        --compressed \
        --insecure
         */

        String bodyRequest = "product_attribute_72_5_18=53&" +
                "product_attribute_72_6_19=54&" +
                "product_attribute_72_3_20=57&" +
                "product_attribute_72_8_30=93&" +
                "product_attribute_72_8_30=95&" +
                "addtocart_72.EnteredQuantity=1";

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyRequest)
                .cookie("Nop.customer", "fde830eb-795c-4371-b9b4-62bca189ded5")
                .log().all()
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    void addToOldCartAsAuthorizedTest() {
//      -H 'Cookie:  Nop.customer=fde830eb-795c-4371-b9b4-62bca189ded5; \
//      -H 'Cookie:  NOPCOMMERCE.AUTH=83598AC991E1B7D12C3C6CA60ADF6A7CBADAAD7004F741E08F967508A43556676F79CD41EBF4B38BFA925C075FEA74007041E89E0DEF0E1779C0FCF35DE6DE063AB0CF8BCE2835965BEC3662823DD6186163A569094FA85D8E16FB028767E992F3CF8D9DEA0C7566B5124C957FBD82CC7E2D36706F1F5CC4ED60AEA04E29B70365607D4C8DCD665723159A33F3AFB400

        String authCookie = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("Email", "vbdv@feferf.ru")
                .formParam("Password", "itLf7@U@Bf6khGH")
//                .body("Email=vbdv@feferf.ru&Password=itLf7@U@Bf6khGH&RememberMe=false")
                .log().all()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie("NOPCOMMERCE.AUTH");

        String bodyRequest = "product_attribute_72_5_18=53&" +
                "product_attribute_72_6_19=54&" +
                "product_attribute_72_3_20=57&" +
                "product_attribute_72_8_30=93&" +
                "product_attribute_72_8_30=95&" +
                "addtocart_72.EnteredQuantity=1";

        given()
                .cookie("NOPCOMMERCE.AUTH", authCookie)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    void addToOldCartAsAuthorizedSizeInWebTest() {
        String email = "vbdv@feferf.ru";
        String password = "itLf7@U@Bf6khGH";
        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("Email", email)
                .formParam("Password", password)
                .log().all()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieName);

        String bodyRequest = "product_attribute_72_5_18=53&" +
                "product_attribute_72_6_19=54&" +
                "product_attribute_72_3_20=57&" +
                "product_attribute_72_8_30=93&" +
                "product_attribute_72_8_30=95&" +
                "addtocart_72.EnteredQuantity=1";

        String cartSize = given()
                .cookie(authCookieName, authCookieValue)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .extract()
                .path("updatetopcartsectionhtml");

        open("/Themes/DefaultClean/Content/images/logo.png");

        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);

        open("");
        $(".cart-qty").shouldHave(Condition.text(cartSize));
    }

    @Test
    void addToCartWithAllureTest() {
        String email = "vbdv@feferf.ru";
        String password = "itLf7@U@Bf6khGH";
        String authCookieValue = getAuthCookie(email,password);
        String bodyRequest = "product_attribute_72_5_18=53&" +
                "product_attribute_72_6_19=54&" +
                "product_attribute_72_3_20=57&" +
                "product_attribute_72_8_30=93&" +
                "product_attribute_72_8_30=95&" +
                "addtocart_72.EnteredQuantity=1";

        String cartSize = getCartSize(bodyRequest, authCookieValue);

        step("Open minimal content, because cookie can be set when site is opened", () ->
                open("/Themes/DefaultClean/Content/images/logo.png"));

        step("Set cookie to the browser", () -> {
            Cookie authCookie = new Cookie(authCookieName, authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });

        step("Open main page", ()->
                open(""));

        step("Check cart size", () ->
                $(".cart-qty").shouldHave(Condition.text(cartSize)));
    }

    @Step("Get authorization cookie")
    String getAuthCookie(String email, String password){
        return given()
//                .filter(new AllureRestAssured())
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("Email", email)
                .formParam("Password", password)
                .log().all()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieName);
    }

    @Step("Get cart size")
    String getCartSize(String bodyRequest, String authCookieValue){
        return given()
//                .filter(new AllureRestAssured())
                .filter(withCustomTemplates())
                .cookie(authCookieName, authCookieValue)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .extract()
                .path("updatetopcartsectionhtml");
    }

}
