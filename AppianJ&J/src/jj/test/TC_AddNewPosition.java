package jj.test;


import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import org.testng.Assert;

import jj.pages.AddNewPosition;
import jj.pages.Headers;
import jj.pages.Login;
import utilities.SharedFunctions;
import utilities.Utilites;

public class TC_AddNewPosition extends TestBase 
{
	Login login; //Initialize class
	Headers headers;
	Utilites utilities;
	AddNewPosition newposition;
	SharedFunctions sharefunctions;
	
	@Test(enabled=true,priority=0)
	public void testcase() throws Exception
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
	public void header() throws Exception
	{
		initializeReport();
		headers = new Headers(driver);
		utilities = new Utilites(driver,test);
		sharefunctions = new SharedFunctions(driver,test);
		
		sharefunctions.click(headers.Tasktab);
		
		String expected = "Tasks";
		Thread.sleep(3000);
		if(driver.getTitle().equals(expected))
		{
			utilities = new Utilites(driver,test);
			test.pass("Clicked on Task Tab");
			utilities.passsnaps(driver);
		}
		else
		{
			utilities = new Utilites(driver,test);
			test.fail("Task Tab is missing");
			utilities.failsnaps(driver);
		}
	}
	
	@Test(enabled=true,priority = 2)
	public void clickondemand() throws Exception
	{
		initializeReport();
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
	
	@Test(enabled=true,priority = 3)
	public void addnewposition() throws Exception
	{
		initializeReport();
		headers = new Headers(driver);
		utilities = new Utilites(driver,test);
		if(sharefunctions.isXpathExists(headers.acceptbtn))
		{
			sharefunctions.click(headers.acceptbtn);
			test.pass("Clicked on Add position button");
			Thread.sleep(2000);
		}
		try {
		sharefunctions.click(headers.addnewposition);
		}catch (Exception e) {
			test.fail("Add New position button missing");
			Assert.fail("Failed to Click on Add position button");
			utilities.failsnaps(driver);
		}
		Thread.sleep(3000);
		String expected = driver.getTitle();
		Thread.sleep(3000);
		if(expected.contains("Provide demand"))
		{
			utilities.passsnaps(driver);
			test.pass("Clicked on Add position button");
		}
		else
		{
			utilities.failsnaps(driver);
			test.fail("Failed to Click on Add position button");
			Assert.fail("Failed to Click on Add position button");
		}
	}
	@Test(enabled=true,priority = 4)
	public void addposition() throws Exception
	{
		initializeReport();
		utilities = new Utilites(driver, test);
		newposition = new AddNewPosition(driver);
		try {
		sharefunctions.dropdown(newposition.position);
		sharefunctions.dropdown(newposition.experience);
		sharefunctions.dropdown(newposition.language);
		utilities.date_excel(1, 1, 1, newposition.datefrom); //Date from: read from excel
		utilities.date_excel(1, 1, 2, newposition.dateto); //Date to: Read from excel
		//sharefunctions.date(newposition.datefrom, 7); // add 7 days from current date
		//sharefunctions.date(newposition.dateto, 30); // add 30 days from current date
		newposition.sponsor("374774");
		sharefunctions.dropdown(newposition.facility);
		sharefunctions.dropdown(newposition.region);
		sharefunctions.dropdown(newposition.country);
		sharefunctions.dropdown(newposition.state);
		sharefunctions.dropdown(newposition.city);
		sharefunctions.dropdown(newposition.role);
		sharefunctions.dropdown(newposition.technology);
		sharefunctions.dropdown(newposition.skill);
		sharefunctions.dropdown(newposition.services);
		sharefunctions.dropdown(newposition.offering);
		sharefunctions.dropdown(newposition.skilllevel);
		sharefunctions.click(newposition.save_complete_submit);
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to complete position details");
			Assert.fail("Not able to complete position details");
			assertTrue(false);
		}
		if(sharefunctions.isXpathExists(newposition.alert))
		{
			System.out.println("fail");
			utilities.failsnaps(driver);
			Assert.fail("Missed some values");
		}
		else
		{
			System.out.println("Pass");
			utilities.passsnaps(driver);
			test.pass("Entered all data successfully!!!");
		}
	}
	@Test(enabled=true,priority = 5)
	public void submitposition() throws Exception
	{
		initializeReport();
		try {
		sharefunctions.click(newposition.save_complete_submit);
		utilities.passsnaps(driver);
		test.pass("Clicked on Submit button");
		
		}
		catch (Exception e) {
			test.fail("Submit button missing");
			utilities.failsnaps(driver);
			Assert.fail("Submit button missing");
		}
		try {
		if(sharefunctions.isXpathExists(newposition.submit_form_yes))
		{
			sharefunctions.click(newposition.submit_form_yes);
			utilities.passsnaps(driver);
			test.pass("Posiiton submiited");
		}
		}catch (Exception e) {
			test.fail("Posiiton not submiited");
			utilities.failsnaps(driver);
			Assert.fail("Posiiton not submiited");
		}
	}
	@Test(enabled=true,priority = 6)
	public void logout() throws InterruptedException
	{
		sharefunctions.click(headers.logo);
		Thread.sleep(2000);
		sharefunctions.click(headers.logout);
	}
}
	