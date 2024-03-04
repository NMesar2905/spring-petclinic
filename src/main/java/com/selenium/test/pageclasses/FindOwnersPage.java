package com.selenium.test.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public void enterOwnerLastName(String lastName) {
		lastNameTextBox.sendKeys(lastName);
		logger.log(Status.PASS, "Entered the lastname : " + lastName);
	}

}
