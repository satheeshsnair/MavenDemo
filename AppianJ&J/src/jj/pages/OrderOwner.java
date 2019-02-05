package jj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderOwner {
	
	public WebDriver driver;
	
	public By submit = By.xpath("//button[@class='Button---btn Button---default_direction Button---primary appian-context-last-in-list']");
	public By position_ID = By.xpath("(//a[@class='elements---global_a'])[1]");
	public By accept = By.xpath("//button[@class='Button---btn Button---default_direction Button---primary']");
	public By reject = By.xpath("//button[@class='Button---btn Button---default_direction Button---destructive appian-context-first-in-list']");
	public By close = By.xpath("//button[@class='Button---btn Button---default_direction appian-context-last-in-list']");
	public By comment = By.xpath("//textarea[@class='ParagraphWidget---textarea ParagraphWidget---align_start']");
	
	public OrderOwner(WebDriver driver)
	{
		this.driver=driver;
		//this.test = test;
	}
}
