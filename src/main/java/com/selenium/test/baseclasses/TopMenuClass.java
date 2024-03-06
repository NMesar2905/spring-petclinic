package com.selenium.test.baseclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.test.pageclasses.FindOwnersPage;
import com.selenium.test.pageclasses.HomePage;
import com.selenium.test.pageclasses.VeterinariansPage;

public class TopMenuClass extends BasePage {

	public TopMenuClass(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//a[@title='home page']")
	public WebElement homeBtn;

	@FindBy(xpath = "//a[@title='find owners']")
	public WebElement findOwnersBtn;

	@FindBy(xpath = "//a[@title='veterinarians']")
	public WebElement veterinariansBtn;

	public HomePage clickHomeBtn() {
		logger.log(Status.INFO, "Clicking the Home Button");
		homeBtn.click();
		logger.log(Status.PASS, "Clicked the Home Button");
		HomePage homePage = new HomePage(driver, logger);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}

	public FindOwnersPage clickFindOwnersBtn() {
		logger.log(Status.INFO, "Clicking the Find Owners Button");
		findOwnersBtn.click();
		logger.log(Status.PASS, "Clicked the Find Owners Button");
		FindOwnersPage findOwnersPage = new FindOwnersPage(driver, logger);
		PageFactory.initElements(driver, findOwnersPage);
		return findOwnersPage;
	}

	public VeterinariansPage clickVeterinariansBtn() {
		logger.log(Status.INFO, "Clicking the Veterinarians Button");
		veterinariansBtn.click();
		logger.log(Status.PASS, "Clicked the Veterinarians Button");
		VeterinariansPage veterinariansPage = new VeterinariansPage(driver, logger);
		PageFactory.initElements(driver, veterinariansPage);
		return veterinariansPage;
	}

}
