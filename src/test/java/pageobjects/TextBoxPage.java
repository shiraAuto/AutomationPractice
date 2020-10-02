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

public class TextBoxPage extends BasePage{

	@FindBy(css="#userName")
	private WebElement name;
	
	@FindBy(css="#userEmail")
	private WebElement email;
	
	@FindBy(css="#currentAddress")
	private WebElement address;
	
	@FindBy(css="#permanentAddress")
	private WebElement paymentaddress;
	
	@FindBy(css="#submit")
	private WebElement formBtn;
	
	private String outputName , outputEmail, outputAddress, outputPaymentaddress;
	
	
	public TextBoxPage(WebDriver driver) {
		super(driver);
	}
	
	public void fillForm(String userName, String userEmail , String userAddress , String userPayAddress)
	{
		fillText(name, userName);
		fillText(email, userEmail);
		fillText(address, userAddress);
		fillText(paymentaddress, userPayAddress);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        // This  will scroll down the page by  1000 pixel vertical		
        js.executeScript("window.scrollBy(0,1000)");
		click(formBtn);
		Log.info("fillForm - done");
	}
	
	
	
	
	public boolean checkOutput(String userName, String userEmail , String userAddress , String userPayAddress) {
		// System.out.println("output - before");
		List<WebElement> outputList = driver.findElements(By.cssSelector("#output>div>p"));
		outputName = outputList.get(0).getText();
		outputEmail = outputList.get(1).getText();
		outputAddress =outputList.get(2).getText();
		outputPaymentaddress =outputList.get(3).getText();
		if(outputName.contains(userName) && outputEmail.contains(userEmail) 
				&& outputAddress.contains(userAddress)  && outputPaymentaddress.contains(userPayAddress))
		{
			Log.info("output after fillform - passed");
			return true;
		}
		else
		{
			Log.info("output after fillform - failed");
			return false;
		}
	}
}


/*
System.out.println(outputList.get(0).getText());
		System.out.println(outputList.get(1).getText());
		System.out.println(outputList.get(2).getText());
		System.out.println(outputList.get(3).getText());

*/