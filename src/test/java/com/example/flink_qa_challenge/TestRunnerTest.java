package com.example.flink_qa_challenge;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestRunnerTest {
    HomePageTest homePageTest = new HomePageTest();
    SunscreenPageTest sunscreenPageTest = new SunscreenPageTest();

    @BeforeClass
    public static void setUpAll() {
        Configuration.browserSize = "1400x900";
        Configuration.browser = "firefox";
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeSuite
    public void setUp() {
        open("https://weathershopper.pythonanywhere.com/");
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWebDriver();
    }

    @Test
    @Description("The user access the homepage website and depending on the temperature will purchase different elements")
    public void userDecidesProductPurchaseHomePage() throws InterruptedException {
        homePageTest.userDecidesProductPurchaseHomePage();
    }

}
