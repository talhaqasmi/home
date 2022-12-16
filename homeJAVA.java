package HMPROJECT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class homeJAVA {
	WebDriver driver;
	//Logger logger=Logger.getLogger("homeJAVA");
	
	@FindBy(xpath="//a[@class='departments-menu-v2-title']")
	WebElement PRODUCTS;
	
	@FindBy(xpath="//ul[@id='menu-all-departments-1']//child::li[@id='menu-item-4761']")
	WebElement Laptops;
	
	
	@FindBy(xpath="//select[@class='electro-wc-wppp-select c-select']")
	WebElement Showdrpdown;
	
	
	@FindBy(xpath="//div[@class='product-loop-body product-item__body']//child::*[@class='woocommerce-loop-product__title']")
	WebElement productName;
	
	@FindBy(xpath="//span[@class='woocommerce-Price-currencySymbol']")
	WebElement Lprice;
	
	@FindBy(xpath="//div[@class='product-short-description']")
	WebElement LDescription;
	
	
homeJAVA  (WebDriver driver){
this.driver = driver;
PageFactory.initElements(driver,this);
}
public void galaxy() throws InterruptedException, IOException {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	Actions action=new Actions(driver);
	action.moveToElement(PRODUCTS).perform();
	wait.until(ExpectedConditions.elementToBeClickable(Laptops));
	Laptops.click();
	Select show=new Select(Showdrpdown);
	show.selectByVisibleText("Show All");  //getting all the laptops in a single page
	
	XSSFWorkbook workbook1=new XSSFWorkbook();
	XSSFSheet sheet1=workbook1.createSheet("ALLDATA");
	sheet1.createRow(0);
	sheet1.getRow(0).createCell(0).setCellValue("LAPTOP NAME");
	sheet1.getRow(0).createCell(1).setCellValue("DESCRIPTION");
	sheet1.getRow(0).createCell(2).setCellValue("PRICE");

	
	
	List<WebElement> laptops = driver.findElements(By.xpath("//div[@class='product-loop-body product-item__body']//child::*[@class='woocommerce-loop-product__title']"));
    System.out.println(laptops.size());
    ArrayList<String> AllNames=new ArrayList<String>();
    for (WebElement webElement : laptops ) {
        String laptopNames = webElement.getText(); //getting all names
        System.out.println(laptops);
        //storing in array
        AllNames.add(laptopNames);
    }
    for(int i=1;i<AllNames.size();i++) {
	sheet1.createRow(i);
	sheet1.getRow(i).createCell(0).setCellValue(AllNames.get(i));
	}
   
  
    
    List<WebElement> laptopsdes = driver.findElements(By.xpath("//span[@class='woocommerce-Price-currencySymbol']"));
    System.out.println(laptopsdes.size());
    ArrayList<String> Alldes=new ArrayList<String>();
    for (WebElement DES : laptopsdes ) {
        String laptopDESCRIPTION = DES.getText(); //getting all names
        System.out.println(laptopsdes);
        //storing in array
        Alldes.add(laptopDESCRIPTION);
    }
    for(int i=1;i<Alldes.size();i=i++) {
	sheet1.getRow(i).createCell(1).setCellValue(Alldes.get(i));
	}
    
    
    
    List<WebElement> laptopsPrice = driver.findElements(By.xpath("//div[@class='product-short-description']"));
    System.out.println(laptopsPrice.size());
    ArrayList<String> AllPrice=new ArrayList<String>();
    for (WebElement price : laptopsPrice ) {
        String laptopPRICE = price.getText(); //getting all names
       System.out.println(laptopsPrice);
        //storing in array
        AllPrice.add(laptopPRICE );
    }
    for(int i=1;i<AllPrice.size();i=i++) {
    	sheet1.getRow(i).createCell(2).setCellValue(AllPrice.get(i));
	}  
	File fil=new File("C:\\Users\\4407\\eclipse-workspace\\HomeProject\\Book1.xlsx");
	FileOutputStream fos=new FileOutputStream(fil);
	workbook1.write(fos);

}
}