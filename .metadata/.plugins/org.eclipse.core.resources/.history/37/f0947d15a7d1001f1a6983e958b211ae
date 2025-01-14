package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FramesPage {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(FramesPage.class);

    private By iframe = By.id("mce_0_ifr");
    private By textArea = By.id("tinymce");

    public FramesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToFrame() {
        logger.info("Switching to the iframe.");
        driver.switchTo().frame(driver.findElement(iframe));
    }

    public void enterTextInFrame(String text) {
        logger.info("Entering text inside the iframe.");
        WebElement editor = driver.findElement(textArea);
        editor.clear();
        editor.sendKeys(text);
    }

    public String getTextFromFrame() {
        logger.info("Getting text from the iframe.");
        return driver.findElement(textArea).getText();
    }

    public void switchToDefaultContent() {
        logger.info("Switching back to the default content.");
        driver.switchTo().defaultContent();
    }
}