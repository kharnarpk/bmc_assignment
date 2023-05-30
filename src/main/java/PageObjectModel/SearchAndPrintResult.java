package PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchAndPrintResult
{
	public WebDriver driver;
	
	By search=By.xpath("//*[@title='Search']");
	By searchResult = By.xpath("//div[@class='GyAeWb']//a");
//	By searchResult = By.xpath("//div[@class='eqAnXb FcOujd']//a");
	By amazonLink = By.xpath("(//div[@class='GyAeWb']//a)[1]"); 

	
	public SearchAndPrintResult(WebDriver driver) 
	{ 
		
		this.driver = driver;
		
	}
	
	public WebElement search()
	{
		
		return driver.findElement(search);
		
	}
	
	public WebElement getElement(By eleToSearch)
	{
		
		return driver.findElement(eleToSearch);
		
	}
	
	public List<WebElement> getSearchResult()
	{
		
		return driver.findElements(searchResult);
		
	}
	
	public WebElement getAmazonLink()
	{
		
		return driver.findElement(amazonLink);
		
	}
	

	
}