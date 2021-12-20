package com.example.flink_qa_challenge;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.attribute;

public class ParallelTest {
        HomePageTest homePageTest  = new HomePageTest();

    @Test
    public void FireFoxTest(){
        Configuration.browser = "firefox";
        Configuration.browserSize = "1400x1000";
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
        homePageTest.userDecidesProductPurchaseHomePage();
    }

    @Test
    public void ChromeTest(){
        Configuration.browser = "chrome";
        Configuration.browserSize = "1400x1000";
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
        homePageTest.userDecidesProductPurchaseHomePage();
    }
}
