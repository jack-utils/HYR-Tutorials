package com.SeleniumPractice;
//Explicit wait lo oka obj create chesukoni tarvta use chesukuntam avasranni batti motham driver ki appicable avvadu
import java.time.Duration;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Yadagiri Reddy
 * Explicit Wait in Selenium WebDriver?
 */
// Explicit Wait waits for a particular condition to be satisfied before continuing execution. It uses WebDriverWait and polls at a default interval. 
// 	Fluent Wait is a more flexible implementation that allows us to customize the polling frequency, timeout duration, and exceptions to ignore. 
// 	In practice, Explicit Wait is sufficient for most scenarios, while Fluent Wait is used when finer control over the waiting behavior is required."
// Polling is the interval at which Selenium repeatedly checks whether the expected condition is met. In Fluent Wait, we can customize this polling frequency using pollingEvery(). 
// 	For example, if the polling interval is 2 seconds, Selenium checks the condition every 2 seconds until the timeout is reached or the condition becomes true.
public class TestExplicitWait {
	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1000, 1000));
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
//		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//		wait.withTimeout(Duration.ofSeconds(10));
		
		driver.get("https://testproject.io/");
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.linkText("Login")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if(!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
//				Function<WebDriver, Boolean> f = new Function<WebDriver, Boolean>() {
//					@Override
//					public Boolean apply(WebDriver webDriver) {
//						return webDriver.findElement(By.id("username")).isDisplayed();
//					}
//				};
//				wait.until(f);
//				wait.until(d->d.findElement(By.id("username")).isDisplayed());
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
				driver.findElement(By.id("username")).sendKeys("HYR Tutorials");
			}
		}
	}
}
