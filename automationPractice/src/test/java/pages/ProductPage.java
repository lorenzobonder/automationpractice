package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait waitPage;

    public ProductPage (WebDriver driver)  {
        this.driver = driver;
        waitPage = new WebDriverWait(driver, 10);
    }

    private WebElement productImage() {
        WebElement img = driver.findElement(By.id("bigpic"));
        waitPage.until((ExpectedConditions.visibilityOfAllElements(img)));
        return img;
    }

    private WebElement productTitle() {
        WebElement title = driver.findElement(By.cssSelector("#center_column > div > div > div.pb-center-column.col-xs-12.col-sm-4 > h1"));
        waitPage.until((ExpectedConditions.visibilityOfAllElements(title)));
        return title;
    }

    private WebElement productSku() {
        WebElement sku = driver.findElement(By.cssSelector("#product_reference > span"));
        waitPage.until((ExpectedConditions.visibilityOfAllElements(sku)));
        return sku;
    }

    private WebElement productCondition() {
        WebElement condition = driver.findElement(By.cssSelector("#product_condition > span"));
        waitPage.until((ExpectedConditions.visibilityOfAllElements(condition)));
        return condition;
    }

    private WebElement quantity() {
        WebElement qty = driver.findElement(By.id("quantity-wanted"));
        waitPage.until((ExpectedConditions.elementToBeClickable(qty)));
        return qty;
    }

    private Select productSize() {
        Select sizeSelect = new Select (driver.findElement(By.id("group_1")));
        waitPage.until(ExpectedConditions.elementToBeClickable(By.id("group_1")));

        return sizeSelect;
    }

    private WebElement addToCartButton() {
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button"));
        waitPage.until((ExpectedConditions.elementToBeClickable(btn)));
        return btn;
    }

    private WebElement productPrice() {
        WebElement price = driver.findElement(By.id("our_price_display"));
        waitPage.until((ExpectedConditions.visibilityOf(price)));
        return price;
    }

    public CheckoutPage addProductToCartAndCheckout() {
        WebElement cartBtn = addToCartButton();
        cartBtn.click();

        waitPage.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_product.col-xs-12.col-md-6 > h2"),
                                                                                        "Product successfully added to your shopping cart"));

        WebElement proceedCheckout = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
        waitPage.until(ExpectedConditions.elementToBeClickable(proceedCheckout));
        proceedCheckout.click();

        waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_title")));
        return new CheckoutPage(driver);
    }

    public String getProductName() {
        WebElement prodNameWbEl = productTitle();
        return prodNameWbEl.getText();
    }

    public String getSku() {
        WebElement skuWbEl = productSku();
        return skuWbEl.getText();
    }

    public String getPrice() {
        WebElement priceWebEl = productPrice();
        return priceWebEl.getText();
    }

    public String getQuantity() {
        WebElement qtyEl = quantity();
        return qtyEl.getText();
    }

    public String getSelectedSize() {
        Select sizeSelect = productSize();
        return sizeSelect.getFirstSelectedOption().getText();
    }
}
