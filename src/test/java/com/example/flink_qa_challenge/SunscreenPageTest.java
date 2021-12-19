package com.example.flink_qa_challenge;

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

public class SunscreenPageTest {
    SunscreenPage sunscreenPage = new SunscreenPage();

    @BeforeClass
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.browser = "firefox";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeSuite
    public void setUp() {
        open("https://weathershopper.pythonanywhere.com/sunscreen");
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWebDriver();
    }

    @Test
    @Description("Access the elements in the page and add them to the cart")
    public void addSunscreenToCart(){
        sunscreenPage.getValuesFromProducts();
    }


}
