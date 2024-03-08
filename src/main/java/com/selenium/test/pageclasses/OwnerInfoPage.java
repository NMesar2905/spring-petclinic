package com.selenium.test.pageclasses;

import java.util.List;

import javax.xml.xpath.XPath;

import org.apache.commons.exec.ExecuteException;
import org.hibernate.annotations.processing.Find;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.TopMenuClass;

public class OwnerInfoPage extends BasePage {

	public TopMenuClass topMenu;

	public OwnerInfoPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(xpath = "//th[text()='Name']/../td")
	public WebElement ownerName;

	@FindBy(xpath = "//th[text()='Address']/../td")
	public WebElement ownerAddress;

	@FindBy(xpath = "//th[text()='City']/../td")
	public WebElement ownerCity;

	@FindBy(xpath = "//th[text()='Telephone']/../td")
	public WebElement ownerTelephone;

	@FindBy(xpath = "//div/div/a[1]")
	public WebElement editOwnerBtn;

	@FindBy(xpath = "//div/div/a[2]")
	public WebElement addNewPetBtn;

	@FindBy(xpath = "//h2")
	public WebElement ownerInformationTitle;

	@FindBy(id = "success-message")
	public WebElement successMessage;

	@FindBy(xpath = "//table[2]")
	public WebElement petsTableInfo;

	public String getOwnerName() {
		return ownerName.getText();
	}

	public void checkOwnerName(String ownerName) {
		try {
			Assert.assertEquals(getOwnerName(), ownerName);
			logger.log(Status.PASS,
					"The Expected name is : '" + ownerName + "' - The Actual name is : '" + getOwnerName() + "'");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void checkOwnerLastName(String ownerName) {
		try {
			Assert.assertTrue(getOwnerName().contains(ownerName));
			logger.log(Status.PASS,
					"The name : '" + getOwnerName() + "' - Contains the lastname : '" + ownerName + "'");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void checkOwnerInformationTitle() {
		try {
			System.out.println(ownerInformationTitle.getText());
			logger.log(Status.INFO, "Checking Owner Information Title");
			Assert.assertEquals(ownerInformationTitle.getText(), "Owner Information");
			logger.log(Status.PASS, "Actual title is : '" + ownerInformationTitle.getText()
					+ "' expected title is 'Owner Information'");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void checkSuccessMessage() {
		try {
			Assert.assertTrue(successMessage.isDisplayed());
			logger.log(Status.PASS, "The message is showed and the content is : '" + successMessage.getText() + "'");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public EditOwnerPage clickUpdateOwnerBtn() {
		try {
			logger.log(Status.INFO, "Clicking Edit Owner Button");
			editOwnerBtn.click();
			logger.log(Status.PASS, "Clicked Edit Owner Button");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		EditOwnerPage editOwnerPage = new EditOwnerPage(driver, logger);
		PageFactory.initElements(driver, editOwnerPage);
		return editOwnerPage;
	}

	public AddUpdatePetPage clickAddNewPetBtn() {
		try {
			logger.log(Status.INFO, "Clicking Add New Pet Button");
			addNewPetBtn.click();
			logger.log(Status.PASS, "Clicked Add New Pet Button");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		AddUpdatePetPage addNewPetPage = new AddUpdatePetPage(driver, logger);
		PageFactory.initElements(driver, addNewPetPage);
		return addNewPetPage;
	}

	public AddUpdatePetPage clickEditPet(int numberPet) {
		System.out.println(petsTableInfo.findElement(By.xpath("//tbody/tr[1]")).getAttribute("outerHTML"));
		try {
			WebElement editPetLink = selectPetRow(numberPet).findElement(By.xpath("//table//td[1]/a"));
			logger.log(Status.INFO, "Clicking Edit Pet Link");
			editPetLink.click();
			logger.log(Status.PASS, "Clicked Edit Pet Link");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		AddUpdatePetPage addNewPetPage = new AddUpdatePetPage(driver, logger);
		PageFactory.initElements(driver, addNewPetPage);
		return addNewPetPage;
	}

	public NewVisitPage clickNewPetVisit(int numberPet) {
		try {
			WebElement editPetLink = selectPetRow(numberPet).findElement(By.xpath("//table//td[2]/a"));
			logger.log(Status.INFO, "Clicking New Visit Link");
			editPetLink.click();
			logger.log(Status.PASS, "Clicked New Visit Link");
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

		NewVisitPage newVisitPage = new NewVisitPage(driver, logger);
		PageFactory.initElements(driver, newVisitPage);
		return newVisitPage;
	}

	private WebElement selectPetRow(int numberPet) {
		List<WebElement> petRowsInfo = driver.findElements(By.xpath("//table[2]/tbody/tr"));
		WebElement petRowInfo = null;
		try {
			petRowInfo = petRowsInfo.get(numberPet - 1);
			String petName = petRowInfo.findElement(By.xpath("//dd[1]")).getText();
			logger.log(Status.INFO, "The selected pet is: " + petName);
		}
		catch (Exception e) {
			reportFail("The owner doesn't have that pet number. '" + getOwnerName() + "' only have '"
					+ petRowsInfo.size() + "' pets");
		}
		return petRowInfo;
	}

}
