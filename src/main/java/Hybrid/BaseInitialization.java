/**
 * 
 */
package Hybrid;

import java.util.concurrent.TimeUnit;

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
	// added by Ramesh Nalla on 30-sep-2019 intializing driver 
	public WebDriver InitializeDriver() {

		String Browser = "iexplore";
		
		switch (Browser) {
			case "chrome" :
				System.setProperty("webdriver.chrome.driver", "C:\\Selemium\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "firefox" : 
				System.setProperty("webdriver.gecko.driver", "C:\\Selemium\\geckodriver-v0.25.0-win64\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case "iexplore" :
				System.setProperty("webdriver.ie.driver", "C:\\Selemium\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
				driver =  new InternetExplorerDriver();
				break;	
			
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;	
	}
	

}
