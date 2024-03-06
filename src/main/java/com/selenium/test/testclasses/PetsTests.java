package com.selenium.test.testclasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.BaseTest;
import com.selenium.test.baseclasses.TopMenuClass;
import com.selenium.test.pageclasses.AddNewPetPage;
import com.selenium.test.pageclasses.AddOwnersPage;
import com.selenium.test.pageclasses.EditOwnerPage;
import com.selenium.test.pageclasses.FindOwnersPage;
import com.selenium.test.pageclasses.HomePage;
import com.selenium.test.pageclasses.ListOwnersPage;
import com.selenium.test.pageclasses.OwnerInfoPage;

public class PetsTests extends BaseTest {

	TopMenuClass topMenu;

	FindOwnersPage findOwnersPage;

	HomePage homePage;

	AddOwnersPage addOwnersPage;

	OwnerInfoPage ownerInfoPage;

	ListOwnersPage listOwnersPage;

	EditOwnerPage editOwnerPage;

	AddNewPetPage addNewPetPage;

	@Test
	public void createNewPetTest() {
		logger = report.createTest("[TC-06] : Create New Pet");
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
		addNewPetPage = ownerInfoPage.clickAddNewPetBtn();
		

		takeScreenShot();
	}

}
