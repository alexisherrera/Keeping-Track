import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DynamicListDemo {

	 private String[] defaultValues =  {
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
		    private JList<String> jList = createJList();
		    private JFrame frame;

		    public DynamicListDemo() {
		        
		    }
		    
		    public JFrame getframe() {
		    	frame = new JFrame();
		        frame.add(new JScrollPane(jList));
		        frame.add(createTextField(), BorderLayout.PAGE_END);
		       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.pack();
		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
		        frame.repaint();
		    	return frame;
		    }
		    
		    public JList getList() {
		    	return jList;
		    }

		    private JTextField createTextField() {
		        final JTextField field = new JTextField(15);
		        field.getDocument().addDocumentListener(new DocumentListener(){
		            @Override public void insertUpdate(DocumentEvent e) { filter(); }
		            @Override public void removeUpdate(DocumentEvent e) { filter(); }
		            @Override public void changedUpdate(DocumentEvent e) {}
		            private void filter() {
		                String filter = field.getText();
		                filterModel((DefaultListModel<String>)jList.getModel(), filter);
		            }
		        });
		        return field;
		    }

		    private JList createJList() {
		        JList<String> list = new JList(createDefaultListModel());
		        list.setVisibleRowCount(6);
		        return list;
		    }

		    private ListModel<String> createDefaultListModel() {
		        DefaultListModel<String> model = new DefaultListModel<>();
		        for (String s : defaultValues) {
		            model.addElement(s);
		        }
		        return model;
		    }

		    public void filterModel(DefaultListModel<String> model, String filter) {
		        for (String s : defaultValues) {
		            if (!s.startsWith(filter)) {
		                if (model.contains(s)) {
		                    model.removeElement(s);
		                }
		            } else {
		                if (!model.contains(s)) {
		                    model.addElement(s);
		                }
		            }
		        }
		    }
}
