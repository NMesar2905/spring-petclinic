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

public class AddOwnersPage extends BasePage {

	public TopMenuClass topMenu;

	public AddOwnersPage(WebDriver driver, ExtentTest logger) {
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

	@FindBy(xpath = "//*[text()='Add Owner']")
	public WebElement addOwnerBtn;

	@FindBy(xpath = "//label[text()='First Name']/..//span[text()='no debe estar vacío']")
	public WebElement errorFirstName;

	@FindBy(xpath = "//label[text()='Last Name']/..//span[text()='no debe estar vacío']")
	public WebElement errorLasttName;

	@FindBy(xpath = "//label[text()='Address']/..//span[text()='no debe estar vacío']")
	public WebElement errorAddress;

	@FindBy(xpath = "//label[text()='City']/..//span[text()='no debe estar vacío']")
	public WebElement errorCity;

	@FindBy(xpath = "//label[text()='Telephone']/..//span[@class='help-inline']")
	public WebElement errorTelephone;

	public OwnerInfoPage createNewOwnerComplete(String firstName, String lastName, String address, String city,
			String telephone) {
		try {
			firstNameTextBox.sendKeys(firstName);
			logger.log(Status.PASS, "Entered the First Name : " + firstName);
			lastNameTextBox.sendKeys(lastName);
			logger.log(Status.PASS, "Entered the Last Name : " + lastName);
			addressTextBox.sendKeys(address);
			logger.log(Status.PASS, "Entered the Address : " + address);
			cityTextBox.sendKeys(city);
			logger.log(Status.PASS, "Entered the City : " + city);
			telephoneTextBox.sendKeys(telephone);
			logger.log(Status.PASS, "Entered the Telephone : " + telephone);
			logger.log(Status.INFO, "Clicking Add Owner Button");
			addOwnerBtn.click();
			logger.log(Status.PASS, "Clicked Add Owner Button");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver, logger);
		PageFactory.initElements(driver, ownerInfoPage);
		return ownerInfoPage;

	}

	public AddOwnersPage createNewOwnerIncomplete(String firstName, String lastName, String address, String city,
			String telephone) {
		if (firstNameTextBox.getText().isEmpty() || lastNameTextBox.getText().isEmpty()
				|| addressTextBox.getText().isEmpty() || cityTextBox.getText().isEmpty()
				|| telephoneTextBox.getText().isEmpty()) {

			try {
				firstNameTextBox.sendKeys(firstName);
				logger.log(Status.PASS, "Entered the First Name : " + firstName);
				lastNameTextBox.sendKeys(lastName);
				logger.log(Status.PASS, "Entered the Last Name : " + lastName);
				addressTextBox.sendKeys(address);
				logger.log(Status.PASS, "Entered the Address : " + address);
				cityTextBox.sendKeys(city);
				logger.log(Status.PASS, "Entered the City : " + city);
				telephoneTextBox.sendKeys(telephone);
				logger.log(Status.PASS, "Entered the Telephone : " + telephone);
				logger.log(Status.INFO, "Clicking Add Owner Button");
				addOwnerBtn.click();
				logger.log(Status.PASS, "Clicked Add Owner Button");
			}
			catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		AddOwnersPage addOwnersPage = new AddOwnersPage(driver, logger);
		PageFactory.initElements(driver, addOwnersPage);
		return addOwnersPage;
	}

	public void checkNameErrorMessage() {
		try {
			Assert.assertTrue(errorFirstName.isDisplayed());
			logger.log(Status.PASS, "The error Name message is displayed");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void checkTelephoneErrorMessage() {
		try {
			Assert.assertTrue(errorTelephone.isDisplayed());
			logger.log(Status.PASS, "The error Telephone message is displayed");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
