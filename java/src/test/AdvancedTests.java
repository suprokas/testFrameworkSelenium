package test;

import Base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DynamicPage;
import pages.FramesPage;
import pages.WindowsPage;
import utils.ConfigReader;
import utils.ReportManager;

public class AdvancedTests extends BaseTest {

    private DynamicPage dynamicPage;
    private FramesPage framesPage;
    private WindowsPage windowsPage;
    private static final Logger logger = LoggerFactory.getLogger(AdvancedTests.class);

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        ConfigReader.props.setProperty("browser", browser);
        initializeDriver();
    }

    @AfterMethod
    public void tearDownTest() {
        logger.info("Test execution completed. Tearing down driver.");
        tearDown();
    }

    @Test
    public void testDynamicLoading() {
        ReportManager.createTest("Dynamic Loading Test");
        logger.info("Starting Dynamic Loading Test.");
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        dynamicPage = new DynamicPage(driver);

        dynamicPage.clickStartButton();
        String finishText = dynamicPage.getFinishText();
        logger.info("Validating the finish text: {}", finishText);

        Assert.assertEquals(finishText, "Hello World!", "Finish text did not match.");
        ReportManager.logPass("Dynamic Loading Test passed.");
    }

    @Test
    public void testFramesInteraction() {
        ReportManager.createTest("Frames Interaction Test");
        logger.info("Starting Frames Interaction Test.");
        driver.get("https://the-internet.herokuapp.com/iframe");
        framesPage = new FramesPage(driver);

        framesPage.switchToFrame();
        String inputText = "Testing frames interaction.";
        framesPage.enterTextInFrame(inputText);
        String text = framesPage.getTextFromFrame();
        logger.info("Validating the frame text: {}", text);

        Assert.assertEquals(text, inputText, "Frame text did not match.");
        framesPage.switchToDefaultContent();
        ReportManager.logPass("Frames Interaction Test passed.");
    }

    @Test
    public void testWindowSwitching() {
        ReportManager.createTest("Window Switching Test");
        logger.info("Starting Window Switching Test.");
        driver.get("https://the-internet.herokuapp.com/windows");
        windowsPage = new WindowsPage(driver);

        windowsPage.clickLink();
        windowsPage.switchToNewWindow();
        String title = windowsPage.getWindowTitle();
        logger.info("Validating the new window title: {}", title);

        Assert.assertEquals(title, "New Window", "Window title did not match.");
        ReportManager.logPass("Window Switching Test passed.");
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down the test.");
        ReportManager.getInstance().flush();
        super.tearDown();
    }
}