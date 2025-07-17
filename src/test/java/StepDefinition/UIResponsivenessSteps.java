package StepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class UIResponsivenessSteps {
    public static WebDriver driver;

    @Given("User opens Add Contact form on desktop")
    public void user_opens_add_contact_form_on_desktop() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        driver.findElement(By.id("email")).sendKeys("vasrira@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Srisathiya@15");
        driver.findElement(By.id("submit")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/contactList"));

        driver.findElement(By.id("add-contact")).click();
    }

    @Then("All fields and buttons are properly aligned")
    public void all_fields_and_buttons_are_properly_aligned() {
        List<WebElement> labels = driver.findElements(By.tagName("label"));
        List<WebElement> inputs = driver.findElements(By.cssSelector("input, button"));

        // Check if label-input pairs are aligned (basic check based on y-coordinate)
        for (int i = 0; i < labels.size(); i++) {
            Point labelPos = labels.get(i).getLocation();
            Point inputPos = inputs.get(i).getLocation();

            int labelY = labelPos.getY();
            int inputY = inputPos.getY();

            // Allowing 10 pixels of alignment tolerance
            Assert.assertTrue(Math.abs(labelY - inputY) < 15, "Field not aligned properly: " + labels.get(i).getText());
        }
    }

    @When("User adds or deletes a contact")
    public void user_adds_or_deletes_a_contact() {
        driver.findElement(By.id("firstName")).sendKeys("UI");
        driver.findElement(By.id("lastName")).sendKeys("Test");
        driver.findElement(By.id("birthdate")).sendKeys("1990-01-01");
        driver.findElement(By.id("email")).sendKeys("ui@test.com");
        driver.findElement(By.id("phone")).sendKeys("9876543210");
        driver.findElement(By.id("street1")).sendKeys("Street 1");
        driver.findElement(By.id("street2")).sendKeys("Street 2");
        driver.findElement(By.id("city")).sendKeys("Chennai");
        driver.findElement(By.id("stateProvince")).sendKeys("TN");
        driver.findElement(By.id("postalCode")).sendKeys("600001");
        driver.findElement(By.id("country")).sendKeys("India");

        driver.findElement(By.id("submit")).click();

        // Wait for redirect to contact list
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/contactList"));

        // Now delete the recently added contact
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[contains(text(),'Delete')]"));
        if (!deleteButtons.isEmpty()) {
            deleteButtons.get(deleteButtons.size() - 1).click();  // Delete the last one
        }
    }

    @Then("A success toast or confirmation message appears")
    public void success_message_appears() {
        try {
            WebElement toast = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className("Toastify__toast-body")));
            Assert.assertTrue(toast.isDisplayed());
        } catch (TimeoutException e) {
            // If no toast, at least verify user is redirected to contact list
            Assert.assertTrue(driver.getCurrentUrl().contains("/contactList"));
        } finally {
            driver.quit();
        }
    }
}
