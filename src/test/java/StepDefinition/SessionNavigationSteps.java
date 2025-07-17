package StepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;

public class SessionNavigationSteps {
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

   @When("User clicks Logout")
   public void user_clicks_logout() {
       WebElement logout = new WebDriverWait(driver, Duration.ofSeconds(10))
               .until(ExpectedConditions.elementToBeClickable(By.id("logout")));
       logout.click();
   }

   @Then("User is redirected to login page")
   public void redirected_to_login() {
       new WebDriverWait(driver, Duration.ofSeconds(10))
               .until(ExpectedConditions.urlContains("/login"));
       Assert.assertTrue(driver.getCurrentUrl().contains("/login") || driver.getCurrentUrl().endsWith("/"));
   }

   @When("User refreshes the page")
   public void user_refreshes_page() {
       driver.navigate().refresh();
   }

   @Then("User stays on contact list page")
   public void user_stays_on_contact_list() {
       Assert.assertTrue(driver.getCurrentUrl().contains("/contactList"));
   }

   @Given("User is not logged in")
   public void user_is_not_logged_in() {
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().window().maximize();
   }

   @When("User navigates to /contact-list URL")
   public void user_navigates_to_contact_list_directly() {
       driver.get("https://thinking-tester-contact-list.herokuapp.com/contactList");
   }

   @Given("User logs out")
   public void user_logs_out() {
       loginToApp();
       user_clicks_logout();
   }

   @When("User presses back button in browser")
   public void user_presses_back_button() {
       driver.navigate().back();
   }

   @Then("User remains on login page")
   public void user_remains_on_login() {
       Assert.assertTrue(driver.getCurrentUrl().contains("/login") || driver.getCurrentUrl().endsWith("/"));
   }
}
