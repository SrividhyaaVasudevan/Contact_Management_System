package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DeleteContactPage {

    WebDriver driver;
    WebDriverWait wait;

    public DeleteContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Delete a contact by name
    public void deleteContactByName(String contactName) {
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(text(),'" + contactName + "')]/following-sibling::button[contains(text(),'Delete')]")));
        deleteBtn.click();
    }

    // Override browser confirmation alert
    public void overrideConfirmationAlert() {
        ((JavascriptExecutor) driver).executeScript("window.confirm = function(){return true;}");
    }

    // Verify contact is deleted
    public boolean isContactDeleted(String contactName) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(text(),'" + contactName + "')]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Refresh the page
    public void refreshPage() {
        driver.navigate().refresh();
    }
}
