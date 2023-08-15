package Lecture5.JaywayPath;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSONPathTest {

    @Test
    public void JSONPathTest()
    {
        RestAssured.baseURI = "http://ergast.com";

        Response response = given()
                .when().log().all(true)
                .log().all(true)
                .get("/api/f1/2016/circuits.json");

        String JsonResponse = response.asString();
        List<String> listCircuitNames = JsonPath.read(JsonResponse,"$..circuitName");
        System.out.println(listCircuitNames);

        Integer lengthCircuits = JsonPath.read(JsonResponse,"$.MRData.CircuitTable.Circuits.length()");
        System.out.println(lengthCircuits);
    }
    //fetching the multiple data in single query (eg. title , price, id)
    @Test
    public void getAllProductsTest() {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given().log().all()
                .when()
                .get("/products");

        String JsonResponse = response.asString();
        List<Map<String, Object>> jewelerylist = JsonPath.read(JsonResponse, "$[?(@.category=='jewelery')].[\"id\",\"title\",\"price\"]");
        for (Map<String, Object> product : jewelerylist) {
            int productID = (int) product.get("id");
            String productTitle = (String)product.get("title");
            Object ProductPrice=(Object)product.get("price");
            System.out.println("product ID is " +productID+ "," +"ProductTitle is " +productTitle +","+"price is " +ProductPrice);

        }

    }}

/*
JQL :


1. $[*].id - Fetch all the IDs *****IMP QUERY
2. $[*].title - Fetch all the titles
3. $[*].rating.rate - fetch all the rates
4. $[*]..rate - another way of fetching all rates with .. *****IMP QUERY
5. $[*]..rate,count -- fetching multiple keys with ..

6. rates which less than 3: *****IMP QUERY
$[?(@.rating.rate < 3)].rating.rate

7. $[*].title
8. fetch the product price where id is 3 *****IMP QUERY
$[?(@.id==3)].price

9. fetch titles and prices of the product where category = jewelery *****IMP QUERY
$[?(@.category == 'jewelery')].[title,price]

10. fetch title of the products with rating rate >=4.5
$[?(@.rating.rate>=4.5)].[title]

11. fetch title of the producs where price is less than 30$
$[?(@.price<30)].[title]

12. fetch title and jewellery category of the products where rate is between 2 and 4:   *****IMP QUERY
$[?(@.category == 'jewelery' && @.rating.rate >=2 && @.rating.rate <= 4)].[title,category]

13. fetch title and women's clothing category where rating count >=100 and price <10$ *****IMP QUERY
$[?(@.category == 'women\'s clothing' && @.rating.count >=100 && @.price < 10)].[tile,category]

14 . Fetch id,title,price,rate,count whose category is jewelery ---- Multiple
$[?(@.category=='jewelery')].[id,title,price,rate,count]

 */