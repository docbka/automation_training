package ru.learnqa.socksshop.assertions;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.learnqa.socksshop.conditions.Condition;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class AssertableResponse {

    private final Response response;

    public AssertableResponse shouldHave(Condition condition){

        condition.check(response);
        return this;

    }

    public <T>T asPojo(Class<T>tClass){
        return response.as(tClass);
    }


    public Map<String, String> getCookies(){

        return response.then().log().all().extract().response().getCookies();
    }


}
