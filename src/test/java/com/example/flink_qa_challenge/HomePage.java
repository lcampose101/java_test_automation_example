package com.example.flink_qa_challenge;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private SelenideElement degreesDisplay = $(By.id("temperature"));
    private SelenideElement buyMoisturizersBtn = $(By.linkText("Buy moisturizers"));
    private SelenideElement buySunscreensBtn = $(By.linkText("Buy sunscreens"));

    public int convertTemperature(String temperature){
        String sliced_temperature = temperature.substring(0,2);
        int temperature_for_comparison = Integer.parseInt(sliced_temperature);
        return temperature_for_comparison;
    }
    public String getTemperatureAndAccessShop(){
        String shopAccessed = "";
        String actual_temperature = "";
        int temperature_for_comparison = 0;
        if(degreesDisplay.isDisplayed()){
         try{
             actual_temperature = degreesDisplay.getText();
             temperature_for_comparison = convertTemperature(actual_temperature);
             if (temperature_for_comparison >34 && buySunscreensBtn.isDisplayed()){
                 buySunscreensBtn.click();
                 shopAccessed = "sunscreens";
             }else{
                 if(temperature_for_comparison <19 && buyMoisturizersBtn.isDisplayed()){
                     buyMoisturizersBtn.click();
                     shopAccessed="moisturizers";
                 }
             }
         }catch(Exception error){
             System.out.println(error);
         }
        }
        else{
            System.out.println("Not able to find the element with the temperature display");
        }
        return shopAccessed;

    }
}
