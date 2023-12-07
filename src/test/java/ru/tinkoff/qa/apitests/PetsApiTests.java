package ru.tinkoff.qa.apitests;

import apimodels.Category;
import apimodels.TagsItem;
import apimodels.UserRequest;
import apimodels.UserResponce;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.List;


public class PetsApiTests {
    UserRequest userRequest;

    @BeforeEach
    public void init() {
        userRequest = new UserRequest();
        userRequest.setId(123);
        Category category = new Category();
        category.setId(999);
        category.setName("dogs");
        userRequest.setName("puppy");
        userRequest.setPhotoUrls(List.of("https://w.forfun.com/fetch/9e/9e7088fd154559d505129ab2cede1c49.jpeg",
                "https://krasivosti.pro/uploads/posts/2022-09/1662049314_9-krasivosti-pro-p-shchenki-kern-terera-sobaki-17.jpg"));
        TagsItem tagsItem = new TagsItem();
        tagsItem.setId(7);
        tagsItem.setName("Super");
        userRequest.setStatus("available");
    }

    @Test
    public void addTest() {
        UserResponce userResponce = RestAssured.given().
                contentType(ContentType.JSON).
                body(userRequest).
                post("https://petstore.swagger.io/v2/pet").
                as(UserResponce.class);
        Assertions.assertEquals(123, userResponce.getId(), "Check id dog");


    }

    @Test
    public void addCodeTest() {
        RestAssured.given().
                contentType(ContentType.JSON).
                body(userRequest).
                post("https://petstore.swagger.io/v2/pet").
                then().statusCode(200);

    }

    @Test
    public void getCodeTest() {
        RestAssured.given().
                contentType(ContentType.JSON).
                body(userRequest).
                get("https://petstore.swagger.io/v2/pet/" + userRequest.getId()).
                then().statusCode(200);
    }

    @Test
    public void putTest() {
        userRequest.setName("pyos");
        UserResponce userResponce = RestAssured.given().
                contentType(ContentType.JSON).
                body(userRequest).
                post("https://petstore.swagger.io/v2/pet").
                as(UserResponce.class);
        Assertions.assertEquals("pyos", userResponce.getName(), "Check  new name dog");


    }

    @Test
    public void deleteCodeTest() {

        RestAssured.given().
                contentType(ContentType.JSON).
                body(userRequest).
                delete("https://petstore.swagger.io/v2/pet/" + userRequest.getId()).
                then().statusCode(200);
    }

}
