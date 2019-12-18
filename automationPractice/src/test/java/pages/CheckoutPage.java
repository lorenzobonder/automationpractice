package pages;

import org.apache.commons.exec.util.StringUtils;
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

    private WebElement deliveryAddressItemBox() {
        WebElement box = driver.findElement(By.id("address_delivery"));
        waitPage.until((ExpectedConditions.elementToBeClickable(box)));
        return box;
    }

    private WebElement invoiceAddressItemBox() {
        WebElement box = driver.findElement(By.id("address_invoice"));
        waitPage.until((ExpectedConditions.elementToBeClickable(box)));
        return box;
    }

    private WebElement goToStep4Button() {
        WebElement btn = driver.findElement(By.name("processAddress"));
        waitPage.until((ExpectedConditions.elementToBeClickable(btn)));
        return btn;
    }

    private WebElement tosCheckbox() {
        WebElement box = driver.findElement(By.id("cgv"));
        return box;
    }

    private WebElement goToStep5Button() {
        WebElement btn = driver.findElement(By.name("processCarrier"));
        waitPage.until((ExpectedConditions.elementToBeClickable(btn)));
        return btn;
    }

    private WebElement priceTotal() {
        WebElement field = driver.findElement(By.id("total_price"));
        waitPage.until((ExpectedConditions.elementToBeClickable(field)));
        return field;
    }

    private WebElement shippingTotal() {
        WebElement field = driver.findElement(By.id("total_shipping"));
        waitPage.until((ExpectedConditions.elementToBeClickable(field)));
        return field;
    }

    private WebElement productTotal() {
        WebElement field = driver.findElement(By.id("total_product"));
        waitPage.until((ExpectedConditions.elementToBeClickable(field)));
        return field;
    }

    private WebElement bankWireOptionButton() {
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a"));
        waitPage.until((ExpectedConditions.elementToBeClickable(btn)));
        return btn;
    }

    private WebElement checkOptionButton() {
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a"));
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

            if(itemProdName.contains(name)) {
                foundProdName = true;
                // go to SKU
                WebElement itemSkuEl = item.findElement(By.cssSelector("td.cart_description > small.cart_ref"));
                String itemSkuName = itemSkuEl.getText();
                if(itemSkuName.contains((sku))) {
                    foundSku = true;
                    //go to size
                    WebElement sizeEl = item.findElement(By.cssSelector("td.cart_description > small:nth-child(3) > a"));
                    String sizeText = sizeEl.getText();
                    sizeText = sizeText.substring(sizeText.lastIndexOf(":")+1);
                    if(sizeText.contains(size)) {
                        foundSize = true;
                        //go to price
                        WebElement priceEl = item.findElement(By.cssSelector("td.cart_unit > span > span"));
                        String priceText = priceEl.getText();
                        if(priceText.contains(price)) {
                            foundPrice = true;
                            //go to quantity
                            WebElement qtyEl = item.findElement(By.cssSelector("td.cart_quantity.text-center > input.cart_quantity_input.form-control.grey"));
                            String qtyText = qtyEl.getAttribute("value");
                            if(qtyText.contains(quantity)) {
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
        Thread.sleep(3000);

        waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#noSlide > h1")));
        String pageTitle = driver.findElement(By.cssSelector("#noSlide > h1")).getText();
        if(pageTitle.contains("CREATE AN ACCOUNT")) {
            return new CreateAUserPage(driver);
        }
        else {
            throw new Exception("Page not existent or wrong page opened");
        }
    }

    public Boolean validateDeliveryAddressDetails(String addressFstName, String addressLstName, String line1, String line2, String city,
                                                  String zip, String state, String country, String mobilePhone) {
        List<WebElement> sumList = getProductSummaryList();
        Boolean foundAddressUserName = false;
        Boolean foundAddressLine = false;
        Boolean foundAddressLocationInfo = false;
        Boolean foundPhone = false;

        WebElement deliveryItemBox = deliveryAddressItemBox();
        //Address User Name
        WebElement itemAddressUserName = deliveryItemBox.findElement(By.cssSelector("#address_delivery > li.address_firstname.address_lastname"));
        String itemUserName = itemAddressUserName.getText();

        if(itemUserName.contains(addressFstName) && itemUserName.contains(addressLstName)) {
            foundAddressUserName = true;
        }

        // go to Address Lines
        WebElement itemAddressLinesEl = deliveryItemBox.findElement(By.cssSelector("#address_delivery > li.address_address1.address_address2"));
        String itemAddress = itemAddressLinesEl.getText();
        if(line2.trim().length() > 0 || line2 != null) {
            if(itemAddress.contains((line1)) && itemAddress.contains(line2)) {
                    foundAddressLine = true;
                }
        }
        else {
            if(itemAddress.contains((line1))) {
                foundAddressLine = true;
            }
        }

        //go to Location Info (state, country, city, zip code)
        WebElement cityStateZipCodeEl = deliveryItemBox.findElement(By.cssSelector("#address_delivery > li.address_city.address_state_name.address_postcode"));
        String cityStateZipCodeTxt = cityStateZipCodeEl.getText();

        WebElement countryEl = deliveryItemBox.findElement(By.cssSelector("#address_delivery > li.address_country_name"));
        String countryTxt = countryEl.getText();
        if(cityStateZipCodeTxt.contains(city) && cityStateZipCodeTxt.contains(state) && cityStateZipCodeTxt.contains(zip) && countryTxt.contains(country)) {
            foundAddressLocationInfo = true;
        }

        //go to Phone
        WebElement phoneEl = deliveryItemBox.findElement(By.cssSelector("#address_delivery > li.address_phone_mobile"));
        String phoneText = phoneEl.getText();
        if(phoneText.contains(mobilePhone)) {
            foundPhone = true;
        }

        if(foundAddressUserName && foundAddressLine && foundAddressLocationInfo && foundPhone) {
            return true;
        }
        else {
            return false;
        }
    }

    public void confirmAddressAndGoToStep4() throws InterruptedException {
        goToStep4Button().click();
        Thread.sleep(3000);

        waitPage.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#carrier_area > h1"),"SHIPPING"));
    }

    public void confirmAddressAndGoToStep5() throws InterruptedException {
        tosCheckbox().click();
        goToStep5Button().click();
        Thread.sleep(3000);

        waitPage.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#center_column > h1"),"PLEASE CHOOSE YOUR PAYMENT METHOD"));
        waitPage.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")));
        waitPage.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")));
    }

    public Boolean validateFinalPriceIsOK() {
        List<WebElement> sumList = getProductSummaryList();
        Double sumProducts = 0.0;
        Double productValue = 0.0;
        Double prodTotal = 0.0;
        Double totalPrice = 0.0;
        Double shipping = 0.0;

        String prodTot = productTotal().getText().substring(1);
        prodTotal = Double.parseDouble(prodTot);
        String ship = shippingTotal().getText().substring(1);
        shipping = Double.parseDouble(ship);
        String total = priceTotal().getText().substring(1);
        totalPrice = Double.parseDouble(total);

        for(WebElement item: sumList) {
            int prodQtd = Integer.parseInt(driver.findElement(By.cssSelector("td.cart_quantity.text-center > span")).getText());
            String prodValue = driver.findElement(By.cssSelector("td.cart_unit > span")).getText();
            productValue = Double.parseDouble(prodValue.substring(1));
            sumProducts += (productValue * prodQtd);
        }

        Double sumTotalAndShipping = prodTotal + shipping;
        if(Double.compare(sumProducts, prodTotal) == 0 && Double.compare(sumTotalAndShipping, totalPrice) == 0) {
            return true;
        }

        return false;
    }

    public Boolean selectBankWireAndConfirmOrder() {
        try {
            bankWireOptionButton().click();
            Thread.sleep(5000);

            waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.id("module-bankwire-payment")));
            waitPage.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cart_navigation\"]/button")));

            WebElement confirmButton = driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button"));
            confirmButton.click();
            Thread.sleep(3000);

            waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.id("order-confirmation")));
            waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#center_column > div > p > strong")));
            waitPage.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#center_column > div > p > strong"), "Your order on My Store is complete."));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

            return false;
    }

    public Boolean selectCheckAndConfirmOrder() {
        try {
            checkOptionButton().click();
            Thread.sleep(5000);

            waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.id("module-cheque-payment")));
            waitPage.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cart_navigation\"]/button")));

            WebElement confirmButton = driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button"));
            confirmButton.click();
            Thread.sleep(3000);

            waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.id("order-confirmation")));
            waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#center_column > p.alert.alert-success")));
            waitPage.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#center_column > p.alert.alert-success"), "Your order on My Store is complete."));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
