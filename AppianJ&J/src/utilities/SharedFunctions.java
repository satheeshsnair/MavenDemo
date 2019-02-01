package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;

public class SharedFunctions {
	
	public WebDriver driver;
	public ExtentTest test;
	
	public SharedFunctions (WebDriver driver, ExtentTest test)
	{
		this.driver = driver;
		this.test = test;
	}
	
	public void dropdown(By locator) throws InterruptedException
	{
		
		Thread.sleep(3000);
		WebElement element = driver.findElement(locator);
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
		Thread.sleep(2000);
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(2000);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(2000);
		test.pass("Selected options from" );
	}
	
	public void click(By locator) throws InterruptedException 
	{
		Thread.sleep(4000);
		driver.findElement(locator).click();
		test.pass("Clicked");
	}
	public void date(By locator, int days) throws InterruptedException
	{
		 Thread.sleep(2000);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 Calendar c = Calendar.getInstance();
		 c.setTime(new Date()); // Now use today date.
		 c.add(Calendar.DATE, days); // Adding 7 days
		 String output = sdf.format(c.getTime());
		 driver.findElement(locator).sendKeys(output);
	}
	public void sendkey(By locator, String value) throws InterruptedException 
	{
		Thread.sleep(2000);
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

}
