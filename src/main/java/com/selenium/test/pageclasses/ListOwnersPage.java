package com.selenium.test.pageclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.test.baseclasses.BasePage;
import com.selenium.test.baseclasses.TopMenuClass;

public class ListOwnersPage extends BasePage {

	public TopMenuClass topMenu;

	public ListOwnersPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(xpath = "//h2")
	public WebElement ownersTitle;

	@FindBy(xpath = "//*[@title='Next']")
	public WebElement nextBtn;

	@FindBy(xpath = "//*[@title='Last']")
	public WebElement lastBtn;

	@FindBy(xpath = "//*[@title='Previous']")
	public WebElement previousBtn;

	@FindBy(xpath = "//*[@title='First']")
	public WebElement firstBtn;

	@FindBy(xpath = "//*[@id=\"owners\"]")
	public WebElement ownerTable;

	public void exploreAllPageOwners() {

		List<WebElement> pagesTag = driver.findElements(By.xpath("//div/span"));
		for (int i = 0; i < pagesTag.size() - 7; i++) {
			logger.log(Status.INFO, "Clicking Next Button");
			nextBtn.click();
			logger.log(Status.PASS, "Clicked Next Button");
			waitLoad(2);
		}
		// do {
		//
		// logger.log(Status.INFO, "Clicking Next Button");
		// nextBtn.click();
		// logger.log(Status.PASS, "Clicked Next Button");
		//// ListOwnersPage listOwnersPage = new ListOwnersPage(driver, logger);
		//// PageFactory.initElements(driver, listOwnersPage);
		// waitLoad(2);
		// nextBtn = driver.findElement(By.xpath("//a[@title='Next']"));
		//// return listOwnersPage;
		// } while (nextBtn.isDisplayed());

		// while (nextBtn.isEnabled()) {
		// logger.log(Status.INFO, "Clicking Next Button");
		// nextBtn.click();
		// logger.log(Status.PASS, "Clicked Next Button");
		//// ListOwnersPage listOwnersPage = new ListOwnersPage(driver, logger);
		// PageFactory.initElements(driver, ListOwnersPage.class);
		// waitLoad(2);
		// nextBtn = driver.findElement(By.xpath("//a[@title='Next']"));
		//// return listOwnersPage;
		// }

	}

	public void checkTitleListOwners(String expectedTitle) {
		String actualTitle = ownersTitle.getText();
		Assert.assertEquals(actualTitle, expectedTitle);
		logger.log(Status.PASS, "The expected Title is : " + expectedTitle + " and the actual is : " + actualTitle);
	}

	public void checkOwnersLastNameMatch(String lastName) {
		try {
			List<WebElement> ownerRows = driver.findElements(By.xpath("//*[@id='owners']/tbody/tr"));
			// List<WebElement> ownerRows =
			// ownerTable.findElements(By.xpath("/tbody/tr"));
			for (WebElement row : ownerRows) {
				List<WebElement> ownerColumns = row.findElements(By.tagName("td"));
				WebElement ownerName = ownerColumns.get(0);
				Assert.assertTrue(ownerName.getText().contains(lastName));
				logger.log(Status.PASS, "The owner : " + ownerName.getText() + " - contains de lastname : " + lastName);
			}
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	@SuppressWarnings("finally")
	public OwnerInfoPage getOwnerInfo() {
		try {
			// List<WebElement> ownerRows =
			// driver.findElements(By.xpath("//*[@id='owners']/tbody/tr"));
			// List<WebElement> ownerColumns =
			// ownerRows.get(0).findElements(By.tagName("td"));
			// WebElement ownerName = ownerColumns.get(0);
			WebElement ownerName = driver.findElement(By.xpath("//*[@id=\"owners\"]/tbody/tr[1]/td[1]/a"));
			logger.log(Status.INFO, "Clicking Owner link of : " + ownerName.getText());
			ownerName.click();

		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
		finally {
			logger.log(Status.PASS, "Clicked Owner link");
			OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver, logger);
			PageFactory.initElements(driver, ownerInfoPage);
			return ownerInfoPage;
		}

	}

}
