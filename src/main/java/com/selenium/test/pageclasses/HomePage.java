package com.selenium.test.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.TopMenuClass;



public class HomePage extends BasePage{
	
	public TopMenuClass topMenu;

	public HomePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

}
