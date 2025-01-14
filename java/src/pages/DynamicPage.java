package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class DynamicPage {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DynamicPage.class);

    private By startButton = By.cssSelector("#start button");
    private By finishText = By.id("finish");

    public DynamicPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickStartButton() {
        logger.info("Clicking the start button.");
        WebElement button = WaitUtils.waitForElementToBeClickable(driver, startButton, 10);
        button.click();
    }

    public String getFinishText() {
        logger.info("Waiting for the finish text to appear.");
        WebElement text = WaitUtils.waitForElementToBeVisible(driver, finishText, 10);
        return text.getText();
    }
}