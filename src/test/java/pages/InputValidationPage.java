package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InputValidationPage {

    WebDriver driver;
    WebDriverWait wait;

    public InputValidationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void loginAndOpenAddContactForm() {
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        driver.findElement(By.id("email")).sendKeys("vasrira@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Srisathiya@15");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("add-contact")).click();
    }

    public void enterLongFirstName() {
        String longName = "A".repeat(301);
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys(longName);
    }

    public int getFirstNameLength() {
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        return firstNameField.getAttribute("value").length();
    }

    public void enterUnicodeInAddress() {
        WebElement street1 = driver.findElement(By.id("street1"));
        String unicodeText = "\ud83c\udfe0 住所 日本語文字";
        street1.sendKeys(unicodeText);
    }

    public String getAddressFieldValue() {
        return driver.findElement(By.id("street1")).getAttribute("value");
    }
}
