package processors;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Data;
import model.Values;
import util.Utility;

public class DisplayProcessor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int currentX = 40;
	Data data = Data.getInstance();

	public DisplayProcessor() throws IOException {
		JFrame f = new JFrame("Nielsen");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.pack();
		f.add(this);
		f.setSize(Utility.getProperty("panel.width"), Utility.getProperty("panel.height"));
		f.setVisible(true);
	}


	/**
	 * This method creates the panel on right corner displays label and assigned
	 * color
	 * 
	 * @param graphics
	 * @throws IOException
	 */
	public void createRightPanel(Graphics graphics) throws IOException {
		int x = Utility.getProperty("right_panel_width");
		int y = Utility.getProperty("right_panel_height");
		int dimension = Utility.getProperty("right_panel_dimensions");

		for (int i = 1; i < data.getLabels().size(); i++) {
			String label = data.getLabels().get(i);
			graphics.setColor(data.getColorsforLabels().get(label));
			graphics.fillRect(x, y, dimension, dimension);
			graphics.setColor(Color.black);
			graphics.drawString(label, x + 30, y + 10);
			y += 30;
		}
	}

	/**
	 * This method draws the horizontal background lines on panel
	 * 
	 * @param g
	 * @param max
	 * @param blockSize
	 */
	public void drawBackGroundLines(Graphics g, int max, int blockSize) {
		for (int i = 30; i < 600; i += 50) {
			g.setColor(Color.black);
			g.drawString(Integer.toString(max), 0, i);
			g.setColor(Color.lightGray);
			g.drawLine(30, i, 680, i);
			max -= blockSize;
		}
	}

	/**
	 * This method draws the horizontal line at(0,0)
	 * 
	 * @param g
	 * @param zeroPosition
	 */
	public void drawZeroAxis(Graphics g, int zeroPosition) {
		g.setColor(Color.black);
		g.drawString("0", 0, zeroPosition);
		g.drawLine(30, zeroPosition, 680, zeroPosition);
	}

	/**
	 * This method sorts the input labels on X axis
	 * 
	 * @param list
	 * @param map
	 * @return
	 */
	public String[] sortLables(String[] list, HashMap<String, Values> map) {
		int temp = 0;
		for (Values v : data.getValues()) {
			map.put(v.getHeading(), v);
			list[temp] = v.getHeading();
			temp++;
		}
		Arrays.sort(list);
		return list;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.black);
		g2d.drawLine(30, 30, 30, 530);
		g2d.drawLine(30, 530, 580, 530);
		g2d.drawString(data.getLabels().get(0).toUpperCase(), 300, 565);

		try {
			createRightPanel(g);
		} catch (IOException e) {
			System.err.println("Error while creating right panel");
		}
		int absDiff = Math.abs(data.getMaxVal()) + Math.abs(data.getMinVal());
		absDiff = absDiff % 10 == 0 ? absDiff : (10 - absDiff % 10) + absDiff;
		int blockSize = absDiff / 10;
		
		int max = data.getMaxVal();
		int zeroPosition = 30 + (max * 50 / blockSize);
		drawBackGroundLines(g, max, blockSize);
		drawZeroAxis(g, zeroPosition);
		HashMap<String, Values> map = new HashMap<>();
		String[] list = sortLables(new String[data.getValues().size()], map);
		drawGraph(g, map, list, zeroPosition, blockSize);
	}
	
	/**
	 * This method returns the bar size based on number of input values
	 * @return
	 */
	public int getBarSize(){
		int count = Data.getInstance().getValues().size();
		if(count<=8){
			return 20;
		}else if(count > 8 && count < 16){
			return 10;
		}else{
			return 5;
		}
		
	}
	/**
	 * This methods draws the graph for input values
	 * 
	 * @param g
	 * @param map
	 * @param list
	 * @param zeroPosition
	 * @param blockSize
	 */
	private void drawGraph(Graphics g, HashMap<String, Values> map, String[] list, int zeroPosition, int blockSize) {
		int barSize=getBarSize();
		int gapBetweenBars=20;
		for (String i : list) {
			Values v = map.get(i);
			g.setColor(Color.BLACK);
			g.drawString(v.getHeading(), currentX, 550);
			for (String s : data.getLabels()) {
				if (v.getMap().containsKey(s)) {
					g.setColor(data.getColorsforLabels().get(s));
					int ans = zeroPosition - (50 * v.getMap().get(s) / blockSize);
					if (v.getMap().get(s) >= 0) {
						/* labels with positive values*/
						g.fillRect(currentX, ans, barSize, v.getMap().get(s) * 50 / blockSize);
					} else {
						/* labels with negative values */
						g.fillRect(currentX, zeroPosition, barSize, -1 * v.getMap().get(s) * 50 / blockSize);
					}
					currentX += barSize;
				}
			}
			g.drawLine(currentX + 5, zeroPosition - 5, currentX + 5, zeroPosition + 5);
			currentX += gapBetweenBars;
		}
	}
}
