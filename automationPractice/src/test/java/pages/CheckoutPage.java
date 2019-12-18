package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait waitPage;

    public CheckoutPage (WebDriver driver)  {
        this.driver = driver;
        waitPage = new WebDriverWait(driver, 10);
    }

    private List<WebElement> getProductSummaryList() {
        List<WebElement> list = driver.findElements(By.cssSelector("#cart_summary > tbody > tr"));

        return list;
    }

    public int getListSize() {
        List<WebElement> list = getProductSummaryList();
        return list.size();
    }

    private WebElement proceedToStep2Button() {
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));
        waitPage.until((ExpectedConditions.elementToBeClickable(btn)));
        return btn;
    }

    private WebElement addUserEmailTextField() {
        WebElement addEmail = driver.findElement(By.id("email_create"));
        waitPage.until((ExpectedConditions.elementToBeClickable(addEmail)));
        return addEmail;
    }

    private WebElement userEmailTextField() {
        WebElement userEmail = driver.findElement(By.id("email"));
        waitPage.until((ExpectedConditions.elementToBeClickable(userEmail)));
        return userEmail;
    }

    private WebElement userPwTextField() {
        WebElement userPw = driver.findElement(By.id("passwd"));
        waitPage.until((ExpectedConditions.elementToBeClickable(userPw)));
        return userPw;
    }

    private WebElement goToCreateAUserButton() {
        WebElement btn = driver.findElement(By.id("SubmitCreate"));
        waitPage.until((ExpectedConditions.elementToBeClickable(btn)));
        return btn;
    }

    private WebElement goToSignInButton() {
        WebElement btn = driver.findElement(By.id("SubmitLogin"));
        waitPage.until((ExpectedConditions.elementToBeClickable(btn)));
        return btn;
    }

    public Boolean validateProductIsInSummary(String name, String price, String sku, String quantity, String size) {
        List<WebElement> sumList = getProductSummaryList();
        Boolean foundProdName = false;
        Boolean foundPrice = false;
        Boolean foundSku = false;
        Boolean foundQuantity = false;
        Boolean foundSize = false;
        for(WebElement item: sumList) {
            //Prod Name
            WebElement itemProdNameEl = item.findElement(By.cssSelector("td.cart_description > p > a"));
            String itemProdName = itemProdNameEl.getText();

            if(name.contains(itemProdName)) {
                foundProdName = true;
                // go to SKU
                WebElement itemSkuEl = item.findElement(By.cssSelector("td.cart_description > small.cart_ref"));
                String itemSkuName = itemSkuEl.getText();
                if(sku.contains((itemSkuName))) {
                    foundSku = true;
                    //go to size
                    WebElement sizeEl = item.findElement(By.cssSelector("td.cart_description > small:nth-child(3) > a"));
                    String sizeText = sizeEl.getText();
                    sizeText = sizeText.substring(sizeText.lastIndexOf(":"+1));
                    if(size.contains(sizeText)) {
                        foundSize = true;
                        //go to price
                        WebElement priceEl = item.findElement(By.cssSelector("span[class='price]"));
                        String priceText = priceEl.getText();
                        if(price.contains(priceText)) {
                            foundPrice = true;
                            //go to quantity
                            WebElement qtyEl = item.findElement(By.cssSelector("td.cart_quantity.text-center > input.cart_quantity_input.form-control.grey"));
                            String qtyText = qtyEl.getAttribute("value");
                            if(quantity.contains(qtyText)) {
                                foundQuantity = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(foundPrice && foundProdName && foundSize && foundSku && foundQuantity) {
            return true;
        }
        else {
            return false;
        }
    }

    public void goToStep2() {
        WebElement step2Btn = proceedToStep2Button();
        step2Btn.click();

        waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.id("authentication")));
    }

    public CreateAUserPage proceedToCreateAUserPage(String inputEmail) throws Exception {
        WebElement newUserEmail = addUserEmailTextField();
        newUserEmail.sendKeys(inputEmail);
        Thread.sleep(1000);

        WebElement openCreateUserBtn = goToCreateAUserButton();
        openCreateUserBtn.click();

        waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#noSlide > h1")));
        String pageTitle = driver.findElement(By.cssSelector("#noSlide > h1")).getText();
        if(pageTitle.contains("Create an account")) {
            return new CreateAUserPage(driver);
        }
        else {
            throw new Exception("Page not existent or wrong page opened");
        }
    }
}
