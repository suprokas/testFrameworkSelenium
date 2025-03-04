package test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ReportManager;
import utils.WaitUtils;
import com.aventstack.extentreports.ExtentTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private ExtentTest test;

    @BeforeMethod
    @Parameters("browser")
    public void SetUp(String browser) {
        logger.info("Setting up the test.");
        test = ReportManager.createTest("Login Test Setup for the browser : " + browser);
        test.info("Initializing the browser and navigating to the application");
        ConfigReader.props.setProperty("browser", browser);
        logger.info("Setting up the test for browser: {}", browser);
        initializeDriver();
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "logindata")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"tomsmith", "SuperSecretPassword!"},
            {"test", "test!"}
        };
    }

    @Test(dataProvider = "logindata")
    public void testLogin(String username, String password) {
        test = ReportManager.createTest("Login Test with username: " + username);
        logger.info("Starting login test with username: {} and password: {}", username, password);
        test.info("Starting login test with provided credentials.");

        loginPage.enterUsername(username);
        test.info("Entered username: " + username);

        loginPage.enterPassword(password);
        test.info("Entered password.");

        loginPage.clickLogin();
        test.info("Clicked login button.");

        //String screenshotPath = ReportManager.captureScreenshot(driver, "LoginTest_" + username);
        //test.info("Screenshot taken:").addScreenCaptureFromPath(screenshotPath);
        
        String screenshotPath = ReportManager.captureScreenshot(driver, "LoginTest_" + username);
        // Attach screenshot to report using the correct relative path
        test.info("Screenshot taken:").addScreenCaptureFromPath(screenshotPath);

        String msg = loginPage.getFlashMessage();
        logger.info("Login test completed with message: {}", msg);
        test.info("Flash message displayed: " + msg);

        try {
            Assert.assertTrue(msg.contains("You logged into a secure area!"), "Login failed or unexpected message.");
            test.pass("Login test passed successfully.");
        } catch (AssertionError e) {
            test.fail("Login test failed: " + e.getMessage());
            throw e;
        }
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down the test.");
        ReportManager.getInstance().flush();
        super.tearDown();
    }
}
