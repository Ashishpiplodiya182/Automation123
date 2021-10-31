package com.demo.baseutil;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.demo.commonutils.FileOperation;
import com.demo.reports.Extent;
import com.demo.reports.ExtentManager;
import com.demo.util.WebActionUtil;
import com.demo.util.WebActionUtil_Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/***********************************************************************
 * Description : Implements Application Precondition and Postcondition.
 * 
 * @author : Sajal, Vikas
 * @BeforeSuite: Creates all the folder structure for Extent Reports
 * @BeforeClass : Sets extent report.
 * @BeforeMethod : Creates node for extent report.
 * 
 */
public class BaseTest {
	public static Properties prop;
	public static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	public static final String CONFIGPATH = System.getProperty("user.dir") + "./config/config.properties";
	public static WebActionUtil actionUtil;
	public static WebActionUtil_Test actionUtilTest;
	public static final int ITO = 10;
	public static final int ETO = 30;
	public static final String EXCELPATH = System.getProperty("user.dir") + "./src/main/resources/data/Data.xlsx";
	public static PrintStream fos;
	public static final String APILOG = System.getProperty("user.dir") + "./apilog/";
	public static RequestSpecBuilder requestSpecBuilder;
	public static ResponseSpecBuilder responseSpecBuilder;
	public static RequestSpecification requestSpecification;
	public static ResponseSpecification responseSpecification;
	static {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATH);
			prop.load(fis);
			fos = new PrintStream(APILOG+"apilog_"+WebActionUtil.getCurrentDateTime()+".log");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Description : Creates folder structure for Extent reports.
	 * 
	 * @author:Sajal, Vikas
	 */
	@BeforeSuite(alwaysRun = true)
	public synchronized void createFiles() {
		try {
			logger.info("Folder creation for Extent");
			FileOperation fileOperation = new FileOperation();
			fileOperation.CreateFiles();
		} catch (Exception e) {
			logger.error("Exception while report inititation");
		}

	}

	/**
	 * Description: sets extent report
	 * 
	 * @author:Sajal, Vikas
	 * 
	 */

	@BeforeClass
	public void setExtentReport() {
		ExtentTest parentExtentTest = Extent.createTest(getClass().getSimpleName());
		ExtentManager.setParentReport(parentExtentTest);
		
		requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri(prop.getProperty("baseUrl"));
	}

	/**
	 * Description: Creates node for extent report.
	 * 
	 * @author Sajal, Vikas
	 * @param: methodName
	 */
//	@Parameters("browserName")
	@BeforeMethod
	public void createNode(Method methodName) {
		try {
			String description = methodName.getAnnotation(Test.class).description();
			ExtentTest testReport = ExtentManager.getParentReport().createNode(methodName.getName(), description);
			ExtentManager.setTestReport(testReport);
			logger = LoggerFactory.getLogger(getClass().getName());
			//actionUtil = new WebActionUtil(ETO);
			actionUtil = new WebActionUtil(ETO);
			actionUtilTest = new WebActionUtil_Test(ETO);

		} catch (Throwable e) {
			WebActionUtil.error("Unable to creates node for extent report");
			Assert.fail("Unable to creates node for extent report");
		}

	}

}
