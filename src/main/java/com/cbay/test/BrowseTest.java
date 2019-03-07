package com.cbay.test;

import com.cbay.pageFactory.Browse;
import com.cbay.util.RunEnvironment;
import com.cbay.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseTest {

    WebDriver driver;
    WebDriverWait wait;
    Browse browse;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        browse = new Browse(driver);
        driver.get("http://localhost:8080");
    }
    @Disabled
    @Test
    public void confirmCategories () {
        ArrayList<String> cats = browse.getCategories();
        System.out.println(cats.size());
        assertTrue(cats.size() > 0);
    }
    @Disabled
    @Test
    public void categoryExists() {
        assertTrue(browse.getCategories().contains("Sedans"));
    }
    @Disabled
    @Test
    public void pickCategory() {
        String pickedCategory = "Convertibles";
        try {
            browse.clickOnCategory(pickedCategory);
        } catch(org.openqa.selenium.StaleElementReferenceException ex) {
            ex.getSupportUrl();
        } finally {
            String url = driver.getCurrentUrl();
            System.out.println(url);
            assertEquals("http://localhost:8080/products?style=" + pickedCategory, url);
        }
    }

    @Test
    public void carsUnderCategory() {
        String pickedCategory = "Hatchbacks";
        driver.get("http://localhost:8080/products?style=" + pickedCategory);
        assertTrue(browse.getCarsUnderCategory().get(0).isDisplayed());
    }
}
