package actions;

import org.openqa.selenium.By;

import Utility.InputBean;


public class PopupAction extends Driver {

	static InputBean input = new InputBean();
	
	static boolean flag =true;
	
public static  boolean buttonlesspopup(String name)
{
	
	try
	{
		System.out.println("the entered buttonless popup");
		driver.findElement(By.xpath("//span[contains(text(),'"+name+"')]/following::span[1]")).click();
		System.out.println("the entered buttonless popup");
	}
	catch(Exception e)
	{
		
		flag = false;
		input.setException(e.getMessage());
		
	}
	
	return flag;
}
	
	
	
	
	
	
	
	
	
}
