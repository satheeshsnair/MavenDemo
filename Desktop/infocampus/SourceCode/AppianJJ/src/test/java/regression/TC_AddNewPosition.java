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
	
	public void initializeClasses() {
		login = new Login(driver,testInstance); //initialize objectl
		newposition = new AddNewPosition(driver,testInstance);
		newposition = new AddNewPosition(driver,testInstance);
		headers = new Headers(driver,testInstance);
		utilities = new Utilites(driver,testInstance);
		sharefunctions = new WebDriverUtilities(driver,testInstance);
	}
	
	@Test(enabled=true,priority=0)
	public void testcase() throws Exception
	{
		initializeUserStoryInReport("Login");
		initializeClasses();
		login.username("TC_AddNewResource", "Uname");
		login.password("TC_AddNewResource", "Password");
		login.login();
		newposition.checkheadernews();
		
	}
	@Test(enabled=true,priority=1)
	public void header() throws Exception
	{
		initializeUserStoryInReport("Click Task tab");
		initializeClasses();
		newposition.clickheadertask(headers.Tasktab);
	}
	@Test(enabled=true,priority = 2)
	public void clickondemand() throws Exception
	{
		initializeUserStoryInReport("Click on Demand");
		initializeClasses();
		newposition.clickondemand();
	}
	@Test(enabled=true,priority = 3)
	public void addnewposition() throws Exception
	{
		initializeUserStoryInReport("Click Add position button");
		initializeClasses();
		newposition.clickaddpositionbtn();
	}
	@Test(enabled=true,priority = 4)
	public void addposition() throws Exception
	{
		initializeUserStoryInReport("Enter Position Details");
		initializeClasses();
		newposition.Selectposition();
		newposition.Selectexperience();
		newposition.Selectlanguage();
		newposition.date_from("TC_AddNewPosition", "date_from", newposition.datefrom);
		newposition.date_from("TC_AddNewPosition", "date_to", newposition.dateto);
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
		initializeUserStoryInReport("Submit Position");
		initializeClasses();
		newposition.clicksubmitbutton();
	}
	@Test(enabled=true,priority = 6)
	public void logout() throws InterruptedException
	{
		newposition.logout();
	}
}