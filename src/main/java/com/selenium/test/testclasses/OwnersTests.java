package com.selenium.test.testclasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.BaseTest;
import com.selenium.test.baseclasses.TopMenuClass;
import com.selenium.test.pageclasses.AddOwnersPage;
import com.selenium.test.pageclasses.EditOwnerPage;
import com.selenium.test.pageclasses.FindOwnersPage;
import com.selenium.test.pageclasses.HomePage;
import com.selenium.test.pageclasses.ListOwnersPage;
import com.selenium.test.pageclasses.OwnerInfoPage;

public class OwnersTests extends BaseTest {

	TopMenuClass topMenu;

	FindOwnersPage findOwnersPage;

	HomePage homePage;

	AddOwnersPage addOwnersPage;

	OwnerInfoPage ownerInfoPage;

	ListOwnersPage listOwnersPage;

	EditOwnerPage editOwnerPage;

	// @Test
	public void createOwnerTest() {
		logger = report.createTest("[TC-01] : Create Owner");
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

	// @Test
	public void findAllOwnersTest() {
		logger = report.createTest("[TC-02] : Find All Owners");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		listOwnersPage = findOwnersPage.clickFindOwnerBtn();
		listOwnersPage.checkTitleListOwners("Owners");
		listOwnersPage = listOwnersPage.exploreAllPageOwners();
	}

	// @Test
	public void searchOwnerByLastNameTest() {
		logger = report.createTest("[TC-03] : Search Owner by Lastname");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		listOwnersPage = findOwnersPage.searchOwnerLastName("Da");
		listOwnersPage.checkTitleListOwners("Owners");
		listOwnersPage.checkOwnersLastNameMatch("Da");
		takeScreenShot();
	}

	// @Test
	public void getOwnerInfoTest() {
		logger = report.createTest("[TC-04] : Get Owner Info");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		listOwnersPage = findOwnersPage.clickFindOwnerBtn();
		listOwnersPage.checkTitleListOwners("Owners");
		listOwnersPage = listOwnersPage.exploreAllPageOwners();
		ownerInfoPage = listOwnersPage.getOwnerInfo();
		ownerInfoPage.checkOwnerInformationTitle();
		takeScreenShot();
	}

	// @Test
	public void updateOwnerTest() {
		logger = report.createTest("[TC-05] : Update owner");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		listOwnersPage = findOwnersPage.clickFindOwnerBtn();
		listOwnersPage.checkTitleListOwners("Owners");
		listOwnersPage = listOwnersPage.exploreAllPageOwners();
		ownerInfoPage = listOwnersPage.getOwnerInfo();
		editOwnerPage = ownerInfoPage.clickUpdateOwnerBtn();
		ownerInfoPage = editOwnerPage.modifyOwnerInfo("Natalia", "Mejia", null, null, null);
		ownerInfoPage.checkSuccessMessage();
		takeScreenShot();
	}

	// @Test
	public void createOwnerVerifyTelephoneTest() {
		logger = report.createTest("[TC-09] : Add Owner: Verify Telephone");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		addOwnersPage = findOwnersPage.addOwner();
		addOwnersPage = (AddOwnersPage) addOwnersPage.createNewOwnerIncomplete("Nicolas", "Mesa", "Cll 33 # 19-105",
				"Medellín", "ABCDEFG");
		addOwnersPage.checkTelephoneErrorMessage();
		takeScreenShot();
	}

	// @Test
	public void createOwnerFirstNameEmptyTest() {
		logger = report.createTest("[TC-10] : Add Owner: First Name Empty");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		addOwnersPage = findOwnersPage.addOwner();
		addOwnersPage = (AddOwnersPage) addOwnersPage.createNewOwnerIncomplete("", "Mesa", "Cll 33 # 19-105",
				"Medellín", "3172792403");
		addOwnersPage.checkNameErrorMessage();
		takeScreenShot();
	}

	// @Test
	public void findOwnersNonExistentTest() {
		logger = report.createTest("[TC-14] : Find Owners: Non-existent Lastname");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		findOwnersPage = findOwnersPage.searchNonExistentLastName("Araque");
		takeScreenShot();
	}

	// @Test
	public void findOwnersExactLastNameTest() {
		logger = report.createTest("[TC-15] : Find Owners: Exact Lastname");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		ownerInfoPage = findOwnersPage.searchOwnerExactLastName("Escobito");
		ownerInfoPage.checkOwnerLastName("Escobito");
		takeScreenShot();
	}

}
