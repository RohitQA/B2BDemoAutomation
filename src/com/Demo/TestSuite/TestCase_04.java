package com.Demo.TestSuite;

// TEST CASE: Check 

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

public class TestCase_04 extends TestSuiteBase{
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
	public void TestCase04() {
		System.out.println("This is TestCase04");
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		System.out.println("This is TestCase 04");
		APP_LOGS.debug(" Executing TestCase_04");
		try{
			
			String out_of_date_count = driver.findElement(By.xpath("//*[@id='segments']/div[2]/div[2]/div/a[2]/div[2]/span")).getText();
			System.out.println(out_of_date_count);
			
			String duplicates_found_count = driver.findElement(By.xpath("//*[@id='segments']/div[2]/div[3]/div/a/div[2]/span")).getText();
			System.out.println(duplicates_found_count);
			
			String up_to_date_count = driver.findElement(By.xpath("//*[@id='segments']/div[2]/div[4]/div/a/div[2]/span")).getText();
			System.out.println(up_to_date_count);
			
			String confidence_index = driver.findElement(By.xpath("//*[@id='edit_segments_salesforce_instance_segment_55baa4d6313434000d060000']/h2")).getText();
			System.out.println(confidence_index);
			
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
