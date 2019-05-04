package Sel1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Item_Sale {

	public static void main(String args[]) throws InterruptedException
	{
		System.setProperty("webdriver.firefox.marionette", "C:\\\\geckodriver.exe");

		WebDriver driver = new FirefoxDriver();
		Item_Sale obj = new Item_Sale();

		driver.get("http://192.168.1.17:8080/POLOXY/jsp/login.jsp#");	

		String UID = "uname";
		String Database = "bmsp";
		String UserName = "e0001";
		String PID = "pwd";
		String Password = "admin";
		String ModuleID = "imgcbf";
		String TopHeadingID = "ACCOUNTINGLi";
		String FormID = "Item Sale";
		new Login().GetUnit0(driver, UID, Database, UserName, PID, Password, ModuleID, TopHeadingID, FormID);

		Thread.sleep(1000);
		String EntryBranch = "Head Office";
		driver.findElement(By.id("diaSelect")).sendKeys(EntryBranch);

		driver.findElement(By.id("diaSelect")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("vouchers")).sendKeys("Sale");

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();

		for(int i = 1; i<=10; i++)
		{
			driver.navigate().refresh();

			//		new Method().Drop_Down_Date(driver);

			WebDriverWait wait = new WebDriverWait(driver, 1000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("date1")));

			driver.findElement(By.id("date1")).sendKeys("10");
			driver.findElement(By.id("date2")).sendKeys("sep");
			driver.findElement(By.id("date3")).sendKeys("2018");

			driver.findElement(By.id("godown")).sendKeys("Head Feed Godown");

			WebElement Auto_party = driver.findElement(By.id("pname"));

			Auto_party.sendKeys("ashwini");     						/// change name if wants to change TAX Type
			Thread.sleep(1000);
			Auto_party.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
			Thread.sleep(1000);

			String Item_Name1 = "Maize";
			String Quantity1 = "11";
			String Rate1 = "16.895";
			obj.Item_Details(driver,Item_Name1,Quantity1,Rate1, 1);


			String Item_Name2 = "salt";
			String Quantity2 = "13";
			String Rate2 = "10";
			obj.Item_Details(driver,Item_Name2,Quantity2,Rate2,2);

			///SUBMIT
			driver.findElement(By.id("ins")).click();

			driver.findElement(By.id("check")).click();

			String SUBMITExpected = "Are You Sure To Submit ?";
			new Method().Validatins_of_Alert(driver, SUBMITExpected);
			Thread.sleep(1000);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("alert_message")));
			
			new Method().alert_message(driver);
		}
	}
	private void Item_Details(WebDriver driver, String Item_Name,String quantity, String rate2, int i) throws InterruptedException {
		// TODO Auto-generated method stub

		WebElement hidden = driver.findElement(By.id("cnt"));
		String Value = hidden.getAttribute("value");
		Integer.parseInt(Value);

		WebElement ITEM = driver.findElement(By.id("iName"+i));
		ITEM.sendKeys(Item_Name);
		Thread.sleep(1000);
		ITEM.sendKeys(Keys.ARROW_DOWN);
		ITEM.sendKeys(Keys.ENTER);

		Thread.sleep(1000);

		WebElement MinQty = driver.findElement(By.id("qty"+i));
		MinQty.sendKeys(String.valueOf(quantity));

		WebElement Rate1 = driver.findElement(By.id("rate"+i));

		Rate1.sendKeys(String.valueOf(rate2));

		if(i<2)
		{
			WebElement New_Row = driver.findElement(By.id("amount"+i));
			New_Row.sendKeys(Keys.ENTER);	
		}
		else {
			System.out.println("not Found");
		}

	}


}
