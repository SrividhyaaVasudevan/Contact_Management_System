package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import StepDefinition.RegisterSteps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    @After
    public void takeScreenshotOnFailure(Scenario scenario) {
        WebDriver driver = RegisterSteps.driver;

        if (driver != null && scenario.isFailed()) {
            try {
                // Create screenshots directory if it doesn't exist
                File screenshotDir = new File("screenshots");
                if (!screenshotDir.exists()) {
                    screenshotDir.mkdir();
                }

                // Take screenshot
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // Generate timestamped filename
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String fileName = "screenshots/" + scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";

                // Save file
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(screenshot);
                fos.close();

                System.out.println("📸 Screenshot saved to: " + fileName);
            } catch (IOException e) {
                System.err.println("❌ Failed to save screenshot: " + e.getMessage());
            }
        }
    }
}
