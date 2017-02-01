package actions;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;





public class MapAction extends Driver {

	
	static boolean flag =false;
	
	public static boolean mapchildclick(String parent,String child)
	{
		
try
{
		driver.findElement(By.xpath("//span[text()='"+parent+"']/following::span[text()='"+child+"'][1]")).click();
		flag=true;
}
catch(Exception e)
{
	flag =false;
}
		
		return flag;
	}

	public static boolean alert() {
		try
		{
		
			Robot ro = new Robot();
			ro.keyPress(KeyEvent.VK_DOWN);
			
			ro.keyPress(KeyEvent.VK_ENTER);
			
		}
		catch(Exception e)
		{
			System.out.println("we have beebn hit");
		}
		return true;
	}

   public static boolean popupmessagecheck(String actual,String expected)
  {
	
try
{
String actualmsg=	driver.findElement(By.xpath("//span[text()='"+actual+"']")).getText();
System.out.println(actual+"msg is ");
if(actualmsg.equals(expected))
{
	flag=true;
}
else
{
	flag=false;
}
}

catch(Exception e)
{
flag =false;
}
	
	return flag;
}	

}

