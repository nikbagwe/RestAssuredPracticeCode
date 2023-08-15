package PostAPIPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostAPIOauth2 {

    @Test
    public void FlightBookingwithOath()
    {
        RestAssured.baseURI = "https://test.api.amadeus.com";
        //to get the access token via oath2 authentication
        String accessToken =
                given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("grant_type","client_credentials")
                .formParam("client_id","gvZJ6xqRijGhfdegTj8AMCM3CZDQnQvR")
                .formParam("client_secret","bflmyuOtGEHAJ5lX")
        .when()
                .post("/v1/security/oauth2/token")
                .then()
                .extract().path("access_token");
        System.out.println(accessToken);

        //flight booking api where this accesstoken will be used

        

    }
}
