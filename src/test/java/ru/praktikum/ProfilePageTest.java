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
import ru.praktikum.pageobjects.ProfilePage;

import static org.junit.Assert.assertTrue;

public class ProfilePageTest {

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
    @DisplayName("Переход в личный кабинет")
    @Description("Переход в личный кабинет по клику на кнопку «Личный кабинет» на главной странице")
    public void switchToProfilePageFromMainPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue("Не произошел переход в личный кабинет", profilePage.exitButtonIsDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор через кнопку «Конструктор»")
    @Description("Переход в конструктор из личного кабинета по клику на кнопку «Конструктор»")
    public void switchToConstructorAfterConstructorButtonClick() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickConstructorButton();

        assertTrue("Не произошел переход в конструктор", mainPage.profileButtonIsDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор через логотип")
    @Description("Переход в конструктор из личного кабинета по клику на логотип Stellar Burgers")
    public void switchToConstructorAfterLogoClick() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogo();

        assertTrue("Не произошел переход в конструктор", mainPage.profileButtonIsDisplayed());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Выход из аккаунта по клику на кнопку «Выйти» в личном кабинете")
    public void exitAfterExitButtonClick() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillEmailAndPassword(RegisterUserData.EMAIL, RegisterUserData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickExitButton();

        assertTrue("Не произошел выход из аккаунта", loginPage.loginButtonIsDisplayed());
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
