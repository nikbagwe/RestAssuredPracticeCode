package RequestAndResponseSpec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class RequestSpecbuild {


    public static RequestSpecification requestSpecBuild(){
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                .addQueryParam("status", "active")
                .build();
        return reqSpec;
    }

    public static ResponseSpecification ResponseSpecStatusCodeAndBody()
    {
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectHeader("Content-Encoding","gzip")
                .expectBody("$.size()", equalTo(10))
                .expectBody("gender", hasSize(10))
                .build();

        return responseSpec;
    }

    //test with both the specification builders used
    @Test
    public void ValidateRequestSpecCreate()
    {
        given(requestSpecBuild()).log().all()
                .when()
                .get("/public/v2/users")
                .then()
                .log().all()
                .assertThat()
                .spec(ResponseSpecStatusCodeAndBody());


    }
}
