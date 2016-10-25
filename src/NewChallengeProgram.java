import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

public class NewChallengeProgram extends JFrame {
    
     private DefaultListModel currentstaffMembers;
     private JList currentStaffList;
     private DefaultListModel loggedOutStaff;
     private JList loggedOutStaffList;
     private LinkedList<Child> tabelList;
     private LinkedList<Child> completedKids;
     private TableChildren tableModel;
     private TableChildren completedTableModel;
     private JTable childrenTable;
     private JTable completedChildrenTable;
     public static final int INTERVAL = 20000;
     private LinkedList<Staff> allStaff;
     private DefaultListModel<preChild> preChildren;
     private DefaultListModel<String> preSetChildren;
     private JList <String> preSetChildrenList;
     private DynamicListDemo popUp;
     
     public NewChallengeProgram() {
         
    	 super("New Challenge");

    	 this.popUp = new DynamicListDemo();
         this.currentstaffMembers = new DefaultListModel();
         JScrollPane scrollPane = new JScrollPane();
         currentStaffList = new JList(currentstaffMembers);
         currentStaffList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
         this.tabelList =  new LinkedList<Child>();
         this.completedKids = new LinkedList<Child>();
         this.completedTableModel = new TableChildren(this.completedKids);
         this.tableModel = new TableChildren(this.tabelList);
         
        
         this.loggedOutStaff = new DefaultListModel();
         this.loggedOutStaffList = new JList(this.loggedOutStaff);
         
         
         
         
          String[] defaultValues =  new String[]{
     			"Sean Hall","Michael Schnepf",
        		 "Angelique Hurtado", "Zakaira Daniels", "Ethan Kreutzer",
        		 "Ava Lewis", "Enrique Ahumada", "Nehemiah Greene", "Thea Reaves"
        		 , "Julian Yucupucio", "Marlyst Antone", "Damien Hardin", 
        		 "Jaymi Kresta", "William Gregory", "Taharka Butler", "Trinity Ritz",
        		 "Austin Liefer", "Esai Acuna", "Zackary Samples", "Joshua “JJ” Chambers",
        		 "Corrine “Coco” Harris", "Nina Marie Goodale", "Elijah Goodale",
        		 "Sarah Coulter", "Joaquin Haley", "Brett Rosenberg", "Abram Rascon",
        		 "Daniel Estillore", "Kielene Anderton", "Elena Garcia-Clark",
        		 "Erwin Iraqui", "Tony Rabago", "Devin Linville", "Nicholas Chivers",
        		 "Levi Sanchez", "Devin Eurto", "Marissa Farnsworth", "David Duran-Garcia",
        		 "Jodi Karns", "Michael Thompson", "Nicholas Parra", "Spencer Baldwin",
        		 "Javier Orta", "Angel Moreno", "Jaylin Biggers", "Blake Spurlock",
        		 "Emily Dawley-Hill", "Austin Umbagh-Camerman", "Isaiah Valenzuela",
        		 "Christopher Burns", "Fabian Arteaga", "Andrew Prather", "Anthony Ancheta",
        		 "Elijah Smith", "Collin Steele", "Jasper Guarino", "Ann Marie Barrett",
        		 "Eliyan Huerta", "Hope MaGrady", "Dierdre Galyon", "Anahi Nunez",
        		 "Diego Aguilar", "Alyanna Parker", "Mathew Hart", "Mario Ramirez-Boubeka",
        		 "Angelito Peralta-Marquez", "Angel Murrieta", "Juan Iturralde",
        		 "Leilani Estrada", "Juan “Johnny” Estrada", "Nico Leon", 
        		 "Noah Curry", "Malachi Hall", "Ryan Smith", "Elijah Marrs-Watts",
        		 "Robert Sanceau", "Ryan Sanceau", "Leopold Neill", "Michael Mostella",
        		 "Shawn Trujillo", "Isaiah Smith", "Xzavier Hunter", "Scott Swartz",
        		 "John Swartz", "Robert Swartz", "Jason Martinez", "Jonathan Chico", 
        		 "Robert Chico", "Alexandra Esquer-Valdez", "Isaac Gonzales",
        		 "Thomas “TJ” Warner", "Spencer Wilson", "Yaralet Castanon",
        		 "Robyn McKee", "Richard Harris", "Bradley Baker-Taylor"
     	    };
         
         
         this.preSetChildren = new DefaultListModel();
         
         //add children to default list
         for (int i = 0; i < 96; i ++) {
        	 this.preSetChildren.addElement(defaultValues[i]);
         }
         
         //JList for preset children
         this.preSetChildrenList = popUp.getList();
         
         
         this.preSetChildrenList.addMouseListener(new MouseAdapter() {
     	    public void mousePressed(MouseEvent me) {
    	        
    	    	
    	    	int cellIndex = preSetChildrenList.getSelectedIndex();
    	    	
    	        if (cellIndex != -1 && me.getClickCount() == 2) {
    	            
    	        	int choice = JOptionPane.showOptionDialog(null, "Are you sure you"
    	        			+ " want to enter this child in the system?", 
               			 "Choose an option", JOptionPane.YES_NO_OPTION, 
               			 JOptionPane.INFORMATION_MESSAGE, 
               			  null, null, "Metric");
    	        	
    	        	//if yes
    	        	if (choice == 0) {
    	        		
    	        		String name = preSetChildrenList.getSelectedValue();
    	        		
    	        		
    	        		addChildWithName(name);
    	        		
    	        	}
    	        	
    	        	else {
    	        		return;
    	        		
    	        	}	
    	        }
    	        
    	        
    	    }

			private void addChildWithName(String name) {
				
				
			 //for when you can choose to put either a 3:1 OR a 1:1
           	 if (currentstaffMembers.size() != 0 && 
           			 (currentstaffMembers.size() >= ((((tabelList.size() + 1) - oneTOOnes()) / 3.0) 
           			 						+ oneTOOnes())) && 
           			 (currentstaffMembers.size() >= ((((tabelList.size() + 1) - 
           					 (oneTOOnes() + 1)) / 3.0) 
                   			 						+ (oneTOOnes() + 1)))) {
           		            	
           	 int choice = JOptionPane.showOptionDialog(null, "1:1?", 
           			 "Choose an option", JOptionPane.YES_NO_OPTION, 
           			 JOptionPane.INFORMATION_MESSAGE, 
           			  null, null, "Metric");
           	 
           	 boolean threeToOne;
           	 int state;
           	 
           	 if (choice == 0) {
           		 threeToOne = false; 
           		 state = 1;
            	 	
           		 }
           	 else { 
           		threeToOne = true;
           		int stafff = currentstaffMembers.size();
           	 	stafff = stafff - oneTOOnes();
           	 	int children = tabelList.size() - oneTOOnes();
           	 	state = updateChildrenState(stafff, children + 1);
           	 }
           	 
           	 
           	 //ENTER DTS information
           	 String DTSHours = JOptionPane.showInputDialog(
   	                 NewChallengeProgram.this, "Enter DTS hours" + "\n"+
           	 "Format: H:MM");
           	 
           	 if (DTSHours == null || DTSHours.length() != 4) {
           		 JOptionPane.showMessageDialog(null, "Not an acceptable "
           		 		+ "value");
           		 return;
           	 }
           	 
           	 String hours = DTSHours.substring(0, 1);
           	 String minutes = DTSHours.substring(2);
           	 int h =0;
           	 int m = 0;
           	 
           	try {
           	   h = Integer.parseInt(hours);
           	   
           	}
           	 catch (NumberFormatException e) {
           		 JOptionPane.showMessageDialog(null, "Not an acceptable "
            		 		+ "value");
           		 return;
           	}
           	
           	
           	try {
           		m = Integer.parseInt(minutes);
            	   
            	}
            	 catch (NumberFormatException e) {
            		 JOptionPane.showMessageDialog(null, "Not an acceptable "
             		 		+ "value");
            		 return;
            	}
           	 
           	 h = h * (60000 * 60);
           	 m = m * (60000);
	                 
           	 
           	 
           	 
	                 
	                 if (name != null && !name.equals("")) {
	                 Child bby = new Child(threeToOne, name, state, h, m); 
	                 updateChildrenStateAddOthers(state);
	                 tableModel.add(bby);
	                 
	                 }
           	 }
           	 
           	 //when you can ONLY put a 3:1 
           	 else if (currentstaffMembers.size() != 0 && 
           			 (currentstaffMembers.size() >= ((((tabelList.size() + 1) - 
           					 (oneTOOnes())) / 3.0) 
                   			 						+ (oneTOOnes())))) {

           	 // prompt user for new philosopher's name
               
                
               int stafff = currentstaffMembers.size();
        	 	stafff = stafff - oneTOOnes();
        	 	int children = tabelList.size() - oneTOOnes();
        	 	int state = updateChildrenState(stafff, children + 1);
        	 	
        	 	
        	 //ENTER DTS information
          	 String DTSHours = JOptionPane.showInputDialog(
  	                 NewChallengeProgram.this, "Enter DTS hours" + "\n"+
          	 "Format: H:MM");
          	 
          	 if (DTSHours == null || DTSHours.length() != 4) {
          		 JOptionPane.showMessageDialog(null, "Not an acceptable "
          		 		+ "value");
          		 return;
          	 }
          	 
          	 String hours = DTSHours.substring(0, 1);
          	 String minutes = DTSHours.substring(2);
          	 int h =0;
          	 int m = 0;
          	 
          	try {
          	   h = Integer.parseInt(hours);
          	   
          	}
          	 catch (NumberFormatException e) {
          		 JOptionPane.showMessageDialog(null, "Not an acceptable "
           		 		+ "value");
          	}
          	
          	
          	try {
          		m = Integer.parseInt(minutes);
           	   
           	}
           	 catch (NumberFormatException e) {
           		 JOptionPane.showMessageDialog(null, "Not an acceptable "
            		 		+ "value");
           		 
           		 return;
           	}
          	 
          	 h = h * (60000 * 60);
          	 m = m * (60000);
	                 
        	 	
        	 	
        	 	
        	 	
                if (name != null && !name.equals("")) {
                Child bby = new Child(true, name, state,h, m);
                updateChildrenStateAddOthers(state);
                tableModel.add(bby);
                	}
           	 }
           	 
           	 //add more staff
           	 else {
           		
                		JOptionPane.showMessageDialog
                		(null,"There is not enough staff! "
                				 + "\n" + "Add another staff member.");
                	
           	 
            }
				
			}
    	});
         
         
        
         
         //create JButton
         JButton addPreChildsButton = addpreChildsButton();
         
         
         //create JButtons
         JButton addButton = addButton(); //add children
         JButton writtenDoc = writeButton(); //log
         //JButton removeButton = removeButton(); //remove children
         JButton removeStaff = removeStaff(); //remove staff
         JButton addStaffButton = addStaff(); //add staff
         JButton loadData       = loadData();
         JButton preData = preData();
         JButton count  = countData();
         
         //set up the JTable
         this.childrenTable = new JTable(this.tableModel);
         childrenTable.setShowGrid(false);
         childrenTable.setShowHorizontalLines(false);
         childrenTable.setShowVerticalLines(false);
         childrenTable.setRowMargin(0);
         childrenTable.setIntercellSpacing(new Dimension(0, 0));
         childrenTable.setFillsViewportHeight(true);
         
         
         
         TableRowSorter<TableChildren> sorter = new TableRowSorter<>
         (this.tableModel);
         childrenTable.setRowSorter(sorter); 
         
         //set up completed JTable
         this.completedChildrenTable = new JTable(this.completedTableModel);
         this.completedChildrenTable.setShowGrid(false);
         this.completedChildrenTable.setShowHorizontalLines(false);
         this.completedChildrenTable.setShowVerticalLines(false);
         this.completedChildrenTable.setRowMargin(0);
         this.completedChildrenTable.setIntercellSpacing(new Dimension(0, 0));
         this.completedChildrenTable.setFillsViewportHeight(true);
         TableRowSorter<TableChildren> sorter2 = new TableRowSorter<>
         (this.completedTableModel);
         this.completedChildrenTable.setRowSorter(sorter2);
         
         
         
         //log in children
         childrenTable.addMouseListener(new MouseAdapter() {
        	    public void mousePressed(MouseEvent me) {
        	        

        	    	JTable table =(JTable) me.getSource();
        	    	if (table == null || table.getRowCount() == 0 || 
        	    			table.rowAtPoint(me.getPoint()) == -1) {
        	    		return;
        	    	}
        	        Point p = me.getPoint();
        	        
        	        int row = table.rowAtPoint(p);
        	        int realRow = childrenTable.convertRowIndexToModel(row);

        	        Child temp = tableModel.getPatientDataAt(realRow);
        	        if (row != -1 && me.getClickCount() == 2) {
        	            
        	        	int choice = JOptionPane.showOptionDialog(null, "Are you sure you"
        	        			+ " want to log this child off?", 
                   			 "Choose an option", JOptionPane.YES_NO_OPTION, 
                   			 JOptionPane.INFORMATION_MESSAGE, 
                   			  null, null, "Metric");
        	        	
        	        	//if yes
        	        	if (choice == 0) {
        	        		
        	        		
        	        		tableModel.removeChild(realRow);
        	        		temp.turnOffTime();

        	        		if (temp.isThreeToOne()) {
        	        			
        	        			int modelState = updateChildrenState(0, 
    	                 				(tabelList.size() - oneTOOnes()));
    	                 		
    	                 		updateChildrenStateSubOthers(modelState);
        	        		}
        	        		
        	        		completedTableModel.add(temp);
        	        		completedTableModel.fireTableDataChanged();
        	        		
        	        	}
        	        	
        	        	else {
        	        		
        	        	}	
        	        }
        	        
        	        
        	    }
        	});
         
         
         currentStaffList.addMouseListener(new MouseAdapter() {
     	    public void mousePressed(MouseEvent me) {
     	    	JList theList = (JList) me.getSource();
     	        if (me.getClickCount() == 2) {
     	        	int index = theList.locationToIndex(me.getPoint());
     	        	if (index >= 0) {
     	        		
     	        		int choice = JOptionPane.showOptionDialog(null, "Are you sure you"
         	        			+ " want to log this Staff off?", 
                    			 "Choose an option", JOptionPane.YES_NO_OPTION, 
                    			 JOptionPane.INFORMATION_MESSAGE, 
                    			  null, null, "Metric");
         	        	
         	        	//if yes
         	        	if (choice == 0) {
         	        		DefaultListModel<String> x  = (DefaultListModel) currentStaffList.getModel();
         	        		String staffTemp = x.getElementAt(index);
         	        		x.remove(index);
         	        		DefaultListModel<String> y  = (DefaultListModel) loggedOutStaffList.getModel();
         	        		
         	        	   Calendar cal = Calendar.getInstance();
         	               SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
         	               String finTIME = sdf.format(cal.getTime());
         	        		
         	        		
         	        		staffTemp = staffTemp + " - " + finTIME;
         	        		y.addElement(staffTemp);
         	        		
         	        	}
         	        	
         	        	else {
         	        		
         	        	}	
     	        	}
     	        	
     	        }
     	    }
     	});
         

         //lay out GUI components
         JPanel inputPanel = new JPanel();
       
         
         inputPanel.add(addButton);
         inputPanel.add(addStaffButton);
        // inputPanel.add(removeButton);
         inputPanel.add(removeStaff );
         //inputPanel.add(loadData);
         //inputPanel.add(preData);
         inputPanel.add(count);
         inputPanel.add(writtenDoc);
         inputPanel.add(addPreChildsButton);
         
                
         Container container = getContentPane();
         container.add(inputPanel, BorderLayout.NORTH);
         JSplitPane splitPaneV = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
         JSplitPane splitPaneTable = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
         splitPaneTable.setLeftComponent(new JScrollPane(childrenTable));
         splitPaneTable.setRightComponent(new JScrollPane
        		 (this.completedChildrenTable));
         JSplitPane splitPanStaff = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
         splitPanStaff.setLeftComponent(this.currentStaffList);
         splitPanStaff.setRightComponent(this.loggedOutStaffList);
         splitPanStaff.getRightComponent().setBackground(new Color(232, 232, 232));
         splitPanStaff.getLeftComponent().setBackground(new Color(247, 247, 247));
         splitPanStaff.setResizeWeight(.54);
         splitPaneV.setLeftComponent(splitPaneTable);
         splitPaneV.setRightComponent(splitPanStaff);
         splitPaneV.setResizeWeight(0.8);
         splitPaneTable.setResizeWeight(0.6);
         container.add(splitPaneV, BorderLayout.CENTER);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setSize(1130, 500);
         setVisible(true);
         
         //initialize timer to update the JTable. Every 30 seconds
         Timer timer = new Timer(INTERVAL, new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				tick();
 			}
 		});
         
         timer.start();
         
         this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent we)
             { 
                 String ObjButtons[] = {"Yes","No"};
                 int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?" + "\n" + 
                 "All information will be lost if" + "\n" + "you do!","Online Examination System",
                 JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,
                 null,ObjButtons,ObjButtons[1]);
                 if(PromptResult==JOptionPane.YES_OPTION)
                 {
                     System.exit(0);
                 }
             }
         });
     } 
     
     private JButton addpreChildsButton() {
		
    	 JButton addpreChildsButtonOG =
                 new JButton( "Pre-Loaded Children" );
		
    	 
    	 addpreChildsButtonOG.addActionListener(
                 new ActionListener() {
                
                	 public void actionPerformed( ActionEvent event ) {
                		 
                		 popUp.getframe().show();
                		 Object name = popUp.getList().getSelectedValue();
                		 
                 }
              }
             );
    	 
    	 
    	 return addpreChildsButtonOG; 
	}

	private JButton countData() {
    	 
    	 JButton countData =
                 new JButton( "Count Data" );
		
    	 
    	 countData.addActionListener(
                 new ActionListener() {
                
                	 public void actionPerformed( ActionEvent event ) {
                	 
                		 String x = "Currently Logged in Staff: " + currentstaffMembers.size()
                		 + "\n" + "Currently Logged in Children: " + tabelList.size();
 
                	JOptionPane.showMessageDialog(null, x);
                 }
              }
             );
    	 
    	 
    	 return countData;
	}

	private JButton preData() {
		
    	 JButton preData =
                 new JButton( "Pre-loaded Data" );
		
    	 
    	 preData.addActionListener(
                 new ActionListener() {
                
                	 public void actionPerformed( ActionEvent event ) {
                	 
                		 if (preChildren == null ) {
                			 JOptionPane.showMessageDialog(null, "No pre-loaded data found");
                		 }
                		 
                		 else {
                			 JFrame frame = new JFrame ("MyPanel");
                	         frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                			 JList list = new JList(preChildren);
                			 frame.add(list);

                		 }
 
                	
                 }
              }
             );
    	 
    	 
    	 return preData;
	}

	private JButton loadData() {
    	 JButton loadData =
                 new JButton( "Load Data" );
		
    	 
    	 loadData.addActionListener(
                 new ActionListener() {
                
                	 public void actionPerformed( ActionEvent event ) {
                	
 
                	JFileChooser fc = new JFileChooser();
                    int option = fc.showOpenDialog(null);
                    
                    if (option == JFileChooser.APPROVE_OPTION){
                    	File file = fc.getSelectedFile();
                    	
                    	preChildren = writeDoc.fromExcel(file);
                    	
                    	}
                 }
              }
             );
    	 
    	 
    	 return loadData;
	}

	/* method to find the number of one to ones a list has */
     private int oneTOOnes() {
    	 
    	int count = 0; 
    	for (Child i : this.tabelList) {
    		if (!i.isThreeToOne()) {
    			count = count + 1;
    		}
    	}
    	
    	return count;
     }
       
     /* method to create a button to add a Child to JTable */
     private JButton addButton() {
    	 
    	 JButton addButton = new JButton("Add Child");
    	
         addButton.addActionListener(
             new ActionListener() {
            
             public void actionPerformed( ActionEvent event )
                 {
            	
            	 //for when you can choose to put either a 3:1 OR a 1:1
            	 if (currentstaffMembers.size() != 0 && 
            			 (currentstaffMembers.size() >= ((((tabelList.size() + 1) - oneTOOnes()) / 3.0) 
            			 						+ oneTOOnes())) && 
            			 (currentstaffMembers.size() >= ((((tabelList.size() + 1) - 
            					 (oneTOOnes() + 1)) / 3.0) 
                    			 						+ (oneTOOnes() + 1)))) {
            		            	
            	 int choice = JOptionPane.showOptionDialog(null, "1:1?", 
            			 "Choose an option", JOptionPane.YES_NO_OPTION, 
            			 JOptionPane.INFORMATION_MESSAGE, 
            			  null, null, "Metric");
            	 
            	 boolean threeToOne;
            	 int state;
            	 
            	 if (choice == 0) {
            		 threeToOne = false; 
            		 state = 1;
             	 	
            		 }
            	 else { 
            		threeToOne = true;
            		int stafff = currentstaffMembers.size();
            	 	stafff = stafff - oneTOOnes();
            	 	int children = tabelList.size() - oneTOOnes();
            	 	state = updateChildrenState(stafff, children + 1);
            	 }
            	 
            	 
            	 //ENTER DTS information
            	 String DTSHours = JOptionPane.showInputDialog(
    	                 NewChallengeProgram.this, "Enter DTS hours" + "\n"+
            	 "Format: H:MM");
            	 
            	 if (DTSHours == null || DTSHours.length() != 4) {
            		 JOptionPane.showMessageDialog(null, "Not an acceptable "
            		 		+ "value");
            		 return;
            	 }
            	 
            	 String hours = DTSHours.substring(0, 1);
            	 String minutes = DTSHours.substring(2);
            	 int h =0;
            	 int m = 0;
            	 
            	try {
            	   h = Integer.parseInt(hours);
            	   
            	}
            	 catch (NumberFormatException e) {
            		 JOptionPane.showMessageDialog(null, "Not an acceptable "
             		 		+ "value");
            		 return;
            	}
            	
            	
            	try {
            		m = Integer.parseInt(minutes);
             	   
             	}
             	 catch (NumberFormatException e) {
             		 JOptionPane.showMessageDialog(null, "Not an acceptable "
              		 		+ "value");
             		 return;
             	}
            	 
            	 h = h * (60000 * 60);
            	 m = m * (60000);
	                 
            	 
            	 
            	 String name = JOptionPane.showInputDialog(
	                 NewChallengeProgram.this, "Enter Name" );
	                 
	                 if (name != null && !name.equals("")) {
	                 Child bby = new Child(threeToOne, name, state, h, m); 
	                 updateChildrenStateAddOthers(state);
	                 tableModel.add(bby);
	                 
	                 }
            	 }
            	 
            	 //when you can ONLY put a 3:1 
            	 else if (currentstaffMembers.size() != 0 && 
            			 (currentstaffMembers.size() >= ((((tabelList.size() + 1) - 
            					 (oneTOOnes())) / 3.0) 
                    			 						+ (oneTOOnes())))) {

            	 // prompt user for new philosopher's name
                 String name = JOptionPane.showInputDialog(
                 NewChallengeProgram.this, "Enter Name for 3:1 or insert" + "\n"
                 +  "new Staff for another 1:1" );
                 
                int stafff = currentstaffMembers.size();
         	 	stafff = stafff - oneTOOnes();
         	 	int children = tabelList.size() - oneTOOnes();
         	 	int state = updateChildrenState(stafff, children + 1);
         	 	
         	 	
         	 //ENTER DTS information
           	 String DTSHours = JOptionPane.showInputDialog(
   	                 NewChallengeProgram.this, "Enter DTS hours" + "\n"+
           	 "Format: H:MM");
           	 
           	 if (DTSHours == null || DTSHours.length() != 4) {
           		 JOptionPane.showMessageDialog(null, "Not an acceptable "
           		 		+ "value");
           		 return;
           	 }
           	 
           	 String hours = DTSHours.substring(0, 1);
           	 String minutes = DTSHours.substring(2);
           	 int h =0;
           	 int m = 0;
           	 
           	try {
           	   h = Integer.parseInt(hours);
           	   
           	}
           	 catch (NumberFormatException e) {
           		 JOptionPane.showMessageDialog(null, "Not an acceptable "
            		 		+ "value");
           	}
           	
           	
           	try {
           		m = Integer.parseInt(minutes);
            	   
            	}
            	 catch (NumberFormatException e) {
            		 JOptionPane.showMessageDialog(null, "Not an acceptable "
             		 		+ "value");
            		 
            		 return;
            	}
           	 
           	 h = h * (60000 * 60);
           	 m = m * (60000);
	                 
         	 	
         	 	
         	 	
         	 	
                 if (name != null && !name.equals("")) {
                 Child bby = new Child(true, name, state,h, m);
                 updateChildrenStateAddOthers(state);
                 tableModel.add(bby);
                 	}
            	 }
            	 
            	 //add more staff
            	 else {
            		
                 		JOptionPane.showMessageDialog
                 		(null,"There is not enough staff! "
                 				 + "\n" + "Add another staff member.");
                 	
            	 }
             }
         }
         );
    	 
         return addButton;
     }
    
     /* method to create a button for the log */     
     private JButton writeButton() {
    	 JButton writtenDoc =
                 new JButton( "Log" );
                
                 writtenDoc.addActionListener(
                     new ActionListener() {
                    
                    	 public void actionPerformed( ActionEvent event ) {
                    	
                    	if (loggedOutStaff.size() > 0) {
                    		
                    	
                    	JFileChooser fc = new JFileChooser();
                        int option = fc.showSaveDialog(null);
                        
                        if (option == JFileChooser.APPROVE_OPTION){
                        	String filename = fc.getSelectedFile().getName(); 
                        	String path = fc.getSelectedFile().getParentFile().getPath();
                        	writeDoc.toExcel(completedChildrenTable, 
                    			 new File(path + "/" + filename + ".xls"), loggedOutStaffList);
                        	
                        	}
                        }
                    	
                    	else {
                    		JOptionPane.showMessageDialog
                    		(null,"The bottom children table "
                    				+ "is empty!" + "\n" + "Add children or staff to it to export as Excel "
                    				+ "\n" + "file.");
                    	}
                     }
                  }
                 );
                 return writtenDoc;
     }
     
     /* method to create a button to delete a child */     
     private JButton removeButton() {
    	 
    	 JButton removeButton =
    	         new JButton( "Remove Child" );
    	        
    	         removeButton.addActionListener(
    	             new ActionListener() {
    	            
    	             public void actionPerformed( ActionEvent event )
    	                 {
    	            	 
    	            	 	if (childrenTable.getSelectedRow() == -1) {
    	            	 		JOptionPane.showMessageDialog
    	                 		(null,"No child selected to remove! "
    	                 				 + "\n" + "Pick a child.");
    	            	 		return;
    	            	 	}
    	            	 	
    	            	 	int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to remove this child?" +
    	            	 	"\n" + "Double click on the child if you mean to log" + "\n" + "them out!", 
    	               			 "Choose an option", JOptionPane.YES_NO_OPTION, 
    	               			 JOptionPane.INFORMATION_MESSAGE, 
    	               			  null, null, "Metric");
    	            	 	
    	            	 	//YES, remove the child
    	            	 	if (choice == 0) {
    	            	 		
    	            	 		
    	   
    	            	 		//if the child selected is exclusively a 1:1
        	                 	if (childrenTable.getSelectedRow() != -1 && 
        	            			 !tableModel.getPatientDataAt
        	            			 (childrenTable.getSelectedRow()).isThreeToOne()) {
        	                 		
        	                 		// remove selected row from the model
        	                 		
        	                 		tableModel.removeChild(childrenTable.getSelectedRow());
        	                 	}
        	            	 	
        	                 	//if the child selected is a 3:1
        	                 	else if (childrenTable.getSelectedRow() != -1 && 
        	                 		 tableModel.getPatientDataAt
           	            			 (childrenTable.getSelectedRow()).isThreeToOne()) {
        	                 		
        	                 		//remove selected row from the model
        	                 		tableModel.removeChild(childrenTable.getSelectedRow());
        	                 		
        	                 	    //remove the row
        	                 		int modelState = updateChildrenState(0, 
        	                 				(tabelList.size() - oneTOOnes()));
        	                 		
        	                 		updateChildrenStateSubOthers(modelState);
        	                 		
        	                 	}
    	            	 	}
    	            	 	  	            	 	
    	             }
    	         }
    	         );
    	        return removeButton;
     }
     
     /* method to create a button to delete a staff */     
     private JButton removeStaff() {
    	 JButton removeStaff =
    	         new JButton( "Remove Staff" );
    	        
    	         removeStaff.addActionListener(
    	             new ActionListener() {
    	            
    	             public void actionPerformed( ActionEvent event )
    	                 {
    	                
    	            	 //System.out.println(list.getSelectedValue());
    	            	 if (currentstaffMembers.size() - 1 >= ((((tabelList.size()) - 
    	            					 (oneTOOnes())) / 3.0) + (oneTOOnes()))) {
    	            	 

    	            	 
    	            		 int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to delete this Staff?" +
    	     	            	 	"\n" + "This completely removes them from the system!" + "\n"
    	            				 + "Double click on them instead to log them out!", 
       	               			 "Choose an option", JOptionPane.YES_NO_OPTION, 
       	               			 JOptionPane.INFORMATION_MESSAGE, 
       	               			  null, null, "Metric");
    	            		 
    	            		 if (choice == 0) {
    	            			 currentstaffMembers.removeElement(currentStaffList.getSelectedValue());
    	            		 }
    	            		 
    	            		 
    	            		 
    	            		 
    	            	 
    	            	 
    	            	 //CHANGE THIS TO FIND THE NAME OF THE OBJECT
    	            	 //staffList.remove(list.getSelectedValue());
    	            	 }
    	            	 
    	            	 else {
    	            		 JOptionPane.showMessageDialog
 	                 		(null,"You cannot remove staff. If you were to" + 
    	            		 "\n" + "remove a staff, there would be not" + "\n" + "enough "
    	            		 		+ "staff for the amount of children.");
    	            	 }
    	             }
    	         }
    	         );
    	         
    	         return removeStaff;
     }
     
     /* method to create a button to add a new staff member */     
     private JButton addStaff() {
    	 JButton addStaffButton =
                 new JButton( "Add Staff" );
                
                 addStaffButton.addActionListener(
                     new ActionListener() {
                    
                    	 public void actionPerformed( ActionEvent event )
                         {
                         // prompt user for new staffs name
                         String name = JOptionPane.showInputDialog(
                         NewChallengeProgram.this, "Enter Name" );
                        
                         // add new staff
                         if (name != null && !name.equals("")) {
                         Staff bby = new Staff(name);
                         currentstaffMembers.addElement(bby.getName() + " " +
                        		 bby.initialTime);
                         //allStaff.add(bby);
                         
                         }
                     }  
                        
                 }
                 );
                 
                 return addStaffButton;
     }
     
     /* updates JTable every 30 seconds */
     private void tick() {
    	this.tableModel.fireTableDataChanged(); 
     }
     
     /* method to set the state of each child in the program */
     private int updateChildrenState(int thr1S, int thr1Ch) {
    	 
    	 int x = (thr1Ch) % 3;
    	 return x;
     }
      
     private void updateChildrenStateAddOthers(int newStates) {
    	 
    	 if (newStates == 2) {
    		 ListIterator<Child >listIterator = this.tabelList.listIterator();
    		 int count = 0;
    		 while (listIterator.hasNext()) {
    			 Child temp = listIterator.next();
    			 if (temp.isThreeToOne() && temp.getState() == 1 && count <= 1) {
    				 temp.setState(2);
    				 count = count + 1;
    			 }
    		 }
    	 }
    	 
    	 if (newStates == 0) {
    		 
    		 ListIterator<Child >listIterator = this.tabelList.listIterator();
    		 
    		 int count = 0;
    		 while (listIterator.hasNext()) {
    			 
    			 Child temp = listIterator.next();
    			
    			 if (temp.isThreeToOne() && temp.getState() == 2 && count <= 2) {
    				 
    				 temp.setState(3);
    				 count = count + 1;
    			 }
    		 }
    	 }
    	 tableModel.fireTableDataChanged();
     }

     private void updateChildrenStateSubOthers(int newStates) {
	 
    	 if (newStates == 2) {
    		 
    		 ListIterator<Child >listIterator = this.tabelList.listIterator();
    		 int count = 0 ;
    		 
    		 while (listIterator.hasNext()) {
    			 Child temp = listIterator.next();
    			 
    			 if (!temp.isThreeToOne()) { }
    			 else if (count <= 1) { 
    				 temp.setState(2);
    				 count = count + 1;
    			 }
    			 else { temp.setState(3); }
    		 } 
    	 }
    		 
    	if (newStates == 1) {
        		 
        	ListIterator<Child >listIterator = this.tabelList.listIterator();
        	int count = 0 ;
   		 
   		 	while (listIterator.hasNext()) {
   		 		Child temp = listIterator.next();
   			 
   			 	if (!temp.isThreeToOne()) { }
   			 	else if (count == 0) { 
   			 		temp.setState(1);
   			 		count = count + 1;
   			 	}
   			 	else { temp.setState(3); }
   		 	} 
   	 	}
        		
    	if (newStates == 0) {
    		
    		ListIterator<Child >listIterator = this.tabelList.listIterator();
    		
    		while (listIterator.hasNext()) {
    			Child temp = listIterator.next();
    			
    			if (temp.isThreeToOne()) {
    				temp.setState(3);
    			}
    		}	
    	}
    }
    	      
     /* main function for the program */
     public static void main(String args[])
         {
         new NewChallengeProgram();
 
     }
}