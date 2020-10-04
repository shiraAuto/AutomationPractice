package test.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.List;

public class FormsPage extends BasePage {


	@FindBy(css = "#firstName")
	private WebElement firstName;

	@FindBy(css = "#lastName")
	private WebElement lastName;

	@FindBy(css = "#userEmail")
	private WebElement userEmail;

	@FindBy(css = "#userNumber")
	private WebElement userNumber;

	@FindBy(css = "#submit")
	private WebElement submit;


	@FindBy(css = ".accordion>div:nth-child(2)>div>ul>.btn")
	private WebElement practiseformBtn;


	public FormsPage(WebDriver driver) {
		super(driver);
	}


	public void moveToTest() {
		click(practiseformBtn);
	}

	public void fillFormPractice(String firstname, String lastname, String email, String gender, String mobile) {
		fillText(firstName, firstname);
		fillText(lastName, lastname);
		fillText(userEmail, email);
		checkRadio(gender);
		fillText(userNumber, mobile);
		click(practiseformBtn);
		Log.info("fill form - done");

	}

	public void checkRadio(String gender) {
		List<WebElement> genderList = driver.findElements(By.cssSelector("#genterWrapper>div:nth-child(2)>div"));
		for (WebElement el : genderList) {
			if (el.getText().equalsIgnoreCase(gender)) {
				click(el);
				break;
			}
			
		}
	 }
	 
	 
	
}
