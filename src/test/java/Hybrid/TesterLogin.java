package Hybrid;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TesterLogin extends BaseInitialization{
	
	@BeforeTest
	public void intialize() {
		driver = InitializeDriver();
		String className = this.getClass().getSimpleName();
		System.out.println(this.getClass().getSimpleName());
	}
	@Test
	public void OpenUrl() {
		driver.get("https://www.ultimatix.net");
		
	}

}
