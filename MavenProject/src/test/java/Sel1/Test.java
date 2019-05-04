package Sel1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test{
	public static void main(String args[])
	{
		System.setProperty("webdriver.firefox.marionette", "c:\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		String URL = driver.getCurrentUrl();
		System.out.println("URL "+ URL);
		
		driver.findElement(By.xpath("//input[id='lst-ib']")).sendKeys("assd");
		
	// driver.findElement(By.cssSelector("a.gb_b.gb_ec")).click(); //css=a.gb_b.gb_ec
//	driver.findElement(By.cssSelector("a#gb_70")).click();
		
		
	}
}