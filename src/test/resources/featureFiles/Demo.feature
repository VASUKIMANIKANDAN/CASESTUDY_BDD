Feature: Product Store Page Test

Scenario: Login into App
Given User into the login page
When User enters login credientials
Then Should Display Home Page

Scenario Outline: Add items to cart
		When  Add an item to cart "<item>"
		Then  Items must be added to cart
		Examples:
		|item|
		|Nokia lumia 1520|
                |Iphone 6 32gb|
	
Scenario: Delete an Item
When List of items should be available in Cart
Then Delete an item from cart

Scenario: Place Order
When Items Should be available in Cart
Then Place order
And Purchase Items
