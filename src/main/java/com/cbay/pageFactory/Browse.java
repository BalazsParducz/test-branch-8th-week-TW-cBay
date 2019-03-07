package com.cbay.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Browse {

    WebDriver driver;
    WebDriverWait wait;

    private static final int TIMEOUT = 7;
    private static final int POLLING = 100;

    public Browse(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "card-title")
    List<WebElement> categories;

    @FindBy(tagName = "strong")
    WebElement categoryTag;

    @FindBy(className = "card")
    List<WebElement> carsUnderCategory;


    public ArrayList<String> getCategories() {
        final ArrayList<String> categoriesAsText = new ArrayList<>();
        for (WebElement category : categories) {
            categoriesAsText.add(category.getText());
        }
        return categoriesAsText;
    }

    public void clickOnCategory(String cat) {
        for (WebElement category : categories) {
            if(category.getText().equals(cat)) {
                category.click();
                wait.until(ExpectedConditions.visibilityOf(categoryTag));
            }
        }
    }

    public List<WebElement> getCarsUnderCategory() {
        return carsUnderCategory;
    }
}
