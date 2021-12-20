#Test Case Automated

###Scenario
Successful purchase from user

###Sub-Scenario
Depending on weather, user buys moisturizer or sunscreen

###Test Steps
1. The user access the app homepage by opening https://weathershopper.pythonanywhere.com/
2. Depending on the degrees displayed in the homepage, the user will follow two different workflows which are:
   1. If it's more than 34 degrees, it will click on the Buy sunscreens button.
   2. If it's less than 19 degrees, the user will click on the Buy moisturizers button.
3. Once in the desired page according to the previous step, the user will add to the cart the least expensive option that matches the following:
   1. For sunscreens: lowest priced SPF-30 and another one with SPF-50 factor.
   2. For moisturizers: lowest priced moisturizer that contains aloe and another ones that contains almond.
4. Once the items are added to the cart, the user will click on the Cart button.
5. On the checkout page, the user will determine if the items are correct. If they are correct, it will click on the Pay with card button.
6. The user will add the information to the form requesting the credit details and click on Pay.

###Expected Results
1. The user will be able to access both the Sunscreens and Moisturizers pages depending on the weather. 
2. The user will be able to add items to the cart and see it reflecting in the cart numbers. 
3. On the checkout page, the correct items are displayed according to the user selections. 
4. The user can submit the form correctly with a valid credit card. 