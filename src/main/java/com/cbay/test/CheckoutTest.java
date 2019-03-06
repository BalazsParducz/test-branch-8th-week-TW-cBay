package com.cbay.test;

import com.cbay.pageFactory.AddToCart;
import com.cbay.pageFactory.Checkout;
import com.cbay.util.RunEnvironment;
import com.cbay.util.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckoutTest {

    WebDriver driver;
    AddToCart addToCart;
    Checkout checkout;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        addToCart = new AddToCart(driver);
        checkout = new Checkout(driver);
    }

    @Test
    public void tryToPayWithNoCheckoutDataGiven_test() {
        checkout.goToPage("http://localhost:8080/products?style=Sport%20cars");
        addToCart.addSingleItemToCart();
        checkout.goToPage("http://localhost:8080/shopping-cart");
        checkout.clickOnCheckoutButton();
        assertThrows(Exception.class, () -> checkout.clickOnProceedToPaymentButton());
    }

    @Test
    public void tryToPayWithNotEnoughCheckoutDataGiven_test() {
        checkout.goToPage("http://localhost:8080/products?style=Sport%20cars");
        addToCart.addSingleItemToCart();
        checkout.goToPage("http://localhost:8080/shopping-cart");
        checkout.clickOnCheckoutButton();
        checkout.enterFirstAndLastNameForPayment();
        assertThrows(Exception.class, () -> checkout.clickOnProceedToPaymentButton());
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }

}