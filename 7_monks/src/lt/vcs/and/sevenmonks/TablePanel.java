package lt.vcs.and.sevenmonks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TablePanel extends JPanel implements PropertyChangeListener{
	
	final int numberOfMonks;
	final int maxMonkNum;
	final List<GrFork> listOfForks;
	final List<GrMonk> listOfMonks;
	
	public TablePanel(int numberOfMonksIn, List<GrFork> listOfForksIn, List<GrMonk> listOfMonksIn){
		numberOfMonks = numberOfMonksIn;
		maxMonkNum = numberOfMonks - 1;
		listOfForks = listOfForksIn;
		listOfMonks = listOfMonksIn;
	}
	
	public void paintComponent(Graphics g){
		//super.paintComponent(g);
		setSize(new Dimension(800, 800));
		setPreferredSize(new Dimension(800, 800));
		setBackground(Color.LIGHT_GRAY);
		setFocusable(true);
		g.drawOval(400-270, 400-270, 540, 540);		// test
		

		for (GrMonk mainGfrMonkIns : listOfMonks) {
			mainGfrMonkIns.drawYourselfMonk(g);		// have to draw monks
			repaint();
		}
		
		for (GrFork grfork : listOfForks) {
			grfork.drawYourselfFork(g);				// have to draw monks
			repaint();
		}
	}

	public static void main(String[] args) {
		
		final int numberOfMonks = 7;				//############### define number of monks ######################
		final int maxMonkNum = numberOfMonks - 1; 
		final List<GrFork> listOfForks = new ArrayList<>();
		final List<GrMonk> listOfMonks = new ArrayList<>(); 

		
		
		for(int i = 0; i<=maxMonkNum; i++){
			GrFork forkIns = new GrFork(i, maxMonkNum);
			listOfForks.add(forkIns);
		}
		
		for(int i = 0; i<=maxMonkNum; i++){
			GrMonk mainGrMonkIns = new GrMonk(i, maxMonkNum, listOfForks);
			listOfMonks.add(mainGrMonkIns);
		}
		

			JFrame monkFrameIns = new JFrame();
			TablePanel tablePanel = new TablePanel(numberOfMonks, listOfForks, listOfMonks);
			monkFrameIns.setSize(802, 802);
			monkFrameIns.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			monkFrameIns.add(tablePanel);
			monkFrameIns.setResizable(false);
			monkFrameIns.setVisible(true);
			
			Graphics g2 =  tablePanel.getGraphics();
			g2.drawRect(200, 200, 100, 100);
			
			tablePanel.paint(g2);
			monkFrameIns.add(tablePanel);
			monkFrameIns.getContentPane().repaint();
			tablePanel.repaint();
	
				
			for (GrMonk mainGfrMonkIns : listOfMonks) {
				mainGfrMonkIns.start();						// DINNER STARTS
			}

	}//******************* END OF MAIN ********************************************

	/*public TablePanel(GrMonk grMonk) {
		//grMonk.addChangeListener(this);
	}*/
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		repaint();
	}
	
}


