package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Utils {

	public static String readProp(String key){
		Properties prop = new Properties();
		String value="";
		try {

			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\data\\settings.properties");
			// load a properties file
			prop.load(input);

			// get the property value 
			value= prop.getProperty(key);
		}
		catch(Exception err)
		{}
		System.out.println("value =" + value);
		return value;
	}

	/**
	 * It is not recommended to use sleep but explicit wait, 
	 * but sometimes we want to save programming time so we will use it and then we will improve it later on.
	 * @param time
	 */
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Read the driver type from data\config.propoerties 
	 */
	public static WebDriver getDriver(){
		WebDriver driver = null;
		//read the driver according to your definition in Const.browser
		String driverType = readProp("browser");
		switch (driverType){
		case "FireFox": //	GeckoDriver = 0.19.0 | FireFox = 58.0.1 | selenium = 3.8.0
			//here you should define the driver for firefox(Mozilla GeckoDriver) browser
			System.setProperty("webdriver.gecko.driver", readProp("driverPath") + "geckodriver.exe");
			driver =  new FirefoxDriver();
			break;
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", readProp("driverPath") + "chromedriver.exe");
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.addArguments("disable-infobars");
			driver = new ChromeDriver(cOptions);
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", readProp("driverPath") + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		default:
			//here you should define the driver for default browser
			break;
		}
		return driver;
	}

}
