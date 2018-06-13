package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	public static void main(String[] args) throws InterruptedException {
		
		//5.Enter a random quantity between 1 and 100
		Random random = new Random();
		int result = random.nextInt(100);
		
		Random random1 = new Random();
		int num = random1.nextInt(90);
		
		//6.b)Instead of <middle_name> your code should enter a random string every time.
		while(result<65) {
			num = random1.nextInt(90);
		}
		char c = (char) num;
		String middle= c +".";
		
		//1.Open browser
		System.setProperty("webdriver.chrome.driver",
				"/Users/Owner/Documents/selenium dependencies/drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//2.Go to url http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx 
		//(Links to an external site.)Links to an external site.
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
			
		//3.Login using username Tester and password test.
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		
		//4.Click on Order link
		driver.findElement(By.cssSelector("input[type ='submit']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a[href='Process.aspx']")).click();
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(String.valueOf(result));

		//6.Enter Customer Name: John <middle_name> Smith. 
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John " + middle + "Smith");
		
		//7.Enter street: 123 Any st
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");
		
		//8.Enter City: Anytown
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");
		
		//9.Enter State: Virginia
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");
		
		//10.Enter a random 5 digit number to the zip code field
		
		Random random2 = new Random();
		int zipCode = random.nextInt(99999);
		
		while (zipCode<10000) {
			zipCode = random2.nextInt(99999);
		}
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(String.valueOf(zipCode));
		
		//11.Select any card types. Every time your code should select a different type.
		
		Random card = new Random();
		int cardType = card.nextInt(3);
		
		Random cardNumber = new Random();
		
		
		System.out.println(cardType);
		if(cardType==0) {
			driver.findElement(By.cssSelector("label[for='ctl00_MainContent_fmwOrder_cardList_0']")).click();
			StringBuilder sb = new StringBuilder();
			sb.append(4);		//If you selected Visa, card number should start with 4. 
			for (int i = 0; i <= 15; i++) {		
				sb.append(cardNumber.nextInt(10));
			}     
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(sb.toString());
		}else if (cardType==1) {
			driver.findElement(By.cssSelector("label[for='ctl00_MainContent_fmwOrder_cardList_1']")).click();
			StringBuilder sb = new StringBuilder();
			sb.append(5);		//If you selected Master, card number should start with 5.Card numbers should be 16 digits for Visa and Master
			for (int i = 0; i <= 15; i++) {		
				sb.append(cardNumber.nextInt(10));
			}
		         
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(sb.toString());
		}else if(cardType==2) {
			driver.findElement(By.cssSelector("label[for='ctl00_MainContent_fmwOrder_cardList_2']")).click();
			StringBuilder sb = new StringBuilder();
			sb.append(3);	//If you selected American Express, card number should start with 3.	15 for American Express.
			for (int i = 0; i <= 14; i++) {		
				sb.append(cardNumber.nextInt(10));
			}
		         
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(sb.toString());
		}
		/*
		12.Enter any card number. 
		New card number should be auto generated every time you run the test.  
		*/
		
		//13.Enter any valid expiration date 
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("05/23");
		
		//14.Click on Process
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		//driver.findElement(By.cssSelector("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		//15.Verify than the page contains text New order has been successfully added.
		String expected = "New order has been successfully added.";
		
		 String actual = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText();
	        System.out.println(actual);
	        if(expected.equals(actual))
	            System.out.println("Expected result matches the actual result.");
	        else
	            System.out.println("Expected result does not match the actual result.");

	      
	}
	 
}
