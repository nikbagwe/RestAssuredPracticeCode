package APITestWithoutBDD;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAPIWithBDD {

    @Test
    public void getAllUsers()
    {
        // RestAssured.given(); Rest Assured is the class which has given() as static method, click on given,
        // it will take to restassured.java class
        // we are calling .given method by using class name (RestAssured) classname.staticMethod, to avoid using classname everytime
        //we can user import statement which can call all the static methods of restassured class directly
        //given() when() then() and()
        //Add import as import static io.restassured.RestAssured.*;
        //Now u can user methods directly without using class name

        given()
                .when().log().all()
                .get("https://fakestoreapi.com/products")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("$.size()", equalTo(20))
                .and()
                .body("id", is(notNullValue()))
                .body("title",hasItem("Solid Gold Petite Micropave "));
    }
}
