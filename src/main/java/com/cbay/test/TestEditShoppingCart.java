package com.cbay.test;

import com.cbay.pageFactory.EditShoppingCart;
import com.cbay.util.RunEnvironment;
import com.cbay.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class TestEditShoppingCart {

    WebDriver driver;
    EditShoppingCart editShoppingCart;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        editShoppingCart = new EditShoppingCart(driver);
    }

    @Test
    public void addItemsToShoppingCart() {
        driver.navigate().to("http://localhost:8080");

        editShoppingCart.addRollsRoyceSilverGhostToShoppingCart("2");
        editShoppingCart.addAstonMartinDB4ToShoppingCart("3");

        editShoppingCart.openShoppingCart();
        //Assert successful navigation to Shopping Cart page
    }

}
