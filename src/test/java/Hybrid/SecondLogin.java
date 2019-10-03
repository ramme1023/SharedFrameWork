package Hybrid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.UltiLogin;

public class SecondLogin extends BaseInitialization{
	String className=this.getClass().getSimpleName();
	File FolderPath=new File(System.getProperty("user.dir")); 
	
	
	
	@BeforeTest
	public void intialize() throws IOException, InterruptedException {
		LoadProp();
		System.out.println("Load");
		MakeResultsFolderForTest(className);
		System.out.println("Folder");
		driver = InitializeDriver();
		System.out.println("driver");
		Properties Prop = new Properties();
		FileOutputStream files = new FileOutputStream(FolderPath+"\\src\\main\\java\\resources\\Prop.properties");
		Prop.setProperty("testname", className);
		Prop.store(files, null);
	}
	@Test
	public void ZE() throws IOException, InterruptedException {
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
		ul.Passfield().sendKeys("Ulti@sep001");
		Thread.sleep(3000);
		Screenshot("Entered Password");
		ul.LoginBttn().click();
		Thread.sleep(2003);
		Screenshot("Final One");
		
	}
	
	@AfterTest
	public void closeAll() {
		EndScreensDoc();
		driver.close();
		driver=null;
		Prop.setProperty("testname", "");
	}

}
