package com.Demo.Base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.Demo.Util.Xls_Reader;

public class TestBase {

	public static Logger APP_LOGS=null;
	public static Properties CONFIG=null;
	public static Properties OR=null;
	public static Xls_Reader suiteXls=null;
	public static Xls_Reader suiteSignUpxls=null;
	public static Xls_Reader suiteDemoxls=null;
	
	public static boolean isInitalized=false;
	public static WebDriver dr=null;
	public static EventFiringWebDriver driver=null;
	
	
	// initializing the Tests
	public void initialize() throws Exception{
		
		if(!isInitalized){
			
		// logs
		APP_LOGS = Logger.getLogger("devpinoyLogger");
		// config
		APP_LOGS.debug("Loading Property files");
	
		CONFIG = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//Demo//config/config.properties");
		CONFIG.load(ip);
			
		OR = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//Demo//config/OR.properties");
		OR.load(ip);
		APP_LOGS.debug("Loaded Property files successfully");
		APP_LOGS.debug("Loading XLS Files");

		// xls file
		suiteDemoxls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//Demo//xls//Demo Suite.xlsx");
		suiteXls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//Demo//xls//Suite.xlsx");
		APP_LOGS.debug("Loaded XLS Files successfully");		
		
		
		//intialize webdriver
		if(CONFIG.getProperty("browser").equals("Firefox"))
		{
			dr = new FirefoxDriver();
		}else if(CONFIG.getProperty("browser").equals("IE")){
			System.setProperty("webdriver.ie.driver","C:\\IEDriverServer_Win32_2.38.0\\IEDriverServer.exe");
			dr = new InternetExplorerDriver();
		}else if(CONFIG.getProperty("browser").equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
			dr = new ChromeDriver();
		}
				
		// selenium RC/ Webdriver
		driver = new EventFiringWebDriver(dr);
		driver.manage().timeouts().implicitlyWait(30 , TimeUnit.SECONDS);
		driver.manage().window().maximize();
		isInitalized=true;
		
	}

	}
	public static WebElement getObject(String xpathKey){
		try{
		return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		}catch(Throwable t){
			//report error
			//TestUtil.takeScreenShot(xpathKey) ;
			//Assert.assertTrue(t.getMessage(),false);
			return null;
			
		}
		
	}

}