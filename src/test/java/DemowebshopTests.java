import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DemowebshopTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com/";
    }

    @Test
    void addToCart() {
        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=439bec77-4c05-4a50-b29b-39511906ad78")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54&product_attribute_72_3_20=57" +
                        "&product_attribute_72_8_30=93&addtocart_72.EnteredQuantity=1")
                .when()
                .post("addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", stringContainsInOrder("product has been added"))
                .extract().response();

        System.out.println("Response" + response.asString());

    }
}
