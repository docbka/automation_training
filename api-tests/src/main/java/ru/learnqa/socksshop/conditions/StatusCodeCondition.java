package ru.learnqa.socksshop.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusCodeCondition implements Condition {

    private final int statusCode;

    @Override
    public void check(Response response) {

        response.then().log().all()
                .assertThat()
                .statusCode(statusCode);

    }
}