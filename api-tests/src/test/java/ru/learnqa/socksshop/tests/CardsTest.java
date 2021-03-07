package ru.learnqa.socksshop.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.learnqa.socksshop.assertions.AssertableResponse;
import ru.learnqa.socksshop.conditions.Conditions;
import ru.learnqa.socksshop.conditions.StatusCodeCondition;
import ru.learnqa.socksshop.payloads.CardsPayload;
import ru.learnqa.socksshop.payloads.UserPayload;
import ru.learnqa.socksshop.responses.CardCreateResponse;
import ru.learnqa.socksshop.services.CardApiService;
import ru.learnqa.socksshop.services.UserApiService;

import java.util.Locale;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

public class CardsTest extends BaseTest {

    private final Faker faker = new Faker(new Locale("ru"));

    @Test
    public void testCreateNewCard() {

        UserPayload user = new UserPayload();
        user.username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password());

        AssertableResponse reg = UserApiService.registerUser(user);

        String userId = reg.asPojo(CardCreateResponse.class).getId();
        Map<String, String> cookieRegResp = reg.getCookies();


        CardsPayload card = new CardsPayload();
        card.ccv(String.valueOf((int) (Math.random() * 1000)))
                .expires(faker.business().creditCardExpiry())
                .longNum(faker.business().creditCardNumber())
                .userID(userId);


        CardApiService.cards(card, cookieRegResp)
                .shouldHave(Conditions.statusCode(200));

    }
}
