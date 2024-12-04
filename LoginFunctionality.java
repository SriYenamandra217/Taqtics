package login;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginFunctionality
{       
	       WebDriver driver;
	      
	      @BeforeMethod
	   public void setUp()
	   {
		  driver = new ChromeDriver();
     	  driver.get("https://landmark.taqtics.co/");
     	  driver.manage().window().maximize();
     	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    }
	           @Test(priority = 1)
        public void LoginwithValidCredentials()
        {
	    	   driver.findElement(By.id("email")).sendKeys("intern@taqtics.co");
	    	   driver.findElement(By.id("password")).sendKeys("TestIntern@123");
	    	   driver.findElement(By.xpath("//button[@type='submit']")).click();
        }
	           @Test(priority =2)
	     public void LoginWithInvalidCreddentials()
	     {
	        	   driver.findElement(By.id("email")).sendKeys("intern@taqtics.com");
		    	   driver.findElement(By.id("password")).sendKeys("TestIntern@1234");
		    	   driver.findElement(By.xpath("//button[@type='submit']")).click();
	     }
	           public String generateRandominValidEmail()
	             {
	            	 String [] emails= {" intern2@taqtics.com","intern3@taqtics.com","intern@taqtics.co1","intern@taqticess.co","intern@tawqtics.co",
	            			 "intern1@taqtics.co"};
	            	 return emails[new Random().nextInt(6)];
	             }
	           
	           public String generateRandominValidPassword()
	             {
	            	 String [] password= {" TestIntern@12345","TesttIntern@1234","TestIntern@12","TestInternt@1234","TestIntern@007",
	            			 "TestIntern001@1234"};
	            	 return password[new Random().nextInt(6)];
	             }
	           @Test (priority=3)
	        public void LoginWithinvalidEmailandValidPassword()
	        { 
	        	   driver.findElement(By.id("email")).sendKeys(generateRandominValidEmail());
		    	   driver.findElement(By.id("password")).sendKeys("TestIntern@1234");
		    	   driver.findElement(By.xpath("//button[@type='submit']")).click();
	        	
	        }
	                @Test(priority = 4)
	           public void LoginWithvalidEmailandInvalidPassword()
		        { 
		        	   driver.findElement(By.id("email")).sendKeys("intern@taqtics.co");
			    	   driver.findElement(By.id("password")).sendKeys(generateRandominValidPassword());
			    	   driver.findElement(By.xpath("//button[@type='submit']")).click();
		        	
		        }
	                
	                @Test(priority = 5)
	                public void LoginwithNoCredentials()
	                {
	        	    	   driver.findElement(By.id("email")).sendKeys("");
	        	    	   driver.findElement(By.id("password")).sendKeys("");
	        	    	   driver.findElement(By.xpath("//button[@type='submit']")).click();
	                }
	                
	                @Test(priority = 6)
	              public void checkForgetPassword()
	              {
	            	boolean isDisplayed =driver.findElement(By.xpath("//span[text()=\"Forgot Password?\"]")).isDisplayed();
                         if (isDisplayed) {
	                      System.out.println("The link is displayed.");
	                            } else {
	                      System.out.println("The link is not displayed.");
	                          }
                         driver.findElement(By.xpath("//span[text()=\"Forgot Password?\"]")).click();
                         String actual="Forgot Password";
                       Assert.assertEquals(actual, driver.findElement(By.xpath("//h1[@class='ant-typography login_title']")).getText());
                     }
	                
	                @Test(priority = 7)
	             public void loggingWithKeyboardKeys()
	             {
	            	 Actions action=new Actions(driver);
	            	 action.sendKeys(Keys.TAB);
	            	 driver.findElement(By.id("email")).sendKeys("intern@taqtics.co");
	            	 action.sendKeys(Keys.TAB);
			    	 driver.findElement(By.id("password")).sendKeys("TestIntern@123");
			    	 action.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			     }
	                
	                      @Test(priority =8)   
	                public void verifyUnsuccessfulLoginCount() 
	                {
	                        int unsuccessfulLoginCount = 0;
                             for (int i = 0; i < 5; i++) 
                             {
	                           driver.findElement(By.id("email")).sendKeys(generateRandominValidEmail());
	         		    	   driver.findElement(By.id("password")).sendKeys(generateRandominValidPassword());
	         		    	   driver.findElement(By.xpath("//button[@type='submit']")).click();
	                           WebElement errorMessage = driver.findElement(By.xpath("//span[normalize-space()='Not Found']"));
                               String actualErrorMessage = errorMessage.getText();
                               String expectedErrorMessage = "Invalid username or password";
	                            if (actualErrorMessage.equals(expectedErrorMessage)) 
	                            {
	                                unsuccessfulLoginCount++;
	                            }
	                        }
	                System.out.println("Total unsuccessful login attempts: " + unsuccessfulLoginCount);
	                }
	                      
	                      @Test (priority = 9)
	                 public void verifyGoogleLink()
	                 {
	                    driver.findElement(By.xpath("//span[text()='Sign in with Google'][1]")).click();
	                	String expectedURL = "https://accounts.google.com/";	
	                	String redirectURL=driver.getCurrentUrl();
	                	Assert.assertEquals(redirectURL,expectedURL);
	                 }
	                            @Test (priority =10)
	                      public void verifyMicrosoftLink()
	 	                 {
	 	                    driver.findElement(By.xpath("//span[text()='Sign in with Google'][2]")).click();
	 	                	String expectedURL = "https://account.microsoft.com/account";	
	 	                	String redirectURL=driver.getCurrentUrl();
	 	                	Assert.assertEquals(redirectURL,expectedURL);
	 	                 }

	            @AfterMethod
	    	   public void tearDown()
	    	   {
	    		   driver.quit();
	    	   }
}

