package ru.learnqa.socksshop.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup(){

        RestAssured.baseURI="http://130.193.54.99/";

    }
}
