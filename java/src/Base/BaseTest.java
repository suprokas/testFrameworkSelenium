package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;
//import utils.WaitUtils;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public void initializeDriver() {
        String browser = ConfigReader.getProperty("browser");
        logger.info("Initializing the driver for browser: {}", browser);
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(ConfigReader.getProperty("url"));
            logger.info("Driver initialized and navigated to URL: {}", ConfigReader.getProperty("url"));
        } catch (Exception e) {
            logger.error("Error during driver initialization", e);
            throw e;
        }
    }

    public void tearDown() {
        if (driver != null) {
            logger.info("Closing the driver.");
            driver.quit();
        }
    }
}
