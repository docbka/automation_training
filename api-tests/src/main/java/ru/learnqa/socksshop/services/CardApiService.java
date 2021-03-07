package ru.learnqa.socksshop.services;

import io.restassured.response.Response;
import ru.learnqa.socksshop.assertions.AssertableResponse;
import ru.learnqa.socksshop.conditions.Condition;
import ru.learnqa.socksshop.payloads.CardsPayload;

import java.util.Map;

public class CardApiService extends ApiService{

    public static AssertableResponse cards(CardsPayload card, Map<String, String> cookie){

        return new AssertableResponse(setUp().body(card)
                .cookies(cookie)
                .when()
                .post("cards"));
    }

}
