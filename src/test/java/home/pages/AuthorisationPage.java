package home.pages;

import static helpers.CustomApiListner.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class AuthorisationPage {

    public static String getAuthCookie(String authCookieName, String email, String password){
        return given()
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
}
