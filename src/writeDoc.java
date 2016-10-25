import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writeDoc {

	public static void toExcel(JTable table, File file, JList staffL) {
			try{
				TableModel model = table.getModel();
				FileWriter excel = new FileWriter(file);
				ListModel<String> listM = staffL.getModel();
				
				for(int i = 0; i < model.getColumnCount(); i++){
					excel.write(model.getColumnName(i) + "\t");
				}

				excel.write("\n");

				for(int i=0; i< model.getRowCount(); i++) {
					for(int j=0; j < model.getColumnCount(); j++) {
						excel.write(model.getValueAt(i,j).toString()+"\t");
					}
					excel.write("\n");
				}
				
				excel.write("\n");
				excel.write("\n");
				excel.write("Staff: Name initialTime - finalTime");
				excel.write("\n");
				
				//time to write in the staff
				for(int i=0; i < listM.getSize(); i++) {
					excel.write(listM.getElementAt(i) +"\t");
					excel.write("\n");
				}
				
				
				
				

				excel.close();
			}
			catch(IOException e){ System.out.println(e); }
		}
	
	
	public static DefaultListModel<preChild> fromExcel(File file) {
		
		try {
			
		    XSSFWorkbook wb = new XSSFWorkbook(file);
		    XSSFSheet sheet = wb.getSheetAt(0);
		    XSSFRow row;
		    XSSFCell cell;

		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 3; // No of columns
		    
		    String name = null;
		    String state = null;
		    String dts = null;
		    DefaultListModel<preChild> list = new DefaultListModel<preChild>();
		    
		    
		    for(int r = 0; r < rows; r++) {
		        if (r == 1) { continue; }
		        else {
		        	
		        	row = sheet.getRow(r);
			        if(row != null) {
			            for(int c = 0; c < cols; c++) {
			                cell = row.getCell(c);
			                if(cell != null) {
			                    //name
			                	if (c == 0) {
			                		 name = cell.getCellFormula();
			                	}
			                	
			                	//state
			                	else if (c == 1) {
			                		 state = cell.getCellFormula();
			                	}
			                	
			                	//DTS hours
			                	else {
			                		 dts = cell.getCellFormula();
			                	
			                }
			            }
			            
			            if ((name != null && state != null && dts != null)
			            		&& (name.trim().length() != 0 && state.trim().length() != 0 && dts.trim().length() != 0)) {
			            	
			            	//create Child
				            boolean st = false;
				            if (state != null & state.equals("3to1")) {
				            	st = true;
				            }
				            
				            String hours = dts.substring(0, 1);
				           	String minutes = dts.substring(2);
				           	int h = 0;
				           	int m = 0;
				           	
				           	h = Integer.parseInt(hours);
				           	m = Integer.parseInt(minutes);
				           	
				           	h = h * (60000 * 60);
				           	m = m * (60000);
				           	
				           	preChild child = new preChild(name, st, h, m);
				           	list.addElement(child);
			            }
			            
			            name = null;
			            state = null;
			            dts = null;
     
			        }
		        }
		        
		    }
			
		}
		
		    return list;
		}
		
		catch (IOException e) { return null; } 
		
		catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			return null;
		}	
		
	}
}
