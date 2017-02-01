
package WriteResult;
import java.io.File;

import Commoninterface.Driveraction;
import jxl.Workbook;

import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
public class WriteExcelSheet  implements Driveraction  {

	

	@SuppressWarnings("all")
	static


		WritableWorkbook ww;
		public static int a;
		public static int b;
		private static boolean abc;
		public static String execption;
		
		public  void writeMeth(int rowNum, boolean result,String expection) throws Exception {
			
			

			if (result) {
				// WriteFile(8, rowNum, true);
				WriteFile(12, rowNum, true,expection);

				write();

			} else {
				WriteFile(12, rowNum, false,expection);
				write();
			}
			
		}

		/**
		 * 
		 * @param a
		 * @param b
		 * @param abc
		 * @return
		 */
		@SuppressWarnings("static-access")
		public  void WriteFile(int a, int b, boolean abc,String expection) {

			this.a = a;
			this.b = b;
			this.abc = abc;
			this.execption = expection;

		}

		/**
		 * writes the file into the excel sheet
		 * 
		 * @throws Exception
		 */
		
		public static void write() throws Exception {

			try {
				
				Workbook workbook = Workbook.getWorkbook(new File(
						fileName));
				ww = Workbook
						.createWorkbook(new File(fileName), workbook);
				WritableSheet sheet1 = ww.getSheet(0);
				WritableFont cellFont = new WritableFont(WritableFont.TIMES, 11);
				// cellFont.setColour(Colour.BLUE);
				WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
				cellFormat
						.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.THIN);
				cellFormat.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
				cellFormat.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
				cellFormat.setBorder(jxl.format.Border.TOP, BorderLineStyle.THIN);
				
				if (abc) {
					cellFont.setColour(jxl.format.Colour.GREEN);
					Label label = new Label(a, b, "OK", cellFormat);
					sheet1.addCell(label);

				} else {
					cellFont.setColour((jxl.format.Colour.RED));
					if(execption==null)
					{
					Label label = new Label(a, b, "NG", cellFormat);
					sheet1.addCell(label);
					}
					else
					{
						Label label = new Label(a, b,execption, cellFormat);
						sheet1.addCell(label);
					}

				}
				
				ww.write();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ww.close();
			}
		}
	}


