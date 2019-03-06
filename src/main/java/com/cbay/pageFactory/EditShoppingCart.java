package com.cbay.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EditShoppingCart {

    WebDriver driver;
    WebDriverWait wait;


    @FindBy(xpath = "//*[@class='col-lg-3']/a[@href='/products?style=Sedans']")
    WebElement categoriesSedan;

    @FindBy(xpath = "//form[@action='products?style=Sedans']/button[@value=30]")
    WebElement addRollsRoyceSilverGhost;

    @FindBy(xpath = "/html/body/div[3]/div[2]/div/div[5]/div/div[2]/div[2]/form/select")
    List<WebElement> rollsRoyceSilverGhostQuantitySelectBox;

    @FindBy(xpath = "//form[@action='products?style=Sedans']/button[@value=39]")
    WebElement addAstonMartinDB4;

    @FindBy(xpath = "/html/body/div[3]/div[2]/div/div[14]/div/div[2]/div[2]/form/select")
    List<WebElement> astonMartinDB4QuantitySelectBox;

    @FindBy(id = "shopping-cart-number")
    WebElement sizeOfShoppingCartNextToTheShoppingCartButton;

    @FindBy(xpath = "//div[@class='shopping-cart']")
    WebElement shoppingCartButton;


    public EditShoppingCart(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void addRollsRoyceSilverGhostToShoppingCart(String quantity) {
        categoriesSedan.click();
        selectFromSelectList(quantity, rollsRoyceSilverGhostQuantitySelectBox);
        addRollsRoyceSilverGhost.click();
    }

    public void addAstonMartinDB4ToShoppingCart(String quantity) {
        selectFromSelectList(quantity, astonMartinDB4QuantitySelectBox);
        addAstonMartinDB4.click();
    }

    public void openShoppingCart() {
        shoppingCartButton.click();
    }

    private void selectFromSelectList(String amount, List<WebElement> elements) {
        for (WebElement element : elements) {
            element.sendKeys(amount);
        }
    }


}
