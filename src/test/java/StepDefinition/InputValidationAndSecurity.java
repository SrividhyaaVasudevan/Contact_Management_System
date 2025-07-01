//package StepDefinition;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class InputValidationAndSecurity {
//    public static WebDriver driver;
//
//    public void loginAndOpenAddContactForm() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
//        driver.findElement(By.id("email")).sendKeys("vasrira@gmail.com");
//        driver.findElement(By.id("password")).sendKeys("Srisathiya@15");
//        driver.findElement(By.id("submit")).click();
//        driver.findElement(By.id("add-contact")).click();
//    }
//
//    @When("User enters more than 300 characters in First Name")
//    public void enter_long_text_in_first_name() {
//        loginAndOpenAddContactForm();
//        String longName = "A".repeat(301);
//        WebElement firstNameField = driver.findElement(By.id("firstName"));
//        firstNameField.sendKeys(longName);
//    }
//
//    @Then("Field should restrict input or show error")
//    public void verify_max_char_limit() {
//        WebElement firstNameField = driver.findElement(By.id("firstName"));
//        String entered = firstNameField.getAttribute("value");
//        Assert.assertTrue(entered.length() <= 300, "Input exceeds max limit or no error shown");
//    }
//
//    @When("User enters emojis or non-English characters in Address field")
//    public void enter_unicode_in_address() {
//        loginAndOpenAddContactForm();
//        WebElement street1 = driver.findElement(By.id("street1"));
//        String unicodeText = "\ud83c\udfe0 住所 日本語文字";
//        street1.sendKeys(unicodeText);
//    }
//
//    @Then("They are accepted and displayed correctly")
//    public void verify_unicode_displayed() {
//        WebElement street1 = driver.findElement(By.id("street1"));
//        String value = street1.getAttribute("value");
//        Assert.assertTrue(value.contains("\ud83c\udfe0") && value.contains("日本語文字"));
//    }
//}
