package com.cbay.test;

import com.cbay.pageFactory.AddToCart;
import com.cbay.util.RunEnvironment;
import com.cbay.util.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.*;

class AddToCartTest {

    WebDriver driver;
    AddToCart addToCart;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        addToCart = new AddToCart(driver);
    }

    @Test
    public void addItemToCart_test() {
        int itemsAddedInCart = 3;
        addToCart.goToPage("http://localhost:8080/products?style=Sport%20cars");
        int shoppingCartNumberOriginal = addToCart.getShoppingCartNumber();
        addToCart.addItemToCart();
        int shoppingCartNumberIncreased = addToCart.getShoppingCartNumber();
        assertEquals(shoppingCartNumberOriginal + itemsAddedInCart, shoppingCartNumberIncreased);
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }
}