package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Utility.InputBean;


public class ClickActions extends Driver  {

	
	static boolean flag = true;
	static Actions action = null;
	static	InputBean input = new InputBean();
public static boolean DoubleclickAction(String fieldtype,String value)
{
	
	action = new Actions(driver);
	WebElement ele=null;
	String tag="";
	if(fieldtype.equals("Gird"))
	{
	
	
		

		
		tag = "div";
	try
	{
		System.out.println("//"+tag+"[text()='"+value+"']");
	 ele=	driver.findElement(By.xpath("//"+tag+"[text()='"+value+"']"));
		

	
	action.moveToElement(ele).doubleClick().build().perform();
	
	}
	catch(Exception e)
	{
		flag = false;
		System.out.println(flag);
		input.setException(e.getMessage());
	}
	
	}
	
	else if(fieldtype.equals("button"))
	{
		
		 ele=  driver.findElement(By.xpath("//label[contains(text(),'"+value+"')]"));
			action.moveToElement(ele).doubleClick().build().perform();
		
	}
	return flag;
}
	
	
	
	
	
	
}
