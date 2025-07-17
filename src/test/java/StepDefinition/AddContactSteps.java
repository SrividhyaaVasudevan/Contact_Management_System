package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;

public class AddContactSteps {
   public static WebDriver driver;

   public void loginToApp() {
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().window().maximize();
       driver.get("https://thinking-tester-contact-list.herokuapp.com/");
       driver.findElement(By.id("email")).sendKeys("vasrira@gmail.com");
       driver.findElement(By.id("password")).sendKeys("Srisathiya@15");
       driver.findElement(By.id("submit")).click();
   }

   @Given("User is logged in")
   public void user_is_logged_in() {
       loginToApp();
   }

   @When("User fills in all required and optional contact fields")
   public void fill_all_contact_fields() {
       driver.findElement(By.id("add-contact")).click();
       driver.findElement(By.id("firstName")).sendKeys("John");
       driver.findElement(By.id("lastName")).sendKeys("Doe");
       driver.findElement(By.id("birthdate")).sendKeys("1990-01-01");
       driver.findElement(By.id("email")).sendKeys("john.doe@example.com");
       driver.findElement(By.id("phone")).sendKeys("9876543210");
       driver.findElement(By.id("street1")).sendKeys("Street 1");
       driver.findElement(By.id("street2")).sendKeys("Street 2");
       driver.findElement(By.id("city")).sendKeys("Chennai");
       driver.findElement(By.id("stateProvince")).sendKeys("TN");
       driver.findElement(By.id("postalCode")).sendKeys("600001");
       driver.findElement(By.id("country")).sendKeys("India");
   }

   @When("User clicks on the Submit button")
   public void user_clicks_submit() {
       driver.findElement(By.id("submit")).click();
   }

   @Then("Contact is added and visible in the contact list")
   public void verify_contact_added() {
       WebElement contactList = new WebDriverWait(driver, Duration.ofSeconds(10))
               .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".contacts")));
       Assert.assertTrue(contactList.getText().contains("John"));
   }

   @When("User leaves First Name and Last Name fields blank")
   public void leave_required_fields_blank() {
       driver.findElement(By.id("add-contact")).click();
       driver.findElement(By.id("firstName")).clear();
       driver.findElement(By.id("lastName")).clear();
   }

   @Then("Required field validation messages are displayed")
   public void required_field_validation_displayed() {
       WebElement error = driver.findElement(By.id("error"));
       Assert.assertTrue(error.isDisplayed());
   }

   @When("User enters alphabets in the phone number field")
   public void enter_invalid_phone_input() {
       driver.findElement(By.id("add-contact")).click();
       driver.findElement(By.id("phone")).sendKeys("abcdxyz");
   }

   @Then("Phone field should show input validation error")
   public void phone_field_should_show_error() {
       String value = driver.findElement(By.id("phone")).getAttribute("value");
       Assert.assertFalse(value.matches("[0-9]+"));
   }

   @When("User adds a contact that already exists")
   public void add_duplicate_contact() {
       fill_all_contact_fields();
       user_clicks_submit();
       fill_all_contact_fields();
       user_clicks_submit();
   }

   @Then("Application should prevent or allow duplicate based on rules")
   public void duplicate_check() {
       Assert.assertTrue(driver.findElement(By.cssSelector(".contacts")).isDisplayed());
   }

   @When("User adds a contact and navigates to add another")
   public void user_adds_another_contact() {
       fill_all_contact_fields();
       user_clicks_submit();
       driver.findElement(By.id("add-contact")).click();
   }

   @Then("The add contact form should be reset for new input")
   public void form_should_be_reset() {
       Assert.assertEquals(driver.findElement(By.id("firstName")).getAttribute("value"), "");
   }

   @After
   public void closeBrowser() {
       if (driver != null) driver.quit();
   }
}
