package com.cbay.test;

import com.cbay.pageFactory.Browse;
import com.cbay.util.RunEnvironment;
import com.cbay.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseTest {

    WebDriver driver;
    Browse browse;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        browse = new Browse(driver);
        driver.get("http://localhost:8080");
    }

    @Test
    public void confirmCategories () {
        ArrayList<String> cats = browse.getCategories();
        System.out.println(cats.size());
        assertTrue(cats!=null);
    }

    @Test
    public void categoryExists() {
        assertTrue(browse.getCategories().contains("Sedans"));
    }
}
