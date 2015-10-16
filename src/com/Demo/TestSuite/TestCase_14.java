package com.Demo.TestSuite;

// TEST CASE: Logout

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

public class TestCase_14 extends TestSuiteBase{
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
	@Test()
	public void TestCase14() {
		System.out.println("This is TestCase14");
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		System.out.println("This is TestCase 14");
		APP_LOGS.debug(" Executing TestCase_14");
		try{
			driver.navigate().refresh();
		    driver.findElement(By.xpath("html/body/header/nav/div[2]/ul/li[1]/a")).click();
		    driver.findElement(By.xpath("html/body/header/nav/div[2]/ul/li[1]/ul/li[3]/a")).click();
		    
			
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



}
