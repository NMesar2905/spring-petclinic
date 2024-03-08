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

public class AddUpdatePetPage extends BasePage {

	public TopMenuClass topMenu;

	public AddUpdatePetPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(id = "name")
	public WebElement petNameTextBox;

	@FindBy(id = "birthDate")
	public WebElement birthDateTextBox;

	@FindBy(id = "type")
	public WebElement petTypeSelect;

	@FindBy(xpath = "//*[text()='Add Pet']")
	public WebElement addPetBtn;

	@FindBy(xpath = "//*[text()='Update Pet']")
	public WebElement updatePetBtn;

	@FindBy(xpath = "//*[text()='Name']/..//span[2]")
	public WebElement errorPetName;

	public OwnerInfoPage createNewPet(String petName, String birthDate, String type) {
		petNameTextBox.sendKeys(petName);
		logger.log(Status.PASS, "Entered the pet name: " + petName);

		selectBirthDateUI(birthDate);

		logger.log(Status.INFO, "Selecting value : " + type + " from dropdown");
		selectDropDownValue(petTypeSelect, type);

		logger.log(Status.INFO, "Clicking Add Pet Button");
		addPetBtn.click();
		logger.log(Status.PASS, "Clicked Add Pet Button");

		OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver, logger);
		PageFactory.initElements(driver, ownerInfoPage);
		return ownerInfoPage;
	}

	public void selectBirthDateUI(String birthDate) {
		try {
			logger.log(Status.INFO, "Clicking date field");
			clickDataPickerUI(birthDateTextBox, birthDate);
			String birthDateValueTextBox = birthDateTextBox.getAttribute("value");
			Assert.assertTrue(checkPetBirthDate(birthDate, birthDateValueTextBox));
			logger.log(Status.PASS, "Setting birth date : " + birthDate + " on field");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	private boolean checkPetBirthDate(String birthExpected, String birthActual) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag = false;
		try {
			Date dateExpected = sdf1.parse(birthExpected);
			Date dateActual = sdf2.parse(birthActual);
			if (dateExpected.compareTo(dateActual) == 0) {
				flag = true;
				logger.log(Status.INFO,
						"The expected date is '" + birthExpected + "' and the actual is '" + birthActual + "'");
			}
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		return flag;
	}

	public OwnerInfoPage updatePetInfo(String petName, String birthDate, String type) {
		try {
			if (haveValues(petName)) {
				logger.log(Status.INFO, "Clear Pet Name field");
				petNameTextBox.clear();
				petNameTextBox.sendKeys(petName);
				logger.log(Status.PASS, "Entered the Pet Name : '" + petName + "'");
			}
			if (haveValues(birthDate)) {
				logger.log(Status.INFO, "Changing Birth Date");
				birthDateTextBox.clear();
				birthDateTextBox.sendKeys(Keys.ARROW_LEFT);
				birthDateTextBox.sendKeys(Keys.ARROW_LEFT);
				waitLoad(2);
				clickDataPicker(birthDateTextBox, birthDate);
				logger.log(Status.PASS, "Entered the Birth Date : '" + birthDate + "'");
			}
			if (haveValues(type)) {
				logger.log(Status.INFO, "Changing the pet type to : '" + type + "'");
				selectDropDownValue(petTypeSelect, type);
			}

			logger.log(Status.INFO, "Clicking Update Pet Button");
			updatePetBtn.click();
			logger.log(Status.PASS, "Clicked Update Pet Button");

		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver, logger);
		PageFactory.initElements(driver, ownerInfoPage);
		return ownerInfoPage;

	}

	public AddUpdatePetPage createNewPetIncomplete(String petName, String birthDate, String type) {

		try {
			petNameTextBox.sendKeys(petName);
			logger.log(Status.PASS, "Entered the Pet Name : '" + petName + "'");

			clickDataPicker(birthDateTextBox, birthDate);

			logger.log(Status.INFO, "Selecting value : '" + type + "' from dropdown");
			selectDropDownValue(petTypeSelect, type);

			logger.log(Status.INFO, "Clicking Add Pet Button");
			addPetBtn.click();
			logger.log(Status.PASS, "Clicked Add Pet Button");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
		AddUpdatePetPage addUpdatePetPage = new AddUpdatePetPage(driver, logger);
		PageFactory.initElements(driver, addUpdatePetPage);
		return addUpdatePetPage;
	}

	public void checkPetNameErrorMessage() {
		try {
			Assert.assertTrue(errorPetName.isDisplayed());
			logger.log(Status.PASS, "The error Pet Name message: '" + errorPetName.getText() + "' is displayed");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
