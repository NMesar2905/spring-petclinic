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

public class FindOwnersPage extends BasePage {

	public TopMenuClass topMenu;

	public FindOwnersPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(xpath = "//*[text()=\"Add Owner\"]")
	public WebElement addOwnerBtn;

	@FindBy(xpath = "//*[@id=\"search-owner-form\"]//button")
	public WebElement findOwnerBtn;

	@FindBy(id = "lastName")
	public WebElement lastNameTextBox;

	@FindBy(xpath = "//*[text()='No ha sido encontrado']")
	public WebElement lastNameErrorMessage;

	public void enterOwnerLastName(String lastName) {
		lastNameTextBox.sendKeys(lastName);
		logger.log(Status.PASS, "Entered the lastname : " + lastName);
	}

	public AddOwnersPage addOwner() {
		logger.log(Status.INFO, "Clicking Add Owner Button");
		addOwnerBtn.click();
		logger.log(Status.PASS, "Clicked Add Owner Button");
		AddOwnersPage addOwnersPage = new AddOwnersPage(driver, logger);
		PageFactory.initElements(driver, addOwnersPage);
		return addOwnersPage;
	}

	public ListOwnersPage clickFindOwnerBtn() {
		try {
			logger.log(Status.INFO, "Clicking Find Owners Button");
			findOwnerBtn.click();
			logger.log(Status.PASS, "Clicked Find Owners Button");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		ListOwnersPage listOwnersPage = new ListOwnersPage(driver, logger);
		PageFactory.initElements(driver, listOwnersPage);
		return listOwnersPage;

	}

	public ListOwnersPage searchOwnerLastName(String lastName) {
		try {
			clickAndEnterLastName(lastName);
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		ListOwnersPage listOwnersPage = new ListOwnersPage(driver, logger);
		PageFactory.initElements(driver, listOwnersPage);
		return listOwnersPage;
	}

	public OwnerInfoPage searchOwnerExactLastName(String lastName) {
		try {
			clickAndEnterLastName(lastName);
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver, logger);
		PageFactory.initElements(driver, ownerInfoPage);
		return ownerInfoPage;
	}

	public FindOwnersPage searchNonExistentLastName(String lastName) {
		try {
			clickAndEnterLastName(lastName);
			Assert.assertTrue(lastNameErrorMessage.isDisplayed());
			logger.log(Status.PASS, "The error Last Name message is displayed");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		FindOwnersPage findOwnersPage = new FindOwnersPage(driver, logger);
		PageFactory.initElements(driver, findOwnersPage);
		return findOwnersPage;
	}

	private void clickAndEnterLastName(String lastName) {
		lastNameTextBox.sendKeys(lastName);
		logger.log(Status.PASS, "Entered the lastname : " + lastName);
		logger.log(Status.INFO, "Clicking Find Owners Button");
		findOwnerBtn.click();
		logger.log(Status.PASS, "Clicked Find Owners Button");
	}

}
