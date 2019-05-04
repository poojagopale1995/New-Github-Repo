package TestNgDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class NewTest {
	
	WebDriver driver = new FirefoxDriver();
  @Test
  public void f() {
	  System.setProperty("webdriver.firefox.marionette", "C:\\geckodriver.exe");
	  driver.get("https://www.google.com/");
	  
  }
}
