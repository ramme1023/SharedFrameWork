package Hybrid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TesterLogin extends BaseInitialization{
	String className=this.getClass().getSimpleName();
	File FolderPath=new File(System.getProperty("user.dir")); 
	
	
	@BeforeTest
	public void intialize() throws IOException, InterruptedException {
		driver = InitializeDriver();
		MakeResultsFolderForTest(className);
		Properties Prop = new Properties();
		FileInputStream files = new FileInputStream("C:\\Users\\UX015235\\eclipse-workspace\\SharedFrameWork\\src\\main\\java\\resources\\Prop.properties");
		Prop.load(files);
		String str = Prop.getProperty("testname");
		Prop.setProperty("testname", className);
	}
	@Test
	public void OpenUrl() throws IOException {
		driver.get("https://www.ultimatix.net");
		System.out.println();
		Screenshot("Login Screen");
		
		
	}

}
