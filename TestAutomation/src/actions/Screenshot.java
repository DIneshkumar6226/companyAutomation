
package actions;

import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import Excelsheet.DBdetails;


public class Screenshot extends Driver {

	public static void screenSnaps(int stepno) {
		try {

			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			
			
			FileUtils.copyFile(scrFile, new File(DBdetails.DBinputdetails().getScreenNamePath()
							+"/"+"Step "+stepno + ".png"));
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}
	
	
	
}
