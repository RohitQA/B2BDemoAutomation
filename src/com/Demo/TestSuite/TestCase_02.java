package com.Demo.TestSuite;

//TESTCASE: Check that user can click on SalesForce cloud.

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Demo.TestSuite.TestSuiteBase;
import com.Demo.Util.ErrorUtil;
import com.Demo.Util.TestUtil;

public class TestCase_02 extends TestSuiteBase {

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
	


	@Test
	public void testcase02() {
		System.out.println("This is TestCase02");
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		System.out.println("This is TestCase 02");
		APP_LOGS.debug(" Executing TestCase_02");
		try{
		
			driver.findElement(By.xpath("html/body/article/div/div[2]/div/div/div[1]/a[3]")).click();
			Thread.sleep(4000L);
			
			
		}catch(Throwable t){
			// code to report the error in testng
			ErrorUtil.addVerificationFailure(t);
			// report the error in xls files
			fail=true;
					
		}
		
		try{
			String actual_title=driver.findElement(By.xpath("html/body/article/div/div[1]/div/h3")).getText();  
			String expected_title="CircleBack Demo Dashboard";
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
/*
@DataProvider
public Object[][] getTestData(){
return TestUtil.getData(suiteDemoxls, this.getClass().getSimpleName()) ;
}
	
*/


}
