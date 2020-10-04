package test.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

public class CheckBoxPage extends BasePage{

	@FindBy(css=".rct-checkbox")
	private WebElement checkBoxHome;
	
	@FindBy(css=".rct-options>[title='Expand all']")
	private WebElement expand;
	
	@FindBy(css=".rct-options>[title='Collapse all']")
	private WebElement collapse;
	
	@FindBy(css=".rct-text>[type='button']")
	private WebElement openHome;
	
	
	private String outputAfterCheckExpand,outputAfterCheckCollapse;
	
	
	public CheckBoxPage(WebDriver driver) {
		super(driver);
	}
	
	public void expandAll() {
		click(expand);
	}
	
	public void collapseAll() {
		click(collapse);
	}
	
	public void openHomeArrow() {
		click(openHome);
	}
	
	public void doCheck() {
		click(checkBoxHome);
	}
	
	public boolean checkBoxOutputAfterCheckExpand() {
		outputAfterCheckExpand=driver.findElements(By.cssSelector("#result>span")).get(0).getText();
		int numOfResult = driver.findElements(By.cssSelector("#result>span")).size();
		if(outputAfterCheckExpand.contains("selected") && numOfResult > 10)
		{
			Log.info("output after check box expand - passed");
			return true;
		}
		else
		{
			Log.info("output after check box expand - failed");
			return false;
		}
	}
	
	public boolean checkBoxOutputAfterCheckCollapse() {
		outputAfterCheckCollapse=driver.findElements(By.cssSelector(".rct-node>ol>li")).get(0).getText();
		int numOfResult = driver.findElements(By.cssSelector(".rct-node>ol>li")).size();
		if(outputAfterCheckCollapse.equalsIgnoreCase("Desktop") && numOfResult == 3)
		{
			Log.info("output after check box collapse - passed");
			return true;
		}
		else
		{
			Log.info("output after check box collapse - failed");
			return false;
		}
	}
	
}

