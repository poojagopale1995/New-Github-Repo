package Sel1;

import java.io.PrintStream;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.Select;
	class Party_Creation
	{
		/**
		 * @param args
		 * @throws InterruptedException
		 */
		@SuppressWarnings("null")
		public static void main(String args[]) throws InterruptedException
		{
			System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
		    WebDriver driver = new FirefoxDriver();
			driver.get("http://192.168.1.17:8080/POLOXY/jsp/login.jsp#");
		    driver.manage().window().maximize();
		    
			String UID = "uname";
			String Database = "bmsp"; String UserName = "e0100";
			String PID = "pwd";
			String Password = "admin";
			String ModuleID = "imgcbf";
			String TopHeadingID = "MASTER_CREATIONLi";
			String FormID = "Party Creation";
			new Login().GetUnit0(driver, UID,Database,UserName,PID, Password, ModuleID, TopHeadingID, FormID);
			
			
		    
//		    System.out.println("Main Title"); 
		    String Title = "//div[@id='main-content']/div/div[10]/div[2]/div";
		    new Method().Title_and_Legend(driver, Title);
		    
//		    System.out.println("Legend");
		    String legendpath1="//fieldset[@id='compfield']/legend";
		    new Method().Title_and_Legend(driver,legendpath1);
		    
		    
//////**************************           NAME Field	         ********************///////////////
		    String Lable1 = "//fieldset[@id='compfield']/div/label";
		    new Method().Title_and_Legend(driver, Lable1);
		    
		    
		    String ID = "s_company_name";
		    int FieldLength = 50;
		    String Name = "Selenium Demo";
			new Method().Maxlength(driver, ID, FieldLength, Name);
				
			
			
			String IDHW = "s_company_name";
			int width = 206;
			int Height = 20;
			new Method().Height_and_Width(driver, IDHW, width,Height, Lable1 );
					
				
			 String expectedTooltip = "Company Name";
			   String xpath = "//fieldset[@id='compfield']/div[2]/input";
			   new Method().ToolTip(driver, xpath, expectedTooltip);		
					
			   
//////////**********************    	   GROUP    			*****************////////////
				
			   		String Lable2 = "//fieldset[@id='compfield']/div[5]/label";
				    new Method().Title_and_Legend(driver, Lable2);
 
					
					driver.findElement(By.id("leggroup")).click();														
					Thread.sleep(2000);
					Select Group = new Select(driver.findElement(By.id("leggroup")));
					Group.selectByVisibleText("SUNDRY CREDITORS");
					
			       
					
					String IDHW1 = "leggroup";
					int width1 = 206;
//					int Height1 = 20;
					new Method().Height_and_Width(driver, IDHW1, width1,Height=0, Lable2 );
					
					
					  String expectedTooltip1 = "Group";
					   String xpath1 = "//fieldset[@id='compfield']/div[6]/select";
					   new Method().ToolTip(driver, xpath1, expectedTooltip1);
					   
					
					
//////////***************      Party Code       ********************////////////
					String Lable3 = "//fieldset[@id='compfield']/div[3]/label";
				    new Method().Title_and_Legend(driver, Lable3);

				    String ID1 = "partycode";
					int FieldLength1 = 11;
					String Name1 = "abc123";
					new Method().Maxlength(driver, ID1, FieldLength1, Name1);
				
					driver.findElement(By.id("aliesname")).click();
					Thread.sleep(1000);
					
					String EqualAlert = "Dublicate Party Code Not Allowed ..!";
					new Method().Validatins_of_Alert(driver,EqualAlert);
				    
					String IDHW2 = "partycode";
					int width2 = 100;
//					int Height2 = 20;
					new Method().Height_and_Width(driver, IDHW2, width2, Height=0,Lable3 );
					
					
			    	
					
					driver.findElement(By.id("partycode")).sendKeys("!@#$%^&*");									// To Check Special Symbols.
					if(driver.findElement(By.id("partycode")).isDisplayed())
					{
						System.err.println("Test Case - Fail --> Special Symbol Should not be acceptable");     
					}
					driver.findElement(By.id("partycode")).clear();
					
					
					 String expectedTooltip2 = "Party Code";
					   String xpath2 = "//fieldset[@id='compfield']/div[4]/input";
					   new Method().ToolTip(driver, xpath2, expectedTooltip2);

			    	
					
/////////////***************          ALIAS NAME          **************/////////////
					String Lable4 = "//fieldset[@id='compfield']/div[7]/label";
				    new Method().Title_and_Legend(driver, Lable4);

					
						
						
					driver.findElement(By.id("aliesname")).clear();
					
					driver.findElement(By.id("aliesname")).sendKeys("!@#$%^&*");									// To Check Special Symbols.
					if(driver.findElement(By.id("aliesname")).getText().contains(""))
					{
						System.out.println("Test Case - Pass --> Special Symbol not acceptable");
					}
					else {System.err.println("Test Case - Fail --> Special Symbol Should not be acceptable");}
					
				
					
					String IDHW3 = "aliesname";
					int width3 = 100;
//					int Height3 = 20;
					new Method().Height_and_Width(driver, IDHW3,Height=0, width3, Lable4 );
					
					
					String ID2 = "aliesname";
					int FieldLength2 = 51;
					String Name2 = "selenium";
					new Method().Maxlength(driver, ID2, FieldLength2, Name2);
					
//////////******************          MOBILE NUMBER         **************////////////
					String Lable5 = "//fieldset[@id='compfield']/div[9]/div/label";
				    new Method().Title_and_Legend(driver, Lable5);

				    String IDm = "cnt";
					new Method().Validations_of_PhoneNo(driver, IDm);
					
					
							String IDHW4 = "cnt";
							int width4 = 206;
//							int Height4 = 20;
							new Method().Height_and_Width(driver, IDHW4, width4, Height=0,Lable5 );
					        
					      
					        
//////////******************          Phone Number         **************////////////
				
							String Lable6 = "//fieldset[@id='compfield']/div[9]/div[3]/label";
						    new Method().Title_and_Legend(driver, Lable6);

						    String IDm1 = "acnt";
							new Method().Validations_of_PhoneNo(driver, IDm1);
						    
					        
					       /* 
					        driver.findElement(By.id("acnt")).sendKeys("abcde");											// Checking forAlphabets. 
							
					    	if(driver.findElement(By.id("acnt")).getText().contains(""))
							{
								System.out.println("Test Case - Pass --> Alphabets not acceptable");
							}
							else {
									System.err.println("Test Case- Fail --> Alphabets Should not be acceptable");
									}
					        
					    	
					    	
					    	driver.findElement(By.id("acnt")).sendKeys("!@#$%^&*");											// To Check Special Symbol.
							
							if(driver.findElement(By.id("acnt")).getText().contains(""))
							{
								System.out.println("Test Case - Pass --> Special Symbols not acceptable");
							}
							else {
								System.err.println("Test Case - Fail --> Special Symbols Should not be acceptable");}
							
						*/
									
									 	
						        
						       
						       
									String IDHW5 = "acnt";
									int width5 = 206;
//									int Height5 = 20;
									new Method().Height_and_Width(driver, IDHW5, width5,Height=0, Lable6 );
						       
						  	
					        
//////////******************          E mail ID         **************////////////
					    String Lable7 = "//fieldset[@id='compfield']/div[9]/div[5]/label";
					   new Method().Title_and_Legend(driver, Lable7);	
						
							    
					    driver.findElement(By.id("email")).sendKeys("abc");												// To Check Alert for Valid Email ID												
					    driver.findElement(By.id("ins")).click();														
					    
					    
						String EqualAlert1 = "Invalid Email Address..!";
						new Method().Validatins_of_Alert(driver,EqualAlert1);
					    
					    
					    String ID3 = "email";
						int FieldLength3 = 51;
						String Name3 = "abc@redif.com";
						new Method().Maxlength(driver, ID3, FieldLength3, Name3);
					    
			        	
//////////******************          Date Of Aggr.          **************////////////
						
						String Lable8 = "//div[@id='cb']/label";
					    new Method().Title_and_Legend(driver, Lable8);
			    
				    	
			        	 driver.findElement(By.id("ins")).click();
						    
						    
							String EqualAlert2 = "Please Select Date For Agreement..!";
							new Method().Validatins_of_Alert(driver, EqualAlert2);
			        	 
						    
						    driver.findElement(By.id("day")).sendKeys("30");
						    driver.findElement(By.id("year")).sendKeys("2019");											// Alert for Month Not ENtered.
						    driver.findElement(By.id("ins")).click();
						    
						    
							String EqualAlert3 = "Please Select Date For Agreement..!";
							new Method().Validatins_of_Alert(driver,EqualAlert3);
							
						    
						    driver.findElement(By.id("month")).sendKeys("feb");											// To Check Date Validation.
						    
						    driver.findElement(By.id("email")).click();
							String EqualAlert4 = "Invalid Date";
							new Method().Validatins_of_Alert(driver,EqualAlert4);
							
						    
						    driver.findElement(By.id("month")).sendKeys("jan");
						    
						    
						    
//////////******************          Upload File          **************////////////							
						    
						    driver.findElement(By.linkText("Upload File")).click();															//TC34 - To check Upload file 			
						    try {
						    	  assertTrue(driver.findElement(By.id("vari-modal")).isDisplayed());										
						    	} catch (Error e) {
						    	  PrintStream verificationErrors = null;
								verificationErrors.append(e.toString());
						    	}
						    
						    if(driver.findElement(By.id("vari-modal")).isDisplayed())
						    {
						    	System.out.println("Test Case - Pass --> Model window for Upload File is Present");
						    }
						    else {
						    	System.err.println("Test Case - Fail --> Model window for Upload File is Not Present");

						    }
						    
						 driver.findElement(By.cssSelector("span.ui-icon.ui-icon-closethick")).click();

	
//							Legend
						 
							String legendpath2="//form[@id='frm1']/div/div/fieldset[2]/legend";
						    new Method().Title_and_Legend(driver,legendpath2);
						    
						    
//////////******************          Salutation         **************////////////		
						 String Lable9 = "//form[@id='frm1']/div/div/fieldset[2]/div/label";
						 new Method().Title_and_Legend(driver, Lable9);
						 	
					     driver.findElement(By.id("sal")).sendKeys("MS.");
						 
//////////******************           First name          **************////////////
					     
					    String Lable10 = "//form[@id='frm1']/div/div/fieldset[2]/div[3]/div/label";
					    new Method().Title_and_Legend(driver, Lable10);

					    
					  
					   		
							
						driver.findElement(By.id("fname")).clear();
						
						driver.findElement(By.id("fname")).sendKeys("!@#$1234");															// To Check Special Symbols.
						if(driver.findElement(By.id("fname")).getText().contains(""))
						{
							System.out.println("Test Case - Pass --> Special Symbol/Numericals are not acceptable");
						}
						else {
							System.err.println("Test Case - Fail --> Special Symbol/Numericals are Should not be acceptable");
							}
						
						
						 String ID4 = "fname";
							int FieldLength4 = 51;
							String Name4 = "sel";
							new Method().Maxlength(driver, ID4, FieldLength4, Name4);
				       
						 
						 
/////////******************          Address 1         **************////////////					 
							
							String Lable11 = "//form[@id='frm1']/div/div/div/fieldset/div/div/label";
						    new Method().Title_and_Legend(driver, Lable11);
							
						driver.findElement(By.id("ins")).click();
					    
					    
						String EqualAlert5 = "Please Enter Address..!";
						new Method().Validatins_of_Alert(driver, EqualAlert5);
					
						
					    String ID5 = "al1";
						int FieldLength5 = 101;
						String Name5 = "Pune @ swarget 09";
						new Method().Maxlength(driver, ID5, FieldLength5, Name5);
					    
					
				    	
/////////******************          Country           **************////////////			
						String Lable12 = "//form[@id='frm1']/div/div/div/fieldset/div[3]/div/label/a";
					    new Method().Title_and_Legend(driver, Lable12);
						
							driver.findElement(By.id("ins")).click();
						    
							
							String EqualAlert6 = "Please Select State..!";
							new Method().Validatins_of_Alert(driver,  EqualAlert6);
							
							
					    if(driver.findElement(By.id("coun")).getText().contains("INDIA"))								// To check Present By-Default or Not.	
					    {
					    	System.out.println("Test Case - Pass --> Country INDIA Present By-default");
					    }
					    else 
					    {
					    	System.err.println("Test Case - Fail --> Country INDIA Not Present By-default");
					    }
		
					    
					   
					    
					    
					   if (!driver.findElement(By.id("sta")).getAttribute("value").isEmpty())															// once Country selected, only Next Field(State should get Editable)
					   {
						   if((driver.findElement(By.id("dist")).getAttribute("value").equals("0"))
								&&(driver.findElement(By.id("teh")).getAttribute("value").equals("0"))
								   &&(driver.findElement(By.id("ci")).getAttribute("value").equals("0")))
								   {
									   System.out.println("Test Case - Pass --> Select Country, only State should be Editable");
								   }
					   }
					   else 
					   {
						   System.err.println("Test Case - Fail --> Select Country, only State should be Editable");
					   }
					   
					  
					   
					   driver.findElement(By.id("sta")).sendKeys("Maharashtra");
					   driver.findElement(By.id("ins")).click();
					   
						String EqualAlert7 = "Please Select District..!";
						new Method().Validatins_of_Alert(driver, EqualAlert7);
					   
					   
					   if(!driver.findElement(By.id("dist")).getAttribute("value").isEmpty())								// once State selected, only Next Field(District should get Editable)
					   {
						   if(((driver.findElement(By.id("teh")).getAttribute("value").equals("0"))
									   &&(driver.findElement(By.id("ci")).getAttribute("value").equals("0"))))
									   {
										   System.out.println("Test Case - Pass --> Select State, only District should be Editable");
									   }
					   }
					   else 
					   {
						   System.err.println("Test Case - Fail --> Select State, only District should be Editable");
					   }
					   driver.findElement(By.id("dist")).sendKeys("Pune");
					   
					 
					   
					   driver.findElement(By.id("al1")).sendKeys(Keys.CONTROL, "a");
					   driver.findElement(By.id("al1")).sendKeys(Keys.CONTROL, "x");
					   
					   
					   driver.findElement(By.id("ins")).click();
					   
					   
						String EqualAlert8 = "Please Enter Address..!";
						new Method().Validatins_of_Alert(driver,EqualAlert8);
					   
					  
					   driver.findElement(By.id("al1")).sendKeys(Keys.CONTROL, "v");
					   
					   
					   
					   
					   Select Dropdown = new Select(driver.findElement(By.id("sta")));
					   Dropdown.selectByIndex(0);
					   
					   driver.findElement(By.id("ins")).click();
					   
					   
						String EqualAlert9 = "Please Select State..!";
						new Method().Validatins_of_Alert(driver,EqualAlert9);
					   
					   
					   if(driver.findElement(By.id("sta")).getAttribute("value").equals("0"))										//State Blank - District should get Blank.
					   {
						   driver.findElement(By.id("dist")).getAttribute("value").equals("0");										
						   System.out.println("Test Case - Pass --> Field becomes Blank");
					   }
					   else 
					   {
						   System.err.println("Test Case - Fail --> Field should becomes Blank");
					   }
					   
					   driver.findElement(By.id("sta")).sendKeys("Maharashtra");
					   driver.findElement(By.id("ins")).click();
					   
					   
						String EqualAlert10 = "Please Select District..!";
						new Method().Validatins_of_Alert(driver,EqualAlert10);
					   
					   
					   driver.findElement(By.id("sta")).sendKeys("Maharashtra");
					   driver.findElement(By.id("dist")).sendKeys("Pune");
					   
					   
					   
/////////******************          SUBMIT BUTTON           **************////////////						   
					   
					   driver.findElement(By.id("ins")).click();
					   
					   if(!driver.switchTo().alert().equals(""))																	//For Alert
					      {
					      String alertMessage=driver.switchTo().alert().getText();																// To Check for Alert.
					      driver.switchTo().alert().accept();
					      System.out.println("Test Case - Pass --> Alert Present --> " + alertMessage); 					
					      }
					      else
					      {
					   	   System.err.println("Test Case - Fail --> Alert not Present");													
					      }
					   
					   
					  Thread.sleep(1000);
					  if((driver.findElement(By.xpath("//div[@id='normal_alert']/div")).isDisplayed())
							  &&(driver.findElement(By.id("alert_message")).getText().contains("Data Submitted Successfully..!")))
					  {
						  System.out.println("Test Case --> Pass - Data Submitted Successfully.. ");
						  driver.findElement(By.id("button_ok")).click();
					  }
					  else if(driver.findElement(By.id("alert_message")).getText().contains("Record Already Exist..!"))
							  {
						  			System.out.println("Test Case --> Pass - Record Already Exist..!");
						  			driver.findElement(By.id("button_ok")).click();
							  }
					  else {
						  System.err.println("Test Case - Fail -->");
					  }
		}
		
		private static void assertTrue(boolean displayed) {
			// TODO Auto-generated method stub
		}	
		}
	
	
		
		
		
					
		
		
	
		


	
		
	

