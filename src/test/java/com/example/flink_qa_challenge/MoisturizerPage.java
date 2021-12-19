package com.example.flink_qa_challenge;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.*;
public class MoisturizerPage {
    private SelenideElement moisturizerProduct1 = $(By.cssSelector("div.row:nth-child(2) > div:nth-child(1)"));
    private SelenideElement moisturizerProduct2 =$(By.cssSelector("div.row:nth-child(2) > div:nth-child(3)"));
    private SelenideElement moisturizerProduct3 = $(By.cssSelector("div.text-center:nth-child(5)"));
    private SelenideElement moisturizerProduct4 = $(By.cssSelector("div.row:nth-child(3) > div:nth-child(1)"));
    private SelenideElement moisturizerProduct5 = $(By.cssSelector("div.text-center:nth-child(2)"));
    private SelenideElement moisturizerProduct6 = $(By.cssSelector("div.row:nth-child(3) > div:nth-child(3)"));
    public ArrayList<SelenideElement> allProductElements = new ArrayList<>();

    //Variables that house selectors for the cart screen
    private SelenideElement cartBtn = $(By.xpath("//button[@onclick='goToCart()']"));
    private SelenideElement firstRowCheckout = $(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1)"));
    private SelenideElement secondRowCheckout = $(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(2)"));
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

    public String getTitlesFromProductsCheckout(SelenideElement element ){
        String productName = element.find(By.tagName("td")).getText();
        return productName;
    }
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

    public SelenideElement compareProducts(SelenideElement element, String productToBuy){
        SelenideElement elementMatched = null;
        String sunscreenProductText = getTitlesFromProducts(element);
        sunscreenProductText = sunscreenProductText.toLowerCase();
        if(sunscreenProductText.contains(productToBuy)){
            elementMatched = element;
        }
        return  elementMatched;
    }
    public void getValuesFromProductsAndAddToCart(){

        //Adding all of the SelenidElements that will be utilized for a comparison at the end of the script
        allProductElements.add(moisturizerProduct1);
        allProductElements.add(moisturizerProduct2);
        allProductElements.add(moisturizerProduct3);
        allProductElements.add(moisturizerProduct4);
        allProductElements.add(moisturizerProduct5);
        allProductElements.add(moisturizerProduct6);

        //Get the titles of each product element for further comparisons
        String sunscreenProduct1Text = getTitlesFromProducts(moisturizerProduct1);
        String sunscreenProduct2Text = getTitlesFromProducts(moisturizerProduct2);
        String sunscreenProduct3Text = getTitlesFromProducts(moisturizerProduct3);
        String sunscreenProduct4Text = getTitlesFromProducts(moisturizerProduct4);
        String sunscreenProduct5Text = getTitlesFromProducts(moisturizerProduct5);
        String sunscreenProduct6Text = getTitlesFromProducts(moisturizerProduct6);

        //Get the price of each product element for further comparisons
        getPricesFromProducts(moisturizerProduct1);
        int priceProduct1 = getPricesFromProducts(moisturizerProduct1);
        int priceProduct2 = getPricesFromProducts(moisturizerProduct2);
        int priceProduct3 = getPricesFromProducts(moisturizerProduct3);
        int priceProduct4 = getPricesFromProducts(moisturizerProduct4);
        int priceProduct5 = getPricesFromProducts(moisturizerProduct5);
        int priceProduct6 = getPricesFromProducts(moisturizerProduct6);


        //Unsorted Hashmap
        HashMap<String,Integer> allSunscreenProducts = new HashMap<>();
        allSunscreenProducts.put(sunscreenProduct1Text.toLowerCase(),priceProduct1);
        allSunscreenProducts.put(sunscreenProduct2Text.toLowerCase(),priceProduct2);
        allSunscreenProducts.put(sunscreenProduct3Text.toLowerCase(),priceProduct3);
        allSunscreenProducts.put(sunscreenProduct4Text.toLowerCase(),priceProduct4);
        allSunscreenProducts.put(sunscreenProduct5Text.toLowerCase(),priceProduct5);
        allSunscreenProducts.put(sunscreenProduct6Text.toLowerCase(), priceProduct6);

        //HashMaps that will contain the filtered values of the sunscreens and the numbers they are
        HashMap<String,Integer> aloe = new HashMap<>();
        HashMap<String,Integer> almond = new HashMap<>();

        allSunscreenProducts.forEach(
                (key,value)
                        -> {
                    if (key.contains("aloe")) {
                        aloe.put(key,value);
                    }else{
                        if (key.contains("almond")){
                            almond.put(key,value);
                        }
                    }
                }
        );

        //Traversing through the Hashmap to retrieve the name of the sunscreen with the lowest price for spf30
        Map.Entry<String,Integer> aloeEntry = null;
        for (Map.Entry<String,Integer> entry:aloe.entrySet()){
            if(aloeEntry ==null ||  aloeEntry.getValue() > entry.getValue()){
                aloeEntry = entry;

            }
        }

        //Traversing through the Hashmap to retrieve the name of the sunscreen with the lowest price for spf50
        Map.Entry<String,Integer> almondEntry = null;
        for (Map.Entry<String,Integer> entry:almond.entrySet()){
            if(almondEntry ==null ||  almondEntry.getValue() > entry.getValue()){
                almondEntry = entry;
            }
        }

        String nameOfProductToAddAloe = aloeEntry.getKey();
        String nameOfProductToAddAlmond = almondEntry.getKey();
        System.out.println(nameOfProductToAddAloe);
        System.out.println(nameOfProductToAddAlmond);
        firstItemRowCheckout = nameOfProductToAddAloe.toLowerCase();
        secondItemRowCheckout = nameOfProductToAddAlmond.toLowerCase();
        priceFirstItemRowCheckout = aloeEntry.getValue().toString().toLowerCase();
        priceSecondItemRowCheckout = almondEntry.getValue().toString().toLowerCase();
        SelenideElement aloeProductSelected = null;
        SelenideElement almondProductSelected = null;
        int indexOfElementMatchedAloe = 0;
        int indexOfElementMatchedAlmond = 0;

        for(int i = 0; i<allProductElements.size(); i++){
            SelenideElement resultElement = compareProducts(allProductElements.get(i),nameOfProductToAddAloe);
            if (resultElement!= null){
                aloeProductSelected = resultElement;
                indexOfElementMatchedAloe = i;
                break;
            }

        }
        for(int i = 0; i<allProductElements.size(); i++){
            SelenideElement resultElement = compareProducts(allProductElements.get(i),nameOfProductToAddAlmond);
            if (resultElement!= null){
                almondProductSelected = resultElement;
                indexOfElementMatchedAlmond=i;
                break;
            }

        }

        try {
            aloeProductSelected.scrollIntoView(true);
            String exactStringOfElementAloe = getTitlesFromProducts(aloeProductSelected);
            SelenideElement interProductAloe = $(By.xpath("//p[@class='font-weight-bold top-space-10'][contains(.,'"+exactStringOfElementAloe+"')]"));
            SelenideElement addButtonAloe = $(By.xpath("(//button[@class='btn btn-primary'][contains(.,'Add')])["+indexOfElementMatchedAloe+"]"));
            interProductAloe.click();
            addButtonAloe.scrollIntoView(true);
            addButtonAloe.shouldBe(visible);
            addButtonAloe.click();

            almondProductSelected.scrollIntoView(true);
            String exactStringOfElementAlmond = getTitlesFromProducts(aloeProductSelected);
            SelenideElement interProductAlmond = $(By.xpath("//p[@class='font-weight-bold top-space-10'][contains(.,'"+exactStringOfElementAlmond+"')]"));
            SelenideElement addButtonAlmond = $(By.xpath("(//button[@class='btn btn-primary'][contains(.,'Add')])["+indexOfElementMatchedAlmond+"]"));
            interProductAlmond.click();
            addButtonAlmond.scrollIntoView(true);
            addButtonAlmond.shouldBe(visible);
            addButtonAlmond.click();

        } catch (Exception error){
            System.out.println(error);
        }
    }
    public boolean checkoutPricesCorrect(SelenideElement element, SelenideElement element2){
        boolean pricesCorrect = false;
        itemsReadyForCheckout.add(element.getText());
        itemsReadyForCheckout.add(element2.getText());

        if (itemsReadyForCheckout.contains(priceFirstItemRowCheckout)){
            if (itemsReadyForCheckout.contains(priceSecondItemRowCheckout)){
                pricesCorrect = true;
            }
        }
        return pricesCorrect;
    }
    public boolean checkoutItemsCorrect(SelenideElement element, SelenideElement element2){
        boolean productsCorrect = false;
        String productNameFirstRow = getTitlesFromProductsCheckout(element).toLowerCase();
        String productNameSecondRow = getTitlesFromProductsCheckout(element2).toLowerCase();
        itemsReadyForCheckout.add(productNameFirstRow);
        itemsReadyForCheckout.add(productNameSecondRow);
        System.out.println(productNameFirstRow);
        System.out.println(firstItemRowCheckout);

        if (itemsReadyForCheckout.contains(firstItemRowCheckout)){
            productsCorrect = true;
        }
        return productsCorrect;
    }
    public void accessCart(){
        if (cartBtn.isDisplayed()){
            try {

                cartBtn.click();
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
