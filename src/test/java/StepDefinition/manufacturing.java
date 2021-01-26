package StepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;






//@RunWith(Cucumber.class)
public class manufacturing {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	 DateFormat df = new SimpleDateFormat("mmss");
		Calendar calobj = Calendar.getInstance();
		String date =df.format(calobj.getTime()); 
	 String email = "manufacturer"+date+"@gmail.com";
     String pwd = "1234";
     
     
     @BeforeSuite
     @Given("^Extent reports$")
     public void extent_reports() throws Throwable {
       System.out.println("Reports generated");
 		extent = new ExtentReports(System.getProperty("user.dir") + "//reports//index.html", true);
 		extent.addSystemInfo("Karthik", "QA");
 		
 	}
     @BeforeTest
     @Given("^Chrome Setup$")
     public void chrome_Setup() throws Throwable {
    	 System.setProperty("webdriver.chrome.driver","driver//chromedriver2.exe");
		 driver = new ChromeDriver();  
//			 ChromeOptions option=new ChromeOptions();
//			 option.setHeadless(true);
//			 driver=new ChromeDriver(option);
    }

     @Test(priority = 0)
      @Given("^Firsttrack Register Page$")
     public void firsttrack_Register_Page() throws Throwable {
    	 test = extent.startTest("Manufacturing_Module ");
    	driver.navigate().to("http://3.13.34.41:3000/user/register");
        driver.manage().window().maximize();
        test.log(LogStatus.PASS, "Page Opened");
    }

     @Test (priority = 1)
     @When("^Registration based on Manufacturing$")
     public void registration_based_on_Manufacturing() throws Throwable {
        
    	
	  		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  		driver.findElement(By.xpath("//*[@id='root']/div/main/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div[2]")).click();
	        WebElement Timezone = driver.findElement(By.id("react-select-2-input"));
	      Timezone.sendKeys("ind");
	      Timezone.sendKeys(Keys.ENTER);
	      driver.findElement(By.id("companyName")).sendKeys("manufacturer");
	      driver.findElement(By.id("email")).sendKeys(email);
	      driver.findElement(By.id("phoneNumber")).sendKeys("9677"+date);
	      driver.findElement(By.id("password")).sendKeys(pwd);
	      driver.findElement(By.id("confirm_password")).sendKeys("1234");
	      driver.findElement(By.xpath("//*[@id='root']/div/main/div/div/div/div/div[2]/form/div[2]/button")).click();
	      System.out.println("Manufacturing Registered successfully");
	      Thread.sleep(20000);
	      test.log(LogStatus.PASS, "Manufacturing_Registration Success");
    }

     @Test (priority = 2)
     @Then("^Subscription$")
     public void subscription() throws Throwable {
       
    	driver.findElement(By.xpath("//*[@id='subscription']/div[3]/div/button")).click();
	      driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div[3]/button[1]")).click();
	      Thread.sleep(20000);
	     List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
	      System.out.println("Size-"+iframes.size());
	  	 for(WebElement indvframe : iframes)
	       {
	      	 driver.switchTo().frame(indvframe);
	      	 System.out.println("frame- > " + indvframe);
	      	 
	      	 if (driver.findElement(By.xpath("//input[@name='cardnumber']")).isDisplayed())
	      	 {
	      		 System.out.println("Card details ");
	      		 Thread.sleep(5000);
	      	
	      		 driver.findElement(By.xpath("//input[@name='cardnumber']")).sendKeys("4242424242424242");
	      		 driver.findElement(By.xpath("//input[@name='exp-date']")).sendKeys("04 / 24");
	      		driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys("242");
	      		driver.findElement(By.xpath("//input[@name='postal']")).sendKeys("24242");
	      		
	      		 break;
	      	 }else
	      	 {
	      		 System.out.println("AddCard Not Found");
	      	 }}
	  	 driver.switchTo().defaultContent();
	     Thread.sleep(4000);
	  	driver.findElement(By.id("card_holder_name")).sendKeys("sk");
		   driver.findElement(By.xpath("//button[@type='submit']")).click();
		   test.log(LogStatus.PASS, "Subscription Success");
		   Thread.sleep(10000);
		   
    }

     @Test (priority = 3)
     @Then("^Add customer$")
     public void add_customer() throws Throwable {
    	 System.out.println("Customer page");
    	driver.navigate().to("http://3.13.34.41:3000/app/customers");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='app-container']/main/div/div/nav/div[2]/div/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("customer_name")).sendKeys("Manufacturer");
		driver.findElement(By.id("customer_email")).sendKeys(email);
		driver.findElement(By.id("customer_number")).sendKeys("111"+date);
		driver.findElement(By.id("customer_code")).sendKeys("manc1");
		driver.findElement(By.id("password")).sendKeys("manc1");
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div[3]/button[2]")).click();
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Customer Added");
		test.log(LogStatus.PASS,"Code : manc1  password : manc1");
    }

     @Test (priority = 4)
     @Then("^Add Operator$")
     public void add_Operator() throws Throwable {
    	driver.navigate().to("http://3.13.34.41:3000/app/operators");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='app-container']/main/div/div/nav/div[2]/div/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("operator_name")).sendKeys("Operator");
		driver.findElement(By.id("operator_email")).sendKeys(email);
		String code = "man"+date;
		String pwd1 = "man"+date;
		driver.findElement(By.id("operator_code")).sendKeys(code);
		driver.findElement(By.id("password")).sendKeys(pwd1);
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div[3]/button[2]")).click();
		test.log(LogStatus.PASS, "Operator Added");
		test.log(LogStatus.PASS, "    "+code+"   "+pwd1);
		Thread.sleep(5000);
    }

     @Test(priority = 5)
     @Then("^Add process$")
     public void add_process() throws Throwable {
    	 driver.navigate().to("http://3.13.34.41:3000/app/processes");
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@id='app-container']/main/div/div/nav/div[2]/div/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("process_name")).sendKeys("Process_01");
			driver.findElement(By.id("process_desc")).sendKeys("01");
			driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div[3]/button[2]")).click();
			test.log(LogStatus.PASS, "Process Added");
			Thread.sleep(5000);
//			
//	// Profile Page 
//			
//			 driver.findElement(By.xpath("//img[@alt='Profile']")).click();
//		  	    driver.findElement(By.xpath("//button[contains(.,'Account')]")).click();
//		  	    driver.navigate().refresh();
//		  	  Thread.sleep(10000);
  
    }

     @Test (priority = 6)
     @Then("^Logged out$")
     public void logged_out() throws Throwable {
    	
    	Thread.sleep(10000);
	    driver.findElement(By.xpath("//img[@alt='Profile']")).click();
  	    driver.findElement(By.xpath("//button[contains(.,'Sign out')]")).click();
  	    
	    System.out.println("Manufacturing Registered successfully");
	    Thread.sleep(5000);
	    test.log(LogStatus.PASS, "Manufacturing Logged-Out Successfully");
       extent.flush();
    }

     
}