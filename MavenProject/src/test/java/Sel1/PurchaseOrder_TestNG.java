package Sel1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.mysql.jdbc.Driver;

public class PurchaseOrder_TestNG {


	static String Party_Name= "Ashwini";
	static String EntryBranch = "Head Office";
	static String Voucher = "Purchase Order";
	WebDriver driver;
	String expectedBranch = "HEAD OFFICE"; 
	int flag = 1;
	String UserName;
	DB_Connection conn=new DB_Connection();
	Connection con = conn.connection;
	PurchaseOrder obj=new PurchaseOrder();
	
	@BeforeTest
	public void Login() throws InterruptedException {

		System.out.println("launching firefox browser"); 
		System.setProperty("webdriver.firefox.marionette", "C:\\\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get("http://192.168.1.17:8080/POLOXY/jsp/login.jsp#");

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		String UID = "uname";
		String Database = "bmsp";  UserName = "e0001";
		String PID = "pwd";
		String Password = "admin";
		String ModuleID = "imgcbf";
		String TopHeadingID = "ACCOUNTINGLi";
		String FormID = "Purchase order";
		new Login().GetUnit0(driver, UID, Database, UserName, PID, Password, ModuleID, TopHeadingID, FormID);
	}

	@Test 
	public void ModelWindowToSelectBranch() throws InterruptedException {
		
		Thread.sleep(1000);
		WebElement Branch = driver.findElement(By.xpath("//div[@id='hidDiv']/div[4]/h3"));
		String actualBranch = Branch.getText();	
		if(actualBranch.equals(expectedBranch))
		{	
			System.out.println("\n");
			System.out.println("LOGIN Branch Is HO, Model window should come to select for Branch");
			if((driver.findElement(By.id("newVoucher")).isDisplayed())
					&&(driver.findElement(By.xpath("//div[@id='dialogModal']/div")).getText().contains("Branch")))
			{
				System.out.println("Test Case Pass --> Branch Model Window should be Present");
			}
			else {
				System.err.println("Test Case - Fail --> LogIn Branch is not HO - model window not present ");
			}
		}

		String xpath1 = "//div[@id='dialogModal']/div";
		new Method().starmand(driver, xpath1);

		driver.findElement(By.xpath("//div[@id='newVoucher']/div[3]/input")).click();

		String EqualAlert = "Please Select Branch";
		new Method().Validatins_of_Alert(driver, EqualAlert);
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();

		driver.findElement(By.xpath("//div[@id='dialogModal']/div")).sendKeys(Keys.ESCAPE);
		String EqualAlert1 = "Please Select Branch Name From List..!";
		new Method().Validatins_of_Alert(driver,EqualAlert1);
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();

		String expectedTooltip = "Select Branch";
		String xpath = "//div[@id='dialogModal']/div[2]/select";
		new Method().ToolTip(driver, xpath, expectedTooltip);


		driver.findElement(By.id("diaSelect")).sendKeys(EntryBranch);

		//*********** Validations For Selecting VOUCHERS	



		WebElement Branch_voucher = driver.findElement(By.id("vouchers"));

		try {
			int globalCount =0;
			PreparedStatement pstmt = con.prepareStatement("select voucher_id,voucher_name from vouchercreation where "+
					"voucher_type='V7' and (branch_id='HO' or branch_id is null) and (vou_module_id='1' or vou_module_id is null) union "+
					"select voucher_id, voucher_name from vouchercreation where voucher_id='V7' and case when (select hide_main_voucher "+
					"from currentdate)=1 then ((select count(voucher_id) from vouchercreation where voucher_type='V7' and (branch_id='HO' or "+
					"branch_id is null))=0 ) else 1=1  end ;");
			Statement calst=con.createStatement();
			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) 
			{
				globalCount++;
			}
			System.out.println("Total Vouchers are ==> "+globalCount);


			if(globalCount>1) {
				System.out.println("If Branch has MORE THAN ONE sub Voucher --> Voucher Field should shown in Mdoel Window");

				System.out.println("Voucher Is Present");

				String Tooltip_Voucher = "/html/body/div[3]/div[2]/div[2]/div/font";
				new Method().starmand(driver, Tooltip_Voucher);

				driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[3]/input")).click();

				String VoucherEqualAlert = "Please Select Voucher Name";
				new Method().Validatins_of_Alert(driver,VoucherEqualAlert);

				driver.findElement(By.xpath("//div[@id='dialogModal']/div")).sendKeys(Keys.ESCAPE);
				String VoucherEqualAlert1 = "Please Select Voucher Name From List..!";
				new Method().Validatins_of_Alert(driver,VoucherEqualAlert1);


				Branch_voucher.sendKeys(Voucher);
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


		driver.findElement(By.xpath("//div[@id='newVoucher']/div[3]/input")).click();


		if(driver.findElement(By.id("entry_branch_id")).getText().equalsIgnoreCase(EntryBranch))
		{
			System.out.println("Test case - Pass --> Selected Branch in Model Window and Branch on Form both are Same");
		}
		else {
			System.err.println("Test case - Fail --> Selected Branch in Model Window and Branch on Form both are NOT Same");
		}


		if(!Branch_voucher.equals(null)==true)
		{
			if(driver.findElement(By.id("entry_voucher_name")).getText().equalsIgnoreCase(Voucher))
			{
				System.out.println("Test case - Pass --> Selected Voucher in Model Window and Voucher on Form both are Same");
			}
			else {
				System.err.println("Test case - Fail --> Selected Voucher in Model Window and Voucher on Form both are NOT Same");
			}
		}
		else {
			System.err.println("Test case - Fail --> Voucher Name is Not Present on Form");
		}


		System.out.println("\n");
		String legendpath="//form[@id='myform1']/div/div[3]/fieldset/legend";
		new Method().Title_and_Legend(driver,legendpath);

	}

	@Test
	public void PO_Type_Radio_Button() {
		if(driver.findElement(By.id("items_purchase")).isSelected())
		{
			System.out.println("Radio Button of ITEM Selected by Default");
			driver.findElement(By.id("asset_purchase")).click();
			if(driver.findElement(By.id("Asset_Ledger_div")).isDisplayed())
			{
				System.out.println("Test case - Pass --> Model Window for Asset is Present");
				driver.findElement(By.xpath("//div[@id='Asset_Ledger_div']/div[3]")).sendKeys(Keys.ESCAPE);

			}
			else {
				System.err.println("Test case - Fail --> Model Window for Asset is Not Present");
			}
		}
		else {
			System.err.println("Test case - Fail --> Radio Button of ITEM Not Selected by Default");
		}
	}

	@Test

	public void Lebel() {
		System.out.println("\n");
		String legendpath1="//form[@id='myform1']/div/fieldset/legend";
		new Method().Title_and_Legend(driver,legendpath1);

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		String Path = "//form[@id='myform1']/div/fieldset/div";


		if(driver.findElement(By.xpath(Path)).getText().equalsIgnoreCase("new Entry"))
		{
			System.out.println("Test Case - Pass --> Label of New Entry is Proper.");
		}
		else 
		{
			System.err.println("Test Case - Fail --> Label of New Entry is Not Proper.");
		}


		System.out.println("\n");
		String legendpath2="//form[@id='myform1']/div/fieldset/div[3]/label";
		new Method().Title_and_Legend(driver,legendpath2);

	}

	@Test
	public void OpenPO_ConfirmPO() {

		if(driver.findElement(By.id("confirm_po")).isSelected())
		{
			System.out.println("Test Case - Pass --> Radio Button of Confirm PO Selected by Default");
			if(driver.findElement(By.id("openPo_list")).isEnabled())
			{
				System.out.println("Test case - Pass --> Field For OPEN PO is Enable");
			}
			else {
				System.out.println("Test case - Fail --> Field For OPEN PO should be Enable for Confirm PO");
			}
		}
		else {
			System.err.println("Test Case - Fail --> Radio Button of Confirm PO Is NOT Selected by Default");
		}


		//Open PO			   
		driver.findElement(By.id("open_po")).click();
		if(driver.findElement(By.id("openPo_list")).isDisplayed())
		{		
			System.err.println("Test case - Fail --> Field For OPEN PO should NOT Display for OPEN PO");
		}
		else 
		{
			System.err.println("Test case - Pass --> Field For OPEN PO should NOT Display for OPEN PO");
		}
	}
	
	@Test
	public void Entry_Date()
	{
		/// Date
		System.out.println("\n");
		String legendpath3="//form[@id='myform1']/div/fieldset/div[5]/label";
		new Method().Title_and_Legend(driver,legendpath3);

		// Query for Date is of last Entry date 
		// query for Current date.


		// Alert for Month Not ENtered.

		driver.findElement(By.id("day")).sendKeys("25");
		driver.findElement(By.id("year")).sendKeys("2019");											
		driver.findElement(By.id("ins")).click();

		String EqualAlert3 = "Please Select Date Less Than Current Date";
		new Method().Validatins_of_Alert(driver, EqualAlert3);

		driver.navigate().refresh();
		driver.findElement(By.id("day")).sendKeys("30");
		driver.findElement(By.id("month")).sendKeys("feb");  
	//	(Uncomment if feb Month is not Selected)

		driver.findElement(By.id("partyName")).click();

		String EqualAlert4 = "Entry Date Is Not Valid..!";
		new Method().Validatins_of_Alert(driver, EqualAlert4);

		String EqualAlert5 = "Invalid Date";
		new Method().Validatins_of_Alert(driver, EqualAlert5);

		
		new Method().Drop_Down_Date(driver);
	}

	
	@Test
	public void Party_Name() throws InterruptedException
	{
		//Party Name
		System.out.println("\n");
		String PartyName_lable = "/html/body/div/div/div[10]/div[2]/form[2]/div/fieldset/div[7]/label/a";
		new Method().Title_and_Legend(driver, PartyName_lable);

		driver.findElement(By.id("partyName")).sendKeys("");
		driver.findElement(By.id("ins")).click();
		
		String PartyEqualAlert = "Please Enter Party Name";
		new Method().Validatins_of_Alert(driver, PartyEqualAlert);

		Thread.sleep(1000);
		driver.findElement(By.id("partyName")).sendKeys("Sandip123");
		Thread.sleep(1000);
		driver.findElement(By.id("ins")).click();
		String PartyEqualAlert1 = "Party does not Exist..!";
		new Method().Validatins_of_Alert(driver, PartyEqualAlert1);

		WebElement PartyName = driver.findElement(By.xpath("//form[@id='myform1']/div/fieldset/div[7]/label/a"));

		Actions actions = new Actions(driver);
		actions.moveToElement(PartyName)
				.sendKeys(Keys.CONTROL)
				.click(PartyName)
				.contextClick().build().perform();

		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL, Keys.TAB);
		System.out.println("Test case - Pass --> Link to open Party Creation Form is Opened.");

		Thread.sleep(2000);

		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"w");


		WebElement Auto_party = driver.findElement(By.id("partyName"));

		
		Auto_party.sendKeys(Party_Name);     						/// change name if wants to change TAX Type
		Thread.sleep(1000);
		Auto_party.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		Thread.sleep(1000);


		if(Auto_party.isDisplayed())
		{
			System.out.println("Test case - Pass --> AutoSuggestion is Proper");
		}

	}
	@Test
	public void Contact_Number()
	{
		//Contact Number
				System.out.println("\n");
				String ALLLegend = "/html/body/div/div/div[10]/div[2]/form[2]/div/fieldset/div[11]/label";
				new Method().Title_and_Legend(driver, ALLLegend);

				String IDm = "suppliercontactNumber";
				new Method().Validations_of_PhoneNo(driver, IDm);
	}
	@Test
	public void Due_Date() {
		//Due Date 
				System.out.println("\n");
				String Due_Date_Label = "/html/body/div/div/div[10]/div[2]/form[2]/div/fieldset/div[16]/label";
				new Method().Title_and_Legend(driver, Due_Date_Label);

				String DueDaysID = "dueDays";
				new Method().Due_Days_Val(driver, DueDaysID);

				String Due_Days_Alert = "Please Enter Due Days/Date";
				new Method().Validatins_of_Alert(driver,Due_Days_Alert);
	}
	
	@Test
	public void Transporter_Details() throws InterruptedException
	{
		String originalHandle = driver.getWindowHandle();

		//Shipping Address
		System.out.println("\n");
		String Address_lable = "/html/body/div/div/div[10]/div[2]/form[2]/div/fieldset[2]/div/label";
		new Method().Title_and_Legend(driver, Address_lable);

		if(driver.findElement(By.id("shipingAddress")).equals(0))
		{
			// Script for Branch Having single Godown --> and we have to enter Shipping Address
		}

		else{
			System.out.println("Switching TAB to Branch Form");

			driver.findElement(By.xpath("//li[@id='SETTINGSLi']/span")).click();
			WebElement s = driver.findElement(By.xpath("//div[@id='SETTINGS']/table/tbody/tr/td[2]/div/div[2]/a"));


			s.sendKeys(Keys.SHIFT);
			s.click();
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL, Keys.TAB);
			System.out.println("Test case - Pass --> BRANCH form is Opened.");

			Thread.sleep(1000);
			driver.navigate().refresh();

			for(String handle : driver.getWindowHandles()) 
			{
				if (!handle.equals(originalHandle)) 
				{
					driver.switchTo().window(handle);
				}
				else {  
					driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
					WebElement Basic_Search = driver.findElement(By.id("branch_name"));
					Basic_Search.sendKeys(expectedBranch);
					Basic_Search.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN);
					Thread.sleep(2000);
					Basic_Search.sendKeys(Keys.ENTER);
					System.out.println("Test Case - Pass --> Branch Retrieved");


					String Adreess = driver.findElement(By.id("al1")).getText();
					String PersonName = driver.findElement(By.id("con_name")).getText();
					String ContactNo = driver.findElement(By.id("c_no")).getText();

					Thread.sleep(1000);
					driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL, Keys.TAB);

					//Shipping Address

					driver.switchTo().window(originalHandle);
					driver.manage().window();
					Thread.sleep(2000);

					String Shipping_Address = driver.findElement(By.id("shipingAddress")).getText();
					if(Shipping_Address.equalsIgnoreCase(Adreess))
					{
						System.out.println("Test Case - Pass --> Shipping Addess and Branch Address is Same");
					}
					else {
						System.err.println("Test Case - Fail --> Shipping Addess and Branch Address is Not Same");
					}

					//Contact Person
					System.out.println("\n");
					String Contactperson_lable = "/html/body/div/div/div[10]/div[2]/form[2]/div/fieldset[2]/div[3]/label";
					new Method().Title_and_Legend(driver, Contactperson_lable);

					driver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);

					String ShipContactPerson = driver.findElement(By.id("contactPerson")).getText();

					if(ShipContactPerson.equals(""))
					{
						driver.findElement(By.id("ins"));

						String ContactPerExpectedAlert = "Please Enter Contact Person Name";
						new Method().Validatins_of_Alert(driver, ContactPerExpectedAlert);

						driver.findElement(By.id("contactPerson")).sendKeys("Ram");
					}
					else {
						if(ShipContactPerson.equalsIgnoreCase(PersonName))
						{
							System.out.println("Test Case - Pass --> Shipping Contact person and Branch's Contact person is Same");
						}
						else 	{
							System.err.println("Test Case - Fail --> Shipping Contact person and Branch's Contact person is Not Same");
						}
					}

					//Contact Number
					System.out.println("\n");
					String ContactNo_lable = "/html/body/div/div/div[10]/div[2]/form[2]/div/fieldset/div[16]/label";
					new Method().Title_and_Legend(driver, ContactNo_lable);

					driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);


					String ShipContactNo = driver.findElement(By.id("contactNumber")).getText();

					if(!ShipContactNo.equals(null)) {
						if(ShipContactNo.equalsIgnoreCase(ContactNo))
						{
							System.out.println("Test Case - Pass --> Shipping Contact Number and Branch's Contact Number is Same");
						}
						else {
							System.out.println("Test Case - Pass --> Shipping Contact Number and Branch's Contact Number is Not Same");
						}
					}
					else {
						driver.findElement(By.id("ins"));

						String ContactPerExpectedAlert = "Please Enter Contact Number";
						new Method().Validatins_of_Alert(driver, ContactPerExpectedAlert);

						driver.findElement(By.id("contactPerson")).sendKeys("9876543210");
					}
				}

				driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
				driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL, Keys.TAB);
				Thread.sleep(1000);
				driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"w");
			}

			// Scroll		
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		}

		// For Multiple Godown of Branch --> user can't select Shipping address others than Auto Suggest list	
		WebElement ShipAddID = driver.findElement(By.id("shipingAddress"));

		ShipAddID.sendKeys(Keys.CONTROL+"a");
		ShipAddID.sendKeys("pune");

		driver.findElement(By.id("ins")).click();
		Thread.sleep(1000);
		String EqualAlert7 = "Please Enter Shipping Valid Address..!";
		new Method().Validatins_of_Alert(driver, EqualAlert7);

		// For Auto Suggestion of Shipping Address

		ShipAddID.clear();
		ShipAddID.sendKeys("pune");
		Thread.sleep(1000);
		ShipAddID.sendKeys(Keys.ARROW_DOWN);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		ShipAddID.sendKeys(Keys.ENTER);

		Thread.sleep(1000);

		if(ShipAddID.getText().equals(0)){
			System.err.println("Test Case - Fail --> Auto Suggest for Address is Not Present");
		}
		else {
			System.out.println("Test Case - Pass --> Auto Suggest for Address is Present");
		}

	}
	
	@Test
	public void ItemDetails() throws InterruptedException, IOException
	{
		String Item_Name1 = "Maize";
		String Min_Qty1 = "150"; 
		String Max_Qty1 = "100";
		String minimumQty1 = "50";
		String Disc1 = "1.7";
		String DueDays1 = "0";
		obj.Item_Details(driver, Item_Name1, Min_Qty1,Max_Qty1,minimumQty1,Disc1,DueDays1,1);

		System.out.println("\n");
		String Item_Name2 = "SOYA DOC";
		String Min_Qty2 = "70"; 
		String Max_Qty2 = "50";
		String minimumQty2 = "30";
		String Disc2 = "0";
		String DueDays2 = "0";
		obj.Item_Details(driver, Item_Name2, Min_Qty2,Max_Qty2,minimumQty2,Disc2,DueDays2,2);

		System.out.println("\n");
					String Item_Name3 = "Salt";
					String Min_Qty3 = "25"; 
					String Max_Qty3 = "20";
					String minimumQty3 = "15";
					String Disc3 = "0";
					String DueDays3 = "0";
					obj.Item_Details(driver, Item_Name3, Min_Qty3,Max_Qty3,minimumQty3,Disc3,DueDays3,3);

					System.out.println("\n");
					String Item_Name4 = "diesel";
					String Min_Qty4 = "65"; 
					String Max_Qty4 = "55";
					String minimumQty4 = "45";
					String Disc4 = "0.9";
					String DueDays4 = "1";
					obj.Item_Details(driver, Item_Name4, Min_Qty4,Max_Qty4,minimumQty4,Disc4,DueDays4,4);	

					System.out.println("\n");
					String Item_Name5 = "MBM";
					String Min_Qty5 = "65"; 
					String Max_Qty5 = "55";
					String minimumQty5 = "45";
					String Disc5 = "0.9";
					String DueDays5 = "1";
					obj.Item_Details(driver, Item_Name5, Min_Qty5,Max_Qty5,minimumQty5,Disc5,DueDays5,5);

					System.out.println("\n");
					String Item_Name6 = "Small Eggs";
					String Min_Qty6 = "65"; 
					String Max_Qty6 = "55";
					String minimumQty6 = "45";
					String Disc6 = "0.9";
					String DueDays6= "1";
					obj.Item_Details(driver, Item_Name6, Min_Qty6,Max_Qty6,minimumQty6,Disc6,DueDays6,6);

					System.out.println("\n");
					String Item_Name7 = "PP BAG FINISHER";
					String Min_Qty7 = "65"; 
					String Max_Qty7 = "55";
					String minimumQty7 = "45";
					String Disc7 = "0.9";
					String DueDays7= "1";
					obj.Item_Details(driver, Item_Name7, Min_Qty7,Max_Qty7,minimumQty7,Disc7,DueDays7,7);
	}
	
	@Test
	public void Submit_open_PO_Entry()
	{
		// To SUBMIT OPEN PO ENTRY
				if(flag == 1)
				{
					driver.findElement(By.id("open_po")).click();
					System.out.println("SUBMIT ENTRY OF OPEN PO");
				}else {
					System.out.println("SUBMIT ENTRY OF CONFIRM PO");
				}
	}
	
	@Test
	public void GSTIN() {
		
		//GSTIN NUMBER	
				System.out.println("\n");
				String GSTING_Label = "/html/body/div/div/div[10]/div[2]/form[2]/div[3]/fieldset/div[3]/div/label";
				new Method().Title_and_Legend(driver, GSTING_Label);


				String GSTING_No = driver.findElement(By.id("gst_in_no")).getAttribute("readonly");
				if(GSTING_No.equals(" "))
				{
					System.out.println("Field is Read Only");
				}
				else {
					System.out.println("Field is not Read Only");
				}
		//		GSTIN no alert					
				String Get_Party_Name = driver.findElement(By.id("partyId")).getAttribute("value");

				try {
					PreparedStatement pstmt = con.prepareStatement("select if(gstin_no,'',0) as GSTIN_No from ledgerdetails where ledger_id ='"+ Get_Party_Name+"'");
					// pstmt = con.prepareStatement("select if(gstin_no,'',0) as GSTIN_No from ledgerdetails where ledger_id ='"+ Get_Party_Name+"'");
					ResultSet rs= pstmt.executeQuery();
					
					if(rs.next())
					{
						if(!rs.getString("GSTIN_No").equals("0")) 
						{
							driver.findElement(By.id("ins")).click();

							String SUBMITExpected = "Are You Sure For Submit ?";
							new Method().Validatins_of_Alert(driver,  SUBMITExpected);
						}else {
							driver.findElement(By.id("ins")).click();

						//	WebDriverWait wait = new WebDriverWait(driver, 1000);
						//			wait.until(ExpectedConditions.alertIsPresent());
							
							
							if(driver.switchTo().alert() != null) {
								
								String GSTIN_Alert_Expect = "Please Assign GSTIN No to Specific Party...!";
								new Method().Validatins_of_Alert(driver, GSTIN_Alert_Expect);

								
								driver.findElement(By.id("reverseCharge_yes")).click();
								driver.switchTo().alert().accept();

								driver.findElement(By.id("ins")).click();
							}
							else {
								System.out.println("Unknown Condition");
							}

							Thread.sleep(1000);

							String SUBMITExpected = "Are You Sure For Submit ?";
							new Method().Validatins_of_Alert(driver, SUBMITExpected);
						}
					}
					else 
					{
						System.out.println("outttttttttt");
					}
				//	calst.close();
				}
				catch (Exception e) {
					System.out.println("Not Excuted "+e);
				}
	}
	
	@Test
	public void Submitted_Entry_PresentIn_RightPanel() throws InterruptedException
	{
		//SUBMIITED Entry in Right panel

				new Method().alert_message(driver);
				String Order_no = driver.findElement(By.id("selectRecordId")).getAttribute("value");
				System.out.println(Order_no);

				try {
					PreparedStatement pstmt = con.prepareStatement("select * from purchaseorder where module_id = 1 and for_branch = 'HO' and voucher_id = 'V7' and order_status != 'complete'\r\n" + 
							" and date between (select fromm from financialyear where status = 'current') \r\n" + 
							" and (select too from financialyear where status = 'current') and last_modified_by = '"+ UserName+"';\r\n" + 
							"");
					
					Statement cstmt = con.createStatement();
					ResultSet rs = pstmt.executeQuery();
					int ordCount=0;
					while(rs.next())
					{
						if(rs.getString("order_no").contains(Order_no))
						{
							ordCount++;
						}
						else {
							ordCount=0;
							break;
						}
					}
					if(ordCount==0)
					{
						System.err.println("Test Case - Fail --> SUBMITTED Entry NOT Present in Right Panel.");
					}else {
						System.out.println("Test Case - Pass --> SUBMITTED Entry Present in Right Panel.");
					}
					cstmt.close();	
				}

				catch (Exception e) {
					System.out.println("Not Executed" +e);
				}

	}
	
	@Test
	public void Selection_Of_PO() throws InterruptedException
	{
		//For selection of Open PO	
		if(flag == 1)
		{
			driver.findElement(By.xpath("/html/body/div/div/div[9]/ul/li[4]/span")).click();

			WebElement MRN = driver.findElement(By.id("Item Receipt Note"));
			MRN.sendKeys(Keys.SHIFT);
			MRN.click();

			Thread.sleep(1000);

			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL,Keys.TAB);
			driver.navigate().refresh();

			driver.findElement(By.id("diaSelect")).sendKeys("Head Office");
			driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/input")).click();
			Thread.sleep(100);
			driver.navigate().refresh();
 
			Thread.sleep(1000);
			driver.findElement(By.id("h_curr_date")).clear();
			
			String ID_Date = "h_curr_date";
			new Method().Entrydate(driver, ID_Date);
			
			
			WebElement Auto_party_for_openPO = driver.findElement(By.id("partyName"));
			Auto_party_for_openPO.sendKeys(Party_Name);     						
			/// change name to check weather Open PO ID is showing or not.
			Thread.sleep(1000);
			Auto_party_for_openPO.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);


			WebElement mySelectElm = driver.findElement(By.id("OrderNumber")); 
			Select mySelect= new Select(mySelectElm);
			
			if(mySelect.getAllSelectedOptions().isEmpty())
			
			//List<WebElement> options = mySelect.getOptions();
		//	for (WebElement option : options) {
			///	if(option.getText().contains("-- SELECT --"))
				
				{
					System.out.println("Test case - Pass --> Order should not shown of Open PO with out Confirm PO");
				}
				else {
					System.err.println("Test case - Fail --> Order should not shown of Open PO with out Confirm PO");
				}
			}
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"w");
			Thread.sleep(1000);

			new Method().Drop_Down_Date(driver);

			WebElement AutopartyForopenPO = driver.findElement(By.id("partyName"));

			AutopartyForopenPO.sendKeys(Party_Name);     						/// change name if check weather Open PO ID is showing or not.
			Thread.sleep(1000);
			AutopartyForopenPO.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
			Thread.sleep(1000);

			String OpenPO = driver.findElement(By.id("openPo_list")).getAttribute("value");

			if(!OpenPO.equals(0))
			{
				driver.findElement(By.id("openPo_list")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.id("contactPerson")).sendKeys("Ram");
			}
			else {
				System.err.println("Test Case - Fail --> Confirm PO ID doesnt not Exist.");
			}

			driver.findElement(By.id("ins")).click();

			String SUBMITExpected = "Are You Sure For Submit ?";
			new Method().Validatins_of_Alert(driver, SUBMITExpected);

			new Method().alert_message(driver);

			System.out.println("Confirm PO against open PO Successfully Submitted");
	}
	
	@Test
	public void For_Narration()
	{
		//Narration
				System.out.println("\n");
				String Narration_Label = "/html/body/div/div/div[10]/div[2]/form[2]/div[3]/fieldset/div[4]/label";
				new Method().Title_and_Legend(driver, Narration_Label);

				driver.findElement(By.id("narration")).sendKeys("Testing of PO through SELENIUM tool");	

	}
	
	// Item Details
		public void Item_Details(WebDriver driver, String Item_Name, String Min_Qty, String Max_Qty, String minimumQty,String Disc, String DueDays,int i) throws InterruptedException, IOException 
		{
			WebElement hidden = driver.findElement(By.id("cnt"));
			String Value = hidden.getAttribute("value");
			Integer.parseInt(Value);

			Actions Action = new Actions(driver);

			//Selection of ITEM
			System.out.println("\n");
			String Item_Name_Label = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[2]";
			new Method().Title_and_Legend(driver, Item_Name_Label);

			WebElement ITEM = driver.findElement(By.id("itemName"+i));
			ITEM.sendKeys(Item_Name);
			Thread.sleep(1000);
			ITEM.sendKeys(Keys.ARROW_DOWN);
			ITEM.sendKeys(Keys.ENTER);

			//Item per UNIT			
			System.out.println("\n");
			String Unit_Label = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[3]";
			new Method().Title_and_Legend(driver, Unit_Label);

			WebElement readOnly = driver.findElement(By.id("itemUnit"+i));
			WebElement RateperUnit = driver.findElement(By.id("rateUnit"+i));

			if((readOnly.getAttribute("readonly").equals("true"))&&(!readOnly.getAttribute("value").isEmpty()))
			{
				System.out.println("Test Case - Pass --> Item Unit is Shown Proper and READ ONLY");
			}
			else {
				System.err.println("Test Case - Fail --> Item Unit is NOT Shown Proper and READ ONLY");
			}

			//Minimum Quantity
			System.out.println("\n");
			String Minimum_Quantity_label = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[4]";
			new Method().Title_and_Legend(driver, Minimum_Quantity_label);

			WebElement MinQty = driver.findElement(By.id("min_quantity"+i));
			MinQty.sendKeys(Min_Qty);
			int Minimum = Integer.parseInt(Min_Qty);

			//Maximum Quantity
			System.out.println("\n");
			String Maximum_Quantity_label = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[5]";
			new Method().Title_and_Legend(driver, Maximum_Quantity_label);	

			WebElement MaxQty = driver.findElement(By.id("quantity"+i));
			MaxQty.sendKeys(Max_Qty);
			int Maximum=Integer.parseInt(Max_Qty);

			if(Minimum>Maximum)
			{
				driver.findElement(By.id("ins")).click();

				String EquaQuantityAlert = "Please Enter Minimum Acceptable Quantity Less Than Maximum Acceptable Quantity..!";
				new Method().Validatins_of_Alert(driver, EquaQuantityAlert);

				if(MinQty.getText().isEmpty())
				{
					System.out.println("Test Case - Pass --> Minimum Qty. Field is Blanked");
				}else
				{
					System.err.println("Test Case - Fail --> Minimum Qty. Field should get Blank");
				}
			}
			else {
				System.out.println("Test Case - Fail --> Minimum Acceptable Quantity Less Than Maximum Acceptable Quantity..!");
			}

			Thread.sleep(1000);

			WebElement Minimum2 = driver.findElement(By.id("min_quantity"+i));
			Minimum2.sendKeys(minimumQty);
			int FinalMinQty = Integer.parseInt(minimumQty);

			// Rate		
			
			System.out.println("\n");
			String Rate_Lable = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[6]";
			new Method().Title_and_Legend(driver, Rate_Lable);

			String Rate = "30";
		
		 	WebElement Rate1 = driver.findElement(By.id("rate"+i));
			Rate1.sendKeys(Rate);
			int RateOfItem = Integer.parseInt(Rate);


			if(!RateperUnit.getAttribute("value").isEmpty())
			{
				System.out.println("Test Case - Pass --> Rate Unit is Shown Proper and READ ONLY");

				RateperUnit.sendKeys("Liter");

				driver.findElement(By.id("ins")).click();
				Thread.sleep(1000);

				String EqualRatePerUnitAlert = "Rate/Unit Does Not Exist";
				new Method().Validatins_of_Alert(driver, EqualRatePerUnitAlert);

				driver.switchTo().alert().dismiss();
			}
			else {
				System.err.println("Test Case - Fail --> Rate Unit is NOT Shown Proper");
			}

			String VAT =  driver.findElement(By.id("vat"+i)).getAttribute("value");
														String CST =  driver.findElement(By.id("cst"+i)).getAttribute("value");
			Double IGST = Double.parseDouble(driver.findElement(By.id("igst"+i)).getAttribute("value"));
			Double CGST = Double.parseDouble(driver.findElement(By.id("cgst"+i)).getAttribute("value"));
			Double SGST = Double.parseDouble(driver.findElement(By.id("sgst"+i)).getAttribute("value"));
			WebElement Discount = driver.findElement(By.id("disc"+i));

			Discount.sendKeys(Disc);
			Double Disc1 = Double.parseDouble(Disc);

			if(!IGST.equals(true))
			{
				System.out.println("Test Case - Pass --> Tax field is READ ONLY");
			}
			else {
				System.err.println("Test Case - Fail --> Tax field is READ ONLY");
			}

			//TAX and Discount Calculations
			double rowGrossAmount = 0,IGSTTaxAmount=0, Tax_On_Gross_Amount = 0,CGSTTaxAmount=0, SGSTTaxAmount=0,CGST_SGST_TaxAmount, TotalDiscountedAmount=0, Net_Amount;

			if((!MinQty.getAttribute("value").isEmpty())&&(!Rate1.getAttribute("value").isEmpty())&&(!IGST.equals(0)))
			{
				rowGrossAmount = (FinalMinQty*RateOfItem);
				System.out.println("\n");
				System.out.println("Gross Amount (Without TAX and DISC.)--> "+ rowGrossAmount);

				boolean IGSTField = driver.findElement(By.id("igst"+i)).isDisplayed();
				boolean CGSTField = driver.findElement(By.id("cgst"+i)).isDisplayed();
				boolean SGSTField = driver.findElement(By.id("sgst"+i)).isDisplayed();

				if(IGSTField == true) {
					//IGST						
					System.out.println("\n");
					String IGST_Lable = "/html/body/div[1]/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[10]";
					new Method().Title_and_Legend(driver, IGST_Lable);

					IGSTTaxAmount = (rowGrossAmount * IGST)/100;
					new Actions(driver).sendKeys(Keys.TAB).perform();
					Tax_On_Gross_Amount = (rowGrossAmount + IGSTTaxAmount);

					System.out.println("IGST is "+IGSTTaxAmount+" = "+ rowGrossAmount +"*"+IGST);
					System.out.println("Amount with IGST is "+Tax_On_Gross_Amount+" = "+ rowGrossAmount+"+"+IGSTTaxAmount);

					//Discount on IGST and Amount
					System.out.println("\n");
					String Discount_Lable = "/html/body/div[1]/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[13]";
					new Method().Title_and_Legend(driver, Discount_Lable);

					TotalDiscountedAmount = (Tax_On_Gross_Amount*Disc1)/100;

					//Net Amount label			
					System.out.println("\n");
					String Net_Amount_Lable = "/html/body/div[1]/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[14]";
					new Method().Title_and_Legend(driver, Net_Amount_Lable);

					Net_Amount = (Tax_On_Gross_Amount-TotalDiscountedAmount);

					System.out.println("Discount is "+TotalDiscountedAmount+"="+ rowGrossAmount+"*"+Disc1);
					System.out.println("Amount with Discount and TAX is "+ Net_Amount +"="+ rowGrossAmount+"-"+TotalDiscountedAmount);

					Action.sendKeys(Keys.TAB).build().perform();
				}
				
				else 
					if(CGSTField == true && SGSTField == true)

					{
						System.out.println("\n");
	//CGST           
						String CGST_label = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[11]";
						new Method().Title_and_Legend(driver, CGST_label);

						CGSTTaxAmount = (rowGrossAmount * CGST)/100;
						System.out.println("CGST is "+ CGSTTaxAmount +"="+rowGrossAmount +"*"+ CGST);

//	SGST			
						System.out.println("\n");
						String SGST_label = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[12]";
						new Method().Title_and_Legend(driver, SGST_label);	

						SGSTTaxAmount = (rowGrossAmount * SGST)/100;
						System.out.println("SGST is "+ SGSTTaxAmount +"="+rowGrossAmount +"*"+ SGST);

						CGST_SGST_TaxAmount = (CGSTTaxAmount + SGSTTaxAmount);
						System.out.println("\n");
						System.out.println("Total Tax Amount--> "+ CGST_SGST_TaxAmount);

						Tax_On_Gross_Amount = (rowGrossAmount + CGST_SGST_TaxAmount);
						System.out.println("Amount with CGST and SGST is "+Tax_On_Gross_Amount+" = "+ rowGrossAmount+"+"+CGST_SGST_TaxAmount);

						//Discount on CGST and SGST and Amount
						System.out.println("\n");
						String Discount_Lable = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[13]";
						new Method().Title_and_Legend(driver, Discount_Lable);

						TotalDiscountedAmount = (Tax_On_Gross_Amount*Disc1)/100;

						//Net Amount					
						Net_Amount = (Tax_On_Gross_Amount-TotalDiscountedAmount);

						System.out.println("Discount is "+TotalDiscountedAmount+"="+ rowGrossAmount+"*"+Disc1);

						System.out.println("\n");
						String Net_Amount_Lable = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[14]";
						new Method().Title_and_Legend(driver, Net_Amount_Lable);

						System.out.println("NET AMOUNT OF AN ITEM IS "+Net_Amount+"="+ rowGrossAmount+"-"+TotalDiscountedAmount);

						Action.sendKeys(Keys.TAB).build().perform();
					}
			}
			else {
				System.out.println("Somthing went wrong");
			}


			//Due Date				
			System.out.println("\n");
			String Due_Days_DateLable = "/html/body/div/div/div[10]/div[2]/form[2]/div[2]/fieldset/table/tbody/tr/td[15]";
			new Method().Title_and_Legend(driver, Due_Days_DateLable);

			WebElement Due_Days = driver.findElement(By.id("dueDays"+i));
			Due_Days.clear();
			driver.findElement(By.id("ins")).click();


			String EqualDueDateAlert = "Entered Date is not Valid!";
			new Method().Validatins_of_Alert(driver, EqualDueDateAlert);

			Due_Days.sendKeys(DueDays);
			Action.sendKeys(Keys.TAB).build().perform();
			//For Duplicate ITEM

			if(i==5) {
												//		String ifDuplicate4 = "Duplicate Entry is Not Allowed.";
												//		String ifDuplicate4alert = "Duplicate Entry is Not Allowed.";
												//		new Method().Validatins_of_Alert(driver, ifDuplicate4, ifDuplicate4alert);}


			WebElement DueDate = driver.findElement(By.id("dueDate"+i));

			Due_Days.sendKeys(DueDays);
			Action.sendKeys(Keys.TAB).build().perform();

			System.out.println(DueDate.getText());

			if(!DueDate.equals(0))
			{
				System.out.println("Due Days and Due Dates are Proper."+"-->"+DueDays);
			}
			//Script conditions for Due Date

			// New Row generation
			if(i<2)
			{
				WebElement New_Row = driver.findElement(By.id("dueDate"+i));
				New_Row.sendKeys(Keys.ENTER);	
			}
			// Row Deletion
			if(i==7) {
				System.out.println("\n");
				WebElement Row_Deletion = driver.findElement(By.id("dueDate"+(i-2)));
				Row_Deletion.click();
				Row_Deletion.sendKeys(Keys.CONTROL,Keys.DELETE);
				System.out.println("Row Deleted - "+(i-2));
			}
			else {
				System.out.println("not Found");
			}
		}
}
}
