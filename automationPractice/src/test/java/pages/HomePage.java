package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait waitPage;

    public HomePage (WebDriver driver)  {
        this.driver = driver;
        waitPage = new WebDriverWait(driver, 10);
    }

    private WebElement womenClothingPageButton() {
        WebElement womenPageBtn = driver.findElement(By.linkText("WOMEN"));
        waitPage.until(ExpectedConditions.elementToBeClickable(womenPageBtn));
        return womenPageBtn;
    }

    private WebElement dressClothingPageButton() {
        WebElement dressPageBtn = driver.findElement(By.linkText("DRESSES"));
        waitPage.until(ExpectedConditions.elementToBeClickable(dressPageBtn));
        return dressPageBtn;
    }

    private WebElement tShirtClothingPageButton() {
        WebElement tShirtsPageBtn = driver.findElement(By.linkText("T-SHIRTS"));
        waitPage.until(ExpectedConditions.elementToBeClickable(tShirtsPageBtn));
        return tShirtsPageBtn;
    }

    public WomenClothingPage goToWomenClothingPage() throws Exception {
        WebElement pageButton = womenClothingPageButton();
        pageButton.click();

        waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/span")));
        String bannerName = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/span")).getText();
        if(bannerName.contains("Women")) {
            return new WomenClothingPage(driver);
        }
        else {
            throw new Exception("Page not found or wrong page opened.");
        }

    }

    public DressClothingPage goToDressClothingPage() throws Exception {
        WebElement pageButton = dressClothingPageButton();
        pageButton.click();

        waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/span")));
        String bannerName = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/span")).getText();

        if(bannerName.contains("Dresses")) {
            return new DressClothingPage(driver);
        }
        else {
            throw new Exception("Page not found or wrong page opened.");
        }
    }

    public TShirtClothingPage goToTShirtClothingPage() throws Exception {
        WebElement pageButton = tShirtClothingPageButton();
        pageButton.click();

        waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/span")));
        String bannerName = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/span")).getText();

        if(bannerName.contains("T-shirts")) {
            return new TShirtClothingPage(driver);
        }
        else {
            throw new Exception("Page not found or wrong page opened.");
        }
    }
}
