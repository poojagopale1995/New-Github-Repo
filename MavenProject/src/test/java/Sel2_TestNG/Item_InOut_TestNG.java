package Sel2_TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Sel1.Login;
import Sel1.Method;

public class Item_InOut_TestNG {

	public WebDriver driver ;
	
	@BeforeTest
	public void Login() throws InterruptedException {

		System.out.println("launching firefox browser"); 
		System.setProperty("webdriver.firefox.marionette", "C:\\\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get("http://192.168.1.17:8080/POLOXY/jsp/login.jsp#");

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		String UID = "uname";
		String Database = "a0091";
		String UserName = "e0001";		//Indore Branch
		String PID = "pwd";
		String Password = "admin";
		String ModuleID = "imgcbf";
		String TopHeadingID = "DAILY_ACTIVITYLi";
		String FormID = "Item InOut";
		new Login().GetUnit0(driver, UID, Database, UserName, PID, Password, ModuleID, TopHeadingID, FormID);
	}

	@Test	
	public void Item_InOut() throws InterruptedException{
		Item_InOut_TestNG obj = new Item_InOut_TestNG();
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 1000);

		//For Branch

		/*driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS); 
		driver.findElement(By.id("diaSelect")).click();
		driver.findElement(By.id("diaSelect")).sendKeys("head Office");

		driver.findElement(By.id("diaSelect")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("vouchers")).sendKeys("Purchase");

		driver.findElement(By.xpath("//input[@class='button_img_save']")).click();*/

		for(int i = 1; i<=50; i++)
		{
			driver.navigate().refresh();
			//		new Method().Drop_Down_Date(driver);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("date")));

			driver.findElement(By.id("date")).sendKeys("10");
			driver.findElement(By.id("month")).sendKeys("oct");
			driver.findElement(By.id("year")).sendKeys("2018");

			WebElement Farmer_name = driver.findElement(By.id("farmer_name"));
			WebElement Shed_Name = driver.findElement(By.id("shed_no"));
			WebElement Batch_Name = driver.findElement(By.id("batch"));

			Farmer_name.sendKeys("Afsar Poultry Farm");     						/// change name if wants to change TAX Type

			
			Thread.sleep(1000);
			
			action.sendKeys(Keys.TAB);
			
			Thread.sleep(1000);
			action.moveToElement(Shed_Name).click().build().perform();
			action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(1000);
			
			action.moveToElement(Batch_Name).click().build().perform();
			action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			
			
			String farmer_Name1 = "aadil Poultry Farm";
			String shed_Name1 = "Aadil";
			String Item_Name1 = "finisher";
			int j = 1;
			int Quantity = j++;
			obj.Item_Details(driver,farmer_Name1,shed_Name1,Item_Name1,Quantity,1);

			String farmer_Name2 = "Bhagya shree poultry farm";
			String shed_Name2 = "Aarohi";
			String Item_Name2 = "Cofrex dl";
			j = 2;
			int Quantity1 = j++;
			obj.Item_Details(driver,farmer_Name2,shed_Name2,Item_Name2,Quantity1,2);


			///SUBMIT

			driver.findElement(By.id("ins")).click();

//			driver.findElement(By.id("check")).click();

			String SUBMITExpected = "Are you sure to Submit ?";
			new Method().Validatins_of_Alert(driver, SUBMITExpected);
			Thread.sleep(1000);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("normal_alert")));

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

	private void Item_Details(WebDriver driver, String farmer_Name,String shed_Name, String Item_Name,int Quantity, int i) throws InterruptedException {

		Actions action = new Actions(driver);
		
		/*	WebElement hidden = driver.findElement(By.id("cnt"));
		String Value = hidden.getAttribute("value");
		Integer.parseInt(Value);*/
		
		
		
		WebElement Farmer_Name = driver.findElement(By.id("farmer"+i));
		action.click(Farmer_Name).build().perform();
		Thread.sleep(1000);
		Farmer_Name.sendKeys(farmer_Name);
		Thread.sleep(500);

		action.sendKeys(Keys.TAB).build().perform();
		
		Thread.sleep(1000);
		
		WebElement Shed_Name = driver.findElement(By.id("shedno"+i));
		action.click(Shed_Name).build().perform();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		Thread.sleep(500);
		
		WebElement Item_name = driver.findElement(By.id("feedname"+i));
		Item_name.sendKeys(String.valueOf(Item_Name));

		driver.findElement(By.id("qty"+i)).sendKeys(String.valueOf(Quantity));
		if(i<2)
		{
			WebElement New_Row = driver.findElement(By.id("qty"+i));
			New_Row.sendKeys(Keys.ENTER);	
		}
	}


}
