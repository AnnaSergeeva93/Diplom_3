package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.api.UserLoginRequest;
import ru.praktikum.api.UserSteps;
import ru.praktikum.constants.RegisterUserData;
import ru.praktikum.pageobjects.LoginPage;
import ru.praktikum.pageobjects.MainPage;
import ru.praktikum.pageobjects.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;

    private String email;
    private String password;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driver = driverRule.getDriver();
    }

    @Test
    @DisplayName("Регистрация пользователя с валидными данными")
    @Description("Успешная регистрация пользователя с валидными данными")
    public void userRegistrationWithValidData() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameEmailAndPassword(RegisterUserData.NAME, RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        registrationPage.clickRegisterButton();

        this.email = RegisterUserData.EMAIL;
        this.password = RegisterUserData.VALID_PASSWORD;


        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();
        MainPage mainPage = new MainPage(driver);

        assertTrue("Не произошёл вход в аккауент и переход на главную страницу", mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Регистрация пользователя с невалидными данными")
    @Description("Регистрация пользователя с невалидными данными")
    public void userRegistrationWithInvalidData() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameEmailAndPassword(RegisterUserData.NAME, RegisterUserData.EMAIL, RegisterUserData.INVALID_PASSWORD);
        registrationPage.clickRegisterButton();

        assertTrue("Не появилась ошибка о некорректном пароле", registrationPage.wrongPasswordTextIsDisplayed());
    }


    @After
    public void tearDown() {
        UserSteps userSteps = new UserSteps();
        UserLoginRequest userLoginRequest = new UserLoginRequest(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        ValidatableResponse responseLogin = userSteps.userLogin(userLoginRequest);
        String accessToken = userSteps.getToken(responseLogin);
        userSteps.userDelete(accessToken);

        driver.quit();
    }
}
