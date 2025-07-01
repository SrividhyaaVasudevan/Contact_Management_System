package StepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import utils.ValidationUtils;

import java.time.Duration;

public class LoginSteps {
    public static WebDriver driver;
    // ---------------- Login  Scenarios ----------------
    @Given("User is on the login page")
    public void user_is_on_login_page() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
    }

    @When("User enters valid email and password")
    public void user_enters_valid_email_and_password() {
        driver.findElement(By.id("email")).sendKeys("vasrira@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Srisathiya@15");
    }

    @When("User enters valid email and invalid password")
    public void user_enters_valid_email_and_invalid_password() {
        driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");
        driver.findElement(By.id("password")).sendKeys("WrongPassword");
    }

    @When("User leaves email and password fields empty")
    public void user_leaves_email_and_password_fields_empty() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();
    }

    @When("User enters an invalid email format")
    public void user_enters_an_invalid_email_format() {
        driver.findElement(By.id("email")).sendKeys("user.com");
        driver.findElement(By.id("password")).sendKeys("somepassword");
    }

    @And("User clicks on the login button")
    public void user_clicks_on_login_button() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("User is redirected to the contact list page")
    public void user_is_redirected_to_contact_list_page() {
        WebElement addBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("add-contact")));
        Assert.assertTrue(addBtn.isDisplayed());
    }

    @Then("User should stay on login page")
    public void user_should_stay_on_login_page() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login") || currentUrl.endsWith("/"));
    }

    @Then("Required field validation should appear")
    public void required_field_validation_should_appear() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login") || currentUrl.endsWith("/"));
    }

    @Then("Email format error should be displayed")
    public void email_format_error_should_be_displayed() {
        String enteredEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertFalse(ValidationUtils.isValidEmail(enteredEmail), "Email format should be invalid.");
    }

    @Then("Password field should mask input")
    public void password_field_should_mask_input() {
        String type = driver.findElement(By.id("password")).getAttribute("type");
        Assert.assertEquals(type, "password");
    }

    @And("Close the browser")
    public void close_the_browser() {
        driver.quit();
    }
}
