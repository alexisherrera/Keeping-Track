import java.text.SimpleDateFormat;
import java.util.Calendar;

//For New Challenge, by Alexis Herrera.
//contact at halexis@sas.upenn.edu


/* Abstract class to represent the staff of New Challenge */
public class Staff {

	/* private instance variables for this class */
	private String staffName;
	public String initialTime;
	private boolean state;
	public String finalTime;
	
	/* constructor which essentially just initializes array and name of Staff */
	public Staff (String name) {
		this.staffName = name;
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.initialTime = sdf.format(cal.getTime());
        state = true;
        finalTime = "";
	}
	
	
	/* get the name of the staff  */
	public String getName() {
		return this.staffName;
	}
	
	public String getITime() {
		return initialTime;
	}

	public void end() {
		state = false;
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.finalTime = sdf.format(cal.getTime());
	}
}
