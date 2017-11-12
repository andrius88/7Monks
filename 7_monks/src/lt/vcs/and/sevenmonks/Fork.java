package lt.vcs.and.sevenmonks;

public class Fork {

	protected boolean isInUse;
	protected int forkNumber;
	protected int occupiedByMonkNum; 
	
	
	public Fork(int forkNumberIn){
		forkNumber = forkNumberIn;
		isInUse = false;
		occupiedByMonkNum = -1;
	}
	
	public void setOccupiedByNum(int monkNum){
		this.occupiedByMonkNum = monkNum;
	}
	
	public boolean isForkInUse (){
		return isInUse;
	}
	
	public boolean isNotForkInUse (){
		return !isInUse;
	}
	
	public boolean setForkAsInUse(){
		boolean actionSuccess =true; 
		if (this.isNotForkInUse()){
			synchronized (this){
				if (this.isNotForkInUse()){
					isInUse = true;
					actionSuccess = true;
				} else {
					actionSuccess = false;
				}
			}
		} else {
			actionSuccess = false;
		}
		return actionSuccess;
	}	
	
	public boolean setForkAsNotInUse(){
		boolean actionSuccess = true;
		if (this.isForkInUse()){
			synchronized (this){
				if (this.isForkInUse()){
					isInUse = false;
					actionSuccess = true;
				} else{
					actionSuccess = false;
				}
			}
		} else {
			actionSuccess = false;
		}
		return actionSuccess;
	}
	
	public void lockFork(){
		synchronized(this){
		}
	}
	
	public int getForkNumber() {
		return forkNumber;
	}

	
}
