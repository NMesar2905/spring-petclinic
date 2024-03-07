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

public class AddNewPetPage extends BasePage {

	public TopMenuClass topMenu;

	public AddNewPetPage(WebDriver driver, ExtentTest logger) {
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

	public OwnerInfoPage createNewPet(String petName, String birthDate, String type) {
		petNameTextBox.sendKeys(petName);
		logger.log(Status.PASS, "Entered the pet name: " + petName);
		
		logger.log(Status.INFO, "Clicking date field");
		clickDataPicker(birthDateTextBox,birthDate);
		logger.log(Status.PASS, "Setting birth date : " + birthDate + " on field");
		
		logger.log(Status.INFO, "Selecting value : " + type + " from dropdown"); 
		selectDropDownValue(petTypeSelect, type);
		
		logger.log(Status.INFO, "Clicking Add Pet Button");
		addPetBtn.click();
		logger.log(Status.PASS, "Clicked Add Pet Button");
		
		OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver, logger);
		PageFactory.initElements(driver, ownerInfoPage);
		return ownerInfoPage;
	}
	
	



}
