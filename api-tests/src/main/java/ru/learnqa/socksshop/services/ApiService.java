package ru.learnqa.socksshop.services;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiService {

    //пересмотреть почему протектид??
    protected static RequestSpecification setUp(){

        return RestAssured.given().contentType(ContentType.JSON).log().all();

    }
}
