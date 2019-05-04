package Sel1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ReceiptNotebyTestNG {
	
		public String baseUrl = "http://192.168.1.17:8080/POLOXY/jsp/login.jsp#";
	    String driverPath = "C:\\geckodriver.exe";
	    public WebDriver driver ;
	
	@Test
	public void Login() throws InterruptedException {
		
		System.out.println("launching firefox browser"); 
	    System.setProperty("webdriver.firefox.marionette", driverPath);
	    driver = new FirefoxDriver();
	    driver.get(baseUrl);
		
		String UID = "uname";
		String Database = "bmsp";
		String UserName = "e0010";
		String PID = "pwd";
		String Password = "admin";
		String ModuleID = "imgcbf";
		String TopHeadingID = "ACCOUNTINGLi";
		String FormID = "Item Receipt Note";
		new Login().GetUnit0(driver, UID, Database, UserName, PID, Password, ModuleID, TopHeadingID, FormID);
		}
		
	@Test
	public void ModalWindow() {
		if(driver.findElement(By.xpath("/html/body/div[3]/div[2]")).getText().contains("Branch"+"Voucher"))
	           {
	           		System.err.println("Test Case Fail --> After Selecting Branch Voucher Field should shown.");
	           }
	           	if((driver.findElement(By.xpath("/html/body/div[3]/div[2]")).getText().contains("Branch"))
	    		   &&(!driver.findElement(By.xpath("/html/body/div[3]/div[2]")).getText().contains("Voucher")))
	           	{
	           		System.out.println("Test Case Pass --> After Selecting Branch Voucher Field should shown.");
	       	    }
	}
}

	











	     /*  
	        String xpath1 = "/html/body/div[3]/div[2]/div";
			new Method().starmand(driver, xpath1);
	       
	       driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();
	       
	        String Alert = "Test Case - Fail --> Alert is not present - Please Select Branch";
			String EqualAlert = "Please Select Branch";
			new Method().GetUnit5(driver, Alert, EqualAlert);
			Thread.sleep(1000);
			driver.switchTo().alert().dismiss();
			
			driver.findElement(By.xpath("//div[@id='dialogModal']/div")).sendKeys(Keys.ESCAPE);
			String Alert1 = "Test Case - Fail --> Alert is not present - Please Select Branch Name From List..!";
			String EqualAlert1 = "Please Select Branch Name From List..!";
			new Method().GetUnit5(driver, Alert1, EqualAlert1);
			Thread.sleep(1000);
			driver.switchTo().alert().dismiss();
			
			   String expectedTooltip = "Select Branch";
			   String xpath = "//div[@id='dialogModal']/div[2]/select";
			   new Method().GetUnit4(driver, xpath, expectedTooltip);
			
			   String EntryBranch = "Head Office";
				driver.findElement(By.id("diaSelect")).sendKeys(EntryBranch);
		
		
		
		
		try {
			int globalCount = 0;
			PreparedStatement pstmt = con.prepareStatement("select voucher_id,voucher_name from vouchercreation where voucher_type='V13' and (branch_id='HO' or branch_id is null) \r\n" + 
					"        and (vou_module_id='1' or vou_module_id is null) \r\n" + 
					"        union select voucher_id, voucher_name from vouchercreation where voucher_id='V13' \r\n" + 
					"        and case when (select hide_main_voucher from currentdate)=1 then ((select count(voucher_id) from vouchercreation where voucher_type='V13' \r\n" + 
					"        and (branch_id='HO' or branch_id is null)=0))  else 1=1  end ;");
			Statement calst = con.createStatement();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				globalCount++;
			}
			System.out.println(" Total Vouchers are "+ globalCount);
			if(globalCount>1)
			{
				System.out.println("If Branch has MORE THAN ONE sub Voucher --> Voucher Field should shown in Mdoel Window");
				
					System.out.println("Voucher Is Present");
					
					String Tooltip_Voucher = "/html/body/div[3]/div[2]/div[3]/div[2]/select";
					new Method().starmand(driver, Tooltip_Voucher);
					
					driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();
					
			        String VoucherAlert = "Test Case - Fail --> Alert is not present - Please Select Voucher Name";
					String VoucherEqualAlert = "Please Select Voucher Name";
					new Method().GetUnit5(driver, VoucherAlert, VoucherEqualAlert);
					
					driver.findElement(By.xpath("/html/body/div[3]/div[2]")).sendKeys(Keys.ESCAPE);
					String VoucherAlert1 = "Test Case - Fail --> Alert is not present - Please Select Voucher Name From List..!";
					String VoucherEqualAlert1 = "Please Select Voucher Name From List..!";
					new Method().GetUnit5(driver, VoucherAlert1, VoucherEqualAlert1);
					
					driver.findElement(By.id("vouchers")).sendKeys("Receipt Note");
					driver.findElement(By.className("button_img_save")).click();
					
			}
		else if(globalCount == 1)
		{
			System.out.println("If Branch has ONLY ONE sub Voucher --> Voucher Field should not shown in Mdoel Window");
		}
		else if(globalCount == 0) {
			System.out.println("If Branch has NO sub Voucher --> Voucher Field should not shown in Mdoel Window");
		}
			calst.close();
			}
	
		 catch (Exception e) {
			System.out.println("fail because of Exception "+e);
		}
		
	}
}*/


