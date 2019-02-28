package reusableLibrary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;

public class WebDriverUtilities {
	
	public WebDriver driver;
	public ExtentTest test;
	
	public WebDriverUtilities (WebDriver driver, ExtentTest test)
	{
		this.driver = driver;
		this.test = test;
	}
	
	public void dropdown(By locator) throws InterruptedException
	{
			WebElement element = driver.findElement(locator);
			Actions action = new Actions(driver);
			action.moveToElement(element).click().build().perform();
			waitTime();
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			waitTime();
			action.sendKeys(Keys.TAB).build().perform();
	}
	
	public void click(By locator) throws InterruptedException 
	{
		waitTime();
		driver.findElement(locator).click();
	}
	public void date(By locator, int days) throws InterruptedException
	{
		 waitTime();
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 Calendar c = Calendar.getInstance();
		 c.setTime(new Date()); // Now use today date.
		 c.add(Calendar.DATE, days); // Adding 7 days
		 String output = sdf.format(c.getTime());
		 driver.findElement(locator).sendKeys(output);
	}
	public void sendkey(By locator, String value) throws InterruptedException 
	{
		waitTime();
		driver.findElement(locator).sendKeys(value);
	}
	public boolean isXpathExists(By xpath) {
		try {
			 driver.findElement(xpath);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	public void waitTime() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
}
