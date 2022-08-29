package home.pages;

import static helpers.CustomApiListner.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class RegistrationPage {
    public static String getHeaderNewLocation(String nonAuthCookieName, String nonAuthCookieValue, String bodyRequest){
        return given()
                .filter(withCustomTemplates())
                .cookie(nonAuthCookieName, nonAuthCookieValue)
                .contentType("application/x-www-form-urlencoded")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().headers()
                .statusCode(302)
                .extract()
                .header("Location");
    }


}
