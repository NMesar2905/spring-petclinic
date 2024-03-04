package com.selenium.test.baseclasses;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.test.pageclasses.HomePage;
import com.selenium.test.utilities.DateUtils;

public class BasePage extends BaseTest {

	public ExtentTest logger;

	public BasePage(WebDriver driver, ExtentTest logger) {
		this.logger = logger;
		this.driver = driver;
	}

	/****************** Open Application ***********************/
	public HomePage openApplication() {
		logger.log(Status.INFO, "Opening the WebSite");
		driver.get("http://localhost:8080/");
		logger.log(Status.PASS, "Succesfully Opened the http://localhost:8080/");
		HomePage landingPage = new HomePage(driver, logger);
		PageFactory.initElements(driver, landingPage);
		return landingPage;
	}

	/****************** Get Page Title ***********************/
	public void getTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/****************** Verify Element is Present ***********************/
	public void veriyElementIsDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				reportPass("Password Box is Displayed");
			} else {
				reportFail("Password box is not appeared");
			}

		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/*************** Reporting Functions ***************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/*************** Capture Screenshot ****************/
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

		String fileName = DateUtils.getTimeStamp() + ".png";
		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "/ScreenShots/" + fileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
