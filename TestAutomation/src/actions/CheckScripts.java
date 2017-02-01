package actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;





import Utility.InputBean;


public class CheckScripts extends Driver {

	static boolean flag = true;
	static	InputBean input = new InputBean();
	public static boolean labelcheck(String Expected_label,String Tag,String gird) {

		try {

			String actual = null;
		
			System.out.println(Tag);
			
			if(gird.equals("Gird-Label"))
			{
			
				
				WebElement ele = driver.findElement(By.xpath(Tag));
				
				//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",ele);
				
				actual=	ele.getText();
				System.out.println("the actual is");
			}
			else
			{
				actual=	driver.findElement(By.xpath(Tag)).getText();	
			}
			
				if (actual.equals(Expected_label)) {
					flag =true;
				
			} else {
					flag = false;
					
		} 
		}catch (Exception e) {
			flag = false;
			
System.out.println(flag+"the expection is"+Expected_label);
input.setException(e.getMessage());
		}
		return flag;

	}




	public static boolean DropdownValueCheckAction(String name,String tag,String location,String value) {

		try {
			int i=0;
			System.out.println("enterd drop down logic");
			
			List<String> expected = new ArrayList<String>();
			String[] dropvalue = value.split("/");
		
			try
			{
				for(i=0;i<=15;i++)
				{
					
				expected.add(dropvalue[i]);
				}
				
			}
			catch(Exception e)
			{
				input.setException(e.getMessage());
			}
			
			
			
			
			List<String> actual = new ArrayList<String>();
			WebElement element =driver.findElement(By.xpath("//label[contains(text(),'"+name+"')]//following::select["+location+"]"));
			Select select = new Select(element);
			List<WebElement> dropdownvalues = new ArrayList<WebElement>();
		 dropdownvalues = select.getOptions();
			Iterator<WebElement> dropdowniterator = dropdownvalues.iterator();
			while (dropdowniterator.hasNext()) {
				WebElement dropdownelement = dropdowniterator.next();
				actual.add(dropdownelement.getText());

			}

			if(actual.containsAll(expected))
			{
				flag =true;
				System.out.println(expected);
				System.out.println(actual);
			}
			else
			{
				flag =false;
				System.out.println(expected);
				System.out.println(actual);
			}
			
		
			
		}catch (Exception e) {
			flag = false;
			System.out.println(flag+"the expection");	
			input.setException(e.getMessage());
		}

		return flag;
	}

	public static boolean DropdownDefaultValueCheckAction(String fieldname,
			String tag, String location, String inputvalue) {
		
		
		try
		{
			System.out.println("entered");
		WebElement element =driver.findElement(By.xpath("//label[contains(text(),'"+fieldname+"')]//following::select["+location+"]"));
		Select select = new Select(element);
		System.out.println(select.getFirstSelectedOption().getText());
	
		if(select.getFirstSelectedOption().getText().equals(inputvalue))
				{
			System.out.println(inputvalue);
			flag = true;
				}
		else
		{
			flag = false;
		}
		
		}
		catch (Exception e) {
		flag = false;
			// TODO: handle exception
		}
		
		
		return flag;
	}

	
	
	
}
