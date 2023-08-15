package LombokPOJO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getProducts {

    @Test
    public void getAllProductswithPOJO() throws JsonProcessingException {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response =  given()
                .when().log().all()
                .get("/products");
        /*
        In get call we have to do de-serialization -> JSON to POJO as we are getting response in JSON format
        we are converting JSON response into POJO (Java Object for validations)
        Note: When we do not need to pass payload (JSON or any other format File, XML) we dont need serialization
        In that case only de serialization is required.
         */

        // JSON to POJO -> Deserialization
        // need to user ObjectMapper class
        //crate Object for this class

        //another way to get the response is jsonpath.read() and user JQL queries to get the data we want for Deserialization
        //Jayway Json JQL query method is an easier way
        //object mapeer use from jackson bind library

        //to remember = ObjectMapper class , create Object , map with out POJO Class with below format , readvalue()

        ObjectMapper mapper = new ObjectMapper();
        ProductPojo product[] = mapper.readValue(response.getBody().asString(), ProductPojo[].class);
        //Reason to Add [] against product , we are getting JSON repsonse with [] which means its a array response
        //if JSON response is object do not store it in []

        for(ProductPojo p :product)
        {
            System.out.println("ID : " +p.getId());
            System.out.println("Title : " +p.getTitle());
            System.out.println("Category : " +p.getCategory());
            System.out.println("Description : " +p.getDescription());
            System.out.println("Image : " +p.getImage());
            System.out.println("Price : " +p.getPrice());
            System.out.println("Rate : " +p.getRating().getRate()); //calling from inner class
            System.out.println("Count : " +p.getRating().getCount());
            System.out.println("----------------------------------");

        }

    }

    @Test
    public void getAllProductsWithLombokClass() throws JsonProcessingException {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response =  given()
                .when().log().all()
                .get("/products");
        /*
        In get call we have to do de-serialization -> JSON to POJO as we are getting response in JSON format
        we are converting JSON response into POJO (Java Object for validations)
        Note: When we do not need to pass payload (JSON or any other format File, XML) we dont need serialization
        In that case only de serialization is required.
         */

        // JSON to POJO -> Deserialization
        // need to user ObjectMapper class
        //crate Object for this class

        //another way to get the response is jsonpath.read() and user JQL queries to get the data we want for Deserialization
        //Jayway Json JQL query method is an easier way
        //object mapeer use from jackson bind library

        //to remember = ObjectMapper class , create Object , map with out POJO Class with below format , readvalue()

        ObjectMapper mapper = new ObjectMapper();
        ProductLombok product[] = mapper.readValue(response.getBody().asString(), ProductLombok[].class);
        //Reason to Add [] against product , we are getting JSON repsonse with [] which means its a array response
        //if JSON response is object do not store it in []

        for(ProductLombok k :product)
        {
            System.out.println("ID : " +k.getId());
            System.out.println("Title : " +k.getTitle());
            System.out.println("Category : " +k.getCategory());
            System.out.println("Description : " +k.getDescription());
            System.out.println("Image : " +k.getImage());
            System.out.println("Price : " +k.getPrice());
            System.out.println("Rate : " +k.getRating().getRate()); //calling from inner class
            System.out.println("Count : " +k.getRating().getCount());
            System.out.println("----------------------------------");

        }

    }

}
