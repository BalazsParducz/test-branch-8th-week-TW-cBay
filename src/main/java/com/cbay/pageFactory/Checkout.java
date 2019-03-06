package com.cbay.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {

    WebDriver driver;
    WebDriverWait wait;
    private static final int TIMEOUT = 7;
    private static final int POLLING = 100;

    String shoppingCartURL = "http://192.168.160.231:8080/shopping-cart";

    public Checkout (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(driver, this);
    }

    public void goToPage(String URL) {
        driver.get(URL);
    }
}
