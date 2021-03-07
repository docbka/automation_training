package ru.learnqa.socksshop.conditions;

import io.restassured.response.Response;

public interface Condition {

    void check(Response response);

}
