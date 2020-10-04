package test.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.List;

public class RadioButtonPage extends BasePage{

	@FindBy(css=".row>div:nth-child(2)>div>div")
	private WebElement text;
	List<WebElement>  radioList = driver.findElements(By.cssSelector(".row>div:nth-child(2)>div>.custom-control"));
	String textAfterclick;
	
	
	public RadioButtonPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean checkIfTestExist()
	{
		System.out.println(text.getText());
		if(text.getText().contentEquals("Do you like the site?"))
		{
			Log.info("the text \"Do you like the site?\" - passed");
			return true;
		}
		else
		{
			Log.info("the text \"Do you like the site?\" - failed");
			return false;
		}
				
	}
	
	public boolean checkRadio(String first , String second , String third)
	{
	//	List<WebElement>  radioList = driver.findElements(By.cssSelector(".row>div:nth-child(2)>div>.custom-control"));
		if(radioList.get(0).getText().contentEquals(first))
			if(radioList.get(1).getText().contentEquals(second))
				if(radioList.get(2).getText().contentEquals(third))
				{
					  Log.info("check 3 radio buttons exist - passed");
	    	          return true;
				}
		Log.info("check 3 radio buttons exist - failed");
		return false;
	}
	
	public boolean clickRadio(String valueToClick)
	{
		int flag=0;
		for (int i = 0; i < radioList.size(); i++) {
			if(radioList.get(i).getText().contentEquals(valueToClick))
			{
				click(radioList.get(i));
				flag=1;
				break;
			}
		}
		
		if(flag==1)
		{
			textAfterclick = driver.findElement(By.cssSelector(".row>div:nth-child(2)>div>p")).getText();
			if(textAfterclick.contains(valueToClick))
			{
				Log.info("check text after click - passed");
				return true;
			}
			else
			{
				Log.info("check text after click - failed");
				return false;
			}
		}
		else
		{
			Log.info("value was not found.");
			return false;
		}
	   
	}
	
	

}

