package TestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import PageObjectModel.SearchAndPrintResult;
import Resources.BaseClass;
import Resources.Constant;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import PageObjectModel.ValidateProdWithRange;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//1.	Launchthe browser.
//2.	OpenURL-http://www.google.com
//3.	Enterthekeyword"amazon"in thesearchbar
//4.	printallthesearchresults
//5.	Clickon thelink whichtakes youtotheamazonloginpage.
//6.	logintohttps://www.amazon.in/

public class tSearchAndPrintResult extends BaseClass {

	tSearchAndPrintResult() {
	
		super("urlSearchandPrint");

		// TODO Auto-generated constructor stub
	}


	
	@Test	
	public void searchResultAndloginToAmazon() throws IOException, InterruptedException {
		
		SoftAssert assertion = new SoftAssert();
		SearchAndPrintResult spr = new SearchAndPrintResult(driver);
		try {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
		
		//Enterthekeyword"amazon"in thesearchbar
		
		spr.search().sendKeys(Constant.search);
		spr.search().sendKeys(Keys.ENTER);

		// print all search results

		List<WebElement> listResult = spr.getSearchResult();

		System.out.println("Print all search resut : ");
		System.out.println(listResult.size());

		for (WebElement results : listResult) {
			String value = results.getText();

			System.out.println(value);
		}

		// Click on the link which takes you to the amazon login page.
		
		spr.getAmazonLink().click();

		// Assertion: Verify login is successful to https://www.amazon.in/
		String currentURL = driver.getCurrentUrl();
		System.out.println("The current URL is: : " + currentURL);
		assertion.assertEquals(currentURL, currentURL.startsWith("https://www.amazon.in/"),
				"Logged into expected url...");
		

	} catch(Exception e) {
   	 System.out.println(e.toString());
    }
	}
}