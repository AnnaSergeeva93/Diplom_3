package ru.praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.praktikum.constants.Url.MAIN_URL;

public class MainPage {
    private WebDriver driver;

    //локатор кнопки Войти в аккаунт
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //локатор кнопки Личный кабинет
    private By profileButton = By.xpath(".//a[@href='/account']");
    //локатор кнопки Оформить заказ
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    //локатор раздела Булки
    private By sectionBun = By.xpath(".//span[text()='Булки']");
    //локатор раздела Соусы
    private By sectionSauce = By.xpath(".//span[text()='Соусы']");
    //локатор раздела Начинки
    private By sectionTopping = By.xpath(".//span[text()='Начинки']");
    //локатор текста выбранного раздела Булки, Соусы или Начинки
    private By selectedSectionText = By.xpath(".//div[contains(@class, 'current')]/span");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Открытие главной страницы Stellar Burgers")
    public void open() {
        driver.get(MAIN_URL);
    }

    @Step("Нажатие на кнопку Войти в аккаунт")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку Личный кабинет")
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Нажатие на раздел Булки")
    public void clickBunsSection() {
        driver.findElement(sectionBun).click();
    }

    @Step("Нажатие на раздел Соусы")
    public void clickSaucesSection() {
        driver.findElement(sectionSauce).click();
    }

    @Step("Нажатие на раздел Начинки")
    public void clickToppingsSection() {
        driver.findElement(sectionTopping).click();
    }

    @Step("Проверка отображения кнопки Личный кабинет")
    public boolean profileButtonIsDisplayed() {
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Проверка отображения кнопки Оформить заказ")
    public boolean createOrderButtonIsDisplayed() {
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Проверка текста выбранной секции текущего меню")
    public String returnTextOfSelectedSection(String sectionName) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.textToBe(selectedSectionText, sectionName));
        return driver.findElement(selectedSectionText).getText();
    }

}
