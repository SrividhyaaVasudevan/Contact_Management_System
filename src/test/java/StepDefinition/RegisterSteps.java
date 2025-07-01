package StepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import utils.ValidationUtils;

import java.time.Duration;

public class RegisterSteps {
    public static WebDriver driver;

    @Given("User is on the registration page")
    public void user_is_on_registration_page() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/addUser");
    }

    @When("User enters valid unique email and password")
    public void user_enters_valid_unique_email_and_password() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        driver.findElement(By.id("firstName"))
              .sendKeys("Test" + timestamp);
        driver.findElement(By.id("lastName"))
              .sendKeys("User" + timestamp);
        driver.findElement(By.id("email"))
              .sendKeys("user" + timestamp + "@gmail.com");
        driver.findElement(By.id("password"))
              .sendKeys("Test@1234");
    }

    @When("User enters already registered email")
    public void user_enters_already_registered_email() {
        driver.findElement(By.id("firstName")).sendKeys("Test");
        driver.findElement(By.id("lastName")).sendKeys("User");
        driver.findElement(By.id("email")).sendKeys("vasrira@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Test@1234");
    }

    @When("User leaves all registration fields blank")
    public void user_leaves_all_registration_fields_blank() {
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();
    }

    @When("User enters invalid email format in registration")
    public void user_enters_invalid_email_format_in_registration() {
        driver.findElement(By.id("firstName")).sendKeys("Test");
        driver.findElement(By.id("lastName")).sendKeys("User");
        driver.findElement(By.id("email")).sendKeys("invalidemail");
        driver.findElement(By.id("password")).sendKeys("Test@1234");
    }

    @And("User clicks the Sign Up button")
    public void user_clicks_the_sign_up_button() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User should be redirected to login page after logout")
    public void user_should_be_redirected_to_login_page_after_logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ensure logout button is present (implying login success)
        WebElement logoutBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.logout#logout")));

        logoutBtn.click();

        // Verify login page
        WebElement loginParagraph = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Log In:']")));
        Assert.assertTrue(loginParagraph.isDisplayed(), "Login page is not displayed after logout.");
    }


    @Then("Error message {string} should be shown on registration")
    public void error_message_should_be_shown_on_registration(String expected) {
        WebElement errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error' and text()='" + expected + "']")));
        Assert.assertEquals(errorElement.getText().trim(), expected);
    }

    @Then("Email address is already in use message should be shown")
    public void email_already_in_use_message_should_be_shown() {
        WebElement errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error' and text()='Email address is already in use']")));
        Assert.assertEquals(errorElement.getText().trim(), "Email address is already in use");
    }

    @Then("Email should be considered invalid during registration")
    public void email_should_be_considered_invalid_during_registration() {
        String enteredEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertFalse(ValidationUtils.isValidEmail(enteredEmail));
    }

    @And("Close the registration browser")
    public void close_the_browser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
