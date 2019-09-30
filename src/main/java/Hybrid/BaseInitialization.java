/**
 * 
 */
package Hybrid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author Ramesh Nalla
 *
 */
public class BaseInitialization {
	public static WebDriver driver;
	public static String Userdir = System.getProperty("user.dir")+"\\Results\\";
	public static String PathForDriver=System.getProperty("user.dir")+"\\src\\main\\java\\Jars\\";

	// added by Ramesh Nalla on 30-sep-2019 intializing driver
	public WebDriver InitializeDriver() {

		String Browser = "chrome";

		switch (Browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", PathForDriver+"chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", PathForDriver+"geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "iexplore":
			System.setProperty("webdriver.ie.driver", PathForDriver+"IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;

		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	
	int i=0;
	public void Screenshot(String name) throws IOException {
		i=i+1;
		Properties Prop = new Properties();
		FileInputStream files = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Prop.properties");
		Prop.load(files);
		String str = Prop.getProperty("testname");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\Results\\"+str+"\\"+i+". "+name+".png"));
	}

	public static void MakeResultsFolderForTest(String TestName) throws IOException, InterruptedException {
		
		File src = new File(Userdir+TestName);
		File origsrc = src;
		if (!src.isDirectory()) {
			src.mkdir();
			System.out.println("completed with making folder");
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
			String now = dtf.format(LocalDateTime.now());
			String strwithdate = TestName + "_" + now;
			File newDir = new File(src.getParent() + "\\" + strwithdate);
			src.renameTo(newDir);
			System.out.println(newDir);
			File destDir = new File(src.getParent() + "\\" + "Archive\\" + strwithdate);
			System.out.println(destDir);
			FileUtils.moveDirectory(newDir, destDir);
			System.out.println("Completed with else block");

			origsrc.mkdir();

		}
	}

}
