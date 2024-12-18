import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class UserApiTest extends BaseTest {

    @Test
    public void testUserRegistration() {
        given()
                .contentType("application/json")
                .body("{\"username\":\"testuser\",\"password\":\"password123\",\"email\":\"test@example.com\"}")
                .when()
                .post("/users/register")
                .then()
                .statusCode(200)
                .body(equalTo("User registered successfully."));
    }

    @Test
    public void testUserLogin() {
        String token = given()
                .contentType("application/json")
                .body("{\"email\":\"test@example.com\",\"password\":\"password123\"}")
                .when()
                .post("/users/login")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        System.out.println("Generated Token: " + token);
        assertNotNull(token);
    }

}
