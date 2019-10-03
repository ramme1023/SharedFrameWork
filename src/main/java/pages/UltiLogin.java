package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UltiLogin {
	
	WebDriver driver;

	public UltiLogin (WebDriver driver) {
		this.driver=driver;
	}
	
	By UserID = By.xpath( "//input[@id='form1']");
	By ProcedButton = By.xpath("//button[@id='proceed-button']");
	By Passbttn = By.xpath("//button[@id='password-btn']");
	By PassField = By.xpath("//input[@id='password-login']");
	By Loginbtttn = By.xpath("//button[@id='form-submit']");
	
	public WebElement EnterUserID() {
		return driver.findElement(UserID);
	}
	public WebElement ClickProcedBttn() {
		return driver.findElement(ProcedButton);
	}
	public WebElement PassBttn() {
		return driver.findElement(Passbttn);
	}
	public WebElement Passfield() {
		return driver.findElement(PassField);
	}
	public WebElement LoginBttn() {
		return driver.findElement(Loginbtttn);
	}

}
