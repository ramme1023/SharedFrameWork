package Hybrid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import okio.Timeout;
import pages.UltiLogin;

public class TesterLogin extends BaseInitialization{
	String className=this.getClass().getSimpleName();
	File FolderPath=new File(System.getProperty("user.dir")); 
	
	
	
	@BeforeTest
	public void intialize() throws IOException, InterruptedException {
		LoadProp();
		MakeResultsFolderForTest(className);
		driver = InitializeDriver();
		Properties Prop = new Properties();
		FileOutputStream files = new FileOutputStream(FolderPath+"\\src\\main\\java\\resources\\Prop.properties");
		String str = Prop.getProperty("testname");
		Prop.setProperty("testname", className);
		Prop.store(files, "EnteredTc");
		files.close();
		files=null;
	}
	@Test
	public void OpenUrl() throws IOException, InterruptedException {
		driver.get("https://www.ultimatix.net");
		System.out.println();
		WebDriverWait wait = new WebDriverWait(driver, 05);
		Thread.sleep(3000);
		Screenshot("Login Screen");
		UltiLogin ul = new UltiLogin(driver);
		ul.EnterUserID().sendKeys("1023170");
		Thread.sleep(3000);
		Screenshot("User ID enetered");
		ul.ClickProcedBttn().click();
		Thread.sleep(3000);
		Screenshot("Selected Password");
		ul.PassBttn().click();
		ul.Passfield().sendKeys("Ulti@sep01");
		Thread.sleep(3000);
		Screenshot("Entered Password");
		ul.LoginBttn().click();
		Thread.sleep(3000);
		System.out.println("first completed");
		Screenshot("Final");
		
	}
	
	@AfterTest
	public void closeAll() throws IOException {
		System.out.println("eneterd after test");
		EndScreensDoc();
		driver.close();
		driver=null;
		Prop.setProperty("testname", "");
		FileOutputStream files = new FileOutputStream(FolderPath+"\\src\\main\\java\\resources\\Prop.properties");
		Prop.store(files, "EnteredTc");
		files=null;
		Prop=null;
		files.close();
		files=null;
	}

}
