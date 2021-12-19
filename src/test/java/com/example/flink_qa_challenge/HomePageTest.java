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

public class HomePageTest {
    HomePage homepage = new HomePage();
    SunscreenPage sunscreenPage = new SunscreenPage();
    MoisturizerPage moisturizerPage = new MoisturizerPage();
    @BeforeClass
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.browser = "firefox";
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
    public void userDecidesProductPurchaseHomePage(){

        String shopAccessed = homepage.getTemperatureAndAccessShop();
        switch (shopAccessed){
            case "sunscreens":
                sunscreenPage.getValuesFromProductsAndAddToCart();
                sunscreenPage.accessCart();
                sunscreenPage.purchaseItems();
                break;

            case "moisturizers":
                moisturizerPage.getValuesFromProductsAndAddToCart();
                moisturizerPage.accessCart();
                moisturizerPage.purchaseItems();
                break;
            default:
                break;
        }


    }



}
