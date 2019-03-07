package com.cbay.test;

import com.cbay.pageFactory.AddToCart;
import com.cbay.pageFactory.EditShoppingCart;
import com.cbay.util.RunEnvironment;
import com.cbay.util.Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

public class EditShoppingCartTest {

    WebDriver driver;
    EditShoppingCart editShoppingCart;
    AddToCart addToCart;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        editShoppingCart = new EditShoppingCart(driver);
        addToCart = new AddToCart(driver);
        driver.navigate().to("http://localhost:8080");
    }

    //@Disabled
    @DisplayName("Test ShoppingCart size")
    @Test
    public void shoppingCartSizeEqualSum() {
        int shoppingCartSize;
        int summaryItems;

        // Fill Shopping Cart with some item for tests
        addToCart.goToPage("http://localhost:8080/products?style=Sedans");
        addToCart.addItemsToCart();
        addToCart.goToPage("http://localhost:8080/products?style=Sport%20cars");
        addToCart.addSingleItemToCart();

        //Get result for assertion
        shoppingCartSize = addToCart.getShoppingCartNumber();
        editShoppingCart.openShoppingCart();
        summaryItems = editShoppingCart.getSummaryItemsInShoppingCart();

        //Assert functions work right
        Assertions.assertEquals(shoppingCartSize, summaryItems, "Number next to the ShoppingCart icon is equal with summary items in the ShoppingCart ");
    }

    //@Disabled
    @DisplayName("Increase and decrease itemm in the shopping cart")
    @Test
    public void changeItemsQuantityWithButtons() {
        int actualResult;
        // Fill Shopping Cart with some item for tests
        addToCart.goToPage("http://localhost:8080/products?style=Sedans");
        addToCart.addItemsToCart();
        addToCart.goToPage("http://localhost:8080/products?style=Sport%20cars");
        addToCart.addSingleItemToCart();
        editShoppingCart.openShoppingCart();

        //Test increase btn
        editShoppingCart.increaseItemQuantity(5);
        actualResult = editShoppingCart.getValueOfInputField();
        Assertions.assertEquals(6, actualResult, "Increase button increase the value");

        /* Test input after refresh
        driver.navigate().refresh();
        actualResult = editShoppingCart.getValueOfInputField();
        Assertions.assertEquals(6, actualResult, "After refresh shopping cart save changes");
        */

        editShoppingCart.decreaseItemQuantity(3);
        actualResult = editShoppingCart.getValueOfInputField();
        Assertions.assertEquals(3, actualResult, "Decrease button decrease the value");

        /* Test input after refresh
        driver.navigate().refresh();
        actualResult = editShoppingCart.getValueOfInputField();
        Assertions.assertEquals(3, actualResult, "After refresh shopping cart save changes");
        */
    }

    //@Disabled
    @DisplayName("Fill input with wrong inputs")
    @Test
    public void fillInputWithWrongDetails(){

        //Fill shopping cart
        addToCart.goToPage("http://localhost:8080/products?style=Sedans");
        addToCart.addItemsToCart();

        //Open and edit input field
        editShoppingCart.openShoppingCart();
        editShoppingCart.fillInputField("Test data");


        //TODO Assertions afer dev team finished the implementation
    }

    //@Disabled
    @DisplayName("Put Rolls Royce Silver Ghost and Aston Martin DB4 to shopping cart")
    @CsvFileSource(resources = "/carnames.csv")
    @ParameterizedTest
    public void addItemsToShoppingCart(String firstRowName, String secondRowName) {
        editShoppingCart.addRollsRoyceSilverGhostToShoppingCart("2");
        editShoppingCart.addAstonMartinDB4ToShoppingCart("3");
        editShoppingCart.openShoppingCart();
        Assertions.assertEquals(firstRowName, editShoppingCart.nameOfTheGivenRowItemNameInShippingCart(1));
        Assertions.assertEquals(secondRowName, editShoppingCart.nameOfTheGivenRowItemNameInShippingCart(2));
    }


    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }

}
