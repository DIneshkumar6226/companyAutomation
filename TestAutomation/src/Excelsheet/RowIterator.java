
/*
 * System Name     :POS
 * Sub system Name :Iterate Individual Row
 * 
 *
 * �ｽ蠖抛vision History�ｽ�ｽ
 * Date       Name(Company Name)             Description
 * ---------- ------------------------------ ---------------------
 * 2014/6/30 Senthil_vel(Infoview)            New Creation
 *
 */
package Excelsheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import utility.InputBean;





public class RowIterator {

	
@SuppressWarnings("rawtypes")
static Map map= new HashMap();
	 
@SuppressWarnings("rawtypes")
static Vector dataHolder = new Vector();	
static InputBean input;
	
@SuppressWarnings({ "rawtypes", "unchecked" })
public static void rowiterator(int rownum)
{
	input = new InputBean();
	map=ReadFile_Product.RowId();

	rownum =rownum+1;

HSSFRow hssfRow = (HSSFRow) map.get(rownum) ;
	
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
	
	 String[] elements = rowElements.split(",");
	
	 input.setRecursivestep(elements[2].trim());
	 if(!input.getRecursivestep().contains("-"))
	 {
		 int newrownum = Integer.parseInt(input.getRecursivestep());
		 newrownum =newrownum+1;
		 
		 hssfRow = (HSSFRow) map.get(newrownum) ;
			
			 iterator = hssfRow.cellIterator();
			 cellTempList = new ArrayList();
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
			 rowElements = cellTempList.toString();
			
			  elements = rowElements.split(",");
	 }

		 input.setFieldtype(elements[1].trim());
		 input.setScreenName(elements[3].trim());
	 input.setFieldname(elements[4].trim());
	 input.setLocation(elements[5].trim());
	 input.setInputvalue(elements[6].trim());
	 input.setEventype(elements[7].trim());
	 input.setDropdownbyindex(elements[8].trim());
	 input.setDropdownbyvalue(elements[9].trim());
	 input.setDropdownbytext(elements[10].trim());
	 input.setScreenshotRequired(elements[11].trim());
	 
	/* //loganathan
	 if(elements[11].contains("]"))
	 {
		 String[] inputvalue = elements[11].split("]");
			
		 input.setScreenshotRequired(inputvalue[11].trim());
	 }
	 else
	 {
	 input.setScreenshotRequired(elements[11].trim());
	 }*/

	 //String[] inputvalue = elements[6].split("]");
		
	 //input.setEventype(inputvalue[0].trim());
	
	 
	
}
public static  InputBean inputdetails()
{
	return (input);
}


}

