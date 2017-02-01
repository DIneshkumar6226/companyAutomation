package actions;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import Commoninterface.Driveraction;
import Excelsheet.DBdetails;

public class Driver implements Driveraction{

static	WebDriver driver ;
	
@SuppressWarnings("static-access")
public   void  browser() 
{
	WebDriver driver = null;
	
	if(DBdetails.DBinputdetails().getBrowsername().equals("IE"))
	{
		File file = new File(driverpath);
		System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
		driver = new InternetExplorerDriver();
	}
	else if(DBdetails.DBinputdetails().getBrowsername().equals("Firefox"))
	{
		System.out.println("firefox");
		   FirefoxProfile fxProfile = new FirefoxProfile();

		    fxProfile.setPreference("browser.download.folderList",2);
		    fxProfile.setPreference("browser.download.manager.showWhenStarting",false);
		    fxProfile.setPreference("browser.download.dir","D:\\ram\\");
		  
		   
		  // fxProfile.setPreference("browser.helperApps.neverAsk.openFile",
				//	"text/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel");
		  
		   // fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", " application/octet-stream, text/csv");	   
		   
		 //   fxProfile.setPreference(
				 //	"browser.helperApps.neverAsk.saveToDisk",
				//	"application/octet-stream"); 
		    
		    
		   
		   // fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
		   
		   
		   
		  
		driver = new FirefoxDriver(fxProfile);

	}
 
	this.driver = driver;
}
	
	
}
