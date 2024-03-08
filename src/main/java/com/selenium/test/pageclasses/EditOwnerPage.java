package com.selenium.test.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.TopMenuClass;

public class EditOwnerPage extends BasePage {

	public TopMenuClass topMenu;

	public EditOwnerPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(id = "firstName")
	public WebElement firstNameTextBox;

	@FindBy(id = "lastName")
	public WebElement lastNameTextBox;

	@FindBy(id = "address")
	public WebElement addressTextBox;

	@FindBy(id = "city")
	public WebElement cityTextBox;

	@FindBy(id = "telephone")
	public WebElement telephoneTextBox;

	@FindBy(xpath = "//*[text()='Update Owner']")
	public WebElement updateOwnerBtn;

	public OwnerInfoPage modifyOwnerInfo(String firstName, String lastName, String address, String city,
			String telephone) {
		try {
			if (haveValues(firstName)) {
				logger.log(Status.INFO, "Clear First Name field");
				firstNameTextBox.clear();
				firstNameTextBox.sendKeys(firstName);
				logger.log(Status.PASS, "Entered the First Name : " + firstName);
			}
			if (haveValues(lastName)) {
				logger.log(Status.INFO, "Clear Last Name field");
				lastNameTextBox.clear();
				lastNameTextBox.sendKeys(lastName);
				logger.log(Status.PASS, "Entered the Last Name : " + lastName);
			}
			if (haveValues(address)) {
				logger.log(Status.INFO, "Clear Address field");
				addressTextBox.clear();
				addressTextBox.sendKeys(address);
				logger.log(Status.PASS, "Entered the Address : " + address);
			}
			if (haveValues(city)) {
				logger.log(Status.INFO, "Clear City field");
				cityTextBox.clear();
				cityTextBox.sendKeys(city);
				logger.log(Status.PASS, "Entered the City : " + city);
			}
			if (haveValues(telephone)) {
				logger.log(Status.INFO, "Clear Telephone field");
				telephoneTextBox.clear();
				telephoneTextBox.sendKeys(telephone);
				logger.log(Status.PASS, "Entered the Telephone : " + telephone);
			}

			logger.log(Status.INFO, "Clicking Update Owner Button");
			updateOwnerBtn.click();
			logger.log(Status.PASS, "Clicked Update Owner Button");

		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver, logger);
		PageFactory.initElements(driver, ownerInfoPage);
		return ownerInfoPage;

	}

}
