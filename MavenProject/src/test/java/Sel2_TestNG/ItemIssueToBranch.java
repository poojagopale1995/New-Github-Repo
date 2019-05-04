package Sel2_TestNG;

import org.testng.annotations.Test;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import Sel1.DB_Connection;
import Sel1.Login;
import Sel1.Method;

public class ItemIssueToBranch {

	WebDriver driver = new FirefoxDriver();
	Actions Action = new Actions(driver);
	/*DB_Connection conn = new DB_Connection();
	Connection con =  conn.connection;*/
	WebDriverWait wait = new WebDriverWait(driver, 20);

	@BeforeTest
	public void Login() throws InterruptedException {

		System.setProperty("webdriver.firefox.marionette", "C:\\geckodriver.exe");

		driver.get("http://192.168.1.17:8080/POLOXY/jsp/login.jsp#");

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);



		String UID = "uname";
		String Database = "bmsp";
		String UserName = "e0100";		//Indore Branch
		String PID = "pwd";
		String Password = "admin";
		String ModuleID = "imgcbf";
		String TopHeadingID = "DAILY_ACTIVITYLi";
		String FormID = "Items Issue To Branch";
		new Login().GetUnit0(driver, UID, Database, UserName, PID, Password, ModuleID, TopHeadingID, FormID);
	}

	@Test
	public void DateSelection() throws InterruptedException
	{
		for(int i = 1; i<=3 ; i++){
			driver.findElement(By.id("date")).sendKeys("15");
			driver.findElement(By.id("month")).sendKeys("feb");
			driver.findElement(By.id("year")).sendKeys("2019");

			driver.findElement(By.id("year")).sendKeys(Keys.TAB);

			driver.findElement(By.id("bname")).sendKeys("Head Office");
			//		wait.until(ExpectedConditions.attributeContains((driver.findElement(By.id("i_name1"))), "value", "IT00002"));
			//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("i_name1"))));

			driver.findElement(By.id("bname")).sendKeys(Keys.TAB);

			/*try {
			PreparedStatement pstmt = con.prepareStatement("");
			Statement calst=con.createStatement();
			ResultSet rs = pstmt.executeQuery();
			} catch (Exception e) {
			// TODO: handle exception
			}*/


			/*
			Select select = new Select(driver.findElement(By.id("i_name1")));
			select.selectByValue("IT00002");
			 */

			WebElement Item = driver.findElement(By.id("i_name1"));
			Item.click();

			for(int j =1 ; j<=3 ; j++)
			{
				Item.sendKeys(Keys.ARROW_DOWN);
			}
			Item.sendKeys(Keys.ENTER);
			Item.sendKeys(Keys.TAB);

			Select select = new Select(driver.findElement(By.id("i_name1")));
			System.out.println(select.getFirstSelectedOption().getText());


			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);



			driver.findElement(By.id("c_godown1")).sendKeys("Head Feed Godown");

			WebElement Godown = driver.findElement(By.id("c_godown1"));
			Godown.click();
			Godown.sendKeys(Keys.ARROW_DOWN);
			Godown.sendKeys(Keys.ENTER);
			Godown.sendKeys(Keys.TAB);

			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			driver.findElement(By.id("bags1")).sendKeys("1");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			driver.findElement(By.id("godown1")).sendKeys("Depo Godown");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);



			driver.findElement(By.id("ins")).click();

			String SUBMITExpected = "Are You sure To Submit?";
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
}
