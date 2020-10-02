package pageobjects;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;
import utils.Utils;

public class ButtonsPage extends BasePage{

	private WebElement doubleClickMsg, rightClickMsg, dynamicClickMsg;
	
	@FindBy(css="#doubleClickBtn")
	private WebElement doubleClickBtn;	
	
	@FindBy(css="#rightClickBtn")
	private WebElement rightClickBtn;
	
	@FindBy(css=".col-md-6>div>div:nth-child(3)>button")
	private WebElement clickMe;
	
	public ButtonsPage(WebDriver driver) {
		super(driver);
	}
	
	//Actions actions = new Actions(driver);
	
	public boolean clickDoubleClickBtn()
	{
		Actions actions = new Actions(driver);
		actions.doubleClick(doubleClickBtn).perform();
		sleep(4000);
		doubleClickMsg = driver.findElement(By.cssSelector(".col-md-6>div>p"));
 	    if(doubleClickMsg.getText().equalsIgnoreCase("You have done a double click"))
 	    {
 	    	Log.info("double click message - pass");
 	    	return true;
 	    }
 	    else
 	    {
 	    	Log.info("double click message - failed");
 	    	return false;
 	    }
	}
	
	public boolean rightClickBtn()
	{
		Actions actions = new Actions(driver);
		actions.contextClick(rightClickBtn).perform();	
		sleep(4000);
		rightClickMsg = driver.findElement(By.cssSelector(".col-md-6>div>#rightClickMessage"));
		
		 if(rightClickMsg.getText().equalsIgnoreCase("You have done a right click"))
		 {
	 	    	Log.info("right click message - pass");
	 	    	return true;
	 	 }
	    else
	    {
	 	    	Log.info("right click message - failed");
	 	    	return false;
 	    }
	}
	
	public boolean clickMeBtn()
	{
		//Actions actions = new Actions(driver);
		click(clickMe);
		dynamicClickMsg = driver.findElement(By.cssSelector(".col-md-6>div>#dynamicClickMessage"));
		sleep(4000);
  		if(dynamicClickMsg.getText().equalsIgnoreCase("You have done a dynamic click"))
  		 {
	 	    	Log.info("dynamic click message - pass");
	 	    	return true;
  		 }
	     else
	     {
	 	    	Log.info("dynamic click message - failed");
	 	    	return false;
	     }
	}

		
}

