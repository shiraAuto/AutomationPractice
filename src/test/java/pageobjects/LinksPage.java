package pageobjects;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
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

public class LinksPage extends BasePage{

	//private WebElement doubleClickMsg, rightClickMsg, dynamicClickMsg;
	
	@FindBy(css="#linkWrapper")
	private WebElement linkOpenNewTabTitle;	
	
	@FindBy(css="#linkWrapper")
	private WebElement sendApiTestTitle;	
	
	String linkResponse="",linkResponseStatus="",linkResponseText="";
	//private WebElement linkResponse,linkResponseStatus,linkResponseText;
	JavascriptExecutor js = (JavascriptExecutor) driver;	
	
	public LinksPage(WebDriver driver) {
		super(driver);
	}
	

	public boolean clickLinkBtn (String linkText)
	{			
		int i=0 , linkNumber=0;
		// This  will scroll to the top of the page in case always	
        js.executeScript("window.scrollBy(0,0)");
		
        List<WebElement> links = driver.findElements(By.cssSelector("#linkWrapper>p>a"));
		sleep(5000);
		for ( i = 0; i < links.size(); i++) 
		{
			if(i > 3)
			{
				
		        // This  will scroll down the page by  120 pixel vertical		
		        js.executeScript("window.scrollBy(0,120)");
			}
			
			if(links.get(i).getText().contentEquals(linkText))			
			{
				click(links.get(i));
				Log.info("click link " + linkText + "- pass");
				break;
			
			}			
		}
		
	    linkNumber=i+1;
		Log.info("link number: " + linkNumber );
		
		
		
		if(linkNumber < 3)
		{
			//get window handlers as list
			List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
			//switch to new tab
			driver.switchTo().window(browserTabs .get(1));
			String currentUrl = driver.getCurrentUrl();
			if(currentUrl.contentEquals("https://demoqa.com/"))
			{
		  	  //then close tab and get back
				Log.info("open new tab - pass");
				driver.close();
		       driver.switchTo().window(browserTabs.get(0));			 
		       return true;
			}
			else
			{
				Log.info("open new tab - failed");
				return false;
			}
		}
		
		else //if (linkNumber >= 3)
		{
			
			sleep(4000);
			 linkResponse = driver.findElement(By.cssSelector("#linkResponse")).getText();
			 linkResponseStatus = driver.findElement(By.cssSelector("#linkResponse>b:nth-child(1)")).getText();
			 linkResponseText = driver.findElement(By.cssSelector("#linkResponse>b:nth-child(2)")).getText();
			System.out.println("linkResponse: " + linkResponse);
			System.out.println("linkResponseStatus: " + linkResponseStatus);
			System.out.println("linkResponseText: " + linkResponseText);
			//////JavascriptExecutor js = (JavascriptExecutor) driver;
	                                  // This  will scroll down the page by  200 pixel vertical		
	        //////   js.executeScript("window.scrollBy(0,200)");
			if(linkResponse.contains("Link has responded with staus") 
					                && linkResponseText.contentEquals(linkText))
			{
				Log.info("link response satus =" + linkResponseStatus + " - pass");
				return true;
			}
			else
			{
				Log.info("link response satus =" + linkResponseStatus + " - failed");
				return false;
			}
		}
		
	}
		
}

