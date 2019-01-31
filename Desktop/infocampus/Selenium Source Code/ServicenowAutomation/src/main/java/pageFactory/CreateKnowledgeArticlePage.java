package pageFactory;

import java.util.LinkedHashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import functionLibrary.sharedFunctions;
import utilities.ExcelUtility;

public class CreateKnowledgeArticlePage extends sharedFunctions{
	
	private WebDriver driver;
	private ExtentTest test;
	private LinkedHashMap<String, String> dataMap;
	
	@FindBy(xpath="//input[@id='sys_display.kb_knowledge.kb_knowledge_base']")
	private WebElement knowledgeBaseField;
	
	@FindBy(xpath="//input[@id='kb_knowledge.short_description']")
	private WebElement shortDescriptionField;
	
	@FindBy(xpath="//button[@id='sysverb_insert']")
	private WebElement submitButton;
	
	@FindBy(xpath="//input[@id='sys_readonly.kb_knowledge.number']")
	private WebElement knowledgeNumber;
	
//	@FindBy(xpath="")
//	private WebElement ele;
//	@FindBy(xpath="")
//	private WebElement ele;
	
	public String createNewKnowledgeArticle() {
		dataMap = new ExcelUtility().getTestData("KnowledgeArticle");
		driver.switchTo().frame("gsft_main");
		waitTime();
		String knowledgeNum = knowledgeNumber.getAttribute("value");
		test.info("Generated knowledge article number is "+knowledgeNum);
		test.info("Entering value in knowledge base field.");
		knowledgeBaseField.sendKeys(dataMap.get("Knowledge base"));
		waitTime();
		new Actions(driver).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
		test.info("Entering value in short description field.");
		shortDescriptionField.sendKeys(dataMap.get("Short description"));
		submitButton.click();
		test.pass("Submitted new knowledge article successfully.");
		driver.switchTo().defaultContent();
		return knowledgeNum;
	}
	
	public void waitTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public CreateKnowledgeArticlePage(WebDriver driver, ExtentTest testInstance) {
		this.setDriver(driver);
		this.test = testInstance;
		PageFactory.initElements(driver, this);
	}
}
