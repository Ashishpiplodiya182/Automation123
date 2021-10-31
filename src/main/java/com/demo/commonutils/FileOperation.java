package com.demo.commonutils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.demo.baseutil.BaseTest;
import com.demo.reports.Extent;
import com.demo.util.WebActionUtil;

/**
 * Description : Implements creation of the folder structure for Extent
 * reports,screenshots,deletion of the previously created folder.
 * 
 * @author: Sajal, Vikas
 */
public class FileOperation {
	FileVariables fileVariables = new FileVariables();

	/**
	 * Description:Implements setting of the path,creation of the folder structure
	 * for Extent reports,screenshot,deletion of the folder.
	 * 
	 * @author: Sajal, Vikas
	 */
	public void CreateFiles() {

		fileVariables.setDate(new Date());
		fileVariables.setSdf(new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss"));
		fileVariables.setsDate(fileVariables.getSdf().format(fileVariables.getDate()));
		fileVariables.setsStartTime(fileVariables.getsDate());
		fileVariables.setExtentReportFolderPath(System.getProperty("user.dir") + "/reports");

		fileVariables.setExtentDir(fileVariables.getExtentReportFolderPath() + "/Automation Report- "
				+ WebActionUtil.getCurrentDateTime());

//		fileVariables.setScreenShotPath(fileVariables.getExtentDir() + "/Screenshots/");

		
		BaseTest.logger.info("ExtentDir:-" + fileVariables.getExtentDir());

		/* delete extent folder if it exists before running suite */
		WebActionUtil.deleteDir(fileVariables.getExtentReportFolderPath());

		try {
			File file = new File(fileVariables.getExtentDir());
			if (!(file.exists())) {
				boolean extentFolderStatus = file.mkdirs();
				if (extentFolderStatus == true) {
					new Extent().initReport(fileVariables.getExtentDir());

				}

				else
					BaseTest.logger.error("--> Extent folder not created");
			}

		} catch (Exception e) {
			BaseTest.logger.info("Inside on start catch block" + e.getMessage());
			e.printStackTrace();
		}

		// Setting ScreenShot Report Location
		/*
		 * try { File screenShot = new File(FileVariables.getScreenShotPath()); if
		 * (!(screenShot.exists())) { boolean screenshotFolderStatus =
		 * screenShot.mkdirs(); if (screenshotFolderStatus == true)
		 * BaseTest.logger.info("Screenshot folder created");
		 * 
		 * else BaseTest.logger.info("Screenshot folder not created"); } } catch
		 * (Exception e) { e.printStackTrace(); }
		 */

	}

}
