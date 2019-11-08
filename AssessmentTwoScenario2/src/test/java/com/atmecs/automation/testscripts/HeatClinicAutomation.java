package com.atmecs.automation.testscripts;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.atmecs.automations.constants.FilePath;
import com.atmecs.automations.pageactions.PageActions;
import com.atmecs.automations.pageactions.PageActionsGet;
import com.atmecs.automations.pageactions.PageActionsScroll;
import com.atmecs.automations.reports.LogReport;
import com.atmecs.automations.testbase.BrowserInvoke;
import com.atmecs.automations.utils.LocatorSelector;
import com.atmecs.automations.utils.PropertiesFileReader;

public class HeatClinicAutomation extends BrowserInvoke {
	LogReport log = new LogReport();

	@Test
	public void shoppingautomation() throws IOException, InterruptedException {

		PageActionsScroll pageactscroll = new PageActionsScroll();
		PropertiesFileReader.loadingPropertyFile(FilePath.SCE2LOCATORS_FILE);

		WebElement[] linktextelements = {
				driver.findElement(
						LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.home"))),
				driver.findElement(LocatorSelector
						.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.hotsauce"))) };

//	        String linktextexpvalstring=PropertiesFileReader.gettingPropertyFileData("expdata.linktext");
//	        cartdettextexpval=linktextexpvalstring.split(",");
//	        for(int index=0;index<linktextelements.size();index++) {
//	        linktextexpval=linktextexpvalstring.split(",");
//	        linktextelements.get(index);
//	        System.out.println(linktextactualarray[index]);
//	        if(!(linktextexpval[index]==linktextactualarray[index])) {
//	        	System.out.println("Footer Linktext text is not matching");
//	        }
		// }

		WebElement merchandiseelement = driver.findElement(
				LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.merchantise")));
		Actions actions = new Actions(driver);
		actions.moveToElement(merchandiseelement).build().perform();
		Thread.sleep(3000);
		actions.sendKeys(Keys.ARROW_DOWN).click();
		// PageActions.click(driver,
		// PropertiesFileReader.gettingPropertyFileData("loc.men"));

		List<WebElement> textelements = driver.findElements(
				LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.viewingmentext")));
		String textactualarray[] = null;
		String textexpval[] = new String[textelements.size()];
		textexpval[0] = "Viewing Mens (1 - 3 ";
		textexpval[1] = "of 3)";
		 
		String linktextexpvalstring=PropertiesFileReader.gettingPropertyFileData("expdata.linktext");
	//	String actualmenfir=driver.findElement(By.cssSelector(#content section header h1 span:first-child)).getText();
		//String actualmensec=driver.findElement(By.cssSelector(#content section header h1 span:first-child)).getText();
		
		//Assert.assertEquals(textexpval[0],actualmenfir+actualmensec);

		PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.buynow"));
		PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("locredcolour"));
		for (int i = 0; i < 3; i++) {
			actions.sendKeys(Keys.TAB);
		}

		PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.personalisedname"));
		PageActions.sendKeys(driver, PropertiesFileReader.gettingPropertyFileData("loc.personalisedname"), "david");

		// PageActions.click(driver,
		// PropertiesFileReader.gettingPropertyFileData("loc.addtocart"));
		driver.findElement(By.cssSelector(".simplemodal-wrap")).click();
		PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.personalisedname"));
		actions.sendKeys(Keys.TAB).click();
		driver.findElement(By.xpath("//a[@title='Close']")).click();
		PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.viewcart"));

		List<WebElement> cartpdtdetelements = driver.findElements(
				LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.cartdet")));
		String cartactualarray[] = null;
		String cartdettextexpval[] = new String[cartpdtdetelements.size()];
		PropertiesFileReader.loadingPropertyFile(FilePath.SCE2EXPECTEDDATA_FILE);
		String cartdettexpvalstring = PropertiesFileReader.gettingPropertyFileData("expdata.detailsincart");

		for (int index = 0; index < cartpdtdetelements.size(); index++) {
			cartdettextexpval = cartdettexpvalstring.split(",");
			cartpdtdetelements.get(index);
			System.out.println(cartdettextexpval[index]);
			if (!(cartdettextexpval[index] == cartdettextexpval[index])) {
				System.out.println(" Cart details is not matching");
			}
		}
		Thread.sleep(3000);
		// PageActions.explicitwait(driver,LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.unitpdtprz")));
		String shtprzact = driver.findElement(
				LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.unitpdtprz")))
				.getText();
		Assert.assertEquals(PropertiesFileReader.gettingPropertyFileData("expdata.shtunitprz"), shtprzact);
		PropertiesFileReader.gettingPropertyFileData(FilePath.SCE2LOCATORS_FILE);
		String shtnameact = driver
				.findElement(LocatorSelector
						.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.pdtnameincart")))
				.getText();
		Assert.assertEquals(PropertiesFileReader.gettingPropertyFileData("expdata.shtname"), shtnameact);

		String shttotprzact = driver.findElement(
				LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.unitpdtprz")))
				.getText();
		PropertiesFileReader.gettingPropertyFileData(FilePath.SCE2EXPECTEDDATA_FILE);

		String input = PropertiesFileReader.gettingPropertyFileData("expdata.shtunitprz");
		String unitprzresult = input.replaceAll("[$-+.^:,]", "");
		int unitprz = Integer.parseInt(unitprzresult);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String sText = js.executeScript("return document.value;").toString();
		System.out.println(sText);

		int quantity = Integer.parseInt(sText);
		int totprz = unitprz * quantity;
		String totalprz = Integer.toString(totprz);
		Assert.assertEquals(totalprz, shttotprzact);

		PageActions.click(driver, "loc.updbtn");
		WebElement uparrows = driver.findElement(LocatorSelector.separatingLocators("loc.incrqty"));

		actions.moveToElement(uparrows).build().perform();

		String sText2 = js.executeScript("return document.value;").toString();
		System.out.println(sText2);
		int sTextvalue = Integer.parseInt(sText2);
		if (!(sTextvalue == 2)) {
			System.out.println("Not updating in the cart");
		}
		driver.quit();
	}
}
