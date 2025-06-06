package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.pageobjects.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {

    private WebDriver driver;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driver = driverRule.getDriver();
    }



    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Переход к разделу «Булки» на главной странице")
    public void switchToBunSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickToppingsSection();
        mainPage.clickBunsSection();

        String expectedText = "Булки";
        String actualText = mainPage.returnTextOfSelectedSection(expectedText);

        assertEquals("Не произошёл переход к нужному разделу", expectedText, actualText);
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Переход к разделу «Соусы» на главной странице")
    public void switchToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesSection();

        String expectedText = "Соусы";
        String actualText = mainPage.returnTextOfSelectedSection(expectedText);

        assertEquals("Не произошёл переход к нужному разделу", expectedText, actualText);
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Переход к разделу «Начинки» на главной странице")
    public void switchToToppingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickToppingsSection();

        String expectedText = "Начинки";
        String actualText = mainPage.returnTextOfSelectedSection(expectedText);

        assertEquals("Не произошёл переход к нужному разделу", expectedText, actualText);
    }

}
