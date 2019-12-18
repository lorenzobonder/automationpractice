package tests;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import java.util.Random;
import support.*;
import pages.*;

public class BuyClothingTest {
    private WebDriver webDriver;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        webDriver = Web.createChromeInstance();
    }

    @Test
    public void TestBuyClothing() throws Exception {
        HomePage hp = new HomePage(webDriver);
        Random r = new Random();
        int option = r.nextInt(3);
        try {
            switch(option) {
                case 0: WomenClothingPage wcp = hp.goToWomenClothingPage();
                        ProductPage pp = wcp.openRandomClothingPage();
                        String qty = pp.getQuantity();
                        String sku = pp.getSku();
                        String price = pp.getPrice();
                        String prodName = pp.getProductName();
                        String prodSize = pp.getSelectedSize();
                        CheckoutPage cp = pp.addProductToCartAndCheckout();
                        Assert.assertTrue(cp.validateProductIsInSummary(prodName, price, sku, qty, prodSize));

                case 1: hp.goToDressClothingPage().openRandomDressPage(); break;
                case 2: hp.goToTShirtClothingPage().openRandomTShirtPage(); break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void shutDown() {
        webDriver.quit();
    }
}
