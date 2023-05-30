package TestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;
import PageObjectModel.SearchAndPrintResult;
import Resources.BaseClass;
import Resources.Constant;

// Covered all below points in this test.

//1.	Launchthe browser.
//2.	OpenURL-http://www.google.com
//3.	Enterthekeyword"amazon"in thesearchbar
//4.	printallthesearchresults
//5.	Clickon thelink whichtakes youtotheamazonloginpage.

public class tSearchAndPrintResult extends BaseClass {

	protected tSearchAndPrintResult() {
		super("urlSearchandPrint");
		// TODO Auto-generated constructor stub
	}


	
	@Test
	public void searchResultAndloginToAmazon() throws IOException, InterruptedException {

		
		try {

//1.	Launch the browser.
//2.	Open URL-http://www.google.com

			
			initializeDriver();
			String url = prop.getProperty("url");
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

//3.	Enter the keyword "amazon" in the search bar
			SearchAndPrintResult spr = new SearchAndPrintResult(driver);
			
			spr.search().sendKeys(Constant.search);
			spr.search().sendKeys(Keys.ENTER);

//4.	print all the search results

			List<WebElement> listResult = spr.getSearchResult();

			System.out.println("Print all search resut : ");
			System.out.println(listResult.size());

			for (WebElement results : listResult) {
				String value = results.getText();

				System.out.println(value);
			}

//5.	Click on the link which takes you to the amazon login page.

			spr.getAmazonLink().click();

			// Assertion: Verify login is successful to https://www.amazon.in/
			String currentURL = driver.getCurrentUrl();
			Assert.assertTrue(currentURL.startsWith("https://www.amazon.in/"));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}