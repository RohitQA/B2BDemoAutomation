package com.Demo.TestSuite;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import com.Demo.Base.TestBase;
import com.Demo.Util.TestUtil;

public class TestSuiteBase extends TestBase {
	// check if the suite ex has to be skiped
				@BeforeSuite
				public void checkSuiteSkip() throws Exception{
					initialize();
					APP_LOGS.debug("Checking Runmode of Demo Suite");
					if(!TestUtil.isSuiteRunnable(suiteXls, "Demo Suite")){
						APP_LOGS.debug("Skipped Suite Login as the runmode was set to NO");
						throw new SkipException("Runmode of Demo Suite  set to no. So Skipping all tests in Demo Suite");
					}

		}
}
