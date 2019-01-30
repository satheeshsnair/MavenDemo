package jj.pages;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.Utilites;


public class Headers
{
	public WebDriver driver;
	Utilites utils = new Utilites(driver);
	
	/*************Header Locator**************/
	public By Actiontab = By.xpath("(//*[@class='appian-menu-item'])[4]");
	public By Tasktab = By.xpath("(//*[@class='appian-menu-item'])[1]");
	public By Records = By.xpath("(//*[@class='appian-menu-item'])[2]");
	public By Reportstab = By.xpath("(//*[@class='appian-menu-item'])[3]");
	public By logo = By.xpath("//a[@class='gwt-Anchor pull-down-toggle']/span");
	public By logout = By.xpath("(//a[@class='gwt-Anchor pull-down-menu-item']/span)[3]");
	
	/*********Task Locator***************/
	public By firsttask = By.xpath("(//*[@class='gwt-Anchor appian-feed-entry-author'])[1]");
	public By acceptbtn = By.xpath("//button[text()='Accept']");
	//public By firstprovidedemandtask = By.xpath("(//*[contains(text(),'Provide')])[1]");
	int demandid = utils.demandid(1, 1, 0);
	public By providedemandtask = By.xpath("//a[contains(text(),'" + demandid + "')]");
	public By addnewposition = By.xpath("(//*[@class='LinkedItem---richtext_link elements---global_a'])[4]");
	
	public Headers(WebDriver driver)
	{
		this.driver=driver;
	}
}	
	