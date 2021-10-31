package com.demo.reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.demo.baseutil.BaseTest;
import com.demo.commonutils.FileVariables;


/**
 * Description: Implements the creation of the Extent Html report with specified
 * name after loading the Extent config file from specified location.
 * 
 * @author Sajal, Vikas
 */
public class Extent {
	
	private ExtentReports extent;
	FileVariables fileVariables = new FileVariables();
	/**
	 * Description: Creates of HTML report in specified path
	 * 
	 * @author Sajal, Vikas
	 * @param filepath
	 */
	public  ExtentReports getExtent(String filepath) {

		if (extent == null) {
			try {
				extent = new ExtentReports();
				extent.attachReporter(getHtmlReporter(filepath+  "/AutomationReport.html"));
				return extent; 
			} catch (Exception e) {
				BaseTest.logger.error("Exception while creating report html file.");
				
			}
		}
		return extent;
	}
	/**s
	 * Description :Loads the Extent-config file specified from the location
	 * 
	 * @author Sajal, Vikas
	 * @param filePath
	 */
	private static ExtentHtmlReporter getHtmlReporter(String filePath) {
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "./config\\extent-config.xml");
		return htmlReporter;
	}
	
	private static ExtentTest extentTtest;
	private static ExtentReports extentReports;
	
	
	/**
	 * Description : Creates the HTML report based on the name specified
	 * @author Sajal, Vikas
	 * @param name
	 */
	public static ExtentTest createTest(String name) {

		extentTtest = extentReports.createTest(name);
		return extentTtest;
	}

	public static ExtentReports getExtent() {
		return extentReports;
	}
	
	/**
	 * Description : Initializes report specified in the file path
	 * 
	 * @author Sajal, Vikas
	 * @param filepath
	 */
	public void initReport(String filepath) {
		extentReports = new Extent().getExtent(filepath);
		BaseTest.logger.info("Report Initiated");
	}


}
