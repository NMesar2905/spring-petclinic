package com.selenium.test.pageclasses;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.TopMenuClass;

public class NewVisitPage extends BasePage {

	public TopMenuClass topMenu;

	public NewVisitPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(id = "date")
	public WebElement visitDateTextBox;

	@FindBy(id = "description")
	public WebElement visitDescriptionTextBox;

	@FindBy(xpath = "//*[text()='Add Visit']")
	public WebElement addVisitBtn;

	@FindBy(xpath = "//h2")
	public WebElement newVisitTitle;

	public OwnerInfoPage createNewVisit(String visitDate, String visitDescription) {
		visitDescriptionTextBox.sendKeys(visitDescription);
		logger.log(Status.PASS, "Entered the visit description: " + visitDescription);

		logger.log(Status.INFO, "Setting the visit date : '" + visitDate + "' in the field");
		clickDataPicker(visitDateTextBox, visitDate);
		logger.log(Status.INFO, "Set the visit date : '" + visitDate + "' in the field");

		logger.log(Status.INFO, "Clicking Add Visit Button");
		addVisitBtn.click();
		logger.log(Status.PASS, "Clicked Add Visit Button");

		OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver, logger);
		PageFactory.initElements(driver, ownerInfoPage);
		return ownerInfoPage;
	}

	public void checkVisitFormTitle() {
		try {
			Assert.assertTrue(newVisitTitle.isDisplayed());
			logger.log(Status.PASS, "The title visit form: '" + newVisitTitle.getText() + "' is displayed");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
