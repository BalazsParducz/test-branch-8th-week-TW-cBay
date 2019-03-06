package com.cbay.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {

    WebDriver driver;
    WebDriverWait wait;
    private static final int TIMEOUT = 7;
    private static final int POLLING = 100;

    @FindBy(className = "btn-primary")
    WebElement chekoutButton;

    @FindBy(id = "proceed")
    WebElement proceedToPayment;

    public Checkout (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(driver, this);
    }

    public void goToPage(String URL) {
        driver.get(URL);
    }

    public void clickOnCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(chekoutButton));
        chekoutButton.click();
    }

    public void clickOnProceedToPaymentButton() {
        try {
            wait.until(ExpectedConditions.visibilityOf(proceedToPayment));
            proceedToPayment.click();
        } catch (Exception e) {
            e.getMessage();
        }

    }


}
