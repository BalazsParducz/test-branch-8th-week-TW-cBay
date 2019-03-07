package com.cbay.test;

import com.cbay.pageFactory.AddToCart;
import com.cbay.pageFactory.Checkout;
import com.cbay.util.RunEnvironment;
import com.cbay.util.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckoutTest {

    WebDriver driver;
    AddToCart addToCart;
    Checkout checkout;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        addToCart = new AddToCart(driver);
        checkout = new Checkout(driver);
        checkout.goToPage("http://192.168.160.231:8080/products?style=Sport%20cars");
        addToCart.addSingleItemToCart();
        checkout.goToPage("http://192.168.160.231:8080/shopping-cart");
        checkout.clickOnCheckoutButton();
    }

    @Test
    public void tryToPayWithNoCheckoutDataGiven_test() {
        assertThrows(Exception.class, () -> checkout.clickOnProceedToPaymentButton());
    }

    // TODO: Checkout modal needs validation from the dev side!
    @Test
    public void tryToPayWithNotEnoughCheckoutDataGiven_test() {
        checkout.enterFirstAndLastNameForPayment();
        assertThrows(Exception.class, () -> checkout.clickOnProceedToPaymentButton());
    }

    // TODO: Checkout modal needs validation from the dev side!
    @Test
    public void invalidInputInZipCodeFields_test() {
        checkout.enterZipCodeForPayment("wrong input");
        assertThrows(Exception.class, () -> checkout.clickOnProceedToPaymentButton());
    }

    // TODO: Checkout modal needs validation from the dev side!
    @Test
    public void validInputInZipCodeFields_test() {
        checkout.enterZipCodeForPayment("1111");
        assertDoesNotThrow(() -> checkout.clickOnProceedToPaymentButton());
    }

    // TODO: Checkout modal needs validation from the dev side!
    @Test
    public void invalidInputInPhoneNumberField_test() {
        checkout.enterPhoneNumberForPayment("wrong input");
        assertThrows(Exception.class, () -> checkout.clickOnProceedToPaymentButton());
    }

    // TODO: Checkout modal needs validation from the dev side!
    @Test
    public void validInputInPhoneNumberField_test() {
        checkout.enterPhoneNumberForPayment("+36123456789");
        assertDoesNotThrow(() -> checkout.clickOnProceedToPaymentButton());
    }

/*    Todo:
    - check fields where no nums are allowed

*/

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }

}