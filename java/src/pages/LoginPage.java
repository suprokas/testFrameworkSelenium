package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class LoginPage {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By flashMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        WebElement element = WaitUtils.waitForElementToBeVisible(driver, usernameField, 10);
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        logger.info("Entering password.");
        WebElement element = WaitUtils.waitForElementToBeVisible(driver, passwordField, 10);
        element.sendKeys(password);
    }

    public void clickLogin() {
        logger.info("Clicking login button.");
        WebElement element = WaitUtils.waitForElementToBeClickable(driver, loginButton, 10);
        element.click();
    }

    public String getFlashMessage() {
        String message = WaitUtils.waitForElementToBeVisible(driver, flashMessage, 10).getText();
        logger.info("Flash message displayed: {}", message);
        return message;
    }
}