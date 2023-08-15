package JSONPathExtract;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class ExtractBody {

    @Test
    public void GetAPIExtractData() {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given().log().all()
                .queryParams("limit", 5)
                .when().log().all()
                .get("/products");
        //System.out.println("Repsonse is" +response);
        //if u directly print response directly it will print garbage value use pretty print
        response.prettyPrint();
        JsonPath js = response.jsonPath();
        //if u directly print js it will print garbage value

        //extracting json values using jsonpath

        //this will extract 0th  index ID
        int firstID = js.getInt("[0].id");
        System.out.println(firstID);

        String title = js.getString("[0].title");
        System.out.println(title);

        float price = js.getFloat("[0].price");
        System.out.println(price);

        float rate = js.getFloat("[0].rating.rate");
        System.out.println(rate);
    }

    //Example to see how to get list of data in the JSON Array resonse
    @Test
    public void GetAPIExtractDataListDataWithJSONArray() {
        RestAssured.baseURI = "https://fakestoreapi.com";
        Response response = given().log().all()
                .queryParams("limit", 5)
                .when().log().all()
                .get("/products");

        JsonPath js = response.jsonPath();
        List<Integer> idList = js.getList("id");
//        System.out.println(idList.size());
//        System.out.println(idList);

        List<String> titleList = js.getList("title");
//        System.out.println(titleList.size());
//        System.out.println(titleList);

        List<Float> rateList = js.getList("rating.rate");
        List<Integer> countList = js.getList("rating.count");

        for (int i = 0; i < idList.size(); i++) {
            Integer id = idList.get(i);
            String title = titleList.get(i);
            Float rate = rateList.get(i);
            Integer count = countList.get(i);

            System.out.println("ID is " + id + "title" + title + "rate is " + rate + "count is " + count);
        }
    }

    @Test     //Example to see how to get list of data in the JSON Object (single set of response)
    public void GetAPIExtractDataListDataWithJSONObject()
    {
        RestAssured.baseURI = "https://gorest.co.in";

        Response response =
                given().log().all()
                .header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                .get("public/v2/users/3962312");

        response.prettyPrint();
        JsonPath js = response.jsonPath();
        int id = js.getInt("id");
        String name = js.getString("name");
        String email =js.getString("email");
        String gender=js.getString("gender");
        String status =js.getString("status");

        System.out.println("Id : " +id+ "\n" +"Name : " +name+ "\n"+"email : " +email+ "\n"+"gender : " +gender+ "\n"+"Status : " +status+ "\n" );



    }

    @Test     //Example to see how to get list of data in the JSON Object (single set of response)
    public void GetAPIExtractDataWithExtractMethod() {
        RestAssured.baseURI = "https://gorest.co.in";

        //This will extract single value from JSON and should be used while using JSON Object not with JSON array
        //extract().path("pass ur JSON key like id,name) after then and store in the respective datatype.
        int UserID =
                given().log().all()
                        .header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                        .get("public/v2/users/4020633")
                        .then()
                        .extract().path("id");

        System.out.println(UserID);
    }
    @Test     //Example to see how to get list of data in the JSON Object (single set of response)
    public void GetAPIExtractDataWithExtractMethodAnotherWay() {
        RestAssured.baseURI = "https://gorest.co.in";

        //This will extract single value from JSON and should be used while using JSON Object not with JSON array
        //extract().response() after then and store in the Response return type and fetch as per the need.

               Response response = given().log().all()
                        .header("Authorization", "Bearer 7cecfd542e55fe5074f6fb9f4115dda6ba11064279930d64d264831d823d1963")
                        .get("public/v2/users/4020633")
                        .then()
                        .extract()
                        .response();

               //fetch id from response
               int id = response.path("id");
               System.out.println(id);

               //fetch title from response
               String name = response.path("name");
               System.out.println(name);
    }

}