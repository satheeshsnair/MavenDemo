package jj.test;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.awt.AWTException;

import org.testng.annotations.Test;

import jj.pages.AddNewPosition;
import jj.pages.Headers;
import jj.pages.Login;
import jj.pages.ResourcesPage;
import utilities.SharedFunctions;
import utilities.Utilites;

public class TC_AddNewResource extends TestBase 
{
	
	Login login; //Initialize class
	Headers headers;
	Utilites utilities;
	AddNewPosition newposition;
	SharedFunctions sharefunctions;
	ResourcesPage resource;
	
	@Test(enabled=true,priority=0)
	public void loginasvendor() throws Exception
	{
		initializeReport();
		driver.get(url);
		login = new Login(driver,test); //initialize objectl
		login.username(0,1,0); //sheet num, row , column
		login.password(0,1,1); //sheet num, row , column
		login.login();
		String expected = "News";
		Thread.sleep(3000);
		if(driver.getTitle().equals(expected))
		{
			utilities = new Utilites(driver,test);
			utilities.passsnaps(driver);
			utilities.WriteExcel(0, 1, 2, "Pass");
		}
		else
		{
			utilities = new Utilites(driver,test);
			utilities.failsnaps(driver);
			utilities.WriteExcel(0, 1, 2, "Fail");
		}
	}
	
	@Test(enabled=true,priority=1)
	public void clickontask() throws Exception
	{
		initializeReport();
		headers = new Headers(driver);
		utilities = new Utilites(driver,test);
		sharefunctions = new SharedFunctions(driver,test);
		sharefunctions.click(headers.Tasktab);
		
		Thread.sleep(4000);
		String expected = "Tasks";
		Thread.sleep(3000);
		if(driver.getTitle().equals(expected))
		{
			utilities = new Utilites(driver,test);
			utilities.passsnaps(driver);
		}
		else
		{
			utilities = new Utilites(driver,test);
			utilities.failsnaps(driver);
		}
	}
	@Test(enabled=true,priority = 2)
	public void clickonresource() throws Exception
	{
		initializeReport();
		String sow = utilities.SOW(1,1,3);
		//String sow = utilities.readexcel(sheetnum, row, column);
		if(sharefunctions.isXpathExists(headers.provideresource)) {
			String task = driver.findElement(headers.provideresource).getText();
			if(task.contains(sow))
			{
				sharefunctions.click(headers.provideresource);
				String expected = driver.getTitle();
				Thread.sleep(3000);
				if(expected.contains("Provide demand"))
				{
					utilities.passsnaps(driver);
					test.pass("Clicked on demand" + sow);
				}
			}
		}else
		{
			Assert.fail("no such demand");
			utilities.failsnaps(driver);
		}
	}
	
	@Test(enabled=true,priority = 3)
	public void addnewresource() throws Exception
	{
		initializeReport();
		headers = new Headers(driver);
		utilities = new Utilites(driver,test);
		resource = new ResourcesPage(driver);
		if(sharefunctions.isXpathExists(headers.acceptbtn))
		{
			sharefunctions.click(headers.acceptbtn);
			Thread.sleep(2000);
		}
		sharefunctions.click(resource.position);
		Thread.sleep(3000);
		String expected = driver.getTitle();
		Thread.sleep(3000);
		if(expected.contains("Provide Resource"))
		{
			utilities.passsnaps(driver);
			test.pass("Clicked on Add position button");
		}
		else
		{
			utilities.failsnaps(driver);
			Assert.fail("Failed to Click on Add position button");
		}
	}
	@Test(enabled =true, priority=4)
	public void enterresourcedetails() throws InterruptedException, AWTException
	{
		initializeReport();
		sharefunctions.sendkey(resource.Firstname, "Test first name");
		sharefunctions.sendkey(resource.Lastname, "Test Last name");
		sharefunctions.sendkey(resource.email, "t@t.com");
		sharefunctions.sendkey(resource.Mobilecountry, "123");
		sharefunctions.sendkey(resource.Mobilenmbr, "0987654321");
		sharefunctions.dropdown(resource.facility);
		sharefunctions.dropdown(resource.Addresstype);
		sharefunctions.sendkey(resource.Addressline1, "Test Address");
		sharefunctions.dropdown(resource.Region);
		sharefunctions.dropdown(resource.country);
		sharefunctions.dropdown(resource.State);
		sharefunctions.dropdown(resource.City);
		sharefunctions.sendkey(resource.postalcode, "123123");
		sharefunctions.sendkey(resource.citizenship, "Indian");
		sharefunctions.dropdown(resource.Experiencelevel);
		sharefunctions.click(resource.addnewskill);
		sharefunctions.dropdown(resource.technology);
		sharefunctions.dropdown(resource.skill);
		sharefunctions.dropdown(resource.Experience);
		sharefunctions.click(resource.isprimary);
		resource.upload("C:\\Users\\satheeshnair\\Desktop\\Dummy1.docx");
		sharefunctions.sendkey(resource.empID, "0000069");
		sharefunctions.click(resource.Addlanguage);
		sharefunctions.dropdown(resource.language);
		sharefunctions.click(resource.isprimarylang);
		sharefunctions.click(resource.next_Submit);
		sharefunctions.click(resource.next_Submit);
		sharefunctions.click(resource.alert_yes);
	}
	@Test(enabled=true,priority = 5)
	public void logout() throws InterruptedException
	{
		sharefunctions.click(headers.logo);
		Thread.sleep(2000);
		sharefunctions.click(headers.logout);
	}
}
