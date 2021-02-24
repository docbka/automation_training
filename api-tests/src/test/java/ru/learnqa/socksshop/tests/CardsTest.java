package ru.learnqa.socksshop.tests;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.learnqa.socksshop.CardsPayload;
import ru.learnqa.socksshop.UserPayload;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

public class CardsTest {

    @BeforeClass
    public void setup(){

        RestAssured.baseURI="http://84.201.137.101/";

    }

    @Test
    public void testCreateNewCard(){

        UserPayload user = new UserPayload();
        user.setUsername(RandomStringUtils.randomAlphanumeric(8));
        user.setEmail("test@mail.gov");
        user.setPassword("test123");

        String userId = RestAssured.given().contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("register")
                .then().log().all()
                .extract().response().jsonPath().get("id");

        CardsPayload card = new CardsPayload();
        card.setCcv(String.valueOf((int)(Math.random()*1000)));
        card.setExpires((1 + (int) (Math.random() * 12)) + "/" + (21 + (int) (Math.random() * 99)));
        card.setLongNum(RandomStringUtils.randomNumeric(16));
        card.setUserID(userId);

        RestAssured.given().contentType(JSON).log().all()
                .body(card)
                .when()
                .post("cards")
                .then().log().all()
                .assertThat()
                .statusCode(200);


    }
}
