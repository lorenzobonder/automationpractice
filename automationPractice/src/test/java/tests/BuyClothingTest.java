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
        try {
            WomenClothingPage wcp = hp.goToWomenClothingPage();
            ProductPage pp = wcp.openRandomClothingPage();
            String screenshotArquivo = "C:\\Users\\Lorenzo\\automation_output" + Generators.dateTimeToFile() + test.getMethodName() + ".png";
            Screenshot.takeScreenshot(webDriver, screenshotArquivo);

            //Product Info
            String qty = pp.getQuantity();
            String sku = pp.getSku();
            String price = pp.getPrice();
            String prodName = pp.getProductName();
            String prodSize = pp.getSelectedSize();
            CheckoutPage cp = pp.addProductToCartAndCheckout();
            Assert.assertTrue(cp.validateProductIsInSummary(prodName, price, sku, qty, prodSize));
            screenshotArquivo = "C:\\Users\\Lorenzo\\automation_output" + Generators.dateTimeToFile() + test.getMethodName() + ".png";
            Screenshot.takeScreenshot(webDriver, screenshotArquivo);

            //If OK, proceed to create a user
            cp.goToStep2();

            String email = "loggers" + r.nextInt(60000) + "@loggers.com";
            CreateAUserPage caup = cp.proceedToCreateAUserPage(email);

            //User and Address Info
            String firstName = "Logger";
            String lastName = "Loggers";
            String passwd = "loggs";
            String addrLine1 = "Loggers St " + r.nextInt(999);
            String city = "Orlando";
            String state = "Florida";
            String zipCode = "99222";
            String country = "United States";
            String mobile = "99932333";
            caup.setCustomerFirstName(firstName);
            caup.setCustomerLastName(lastName);
            caup.setCustomerPassword(passwd);
            caup.setAddressLineOne(addrLine1);
            caup.setAddressCity(city);
            caup.setAddressState(state);
            caup.setAddressZipCode(zipCode);
            caup.setAddressMobilePhone(mobile);
            screenshotArquivo = "C:\\Users\\Lorenzo\\automation_output" + Generators.dateTimeToFile() + test.getMethodName() + ".png";
            Screenshot.takeScreenshot(webDriver, screenshotArquivo);
            cp = caup.registerAndContinueCheckout();

            //Validate Address checks out + Shipping (ToS included) and go to Payment Options
            Assert.assertTrue(cp.validateDeliveryAddressDetails(firstName, lastName, addrLine1, "", city, zipCode, state, country, mobile));
            cp.confirmAddressAndGoToStep4();
            screenshotArquivo = "C:\\Users\\Lorenzo\\automation_output" + Generators.dateTimeToFile() + test.getMethodName() + ".png";
            Screenshot.takeScreenshot(webDriver, screenshotArquivo);
            cp.confirmAddressAndGoToStep5();
            screenshotArquivo = "C:\\Users\\Lorenzo\\automation_output" + Generators.dateTimeToFile() + test.getMethodName() + ".png";
            Screenshot.takeScreenshot(webDriver, screenshotArquivo);

            //Validate Price is correct + select payment option and confirm
            Assert.assertTrue(cp.validateFinalPriceIsOK());
            int option = r.nextInt(2);
            if(option == 1) {
                Assert.assertTrue(cp.selectCheckAndConfirmOrder());
                screenshotArquivo = "C:\\Users\\Lorenzo\\automation_output" + Generators.dateTimeToFile() + test.getMethodName() + ".png";
                Screenshot.takeScreenshot(webDriver, screenshotArquivo);
            }
            else {
                Assert.assertTrue(cp.selectBankWireAndConfirmOrder());
                screenshotArquivo = "C:\\Users\\Lorenzo\\automation_output" + Generators.dateTimeToFile() + test.getMethodName() + ".png";
                Screenshot.takeScreenshot(webDriver, screenshotArquivo);
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
