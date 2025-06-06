package ru.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static ru.praktikum.api.Client.spec;
import static ru.praktikum.constants.Url.*;

public class UserSteps {

    @Step("Создание нового пользователя")
    public ValidatableResponse userCreate(UserCreateAndEditRequest userCreateAndEditRequest) {
        return spec()
                .body(userCreateAndEditRequest)
                .post(AUTH_API)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse userLogin(UserLoginRequest userLoginRequest) {
        return spec()
                .body(userLoginRequest)
                .post(LOGIN_API)
                .then();
    }

    @Step("Получение accessToken")
    public String getToken(ValidatableResponse validatableResponse) {
        return validatableResponse.extract().path("accessToken");
    }

    @Step("Удаление пользователя")
    public ValidatableResponse userDelete(String accessToken) {
        return spec()
                .header("Authorization", accessToken)
                .delete(USER_API)
                .then();
    }
}
