package jj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddNewPosition {
	
	public WebDriver driver;
	
	public By position = By.xpath("//span[contains(text(),'Position Title')]/../following-sibling::div//div[@role='listbox']");
	public By language = By.xpath("//span[contains(text(),'Language')]/../following-sibling::div//div[@role='listbox']");
	public By experience = By.xpath("//span[contains(text(),'Experience')]/../following-sibling::div//div[@role='listbox']");
	public By datefrom = By.xpath("(//input[@type='text'])[1]");
	public By dateto = By.xpath("(//input[@type='text'])[2]");
	public By sponsorwwid = By.xpath("(//input[@type='text'])[3]");
	public By facility = By.xpath("//span[contains(text(),'Facility')]/../following-sibling::div//div[@role='listbox']");
	public By region = By.xpath("//span[contains(text(),'Region')]/../following-sibling::div//div[@role='listbox']");
	public By country = By.xpath("//span[contains(text(),'Country')]/../following-sibling::div//div[@role='listbox']");
	public By state = By.xpath("//span[contains(text(),'State')]/../following-sibling::div//div[@role='listbox']");
	public By city = By.xpath("//span[contains(text(),'City')]/../following-sibling::div//div[@role='listbox']");
	public By role = By.xpath("//span[contains(text(),'Role')]/../following-sibling::div//div[@role='listbox']");
	public By technology = By.xpath("//span[contains(text(),'Technology')]/../following-sibling::div//div[@role='listbox']");
	public By skill = By.xpath("//span[contains(text(),'Skill')]/../following-sibling::div//div[@role='listbox']");
	public By services = By.xpath("//span[contains(text(),'Service')]/../following-sibling::div//div[@role='listbox']");
	public By offering = By.xpath("//span[contains(text(),'Offering')]/../following-sibling::div//div[@role='listbox']");
	public By skilllevel = By.xpath("//span[contains(text(),'Skill Level')]/../following-sibling::div//div[@role='listbox']");
	public By save_complete_submit = By.xpath("//*[@class='Button---btn Button---default_direction Button---primary appian-context-first-in-list appian-context-last-in-list']");
	public By searchicon = By.xpath("//*[@class='DocumentImage---image_link elements---global_a']/img");
	public By alert = By.xpath("//div[@class='FieldLayout---field_error']");
	
	public AddNewPosition(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public void sponsor(String wwid) throws InterruptedException 
	{
		driver.findElement(sponsorwwid).sendKeys(wwid);
		Thread.sleep(5000);
		driver.findElement(searchicon).click();
		Thread.sleep(10000);
	}
	

}
