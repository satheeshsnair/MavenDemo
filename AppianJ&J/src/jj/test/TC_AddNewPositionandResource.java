package jj.test;

import org.testng.annotations.Test;

import jj.pages.AddNewPosition;
import jj.pages.Headers;
import jj.pages.Login;
import utilities.SharedFunctions;
import utilities.Utilites;

public class TC_AddNewPositionandResource extends TestBase 
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
		}
	}
	
	@Test(enabled=true,priority = 3)
	public void addnewposition() throws Exception
	{
		initializeReport();
		headers = new Headers(driver);
		utilities = new Utilites(driver,test);
		//String acceptbtn = driver.findElement(headers.acceptbtn).getText();
		if(sharefunctions.isXpathExists(headers.acceptbtn))
		{
			sharefunctions.click(headers.acceptbtn);
			Thread.sleep(2000);
		}
		sharefunctions.click(headers.addnewposition);
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
		}
	}
	@Test(enabled=true,priority = 4)
	public void addposition() throws Exception
	{
		initializeReport();
		utilities = new Utilites(driver, test);
		newposition = new AddNewPosition(driver);
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
		if(sharefunctions.isXpathExists(newposition.alert))
		{
			System.out.println("fail");
			utilities.failsnaps(driver);
			test.pass("Missed some values");
		}
		else
		{
			System.out.println("Pass");
			utilities.passsnaps(driver);
			test.pass("Entered all data successfully!!!");
		}
	}
	@Test(enabled=false,priority = 5)
	public void submitposition() throws InterruptedException
	{
		sharefunctions.click(newposition.save_complete_submit);
		test.pass("Posiiton submiited");
	}
	@Test(enabled=false,priority = 6)
	public void logout() throws InterruptedException
	{
		sharefunctions.click(headers.logo);
		sharefunctions.click(headers.logout);
		test.pass("Posiiton submiited");
	}
	@Test(enabled=false,priority = 7)
	public void loginasvendor() throws Exception
	{
		Thread.sleep(4000);
		login = new Login(driver,test); //initialize objectl
		login.username(0,1,0); //sheet num, row , column
		login.password(0,1,1); //sheet num, row , column
		login.login();
		Thread.sleep(4000);
		String expected = "News";
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
	@Test(enabled=false,priority=8)
	public void clickontask() throws Exception
	{
		sharefunctions.click(headers.Tasktab);
		
		Thread.sleep(4000);
		String expected = "Tasks";
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
	@Test(enabled=false,priority = 9)
	public void clickonresource() throws Exception
	{
		//headers = new Headers(driver);
		//utilities = new Utilites(driver);
		Thread.sleep(4000);
		String task = driver.findElement(headers.providedemandtask).getText();
		int demandID = utilities.demandid(0,1,3);
		String demand = Integer.toString(demandID);	 
		if(task.contains(demand))
		{
			sharefunctions.click(headers.providedemandtask);
		}
	else
	{
		System.out.println("no such demand");
	}
		String expected = driver.getTitle();
		if(expected.contains("Provide demand"))
		{
			utilities.passsnaps(driver);
		}
		else
		{
			utilities.failsnaps(driver);
		}
	}
}
