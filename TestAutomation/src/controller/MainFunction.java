package controller;



import java.util.ArrayList;
import java.util.List;

import utility.InputBean;
import WriteResult.WriteExcelSheet;
import actions.CheckScripts;
import actions.ClickActions;
import actions.DBCheck;
import actions.Driver;
import actions.GirdAction;
import actions.MapAction;
import actions.PopupAction;
import actions.Screenshot;
import actions.ScriptActions;


import actions.Sort;
import Excelsheet.DBdetails;
import Excelsheet.ReadFile_Product;
import Excelsheet.RowIterator;

public class MainFunction {

	
public static void main(String args[])
{
	
	
	
	
	try
	{
ReadFile_Product.readFile(1);
		
		DBdetails.dbrowiterator();
		
		Driver driverintialiser = new Driver();
		driverintialiser.browser();
		
		
	WriteExcelSheet excel = new WriteExcelSheet();
	ReadFile_Product.readFile(0);
	int i=0;

	
	for(i=1;i<=ReadFile_Product.RowId().size();i++)
	{
	
	RowIterator.rowiterator(i);
	
	System.out.println(RowIterator.inputdetails().getFieldtype());
	
	InputBean input = new InputBean();
	input=RowIterator.inputdetails();
	
	
	boolean flag=false;
	
	if(input.getFieldtype().equals("URL"))
	{
		System.out.println(input.getInputvalue());
		
		flag =	ScriptActions.UrlAction(input.getInputvalue());
	}
	else if(input.getFieldtype().equals("Textbox"))
	{
		String tag ="input";
		flag=	ScriptActions.TextBoxAction(input.getFieldname(),tag,input.getLocation(),input.getInputvalue(),input.getEventype(),input.getScreenName());
	}
	else if(input.getFieldtype().equals("PopupMessageCheck"))
	{
		
		flag = MapAction.popupmessagecheck(input.getFieldname(),input.getInputvalue());
	}
	else if(input.getFieldtype().equals("Button"))
	{
		@SuppressWarnings("unused")
		String tag = "button";
		flag=	ScriptActions.ButtonAction(input.getFieldname(),input.getScreenName(),input.getEventype());
	}
	else if(input.getFieldtype().equals("Dropdown"))
	{
		String tag ="select";
		flag=	ScriptActions.DropdownAction(input.getFieldname(),tag,input.getLocation(),input);
	}
	else if(input.getEventype().equals("Wait"))
	{
		flag=	ScriptActions.WaitAction(input.getInputvalue());
	}
	else if(input.getEventype().equals("ScreenNavigation"))
	{
		System.out.println("screen transistion entered");
		
		
		
		flag=	ScriptActions.WindowSwitchAction(input.getInputvalue());
	}
	
	else if(input.getFieldtype().contains("Label"))
	{	System.out.println("entered label check");
	
	
	
	String tag="";
	if(input.getFieldtype().equals("Label"))
	{
		//tag = "//label[contains(text(),'"+input.getInputvalue()+"')]";
		tag = "//span[text()="+"'"+input.getScreenName()+"']/following::label[text()="+"'"+input.getInputvalue()+"']";
	}
	else if(input.getFieldtype().equals("Gird-Label"))
	{
		//tag ="//div[contains(text(),'"+input.getInputvalue()+"')]";
		tag = "//span[text()="+"'"+input.getScreenName()+"']/following::span[text()="+"'"+input.getInputvalue()+"']";
		
	}
	
	else if(input.getFieldtype().equals("Button-Label"))
	{
		tag = "//span[text()="+"'"+input.getScreenName()+"']/following::span[text()="+"'"+input.getInputvalue()+"']";
	}
	flag=	CheckScripts.labelcheck(input.getInputvalue(),tag,input.getFieldtype());
	}
	else if(input.getEventype().equals("DropdownFieldcheck"))
	{	System.out.println("entered label check");
	String tag ="select";
	flag=	CheckScripts.DropdownValueCheckAction(input.getFieldname(),tag,input.getLocation(),input.getInputvalue());
}
	else if(input.getEventype().equals("DoubleClick"))
	{	
	flag=	ClickActions.DoubleclickAction(input.getFieldtype(),input.getFieldname());
}

	
	else if(input.getFieldtype().equals("GirdValue"))
	{
	
		flag =GirdAction.girdcontentcheck(input.getFieldname(),input.getLocation(),input.getInputvalue());
	}
	else if(input.getFieldtype().equals("girdclick"))
	{
		flag =GirdAction.girdclick(input.getFieldname(),input.getLocation(),input.getInputvalue(),input.getEventype(),input.getScreenName());
	}
	
	
	
	
	else if(input.getEventype().equals("DefaulSelectedCheck"))
	{
		String tag ="select";
		flag=	CheckScripts.DropdownDefaultValueCheckAction(input.getFieldname(),tag,input.getLocation(),input.getInputvalue());
		
	}

	else if(input.getFieldtype().equals("ButtonlessPopUp"))
	{
		flag =PopupAction.buttonlesspopup(input.getFieldname());
		
	}
	
	//loganathan
	
	else if(input.getFieldtype().equals("Tabclick"))
	{
		flag =ScriptActions.ankerTagAction(input.getFieldname(),input.getLocation());
	}
	//loganathan
	else if(input.getFieldtype().equals("Screennamecheck"))
	{
		flag =ScriptActions.screenNameCheck(input.getScreenName(),input.getInputvalue());
	}
	
	
	//loganathan
		else if(input.getFieldtype().equals("Checkboxclick"))
		{
			String tag ="input";
			flag=	ScriptActions.checkBoxClick(input.getScreenName(),tag,input.getLocation(),input.getFieldname());
			
				}

		else if(input.getEventype().contains("Query"))
		{
			System.out.println("entered db");
			flag = DBCheck.DBvaluecheck(input.getFieldname(),input.getInputvalue(),input.getEventype());
			
		}
	
	//loganathan
			else if(input.getFieldtype().equals("SelectButton"))
			{
			
				flag=	ScriptActions.SelectButtonAction(input.getFieldname(),input.getLocation());
				
					}
	
	//loaganathan
			else if(input.getFieldtype().equals("Editable"))
			{
				flag =	GirdAction.EditGird(input.getFieldname(),input.getLocation(),input.getInputvalue(),input);
			}
	
			else if(input.getFieldtype().equals("Sort"))
			{
				@SuppressWarnings("rawtypes")
				List datadb = new ArrayList();
				//System.out.println(input.getFieldname().replaceAll(":",","));
				
				datadb=	Sort.DBvaluecheck(input.getFieldname());
				flag=	GirdAction.girdcoloumvaluelist(input.getLocation(),datadb,input.getScreenName());
			}
	
			else if(input.getFieldtype().equals("Mapchild"))
			{
				
				flag = MapAction.mapchildclick(input.getFieldname(),input.getInputvalue());
			}
	
			else if(input.getFieldtype().equals("alert"))
			{
				
				flag = MapAction.alert();
			}
	if(input.getScreenshotRequired().equals("Y"))
	{
		Screenshot.screenSnaps(i);
	}
	
	
	excel.writeMeth(i,flag,input.getException());

	}
	

	
System.out.println("===Process finished=====");

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	
}


}
