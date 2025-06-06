package ru.praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import static ru.praktikum.constants.Url.LOGIN_PAGE;

public class LoginPage {
    private WebDriver driver;

    //локатор поля Email
    private By emailField = By.xpath(".//input[@type='text']");
    //локатор поля Пароль
    private By passwordField = By.xpath(".//input[@type='password']");
    //локатор кнопки Войти
    private By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Открытие страницы входа в аккаунт")
    public void open() {
        driver.get(LOGIN_PAGE);
    }

    @Step("Заполнение полей Email и Пароль")
    public void fillEmailAndPassword(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Проверка отображения кнопки Войти")
    public boolean loginButtonIsDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
