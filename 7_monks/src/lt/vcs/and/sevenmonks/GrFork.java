package lt.vcs.and.sevenmonks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GrFork extends Fork{

	private final String grForkName;
	private final int id;
	int numberOfForks;
	int alphaMonk;
	private int xPos;
	private int yPos;
	private int alphaFork;
	private int forkLength;
	private int forkWidth;
	private int tableWidth = 800;
	private int tableHight = 800;
	private int tableRadius = 300;
	
	public GrFork(int forkNumberIn, int maxMonkNumIn){ 	
		super(forkNumberIn);
		
		this.id = forkNumberIn;
		numberOfForks = maxMonkNumIn +1;
		alphaFork=(int) ((360/(numberOfForks))*id + (360/(numberOfForks)/2));
		alphaMonk= (int) (360/(numberOfForks))*id;
		
		this.xPos = (int) (tableWidth/2 + (tableRadius-80) * Math.sin(Math.toRadians(alphaFork)));
		this.yPos = (int) (tableHight/2 + (tableRadius-80) * Math.cos(Math.toRadians(alphaFork)));
		//g2.drawOval(tableCentrX, tableCentrY, 3, 3);
		
		this.forkLength =60;
		this.forkWidth = 4;
		this.grForkName = "GraphicFork-" + super.forkNumber;
	}

	public void drawYourselfFork(Graphics gIn){
		//System.out.println("GraphicFork.drawYourselfFork() " + super.getForkNumber() + " Is in use Status: " + this.isInUse);
		
		Graphics2D g2 = (Graphics2D) gIn;
		//g2.rotate(theta, x, y);
		g2.rotate((double) Math.toRadians((-1)*alphaFork), (double) (xPos-forkWidth/2), (double) (yPos-forkLength)); 
		if(super.isInUse == true){
			g2.setColor(Color.WHITE);
			//alphaMonk = (int) (360/(this.numberOfForks))*this.occupiedByMonkNum;
			//int x = (int) (tableWidth/2 + (tableRadius-50) * Math.sin(Math.toRadians(alphaMonk)));
			//int y = (int) (tableHight/2 + (tableRadius-50) * Math.cos(Math.toRadians(alphaMonk)));
			g2.fillRect((int) (xPos-forkWidth/2), (int) (yPos), forkWidth, forkLength);
			//g2.fillRect((int) (xPos-forkWidth/2), (int) (yPos-forkLength/2), (-1)*forkWidth/2, (-1)*forkLength/2);
			g2.setColor(Color.BLACK);
			g2.drawRect((int) (xPos-forkWidth/2), (int) (yPos), forkWidth, forkLength);
			//g2.drawRect((int) (xPos-forkWidth/2), (int) (yPos-forkLength/2), (-1)*forkWidth/2, (-1)*forkLength/2);
		} else if(super.isInUse != true){
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect((int) (xPos-forkWidth/2), (int) (yPos), forkWidth, forkLength);
			//g2.fillRect((int) (xPos-forkWidth/2), (int) (yPos-forkLength/2), (-1)*forkWidth/2, (-1)*forkLength/2);
			g2.setColor(Color.BLACK);
			g2.drawRect((int) (xPos-forkWidth/2), (int) (yPos), forkWidth, forkLength);
			//g2.drawRect((int) (xPos-forkWidth/2), (int) (yPos-forkLength/2), (-1)*forkWidth/2, (-1)*forkLength/2);
			gIn.setFont(new Font("Arial", Font.BOLD, 16));
			gIn.drawString("" + (this.id + 1)  + "", (int) (xPos-forkWidth)+5, (int) (yPos-forkLength/2)+5);
		}
		g2.rotate((double) Math.toRadians((1)*alphaFork), (double) (xPos-forkWidth/2), (double) (yPos-forkLength)); 
		
		gIn.setColor(Color.BLACK);
		gIn.fillOval((int) (xPos-2), (int) (yPos-2), 4, 4);
	}

	
	public int getId(){
		return this.id;
	}
	
	public int getXPos(){
		return this.xPos;
	}
	
	public int getYPos(){
		return this.yPos;
	}
	
	public int getDiameter(){
		return this.forkLength;
	}
		
	
}
