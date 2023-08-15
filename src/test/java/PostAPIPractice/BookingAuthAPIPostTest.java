package PostAPIPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookingAuthAPIPostTest {

    @Test
    public void BookingTestAPIWithStringBody() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String tokenID = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().path("token");

        System.out.println(tokenID);
        Assert.assertNotNull(tokenID);

    }

    @Test
    public void BookingTestAPIWithJSONConfigFile() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String tokenID = given().log().all()
                .contentType(ContentType.JSON)
                .body(new File(".\\src\\test\\resources\\JSON\\toekn.json")) //Please revise this syntax newFile(".path from Src")
                .when().log().all()
                .post("/auth")
                .then().log().all()
                .statusCode(200)
                .extract().path("token");

        System.out.println(tokenID);
        Assert.assertNotNull(tokenID);

    }

    @Test

    public void GetandPostCall() {
        RestAssured.baseURI = "https://gorest.co.in";

        //Post call to create user and fetch the ID to pass in get call

        int ID = given().log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\" : \"Nikhil\",\n" +
                        "    \"email\" : \"nikhilbagwewewe@yahoo.com\",\n" +
                        "    \"gender\" : \"male\",\n" +
                        "    \"status\" : \"active\"\n" +
                        "    }")
                .header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                .when().log().all()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("id");
        System.out.println(ID);

        //get call to validate with created id.
        int createdID =
                given().log().all()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                        .when().log().all()
                        .get("/public/v2/users/" + ID)
                        .then()
                        .assertThat().statusCode(200)
                        .and()
                        .extract()
                        .path("id");

        Assert.assertEquals(ID, createdID);
        //u can user body("id", equalTo (ID)) also instrad of creating Created ID.

    }
    @Test
    public void GetAndPostCallUsingJSONFile() {
        RestAssured.baseURI = "https://gorest.co.in";

        //Post call to create user and fetch the ID to pass in get call

        int ID = given().log().all()
                .contentType(ContentType.JSON)
                .body(new File(".\\src\\test\\resources\\JSON\\createuser.json"))
                .header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                .when().log().all()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("id");
        System.out.println(ID);

        //get call to validate with created id.

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                .when().log().all()
                .get("/public/v2/users/" + ID)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("id", equalTo(ID));
    }
}