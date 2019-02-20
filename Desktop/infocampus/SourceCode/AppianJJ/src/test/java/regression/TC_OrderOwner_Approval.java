package regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AddNewPosition;
import pages.Headers;
import pages.Login;
import pages.OrderOwner;
import pages.ResourcesPage;
import reusableLibrary.DriverFactory;
import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;

public class TC_OrderOwner_Approval extends DriverFactory 
{
	Login login; //Initialize class
	Headers headers;
	Utilites utilities;
	AddNewPosition newposition;
	WebDriverUtilities sharefunctions;
	ResourcesPage resource;
	OrderOwner orderowner;
	
	@Test(enabled=true,priority=0)
	public void loginasvendor() throws Exception
	{
		initializeExtentReport("Login");
		login = new Login(driver,testInstance); //initialize objectl
		login.username(0,2,0); //sheet num, row , column
		login.password(0,2,1); //sheet num, row , column
		login.login();
		String expected = "News";
		Thread.sleep(2000);
		if(driver.getTitle().equals(expected))
		{
			utilities = new Utilites(driver,testInstance);
			utilities.passsnaps(driver);
			utilities.WriteExcel(0, 1, 2, "Pass");
		}
		else
		{
			utilities = new Utilites(driver,testInstance);
			utilities.failsnaps(driver);
			utilities.WriteExcel(0, 1, 2, "Fail");
		}
	}
	@Test(enabled=true,priority=1)
	public void clickontask() throws Exception
	{
		initializeExtentReport("Click on Task");
		headers = new Headers(driver, testInstance);
		utilities = new Utilites(driver,testInstance);
		sharefunctions = new WebDriverUtilities(driver,testInstance);
		sharefunctions.click(headers.Tasktab);
		
		String expected = "Tasks";
		Thread.sleep(2000);;
		if(driver.getTitle().equals(expected))
		{
			utilities.passsnaps(driver);
			testInstance.pass("Clicked on Task");
		}
		else
		{
			utilities.failsnaps(driver);
			testInstance.fail("Click not happened");
			Assert.fail("Click not happened");
		}
	}
	@Test(enabled=true,priority = 2)
	public void clickondemand() throws Exception
	{
		initializeExtentReport("Select Demand task");
		int demandID = utilities.demandid(1,1,0);
		String demand = Integer.toString(demandID);		
		if(sharefunctions.isXpathExists(headers.providedemandtask)) {
			String task = driver.findElement(headers.providedemandtask).getText();
			if(task.contains(demand))
			{
				sharefunctions.click(headers.providedemandtask);
				String expected = driver.getTitle();
				Thread.sleep(3000);
				if(expected.contains("Provide demand"))
				{
					utilities.passsnaps(driver);
					testInstance.pass("Clicked on demand" + demand);
				}
			}
		}else
		{
			testInstance.fail("no such demand");
			utilities.failsnaps(driver);
			Assert.fail("no such demand");
		}
	}
	@Test(enabled=true,priority = 3)
	public void clickonpositionid() throws Exception
	{
		initializeExtentReport("Click on Position ID and Approve");
		orderowner = new OrderOwner(driver);
		Thread.sleep(6000);
		if(sharefunctions.isXpathExists(orderowner.position_ID))
		{
			sharefunctions.click(orderowner.position_ID);
			testInstance.pass("Clicked on Position ID");
			utilities.passsnaps(driver);
		}
		else
		{
			testInstance.fail("Position ID missing");
			utilities.failsnaps(driver);
			//Assert.fail("Position ID missing");
		}
		if(sharefunctions.isXpathExists(orderowner.accept))
		{
			sharefunctions.click(orderowner.accept);
			testInstance.pass("Clicked on Accept");
			utilities.passsnaps(driver);
		}
		else
		{
			testInstance.fail("Accept button missing");
			utilities.failsnaps(driver);
			//Assert.fail("Accept button missing");
		}if(sharefunctions.isXpathExists(orderowner.submit))
		{
			sharefunctions.click(orderowner.submit);
			testInstance.pass("Clicked on Submit");
			utilities.passsnaps(driver);
		}
		else
		{
			testInstance.fail("Submit button missing");
			utilities.failsnaps(driver);
			//Assert.fail("Submit button missing");
		}
		String expected = "News";
		Thread.sleep(2000);
		if(driver.getTitle().equals(expected))
		{
			testInstance.pass("Reource and position approved");
			utilities.passsnaps(driver);
		}
		else
		{
			utilities.failsnaps(driver);
			testInstance.fail("Not able to approve");
			Assert.fail("Not able to approve");
		}
	}
	@Test(enabled=true,priority = 4)
	public void logout() throws Exception
	{
		sharefunctions.click(headers.logo);
		Thread.sleep(2000);
		sharefunctions.click(headers.logout);
	}
}
