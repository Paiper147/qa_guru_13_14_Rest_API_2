package home.pages;

import static helpers.CustomApiListner.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class ProfilePage {
    public static void profileEdit(String authCookieName, String authCookieValue, String nonAuthCookieName, String nonAuthCookieValue, String bodyRequest) {
        given()
                .filter(withCustomTemplates())
                .cookie(authCookieName, authCookieValue)
                .cookie(nonAuthCookieName, nonAuthCookieValue)
                .contentType("application/x-www-form-urlencoded")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/customer/info")
                .then()
                .log().status()
                .log().headers()
                .statusCode(302)
                .header("Location","/customer/info");
    }


}
