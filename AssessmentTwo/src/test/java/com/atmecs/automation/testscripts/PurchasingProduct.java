package com.atmecs.automation.testscripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.atmecs.automations.constants.FilePath;
import com.atmecs.automations.pageactions.PageActions;
import com.atmecs.automations.pageactions.PageActionsScroll;

import com.atmecs.automations.reports.LogReport;
import com.atmecs.automations.testbase.BrowserInvoke;
import com.atmecs.automations.utils.ExcelFileReader;
import com.atmecs.automations.utils.LocatorSelector;
import com.atmecs.automations.utils.PropertiesFileReader;
import com.atmecs.automations.utils.TestDataProvider;

public class PurchasingProduct extends BrowserInvoke {
	LogReport log = new LogReport();

	@Test//(dataProvider = "Shopping Gadgets",dataProviderClass=TestDataProvider.class)
	public void addingPdtsToCart() throws IOException {
	    PropertiesFileReader.loadingPropertyFile(FilePath.LOCATORS_FILE);
        PageActionsScroll pageactscroll=new PageActionsScroll();
		
		PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox"));
		PageActions.sendKeys(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox"),"iphone");
        PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchbtn"));
		
        WebElement addtocartelement= driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.addtocartbtn")));
        PageActionsScroll.scrollDown(driver,"700");
        pageactscroll.scrolDownTillElementOccurs(driver, addtocartelement);
        
        Actions actions= new Actions(driver);
        actions.doubleClick();
        log.info("iphone of quantity two added to cart");
        
        PageActionsScroll.scrollUp(driver,"-700");
    	WebElement searchboxelement =driver.findElement(By.cssSelector(PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox")));
        searchboxelement.sendKeys(Keys.CLEAR);
        PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox"));
    	PageActions.sendKeys(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox"),"MacBook Air");
    	PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchbtn"));
    	
    	
    	 PageActionsScroll.scrollDown(driver,"700");
    	int pdttwoqty=Integer.parseInt(ExcelFileReader.getData(0,1,3));
        for(int pdtqtyindex=0;pdtqtyindex<pdttwoqty;pdtqtyindex++) {
    	PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.addtocartbtn"));
        }
    	PageActionsScroll.scrollUp(driver,"-700");
    	PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.gotocartbtn"));
    	
    	PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.viewcart"));
    	
    	
	}
	public void verifyCart() throws IOException {
		int pdtoneqty=Integer.parseInt(ExcelFileReader.getData(0,1,1));
		int pdttwoqty=Integer.parseInt(ExcelFileReader.getData(0,1,3));
		String pdtnamearray[]= {"iPhone","MacBook Air"};
	
			String  arrayactualpdtincart[] = null;
			arrayactualpdtincart[0]=driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.iphonetext"))).getText();
		    arrayactualpdtincart[1]=driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.iphonetext"))).getText();
		    
	   for(int pdtindex; pdtindex<pdt) 
	}
}

