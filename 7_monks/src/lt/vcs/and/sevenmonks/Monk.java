package lt.vcs.and.sevenmonks;

//import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Monk extends Thread {
	
	private Thread threadIns;
	private final int thredNumber;
	private final int maxMonkNum;
	protected boolean isEating;
	protected final Dish dishPlate;
	
	private int sleepInterval = 10;	// dummy initial value
	private int sl = 80;
	private final Random randomGenerator = new Random();
	protected final long strtTime;			// just know application start time
	private int eatTime = 30;
    private int restTime = 20;
	private int i;

	private final List<GrFork> listOfForks;
	
		
	public Monk(int monkNumberIn, int maxMonkNumIn, List<GrFork> listOfForksIn) {
		      this.setName("Monk-" + monkNumberIn);
		      this.thredNumber = monkNumberIn;
		      this.maxMonkNum = maxMonkNumIn;
		      this.isEating = false;
		      this.dishPlate = new Dish(6);
		      this.i  = 0;					// loop counter, not necessary
		      listOfForks = listOfForksIn;
		      //eatTime = 30;
		      //restTime = 20;
		      this.strtTime = System.currentTimeMillis();
		      System.out.println(System.currentTimeMillis() - strtTime + " " + this.getName() +"       run started ");
		      //System.out.println("Monk constructor: Creating Monk:  " +  threadName );
	}

	
	@Override
	public void run() {
		
		try {
			sleepInterval = (randomGenerator.nextInt(40)+5)*sl;
            this.sleep(sleepInterval); 
            System.out.println(System.currentTimeMillis() - strtTime + " " + this.thredNumber + ".run: "
            					+ this.getName() + ", " + " sleep: " + sleepInterval); 
            this.i=0; 
            while(!this.dishPlate.isEmplty()){
	            this.i=this.i+1;		// loop counter, not necessary indeed
	            
				if ((this.thredNumber == 0) && (listOfForks.get(0).isNotForkInUse()) 
						&& (listOfForks.get(maxMonkNum).isNotForkInUse()) && ! this.dishPlate.isEmplty()){
	            				
					//System.out.println("Is Fork-0 locked: " + this.holdsLock(listOfForks.get(0)));
	            	//System.out.println("Is Fork-" + maxMonkNum + " locked: " + this.holdsLock(listOfForks.get(maxMonkNum)));
	            	if(listOfForks.get(0).setForkAsInUse() && listOfForks.get(maxMonkNum).setForkAsInUse()){
	            		listOfForks.get(0).setOccupiedByNum(this.thredNumber);
	            		listOfForks.get(maxMonkNum).setOccupiedByNum(this.thredNumber);
	            		this.dishPlate.eatDumplingIfCan();
	            		this.isEating = true;
	            		sleepInterval = (randomGenerator.nextInt(eatTime)+5)*sl;
	    	            
	            		System.out.println(System.currentTimeMillis() - strtTime + " " + this.getName() +" eats dumpling no: " 
	            		+ (this.dishPlate.getDumplingsLeft()+1) + " for time: " + sleepInterval 
            			+ " is eating status: " + this.isEating + " Loop: " + i + "    No of threads:" + activeCount());
	            		
	            		this.sleep(sleepInterval);
	    	            this.isEating = false;
	    	            listOfForks.get(0).setOccupiedByNum(-1);
	            		listOfForks.get(maxMonkNum).setOccupiedByNum(-1);

	    	            if (this.dishPlate.getDumplingsLeft()==0){
	    	            	System.out.println(System.currentTimeMillis()  - strtTime + " " + this.getName() +
		            				" FINISHED EATING, DUMPLINGS LEFT: " + dishPlate.getDumplingsLeft()
		            				+ " LOOP :" + i + "    No of threads:" + activeCount());
	    	            	this.isEating = false;
	    	            	break;
	    	            }
	            	} else {
	            		listOfForks.get(0).setForkAsNotInUse();
	            		listOfForks.get(maxMonkNum).setForkAsNotInUse();
	            		this.isEating = false;
	            	}
				} else {
					listOfForks.get(0).setForkAsNotInUse();
            		listOfForks.get(maxMonkNum).setForkAsNotInUse();
            		this.isEating = false;
				}
				
				//System.out.println("Is Fork-0 locked: " + this.holdsLock(listOfForks.get(0)) + " Is in use: " + listOfForks.get(0).isForkInUse());
            	//System.out.println("Is Fork-" + maxMonkNum + " locked: " + this.holdsLock(listOfForks.get(maxMonkNum)) + " Is in use: " + listOfForks.get(maxMonkNum).isForkInUse());
				this.isEating = false;
				sleepInterval = (randomGenerator.nextInt(restTime)+5)*sl;		// monk rests
	            this.sleep(sleepInterval);
	            	
	            if (((this.thredNumber >= 1) || (this.thredNumber <= maxMonkNum)) && ! this.dishPlate.isEmplty()){
	            	for(int j=1; j<= maxMonkNum-1; j++){
	            		
	            		if((listOfForks.get(j).setForkAsInUse()) && (listOfForks.get(j-1).setForkAsInUse())){
	            			listOfForks.get(j).setOccupiedByNum(this.thredNumber);
		            		listOfForks.get(j-1).setOccupiedByNum(this.thredNumber);
	            			this.isEating = true;
	            			dishPlate.eatDumplingIfCan();
	    	            	sleepInterval = (randomGenerator.nextInt(eatTime)+5)*sl;
	    	    	        this.sleep(sleepInterval);
	    	    	        System.out.println(System.currentTimeMillis() - strtTime + " " + this.getName() + " eats dumpling no: " 
	    	            			+ (dishPlate.getDumplingsLeft()+1) + " for time: " + sleepInterval 
	    	            			+ " is eating status: " + this.isEating + " Loop: " + i + "    No of threads:" + activeCount());
	    	            	this.isEating = false;
	    	            	listOfForks.get(j).setOccupiedByNum(-1);
		            		listOfForks.get(j-1).setOccupiedByNum(-1);
	    	            	listOfForks.get(j).setForkAsNotInUse();
    		            	listOfForks.get(j-1).setForkAsNotInUse();
    		            	if (this.dishPlate.getDumplingsLeft()==0){
    		            		System.out.println(System.currentTimeMillis() - strtTime + " " + this.getName() +
    		            				" FINISHED EATING, DUMPLINGS LEFT: " + dishPlate.getDumplingsLeft() 
    		            				+ " LOOP :" + i + "    No of threads:" + activeCount());
    		            		this.isEating = false;
	    	            		break;
    		            	} else {
    		            		listOfForks.get(j).setForkAsNotInUse();
        		            	listOfForks.get(j-1).setForkAsNotInUse();
        		            	this.isEating = false;
    		            	} // end of if
    		            } else {
    		            	listOfForks.get(j).setForkAsNotInUse();
    		            	listOfForks.get(j-1).setForkAsNotInUse();
    		            	this.isEating = false;
    		            } // end of if

            		//System.out.println("Is Fork-" + j + " locked: " + this.holdsLock(listOfForks.get(j)));
	            	//System.out.println("Is Fork-" + (j-1) + " locked: " + this.holdsLock(listOfForks.get(j-1)));
            		sleepInterval = (randomGenerator.nextInt(restTime)+5)*sl;		// monk rests
            		this.isEating = false;
            		this.sleep(sleepInterval);
	            }	// end of if
	        }	// end of loop
            }	// try block
            } catch (InterruptedException e) {
	    	 e.printStackTrace();
	     }
	      System.out.println(System.currentTimeMillis() - strtTime + " " + this.getName() + ".run: " + "IS EXITING.");
	}

	
	public void start () {
      //System.out.println("Starting " +  this.getName() );
      
      if (threadIns == null) {
    	  threadIns = new Thread (this, this.getName());
    	  threadIns.start ();
      }
	}

	public int howManyDumplingsLeft(){
		return this.dishPlate.getDumplingsLeft();
	}
	
	public boolean isEating(){
		return this.isEating;
	}
	
	public void setAsEating(){
		this.isEating = true;
	}
	
	public void setAsNotEating(){
		this.isEating = false;
		System.out.println("GraphicMonk.setAsNotEating() " + this.getName() + " IsEating Status: " + this.isEating);
	}
}
