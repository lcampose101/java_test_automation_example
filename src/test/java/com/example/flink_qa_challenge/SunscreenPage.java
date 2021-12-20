package com.example.flink_qa_challenge;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;


import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;

import static com.codeborne.selenide.Selenide.*;

public class SunscreenPage {


//Variables that house the css selectors for the products on the page, as well as an array for use inside the getValuesFromProductsAndAddToCart method
private SelenideElement sunscreenProduct1 = $(By.cssSelector("div.row:nth-child(2) > div:nth-child(1)"));
private SelenideElement sunscreenProduct2  =$(By.cssSelector("div.row:nth-child(2) > div:nth-child(3)"));
private SelenideElement sunscreenProduct3 = $(By.cssSelector("div.text-center:nth-child(5)"));
private SelenideElement sunscreenProduct4 = $(By.cssSelector("div.row:nth-child(3) > div:nth-child(1)"));
private SelenideElement sunscreenProduct5 = $(By.cssSelector("div.text-center:nth-child(2)"));
private SelenideElement sunscreenProduct6 = $(By.cssSelector("div.row:nth-child(3) > div:nth-child(3)"));
public ArrayList<SelenideElement> allProductElements = new ArrayList<>();

//Variables that house selectors for the cart screen
private SelenideElement cartBtn = $(By.xpath("//button[@onclick='goToCart()']"));
private SelenideElement firstRowCheckoutElement = $(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1)"));
private SelenideElement secondRowCheckoutElement = $(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(2)"));
private SelenideElement payWithCardBtn = $(By.xpath("//span[contains(.,'Pay with Card')]"));

//Variables that are used for comparisons between methods outputs
String firstItemRowCheckout = "";
String secondItemRowCheckout="";
String priceFirstItemRowCheckout = "";
String priceSecondItemRowCheckout = "";
ArrayList<String> itemsReadyForCheckout = new ArrayList<>();

//Variables for the Stripe form test
String stripeFrameID = "https://checkout.stripe.com/v3/k7NvLmX7CQ4t5S85kQXOg.html?distinct_id=7dfe440b-95fc-5a39-5527-ad6dcecd527e";
private String testEmail = "luis.test@testscript.com";
private SelenideElement emailFieldStripeForm = $(By.xpath("//input[@type='email']"));
private SelenideElement cardNumberFieldStripeForm = $(By.xpath("//input[contains(@placeholder,'Card number')]"));
private SelenideElement monthDayFieldStripeForm = $(By.xpath("//input[@placeholder='MM / YY']"));
private SelenideElement cvcFieldStripeForm = $(By.xpath("//input[@placeholder='CVC']"));
private SelenideElement payTotal = $(By.xpath("//span[@class='iconTick']"));
private String  testCardStrip = "4242424242424242";
private String cardBrand = "Visa";
private String expirationDates= "0224";
private String cvcCard = "003";

public String getTitlesFromProducts(SelenideElement element){
        String productName = element.find(By.className("font-weight-bold")).getText();
        return productName;
}
        //Method that retrieves the prices from each product and converts them to Int
public int getPricesFromProducts(SelenideElement element){
        List<WebElement> pTagElements = element.findElements(By.tagName("p"));
        WebElement elementWithPrice = pTagElements.get(1);
        String strPriceOfProduct  = elementWithPrice.getText();
        strPriceOfProduct = strPriceOfProduct.toLowerCase();
        strPriceOfProduct = strPriceOfProduct.replace("price:","");
        strPriceOfProduct = strPriceOfProduct.replace("rs","");
        strPriceOfProduct = strPriceOfProduct.replace(".","");
        strPriceOfProduct = strPriceOfProduct.replace(" ","");
        int intPriceOfProduct = Integer.parseInt(strPriceOfProduct);
        return intPriceOfProduct;

}
        //Method that compares the products in order to retrieve the correct index that will aid in the Xpath selector for the button
public SelenideElement compareProducts(SelenideElement element, String productToBuy){
        SelenideElement elementMatched = null;
        String sunscreenProductText = getTitlesFromProducts(element).toLowerCase();
        if(sunscreenProductText.equals(productToBuy)){
                System.out.println("The elements that matched are"+sunscreenProductText.toString()+"and"+productToBuy);
                elementMatched = element;
        }
        return  elementMatched;
}
        //Main method that determines which items have the lowest price from the qualifying conditions and adds them to the cart
public void getValuesFromProductsAndAddToCart(){

        //Adding all of the SelenidElements that will be utilized for a comparison at the end of the script
        allProductElements.add(sunscreenProduct1);
        allProductElements.add(sunscreenProduct2);
        allProductElements.add(sunscreenProduct3);
        allProductElements.add(sunscreenProduct4);
        allProductElements.add(sunscreenProduct5);
        allProductElements.add(sunscreenProduct6);

        //Get the titles of each product element for further comparisons
        String sunscreenProduct1Text = getTitlesFromProducts(sunscreenProduct1);
        String sunscreenProduct2Text = getTitlesFromProducts(sunscreenProduct2);
        String sunscreenProduct3Text = getTitlesFromProducts(sunscreenProduct3);
        String sunscreenProduct4Text = getTitlesFromProducts(sunscreenProduct4);
        String sunscreenProduct5Text = getTitlesFromProducts(sunscreenProduct5);
        String sunscreenProduct6Text = getTitlesFromProducts(sunscreenProduct6);

        //Get the price of each product element for further comparisons
        getPricesFromProducts(sunscreenProduct1);
        int priceProduct1 = getPricesFromProducts(sunscreenProduct1);
        int priceProduct2 = getPricesFromProducts(sunscreenProduct2);
        int priceProduct3 = getPricesFromProducts(sunscreenProduct3);
        int priceProduct4 = getPricesFromProducts(sunscreenProduct4);
        int priceProduct5 = getPricesFromProducts(sunscreenProduct5);
        int priceProduct6 = getPricesFromProducts(sunscreenProduct6);


        //Unsorted Hashmap
        HashMap<String,Integer> allSunscreenProducts = new HashMap<>();
        allSunscreenProducts.put(sunscreenProduct1Text.toLowerCase(),priceProduct1);
        allSunscreenProducts.put(sunscreenProduct2Text.toLowerCase(),priceProduct2);
        allSunscreenProducts.put(sunscreenProduct3Text.toLowerCase(),priceProduct3);
        allSunscreenProducts.put(sunscreenProduct4Text.toLowerCase(),priceProduct4);
        allSunscreenProducts.put(sunscreenProduct5Text.toLowerCase(),priceProduct5);
        allSunscreenProducts.put(sunscreenProduct6Text.toLowerCase(), priceProduct6);

        //HashMaps that will contain the filtered values of the sunscreens and the numbers they are
        HashMap<String,Integer> sunscreenSPF30 = new HashMap<>();
        HashMap<String,Integer> sunscreensSPF50 = new HashMap<>();

        allSunscreenProducts.forEach(
                (key,value)
                -> {
                        if (key.contains("spf-30")) {
                                sunscreenSPF30.put(key,value);
                        }else{
                                if (key.contains("spf-50")){
                                        sunscreensSPF50.put(key,value);
                                }else{
                                        System.out.println("is not a valid product for this test scenario");
                                }
                        }
                }
        );

        //Traversing through the Hashmap to retrieve the name of the sunscreen with the lowest price for spf30
        Map.Entry<String,Integer> minSPF30 = null;
        for (Map.Entry<String,Integer> entry:sunscreenSPF30.entrySet()){
                if(minSPF30 ==null ||  minSPF30.getValue() > entry.getValue()){
                        minSPF30 = entry;
                }
        }

        //Traversing through the Hashmap to retrieve the name of the sunscreen with the lowest price for spf50
        Map.Entry<String,Integer> minSPF50 = null;
        for (Map.Entry<String,Integer> entry:sunscreensSPF50.entrySet()){
                if(minSPF50 ==null ||  minSPF50.getValue() > entry.getValue()){
                        minSPF50 = entry;
                }
        }

        //Adding to the variables nameOf the values that are returned from getting the Key from the hashmap
        String nameOfProductToAddSPF30 = minSPF30.getKey();
        String nameOfProductToAddSPF50 = minSPF50.getKey();
        System.out.println(nameOfProductToAddSPF30);
        System.out.println(nameOfProductToAddSPF50);
        SelenideElement spf30ProductSelected = null;
        SelenideElement spf50ProductSelected = null;
        int indexOfElementMatchedSPF30 = 0;
        int indexOfElementMatchedSPF50 = 0;

        for(int i = 0; i<allProductElements.size(); i++){
                SelenideElement resultElement = compareProducts(allProductElements.get(i),nameOfProductToAddSPF30.toLowerCase());
                if (resultElement!= null){
                        System.out.println("Element is no longer null and it matched");
                        spf30ProductSelected = resultElement;
                        indexOfElementMatchedSPF30 = i;
                        allProductElements.get(i).toString();
                        firstItemRowCheckout = getTitlesFromProducts(allProductElements.get(i));
                        System.out.println(firstItemRowCheckout);
                        break;
                }

        }
        for(int i = 0; i<allProductElements.size(); i++){
                SelenideElement resultElement = compareProducts(allProductElements.get(i),nameOfProductToAddSPF50);
                if (resultElement!= null){
                        System.out.println("Element is no longer null and it matched");
                        spf50ProductSelected = resultElement;
                        indexOfElementMatchedSPF50=i;
                        allProductElements.get(i).toString();
                        secondItemRowCheckout = getTitlesFromProducts(allProductElements.get(i));
                        System.out.println(secondItemRowCheckout);
                        break;
                }

        }

        try {
        // Code that scrolls the element to view, retrieves the value of the title, and access both the element that contains the title of the product and the add button
                //in order to access the button it is necessary to use the Xpath and add the matching value as a variable previously defined

                spf30ProductSelected.scrollIntoView("{ behavior: 'smooth', block: 'center' }");
                String exactStringOfElementSPF30 = getTitlesFromProducts(spf30ProductSelected);
                indexOfElementMatchedSPF30= indexOfElementMatchedSPF30+1;
                SelenideElement interProductSPF30 = $(By.xpath("//p[@class='font-weight-bold top-space-10'][contains(.,'"+exactStringOfElementSPF30+"')]"));
                SelenideElement addButtonSPF30 = $(By.xpath("(//button[@class='btn btn-primary'][contains(.,'Add')])["+indexOfElementMatchedSPF30+"]"));
                interProductSPF30.click();
                addButtonSPF30.scrollIntoView("{ behavior: 'smooth', block: 'center' }");
                addButtonSPF30.shouldBe(visible);
                addButtonSPF30.click();


                spf50ProductSelected.scrollIntoView("{ behavior: 'smooth', block: 'center' }");
                String exactStringOfElementSPF50 = getTitlesFromProducts(spf30ProductSelected);
                secondItemRowCheckout = exactStringOfElementSPF50;
                indexOfElementMatchedSPF50 = indexOfElementMatchedSPF50+1;
                SelenideElement interProductSPF50 = $(By.xpath("//p[@class='font-weight-bold top-space-10'][contains(.,'"+exactStringOfElementSPF50+"')]"));
                SelenideElement addButtonSPF50 = $(By.xpath("(//button[@class='btn btn-primary'][contains(.,'Add')])["+indexOfElementMatchedSPF50+"]"));
                interProductSPF50.click();
                addButtonSPF50.scrollIntoView("{ behavior: 'smooth', block: 'center' }");
                addButtonSPF50.shouldBe(visible);
                addButtonSPF50.click();

        } catch (Exception error){
                System.out.println(error);
        }
}
//Method that asserts that the item in the cart added are the correct ones
public void checkoutPricesCorrect(SelenideElement element, SelenideElement element2){

        String firstItemCheckout = element.getText();
        String secondItemCheckout = element2.getText();
        assertThat(firstItemCheckout).contains(priceFirstItemRowCheckout);
        assertThat(secondItemCheckout).contains(priceSecondItemRowCheckout);
}
///Method that asserts that the item in the cart added are the correct ones
public void checkoutItemsCorrect(SelenideElement element, SelenideElement element2){
        String firstItemCheckout = element.getText();
        String secondItemCheckout = element2.getText();
        assertThat(firstItemCheckout).contains(firstItemRowCheckout);
        assertThat(secondItemCheckout).contains(secondItemRowCheckout);
}

        //Method that access the cart to do the asserts and access the form
public void accessCart(){
        cartBtn.shouldBe(visible);
        if (cartBtn.isDisplayed()){
                try {
                        cartBtn.click();
                        checkoutItemsCorrect(firstRowCheckoutElement,secondRowCheckoutElement);
                        payWithCardBtn.click();

                }catch (Exception error){
                        System.out.println(error);
                }
        }
}
public void purchaseItems(){
        Selenide.switchTo().frame(0);
        String itemsPurchased = "";
        emailFieldStripeForm.shouldBe(visible);
        if (emailFieldStripeForm.isDisplayed()){
                try{
                        emailFieldStripeForm.sendKeys(testEmail);
                        cardNumberFieldStripeForm.sendKeys(testCardStrip);
                        monthDayFieldStripeForm.sendKeys(expirationDates);
                        cvcFieldStripeForm.sendKeys(cvcCard);
                        payTotal.click();
                        itemsPurchased = "order successful";

                }catch(Exception error){

                }
                System.out.println(itemsPurchased);
        }
}
}


