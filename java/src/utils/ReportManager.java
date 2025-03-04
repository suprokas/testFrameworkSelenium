package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
    private static ExtentReports extent;
    private static ExtentTest currentTest;
    private static final Logger logger = LoggerFactory.getLogger(ReportManager.class);

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (ReportManager.class) {
                if (extent == null) {
                    File reportDir = new File("test-output2");
                    if (!reportDir.exists()) {
                        reportDir.mkdirs();
                    }

                    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String reportPath = "test-output2/ExtentReport_" + timestamp + ".html";
                    logger.info("Report saved at: {}", reportPath);
                    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
                    sparkReporter.config().setTheme(Theme.DARK);
                    sparkReporter.config().setDocumentTitle("Test Automation Report");
                    sparkReporter.config().setReportName("Automation Tests");

                    extent = new ExtentReports();
                    extent.attachReporter(sparkReporter);
                    extent.setSystemInfo("Environment", "QA");
                    extent.setSystemInfo("Tester", "Test name");
                }
            }
        }
        return extent;
    }
    
    public static void logPass(String message) {
        if (currentTest != null) {
            currentTest.pass(message);
            logger.info("Logged PASS in the report: {}", message);
        } else {
            logger.warn("No active test found to log PASS: {}", message);
        }
    }

    public static ExtentTest createTest(String testName) {
        logger.info("Creating ExtentTest for: {}", testName);
        return getInstance().createTest(testName);
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotsDir = "test-output2/screenshots"; // Define a single directory for screenshots
        String fileName = screenshotName + "_" + date + ".png";
        String fullPath = System.getProperty("user.dir") + "/" + screenshotsDir + "/" + fileName;

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(fullPath);

        try {
            // Ensure the directory structure exists
            destFile.getParentFile().mkdirs();
            FileUtils.copyFile(srcFile, destFile);
            logger.info("Screenshot saved at: {}", fullPath);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }

        // Return the relative path from the report's location
        return "screenshots/" + fileName;
    }




}
