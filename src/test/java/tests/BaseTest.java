package test.java.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utils.Utils;

import java.io.File;
import java.io.IOException;

public class BaseTest {
	WebDriver driver;
	public Logger logger = Logger.getLogger(utils.Log.class);

	@BeforeClass
	public void setup() {

		//PropertyConfigurator.configure("C:\\Users\\ASUS\\eclipse-workspace\\AutomationPractice\\log4j.properties");
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\java\\log4j.properties");
		//AutomationEmployers
		//System.getProperty("user.dir")
		//System.setProperty("webdriver.chrome.driver", "C:\\Automation\\Drivers\\chromedriver.exe"); 
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		logger.info("Browser Opened");
		driver.manage().window().maximize();
		driver.get(Utils.readProp("url"));
		logger.info("open url " + Utils.readProp("url"));
	}

	@AfterMethod
	public void failedTest(ITestResult result) {
	  //check if the test failed
		if (result.getStatus() == ITestResult.FAILURE ){
			TakesScreenshot ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			try {
			//	FileUtils.copyFile(srcFile, new File("./ScreenShots/"+result.getName()+".jpg"));
				FileUtils.copyFile(srcFile, new File("D:\\automations\\projectsLogs\\screenshots\\AutomationPractice\\"+result.getName()+".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//result.getname() method will give you current test case name. 
			//./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
		}
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		logger.info("Browser Closed");
	}
}
