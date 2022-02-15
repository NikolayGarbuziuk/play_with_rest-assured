import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidTest {

    //make request to https://selenoid.autotests.cloud/status
    //assert that   "total": 20


    @Test
    void checkTotal20() {
        given().
                when().
                get("https://selenoid.autotests.cloud/status").
                then().
                statusCode(200).
                body("total", is(20));
    }

    @Test
    void checkTotal20v2() {
        Integer response = given().
                when().
                get("https://selenoid.autotests.cloud/status").
                then().
                extract().path("total");
        assertEquals(20, response);
    }

    @Test
    void checkChrome() {
        given().
                when().
                get("https://selenoid.autotests.cloud/status").
                then().
                statusCode(200).
                body("browsers.chrome", hasKey("91.0"));
    }

    @Test
    void checkWdHubStatus401() {
        given().
                when().
                get("https://selenoid.autotests.cloud/wd/hub/status").
                then().
                statusCode(401);
    }

    @Test
    void checkWdHubStatus200() {
        given().
                when().
                get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status").
                then().
                statusCode(200);
    }

    @Test
    void checkWdHubStatus200WithGiven() {
        given().
                auth().basic("user1", "1234").
                when().
                get("https://selenoid.autotests.cloud/wd/hub/status").
                then().
                statusCode(200);
    }
}
