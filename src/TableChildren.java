import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/* JTable to hold all of the children */ 
public class TableChildren extends AbstractTableModel {

	 protected static final String[] COLUMN_NAMES = {
		        "Name",
		        "1:1?",
		        "State",
		        "Initial Time",
		        "DTS Time",
		        "RSP 3:1 Time",
		        "RSP 2:1 Time",
		        "RSP 1:1 Time",
		        "Total Time", 
		        "Time Out"
		    };

		    private List<Child> rowData;
		    
		    //constructor
		    public TableChildren(LinkedList<Child> children) {
		        this.rowData = children;
		      
		    }

		    public void add(Child baby) {
		        this.rowData.add(baby);
		        fireTableDataChanged();
		    }

		    public void add(List<Child> baby) {
		        this.rowData.addAll(baby);
		        fireTableDataChanged();
		    }

		    @Override
		    public int getRowCount() {
		        return this.rowData.size();
		    }

		    @Override
		    public int getColumnCount() {
		        return COLUMN_NAMES.length;
		    }

		    @Override
		    public String getColumnName(int column) {
		        return COLUMN_NAMES[column];
		    }

		    public Child getPatientDataAt(int row) {
		        return rowData.get(row);
		    }

		    //check on this one
		    @Override
		    public Object getValueAt(int rowIndex, int columnIndex) {
		        Child pd = getPatientDataAt(rowIndex);
		        Object value = null;
		        switch (columnIndex) {
		            
		        	//name
		        	case 0:
		                value = pd.getName();
		                break;
		            
		            //is 1:1?
		        	case 1:
		                boolean x = pd.isThreeToOne();
		                if (x) { value = "No"; }
		                else { value = "Yes"; }
		                break;
		            
		        	//State
		        	case 2:
		                
		            	if (pd.getState() == 1) { value = "1:1"; }
		            	else if (pd.getState() == 2) { value = "2:1"; }
		            	else { value = "3:1"; }
		            	
		                break;
		            
		        	//initial time
		        	case 3:
		            	value = pd.initialTime;
		            	break;
		            
		            //DTS time
		        	case 4:
		        		value = pd.getDTSTime();
		        		break;
		            	
		            	
		            //3:1 time
		            case 5:
		            	value = pd.getThreT1Time();
		            	break;
		            
		            //2:1 Time
		            case 6: 
		            	value = pd.getTwoT1Time();
		            	break;
		            
		            //1:1 Time
		            case 7:
		            	value = pd.getOneT1Time();
		            	break;
		            
		            //Total Time
		            case 8:
		            	value = pd.getTotalTime();
		            	break;
		            	
		            //Time out
		            case 9:
		            	value = pd.timeOut;
		            	break;
		           
		        }
		        return value;
		    }
		    
		    //remove a child from the 2D Array
		    public void removeChild(int row) {
		    	this.rowData.remove(row);
		    	fireTableDataChanged();
		    }		
}
