package com.Demo.TestSuite;

// TEST CASE: Search on Advance Filters

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

public class TestCase_12 extends TestSuiteBase{
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
	public void TestCase12() {
		System.out.println("This is TestCase12");
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		System.out.println("This is TestCase 12");
		APP_LOGS.debug(" Executing TestCase_12");
		try{
			driver.navigate().refresh();
			
			driver.findElement(By.xpath("html/body/article/div/div/div[1]/div[8]/label/input")).click();
			driver.executeScript("scroll(0,100)");
			driver.findElement(By.xpath("//*[@id='s2id_autogen22']")).sendKeys("445");
			Thread.sleep(5000L);
			driver.findElement(By.xpath("//*[@id='select2-result-label-29']")).click();
			driver.findElement(By.xpath("//*[@id='select2-drop-mask']")).click();
			driver.findElement(By.xpath("html/body/article/div/div/div[2]/h1/contact-count/small")).getText();
			driver.findElement(By.xpath("html/body/article/div/div/div[2]/h1/div/a")).click();
			Thread.sleep(5000L);
			driver.findElement(By.xpath("html/body/article/div/div/div[2]/a")).click();
			driver.findElement(By.xpath("html/body/article/div/div/div[2]/div[11]/a[1]")).click();
			Thread.sleep(5000L);		
			
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
