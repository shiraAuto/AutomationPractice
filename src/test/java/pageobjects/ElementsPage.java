package test.java.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.List;

public class ElementsPage extends BasePage {

	List<WebElement> listMenu = driver.findElements(By.cssSelector(".element-list>ul>li"));

	@FindBy(css = ".srp-controls__default-refinements")
	private WebElement searchBar;


	public ElementsPage(WebDriver driver) {
		super(driver);
	}

	public void printList() {
		//List<WebElement> list = driver.findElements(By.cssSelector(".element-list>ul>li"));
		for (WebElement item : listMenu) {
    		if(!(item.getText().isEmpty()))
			   System.out.println(item.getText());
    		//else
    		 //  System.out.println("empty");
		}
    	
    }
    
    public void elementToTest(String selectedElement) {
    	Log.info("elementToTest = " + selectedElement);
    	int numOfItem = 0;
    	for (WebElement item : listMenu) {
    		numOfItem++;
    		if(item.getText().equalsIgnoreCase(selectedElement))
    		{ 			
    		    if(numOfItem >= 3)
    		    {
    			JavascriptExecutor js = (JavascriptExecutor) driver;
    	        // This  will scroll down the page by  100 pixel vertical		
    	        js.executeScript("window.scrollBy(0,200)");
    		    }
    			click(item);  			
    		}
			
		}
    }
	
    public boolean textBoxFillForm(String userName, String userEmail , String userAddress , String userPayAddress) {
    	TextBoxPage tb = new TextBoxPage(driver);
    	tb.fillForm( userName,  userEmail ,  userAddress ,  userPayAddress);
    	return (tb.checkOutput(userName, userEmail, userAddress, userPayAddress));
    	
    }
	
    public boolean testCheckBoxExpand() { 
    	CheckBoxPage cb = new CheckBoxPage(driver);
    	cb.expandAll();
    	cb.doCheck();
    	return (cb.checkBoxOutputAfterCheckExpand()) ;
    }
    
    public boolean testCheckBoxCollapse() { 
    	CheckBoxPage cb = new CheckBoxPage(driver);
    	cb.collapseAll();
        cb.openHomeArrow();
    	return (cb.checkBoxOutputAfterCheckCollapse()) ;
    }
    
}
