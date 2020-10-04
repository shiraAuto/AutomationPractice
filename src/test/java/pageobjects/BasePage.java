package test.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	JavascriptExecutor js; 
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		js=(JavascriptExecutor)driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	protected void fillText(WebElement el, String text) {
		//highlight
		//js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid blue;background-color:yellow;');", el);
		highlightElement(el,"blue");
		el.clear();
		el.sendKeys(text);
	}

	protected void click (WebElement el) {
		//highlight
		//js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid green;');", el);
		highlightElement(el,"green");
		//write to log
		sleep(1000);
		el.click();
	}

	protected String getText(WebElement el) {
		//js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid orange;background-color:yellow;');", el);
		highlightElement(el,"orange");
		return el.getText();
	}

	protected void selectByValue(WebElement el,String value) {
		//js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid blue;');", el);
		highlightElement(el, "blue");
		Select select = new Select(el);
		select.selectByValue(value);
	}

	public void waitForLoading() {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loading"))); 
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#loading"))); 
	
	}
	
	protected void alertOK() {
		driver.switchTo().alert().accept();
	}

	protected void alertFillText(String text) {
		driver.switchTo().alert().sendKeys(text);
		driver.switchTo().alert().accept();
	}

	protected void sleep(long mil) {
		try {
			Thread.sleep(mil);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Call this method with your element and a color like (red,green,orange etc...)
	 */
	protected void highlightElement(WebElement element, String color) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "border: 1px solid " + color + ";background-color:yellow;" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}


}