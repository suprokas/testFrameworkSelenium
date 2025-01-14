package sample;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class test {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField1 = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        WebElement alertMessage;      
      

        // Perform login actions
        usernameField1.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
        loginButton.click();

        // Verify login success
        alertMessage = driver.findElement(By.xpath("//div[@id='flash']"));
        String messageText = alertMessage.getText();

        if (messageText.contains("You logged into a secure area!")) {
            System.out.println("Login Test Passed.");
        } else {
            System.out.println("Login Test Failed.");
        }
        
        driver.quit();

	}

}
