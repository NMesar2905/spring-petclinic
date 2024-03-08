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

	@Test(priority = 0)
	public void createOwnerTest() {
		logger = report.createTest("[TC-01] : Create Owner").assignCategory("Owner-Test");
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

	@Test(priority = 1)
	public void findAllOwnersTest() {
		logger = report.createTest("[TC-02] : Find All Owners").assignCategory("Owner-Test");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		listOwnersPage = findOwnersPage.clickFindOwnerBtn();
		listOwnersPage.checkTitleListOwners("Owners");
		listOwnersPage.exploreAllPageOwners();
	}

	@Test(priority = 2)
	public void searchOwnerByLastNameTest() {
		logger = report.createTest("[TC-03] : Search Owner by Lastname").assignCategory("Owner-Test");
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

	@Test(priority = 3)
	public void getOwnerInfoTest() {
		logger = report.createTest("[TC-04] : Get Owner Info").assignCategory("Owner-Test");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		listOwnersPage = findOwnersPage.clickFindOwnerBtn();
		listOwnersPage.checkTitleListOwners("Owners");
		listOwnersPage.exploreAllPageOwners();
		ownerInfoPage = listOwnersPage.getOwnerInfo();
		ownerInfoPage.checkOwnerInformationTitle();
		takeScreenShot();
	}

	@Test(priority = 4)
	public void updateOwnerTest() {
		logger = report.createTest("[TC-05] : Update owner").assignCategory("Owner-Test");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		listOwnersPage = findOwnersPage.clickFindOwnerBtn();
		listOwnersPage.checkTitleListOwners("Owners");
		listOwnersPage.exploreAllPageOwners();
		ownerInfoPage = listOwnersPage.getOwnerInfo();
		editOwnerPage = ownerInfoPage.clickUpdateOwnerBtn();
		ownerInfoPage = editOwnerPage.modifyOwnerInfo("Natalia", "Mejia", null, null, null);
		ownerInfoPage.checkSuccessMessage();
		takeScreenShot();
	}

	@Test(priority = 5)
	public void createOwnerVerifyTelephoneTest() {
		logger = report.createTest("[TC-09] : Add Owner: Verify Telephone").assignCategory("Owner-Test");
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

	@Test(priority = 6)
	public void createOwnerFirstNameEmptyTest() {
		logger = report.createTest("[TC-10] : Add Owner: First Name Empty").assignCategory("Owner-Test");
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

	@Test(priority = 7)
	public void findOwnersNonExistentTest() {
		logger = report.createTest("[TC-14] : Find Owners: Non-existent Lastname").assignCategory("Owner-Test");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		topMenu = homePage.gettopMenu();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		findOwnersPage = findOwnersPage.searchNonExistentLastName("Araque");
		takeScreenShot();
	}

	@Test(priority = 8)
	public void findOwnersExactLastNameTest() {
		logger = report.createTest("[TC-15] : Find Owners: Exact Lastname").assignCategory("Owner-Test");
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
