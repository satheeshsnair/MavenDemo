package regression;


import org.testng.annotations.Test;

import pages.AddNewPosition;
import pages.Headers;
import pages.Login;
import reusableLibrary.DriverFactory;
import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;

public class TC_AddNewPosition extends DriverFactory 
{
	Login login; //Initialize class
	Headers headers;
	Utilites utilities;
	AddNewPosition newposition;
	WebDriverUtilities sharefunctions;
	
	@Test(enabled=true,priority=0)
	public void testcase() throws Exception
	{
		initializeExtentReport("Login");
		login = new Login(driver,testInstance); //initialize objectl
		newposition = new AddNewPosition(driver,testInstance);
		login.username(0,1,0); //sheet num, row , column
		login.password(0,1,1); //sheet num, row , column
		login.login();
		newposition.checkheadernews();
	}
	@Test(enabled=true,priority=1)
	public void header() throws Exception
	{
		initializeExtentReport("Click Task tab");
		headers = new Headers(driver,testInstance);
		utilities = new Utilites(driver,testInstance);
		sharefunctions = new WebDriverUtilities(driver,testInstance);
		
		newposition.clickheadertask(headers.Tasktab);
	}
	@Test(enabled=true,priority = 2)
	public void clickondemand() throws Exception
	{
		initializeExtentReport("Click on Demand");
		newposition.clickondemand();
	}
	@Test(enabled=true,priority = 3)
	public void addnewposition() throws Exception
	{
		initializeExtentReport("Click Add position button");
		newposition.clickaddpositionbtn();
	}
	@Test(enabled=true,priority = 4)
	public void addposition() throws Exception
	{
		initializeExtentReport("Enter Position Details");
		newposition.Selectposition();
		newposition.Selectexperience();
		newposition.Selectlanguage();
		utilities.date_excel(1, 1, 1, newposition.datefrom); //Date from: read from excel
		utilities.date_excel(1, 1, 2, newposition.dateto); //Date to: Read from excel
		newposition.Selectsponsor("374774");
		newposition.Selectfacility();
		newposition.Selectregion();
		newposition.Selectcountry();
		newposition.Selectstate();
		newposition.Selectcity();
		newposition.Selectrole();
		newposition.Selecttechnology();
		newposition.Selectskill();
		newposition.Selectservices();
		newposition.Selectoffering();
		newposition.Selectskilllevel();
		newposition.clickonsave();
		//newposition.alert();
	}
	@Test(enabled=true,priority = 5)
	public void submitposition() throws Exception
	{
		initializeExtentReport("Submit Position");
		newposition.clicksubmitbutton();
	}
	@Test(enabled=true,priority = 6)
	public void logout() throws InterruptedException
	{
		newposition.logout();
	}
}