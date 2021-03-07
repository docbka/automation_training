package ru.learnqa.socksshop.services;
import io.restassured.response.Response;
import ru.learnqa.socksshop.assertions.AssertableResponse;
import ru.learnqa.socksshop.payloads.UserPayload;

public class UserApiService extends ApiService {
    //если сделать статик но не придется создавать поле в классе UsersTest??
    public static AssertableResponse registerUser(UserPayload user) {

                 return new AssertableResponse(setUp()
                .body(user)
                .when()
                .post("register"));
    }
}
