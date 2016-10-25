/*this program creates an array in which each slot represents a staff member
 *and the value in each slot, which is from 0 to 3, represents the number
 *of children that they are taking care of given certain conditions
 *
 *Creates maximum profit for new challenge
 * 
 * 
 */
//class to sort the number of staff, one-to-ones, and the rest of the children
//accordingly to maximize profit 
public class stafftochildren {
    
    //private instance variables
    private int staff;
    private int onetones;
    private int restChildren;
    private int[] slotsOfStaff;
    
    //constructor
    public stafftochildren(int staff, int onetones, int restChildren) {
        
        //check for impossible conditions
        if (staff <= 0 || onetones < 0 || restChildren < 0) {
            throw new RuntimeException("negative or 0 for values"); 
        }
        
        //check for more conditions
        if (onetones > staff || restChildren > ((staff - onetones) *3)) {
            throw new RuntimeException("Not enough staff");
        }
        
        //assign constructor inputs to private instance variables
        this.staff = staff;
        this.onetones = onetones;
        this.restChildren = restChildren;
        
        //create an array to hold the number of kids each staff has
        //each slot in the array counts as a staff
        int[] slotsOfStaff = new int[staff];
        
        this.slotsOfStaff = slotsOfStaff;
    }
    
    //method that fills an array with zeros if condition where there are more staff than children
    //is fulfilled
    private int overStaff() {
        
        //int to keep track of run
        int keeptracker = 0;
        int keeptracksum = 0;
        
        for (int i = 0; i < this.slotsOfStaff.length; i++) {
            
            keeptracksum = keeptracksum + this.slotsOfStaff[i];
            
            if (keeptracksum == (this.onetones + restChildren)) {
                
                keeptracker = i;
                return keeptracker; 
            }
        }
        return keeptracker;
    }
    
    //private method for when there is only one staff able to work
    private int[] oneStafff() {
        
        if (this.onetones >= 1) {
            
            slotsOfStaff[0] = 1;
            
            return slotsOfStaff;
        }
        
        else {
            
            slotsOfStaff[0] = this.restChildren;
            
           return slotsOfStaff;
            
            
        }   
    }
    
    //method that fills an array with the kinds of slots that maximize profit at new challenge
    public int[] idealRatios() {
        
        //if there is only one staff there is a particular case
        if (this.staff == 1) {
            
            
            return oneStafff();
        }
        
        //check if we need to account for 1:1 children
        if (this.onetones > 0 && this.staff > 1) {
            
            //for loop that fills the array with the necessary one to ones and
            //decreases the value of the staff accordingly
            for (int i = 0; i < this.onetones; i++) {
                
                this.slotsOfStaff[i] = 1;
                this.staff = this.staff - 1;    
            }
        }
        
        //check if we can make it all 3:1 ratios to maximize the profit
        if ((this.restChildren % 3) == 0) { 
            
            //int arrkeeper = 0;
            
            //for loop to fill up the rest of the array
            for (int z = this.onetones; z < this.slotsOfStaff.length; z++) {
                
                this.slotsOfStaff[z] = 3;
                //arrkeeper = j;
            }
            
            if (overStaff() != (this.slotsOfStaff.length - 1)) {
                
                for (int x = (overStaff() + 1); x < this.slotsOfStaff.length; x++) {
                    
                    this.slotsOfStaff[x] = 0;
                }
            }
            //return the array
            return this.slotsOfStaff;
        }
        
        //if there is not a number of kids to make it all 3:1, then check 2:1
        else if ((this.restChildren % 3) == 2) {
            
            int keepr = 0;
            int keeprinside = this.onetones;
            //for loop to fill up the array up to the last slot
            for (int j = this.onetones; j < slotsOfStaff.length - 1; j++) {
                
                if (keeprinside + 2 == ((this.onetones + this.restChildren))) {
                    
                    keepr = j - 1;
                    
                }
                
                this.slotsOfStaff[j] = 3;
                keeprinside += this.slotsOfStaff[j]; 
                
                
            }
            
            //fill the last slot with a 2 to complete a 2:1 ratio with mostly 3:1 ratios
            slotsOfStaff[keepr + 1] = 2;
            
            if ((keepr + 1) != (this.slotsOfStaff.length - 1)) {
                
                for (int y = (keepr + 2); y < this.slotsOfStaff.length; y++) {
                    
                    slotsOfStaff[y] = 0;
                } 
            }
            
            //return the full array
            return slotsOfStaff;
        }
        
        //if it cannot all be filled with 3:1's and 2:1's, then use 1:1's
        else {
            
            int keepr = 0;
            int keeprinside = this.onetones;
            //for loop to fill up the array up to the last slot
            for (int j = this.onetones; j < slotsOfStaff.length - 1; j++) {
                
                if (keeprinside + 1 == ((this.onetones + this.restChildren))) {
                    
                    keepr = j - 1;
                    
                }
                
                this.slotsOfStaff[j] = 3;
                
                keeprinside += this.slotsOfStaff[j]; 
                
                
            }
            
            slotsOfStaff[keepr + 1] = 1;
            
            if ((keepr + 1) != (this.slotsOfStaff.length - 1)) {
                
                for (int y = (keepr + 2); y < this.slotsOfStaff.length; y++) {
                    
                    slotsOfStaff[y] = 0;
                } 
            }
            
            //fill the last slot with a 1 to complete a 1:1 ratio with mostly 3:1 ratios
            
            
            //return the full array
            return slotsOfStaff;
        }
    }
    
    //main class for TESTING
    public static void main(String[] args) {
        
        int stafff = 4;
        int one2ones = 3;
        int resttchildren = 1;
        
        stafftochildren test = new stafftochildren(stafff, one2ones, resttchildren); 
        
        int [] yay;
        yay = test.idealRatios();
        
        String printer = "| ";
        for (int i = 0; i < yay.length; i++) {
            
            printer += yay[i] + " | ";
        }
        
        System.out.println(printer);
        
        System.out.println(" ");
        
        System.out.println("Total children: " + (resttchildren + one2ones));
        System.out.println("Staff: " + yay.length);
        System.out.println("1:1 children: " + one2ones);
        System.out.println("Rest of children: " + resttchildren);
        
        
    }
}