package com.pet.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetStore {

    @Test
    public void createPetUsingLombokBuilderPatter() throws JsonProcessingException {
        RestAssured.baseURI = "https://petstore.swagger.io";

        //create object for Category class
        PetLombok.Category category = new PetLombok.Category.CategoryBuilder()
                .id(1)
                .name("Dog")
                .build();


        List<String> photoUrls = Arrays.asList("https://www.dog1.com","https://www.dog2.com");

        //create objects for Tag class and then add them as list of tags
        PetLombok.Tag tag1 = new PetLombok.Tag.TagBuilder()
                .id(11)
                .name("Red")
                .build();

        PetLombok.Tag tag2 = new PetLombok.Tag.TagBuilder()
                .id(12)
                .name("Blank")
                .build();

        List<PetLombok.Tag> tagList = Arrays.asList(tag1,tag2);

        //Create main Lombok class object
        PetLombok pet = new PetLombok.PetLombokBuilder()
                .id(1)
                .name("Monty")
                .status("Available")
                .category(category)
                .photoUrls(photoUrls)
                .tags(tagList)
                .build();

        //get the response
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .body(pet)
                .when().log().all()
                .post("/v2/pet");
        response.prettyPrint();

        //get a response
        ObjectMapper mapper = new ObjectMapper();
        PetLombok petresp= mapper.<PetLombok>readValue(response.getBody().asString(), PetLombok.class);
        System.out.println("ID : " + petresp.getId());
        Assert.assertEquals(petresp.getId(), pet.getId());
        // Assertion to compare actual and expected ID perresp
        // object hold the actual ID after creation and pet classs object holds to input iD.
        System.out.println("Name : " + petresp.getName());
        //String assertion
        Assert.assertEquals(petresp.getName(),pet.getName());
        System.out.println("Tag ID: " + petresp.getTags().get(0).getId());
        //VIMP****** Asssrting the nested JSON elemnets
        Assert.assertEquals(petresp.getTags().get(0).getId(),pet.getTags().get(0).getId());
        System.out.println("Status : " + petresp.getStatus());
        System.out.println("PhotoUrls : " + petresp.getPhotoUrls());
        System.out.println("Category ID : " + petresp.getCategory().getId());
        System.out.println("Category Name : " + petresp.getCategory().getName());


    }
}
