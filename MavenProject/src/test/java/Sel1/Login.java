package Sel1;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	public void GetUnit0(WebDriver driver, String UID,String Database, String UserName, String PID, String Password , String ModuleID, String TopHeadingID, String FormID) throws InterruptedException {
		driver.manage().window().maximize();

		driver.findElement(By.id(UID)).sendKeys(Database+UserName);
		driver.findElement(By.id(PID)).sendKeys(Password);
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

		driver.findElement(By.id(ModuleID)).click();

		Thread.sleep(1000);

		WebDriverWait wait = new WebDriverWait(driver, 1500);

		WebElement Dialog_Model =	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dialog-modal")));


		if(Dialog_Model.isDisplayed())
		{
			System.out.println("Test Case Pass --> Radio Button are Present to Select Integrated Or Individual");

			driver.findElement(By.id("dialog-modal")).sendKeys(Keys.ESCAPE);
			String EqualAlert = "Please select option...!";
			new Method().Validatins_of_Alert(driver,  EqualAlert);

			String expectedTooltip = "with all division";
			String xpath = "//div[@id='dialog-modal']/div/div/input";
			new Method().ToolTip(driver, xpath, expectedTooltip);

			String expectedTooltip1 = "with login division only";
			String xpath1 = "//div[@id='dialog-modal']/div/div[3]/input";
			new Method().ToolTip(driver, xpath1, expectedTooltip1);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='dialog-modal']/div/div/input"))).click();
			
//			driver.findElement(By.xpath("//div[@id='dialog-modal']/div/div/input")).click();

			driver.findElement(By.id(TopHeadingID)).click();

			driver.findElement(By.id(FormID)).click();
		}
		else {
			driver.findElement(By.id(TopHeadingID)).click();

			Thread.sleep(1000);
			driver.findElement(By.id(FormID)).click();
			Thread.sleep(1000);
		}
		
	}
}
