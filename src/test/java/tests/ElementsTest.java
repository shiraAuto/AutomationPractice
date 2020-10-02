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


public class ElementsTest extends BaseTest {

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
	public void test00_moveToElements()  {
		Log.info("start test00_moveToElements");
		String currentUrl;
		HomePage hp = new HomePage(driver);
		hp.moveToElementsCard();
		currentUrl=driver.getCurrentUrl();
		Log.info("move to " + currentUrl);
		softAssert.assertEquals(currentUrl, "https://demoqa.com/elements");
		softAssert.assertAll();
	}
	
	@Test
	public void test01_textBoxTest()  {
		Log.info("start test01_textBoxTest");
	    String fullName,email,currentAddress,paymentAddress;	
		ElementsPage el = new ElementsPage(driver);
	//	el.printList();
		el.elementToTest(categoryToTest1);
		Log.info("move to category: " + categoryToTest1);
		fullName = Utils.readProp("fullName");
		email = Utils.readProp("email");
		currentAddress = Utils.readProp("currentAddress");
		paymentAddress = Utils.readProp("paymentAddress");
		softAssert.assertTrue(el.textBoxFillForm(fullName,email,currentAddress,paymentAddress));
		softAssert.assertAll();
   }
	
   // @Test
	public void test02_checkBoxTest() {
		Log.info("start test02_checkBoxTest");
		//HomePage hp = new HomePage(driver);
		//hp.moveToElementsCard();
	    ElementsPage el = new ElementsPage(driver);
		el.elementToTest(categoryToTest2);
		Log.info("move to category: " + categoryToTest2);
		softAssert.assertTrue( el.testCheckBoxExpand());
		softAssert.assertTrue( el.testCheckBoxCollapse());
		softAssert.assertAll();
      
   }

	@Test
	public void test03_radioButtonTest() {
		Log.info("start test03_radioButtonTest");
	    ElementsPage el = new ElementsPage(driver);
		el.elementToTest(categoryToTest3);
		Log.info("move to category: " + categoryToTest3);
		RadioButtonPage rb = new RadioButtonPage(driver);	
		softAssert.assertTrue(rb.checkIfTestExist());
		softAssert.assertTrue(rb.checkRadio("Yes", "Impressive", "No"));
		softAssert.assertTrue(rb.clickRadio("Impressive"));
		softAssert.assertAll();
	}
	
	//@Test
	public void test04_webTablesTest() {
		Log.info("start test04_webTablesTest");
		int numOfRawsBefore,numOfRawsAfter;
		String firstName,lastName,email,age,salary,department;
		ElementsPage el = new ElementsPage(driver);
		el.printList();
		Log.info("before elementToTest(categoryToTest4)");
		el.elementToTest(categoryToTest4);
		Log.info("move to category: " + categoryToTest4);
		WebTablePage wt = new WebTablePage(driver);
		softAssert.assertTrue(wt.checkPlaceHolderText("Type to search"));
		numOfRawsBefore = wt.getTableSize();
	    Log.info("numOfRaws before add row: " + numOfRawsBefore ); 
	    firstName=Utils.readProp("webTable_firstName");
	    lastName=Utils.readProp("webTable_lastName");
	    email=Utils.readProp("webTable_email");
	    age=Utils.readProp("webTable_age");
	    salary=Utils.readProp("webTable_salary");
	    department=Utils.readProp("webTable_department");
		wt.addRaw(firstName,lastName,email,age,salary,department); // add row to table
		numOfRawsAfter = wt.getTableSize();
	    Log.info("numOfRaws after add row: " + numOfRawsAfter ); 
		softAssert.assertEquals(numOfRawsBefore+1, numOfRawsAfter);
		//softAssert.assertTrue(checkAddedRaw(firstName,lastName));////TBD  
	    //wt.printTable();  // working good- prints all lines without the empty ones
	    softAssert.assertAll();	
	}
	

	//@Test
	public void test05_buttonTest() {
		Log.info("start test05_buttonTest");
		ElementsPage el = new ElementsPage(driver);
		el.printList();
		el.elementToTest(categoryToTest5);
		Log.info("move to category: " + categoryToTest5);
		ButtonsPage bp = new ButtonsPage(driver);
		softAssert.assertTrue(bp.clickDoubleClickBtn());
		softAssert.assertTrue(bp.rightClickBtn());
		softAssert.assertTrue(bp.clickMeBtn());
		softAssert.assertAll();
	}
	
	//@Test
	public void test06_linksTest() {
		Log.info("start test05_buttonTest");
		ElementsPage el = new ElementsPage(driver);
		el.elementToTest(categoryToTest6);
		Log.info("move to category: " + categoryToTest6);
		LinksPage lp = new LinksPage(driver);	
		softAssert.assertTrue(lp.clickLinkBtn("Forbidden"));
		softAssert.assertTrue(lp.clickLinkBtn("Home"));
		softAssert.assertAll();
		
	}
	
	
}