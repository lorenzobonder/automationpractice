package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class CreateAUserPage {

    private WebDriver driver;
    private WebDriverWait waitPage;

    public CreateAUserPage (WebDriver driver)  {
        this.driver = driver;
        waitPage = new WebDriverWait(driver, 10);
    }

    private WebElement radioButtonMaleGender() {
        WebElement radioMale = driver.findElement(By.id("uniform-id_gender1"));
        waitPage.until((ExpectedConditions.elementToBeClickable(radioMale)));
        return radioMale;
    }

    private WebElement radioButtonFemaleGender() {
        WebElement radioFemale = driver.findElement(By.id("uniform-id_gender2"));
        waitPage.until((ExpectedConditions.elementToBeClickable(radioFemale)));
        return radioFemale;
    }

    private WebElement customerFirstName() {
        WebElement fstName = driver.findElement(By.id("customer_firstname"));
        waitPage.until((ExpectedConditions.elementToBeClickable(fstName)));
        return fstName;
    }

    private WebElement customerLastName() {
        WebElement lstName = driver.findElement(By.id("customer_lastname"));
        waitPage.until((ExpectedConditions.elementToBeClickable(lstName)));
        return lstName;
    }

    private WebElement customerEmail() {
        WebElement email = driver.findElement(By.id("customer_lastname"));
        waitPage.until((ExpectedConditions.elementToBeClickable(email)));
        return email;
    }

    private WebElement customerPassword() {
        WebElement pw = driver.findElement(By.id("passwd"));
        waitPage.until((ExpectedConditions.elementToBeClickable(pw)));
        return pw;
    }

    private Select dateOfBirthDaySelector() {
        Select day = new Select (driver.findElement(By.id("days")));

        return day;
    }

    private Select dateOfBirthMonthSelector() {
        Select month = new Select (driver.findElement(By.id("months")));

        return month;
    }

    private Select dateOfBirthYearSelector() {
        Select year = new Select (driver.findElement(By.id("years")));

        return year;
    }

    private WebElement newsletterCheckbox() {
        WebElement newsletter = driver.findElement(By.id("newsletter"));
        waitPage.until((ExpectedConditions.elementToBeClickable(newsletter)));
        return newsletter;
    }

    private WebElement optInCheckbox() {
        WebElement optIn = driver.findElement(By.id("optin"));
        waitPage.until((ExpectedConditions.elementToBeClickable(optIn)));
        return optIn;
    }

    private WebElement addressUserFirstName() {
        WebElement fstN = driver.findElement(By.id("firstname"));
        waitPage.until((ExpectedConditions.elementToBeClickable(fstN)));
        return fstN;
    }

    private WebElement addressUserLastName() {
        WebElement lastN = driver.findElement(By.id("lastname"));
        waitPage.until((ExpectedConditions.elementToBeClickable(lastN)));
        return lastN;
    }

    private WebElement addressCompany() {
        WebElement comp = driver.findElement(By.id("company"));
        waitPage.until((ExpectedConditions.elementToBeClickable(comp)));
        return comp;
    }

    private WebElement addressLineOneCompany() {
        WebElement line1 = driver.findElement(By.id("address1"));
        waitPage.until((ExpectedConditions.elementToBeClickable(line1)));
        return line1;
    }

    private WebElement addressLineTwoCompany() {
        WebElement line2 = driver.findElement(By.id("address2"));
        waitPage.until((ExpectedConditions.elementToBeClickable(line2)));
        return line2;
    }

    private WebElement addressCity() {
        WebElement city = driver.findElement(By.id("city"));
        waitPage.until((ExpectedConditions.elementToBeClickable(city)));
        return city;
    }

    private Select addressStateSelector() {
        Select stateUs = new Select (driver.findElement(By.id("id_state")));


        return stateUs;
    }

    private WebElement addressZipCode() {
        WebElement zip = driver.findElement(By.id("postcode"));
        waitPage.until((ExpectedConditions.elementToBeClickable(zip)));
        return zip;
    }

    private Select addressCountrySelector() {
        Select ctry = new Select (driver.findElement(By.id("id_country")));

        return ctry;
    }

    private WebElement addressExtraInfo() {
        WebElement other = driver.findElement(By.id("other"));
        waitPage.until((ExpectedConditions.elementToBeClickable(other)));
        return other;
    }

    private WebElement addressHomePhone() {
        WebElement homePh = driver.findElement(By.id("phone"));
        waitPage.until((ExpectedConditions.elementToBeClickable(homePh)));
        return homePh;
    }

    private WebElement addressMobilePhone() {
        WebElement mob = driver.findElement(By.id("phone_mobile"));
        waitPage.until((ExpectedConditions.elementToBeClickable(mob)));
        return mob;
    }

    private WebElement addressAlias() {
        WebElement alias = driver.findElement(By.id("alias"));
        waitPage.until((ExpectedConditions.elementToBeClickable(alias)));
        return alias;
    }

    private WebElement registerButton() {
        WebElement btn = driver.findElement(By.id("submitAccount"));
        waitPage.until((ExpectedConditions.elementToBeClickable(btn)));
        return btn;
    }

    public String getCustomerFirstName() {
        return customerFirstName().getText();
    }

    public String getCustomerLastName() {
        return customerLastName().getText();
    }

    public String getCustomerEmail() {
        return customerEmail().getText();
    }

    public String getCustomerDayOfBirth() {
        return dateOfBirthDaySelector().getFirstSelectedOption().getText();
    }

    public String getCustomerMonthOfBirth() {
        return dateOfBirthMonthSelector().getFirstSelectedOption().getText();
    }

    public String getCustomerYearOfBirth() {
        return dateOfBirthYearSelector().getFirstSelectedOption().getText();
    }

    public String getAddressFirstName() {
        return addressUserFirstName().getText();
    }

    public String getAddressLastName() {
        return addressUserLastName().getText();
    }

    public String getAddressCompany() {
        return addressCompany().getText();
    }

    public String getAddressLineOne() {
        return addressLineOneCompany().getText();
    }

    public String getAddressLineTwo() {
        return addressLineTwoCompany().getText();
    }

    public String getAddressCity() {
        return addressCity().getText();
    }

    public String getAddressState() {
        return addressStateSelector().getFirstSelectedOption().getText();
    }

    public String getAddressZipCode() {
        return addressZipCode().getText();
    }

    public String getAddressCountry() {
        return addressCountrySelector().getFirstSelectedOption().getText();
    }

    public String getAddressExtraInfo() {
        return addressExtraInfo().getText();
    }

    public String getHomePhone() {
        return addressHomePhone().getText();
    }

    public String getMobilePhone() {
        return addressMobilePhone().getText();
    }

    public String getAddressAlias() {
        return addressAlias().getText();
    }

    public void setCustomerFirstName(String name) throws InterruptedException {
        customerFirstName().sendKeys(name);
        Thread.sleep(1000);
    }

    public void setCustomerLastName(String name) throws InterruptedException {
        customerLastName().sendKeys(name);
        Thread.sleep(1000);
    }

    public void setCustomerEmail(String email) throws InterruptedException {
        customerEmail().sendKeys(email);
        Thread.sleep(1000);
    }

    public void setCustomerPassword(String pw) throws InterruptedException {
        customerPassword().sendKeys(pw);
        Thread.sleep(1000);
    }

    public void setCustomerDOB(String day, String month, String year) throws InterruptedException {
        dateOfBirthDaySelector().selectByValue(day);
        Thread.sleep(500);
        dateOfBirthMonthSelector().selectByValue(month);
        Thread.sleep(500);
        dateOfBirthYearSelector().selectByValue(year);
        Thread.sleep(500);
    }

    public void checkNewsletter() {
        newsletterCheckbox().click();
    }

    public void checkOptIn() {
        optInCheckbox().click();
    }

    public void setAddressFirstName(String name) throws InterruptedException {
        addressUserFirstName().sendKeys(name);
        Thread.sleep(1000);
    }

    public void setAddressLastName(String name) throws InterruptedException {
        addressUserLastName().sendKeys(name);
        Thread.sleep(1000);
    }

    public void setAddressCompany(String company) throws InterruptedException {
        addressCompany().sendKeys(company);
        Thread.sleep(1000);
    }

    public void setAddressLineOne(String lineOne) throws InterruptedException {
        addressLineOneCompany().sendKeys(lineOne);
        Thread.sleep(1000);
    }

    public void setAddressLineTwo(String lineTwo) throws InterruptedException {
        addressLineTwoCompany().sendKeys(lineTwo);
        Thread.sleep(1000);
    }

    public void setAddressCity(String city) throws InterruptedException {
        addressCity().sendKeys(city);
        Thread.sleep(1000);
    }

    public void setAddressState(String state) throws InterruptedException {
        addressStateSelector().selectByVisibleText(state);
        Thread.sleep(1000);
    }

    public void setAddressZipCode(String zip) throws InterruptedException {
        addressZipCode().sendKeys(zip);
        Thread.sleep(1000);
    }

    public void setAddressCountry(String country) throws InterruptedException {
        addressStateSelector().selectByVisibleText(country);
        Thread.sleep(1000);
    }

    public void setAddressExtraInfo(String extra) throws InterruptedException {
        addressExtraInfo().sendKeys(extra);
        Thread.sleep(1000);
    }

    public void setAddressHomePhone(String phone) throws InterruptedException {
        addressHomePhone().sendKeys(phone);
        Thread.sleep(1000);
    }

    public void setAddressMobilePhone(String phone) throws InterruptedException {
        addressMobilePhone().sendKeys(phone);
        Thread.sleep(1000);
    }

    public void setAddressAlias(String alias) throws InterruptedException {
        addressAlias().sendKeys(alias);
        Thread.sleep(1000);
    }

    public CheckoutPage registerAndContinueCheckout() throws InterruptedException {
        registerButton().click();
        Thread.sleep(3000);

        waitPage.until(ExpectedConditions.visibilityOfElementLocated(By.id("order")));
        waitPage.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#center_column > h1"),"ADDRESSES"));

        return new CheckoutPage(driver);
    }
}
