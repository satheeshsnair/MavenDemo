package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;

public class OrderOwner {
	
	public WebDriver driver;
	WebDriverUtilities sharefunctions;
	Utilites utilities;
	Login login; //Initialize class
	Headers headers;
	AddNewPosition newposition;
	ExtentTest test;
	
	public By submit = By.xpath("//button[@class='Button---btn Button---default_direction Button---primary appian-context-last-in-list']");
	public By position_ID = By.xpath("(//a[@class='elements---global_a'])[1]");
	public By accept = By.xpath("//button[@class='Button---btn Button---default_direction Button---primary']");
	public By reject = By.xpath("//button[@class='Button---btn Button---default_direction Button---destructive appian-context-first-in-list']");
	public By close = By.xpath("//button[@class='Button---btn Button---default_direction appian-context-last-in-list']");
	public By comment = By.xpath("//textarea[@class='ParagraphWidget---textarea ParagraphWidget---align_start']");
	public By providedemandtask;
	 
	
	public OrderOwner(WebDriver driver, ExtentTest test)
	{
		this.driver=driver;
		this.test = test;
		headers = new Headers(driver, test);
		utilities = new Utilites(driver,test);
		sharefunctions = new WebDriverUtilities(driver,test);
		int demandid = utilities.demandid(1, 1, 0);
		providedemandtask = By.xpath("//a[contains(text(),'" + demandid + "')]");
	}
	public void clickontask() throws Exception
	{
		sharefunctions.click(headers.Tasktab);
		sharefunctions.waitTime();
		String expected = "Tasks";
		sharefunctions.waitTime();
		if(driver.getTitle().equals(expected))
		{
			utilities.passsnaps(driver);
			test.pass("Clicked on Task");
		}
		else
		{
			utilities.failsnaps(driver);
			test.fail("Click not happened");
			Assert.fail("Click not happened");
		}
	}
	public void clickondemand() throws Exception
	{
		int demandID = utilities.demandid(1,1,0);
		String demand = Integer.toString(demandID);		
		if(sharefunctions.isXpathExists(providedemandtask)) {
			String task = driver.findElement(providedemandtask).getText();
			if(task.contains(demand))
			{
				sharefunctions.click(providedemandtask);
				String expected = driver.getTitle();
				sharefunctions.waitTime();
				if(expected.contains("Provide demand"))
				{
					utilities.passsnaps(driver);
					test.pass("Clicked on demand" + demand);
				}
			}
		}else
		{
			test.fail("no such demand");
			utilities.failsnaps(driver);
			Assert.fail("no such demand");
		}
	}
	public void clickpositionid() throws Exception
	{
		sharefunctions.waitTime();
		if(sharefunctions.isXpathExists(position_ID))
		{
			sharefunctions.click(position_ID);
			test.pass("Clicked on Position ID");
			utilities.passsnaps(driver);
		}
		else
		{
			test.fail("Position ID missing");
			utilities.failsnaps(driver);
			//Assert.fail("Position ID missing");
		}
		if(sharefunctions.isXpathExists(accept))
		{
			sharefunctions.click(accept);
			test.pass("Clicked on Accept");
			utilities.passsnaps(driver);
		}
		else
		{
			test.fail("Accept button missing");
			utilities.failsnaps(driver);
			//Assert.fail("Accept button missing");
		}if(sharefunctions.isXpathExists(submit))
		{
			sharefunctions.click(submit);
			test.pass("Clicked on Submit");
			utilities.passsnaps(driver);
		}
		else
		{
			test.fail("Submit button missing");
			utilities.failsnaps(driver);
			Assert.fail("Submit button missing");
		}
//		String expected = "News";
//		sharefunctions.waitTime();
//		sharefunctions.waitTime();
//		if(driver.getTitle().equals(expected))
//		{
//			test.pass("Reource and position approved");
//			utilities.passsnaps(driver);
//		}
//		else
//		{
//			utilities.failsnaps(driver);
//			test.fail("Not able to approve");
//			Assert.fail("Not able to approve");
//		}
	}
	public void logout() throws Exception
	{
		sharefunctions.click(headers.logo);
		sharefunctions.waitTime();
		sharefunctions.click(headers.logout);
	}
}
