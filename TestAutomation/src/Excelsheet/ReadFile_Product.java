
/*
 * System Name     :POS
 * Sub system Name :Read Excel Sheet Rows
 * 
 *
 * �ｽ蠖抛vision History�ｽ�ｽ
 * Date       Name(Company Name)             Description
 * ---------- ------------------------------ ---------------------
 * 2014/6/30 Senthil_vel(Infoview)            New Creation
 *
 */
package Excelsheet;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;






import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;





import Commoninterface.Driveraction;

public class ReadFile_Product implements Driveraction {
	
	
	
	@SuppressWarnings("rawtypes")
	static Map map =new HashMap();

	@SuppressWarnings("rawtypes")
	static Map mp =new HashMap();	
	
	
	

	public static void readFile(int row) {

		//String fileName ="../Exampleread/Resource/Script.xls";
		 readExcelFile(fileName,row);
		
	}

	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void readExcelFile(String fileName,int row) {
		List cellDataList = new ArrayList();
		int i=1;
		 
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheetAt(row);
			
			Iterator rowIterator = hssfSheet.rowIterator();
			
			while (rowIterator.hasNext()) {
				HSSFRow hssfRow =  (HSSFRow) rowIterator.next();
			
				if(i>=2)
				{
				mp.put(i,hssfRow);
				System.out.println(mp.size());
				}
				
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out
					.println("The File You are trying to use is not available::try with correct File path");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@SuppressWarnings("rawtypes")
	public static Map RowId()
	{
		map=mp;
		return map;
	}
}
	
	
	
	
	
	
	
	