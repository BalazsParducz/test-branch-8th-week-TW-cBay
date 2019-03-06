package com.cbay.test;

import com.cbay.pageFactory.AddToCart;
import com.cbay.pageFactory.Checkout;
import com.cbay.util.RunEnvironment;
import com.cbay.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


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
    public void checkoutWithNoDataGiven() {

    }

}