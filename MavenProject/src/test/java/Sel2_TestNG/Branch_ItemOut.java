package Sel2_TestNG;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Sel1.Login;

public class Branch_ItemOut {
	WebDriver driver = new FirefoxDriver();
	Actions Action = new Actions(driver);
	
	
	
  @BeforeTest
  public void Login() throws InterruptedException {

		System.setProperty("webdriver.firefox.marionette", "C:\\\\geckodriver.exe");

		driver.get("http://192.168.1.17:8080/POLOXY/jsp/login.jsp#");

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		String UID = "uname";
		String Database = "bmsp";
		String UserName = "e0100";		//Indore Branch
		String PID = "pwd";
		String Password = "admin";
		String ModuleID = "imgcbf";
		String TopHeadingID = "ACCOUNTINGLi";
		String FormID = "Branch Item Out";
		new Login().GetUnit0(driver, UID, Database, UserName, PID, Password, ModuleID, TopHeadingID, FormID);
	}
 @Test
 public void Branch_Selection() throws InterruptedException
 {
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 WebElement Branch = driver.findElement(By.id("branchItemOut"));
	 
	 Select select = new Select(Branch);
	 select.selectByValue("HO");
	 
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/input")).click();
	 
	String ModelWindoBranch =  driver.findElement(By.id("entry_branch_id")).getAttribute("value");
	System.out.println(ModelWindoBranch);
	
	String FormBranch = driver.findElement(By.id("entry_branch_id")).getAttribute("Value");
	System.out.println(FormBranch);
	
	 if(ModelWindoBranch.equals(FormBranch))
	 {
		 System.out.println("Test case Pass");
	 }
	 else{
		 System.out.println("Test case Fail");
	 }
 }
 
@Test
public void DateValidation() throws IOException
{
	WebElement Date = driver.findElement(By.id("date"));
	
	Date.clear();
	Date.sendKeys("14/06/2019");
	Date.sendKeys(Keys.TAB);
	
	String ExpectedAlert = "Date should be Less/Equal to current date and within Financial Year";
	String alert = driver.switchTo().alert().getText();
	if(driver.switchTo().alert() != null)
	{
		if(ExpectedAlert.equals(alert))
		{
			System.out.println("Date Validation Passed");
		}
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\Users\\pooja\\Desktop\\ScreenShots"));
	}
	else{
		System.out.println("Date Validation Failed");
	}
	
}
 
}
