package lt.vcs.and.sevenmonks;

import java.util.ArrayList;
import java.util.List;

public class GrMonkList extends ArrayList {
	
	private List<GrMonk> grMonkList;
	
	public GrMonkList(){
		this.grMonkList = new ArrayList<>();
	}
	
	public void add(GrMonk grMonkIn){
		grMonkList.add(grMonkIn);
	}

}
