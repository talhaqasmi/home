package HMPROJECT;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class homePOM {
	WebDriver driver;
	homeJAVA object;
	@BeforeTest
	public void before() {
	driver =new ChromeDriver();
	driver.get("https://www.galaxy.pk/");
	driver.manage().window().maximize();
	object=new homeJAVA(driver);
	//PropertyConfigurator.configure("log4j.properties");
	}
	@Test
	public void customer() throws Exception {
		object.galaxy();
	}
	//@AfterTest
	//public void close() throws Exception {
		//driver.close();
	//}

}