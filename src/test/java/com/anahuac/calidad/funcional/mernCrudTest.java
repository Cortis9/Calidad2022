package com.anahuac.calidad.funcional;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class mernCrudTest {

	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  JavascriptExecutor js;

	  @Before
	  public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	  }

	  @Test
	  public void ACrear() throws Exception {
		    driver.get("https://mern-crud.herokuapp.com/");
		    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
		    pause(5000);
		    driver.findElement(By.name("name")).click();
		    pause(5000);
		    driver.findElement(By.name("name")).clear();
		    pause(5000);
		    driver.findElement(By.name("name")).sendKeys("alan");
		    pause(5000);
		    driver.findElement(By.name("email")).click();
		    pause(5000);
		    driver.findElement(By.name("email")).clear();
		    pause(5000);
		    driver.findElement(By.name("email")).sendKeys("hola@hmai.com");
		    pause(5000);
		    driver.findElement(By.name("age")).click();
		    pause(5000);
		    driver.findElement(By.name("age")).clear();
		    pause(5000);
		    driver.findElement(By.name("age")).sendKeys("12");
		    pause(5000);
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
		    pause(5000);
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
		    pause(5000);
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
	    
		    pause(5000);
		    
		    WebElement etiquetaEsperada = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div"));
	    
	    String text = etiquetaEsperada.getText();
	    
	    
	    assertThat("Nice one!", is(text));
	    
	  }
	  
	  @Test
	  public void BModificar() throws Exception {
		  
		    driver.get("https://mern-crud.herokuapp.com/");
		    pause(5000);
		    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
		    pause(5000);
		    driver.findElement(By.name("name")).click();
		    pause(5000);
		    driver.findElement(By.name("name")).clear();
		    pause(5000);
		    driver.findElement(By.name("name")).sendKeys("rene");
		    pause(5000);
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
		  
		   
		    
        pause(5000);
		    
	    WebElement etiquetaEsperada = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p"));
	    
	    String text = etiquetaEsperada.getText();
	    
	    
	    assertThat("Successfully updated!", is(text));
		  
	  }
	  
	  @Test
	  public void CEncontrar() throws Exception {
		  
		  driver.get("https://mern-crud.herokuapp.com/");
		  
		  pause(3000);
		    
		  WebElement etiquetaEsperada = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[1]"));
		    
		  String text = etiquetaEsperada.getText();
		    
		    
		  assertThat("Rene", is(text));
		  
		  
		  
	  }
	  
	  
	  @Test
	  public void DEliminar() throws Exception {
		  
		  driver.get("https://mern-crud.herokuapp.com/");
		    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
		    WebElement etiquetaEsperada = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]"));
		    pause(3000);
		    
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rene'])[2]/following::button[1]")).click();

		
		    
		    String text = etiquetaEsperada.getText();
		    
		    
		    assertThat("Rene", is(not(text)));
		  
	  }
	  

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	  
	  private void pause(long mils) {
		  try {
			  Thread.sleep(mils);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	  }
}

