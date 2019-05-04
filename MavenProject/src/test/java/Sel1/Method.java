package Sel1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Method{

	static String EntryDate = "30082018";
	
	///////////  For Form Title and Legend
	public void Title_and_Legend(WebDriver driver,String ALLLegend )
	{
		String OnForm= driver.findElement(By.xpath(ALLLegend)).getText();
		if(driver.findElement(By.xpath(ALLLegend)) != null)
		{
			System.out.println(OnForm);
		}
		else
		{ 
			System.err.println("Test Case - Fail --> Check Lable");															 
		}
	}


	/////////////max Length of fields 
	public void Maxlength(WebDriver driver, String ID, int fieldLength, String Name)
	{   
	
			if(driver.findElement(By.id(ID)).getAttribute("maxlength").equals(fieldLength))  
			{
				System.out.println("Test Case - Pass --> Maxlength is proper");
			}
			else {
				System.err.println("Test Case - Fail --> Maxlength is not Proper");
			}
		driver.findElement(By.id(ID)).sendKeys(Name);
	}


	//////////// date
	public String Entrydate(WebDriver driver, String ID_Date)
	{
		String Date = EntryDate;
		driver.findElement(By.id(ID_Date)).sendKeys(Date);
		return Date;
	}

	public void Drop_Down_Date(WebDriver driver)
	{
		driver.findElement(By.id("day")).sendKeys("10");
		driver.findElement(By.id("month")).sendKeys("sep");
		driver.findElement(By.id("year")).sendKeys("2018");
	}




	///////////   		 Validations of Phone No	
	public void Validations_of_PhoneNo(WebDriver driver, String IDm )
	{
		// For Duplicate Number
		for (int i=0; i<=10; i++)
		{
			CharSequence j = "1";
			driver.findElement(By.id(IDm)).sendKeys(j);
		}
		driver.findElement(By.id(IDm)).sendKeys(Keys.TAB);

		// To Check Duplicate No
		try {
			if(driver.switchTo().alert() == null)
				System.err.println("Test Case - Fail --> Alert is not present - Duplicate No. allowed. or Same Digits Not Allowed");
			else {
				String alert=driver.switchTo().alert().getText();
				if((alert.equals("Duplicate digit not Allowed"))||(alert.equalsIgnoreCase("Same Digits Not Allowed")))
				{

					System.out.println("Test Case - Pass  --> Alert Present --> "+alert); 
					driver.switchTo().alert().dismiss();
				}
				else
				{
					System.err.println("Test Case - Fail --> Alert not Present as " + alert);													
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("Test Case - Fail --> Alert not Present");
		}

		// For Max Length		
		if(	driver.findElement(By.id(IDm)).getAttribute("maxlength").equals("10"))

		{
			System.out.println("Test Case - Pass --> Maxlength is proper");
		}
		else {
			System.err.println("Test Case - Fail --> Maxlength is not Proper");
		}

		// Checking for Alphabets.
		driver.findElement(By.id(IDm)).sendKeys("abcde");													

		if(driver.findElement(By.id(IDm)).getText().contains(""))
		{
			System.out.println("Test Case - Pass --> Alphabets not acceptable");
		}
		else {
			System.err.println("Test Case - Fail --> Alphabets Should not be acceptable");
		}

		// To Check Special Symbols.	
		driver.findElement(By.id(IDm)).sendKeys("!@#$%^&*");												

		if(driver.findElement(By.id(IDm)).getText().contains(""))
		{
			System.out.println("Test Case - Pass --> Special Symbols not acceptable");
		}
		else {
			System.err.println("Test Case - Fail --> Special Symbols Should not be acceptable");}


		// 10 Digits
		driver.findElement(By.id(IDm)).sendKeys("12345");
		driver.findElement(By.id(IDm)).sendKeys(Keys.TAB);

		try {
			if(driver.switchTo().alert() == null)
			{
				System.out.println("Test Case - fail --> Contact Number Must Contain 10 Digits ");
			}
			else {
				String alert = driver.switchTo().alert().getText();
				if(alert.equalsIgnoreCase("Contact Number Must Contain 10 Digits"))
				{
					System.out.println("Test Case - Pass  --> Alert Present --> "+alert);
					driver.switchTo().alert().dismiss();
				}
				else {
					System.out.println("Test Case - Pass  --> Alert Present --> "+alert);
				}
			}
		} catch (Exception e) {
			System.out.println("Test Case - Fail --> Alert is not present - Contact Number Must Contain 10 Digits");
		}
		driver.findElement(By.id(IDm)).clear();
		driver.findElement(By.id(IDm)).sendKeys("9632159874");

	}

	////////////		Height and Width

	public void Height_and_Width(WebDriver driver ,String IDHW,int width,int Height, String Lable)
	{
		if((driver.findElement(By.id(IDHW)).getSize().getWidth() == width)&&							// To check Width and Height
				(driver.findElement(By.id(IDHW)).getSize().getHeight() == Height))					
		{

			System.out.println("Test Case - Pass --> Width Of Field Is Proper.");
		}
		else {System.err.println("Test Case - Fail --> Width Of Field Is NOT Proper.");
		}
	}


	//////////		Tool-tip 

	public void ToolTip(WebDriver driver, String xpath, String expectedTooltip) 
	{
		WebElement tooltip = driver.findElement(By.xpath(xpath));
		String actualTooltip = tooltip.getAttribute("title");	
		if(actualTooltip.equals(expectedTooltip))
		{							
			System.out.println("Test Case - Pass --> Tooltip --> " + actualTooltip);
		}
		else {
			System.err.println("Test Case - Fail --> Tooltip --> No Tooltip --> "+ actualTooltip);
		}
	}

	//*************		Alerts *****************
	public void Validatins_of_Alert(WebDriver driver, String EqualAlert)
	{
		try {
			if(driver.switchTo().alert() == null)
			{
				System.err.println("Test Case - Fail --> Alert is not present" + EqualAlert);
				}
			else {
				String alert = driver.switchTo().alert().getText();
				if((alert.equalsIgnoreCase(EqualAlert)))
				{
					Thread.sleep(1000);
					System.out.println("Test Case - Pass --> Alert Present --> "+EqualAlert);
					Thread.sleep(1000);
					driver.switchTo().alert().accept();
				}
				else
				{
					System.err.println("Test Case - Fail --> Alert not Present --> "+ EqualAlert);													
				}
			}
		}
		catch(Exception e)
		{	
			System.err.println("Test Case - Fail --> Alert not Present --> "+ EqualAlert);
		}

	}

	//

	////////********** Star-mand --> Mandatory Field

	public void starmand(WebDriver driver, String xpath)
	{
		if(driver.findElement(By.xpath(xpath)).getAttribute("class").contains("starmand"))
		{
			System.out.println("Test Case - Pass --> Mandatory Mark Present");
		}
		else 
		{
			System.err.println("Test Case - Fail --> Mandatory Mark Not Present");
		}


	}

	public void Due_Days_Val(WebDriver driver, String DueDaysID)
	{
		driver.findElement(By.id(DueDaysID));

		String DueDays = driver.findElement(By.id("dueDays")).getAttribute("maxlength");
		if(DueDays=="3")
		{
			System.out.println("Test Case - Pass --> Maxlength should be 3 only");
		}
		else {
			System.err.println("Test Case - Fail --> Maxlength should be 3 only");
		}


		if(DueDays.contains("0"))
		{
			System.out.println("Test Case - Pass --> Due Days are default"+driver.findElement(By.id("dueDays")).getText());
		}
		else {
			driver.findElement(By.id("dueDays")).sendKeys("1");
			System.out.println("Test Case - Pass --> Entered Due Day 1");
		}

		/*driver.findElement(By.id("dueDate")).sendKeys(Keys.TAB);

			WebElement Date  = driver.findElement(By.id("day"));
		    WebElement Month = driver.findElement(By.id("month"));
		    WebElement Year  = driver.findElement(By.id("year"));

		System.out.println(Date.getAttribute("value")+"/"+Month.getAttribute("value")+"/"+Year.getAttribute("value"));
		System.out.println("Due date --> "+driver.findElement(By.id("dueDate")).getAttribute("value"));
		 */
	}

	public void alert_message(WebDriver driver) throws InterruptedException
	{
		String alert_message = driver.findElement(By.id("alert_message")).getText();

		if(alert_message.contains("Data Submitted Successfully"))
		{
			System.out.println("Test Case - Pass --> - Data Submitted Successfully..!");
//PDF
			if(alert_message.contains("Do you want to download as PDF?"))
			{
				driver.findElement(By.id("button_cancel")).click();
			}
			else {
				driver.findElement(By.id("button_ok")).click();
			}
		}	
			//Print		
		
		else if(alert_message.contains("Data Updated Successfully..!"))
		{
			System.out.println("Test Case - Pass --> - Data Updated Successfully..!");

			if(alert_message.contains("Do you want to download as PDF?"))
			{
				driver.findElement(By.id("button_cancel")).click();
			}
			else {
				driver.findElement(By.id("button_ok")).click();
			}
		}
		else if (alert_message.contains("Data Deleted Successfully..!"))
		{
			System.out.println("Test Case - Pass --> - Data Deleted Successfully..!");
			Thread.sleep(1000);
			if(alert_message.contains("Do you want to download as PDF?"))
			{
				driver.findElement(By.id("button_cancel")).click();
			}
			else {
				driver.findElement(By.id("button_ok")).click();
			}
		}
		else {
			System.err.println("Test Case - Fail --> - Alert Message Not Present");
		}
		
		/* if(!driver.switchTo().alert().equals(0)) {
				
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
				}*/
	}


	public void Date_Validation(WebDriver driver, String DateId) throws InterruptedException
	{
		Thread.sleep(1000);
		WebElement EntryDate = driver.findElement(By.id(DateId));
		EntryDate.clear();
		EntryDate.sendKeys("25052019");  

		EntryDate.sendKeys(Keys.TAB);

		String EqualAlert3 = "Date should be Less/Equal to current date and within Financial Year";
		new Method().Validatins_of_Alert(driver,EqualAlert3);

		EntryDate.clear();
		EntryDate.sendKeys("30022018");

		EntryDate.sendKeys(Keys.TAB);

		String EqualAlert4 = "You Have Entered Invalid Date..!";
		new Method().Validatins_of_Alert(driver,EqualAlert4);

	}
	
	
	public void Mfg_Exp_Alerts(WebDriver driver, String mfg_id , String exp_id ,int i) throws InterruptedException
	{
		String mfg_Date = "01052018"; //mfg should not be greater than Entry date
		String exp_Date= "01062018";

		String GetEntryDate = EntryDate.substring(0,2)+"/"+EntryDate.substring(2,4)+"/"+EntryDate.substring(4);
		String GetMFGDate = mfg_Date.substring(0,2)+"/"+mfg_Date.substring(2,4)+"/"+mfg_Date.substring(4);
		String GetExpDate = exp_Date.substring(0,2)+"/"+exp_Date.substring(2,4)+"/"+exp_Date.substring(4);

		try {
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(GetEntryDate);
			Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(GetMFGDate);
			Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(GetExpDate);

			Thread.sleep(500);
			WebElement Mfg_date = driver.findElement(By.id(mfg_id+i));
			Mfg_date.clear();
			Mfg_date.sendKeys(mfg_Date);
			
			if(date2.after(date1))
			{
				driver.findElement(By.id(mfg_id+i)).sendKeys(Keys.TAB);
				Thread.sleep(2000);
				String EqualAlert = "Manufacturing Date Should be Less than or  equal to Receipt/Purchase Date ";
				new Method().Validatins_of_Alert(driver, EqualAlert);
			}
			
			WebElement Exp_Date = driver.findElement(By.id(exp_id+i));
			Exp_Date.clear();
			Exp_Date.sendKeys(exp_Date);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail"+e);
		}
/**********/			
			String mfg_Date1 = "01012018"; //Exp Should not be less Than Entry date
			String exp_Date1 = "15012018";
			
			String GetEntryDate1 = EntryDate.substring(0,2)+"/"+EntryDate.substring(2,4)+"/"+EntryDate.substring(4);
			String GetMFGDate1 = mfg_Date1.substring(0,2)+"/"+mfg_Date1.substring(2,4)+"/"+mfg_Date1.substring(4);
			String GetExpDate1 = exp_Date1.substring(0,2)+"/"+exp_Date1.substring(2,4)+"/"+exp_Date1.substring(4);

			try {
				Date date4 = new SimpleDateFormat("dd/MM/yyyy").parse(GetEntryDate1);
				Date date5 = new SimpleDateFormat("dd/MM/yyyy").parse(GetMFGDate1);
				Date date6 = new SimpleDateFormat("dd/MM/yyyy").parse(GetExpDate1);

				Thread.sleep(500);
				WebElement Mfg_date1 = driver.findElement(By.id(mfg_id+i));
				Mfg_date1.clear();
				Mfg_date1.sendKeys(mfg_Date1);
			
				WebElement Exp_Date1 = driver.findElement(By.id(exp_id+i));
				Exp_Date1.clear();
				Exp_Date1.sendKeys(exp_Date1);
				
			if(date6.before(date4))
			{
				Thread.sleep(1000);
				String EqualAlert = "Expiry Date Should be greater than Receipt/Purchase Date ";
				new Method().Validatins_of_Alert(driver, EqualAlert);
			}
		
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("fail"+e1);
			}	

////*********/
			String mfg_Date2 = "01022018"; //mfg should not be greater than Exp date
			String exp_Date2 = "01012018";			
			
			String GetEntryDate2 = EntryDate.substring(0,2)+"/"+EntryDate.substring(2,4)+"/"+EntryDate.substring(4);
			String GetMFGDate2 = mfg_Date2.substring(0,2)+"/"+mfg_Date2.substring(2,4)+"/"+mfg_Date2.substring(4);
			String GetExpDate2 = exp_Date2.substring(0,2)+"/"+exp_Date2.substring(2,4)+"/"+exp_Date2.substring(4);

			try {
				Date date7 = new SimpleDateFormat("dd/MM/yyyy").parse(GetEntryDate2);
				Date date8 = new SimpleDateFormat("dd/MM/yyyy").parse(GetMFGDate2);
				Date date9 = new SimpleDateFormat("dd/MM/yyyy").parse(GetExpDate2);

				Thread.sleep(500);
				WebElement Mfg_date2 = driver.findElement(By.id(mfg_id+i));
				Mfg_date2.clear();
				Mfg_date2.sendKeys(mfg_Date2);
			
				WebElement Exp_Date2 = driver.findElement(By.id(exp_id+i));
				Exp_Date2.clear();
				Exp_Date2.sendKeys(exp_Date2);
			
			if(date9.before(date8))
			{
				Thread.sleep(1000);
				driver.findElement(By.id(exp_id+i)).sendKeys(Keys.TAB);
				String EqualAlert = "Expiry Date Should be Greater than or  equal to Manufacturing Date";
				new Method().Validatins_of_Alert(driver, EqualAlert);
			}
			
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("fail"+e2);
	}
			driver.findElement(By.id(mfg_id+i)).clear();
			driver.findElement(By.id(mfg_id+i)).sendKeys("01012018");
			driver.findElement(By.id(exp_id+i)).clear();
			driver.findElement(By.id(exp_id+i)).sendKeys("01012019");
}
}
		
	










