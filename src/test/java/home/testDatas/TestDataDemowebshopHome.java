package home.testDatas;

import com.github.javafaker.Faker;
import home.config.RegisteredUserLoginConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.Locale;

import static utils.RandomUtils.getRandomNumberDesiredSize;

public class TestDataDemowebshopHome {

    Faker faker = new Faker(new Locale("en"));

    public final static String EMAIL_DOMAIN = "@ya.ru";

    public final static String GENDER = "F";
    public String firstName = faker.address().firstName();
    public String lastName = faker.address().lastName();
    public String email = firstName + lastName + EMAIL_DOMAIN;
    public String password = getRandomNumberDesiredSize(6);
    public String registerButton = "Register";
    public String saveInfoButton = "Save";
    public String nonAuthCookieName = "__RequestVerificationToken";
    public String nonAuthCookieValue = "_LAVY-j2wXAWhT97yY8SIAqxX_Kg4Ik8dwEMtyrHCKnq75i3lPPzRw5k98eFepjYUOsaeCHcLl0TAMSvfZVz29hLfxGF6jI6YX-i0iYmZbc1";
    public String nonAuthForBodyCookieValue = "rDouguN2qdZEsi1AvWB81NgywxbttMKBKJqV6fB63HYw9uOg6gRBGGK49CmSP7-j8FMHIKGokWtZeh42c_lqMoFL5FHpelYyCGOOVLqg8cc1";

    public String authCookieName = "NOPCOMMERCE.AUTH";
    public String bodyRequest =
            nonAuthCookieName + "=" + nonAuthForBodyCookieValue + "&" +
                    "Gender=" + GENDER + "&" +
                    "FirstName=" + firstName + "&" +
                    "LastName=" + lastName + "&" +
                    "Email=" + email + "&" +
                    "Password=" + password + "&" +
                    "&ConfirmPassword=" + password + "&" +
                    "register-button=" + registerButton;

    RegisteredUserLoginConfig registeredUserLoginConfig = ConfigFactory.create(RegisteredUserLoginConfig.class, System.getProperties());
    public String firstNameOld = registeredUserLoginConfig.firstName();
    public String lastNameOld = registeredUserLoginConfig.lastName();
    public String loginOld = registeredUserLoginConfig.login();
    public String passwordOld = registeredUserLoginConfig.password();
    public String loginNew = loginOld + "New";
    public String authBodyCookieValue = "3ZHN3zoQlI9QpoNDVYo2Q9uEa_rzgtjbBRTwlASvsJiCqpiQI4WM02c4VuUNqjEmLtZ29NMOx97tsf2qHYTMYzTeniyT9uoJbNz-0y1C5v-o-Z7oiE6ZgkT-7oVmwaWq0";

    public String bodyRequestNew =
            nonAuthCookieName + "=" + authBodyCookieValue + "&" +
                    "Gender=" + GENDER + "&" +
                    "FirstName=" + firstNameOld + "&" +
                    "LastName=" + lastNameOld + "&" +
                    "Email=" + loginOld + "New&" +
                    "save-info-button=" + saveInfoButton;
    public String bodyRequestOld =
            nonAuthCookieName + "=" + authBodyCookieValue + "&" +
                    "Gender=" + GENDER + "&" +
                    "FirstName=" + firstNameOld + "&" +
                    "LastName=" + lastNameOld + "&" +
                    "Email=" + loginOld + "&" +
                    "save-info-button=" + saveInfoButton;
}
