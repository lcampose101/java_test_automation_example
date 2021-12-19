package com.example.flink_qa_challenge;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
public class MoisturizerPageTest {
    MoisturizerPage moisturizerPage = new MoisturizerPage();
    @BeforeClass
    public static void setUpAll() {
        Configuration.browserSize="1366x900";
        Configuration.browser = "firefox";
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeSuite
    public void setUp() {
        open("https://weathershopper.pythonanywhere.com/moisturizer");
    }

    @Test
    @Description("Access the elements in the page and add them to the cart")
    public void addSunscreenToCart(){
        moisturizerPage.getValuesFromProductsAndAddToCart();
        moisturizerPage.accessCart();
        moisturizerPage.purchaseItems();
    }
}
