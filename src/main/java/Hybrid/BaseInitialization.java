/**
 * 
 */
package Hybrid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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
	public static ArrayList<String> names = new ArrayList<String>();
	public static String strtestname="";

	// added by Ramesh Nalla on 30-sep-2019 intializing driver
	public WebDriver InitializeDriver() {

		String Browser = "chrome";

		switch (Browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", PathForDriver+"chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
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
	public void LoadProp() throws IOException {
		Properties Prop = new Properties();
		FileInputStream files = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Prop.properties");
		Prop.load(files);
		strtestname = Prop.getProperty("testname");
	}
	
	public void Screenshot(String name) throws IOException {
		i=i+1;
		Properties Prop = new Properties();
		FileInputStream files = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Prop.properties");
		Prop.load(files);
		String strtestname = Prop.getProperty("testname");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String finalname = i+". "+name;
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\Results\\"+strtestname+"\\"+finalname+".png"));
		names.add(finalname);
	}

	public static void MakeResultsFolderForTest(String TestName) throws IOException, InterruptedException {
		
		File src = new File(Userdir+TestName);
		File origsrc = src;
		if (!src.isDirectory()) {
			src.mkdir();
			System.out.println("Creating a new Results folder");
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
			String now = dtf.format(LocalDateTime.now());
			String strwithdate = TestName + "_" + now;
			File newDir = new File(src.getParent() + "\\" + strwithdate);
			src.renameTo(newDir);
			File destDir = new File(src.getParent() + "\\" + "Archive\\" + strwithdate);
			FileUtils.moveDirectory(newDir, destDir);
			System.out.println("Moved previous folder to Archieve and created freshly");

			origsrc.mkdir();

		}
	}
	public void EndScreensDoc() {
		try {

            XWPFDocument docx = new XWPFDocument();
            XWPFRun run = docx.createParagraph().createRun();
            FileOutputStream out = new FileOutputStream("C:\\Users\\UX015235\\eclipse-workspace\\SharedFrameWork\\Results\\TesterLogin\\Res.doc");

            for (String str : names) {
                docx.createParagraph();
                run.addBreak();
                run.setText(str);
                InputStream pic = new FileInputStream(Userdir+strtestname+"\\"+str+".png");
                run.addPicture(pic , XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(475), Units.toEMU(280));
            }

            docx.write(out);
            out.flush();
            out.close();
            docx.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exeception block for EndScreensDoc() in BaseInti");
        }
		
	}

}
