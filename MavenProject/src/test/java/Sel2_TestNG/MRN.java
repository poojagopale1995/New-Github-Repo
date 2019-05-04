package Sel2_TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class MRN {
	
 WebDriver driver ;

 @Test
 public void Login()
 {
	 MRN obj = new MRN();
	 System.setProperty("webdriver.firefox.marionette", "C:\\\\geckodriver.exe");
	 driver = new FirefoxDriver();
	 driver.get("http://a0048e0001:admin@192.168.1.22:8080/POLOXY/jsp/login.jsp#");
//	 ex- http://creyate:tom@www.gmail.com
	 
 }
}
