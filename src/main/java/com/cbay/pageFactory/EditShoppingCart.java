package com.cbay.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    WebElement rollsRoyceSilverGhostQuantitySelectBox;

    @FindBy(xpath = "//form[@action='products?style=Sedans']/button[@value=39]")
    WebElement addAstonMartinDB4;

    @FindBy(xpath = "/html/body/div[3]/div[2]/div/div[14]/div/div[2]/div[2]/form/select")
    WebElement astonMartinDB4QuantitySelectBox;

    @FindBy(id = "shopping-cart-number")
    WebElement sizeOfShoppingCartNextToTheShoppingCartButton;

    @FindBy(xpath = "//div[@class='shopping-cart']")
    WebElement shoppingCartButton;

    @FindBy(xpath = "//table/tbody/tr[1]/td[3]/input")
    WebElement amountOfTheFirstItem;

    @FindBy(xpath = "//table/tbody/tr[2]/td[3]/input")
    WebElement amountOfTheSecondItem;

    @FindBy(className = "table-striped")
    WebElement tableInCart;

    @FindBy(xpath = "//table/tbody/tr[2]/td[2]/button")
    WebElement increaseItemByOneBtn;

    @FindBy(xpath = "//table/tbody/tr[2]/td[4]/button")
    WebElement decreaseItemByOneBtn;


    public EditShoppingCart(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String nameOfTheGivenRowItemNameInShippingCart(int row) {
        return driver.findElement(By.xpath("//table/tbody/tr["+ row +"]/td[1]")).getText();
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

    public int getSummaryItemsInShoppingCart() {
        wait.until(ExpectedConditions.visibilityOf(tableInCart));
        int result1 = Integer.parseInt(amountOfTheFirstItem.getAttribute("value"));
        int result2 = Integer.parseInt(amountOfTheSecondItem.getAttribute("value"));
        return result1+result2;
    }

    public int getValueOfInputField() {
        return Integer.parseInt(amountOfTheSecondItem.getAttribute("value"));
    }

    public void increaseItemQuantity(int increaseWith) {
        for (int i = 0; i < increaseWith; i++) {
            increaseItemByOneBtn.click();
        }
    }

    public void decreaseItemQuantity(int increaseWith) {
        for (int i = 0; i < increaseWith; i++) {
            decreaseItemByOneBtn.click();
        }
    }

    public void fillInputField(String userInput){
        amountOfTheFirstItem.sendKeys(userInput);
    }



    public void openShoppingCart() {
        shoppingCartButton.click();
    }

    private void selectFromSelectList(String quantity, WebElement element) {
        element.sendKeys(quantity);
    }


}
