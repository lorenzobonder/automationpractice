package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;

public class TShirtClothingPage {

    private WebDriver driver;
    private WebDriverWait waitPage;

    public TShirtClothingPage (WebDriver driver)  {
        this.driver = driver;
        waitPage = new WebDriverWait(driver, 10);
    }

    private WebElement getProductHeadingCounter() {
        WebElement counter = driver.findElement(By.className("heading-counter"));

        waitPage.until(ExpectedConditions.visibilityOfAllElements(counter));
        return counter;
    }

    private int getProductCountFromHeader() {
        WebElement counter = getProductHeadingCounter();
        String counterText = counter.getText();
        int startInd = 0;
        if (counterText.contains("There is 1 product")) {
             startInd = counterText.lastIndexOf("is")+3;
        }
        else {
            startInd = counterText.lastIndexOf("are")+4;
        }
        int endInd = counterText.indexOf("p")-1;
        counterText = counterText.substring(startInd,endInd);

        return Integer.parseInt(counterText);
    }

    public ProductPage openRandomTShirtPage() throws InterruptedException {
        int productTotal = getProductCountFromHeader();

        if(productTotal == 1) {
            WebElement product = driver.findElement(By.cssSelector("#center_column > ul > li > div > div.right-block > h5 > a"));
            waitPage.until(ExpectedConditions.elementToBeClickable(product));

            Actions actions = new Actions(driver);
            actions.moveToElement(product);
            actions.perform();
            Thread.sleep(3000);

            product.click();
            Thread.sleep(5000); //forçado, não pensei na melhor forma de validar pós click

            return new ProductPage(driver);
        }
        else {
            Random r = new Random();
            int listItem = r.nextInt(productTotal);
            List<WebElement> productList = driver.findElements(By.cssSelector("#center_column > ul > li"));

            WebElement product = productList.get(listItem-1).findElement(By.cssSelector("div > div.left-block > div > a.product_img_link"));
            waitPage.until(ExpectedConditions.elementToBeClickable(product));

            Actions actions = new Actions(driver);
            actions.moveToElement(product);
            actions.perform();
            Thread.sleep(3000);

            product.click();
            Thread.sleep(5000); //forçado, não pensei na melhor forma de validar pós click

            return new ProductPage(driver);
        }
    }
}
