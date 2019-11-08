package com.atmecs.automation.testscripts;

import java.io.IOException;

import org.junit.Assert;
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
	public void addingPdtsToCart() throws IOException, InterruptedException {
		 String input = "$500";
		 String result =input .replaceAll("[$-+.^:,]","");
		 System.out.println(result);
	    PropertiesFileReader.loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
        PageActionsScroll pageactscroll=new PageActionsScroll();
		
        String actualtitle=driver.getTitle();
        String expectedtitle=PropertiesFileReader.gettingPropertyFileData("expdata.title");
        Assert.assertEquals(expectedtitle, actualtitle);
        PropertiesFileReader.loadingPropertyFile(FilePath.LOCATORS_FILE);
		PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox"));
		PageActions.sendKeys(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox"),"iphone");
        PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchbtn"));
		
        WebElement addtocartelement= driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.addtocartbtn")));
        PageActionsScroll.scrollDown(driver,"700");
        pageactscroll.scrolDownTillElementOccurs(driver, addtocartelement);
        addtocartelement.click();
       Thread.sleep(3000);
        addtocartelement.click();
        
        log.info("iphone of quantity two added to cart");
        
        PageActionsScroll.scrollUp(driver,"-700");
    	WebElement searchboxelement =driver.findElement(By.cssSelector(PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox")));
        driver.navigate().to("http://tutorialsninja.com/demo/");
      //  PageActions.explicitwait(driver,By.cssSelector(""));
        PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox"));
    	PageActions.sendKeys(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchinputbox"),"MacBook Air");
    	PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.searchbtn"));
    	
    	PropertiesFileReader.loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
    	PageActionsScroll.scrollDown(driver,"700");
    	int pdttwoqty=Integer.parseInt(PropertiesFileReader.gettingPropertyFileData("expdata.secpdtqty"));
    	PropertiesFileReader.loadingPropertyFile(FilePath.LOCATORS_FILE);
        for(int pdtqtyindex=0;pdtqtyindex<pdttwoqty;pdtqtyindex++) {
    	PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.addtocartbtn"));
        }
    	PageActionsScroll.scrollUp(driver,"-700");
    	PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.gotocartbtn"));
    	
    	PageActions.click(driver, PropertiesFileReader.gettingPropertyFileData("loc.viewcart"));
    	
    	
	}
	@Test
	public void verifyCart() throws IOException {
		PropertiesFileReader.loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
		PropertiesFileReader.gettingPropertyFileData("expdata.firstpdtqty");
		int pdtoneqty=Integer.parseInt(PropertiesFileReader.gettingPropertyFileData("expdata.firstpdtqty"));
		int pdttwoqty=Integer.parseInt(PropertiesFileReader.gettingPropertyFileData("expdata.secpdtqty"));
		String pdtnamearray[]= {"iPhone","MacBook Air"};
	     PropertiesFileReader.loadingPropertyFile(FilePath.LOCATORS_FILE);
			String  arrayactualpdtincart[] = null;
			arrayactualpdtincart[0]=driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.iphonetext"))).getText();
		    arrayactualpdtincart[1]=driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.macbookairtext"))).getText();

	 Assert.assertEquals(pdtnamearray[0],arrayactualpdtincart[0]);
	 Assert.assertEquals(pdtnamearray[1],arrayactualpdtincart[1]);

	 PropertiesFileReader.loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	 String pricearray[] = null;
	 pricearray[0]=driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.pdtoneunitprice"))).getText();
	 pricearray[1]=driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.pdttwounitprice"))).getText();
	 pricearray[2]=driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("locpdtonetotprice"))).getText();
	 pricearray[3]=driver.findElement(LocatorSelector.separatingLocators(PropertiesFileReader.gettingPropertyFileData("loc.pdttwottotalprice"))).getText();
	// String pricearray[]= {driver.findElement(By.cssSelector("))d
	 for(int priceindex = 0;priceindex<pricearray.length;priceindex++) {
	// String resultunitprice[] = PropertiesFileReader.gettingPropertyFileData("");
	// resultunitprice[priceindex] = pricearray[priceindex].replaceAll("[$-+.^:,]","");
	 
	 	 }
     
	}
	}	



