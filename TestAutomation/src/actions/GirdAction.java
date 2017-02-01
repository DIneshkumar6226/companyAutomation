package actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;







import org.openqa.selenium.support.ui.Select;

import Utility.InputBean;


public class GirdAction extends Driver {

	
	
	
	static boolean flag =true;
	static Actions action;
	
	
	
	
	public static boolean  girdcontentcheck(String name,String location,String value)
	{
		
		String [] rowcoloum =location.split(":");
		
		String row = rowcoloum[0];
		String coloum = rowcoloum[1];
		
try
{
String actual = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div[contains(text(),'"+name+"')]")).getText();
if(actual.equals(value))
{
	flag = true;
}
		
else
{
	flag= false;
}
}
catch(Exception e)
{
	flag= false;
	System.out.println(flag);
}

return flag;
		
	}
	
	public static boolean EditGird(String name,String location,String value,InputBean input)
	{
String [] rowcoloum =location.split(":");
		
		String row = rowcoloum[0];
		String coloum = rowcoloum[1];
		action = new Actions(driver);
	System.out.println("edit gird");
	if(input.getEventype().equals("Textbox"))
	{
	
		
		WebElement element = null;
		try
		{
			
	element = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div[contains(@class,'cg')]/child::br"));
		flag = true;
		}
		catch(Exception e)
		{
			
			System.out.println("we have been hit");
			flag =false;
		}
		if(flag==false)
			
		{
			System.out.println("//tbody/tr["+row+"]/td["+coloum+"]/div[text()='"+name+"']");
			element = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div[text()='"+name+"']"));
		}
		try
		{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		action.moveToElement(element).doubleClick().build().perform();
	
		Thread.sleep(500);
		
		//System.out.println("//tbody/tr["+row+"]/td["+coloum+"]/div[text()='"+name+"']/div/input");
	 element =	driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div/div/input"));
		
		element.clear();
		element.sendKeys("");
		element.sendKeys(value);
		//element.sendKeys(Keys.ENTER);
		flag =true;

	}
	catch(Exception e)
	{
		backup(element,action,value,row,coloum,name);
	flag= false;

	if(!flag)
	{
		editaviabletextobx(element,action,value,row,coloum,name);
	}
	}

	
	}	
	
	else if(input.getEventype().contains("Dropdown"))
	{
		try
		{
			
			WebElement element = null;
			if(input.getEventype().equals("UpdateDropdown"))
			{
				element = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div[contains(@class,'cg') and @kbval="+input.getScreenName()+"]"));
				
			}
			else
			{
			
			System.out.println("//tbody/tr["+row+"]/td["+coloum+"]/div[contains(@class,'cg') and not(@kbval='1')]");
			element = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div[contains(@class,'cg') and not(@kbval)]"));
			
			}
			
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
			action.moveToElement(element).doubleClick().build().perform();
			Thread.sleep(500);
			
			
		 element =	driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div/div/select"));
			
			//element.clear();
			//element.sendKeys(value);
			flag =true;

			
			
			Select select = new Select(element);
			
			if(!input.getDropdownbyindex().contains("-"))
			{
				
				int index=Integer.parseInt(input.getDropdownbyindex());
				select.selectByIndex(index);
				flag = true;
			}
			else if(!input.getDropdownbyvalue().contains("-"))
			{
				select.selectByValue(input.getDropdownbyvalue());
				flag=true;
			}
			else if(!input.getDropdownbytext().contains("-"))
			{

	List<WebElement> li =select.getOptions();
	Iterator<WebElement> it = li.iterator();
	System.out.println("the element given"+input.getDropdownbytext());
	while (it.hasNext()) {
		
		WebElement element1 = it.next();
		System.out.println(element1.getText()+"the actual ");
		if(element1.getText().equals(input.getDropdownbytext()))
		{
			System.out.println(element1.getText());
			element1.click();
			flag=true;
			break;
		}
	}

		}

		}
		catch(Exception e)
		{
		flag= false;
		System.out.println(flag);
		}
	}
		return flag;	
		}
	

	
	
	
	public static boolean  girdclick(String name,String location,String value,String event,String screenane)
	{
	
	Actions	action = new Actions(driver);
	if(location.contains(":"))
	{
String [] rowcoloum =location.split(":");
	
	String row = rowcoloum[0];
	String coloum = rowcoloum[1];
	
try
{
	WebElement ele = null;

	if(event.equals("checkbox"))
	{
		if(!screenane.equals("-"))
		{
			System.out.println("//label[text()='"+screenane+"']/following::tbody/tr["+row+"]/td["+coloum+"]/div/div/input");
		ele= driver.findElement(By.xpath("//label[text()='"+screenane+"']/following::tbody/tr["+row+"]/td["+coloum+"]/div/div/input"));
		}
		else
		{
			ele= driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/input"));
		}
	}
	else if(event.equals("date"))
	{
		System.out.println("//div[text()='"+value+"']");
		ele= driver.findElement(By.xpath("//div[text()='"+value+"']"));
		
	}
	else
	{
	ele= driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div[text()='"+value+"']"));
	}
	//arguments[0].scrollIntoView(true)
	//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",ele);
System.out.println("i got it");

action.moveToElement(ele).click().build().perform();
System.out.println("i got it");
	flag = true;
}	
catch(Exception e)
{System.out.println("the expection is");
flag= false;
//e.printStackTrace();
}

	}	
	
	return flag;	
	}	
	
	
	@SuppressWarnings("unchecked")
	public static boolean girdcoloumvaluelist(String coloum,@SuppressWarnings("rawtypes") List datadb,String name)

	{String coloum2 = null;
	String colum1 = null;
		if(coloum.contains(":"))
		{
	String [] rowcoloum =coloum.split(":");
		
	 colum1 = rowcoloum[0];
		coloum2 = rowcoloum[1];
		System.out.println(colum1+""+coloum2);
		}
		
		
		@SuppressWarnings("rawtypes")
		List datainweb = new ArrayList();
		int i;
		int j=Integer.valueOf(colum1);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement ele;
		for(i=1;i<100;i++)
		{
			for(j=Integer.valueOf(colum1);j<=Integer.valueOf(coloum2);j++)
			{
				try
				{
			
					if(!name.equals("-"))
					{
					
						ele = driver.findElement(By.xpath("//label[text()='"+name+"']/following::tbody/tr["+i+"]/td["+j+"]/div[contains(@class,'cg')]"));
						System.out.println("//label[text()='"+name+"']/following::tbody/tr["+i+"]/td["+j+"]/div[contains(@class,'cg')]/div/input");
						// driver.findElement(By.xpath("//label[text()='"+name+"']/following::tbody/tr["+i+"]/td["+j+"]/div[contains(@class,'cg')]/div/input"));
					}
					else
					{
					ele = driver.findElement(By.xpath("//tbody/tr["+i+"]/td["+j+"]/div[contains(@class,'cg')]"));
					}
				
				
					datainweb.add(ele.getText().replace("/","-"));
				}
				catch(Exception e)
				{
					break;
				}
			}
		}
		System.out.println(datadb);
		System.out.println(datainweb);
		System.out.println(datainweb.size());
		System.out.println(datadb.size());
		flag=true;
		if(datainweb.size()==datadb.size())
		{
			
			for(i=0;i<datainweb.size();i++)
			{
				if(datadb.get(i).toString().equals(datainweb.get(i).toString()))
				{
					
				}
				else
				{
					System.out.println(datadb.get(i).toString());
					System.out.println(datainweb.get(i).toString());
					flag =false;
				}
			}
			
			
		}
		else
		{
			flag=false;
		}
		
		
		
		
		return flag;
	}
	
	public static void  backup(WebElement element,Actions action,String value,String row,String coloum,String name)
	{
		try
		{
			action = new Actions(driver);
		element = driver.findElement(By.xpath("//div//tbody/tr["+row+"]/td[not(@class='viewOnly')]["+coloum+"]/div"));
		
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		action.moveToElement(element).doubleClick().build().perform();
		 element =	driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div/div/input"));
		element.clear();
		element.sendKeys("");
		element.sendKeys(value);
		//element.sendKeys(Keys.ENTER);
	}
		catch(Exception e)
		{
			
			
			
		}
}

public static boolean editaviabletextobx(WebElement element,Actions action,String value,String row,String coloum,String name)
{
	try
	{
		element = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div[text()='"+name+"']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		action.moveToElement(element).doubleClick().build().perform();
		 element =	driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+coloum+"]/div/div/input"));
			element.clear();
			element.sendKeys("");
			element.sendKeys(value);
			flag=true;
	}
	catch(Exception e)
	{
		
	}
return flag;
}
}



