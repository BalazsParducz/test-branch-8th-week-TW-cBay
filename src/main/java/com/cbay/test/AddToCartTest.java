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
        int numOfItemsAddedInCart = 3;
        addToCart.goToPage("http://localhost:8080/products?style=Sport%20cars");
        int shoppingCartNumberOriginal = addToCart.getShoppingCartNumber();
        addToCart.addItemsToCart();
        int shoppingCartNumberIncreased = addToCart.getShoppingCartNumber();
        assertEquals(shoppingCartNumberOriginal + numOfItemsAddedInCart, shoppingCartNumberIncreased);
    }

    @Test
    public void checkItemsDataInCart_test() {
        addToCart.goToPage("http://localhost:8080/products?style=Sport%20cars");
        String carData = addToCart.getCarData();
        String carPrice = addToCart.getCarPrice();
        addToCart.addSingleItemToCart();
        addToCart.goToPage("http://localhost:8080/shopping-cart");
        String carNameInTableInCart = addToCart.getItemDataFromTableInCart(1);
        String carPriceInTableInCart = addToCart.getItemDataFromTableInCart(5);
        assertTrue(carData.contains(carNameInTableInCart) && carPrice.contains(carPriceInTableInCart));
    }

    @Test
    public void checkQuantityOfItemInCart_test() {
        addItemToCart_test();
        addToCart.goToPage("http://localhost:8080/shopping-cart");
        String carQuantityInTableInCart = addToCart.getItemQuantityFromTableInCart(3);
        assertEquals(Integer.parseInt(carQuantityInTableInCart), 3);
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }
}