package lt.vcs.and.sevenmonks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.List;

public class GrMonk extends Monk {
	
	private final String grMonkName;
	//private boolean isEating = false;
	private final int id;
	private int xPos;
	private int yPos;
	private int alpha;
	private int xDish;
	private int yDish;
	private int diameter;
	private float elipsFctr;
	private int tableWidth = 800;
	private int tableHight = 800;
	private int tableRadius = 300;
	

	public GrMonk(int numberIn, int maxMonkNumIn, List<GrFork> listOfForksIn){
		
		super(numberIn, maxMonkNumIn, listOfForksIn);
		
		this.id = numberIn;
		this.grMonkName = "GraphicMonk-" + numberIn;
		this.diameter = 80;
		this.elipsFctr = 1.0F;	// 1.5F
		
		int numberOfMonks = maxMonkNumIn +1;
		alpha = (int) (360/(numberOfMonks))*id;
		xPos = (int) (tableWidth/2 + tableRadius * Math.sin(Math.toRadians(alpha)));
		yPos = (int) (tableHight/2 + tableRadius * Math.cos(Math.toRadians(alpha)));
		xDish = (int) (tableWidth/2 + (tableRadius-90) * Math.sin(Math.toRadians(alpha)));
		yDish = (int) (tableHight/2 + (tableRadius-90) * Math.cos(Math.toRadians(alpha)));
	}


	public void drawYourselfMonk(Graphics g){
		Graphics2D gIn = (Graphics2D) g;
		//System.out.println("GRAPHICMonk.drawYourselfMonk() " + this.getName() + " IsEating Status: " + super.isEating);
		
		//gIn.rotate(Math.toRadians((-1)*alpha), (int) (xPos-diameter*elipsFctr/2), (int) (yPos-diameter/elipsFctr/2)); 
		
		if(super.isEating == true){
			gIn.setColor(Color.WHITE);
			gIn.fillRect(10, 10, 95, 30);
			gIn.setColor(Color.BLACK);
			gIn.setFont(new Font("Arial", Font.BOLD, 20));
			
			//gIn.drawString("   ", 20, 20);
			
			gIn.drawString("" + applTimeRunning() + " s", 25, 30);
			gIn.setColor(Color.WHITE);
			gIn.fillOval((int) (xDish-70/2), (int) (yDish-70/2), ((int) (70)), ((int) (70)));
			gIn.setColor(Color.BLACK);
			gIn.drawOval((int) (xDish-70/2), (int) (yDish-70/2), ((int) (70)), ((int) (70)));
			gIn.setFont(new Font("Arial", Font.BOLD, 20));
			gIn.setColor(Color.BLACK);
			gIn.drawString("   ", xDish, yDish);
			gIn.drawString("" + super.dishPlate.getDumplingsLeft() + "", xDish, yDish);
			gIn.setColor(Color.ORANGE);
			gIn.fillOval((int) (xPos-diameter*elipsFctr/2), (int) (yPos-diameter/elipsFctr/2), ((int) (this.diameter*elipsFctr)), ((int) (this.diameter/elipsFctr)));
			gIn.setColor(Color.BLACK);
			gIn.drawOval((int) (xPos-diameter*elipsFctr/2), (int) (yPos-diameter/elipsFctr/2), ((int) (this.diameter*elipsFctr)), ((int) (this.diameter/elipsFctr)));
			gIn.setFont(new Font("Arial", Font.BOLD, 16));
			gIn.drawString("" + (this.id + 1)  + "", xPos, yPos);
		} else if(super.isEating != true){
			gIn.setColor(Color.LIGHT_GRAY);
			gIn.fillOval((int) (xPos-diameter*elipsFctr/2), (int) (yPos-diameter/elipsFctr/2), ((int) (this.diameter*elipsFctr)), ((int) (this.diameter/elipsFctr)));
			gIn.setColor(Color.BLACK);
			gIn.drawOval((int) (xPos-diameter*elipsFctr/2), (int) (yPos-diameter/elipsFctr/2), ((int) (this.diameter*elipsFctr)), ((int) (this.diameter/elipsFctr)));
			gIn.setFont(new Font("Arial", Font.BOLD, 16));
			gIn.drawString("" + (this.id + 1)  + "", xPos, yPos);
		}
		
		//gIn.rotate(Math.toRadians((1)*alpha), (int) (xPos-diameter*elipsFctr/2), (int) (yPos-diameter/elipsFctr/2));
		gIn.setColor(Color.BLACK);
		gIn.fillOval((int) (xPos-2), (int) (yPos-2), 4, 4);
	}
	
	private String applTimeRunning(){
		String time = new DecimalFormat("##.###").format((float ) (System.currentTimeMillis() - super.strtTime)/1000 );
		return time;
	}
	
	
	
	public String getMonkName(){
		return this.grMonkName;
	}
	
	public int getMonkId(){
		return this.id;
	}
	
	public int getXPos(){
		return this.xPos;
	}
	
	public int getYPos(){
		return this.yPos;
	}
	
	public int getAlpha(){
		return this.alpha;
	}
	
	public int getDiameter(){
		return this.diameter;
	}


	public void addChangeListener(TablePanel tablePanel) {
		// TODO Auto-generated method stub
		
	}


	
}
