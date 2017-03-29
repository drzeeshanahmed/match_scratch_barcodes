package Match_Barcodes;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JTable;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.*;


public class CLA_Write_To_Excel_CSV {
		// Write Excel Sheet
		public void WriteToExcel(JTable JTable_Param, String str_ColumnName[])
		{
			try
			{
				if (JTable_Param.getRowCount() > 0)
				{
						
						String str_ExcelFile_Path_Name = ""; 
						
						JFileChooser chooser = new JFileChooser();
					    int retrival = chooser.showSaveDialog(null);
					    if (retrival == JFileChooser.APPROVE_OPTION) 
					    {
				        	str_ExcelFile_Path_Name = chooser.getSelectedFile().toString();
					    }
					
					    if(!str_ExcelFile_Path_Name.equals(""))
					    {
					    	if(!str_ExcelFile_Path_Name.contains(".xlsx"))
					    	{
					    		str_ExcelFile_Path_Name = str_ExcelFile_Path_Name + ".xlsx";	//str_ExcelFile_Path_Name = str_ExcelFile_Path_Name + ".xlsx";
					    	}

					    	XSSFWorkbook obj_WorkBook  = new XSSFWorkbook();
							XSSFSheet obj_Sheet = obj_WorkBook.createSheet();
							 
							int rowCount = 0; 
							XSSFRow row = obj_Sheet.createRow(rowCount);

							for(int colCount = 0; colCount < str_ColumnName.length; colCount++)
							{
								String str_JTable_Row_Col = str_ColumnName[colCount];
										
									XSSFCell cell = row.createCell(colCount);
									cell.setCellValue(str_JTable_Row_Col);
							}
							
							for(rowCount = 0; rowCount < JTable_Param.getRowCount(); rowCount++)
							{
								row = obj_Sheet.createRow(rowCount+1);

								for(int colCount = 0; colCount < JTable_Param.getColumnCount() ; colCount++)
								{
									String str_JTable_Row_Col  = JTable_Param.getModel().getValueAt(rowCount, colCount).toString();

									XSSFCell cell = row.createCell(colCount);
									cell.setCellValue(str_JTable_Row_Col);
								}
					        }
							
							FileOutputStream fileOut = new FileOutputStream(str_ExcelFile_Path_Name);
							
							obj_WorkBook.write(fileOut);
							fileOut.flush();
							fileOut.close();
					    }
				}	
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
}
