package com.cbay.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {

    WebDriver driver;
    WebDriverWait wait;
    private static final int TIMEOUT = 7;
    private static final int POLLING = 100;

    @FindBy(className = "quantity")
    WebElement quantityDropDown;

    @FindBy(css = "option[value='3']")
    WebElement quantitiyValue;

    @FindBy(className = "card-btn")
    WebElement addToCartButton;

    @FindBy(id = "shopping-cart-number")
    WebElement shoppingCartNumber;

    public AddToCart(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(driver, this);
    }

    public void goToPage(String URL) {
        driver.get(URL);
    }

    public int getShoppingCartNumber() {
        if (shoppingCartNumber.isDisplayed()){
            wait.until(ExpectedConditions.visibilityOf(shoppingCartNumber));
            return Integer.parseInt(shoppingCartNumber.getText());
        }
        return 0;
    }

    public void addItemToCart() {
        wait.until(ExpectedConditions.visibilityOf(quantityDropDown));
        quantityDropDown.click();
        wait.until(ExpectedConditions.visibilityOf(quantitiyValue));
        quantitiyValue.click();
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }


}
