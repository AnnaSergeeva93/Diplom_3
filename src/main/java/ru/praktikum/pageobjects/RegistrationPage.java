package ru.praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.praktikum.constants.Url;

public class RegistrationPage {
    private WebDriver driver;

    //локатор поля Имя
    private By nameField = By.xpath("(//*[contains(@class, 'input pr-6 pl-6')]/input)[1]");
    //локатор поля Email
    private By emailField = By.xpath("(//*[contains(@class, 'input pr-6 pl-6')]/input)[2]");
    //локатор поля Пароль
    private By passwordField = By.xpath(".//input[@type='password']");
    //локатор кнопки Зарегистрироваться
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //локатор кнопки Войти внизу страницы
    private By loginButton = By.xpath(".//a[@href='/login']");
    //локатор надписи Некорректный пароль
    private By wrongPasswordText = By.xpath(".//*[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get(Url.REGISTER_PAGE);
    }

    @Step("Заполнение полей Имя, Email и Пароль")
    public void fillNameEmailAndPassword(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие на кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Проверка отображения ошибки о некорректном пароле")
    public boolean wrongPasswordTextIsDisplayed() {
        return driver.findElement(wrongPasswordText).isDisplayed();
    }

    @Step("Нажатие на кнопку Войти внизу страницы")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
