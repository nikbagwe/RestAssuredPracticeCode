package POJOPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserWithPOJO {

    //POJO - Plain Old Java Object
    //POJO - Serialization and De-serialization
    //Serialization - > Request Payload -> Convert Java Object to JSON and Pass through Body()
    //Convert Java object to JSON object means -> Create POJO class with all the Keys in JSON
    //Step 1 : Create Private variables of All the Payload JSON keys (Name, gender, Status etc as a Private variables)
    //Step 2 : Create Parameterized constructor
    //Step 3 : Create getters and Setters (used for validation by calling get methods)
    //Step 4 : Creating the object of same pojo class which will be passed in Body (request payload)
    //Step 5 : this is also example of Encapsulation , we are calling private variables by using getter and setters public methods from other class

    //De-Serialization - > Convert JSON Object in Java Object > Used when JSON Response payload needs to be verified by converting it to Java Object

    //to generate RandomEmail
    public String RandomEmail()
    {
        return "nikhilAPI"+System.currentTimeMillis()+"@mail.com";
    }
    @Test
    //fetch the user id by passing pojo object of UserPojo class and supply as body
    public void CreateUserWithPOJO()
    {
        RestAssured.baseURI = "https://gorest.co.in";
        UserPojo user = new UserPojo("nikhil bagwe",RandomEmail(), "male","active");

    int userID = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                .body(user)
                    .when().log().all()
                .post("/public/v2/users/")
                .then().log().all()
                .assertThat().statusCode(201)
                .extract().path("id");
    System.out.println(userID);

    //using the same id in get call to validate the user is created

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                .when()
                .get("/public/v2/users/"+userID)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(userID))
                .body("name",equalTo(user.getName()))
                .body("gender",equalTo(user.getGender()))
                .body("email",equalTo(user.getEmail()))
                .body("status",equalTo(user.getStatus()));
    }


}
