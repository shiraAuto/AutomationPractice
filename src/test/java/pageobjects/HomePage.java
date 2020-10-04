package test.java.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	@FindBy(css=".category-cards>div:nth-child(1)")
	private WebElement elements;

	@FindBy(css=".category-cards>div:nth-child(2)")
	private WebElement forms;
	
	@FindBy(css=".category-cards>div:nth-child(3)")
	private WebElement alertsAndFrames;
	
	@FindBy(css=".category-cards>div:nth-child(4)")
	private WebElement widgets;
	
	@FindBy(css=".category-cards>div:nth-child(5)")
	private WebElement interactions;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
    
	public void moveToElementsCard() {
		 click(elements);	
	}
	
	public void moveToFormsCard() {
		 click(forms);	
	}
	
	public void moveToAlertAndFramesCard() {
		 click(alertsAndFrames);	
	}
	
	public void moveToWidgetsCard() {
		 click(widgets);	
	}
	
	public void moveToInteractionsCard() {
		 click(interactions);	
	}
	/*
	public void searchItem(String query) {
		 System.out.println("enter searchItem");
		fillText(searchBar, query);
		click(searchBtn);
		
	}
	
	public void chooseCategory(String categoryToSearch) {
		 System.out.println("enter chooseCategory");
		Select categories = new Select(driver.findElement(By.cssSelector("#gh-cat")));
		categories.selectByVisibleText(categoryToSearch);	
	}
     */
}
