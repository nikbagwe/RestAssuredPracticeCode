package APITestWithoutBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetAPIRequest {

    @Test
    public  void GetTestAllUsersAPI()
    {
        //Request
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given().log().all();
        request.header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963");
                request.queryParam("status","active");
                request.queryParam("name","naveen");


        //Response
        Response response = request.get("/public/v2/users");
        response.prettyPrint();
        String responseString = response.body().asString();
        System.out.println("Response is " +responseString);

        int statuscode = response.getStatusCode();
        System.out.println(statuscode);
        Assert.assertEquals(statuscode, 200);

       String statusLine = response.statusLine();
       System.out.println(statusLine);
       Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

       String respHeader = response.header("content-type");
       System.out.println(respHeader);


    }
    @Test
    public  void GetTestAPIAllUsersHashmap()
    {
        //Request
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given().log().all();
        request.header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963");

        //Hashmap

        Map<String, String> queryParamsMap = new HashMap<String, String>();
        queryParamsMap.put("name", "naveen");
        queryParamsMap.put("status","inactive");
        request.queryParams(queryParamsMap);

        //Response
        Response response = request.get("/public/v2/users");
        response.prettyPrint();


    }


}
