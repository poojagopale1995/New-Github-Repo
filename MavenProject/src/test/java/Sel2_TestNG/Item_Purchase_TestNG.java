package Sel2_TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.annotations.Until;

import Sel1.Login;
import Sel1.Method;

public class Item_Purchase_TestNG {

	public WebDriver driver ;
	
	@BeforeTest
	public void Login() throws InterruptedException {

		System.out.println("launching firefox browser"); 
		System.setProperty("webdriver.firefox.marionette", "C:\\\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get("http://192.168.1.17:8080/POLOXY/jsp/login.jsp#");
		
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		

		String UID = "uname";
		String Database = "bmsp";
		String UserName = "e0100";
		String PID = "pwd";
		String Password = "admin";
		String ModuleID = "imgcbf";
		String TopHeadingID = "ACCOUNTINGLi";
		String FormID = "Item Purchase";
		new Login().GetUnit0(driver, UID, Database, UserName, PID, Password, ModuleID, TopHeadingID, FormID);
	}
	
	/*@AfterTest
	public void Close_driver() {
		driver.close();
	}*/
	
	@Test	
	public void Item_Purchase() throws InterruptedException{
		
		
		Item_Purchase_TestNG obj = new Item_Purchase_TestNG();
		WebDriverWait wait = new WebDriverWait(driver, 1000);

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(By.id("diaSelect")).click();
		driver.findElement(By.id("diaSelect")).sendKeys("head Office");

		driver.findElement(By.id("diaSelect")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("vouchers")).sendKeys("Purchase");

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();

		for(int i = 1; i<=5; i++)
		{
			/*WebElement Date = driver.findElement(By.id("day"));
			WebElement Month = driver.findElement(By.id("month"));
			WebElement Year = driver.findElement(By.id("year"));
			
			Select select1 = new Select(Date);
			select1.selectByValue("10");
			
			Select select2 = new Select(Month);
			select2.selectByValue("oct");
			
			Select select3 = new Select(Year);
			select3.selectByValue("2018");*/
			
			/*//new Method().Drop_Down_Date(driver);
			Thread.sleep(1000);
			driver.findElement(By.id("day")).sendKeys("01");
			Thread.sleep(500);
			driver.findElement(By.id("month")).sendKeys("oct");
			Thread.sleep(500);
			driver.findElement(By.id("year")).sendKeys("2018");
*/
			driver.navigate().refresh();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("partyName")));
			
			WebElement Auto_party = driver.findElement(By.id("partyName"));
			
			if(Auto_party.isDisplayed()) {
				Thread.sleep(500);
			Auto_party.sendKeys("sandeep");     						/// change name if wants to change TAX Type
			Thread.sleep(1000);
			Auto_party.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
			Thread.sleep(1000);
			System.out.println("p");
			}
			else {
				System.out.println("Not present");
			}
			
			
			String Item_Name1 = "Maize";
			int j = 1;
			int  Quantity1 = j++;
			String Rate1 = "16.895";
			String Godown1 = "Head Feed Godown";
			obj.Item_Details(driver,Item_Name1,Quantity1,Rate1,Godown1, 1);

			String Item_Name2 = "salt";
			j = 2;
			int  Quantity2 = j++;
			String Rate2 = "10";
			String Godown2 = "Head Feed Godown";
			obj.Item_Details(driver,Item_Name2,Quantity2,Rate2,Godown2,2);
		
			
			///SUBMIT
				
			driver.findElement(By.id("ins")).click();

			driver.findElement(By.id("check")).click();

			String SUBMITExpected = "Are You Sure To Submit ?";
			new Method().Validatins_of_Alert(driver, SUBMITExpected);
			Thread.sleep(1000);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("alert_message")));

			new Method().alert_message(driver);
			
			 if(!driver.switchTo().alert().equals(0)) {
			
			String alert = driver.switchTo().alert().getText();
			if(alert.contains("Print"))
			{
				driver.switchTo().alert().dismiss();
			}
			else {
				System.err.println("error");
			}
			}
			else {
				System.out.println("NO Print at the time of SUBMIT");
			}
		}
	}
	
	private void Item_Details(WebDriver driver, String Item_Name,int quantity, String rate,String Godown, int i) throws InterruptedException {

		WebElement hidden = driver.findElement(By.id("cnt"));
		String Value = hidden.getAttribute("value");
		Integer.parseInt(Value);

		WebElement ITEM = driver.findElement(By.id("itemName"+i));
		ITEM.sendKeys(Item_Name);
		Thread.sleep(1000);
		ITEM.sendKeys(Keys.ARROW_DOWN);
		ITEM.sendKeys(Keys.ENTER);

		Thread.sleep(1000);

		WebElement MinQty = driver.findElement(By.id("quantity"+i));
		MinQty.sendKeys(String.valueOf(quantity));

		
		WebElement Rate1 = driver.findElement(By.id("prate"+i));
		Rate1.clear();
		Rate1.sendKeys(String.valueOf(rate));

		driver.findElement(By.id("godown"+i)).sendKeys(Godown);
		if(i<2)
		{
			WebElement New_Row = driver.findElement(By.id("row_Net_Amount"+i));
			New_Row.sendKeys(Keys.ENTER);	
		}
		

	}
	
	
}



