package jj.test;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import jj.pages.AddNewPosition;
import jj.pages.Headers;
import jj.pages.Login;
import utilities.Utilites;

public class TC_AddNewPositionandResource extends TestBase {
	Login login; //Initialize class
	Headers headers;
	Utilites utilities;
	AddNewPosition newposition;
	
	@Test(enabled=true,priority=0)
	public void testcase() throws Exception
	{
		initializeReport();
		driver.get(url);
		//utilities = new Utilites(driver);
		//Thread.sleep(2000);
		login = new Login(driver,test); //initialize objectl
		login.username(0,1,0); //sheet num, row , column
		login.password(0,1,1); //sheet num, row , column
		login.login();
		String expected = "News";
		if(driver.getTitle().equals(expected))
		{
			utilities = new Utilites(driver);
			utilities.passsnaps(driver);
			utilities.WriteExcel(0, 1, 2, "Pass");
		}
		else
		{
			utilities = new Utilites(driver);
			utilities.failsnaps(driver);
			utilities.WriteExcel(0, 1, 2, "Fail");
		}
	}

	@Test(enabled=true,priority=1)
	public void header() throws Exception
	{
		initializeReport();
		headers = new Headers(driver);
		utilities = new Utilites(driver);
		utilities.click(headers.Tasktab);
		
		String expected = "Tasks";
		if(driver.getTitle().equals(expected))
		{
			utilities = new Utilites(driver);
			utilities.passsnaps(driver);
		}
		else
		{
			utilities = new Utilites(driver);
			utilities.failsnaps(driver);
		}
	}
	
	@Test(enabled=true,priority = 2)
	public void clickondemand() throws Exception
	{
		int demandID = utilities.demandid(0,1,3);// need to capture data from excel sheet once ASOE flow is completed
		String demand = Integer.toString(demandID);		
		if(utilities.isXpathExists(headers.providedemandtask)) {
			String task = driver.findElement(headers.providedemandtask).getText();
			if(task.contains(demand))
			{
				utilities.click(headers.providedemandtask);
			}
		}else
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
	
	@Test(enabled=true,priority = 3)
	public void addnewposition() throws Exception
	{
		headers = new Headers(driver);
		utilities = new Utilites(driver);
		//String acceptbtn = driver.findElement(headers.acceptbtn).getText();
		if(utilities.isXpathExists(headers.acceptbtn))
		{
			utilities.click(headers.acceptbtn);
			Thread.sleep(2000);
		}
		utilities.click(headers.addnewposition);
		Thread.sleep(4000);
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
	@Test(enabled=true,priority = 4)
	public void addposition() throws Exception
	{
		utilities = new Utilites(driver);
		newposition = new AddNewPosition(driver);
		utilities.dropdown(newposition.position);
		utilities.dropdown(newposition.experience);
		utilities.dropdown(newposition.language);
		utilities.date(newposition.datefrom, 7); // locator, number of days to be added to current date
		utilities.date(newposition.dateto, 30); // locator, number of days to be added to current date
		newposition.sponsor("374774");
		utilities.dropdown(newposition.facility);
		utilities.dropdown(newposition.region);
		utilities.dropdown(newposition.country);
		utilities.state(newposition.state);
		utilities.dropdown(newposition.city);
		utilities.dropdown(newposition.role);
		utilities.dropdown(newposition.technology);
		utilities.dropdown(newposition.skill);
		utilities.dropdown(newposition.services);
		utilities.dropdown(newposition.offering);
		utilities.dropdown(newposition.skilllevel);
		utilities.click(newposition.save_complete_submit);
		WebElement error = driver.findElement(newposition.alert);
		String alert = error.getText();
		if(alert.isEmpty())
		{
			System.out.println("Pass");
			utilities.passsnaps(driver);
		}
		else
		{
			System.out.println("fail");
			//Assert.assertTrue(alert.contains("Please select the Services"));
			utilities.failsnaps(driver);
		}
	}
	@Test(enabled=false,priority = 5)
	public void submitposition() throws InterruptedException
	{
		utilities.click(newposition.save_complete_submit);
	}
	@Test(enabled=false,priority = 6)
	public void logout() throws InterruptedException
	{
		utilities.click(headers.logo);
		utilities.click(headers.logout);
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
			utilities = new Utilites(driver);
			utilities.passsnaps(driver);
			utilities.WriteExcel(0, 1, 2, "Pass");
		}
		else
		{
			utilities = new Utilites(driver);
			utilities.failsnaps(driver);
			utilities.WriteExcel(0, 1, 2, "Fail");
		}
	}
	@Test(enabled=false,priority=8)
	public void clickontask() throws Exception
	{
		headers = new Headers(driver);
		utilities = new Utilites(driver);
		utilities.click(headers.Tasktab);
		
		Thread.sleep(4000);
		String expected = "Tasks";
		if(driver.getTitle().equals(expected))
		{
			utilities = new Utilites(driver);
			utilities.passsnaps(driver);
		}
		else
		{
			utilities = new Utilites(driver);
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
		String demandID = "248"; // need to capture data from excel sheet once ASOE flow is completed
		for(int i=0;i<20;i++)
		{
			if(task.contains(demandID))
			{
				utilities.click(headers.providedemandtask);
				break;
			}
			else
			{
				Thread.sleep(4000);
				driver.navigate().refresh();
			}
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
