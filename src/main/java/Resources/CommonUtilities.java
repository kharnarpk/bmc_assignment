package Resources;

import java.util.Arrays;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class CommonUtilities
{

	public static WebDriver driver;
	
	
//Handle Assertion
	public static void handleAssertions(String actual, String expected, String message)
	{
		SoftAssert a = new SoftAssert();
		String ac = actual;
		String exp = expected;
		a.assertEquals(ac, exp, message);
		a.assertAll();
	}
	
	
//Handle Moverover
	public static void mouseOverHandle(WebElement ele,WebDriver driver) throws InterruptedException
	{
		WebElement ele1=ele;
		Actions action1=new Actions(driver);
		Thread.sleep(2000);
		action1.moveToElement(ele1).perform();
	}


}