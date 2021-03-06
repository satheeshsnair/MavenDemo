package regression;


import org.testng.annotations.Test;

import pages.AddNewPosition;
import pages.Headers;
import pages.Login;
import pages.ResourcesPage;
import reusableLibrary.DriverFactory;
import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;

public class TC_AddNewResource extends DriverFactory 
{
	
	Login login; //Initialize class
	Headers headers;
	Utilites utilities;
	AddNewPosition newposition;
	WebDriverUtilities sharefunctions;
	ResourcesPage resource;
	
	@Test(enabled=true,priority=0)
	public void loginasvendor() throws Exception
	{
		initializeUserStoryInReport("Login");
		login = new Login(driver,testInstance); //initialize objectl
		resource = new ResourcesPage(driver, testInstance);
		login.username("TC_AddNewResource", "Uname");
		login.password("TC_AddNewResource", "Password");
		login.login();
		
	}
	@Test(enabled=true,priority=1)
	public void clickontask() throws Exception
	{
		initializeUserStoryInReport("Click on task");
		headers = new Headers(driver, testInstance);
		utilities = new Utilites(driver,testInstance);
		sharefunctions = new WebDriverUtilities(driver,testInstance);
		
		resource.clickontasktab(headers.Tasktab);
	}
	@Test(enabled=true,priority = 2)
	public void clickonresource() throws Exception
	{
		initializeUserStoryInReport("Select Resource task");
		resource.clickonresource();
	}
	@Test(enabled=true,priority = 3)
	public void addnewresource() throws Exception
	{
		initializeUserStoryInReport("Click on Add Resource");
		resource.clickaddresourcebtn();
	}
	@Test(enabled =true, priority=4)
	public void enterresourcedetails() throws Exception
	{
		initializeUserStoryInReport("Enter resource details and save");
		resource.firstname();
		resource.lastname();
		resource.email();
		resource.mobilecode();
		resource.mobilenmbr();
		resource.facility();
		resource.addresstype();
		resource.addressline();
		resource.region();
		resource.country();
		resource.state();
		resource.city();
		resource.postalcode();
		resource.citizenship();
		resource.explevel();
		resource.addnewskill();
		resource.Technology();
		resource.skill();
		resource.experience();
		resource.isprimary();
		resource.upload("C:\\Users\\satheeshnair\\Desktop\\Dummy1.docx");
		resource.empID("TC_AddNewResource", "empid");
		resource.addlang();
		resource.lang();
		resource.isprimarylang();
	}
	@Test(enabled=true,priority=5)
	public void submitresource() throws Exception
	{
		resource.next();
		resource.submit();
		resource.alertyes();
	}
	@Test(enabled=true,priority = 6)
	public void logout() throws InterruptedException
	{
		sharefunctions.click(headers.logo);
		Thread.sleep(2000);
		sharefunctions.click(headers.logout);
	}
}
