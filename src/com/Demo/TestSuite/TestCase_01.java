package com.Demo.TestSuite;

//TEST CASE: Verify that user can login with valid credentials.
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Demo.TestSuite.TestSuiteBase;
import com.Demo.Util.ErrorUtil;
import com.Demo.Util.TestUtil;

public class TestCase_01 extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(suiteDemoxls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(suiteDemoxls, this.getClass().getSimpleName());
	}
	
	@SuppressWarnings("deprecation")
	@Test(dataProvider="getTestData")
	public void testcase01(String username , String password) {
		System.out.println("This is TestCase01");
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		System.out.println("This is TestCase 01");
		APP_LOGS.debug(" Executing TestCase_01");
		try{
			driver.get(CONFIG.getProperty("testSiteName"));
			driver.findElement(By.xpath("//*[@id='user_email']")).clear();
			driver.findElement(By.xpath("//*[@id='user_email']")).sendKeys(username);
			driver.findElement(By.xpath("//*[@id='user_password']")).clear();
			driver.findElement(By.xpath("//*[@id='user_password']")).sendKeys(password);
			driver.findElement(By.xpath("//*[@id='new_user']/div[4]/div[1]/input")).click();
			
			Thread.sleep(4000L);
		
		}catch(Throwable t){
			// code to report the error in testng
			ErrorUtil.addVerificationFailure(t);
			// report the error in xls files
			fail=true;
					
		}
		
		try{
			String actual_title=driver.findElement(By.xpath("html/body/article/div/div[1]/h1")).getText();  
			String expected_title="Welcome to CircleBack Analysis";
			Assert.assertEquals(expected_title, actual_title);
		}catch(Throwable t){
			// code to report the error in testng
			ErrorUtil.addVerificationFailure(t);
			// report the error in xls files
			fail=true;
		}
}
@AfterMethod
public void reportDataSetResult(){
if(skip)
TestUtil.reportDataSetResult(suiteDemoxls, this.getClass().getSimpleName(), count+2, "SKIP");
else if(fail){
isTestPass=false;
TestUtil.reportDataSetResult(suiteDemoxls, this.getClass().getSimpleName(), count+2, "FAIL");
}
else
TestUtil.reportDataSetResult(suiteDemoxls, this.getClass().getSimpleName(), count+2, "PASS");

skip=false;
fail=false;


}

@AfterTest
public void reportTestResult(){
if(isTestPass)
TestUtil.reportDataSetResult(suiteDemoxls, "Test Cases", TestUtil.getRowNum(suiteDemoxls, this.getClass().getSimpleName()), "PASS");
else
TestUtil.reportDataSetResult(suiteDemoxls, "Test Cases", TestUtil.getRowNum(suiteDemoxls, this.getClass().getSimpleName()), "FAIL");

}

@DataProvider
public Object[][] getTestData(){
return TestUtil.getData(suiteDemoxls, this.getClass().getSimpleName()) ;
}
	
}
