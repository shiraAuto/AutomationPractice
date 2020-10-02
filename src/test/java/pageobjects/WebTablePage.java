package pageobjects;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;
import utils.Utils;

public class WebTablePage extends BasePage{

	@FindBy(css="#addNewRecordButton")
	private WebElement addButton;
	@FindBy(css=".input-group>input")
	private WebElement searchBar; 
	@FindBy(css="#addNewRecordButton")
	private WebElement addRawButton;
	
	String textAfterclick;
	
	
	
	public WebTablePage(WebDriver driver) {
		super(driver);
	}
	
	
	public boolean checkPlaceHolderText(String text)
	{
		String placeHolderText = searchBar.getAttribute("placeholder").toString();
			
		if(placeHolderText.contentEquals(text))
		{
			Log.info("check place holder text = " + placeHolderText + " - passed");
			return true;
		}
		else
		{
			Log.info("check place holder text = " + placeHolderText + " - failed");
			return false;
		}		
		//<input autocomplete="off" placeholder="Type to search" type="text" id="searchBox" class="form-control">
	}
	
	public int getTableSize()
	{
		int count = 0;
		String textInRaw;
		List<WebElement>  tableRaws = driver.findElements(By.cssSelector(".rt-tbody>.rt-tr-group"));
		for (WebElement raw : tableRaws) 
		{
			textInRaw=raw.findElement(By.cssSelector(".rt-tr")).getText();
			if (!(textInRaw.startsWith("   ")))
			{
				count++;
				continue;
			}
		}
		Log.info("table size: " + count);
		return count;
	}
	
	public void addRaw (String first,String last,String email,String age, String salary,String area)
	{
	 click(addRawButton);
	 Log.info("click add raw");
	 fillText(driver.findElement(By.cssSelector("#firstName")), first);
	 fillText(driver.findElement(By.cssSelector("#lastName")), last);
	 fillText(driver.findElement(By.cssSelector("#userEmail")), email);
	 fillText(driver.findElement(By.cssSelector("#age")), age);
	 fillText(driver.findElement(By.cssSelector("#salary")), salary);
	 fillText(driver.findElement(By.cssSelector("#department")), area);
	 click(driver.findElement(By.cssSelector("#submit")));
 
	}
	
	/*
	public void printTableOld()
	{   int rawCounter=1;
		List<WebElement>  tableRaws = driver.findElements(By.cssSelector(".rt-table>.rt-tbody>div"));
		for (WebElement raw : tableRaws) 
		{
			System.out.println("raw number: " + rawCounter);
			rawCounter++;
			List<WebElement>  valuesAddedRaw = raw.findElements(By.cssSelector(".rt-tr"));
			for (WebElement value : valuesAddedRaw) 
			{
				System.out.println(value.getText());
			}	
		}
	}
	*/
	
	public void printTable()
	{   
		int rawCounter=1, flag=0;
	    WebElement raw;
		List<WebElement>  rawList = driver.findElements(By.cssSelector(".rt-table>.rt-tbody>div")); //10 raws
		for (int i = 0; i < rawList.size() && flag==0 ; i++) //10 raws
		{
			Log.info("raw number: " + rawCounter );
			 raw =  rawList.get(i);  //for (WebElement raw : rawList) 
			 List<WebElement>  valuesList = raw.findElements(By.cssSelector(".rt-tr")); // 7 raw
			 for (int j = 0; j < valuesList.size() && flag==0; j++) 
				{

					 if(valuesList.get(j).getText().startsWith(" "))
					 {
						rawCounter--;
						flag=1;
						Log.info("last raw - empty raw");
						Log.info("table size: " + rawCounter);
						break;
					 }
					 else
					 {
						 Log.info(valuesList.get(j).getText());
					 }				
				}
			 if (flag==0)
				 rawCounter++; 
		}
	}
	
/*	public boolean checkAddedRaw(String value1, String value2) //// TBD
	{   
	   fillText(searchBar,value1);
	   int countValues=0;
		List<WebElement>  tableRaws = driver.findElements(By.cssSelector(".rt-table>.rt-tbody>div"));
		for (WebElement raw : tableRaws) 
		{	///////////////////		
		  countValues=0;
			List<WebElement>  valuesFound = raw.findElements(By.cssSelector(".rt-tr"));
			while(countValues < 2)
			{
				for (WebElement textValue : valuesFound) 
				{		
						if(textValue.getText().equalsIgnoreCase(value1) ||textValue.getText().equalsIgnoreCase(value2) )
						{
							countValues++;
							Log.info("found value: " + textValue.getText());
						}
				}		
			}
			
			if(countValues>=2)
			{
				Log.info("2 values where found");
				return true;
			}
			else
				continue;
			
		}///////////////
		
		if(countValues < 2)
			return false;
	}
	*/
	
}

