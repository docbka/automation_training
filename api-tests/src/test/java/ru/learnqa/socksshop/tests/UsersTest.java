package ru.learnqa.socksshop.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.annotation.Contract;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.learnqa.socksshop.UserPayload;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.core.Is.is;

public class UsersTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ("http://84.201.137.101/");
    }

    @Test
    public void testCanBeRegistrationNewUser() {

        UserPayload user = new UserPayload();
        user.setUsername(RandomStringUtils.randomAlphanumeric(8));
        user.setEmail("test@mail.gov");
        user.setPassword("test123");

        RestAssured.given().contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("id", is(not(emptyString())));
    }

    @Test
    public void testCanNotRegisterSameUserTwice() {
        UserPayload user = new UserPayload();
        user.setUsername(RandomStringUtils.randomAlphanumeric(8));
        user.setEmail("test@mail.gov");
        user.setPassword("test123");

        RestAssured.given().contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("id", is(not(emptyString())));

        RestAssured.given().contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(500);

    }
}
