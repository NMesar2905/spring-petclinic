package com.selenium.test.testclasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.BaseTest;
import com.selenium.test.baseclasses.TopMenuClass;
import com.selenium.test.pageclasses.AddOwnersPage;
import com.selenium.test.pageclasses.FindOwnersPage;
import com.selenium.test.pageclasses.HomePage;
import com.selenium.test.pageclasses.OwnerInfoPage;

public class OwnersTests extends BaseTest {

	TopMenuClass topMenu;
	FindOwnersPage findOwnersPage;
	HomePage homePage;
	AddOwnersPage addOwnersPage;
	OwnerInfoPage ownerInfoPage;

	@Test
	public void createOwnerTest() {
		logger = report.createTest("Create Owner");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		addOwnersPage = findOwnersPage.addOwner();
		ownerInfoPage = addOwnersPage.createNewOwnerComplete("Nicolas", "Mesa", "Cll 33 # 19-105", "Medellín",
				"3172792403");
		ownerInfoPage.checkOwnerName("Nicolas Mesa");
		takeScreenShot();
	}

	@Test
	public void createOwnerFirstNameEmptyTest() {
		logger = report.createTest("Create Owner: First Name Empty");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		addOwnersPage = findOwnersPage.addOwner();
		addOwnersPage = (AddOwnersPage) addOwnersPage.createNewOwnerIncomplete("", "Mesa", "Cll 33 # 19-105", "Medellín",
				"3172792403");
		addOwnersPage.checkNameErrorMessage();
		takeScreenShot();
	}

	@Test
	public void createOwnerVerifyTelephoneTest() {
		logger = report.createTest("Create Owner: Verify Telephone");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		addOwnersPage = findOwnersPage.addOwner();
		addOwnersPage = (AddOwnersPage) addOwnersPage.createNewOwnerIncomplete("Nicolas", "Mesa", "Cll 33 # 19-105", "Medellín",
				"ABCDEFG");
		addOwnersPage.checkTelephoneErrorMessage();
		takeScreenShot();
	}

}
