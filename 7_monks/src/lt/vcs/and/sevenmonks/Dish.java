package lt.vcs.and.sevenmonks;

import java.util.ArrayList;
import java.util.List;


public class Dish {
	
	private int dishNumber;
	private List<Dumpling> listOfDumplings;
	
	public Dish(int numbOfDumplings){
		listOfDumplings = new ArrayList<>();
		for(int i=0; i<=numbOfDumplings -1 ; i++){
			listOfDumplings.add(new Dumpling(i));
		}
	}
	
	public int getDishNumber() {	// is this necessary??
		return dishNumber;
	}
	
	public boolean isEmplty(){
		if (listOfDumplings.isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	public int getDumplingsLeft(){
		return listOfDumplings.size();
	}

	public boolean eatDumplingIfCan(){
		if (!listOfDumplings.isEmpty()){
			listOfDumplings.remove(listOfDumplings.size()-1);
			return true;
		}
		return false;
	}

}
