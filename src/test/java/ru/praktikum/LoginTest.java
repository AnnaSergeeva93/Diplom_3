package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.api.UserCreateAndEditRequest;
import ru.praktikum.api.UserLoginRequest;
import ru.praktikum.api.UserSteps;
import ru.praktikum.constants.RegisterUserData;
import ru.praktikum.pageobjects.LoginPage;
import ru.praktikum.pageobjects.MainPage;
import ru.praktikum.pageobjects.PasswordRecoveryPage;
import ru.praktikum.pageobjects.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @Rule
    public DriverRule driverRule = new DriverRule();


    @Before
    public void setUp() {
        UserSteps userSteps = new UserSteps();
        UserCreateAndEditRequest userCreateAndEditRequest = new UserCreateAndEditRequest(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD, RegisterUserData.NAME);
        userSteps.userCreate(userCreateAndEditRequest);

        driver = driverRule.getDriver();
    }

    @Test
    @DisplayName("Вход в аккаунт по кнопке «Войти в аккаунт» на главной странице")
    @Description("Вход в аккаунт по кнопке «Войти в аккаунт» на главной странице")
    public void loginWithManePageLoginButton () {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку «Личный кабинет»")
    @Description("Вход в аккаунт через кнопку «Личный кабинет» на главной странице")
    public void loginWithManePageProfileButton () {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickProfileButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме регистрации")
    @Description("Вход в аккаунт через кнопку в форме регистрации")
    public void loginWithRegistrationPageLoginButton () {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме восстановления пароля")
    @Description("Вход в аккаунт через кнопку в форме восстановления пароля")
    public void loginWithPasswordRecoveryPageLoginButton () {
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.open();
        passwordRecoveryPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @After
    public void tearDown() {
        UserSteps userSteps = new UserSteps();
        UserLoginRequest userLoginRequest = new UserLoginRequest(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        ValidatableResponse responseLogin = userSteps.userLogin(userLoginRequest);
        String accessToken = userSteps.getToken(responseLogin);
        userSteps.userDelete(accessToken);
    }
}
