package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

public class WindowsPage {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(WindowsPage.class);

    private By clickHereLink = By.linkText("Click Here");

    public WindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLink() {
        logger.info("Clicking the 'Click Here' link.");
        driver.findElement(clickHereLink).click();
    }

    public void switchToNewWindow() {
        logger.info("Switching to the new window.");
        String mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public String getWindowTitle() {
        return driver.getTitle();
    }
}