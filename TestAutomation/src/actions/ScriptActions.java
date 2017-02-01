package actions;


import java.util.Iterator;
import java.util.List;
import java.util.Set;











import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;











import Utility.InputBean;
import constants.KeyConstants;


public class ScriptActions extends Driver {

	static boolean flag =true;
	static	InputBean input = new InputBean();
	//----------------------------------------------------------URL Action------------------------------------------------	
public static boolean 	UrlAction(String url)
{
	try
	{
	driver.get(url);
	//driver.manage().window().maximize();
	}
	catch(Exception e)
	{
		flag=false;
		System.out.println(flag);
	input.setException(e.getMessage());
	}
	
	return flag;
}
//----------------------------------------------------------Textbox Action------------------------------------------------
public static boolean TextBoxAction(String name,String tag,String location,String value,String event,String screenName)

{

	String actul ="";
	if(event.equals("=="))
	{
		
		if(!screenName.contains("-"))
		{
			try
			{
				Thread.sleep(350);
			actul=	driver.findElement(By.xpath("//span[text()='"+screenName+"']//following::"+tag+"["+location+"]")).getAttribute("bfval");
			}
			catch(Exception e)
			{
				System.out.println(actul);
			}
			if(actul.equals(value))
			{
				
				flag = true;
			}
			else
			{
				flag = false;
			}
			
		}
		
		
		
		
		
		
		
		else
		{
		
		try
		{
			
			System.out.println("//label[text()='"+name+"']//following::"+tag+"["+location+"]");
			actul=	driver.findElement(By.xpath("//label[text()='"+name+"']//following::"+tag+"["+location+"]")).getAttribute("bfval");
			System.out.println("the actual is"+actul);
			
		}
		catch(Exception e)
		{
			
			
			System.out.println("actual is"+actul);
		}
		
		
		
		if(actul==null)
		{
		try
		{
			actul=	driver.findElement(By.xpath("//label[text()='"+name+"']//following::"+tag+"["+location+"]")).getAttribute("errorval");
			
		}
		catch(Exception e)
		{
			actul=	driver.findElement(By.xpath("//input[contains(@bfval,'"+value+"')]")).getAttribute("errorval");
			
		}
		}
		
		if(actul==null)
		{
			try
			{
			System.out.println("//input[contains(@bfval,'"+value+"')]");
			actul=	driver.findElement(By.xpath("//input[contains(@bfval,'"+value+"')]")).getAttribute("bfval");
			System.out.println("actual"+actul);
			}
			catch(Exception e)
			{
				
			}
			
			}
		
		if(actul!=null)
		{
		if(actul.equals(value))
		{
			
			flag = true;
		}
		else
		{
			flag = false;
		}
		}
		else
		{
			flag = false;
		}
	}
	}
	else if(event.equals("KeyPress"))
	{
		
		
			 tag ="input";

		System.out.println("double click action");
		flag=	ScriptActions.KeyPressAction(name,tag,location,value,screenName);
	}
	else if(event.equals("KeyPresschord"))
	{
		 tag ="input";
		String a[] = value.split("%");
		if(a[0].contains("ctrl"))
		{
			System.out.println(a[1]);
			driver.findElement(By.xpath("//label[contains(text(),'"+name+"')]//following::"+tag+"["+location+"]")).sendKeys(Keys.chord(Keys.CONTROL,a[1]));	
		flag = true;
		}
		

		System.out.println("double click action");
			
		
		//flag=	ScriptActions.KeyPressAction(name,tag,location,value);
	}
	else if(event.equals("clear"))
	{
		
//loganathan
		if(!screenName.equals("-"))
		{
			try
			{
			driver.findElement(By.xpath("//span[text()='"+screenName+"']//following::"+tag+"["+location+"]")).clear();
			flag = true;
			}
			catch(Exception e)
			{
				flag=false;
				System.out.println(flag);
				input.setException(e.getMessage());
			}
		}
		
		
		else
		{
		
		try
		{
			driver.findElement(By.xpath("//label[text()='"+name+"']//following::"+tag+"["+location+"]")).clear();
		flag=true;
		}
		
		catch(Exception e)
		{
			flag=false;
			System.out.println(flag);
			input.setException(e.getMessage());
		}
		
	}
	}
	else if(event.equals("NonEditableCheck"))
	{
		
		try
		{
		driver.findElement(By.xpath("//label[text()='"+name+"']//following::"+tag+"["+location+"]")).sendKeys("123");
		
		flag=false;
		}
		catch(Exception e)
		{
			flag = true;
		}
		
		
	}
	
	else if (event.equals("EnterDate")) {
		int i;
		try {
			System.out.println("//label[text()='" + name
					+ "']//following::" + tag + "[" + location + "]");
			WebElement element = driver.findElement(By
					.xpath("//label[text()='" + name + "']//following::"
							+ tag + "[" + location + "]"));
			element.click();
			for (i = 0; i < 2; i++) {

				// element.sendKeys(Keys.DELETE);
			}
			element.sendKeys(value);
System.out.println(value);
			flag = true;

		} catch (Exception e) {
			flag = false;
		}
	}

	else if (event.equals("CleardateDate")) {
		int i;
		try {
			System.out.println("//label[text()='" + name
					+ "']//following::" + tag + "[" + location + "]");
			WebElement element = driver.findElement(By
					.xpath("//label[text()='" + name + "']//following::"
							+ tag + "[" + location + "]"));
			element.click();
			for (i = 0; i < 5; i++) {

				element.sendKeys(Keys.DELETE);
			}

		} catch (Exception e) {
			flag = false;
		}
	}
	
	
	
	
	//this is used to send the keys to the textbox
	
	else
	{
	
	if(!screenName.equals("-"))
	{
		try
		{
			if(value.equals("-"))
			{
				value="";
			}
			
		driver.findElement(By.xpath("//span[text()='"+screenName+"']//following::"+tag+"["+location+"]")).sendKeys(value);
		flag=true;
	}catch(Exception e)
	{
		flag=false;
		System.out.println(flag);
		input.setException(e.getMessage());
	}
	}
		
	
	else
	{

	try
	{
		driver.findElement(By.xpath("//label[text()='"+name+"']//following::"+tag+"["+location+"]")).sendKeys(value);
	flag=true;
	}
	catch(Exception e)
	{
		flag=false;
		System.out.println(flag);
		input.setException(e.getMessage());
	}
	}
	}

		return flag;
}
//----------------------------------------------------------Button Action------------------------------------------------
public static boolean ButtonAction(String value,String screenname,String event,String location)
{
	try
	{
		Thread.sleep(350);
		
		if(!location.equals("-"))
		{
			
			
			System.out.println("//span[text()='"+screenname+"']//following::button["+location+"]");
			driver.findElement(By.xpath("//span[text()='"+screenname+"']//following::button["+location+"]")).click();
			flag=true;
	
		}
		
		
		if(event.contains("=="))
		{
			if(!screenname.equals("-"))
			{
			flag =driver.findElement(By.xpath("//span[text()='"+screenname+"']//following::span[text()='"+value+"']")).isDisplayed();
			System.out.println("the button enabled"+flag);	
			}
			else
			{
				flag =	driver.findElement(By.xpath("//span[text()='"+value+"']")).isEnabled();
				
			}

			
		}
		
		else if(value.equals("Login"))
		{
		try
		{
			System.out.println("//button[@title='"+value+"']");
			driver.findElement(By.xpath("//button[@title='"+value+"']")).click();
			//driver.findElement(By.xpath("//button[@title='Login']")).click();
			
			flag=true;
		}
		catch(Exception e)
		{
			
		}
		}
		//loganathan
		else if(!screenname.equals("-"))
		{
			try{
				
				
				if(screenname.equals("2"))
				{
					System.out.println("//span[text()='"+value+"']/parent::button[@bfval='']");
					driver.findElement(By.xpath("//span[text()='"+value+"']/parent::button[@bfval='']//span")).click();	
				}
				else if(location.equals("-"))
				{
			driver.findElement(By.xpath("//span[text()='"+screenname+"']//following::span[text()='"+value+"']")).click();

				}flag=true;
		}
		catch (Exception e)
		{
			flag=false;
		}
			
			
			
	}
		
	
		
		
		
		
		
		else
		{
	try
		{
		//Actions ac = new Actions(driver);
		
	
		if(value.equals("メンテナンス"))
		{
	
			Actions ac = new Actions(driver);
			WebElement ele =driver.findElement(By.xpath("//label[text()='"+value+"']"));
			ac.moveToElement(ele).build().perform();
			
		}else
			
			
		{
			
			
			
			
			
			System.out.println("//span[text()='"+value+"']");
			
			driver.findElement(By.xpath("//span[text()='"+value+"']")).click();
			flag=true;
		}
		}
		catch(Exception e)
		{
			flag = false;
		}
		
//loganathan logic	
if(!flag)
{
	try
	{
		Thread.sleep(1000);
		System.out.println("//div[text()='"+value+"']/parent::dt");
		driver.findElement(By.xpath("//div[text()='"+value+"']/parent::dt")).click();
		flag=true;
	}
	catch(Exception e)
	{
		System.out.println("we caught with expection");
		flag = false;
	}
}
	
	
	
	
if(!flag)
{
		try
		{
			driver.findElement(By.xpath("//div[text()='"+value+"']")).click();
			flag=true;
		}
		catch(Exception e)
		{
			flag = false;
		}
		
if(!flag)
{
		
		
		try
		{
			driver.findElement(By.xpath("//a[text()='"+value+"']")).click();
			flag=true;
		}
		catch(Exception e)
		{
		}
		
		}
}

if(!flag)
{
		try
		{
			System.out.println("//label[text()='"+value+"']");
			driver.findElement(By.xpath("//label[text()='"+value+"']")).click();
			flag=true;
		}
		catch(Exception e)
		{
			flag = false;
		}
}


	}
	}

	catch(Exception e)
	{
		flag=false;
		System.out.println(flag);
		input.setException(e.getMessage());
	}
	
		return flag;
}



//----------------------------------------------------------Wait Action------------------------------------------------
public static boolean WaitAction(String value)
{
	int sleepvalue = Integer.parseInt(value);
System.out.println(sleepvalue+"parsed int");
	try
	{
		Thread.sleep(sleepvalue);
	}
	catch(Exception e)
	{
		flag=false;
		System.out.println(flag);
		input.setException(e.getMessage());
	}
	
		return flag;
}
//----------------------------------------------------------Window Action------------------------------------------------
public static boolean WindowSwitchAction(String val) throws InterruptedException {
	
	try {
        int i = 1;
        Set<String> windows = driver.getWindowHandles();
System.out.println(windows.size()+"the size of window");
        Iterator<String> iterator = windows.iterator();
int value =Integer.valueOf(val);
        while (iterator.hasNext()) {
            String windowhandler = iterator.next();
            System.out.println(windowhandler+"the windows");
            if (i == value) {
                Thread.sleep(2000);
                driver.switchTo().window(windowhandler);
                driver.manage().window().maximize();
               
            } else {
                // driver.close();
            }
            i++;

        }
        flag=true;
    } catch (Exception e) {
    	flag=false;
		System.out.println(flag);
		input.setException(e.getMessage());
    }
	/*String base = driver.getWindowHandle();
	Set<String> set = driver.getWindowHandles();
	 String windowhandler = null;
		System.out.println(set);
		System.out.println(set.size());
	 Iterator<String> iterator = set.iterator();
	while (iterator.hasNext()) {
		windowhandler    = iterator.next();
        System.out.println(windowhandler+"the windows");
       
	}
	Thread.sleep(5000);
	driver.switchTo().window(windowhandler);
	*/

	//set.remove(base);
	//assert set.size() == 2;



	//close the window and sitch back to the base tab
	//driver.close();
	//driver.switchTo().window(base);
	return flag;	
}


//----------------------------------------------------------Key Press Action------------------------------------------------
public static boolean KeyPressAction(String name,String tag,String location,String value,String screename) {
	try {
System.out.println("entered the keypress action");

//System.out.println("//label[text()='"+name+"']//following::"+tag+"["+location+"]");

if(!screename.equals("-"))
{
	driver.findElement(By.xpath("//span[text()='"+screename+"']//following::"+tag+"["+location+"]")).sendKeys(KeyConstants.KeyBoardConstants().get(value));
	flag=true;
}
else
{


driver.findElement(By.xpath("//label[text()='"+name+"']//following::"+tag+"["+location+"]")).sendKeys(KeyConstants.KeyBoardConstants().get(value));
		flag=true;
}	

    } catch (Exception e) {
    	flag=false;
		System.out.println(flag);
		input.setException(e.getMessage());
    }
	return flag;		
	
}		
//----------------------------------------------------------Dropdown Action------------------------------------------------
public static boolean DropdownAction(String name,String tag,String location,InputBean input)
{
	WebElement ele =null;
	try
	{
		if(!input.getScreenName().equals("-"))
		{
			try
			{
			
				ele= driver.findElement(By.xpath("//span[text()='"+input.getScreenName()+"']//following::select["+location+"]"));
				
		}
			catch(Exception e)
			{
				flag=false;
				System.out.println("the expection is");
				System.out.println(e.getMessage());
			}
		}
		
		else
		{
		Thread.sleep(250);
		ele= driver.findElement(By.xpath("//label[contains(text(),'"+name+"')]//following::"+tag+"["+location+"]"));
		}
		Select select = new Select(ele);
		
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
	
	WebElement element = it.next();
	System.out.println(element.getText()+"the actual ");
	if(element.getText().equals(input.getDropdownbytext()))
	{
		System.out.println(element.getText());
		element.click();
		flag=true;
		break;
	}
}

	}
	
	}
	catch(Exception e)
	{
		flag=false;
		System.out.println("the expection flag is"+flag);
		input.setException(e.getMessage());
	}
		return flag;
	

	
	
}

//loganathan ankertagAction
//----------------------------------------------------------Anker (Tab click) Action------------------------------------------------
public static boolean ankerTagAction(String name,String location){
	
	
	try
	{
		System.out.println();
		WebElement ankerelement = driver.findElement(By.xpath("//label[contains(text(),'"+name+"')]//parent::a["+location+"]"));
		ankerelement.click();
	}catch(Exception e)
	{
		flag=false;
		System.out.println("the expection flag is"+flag);
		input.setException(e.getMessage());
		
	}
	
return flag;

}

//loganathan
//----------------------------------------------------------Screen name check Action------------------------------------------------
	public static boolean screenNameCheck(String name,String value){
		try
		{
			
			String screenelement = driver.findElement(By.xpath("//span[contains(text(),'"+name+"')]")).getText();
			if(screenelement.equals(value))
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}catch(Exception e)
		{
			flag=false;
			System.out.println("the expection flag is"+flag);
			input.setException(e.getMessage());
			
		}
		return flag;
	}
	//loganathan	
	//----------------------------------------------------------Check box click Action------------------------------------------------
	public static boolean checkBoxClick(String screenName,String tag,String location,String fieldname){
		try
		{
			if(!screenName.contains("-"))
			{
				
				Thread.sleep(350);
				driver.findElement(By.xpath("//span[text()='"+screenName+"']//following::"+tag+"["+location+"]")).click();
				flag = true;
				
				
				
				
			}
			else
			{
				Thread.sleep(350);
			
				driver.findElement(By.xpath("//label[text()='"+fieldname+"']//following::"+tag+"["+location+"]")).click();
				flag = true;
				
			}
		}catch (Exception e)
		{
			flag=false;
			System.out.println("the expection flag is"+flag);
			input.setException(e.getMessage());
		}
		return flag;
	}
	//loganathan
	//----------------------------------------------------------Select box which comes in logu screen------------------------------------------------
	public static boolean SelectButtonAction(String fieldname,String location)
	{
		try
		{
			
WebElement ele =			driver.findElement(By.xpath("//label[text()='"+fieldname+"']/preceding::div[contains(@id,'detail_first')]/child::div[1]/div["+location+"]/div[contains(@class,'dynamic_div_normal')]/div[1]"));


((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",ele);
ele.click();
flag = true;
		}catch (Exception e)
	{
		flag=false;
		System.out.println("the expection flag is"+flag);
		input.setException(e.getMessage());
	}
	return flag;
}
	
	
	
	public static boolean java_script_executor(String name,String loacation,String fieldtype,String value,String screenname)
	{
		try
		{
			
			String id = null;
		WebElement element;
			if(fieldtype.equals("Textbox"))
			{
				
				if(!screenname.equals("-"))
				{
					element =		driver.findElement(By.xpath("//label[text()='"+screenname+"']/following::input["+loacation+"]"));
				}
				
				
				else
				{
				element =		driver.findElement(By.xpath("//label[text()='"+name+"']/following::input["+loacation+"]"));
				
				}
				id=element.getAttribute("id");
				}
			
			
		JavascriptExecutor exe =(JavascriptExecutor)driver;
		
		WebElement ele =(WebElement) exe.executeScript("return document.getElementById('"+id+"');");

		
		if(value.equals(ele.getAttribute("value")))
			{
		flag=true;
		System.out.println(ele.getAttribute("value")+"pass");
			}
	else
	{
		
		System.out.println(ele.getAttribute("value")+"fail");
		flag=false;
	}
			
		}
		catch(Exception e)
		{
			flag= false;
			e.printStackTrace();
		}
		System.out.println(flag);
		return flag;
		}

	
public static boolean drag_drop(String coloumname,String row)
{
	try
	{
		Actions act =new Actions(driver);
	
		System.out.println("//div[contains(@data-columns,'"+coloumname+"')]//child::canvas[1]");
		WebElement ele = driver.findElement(By.xpath("//div[contains(@data-columns,'"+coloumname+"')]//parent::div//parent::div[contains(@class,'container')]"));
		Point po =ele.getLocation();
		
		
		//ele=	driver.findElement(By.xpath("//div[@class='goog-splitpane-second-container']"));
		
		
		////div[contains(@data-columns,'年度名称')]//parent::div//parent::div[contains(@class,'container')]
		System.out.println(po.getX()+"yyy"+po.getY());
		
		int rowtobeselected=Integer.parseInt(row);
		
		
		int x,y;
		//-75 is the leat
		x=200-((rowtobeselected-1)*25);
		y=(-200)+((rowtobeselected-1)*25);
		
		
	System.out.println(x+"the value"+y);
		//50,75
	act.dragAndDropBy(ele,50,75).release().doubleClick().build().perform();
		flag=true;	
	}

	catch(Exception e)
	{
		flag=false;	
		e.printStackTrace();
	}
	return flag;
}
	
public static boolean buttonhue(String screenname,String location,String event)
{

	
	
	
	if(event.equals("HUE-POPUP"))
	{
		
	
		

		try
		
		{Thread.sleep(2000);
			System.out.println("//h4[text()='"+screenname+"']/following::button["+location+"]//span");
			driver.findElement(By.xpath("//label[text()='"+screenname+"']/following::button["+location+"]")).click();
			flag=true;	
		}

		catch(Exception e)
		{
			flag=false;
			
		}
		
	}
	
	
	else
	{
	
	try
	{
		driver.findElement(By.xpath("//label[text()='"+screenname+"']/following::button["+location+"]")).click();
		flag=true;	
	}

	catch(Exception e)
	{
		flag=false;
		e.printStackTrace();
	}
	}
	return flag;
}
public static boolean hueclaender(String fieldname, String inputvalue,
		String location) {

	
	try
	{
		driver.findElement(By.xpath("//label[text()='"+fieldname+"']//following::input["+location+"]")).click();
		String input[] =inputvalue.split("/");
		
		String year=input[0];
		String month = input[1];
		
		int month2=Integer.parseInt(month);
		int year2 =Integer.parseInt(year);
		String day =input[2];
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@data-handler='prev']")).click();
String calender_year=		driver.findElement(By.xpath("//span[@class='date-input-year']")).getText();
		
String calender_month=			driver.findElement(By.xpath("//span[@class='date-input-month']")).getText();
		
calender_month=calender_month.replace("月", "");
System.out.println(calender_year+""+calender_month);

int year1 =Integer.parseInt(calender_year);

int month1=Integer.parseInt(calender_month);


int diff=year1-year2;

int diff1=month1-month2;

System.out.println(diff+""+diff1);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	
	return flag;
}
	
}
