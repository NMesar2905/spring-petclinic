package com.selenium.test.testclasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.BaseTest;
import com.selenium.test.baseclasses.TopMenuClass;
import com.selenium.test.pageclasses.AddUpdatePetPage;
import com.selenium.test.pageclasses.AddOwnersPage;
import com.selenium.test.pageclasses.EditOwnerPage;
import com.selenium.test.pageclasses.FindOwnersPage;
import com.selenium.test.pageclasses.HomePage;
import com.selenium.test.pageclasses.ListOwnersPage;
import com.selenium.test.pageclasses.NewVisitPage;
import com.selenium.test.pageclasses.OwnerInfoPage;

public class PetsTests extends BaseTest {

	TopMenuClass topMenu;

	FindOwnersPage findOwnersPage;

	HomePage homePage;

	AddOwnersPage addOwnersPage;

	OwnerInfoPage ownerInfoPage;

	ListOwnersPage listOwnersPage;

	EditOwnerPage editOwnerPage;

	AddUpdatePetPage addUpdatePetPage;

	NewVisitPage newVisitPage;

	@Test(priority = 0)
	public void createNewPetTest() {
		logger = report.createTest("[TC-06] : Create New Pet").assignCategory("Pet-Test");
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
		addUpdatePetPage = ownerInfoPage.clickAddNewPetBtn();
		ownerInfoPage = addUpdatePetPage.createNewPet("Coffee", "01/01/2024", "cat");
		ownerInfoPage.checkSuccessMessage();
		takeScreenShot();
	}

	@Test(priority = 1)
	public void updatePetTest() {
		logger = report.createTest("[TC-07] : Updtate Pet").assignCategory("Pet-Test");
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
		addUpdatePetPage = ownerInfoPage.clickEditPet(1);
		ownerInfoPage = addUpdatePetPage.updatePetInfo("Milo", "01/01/2022", "dog");
		ownerInfoPage.checkSuccessMessage();
		takeScreenShot();
	}

	@Test(priority = 2)
	public void createNewVisit() {
		logger = report.createTest("[TC-08] : Create New Visit").assignCategory("Pet-Test");
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
		newVisitPage = ownerInfoPage.clickNewPetVisit(1);
		ownerInfoPage = newVisitPage.createNewVisit("01/01/2022",
				"Pets visit the vet for check-ups, vaccinations, and specific health concerns.");
		ownerInfoPage.checkSuccessMessage();
		takeScreenShot();
	}

	@Test(priority = 3)
	public void petEmptyNameTest() {
		logger = report.createTest("[TC-11] : Add pet: Name Empty").assignCategory("Pet-Test");
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
		addUpdatePetPage = ownerInfoPage.clickAddNewPetBtn();
		addUpdatePetPage = addUpdatePetPage.createNewPetIncomplete("", "29/05/2023", "bird");
		addUpdatePetPage.checkPetNameErrorMessage();
		takeScreenShot();
	}

	@Test(priority = 4)
	public void petSelectBirthDateUITest() {
		logger = report.createTest("[TC-12] : Add pet: Select Birth Date by UI").assignCategory("Pet-Test");
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
		addUpdatePetPage = ownerInfoPage.clickAddNewPetBtn();
		addUpdatePetPage.selectBirthDateUI("01/12/2023");
		takeScreenShot();
	}

	@Test(priority = 5)
	public void createPetAlreadyCreatedTest() {
		logger = report.createTest("[TC-13] : Add pet: Create Pet Already Created").assignCategory("Pet-Test");
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
		addUpdatePetPage = ownerInfoPage.clickAddNewPetBtn();
		addUpdatePetPage = addUpdatePetPage.createNewPetIncomplete("Milo", "29/05/2023", "bird");
		addUpdatePetPage.checkPetNameErrorMessage();
		takeScreenShot();
	}

}
