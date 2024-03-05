package com.selenium.test.pageclasses;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.TopMenuClass;



public class OwnerInfoPage extends BasePage{

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
	
	public String getOwnerName() {
		return ownerName.getText();
	}

	public void checkOwnerName(String ownerName) {
		try {
			Assert.assertEquals(getOwnerName(), ownerName);
			logger.log(Status.PASS, "The Expected name is : " + ownerName + " - The Actual name is : " + getOwnerName());		
		}catch(Exception e){
			reportFail(e.getMessage());
		}
	}
}
