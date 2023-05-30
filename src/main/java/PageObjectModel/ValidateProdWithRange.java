package PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidateProdWithRange
{
	public WebDriver driver;

	By search=By.xpath("//textarea[@title='Search']");
	By signIn = By.xpath("//a[@data-nav-ref='nav_ya_signin']");
	By email = By.xpath("//input[@type='email']");
	By contin = By.xpath("//input[@id='continue']");
	By password = By.xpath("//input[@type='password']");
	By signInSubmit = By.xpath("//input[@id='signInSubmit']");
	By all = By.xpath("//select[@aria-describedby='searchDropdownDescription']");
	By electronics = By.xpath("//select[@aria-describedby='searchDropdownDescription']");	
	By searchDell = By.xpath("//input[@id='twotabsearchtextbox']");
	By lowPrice = By.xpath("//input[@id='low-price']");
	By highPrice = By.xpath("//input[@id='high-price']");
	By go = By.xpath("//input[@class='a-button-input']");
	public By prodFirstPage = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//span[@class='a-price-whole']");
	public By prodSecondPage = By.xpath("//span[@data-component-type='s-search-results']//span[@class='a-price-whole']");
	By page2 = By.xpath("//a[@aria-label='Go to page 2']");
	By rating = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//span[contains(text(),'5.0 out of 5 stars')]/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/div/h2/a");
	public By wishList = By.xpath("(//form[@id='addToCart'])[2]");
//	public By wishList = By.xpath("//input[@id='add-to-wishlist-button-submit']");
//	public By wishList = By.xpath("//span[@id='wishListMainButton']//span[@id='al-add-to-list-button-early-click-span']");
	public By wishListdrp = By.xpath("//input[@id='add-to-wishlist-button']");
	public By createWishList = By.xpath("//a[@id='atwl-dd-create-list']");
	public By btnCreateWishList = By.xpath("//input[@class='a-button-input a-declarative']");
	public By viewWishList = By.xpath("//a[contains(text(),'View Wish List')]']");
	
	public ValidateProdWithRange(WebDriver driver) 
	{ 
		this.driver = driver;
	}
	
	public WebElement search(String serachBy)
	{
		if (serachBy.equalsIgnoreCase("email"))
			return driver.findElement(email);
		else if (serachBy.equalsIgnoreCase("continue"))
			return driver.findElement(contin);
		else if (serachBy.equalsIgnoreCase("password"))
			return driver.findElement(password);
		else if (serachBy.equalsIgnoreCase("signInSubmit"))
			return driver.findElement(signInSubmit);
		
		else if (serachBy.equalsIgnoreCase("All"))
			return driver.findElement(all);
		else if (serachBy.equalsIgnoreCase("electronics"))
			return driver.findElement(electronics);
		else if (serachBy.equalsIgnoreCase("searchDell"))
			return driver.findElement(searchDell);
		else if (serachBy.equalsIgnoreCase("lowPrice"))
			return driver.findElement(lowPrice);
		else if (serachBy.equalsIgnoreCase("highPrice"))
			return driver.findElement(highPrice);
		else if (serachBy.equalsIgnoreCase("go"))
			return driver.findElement(go);
		else if (serachBy.equalsIgnoreCase("page2"))
			return driver.findElement(page2);
		else if (serachBy.equalsIgnoreCase("wishListdrp"))
			return driver.findElement(wishListdrp);
		else if (serachBy.equalsIgnoreCase("createWishList"))
			return driver.findElement(createWishList);
		else if (serachBy.equalsIgnoreCase("btnCreateWishList"))
			return driver.findElement(btnCreateWishList);
		else if (serachBy.equalsIgnoreCase("viewWishList"))
			return driver.findElement(viewWishList);
		
		return null;
	}
	
	public List<WebElement> searchElements(String serachBy)
	{
		if (serachBy.equalsIgnoreCase("prodFirstPage"))
			return driver.findElements(prodFirstPage);
		else if (serachBy.equalsIgnoreCase("prodSecondPage"))
			return driver.findElements(prodSecondPage);
		else if (serachBy.equalsIgnoreCase("rating"))
			return driver.findElements(rating);
		
		return null;
		
	}
	
	
	public WebElement getSignIn()
	{
		
		return driver.findElement(signIn);
		
	}

	

	
}