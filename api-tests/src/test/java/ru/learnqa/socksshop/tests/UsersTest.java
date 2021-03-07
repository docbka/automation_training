package ru.learnqa.socksshop.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.learnqa.socksshop.conditions.Conditions;
import ru.learnqa.socksshop.conditions.StatusCodeCondition;
import ru.learnqa.socksshop.payloads.UserPayload;
import ru.learnqa.socksshop.responses.UserRegistrationResponse;
import ru.learnqa.socksshop.services.UserApiService;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.core.Is.is;

public class UsersTest extends BaseTest {

    private final Faker faker = new Faker();

    @Test
    public void testCanBeRegistrationNewUser() {

        UserPayload user = new UserPayload();
        user.username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password());
        UserRegistrationResponse response = UserApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("id", is(not(emptyString()))))
        .asPojo(UserRegistrationResponse.class);
        response.getId();
    }

    @Test
    public void testCanNotRegisterSameUserTwice() {
        UserPayload user = new UserPayload();
        user.username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password());

        UserApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("id", is(not(emptyString()))));


        UserApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(500));
    }
}
