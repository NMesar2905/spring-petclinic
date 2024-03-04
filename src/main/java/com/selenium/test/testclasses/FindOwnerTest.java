package com.selenium.test.testclasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.BaseTest;
import com.selenium.test.baseclasses.TopMenuClass;
import com.selenium.test.pageclasses.FindOwnersPage;
import com.selenium.test.pageclasses.HomePage;


public class FindOwnerTest extends BaseTest{
	
	TopMenuClass topMenu;
	FindOwnersPage findOwnersPage;
	HomePage homePage;
	
	
	@Test
	public void findOwnerTest(){
		logger = report.createTest("Find Existing Owner");
		invokeBrowser("Chrome");
		BasePage pageBase = new BasePage(driver, logger);
		PageFactory.initElements(driver, pageBase);
		homePage = pageBase.openApplication();
		findOwnersPage = topMenu.clickFindOwnersBtn();
		findOwnersPage.enterOwnerLastName("Mesa");
	}
	

}
