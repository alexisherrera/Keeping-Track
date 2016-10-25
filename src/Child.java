//For New Challenge, by Alexis Herrera.
//contact at halexis@sas.upenn.edu

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

/* class to abstractly represent a child */
public class Child {

	/*instance variables */
	private boolean threeToOne;
	private String name;
	private String staff;
	private StopWatch stopWM;
	private int state;
	private StopWatch stopW3;
	private StopWatch stopW2;
	private StopWatch stopW1;
	private StopWatch dts;
	public String initialTime;
	private int stateCounter;
	public boolean stateOfChild;
	public String timeOut;
	private int hours;
	private int minute;
	
	
	/* constructor. Decides whether the child is a 3:1 or not */
	public Child (boolean threeToOne, String name, int state, int hours, int minute) {
		this.threeToOne = threeToOne;
		this.hours = hours;
		this.minute = minute;
		this.stateOfChild = true;
		this.name = name;
		this.staff = "";
		this.stopWM = new StopWatch();
		this.stopW3 = new StopWatch();
		this.stopW2 = new StopWatch();
		this.stopW1 = new StopWatch();
		this.dts    = new StopWatch();
		this.stopWM.start();
		this.state = state;
		this.stateCounter = 0;
		this.stopW1.start();
		this.stopW1.suspend();
		this.stopW2.start();
		this.stopW2.suspend();;
		this.stopW3.start();
		this.stopW3.suspend();
		this.timeOut = "";
		
		
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.initialTime = sdf.format(cal.getTime());
        
		this.dts.start();
		
		setState(this.state);
		//if (this.state == 1) { this.stopW1.resume(); }
		//else if (this.state == 2) { this.stopW2.resume(); }
		//else { this.stopW3.resume(); }
		
	}
	
	/* return whether the child is 3:1 or not */
	public boolean isThreeToOne () {
		return this.threeToOne;
	}
	
	/* get the name */
	public String getName () {
		return this.name;
	}
	
	public String getThreT1Time() {
		long x = this.stopW3.getTime();
		String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(x),
			    TimeUnit.MILLISECONDS.toMinutes(x) % TimeUnit.HOURS.toMinutes(1));
		return hms;
	}
	
	public String getTwoT1Time() {
		long x = this.stopW2.getTime();
		String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(x),
			    TimeUnit.MILLISECONDS.toMinutes(x) % TimeUnit.HOURS.toMinutes(1));
		return hms;
	}
	
	public String getOneT1Time() {
		long x = this.stopW1.getTime();
		String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(x),
			    TimeUnit.MILLISECONDS.toMinutes(x) % TimeUnit.HOURS.toMinutes(1));
		return hms;
	}
	
	public String getTotalTime() {
		long x = this.stopWM.getTime();
		String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(x),
			    TimeUnit.MILLISECONDS.toMinutes(x) % TimeUnit.HOURS.toMinutes(1));
		return hms;
	}	
		
	public void assignStaff(String staffName) {
		this.staff = staffName;
	}
	
	public String getStaff() {
		return this.staff;
	}
	
	public int getState() {
		return this.state;
	}
	
	public String getDTSTime() {
		long x = this.dts.getTime();
		String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(x),
			    TimeUnit.MILLISECONDS.toMinutes(x) % TimeUnit.HOURS.toMinutes(1));
		return hms;
	}
	
	
	public void turnOffTime() {
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.timeOut = sdf.format(cal.getTime());
		
		this.stopWM.stop();
		this.stopW3.stop();
		this.stopW2.stop();
		this.stopW1.stop();
		this.dts.stop();
	}
	
	public void setState(int newState) {
		
		
		if (this.dts.getTime() >= this.minute + this.hours) {
			
			if (this.stateCounter == 0) {
				this.dts.stop();
				this.stateCounter = 1;
				
				this.state = newState;
				
				if (this.state == 1) {
					this.stopW1.resume();
				}
				
				else if (this.state == 2) {
					this.stopW2.resume();
				}
				
				else {this.stopW3.resume(); }
				
				return;
			}
			
			
			if (this.state == newState) {
				return;
			}
			
			if (this.state == 1) {
				this.stopW1.suspend();
			}
			
			else if (this.state == 2) {
				this.stopW2.suspend();
			}
			
			else {this.stopW3.suspend();}
			
			this.state = newState;
			
			if (this.state == 1) {
				this.stopW1.resume();
			}
			
			else if (this.state == 2) {
				this.stopW2.resume();
			}
			
			else {this.stopW3.resume(); }
		}
		
		else { this.state = newState; }
	}
	
			
			
		
	
	
	
	public String currentTime() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
        
	}
	
}
