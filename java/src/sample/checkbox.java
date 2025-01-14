package sample;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class checkbox {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
		
		
		 driver.get("https://the-internet.herokuapp.com/dropdown");

	        // Locate dropdown using XPath
	        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='dropdown']"));

	        // Interact with dropdown
	        Select dropdown = new Select(dropdownElement);
	        dropdown.selectByValue("1"); // Select "Option 1"

	        // Verify selected option
	        WebElement selectedOption = dropdown.getFirstSelectedOption();
	        String selectedText = selectedOption.getText();

	        if (selectedText.equals("Option 1")) {
	            System.out.println("Dropdown Test Passed.");
	        } else {
	            System.out.println("Dropdown Test Failed.");
	        }
	        Thread.sleep(1000);
	        driver.quit();

	}

}
