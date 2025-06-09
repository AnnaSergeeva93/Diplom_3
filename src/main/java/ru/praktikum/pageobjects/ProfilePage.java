package ru.praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;

    //локатор кнопки Конструктор
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //локатор логотипа
    private By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']");
    //локатор кнопка Выход
    private By exitButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажатие на кнопку Конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Проверка отображения кнопки Выйти")
    public boolean exitButtonIsDisplayed() {
        return driver.findElement(exitButton).isDisplayed();
    }

    @Step("Нажатие на кнопку Выход")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
}
