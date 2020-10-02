package tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.HomePage;
import pageobjects.LinksPage;
import pageobjects.RadioButtonPage;
import pageobjects.WebTablePage;
import utils.Log;
import utils.Utils;
import pageobjects.ButtonsPage;
import pageobjects.ElementsPage;
import pageobjects.FormsPage;


public class FormsTest extends BaseTest {

	String categoryToTest1="Text Box";	
	String categoryToTest2="Check Box";
	String categoryToTest3="Radio Button";
	String categoryToTest4="Web Tables";
	String categoryToTest5="Buttons";
	String categoryToTest6="Links";
	String categoryToTest7="Upload and Download";
	String categoryToTest8="Dynamic Properties";
	
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void test00_moveToForms()  {
		Log.info("start test00_moveToForms");
		String currentUrl;
		HomePage hp = new HomePage(driver);
		hp.moveToFormsCard();
		currentUrl=driver.getCurrentUrl();
		Log.info("move to " + currentUrl);
		softAssert.assertEquals(currentUrl, "https://demoqa.com/forms");
		softAssert.assertAll();
	}
	
	@Test
	public void test01_practiceFormsTest()  {
		Log.info("start test01_practiceFormsTest");
	    String firstname,lastname,email,gender;
		FormsPage ft = new FormsPage(driver);
	    ft.moveToTest();
	    softAssert.assertTrue(driver.getCurrentUrl().contentEquals("https://demoqa.com/automation-practice-form"));
	    firstname = Utils.readProp("practiseform_firstname");
	    lastname = Utils.readProp("practiseform_lastName");
		email = Utils.readProp("practiseform_email");
		gender=Utils.readProp("practiseform_gender");
		ft.fillFormPractice(firstname, lastname, email, gender);
	    softAssert.assertAll();
		/*
	    Log.info("move to test practiceForms");
		fullName = Utils.readProp("fullName");
		email = Utils.readProp("email");
		currentAddress = Utils.readProp("currentAddress");
		paymentAddress = Utils.readProp("paymentAddress");
		softAssert.assertTrue(el.textBoxFillForm(fullName,email,currentAddress,paymentAddress));
		softAssert.assertAll();
		*/
   }
	
   
	
	
}