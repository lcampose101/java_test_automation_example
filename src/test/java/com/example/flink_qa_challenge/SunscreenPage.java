package com.example.flink_qa_challenge;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;

import static com.codeborne.selenide.Selenide.*;

public class SunscreenPage {



private SelenideElement sunscreenProduct1 = $(By.cssSelector("div.row:nth-child(2) > div:nth-child(1)"));
private SelenideElement sunscreenProduct2  =$(By.cssSelector("div.row:nth-child(2) > div:nth-child(3)"));
private SelenideElement sunscreenProduct3 = $(By.cssSelector("div.text-center:nth-child(5)"));
private SelenideElement sunscreenProduct4 = $(By.cssSelector("div.row:nth-child(3) > div:nth-child(1)"));
private SelenideElement sunscreenProduct5 = $(By.cssSelector("div.text-center:nth-child(2)"));
private SelenideElement sunscreenProduct6 = $(By.cssSelector("div.row:nth-child(3) > div:nth-child(3)"));

public void getValuesFromProducts(){
        String sunscreenProduct1Text = sunscreenProduct1.getText();
        String sunscreenProduct2Text = sunscreenProduct2.getText();
        String sunscreenProduct3Text = sunscreenProduct3.getText();
        String sunscreenProduct4Text = sunscreenProduct4.getText();
        String sunscreenProduct5Text = sunscreenProduct5.getText();
        String sunscreenProduct6Text = sunscreenProduct6.getText();

        String sunscreenPriceP1 = sunscreenProduct1.getAttribute("onclick");
        System.out.println(sunscreenProduct1Text);
        System.out.println(sunscreenPriceP1);

}
}


