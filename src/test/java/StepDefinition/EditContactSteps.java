//package StepDefinition;
//
//import io.cucumber.java.en.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.*;
//import org.testng.Assert;
//
//import java.time.Duration;
//
//public class EditContactSteps {
//    public static WebDriver driver;
//    String contactName = "EditMe";
//
//    public void loginToApp() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
//        driver.findElement(By.id("email")).sendKeys("vasrira@gmail.com");
//        driver.findElement(By.id("password")).sendKeys("Srisathiya@15");
//        driver.findElement(By.id("submit")).click();
//    }
//
//    public void createContactForEdit() {
//        driver.findElement(By.id("add-contact")).click();
//        driver.findElement(By.id("firstName")).sendKeys(contactName);
//        driver.findElement(By.id("lastName")).sendKeys("User");
//        driver.findElement(By.id("email")).sendKeys("editme@example.com");
//        driver.findElement(By.id("phone")).sendKeys("1234567890");
//        driver.findElement(By.id("submit")).click();
//    }
//
//    @Given("User is logged in and contact exists")
//    public void user_is_logged_in_and_contact_exists() {
//        loginToApp();
//        createContactForEdit();
//    }
//
//    @When("User clicks Edit and modifies phone or email")
//    public void user_clicks_edit_and_modifies() {
//        WebElement contact = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + contactName + "')]")));
//        contact.click();
//
//        WebElement phoneField = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
//        phoneField.clear();
//        phoneField.sendKeys("9999999999");
//    }
//
//    @And("User saves the changes")
//    public void user_saves_the_changes() {
//        driver.findElement(By.id("submit")).click();
//    }
//
//    @Then("Contact list shows updated information")
//    public void contact_list_shows_updated_info() {
//        WebElement updatedContact = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".contacts")));
//        Assert.assertTrue(updatedContact.getText().contains("9999999999"));
//    }
//
//    @Given("User is editing a contact")
//    public void user_is_editing_contact() {
//        user_is_logged_in_and_contact_exists();
//        WebElement contact = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + contactName + "')]")));
//        contact.click();
//    }
//
//    @When("User clicks cancel or navigates away")
//    public void user_cancels_edit() {
//        driver.findElement(By.id("cancel")).click();
//    }
//
//    @Then("Contact remains unchanged")
//    public void contact_remains_unchanged() {
//        WebElement phone = driver.findElement(By.cssSelector(".contacts"));
//        Assert.assertTrue(phone.getText().contains("1234567890"));
//    }
//
//    @When("User removes Last Name and saves")
//    public void user_removes_last_name_and_saves() {
//        WebElement lastNameField = driver.findElement(By.id("lastName"));
//        lastNameField.clear();
//        driver.findElement(By.id("submit")).click();
//    }
//
//    @Then("Validation error \"Last Name is required\" is shown")
//    public void validation_error_lastname_required() {
//        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
//        Assert.assertTrue(error.getText().contains("Last Name is required"));
//    }
//}
