package Sel1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ItemReceiptNote extends PurchaseOrder{

	public static void main(String args[]) throws InterruptedException
	{
		System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
		//System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox 50");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://192.168.1.17:8080/POLOXY/jsp/login.jsp#");

		DB_Connection conn= new DB_Connection();
		Connection con = conn.connection;
		Actions Action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		DecimalFormat DecimalFor = new DecimalFormat("0.0");
		int flag = 0; // For Plan MRN

		String UID = "uname";
		String Database = "bmsp";
		String UserName = "e0001";
		String PID = "pwd";
		String Password = "admin";
		String ModuleID = "imgcbf";
		String TopHeadingID = "ACCOUNTINGLi";
		String FormID = "Item Receipt Note";
		new Login().GetUnit0(driver, UID, Database, UserName, PID, Password, ModuleID, TopHeadingID, FormID);

		if((driver.findElement(By.xpath("/html/body/div[3]/div[2]")).getText().contains("Branch"))
				&&(driver.findElement(By.xpath("/html/body/div[3]/div[2]")).getText().contains("Voucher")))
		{
			System.err.println("Test Case Fail --> After Selecting Branch Voucher Field should shown.");
		}

		if((driver.findElement(By.xpath("/html/body/div[3]/div[2]")).getText().contains("Branch"))
				&&(!driver.findElement(By.xpath("/html/body/div[3]/div[2]")).getText().contains("Voucher")))
		{
			System.out.println("Test Case Pass --> After Selecting Branch Voucher Field should shown.");
		}

		String xpath1 = "/html/body/div[3]/div[2]/div";
		new Method().starmand(driver, xpath1);

		String expectedTooltip = "Select Branch";
		String xpath = "/html/body/div[3]/div[2]/div[2]/select";
		new Method().ToolTip(driver, xpath, expectedTooltip);

		//Branch	       
		if(driver.findElement(By.id("diaSelect")).getAttribute("value").contains("-1"))
		{
			driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();

			String EqualAlert = "Please Select Branch";
			new Method().Validatins_of_Alert(driver,EqualAlert);
			Thread.sleep(1000);
			driver.switchTo().alert().dismiss();

			driver.findElement(By.xpath("/html/body/div[3]/div[2]")).sendKeys(Keys.ESCAPE);
			String EqualAlert1 = "Please Select Branch Name From List..!";
			new Method().Validatins_of_Alert(driver, EqualAlert1);
			Thread.sleep(1000);
			driver.switchTo().alert().dismiss();
		}
		else {
			Select B = new Select(driver.findElement(By.id("diaSelect")));
			B.selectByValue("-1");

			driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();

			String EqualAlert = "Please Select Branch";
			new Method().Validatins_of_Alert(driver,EqualAlert);
			Thread.sleep(1000);
			driver.switchTo().alert().dismiss();

			driver.findElement(By.xpath("/html/body/div[3]/div[2]")).sendKeys(Keys.ESCAPE);
			String EqualAlert1 = "Please Select Branch Name From List..!";
			new Method().Validatins_of_Alert(driver,EqualAlert1);
			Thread.sleep(1000);
			driver.switchTo().alert().dismiss(); 
		}

		String EntryBranch = "Head Office";
		driver.findElement(By.id("diaSelect")).sendKeys(EntryBranch);

		//Voucher		
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

				String VoucherEqualAlert = "Please Select Voucher Name";
				new Method().Validatins_of_Alert(driver, VoucherEqualAlert);

				driver.findElement(By.xpath("/html/body/div[3]/div[2]")).sendKeys(Keys.ESCAPE);
				String VoucherEqualAlert1 = "Please Select Voucher Name From List..!";
				new Method().Validatins_of_Alert(driver, VoucherEqualAlert1);

				driver.switchTo().alert().dismiss();

				driver.findElement(By.id("vouchers")).sendKeys("Receipt Note");

				driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();

			}

			else if(globalCount == 0) {
				System.out.println("If Branch has NO sub Voucher --> Voucher Field should not shown in Mdoel Window");
			}
			calst.close();
		}
		catch (Exception e) {
			System.out.println("fail because of Exception "+e);
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();

		//Date	
		// Alert for Month Not ENtered.

		for(int i=0; i<4;i++)
			try {
				Thread.sleep(1000);

				String DateId = "h_curr_date";
				new Method().Date_Validation(driver, DateId);			//date Validation

				driver.findElement(By.id("h_curr_date")).clear();
				String ID_Date = "h_curr_date";
				String Entry_Date = new Method().Entrydate(driver,ID_Date);

				WebElement Auto_party = driver.findElement(By.id("partyName"));
				Auto_party.sendKeys(Party_Name);
				Thread.sleep(1000);
				Auto_party.sendKeys(Keys.ARROW_DOWN);
				Auto_party.sendKeys(Keys.ENTER);

				Thread.sleep(1000);
				String a1 = "01012019";
				WebElement InvoiceDate = driver.findElement(By.name("invoice_date"));
				InvoiceDate.sendKeys(a1);

				String GetEntryDate = Entry_Date.substring(0, 2)+"/"+Entry_Date.substring(2,4)+"/"+Entry_Date.substring(4);
				String GetInvoiceDate = a1.substring(0, 2)+"/"+a1.substring(2, 4)+"/"+a1.substring(4);

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date1 = sdf.parse(GetEntryDate);
					Date date2 = sdf.parse(GetInvoiceDate);

					InvoiceDate.sendKeys(Keys.TAB);

					if(date2.after(date1))
					{
						String ExpectedAlert = "Invoice Date Should be Less Than or Equal To Entry Date..!";
						new Method().Validatins_of_Alert(driver, ExpectedAlert);

						InvoiceDate.sendKeys("01012017");	
					}
					else {
						System.err.println("Test Case - Fail --> Alert is not present ");

					}
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			} catch(StaleElementReferenceException e) {
				e.toString();
				System.out.println("Trying to recover from a stale element :" + e.getMessage());
			}

		WebElement OrderNo = driver.findElement(By.id("OrderNumber"));
		String OrderNo1 = OrderNo.getAttribute("value");


		if(flag ==1)
		{
			//Code for Plane MRN
		}
		else {

			if(!OrderNo1.equals(null))
			{
				driver.findElement(By.id("OrderNumber")).sendKeys(Keys.ARROW_DOWN);
				{
					driver.findElement(By.id("OrderNumber")).sendKeys(Keys.TAB);
					Thread.sleep(1000);

					Select select = new Select(OrderNo);
					String GetOrdereNo = select.getFirstSelectedOption().getText();

					/*if(!GetOrdereNo.equals(""))
					{
						if((driver.findElement(By.id("dueDays")).getAttribute("readony").contains(""))&&
								(driver.findElement(By.id("txt")).getAttribute("readonly").contains("")))
						{
							System.out.println("Test Case - Pass --> Select Order No.-> Due Date and Text Box should be Read Only");

							String TRK = driver.findElement(By.name("txt")).getAttribute("value");

							if((TRK.contains(":"))&&(TRK.contains(GetOrdereNo)))
							{
								System.out.println("Test Case - Pass --> Format of Tracking No. is proper.");
							}
							else {
								System.err.println("Test Case - Fail --> Format of Tracking No. is not proper.");
							}
						}
					}*/
				}
			}
			else {
				System.err.println("Fail");
			}


			Action.sendKeys(Keys.TAB).build().perform();
			driver.findElement(By.cssSelector("div.addrtxt > #ok")).click();
		}

		Thread.sleep(1000);												//Scroll Down
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		int rowCount = Integer.parseInt(driver.findElement(By.id("cnt")).getAttribute("value"));

		double Total_Gross_Amt=0;
		double Total_net_amount = 0;

		if(rowCount>0)
		{
			for(int i=1; i<=rowCount;i++)
			{
				System.out.println("For Row "+ i);							//Expiry and mfg Date
				Thread.sleep(1000);
				String mfg_id = "manufaDate";
				String exp_id = "expiryDate";
//				new Method().Mfg_Exp_Alerts(driver, mfg_id, exp_id, i);


				String BatchID = "batch"+i;								//Batch No.
				int fieldLength = 15;
				String Name = "abc123";
				new Method().Maxlength(driver, BatchID, fieldLength, Name);	

				driver.findElement(By.id("quantity"+i)).click();
				if(driver.findElement(By.cssSelector("#PoSummaryToolTip > fieldset > legend")).isDisplayed())
				{
					System.out.println("Po Summary ToolTip present");

					int Min_Order_Wt_Qty =Integer.parseInt(driver.findElement(By.cssSelector("#PoSummaryToolTip > fieldset > div.addrtxt > input[type=\"text\"]")).getAttribute("value"));
					int Max_Order_Wt_Qty = Integer.parseInt(driver.findElement(By.xpath("//div[@id='PoSummaryToolTip']/fieldset/div[4]/input")).getAttribute("value"));
					int Delivered_wt_Qty = Integer.parseInt(driver.findElement(By.xpath("//div[@id='PoSummaryToolTip']/fieldset/div[6]/input")).getAttribute("value"));
					int Pending_wt_Qty = Integer.parseInt(driver.findElement(By.xpath("//div[@id='PoSummaryToolTip']/fieldset/div[8]/input")).getAttribute("value"));

					String TempQty = "500";
					driver.findElement(By.id("quantity"+i)).sendKeys(TempQty);

					if(Integer.parseInt(TempQty) > Max_Order_Wt_Qty )
					{
						String ExpectedAlert = "Please Enter Quantity Equal Or Less Than="+Max_Order_Wt_Qty;
						new Method().Validatins_of_Alert(driver, ExpectedAlert);
					}

					int Pending_Qty = (Max_Order_Wt_Qty - Delivered_wt_Qty);

					if(Pending_wt_Qty == Pending_Qty)
					{
						System.out.println("Test Case - Pass --> Pending Wt./Qty is Shown Proper");
					}
					else{
						System.err.println("Test Case - Fail --> Pending Wt./Qty is not Shown Proper");
					}

					driver.findElement(By.id("ins")).click();

					String EqualAlert = "Please Enter Item Quantity";
					new Method().Validatins_of_Alert(driver, EqualAlert);


					driver.findElement(By.id("quantity"+i)).sendKeys("15.5555");

					String Expectedalert = "Please Enter Proper Decimal Places";
					new Method().Validatins_of_Alert(driver, Expectedalert);

					String Quantity = String.valueOf(Min_Order_Wt_Qty/3);

					driver.findElement(By.id("quantity"+i)).sendKeys(Quantity);

					String NetQty = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("net_quantity"+i)));

					if(NetQty.equals(Quantity))
					{
						System.out.println("Test Case - Pass --> Billed Qty. and Net Qty is Same");
					}
					else {
						System.err.println("Test Case - Fail --> Billed Qty. and Net Qty should be Same");
					}

					String rate = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("prate"+i)));
					double gross_Amount = (Double.parseDouble(Quantity)*Double.parseDouble(rate));

					DecimalFor.format(Total_Gross_Amt += (gross_Amount)); // Gross Amount
					
					String Discount = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("discount"+i))); //Net Amount
					String IGST = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("igst"+i)));
					String CGST = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("cgst"+i)));
					String SGST = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("sgst"+i)));
					String row_Net_Amount = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("row_Net_Amount"+i)));
					
					if(!IGST.equals(0))
					{
						double IGST_Amount = gross_Amount * (Double.parseDouble(IGST))/100;

						double	Disc_Amt = gross_Amount * (Double.parseDouble(Discount))/100;
						double Net_Amt = (gross_Amount + IGST_Amount) - Disc_Amt;
					
						if(Double.parseDouble(row_Net_Amount) == Net_Amt)
						{
							System.out.println("Test Case - Pass --> Row Net Quantity is proper");
						}
						else {
							System.err.println("Test Case - Fail --> Row Net Quantity should be proper");	
						}	
					}
					else if((!CGST.equals(0)) && (!SGST.equals(0)))
					{
						double CGST_Amount = gross_Amount * (Double.parseDouble(CGST))/100;
						double SGST_Amount = gross_Amount * (Double.parseDouble(SGST))/100;
						
						double Total_Tax = CGST_Amount + SGST_Amount;
						
						double	Disc_Amt = gross_Amount * (Double.parseDouble(Discount))/100;
						
						double Net_Amt = (gross_Amount + Total_Tax) - Disc_Amt;
					
						if(Double.parseDouble(row_Net_Amount) == Net_Amt)
						{
							System.out.println("Test Case - Pass --> Row Net Quantity is proper");
						}
						else {
							System.err.println("Test Case - Fail --> Row Net Quantity should be proper");	
						}	
					}
					
					if(!Discount.equals(0))	
					{
						double	Disc_Amt = gross_Amount * (Double.parseDouble(Discount))/100;	
						Double Net_Amt = gross_Amount - Disc_Amt;
					
						if(Double.parseDouble(row_Net_Amount) == Net_Amt)
						{
							System.out.println("Test Case - Pass --> Row Net Quantity is proper");
						}
						else {
							System.err.println("Test Case - Fail --> Row Net Quantity should be proper");	
						}
					}
					DecimalFor.format(Total_net_amount += Double.parseDouble(row_Net_Amount)); // Net Amount
					
					//Summary
					if(i == rowCount) {
						try {
							String gross_Qty = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("grossAmount"))); //get value of Read only
							if(Total_Gross_Amt == (Double.parseDouble(gross_Qty)))		// Total Gross Amount
							{
								System.out.println("Test Case - Pass --> Gross Amount is Proper");
							}
							else {
								System.err.println("Test Case - Fail --> Gross Amount should be proper");
							}
							
							String Net_Amount = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("netRs"))); 
							if(Total_net_amount == Double.parseDouble(Net_Amount))       // Total Net Amount
							{
								System.out.println("Test Case - Pass --> Net Amount is Proper");
							}
							else {
								System.err.println("Test Case - Fail --> Net Amount should be proper");
							}
						
						}
						catch (NumberFormatException e) {
							System.out.println("wrong");
						}
					}
				}
				else{
					System.err.println("Test Case - Fail --> Purchase Order Summary not Shown");
				}
				driver.findElement(By.id("godown"+i)).sendKeys("head Feed Godown");
			}
		}
		else {
			System.out.println("Something went wrong");
		}
	}
}












