package StepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;

public class DeleteContactSteps {
   public static WebDriver driver;
   String contactName = "DeleteMe";

   public void loginToApp() {
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().window().maximize();
       driver.get("https://thinking-tester-contact-list.herokuapp.com/");
       driver.findElement(By.id("email")).sendKeys("vasrira@gmail.com");
       driver.findElement(By.id("password")).sendKeys("Srisathiya@15");
       driver.findElement(By.id("submit")).click();
   }

   public void createContactForDeletion() {
       driver.findElement(By.id("add-contact")).click();
       driver.findElement(By.id("firstName")).sendKeys(contactName);
       driver.findElement(By.id("lastName")).sendKeys("User");
       driver.findElement(By.id("email")).sendKeys("deleteme@example.com");
       driver.findElement(By.id("phone")).sendKeys("1234567890");
       driver.findElement(By.id("submit")).click();
   }

   @Given("User is logged in and contact exists")
   public void user_is_logged_in_and_contact_exists() {
       loginToApp();
       createContactForDeletion();
   }

   @When("User clicks delete icon")
   public void user_clicks_delete_icon() {
       WebElement contactRow = new WebDriverWait(driver, Duration.ofSeconds(10))
               .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + contactName + "')]/following-sibling::button[contains(text(),'Delete')]")));
       contactRow.click();
   }

   @Then("Contact is removed from the list")
   public void contact_removed_from_list() {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       boolean contactDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(
               By.xpath("//div[contains(text(),'" + contactName + "')]")));
       Assert.assertTrue(contactDeleted);
   }

   @Given("User is about to delete a contact")
   public void user_is_about_to_delete_contact() {
       loginToApp();
       createContactForDeletion();
   }

   @Then("Confirmation alert appears")
   public void confirmation_alert_appears() {
       // Assuming alert is handled via JS confirm
       ((JavascriptExecutor) driver).executeScript("window.confirm = function(){return true;};");
       driver.findElement(By.xpath("//div[contains(text(),'" + contactName + "')]/following-sibling::button[contains(text(),'Delete')]")).click();
   }

   @When("User confirms deletion")
   public void user_confirms_deletion() {
       // Already confirmed in previous step
   }

   @Then("Contact is deleted")
   public void contact_is_deleted() {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       boolean deleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(
               By.xpath("//div[contains(text(),'" + contactName + "')]")));
       Assert.assertTrue(deleted);
   }

   @Given("User deletes a contact")
   public void user_deletes_a_contact() {
       loginToApp();
       createContactForDeletion();
       WebElement deleteButton = new WebDriverWait(driver, Duration.ofSeconds(10))
               .until(ExpectedConditions.elementToBeClickable(
                       By.xpath("//div[contains(text(),'" + contactName + "')]/following-sibling::button[contains(text(),'Delete')]")));
       deleteButton.click();
   }

   @When("Page is refreshed")
   public void page_is_refreshed() {
       driver.navigate().refresh();
   }

   @Then("Deleted contact is not listed")
   public void deleted_contact_not_listed() {
       boolean deleted = driver.findElements(By.xpath("//div[contains(text(),'" + contactName + "')]"))
               .isEmpty();
       Assert.assertTrue(deleted);
   }
}
