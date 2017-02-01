package Excelsheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import Utility.InputBean;

public class DBdetails {
	
@SuppressWarnings("rawtypes")
static Map map= new HashMap();
	 
@SuppressWarnings("rawtypes")
static Vector dataHolder = new Vector();	

static InputBean input;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void dbrowiterator()
	{
		input = new InputBean();
		map=ReadFile_Product.RowId();

		

	HSSFRow hssfRow = (HSSFRow) map.get(2) ;
		
		Iterator iterator = hssfRow.cellIterator();
		List cellTempList = new ArrayList();
		while (iterator.hasNext()) {
			HSSFCell hssfCell = (HSSFCell) iterator.next();
			switch (hssfCell.getCellType()) {  
			case 0:  
	            Double d=hssfCell.getNumericCellValue();
	            int i=d.intValue();
	       
	        
	            cellTempList.add(i);
	        
	            
	            break;  
	        case 1:  
	        	cellTempList.add(hssfCell.getStringCellValue());
	        	
	        
	            break;  
	        case 2:                               
	            break;                            
	        }  	
		
			
			
	}
		String rowElements = cellTempList.toString();
		System.out.println(rowElements);
		
		 String[] elements = rowElements.split(",");
		 input.setDbUrl(elements[1].trim());
		 input.setSsid(elements[2].trim());
		 input.setPortnummber(elements[3].trim());
		 input.setUsername(elements[4].trim());
		 input.setPassword(elements[5].trim());
		input.setBrowsername(elements[6].trim());
		input.setScreenNamePath(elements[7].trim());
		 
		
	}
	public static  InputBean DBinputdetails()
	{
		return (input);
	}

	
}
