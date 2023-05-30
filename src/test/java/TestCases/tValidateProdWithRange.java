package TestCases;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import PageObjectModel.ValidateProdWithRange;
import Resources.BaseClass;
import Resources.Constant;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//6.	logintohttps://www.amazon.in/
//7.	clickonallbuttonsonsearch& selectElectronics.
//8.	searchfordellcomputers
//9.	apply the filterofrangeRs30000 to50000
//10.	Validatealltheproductsonthefirst2pagesareshownintherangeofRs30000to50000
//11.	printalltheproductsonthe first2pages whoseratingis 5outof5
//12.	addthe firstproductwhose ratingis5outof5tothewishlist.(Createanewwishlist)
//13.	Validatetheproductisaddedto thewishlist

@Test
public class tValidateProdWithRange extends BaseClass {

	String userName;
	String password;
	
	tValidateProdWithRange() {
		super("urlValidateProdWithRange");
	}

	@SuppressWarnings("unchecked")
	public void ValidateProducts() throws IOException, InterruptedException, StaleElementReferenceException {

		try {
			initializeDriver();
			String url=prop.getProperty("urlValidateProdWithRange");
			driver.manage().window().maximize();
			driver.get(url);

//			System.out.println("Data access from Excel Sheet:"+"\n");
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\amazonLoginDetails.xlsx");
	        XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(1);
			
			Cell cell = row.getCell(1);
			userName = cell.toString();
			
			Cell cell1 = row.getCell(2);
			password = cell1.toString();
			
			ValidateProdWithRange vProd = new ValidateProdWithRange(driver);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));

//6.	login to https://www.amazon.in/ (with taking login details from excel sheet)

			vProd.getSignIn().click();
			vProd.search("email").sendKeys(userName);
			vProd.search("continue").click();
			vProd.search("password").sendKeys(password);
			vProd.search("signInSubmit").click();

//7.	click on all buttons on search & select Electronics.

			vProd.search("All").click();
			Select objSelect = new Select(vProd.search("All"));
			objSelect.selectByVisibleText(Constant.electronics);

//8.	search for dell computers

			vProd.search("searchDell").sendKeys(Constant.searchDell);
			vProd.search("searchDell").sendKeys(Keys.ENTER);

//9.	apply the filter of range Rs 30000 to 50000

			vProd.search("lowPrice").sendKeys(Constant.lowPrice);
			vProd.search("highPrice").sendKeys(Constant.highPrice);
			vProd.search("go").click();

//10.	Validate all the products on the first 2pages are shown in the range of Rs 30000 to 50000

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.presenceOfElementLocated(vProd.prodFirstPage));
			List<WebElement> listResult = vProd.searchElements("prodFirstPage");

			System.out.println("Print and validate all products price : ");
			System.out.println(listResult.size());

			for (WebElement results : listResult) {
				String value = results.getText();
				System.out.println(value);
				String value1 = value.replaceAll(",", "");
				int price = Integer.parseInt(value1);
				Assert.assertTrue(price >= 30000 && price <= 50000);
			}

			vProd.search("page2").click();

			wait.until(ExpectedConditions.presenceOfElementLocated(vProd.prodSecondPage));
			List<WebElement> listPriceOfPage2 = vProd.searchElements("prodSecondPage");

			System.out.println("Print and validate all products price of page 2 : ");
			System.out.println(listPriceOfPage2.size());

//			listResult.addAll(listResult.size()+1, listPriceOfPage2);

			for (WebElement results : listPriceOfPage2) {
				String value = results.getText();
				System.out.println(value);
				String value1 = value.replaceAll(",", "");
				int price = Integer.parseInt(value1);
				Assert.assertTrue(price >= 30000 && price <= 50000);
			}
			
//11.	print all the products on the first 2 pages whose rating is 5 out of 5

			List<WebElement> listRating = vProd.searchElements("rating");

			System.out.println("Print and validate all 5 Star Rating produtcs : ");
			System.out.println(listRating.size());
			for (WebElement results : listRating) {
				System.out.println(" Product name : ");
				String value = results.getText();
				System.out.println(value);
			}

//12.	add the first product whose rating is 5 out of 5 to the wish list.(Create a new wish list)

			System.out.println(" First 5 star product : " + listRating.get(0));
			listRating.get(0).click();

//			screenShot(driver, "First5StarRatingProduct");
			
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait1.until(ExpectedConditions.presenceOfElementLocated(vProd.wishListdrp));
//			vProd.search("wishListdrp").click();
			vProd.search("createWishList").click();
			vProd.search("btnCreateWishList").click();
			vProd.search("viewWishList").click();
			
//			Actions act = new Actions(driver);
//			
//			Actions builder = new Actions(driver);
//            Action mouseOverHome = builder
//                    .moveToElement(wait1.until(ExpectedConditions.presenceOfElementLocated(vProd.wishList)))
//                    .build();
             
  //            mouseOverHome.perform(); 
            
//			JavascriptExecutor js = (JavascriptExecutor) driver;  
//    		js.executeScript("arguments[0].click();", vProd.search("wishListdrp"));
			 
//			WebElement l = driver.findElement(By.name("//div[@id='addToWishlist_feature_div']"));
//		      //JavaScript Executor to click element
//		      JavascriptExecutor j = (JavascriptExecutor) driver;
//		      j.executeScript("arguments[0].click();", l);

//13.	Validate the product is added to the wish list

			Assert.assertEquals(null, listResult.get(0));

		} catch (Exception e) {

			System.out.println(e.toString());
		}
	}
	
	
	
	
}