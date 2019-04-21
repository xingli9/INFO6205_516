package UI;

import GA.Individual;
import GA.Population;
import GA.PopulationSet;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;


/**
 * A sample canvas that draws a rainbow of lines
 * @author xing li
 */
public class BGCanvas extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGCanvas.class.getName());
	double[][] history = new double[PopulationSet.MAX_GENERATION][];
	int beginX;
	int beginY;
	int delta;
	int mostX = 100;
	int mostY;
	int generation;

    private Color col = null;
    private long counter = 0L;
    public static int rule = 0;
    private Population population = null;

	public BGCanvas() {

	}
	public BGCanvas(int i) {
		col = Color.WHITE;
		rule = i;
		System.out.println("BGconvas");
	}

	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {        //use repaint to call this method
		drawGraph(g);

    }

	// draw the welcome page
	public void drawBG(Graphics g) {
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, size.width, size.height);//填充颜色

		int x0 = size.width / 2 - 120;
		int y0 = size.height / 2;
		g2d.setColor(Color.RED);
		g2d.drawString("Please select a rule and press 'Start' button!", x0, y0);//添加文字
	}

	//show have not select a rule
	public void noSelect(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, size.width, size.height);//填充颜色

		int x0 = size.width / 2 - 120;
		int y0 = size.height / 2;
		g2d.setColor(Color.WHITE);
		g2d.drawString("You have not select a precision yet!!", x0, y0);//添加文字
	}

	// draw the lattice
	public void drawCoordinate(Graphics g) {
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		int edge = 20;
		int arrowLength = 10;
		if (generation > mostX) mostX += 100;
		if (generation == 0) {
			mostY = (int)population.getBest();

			if (mostY % 100 != 0) {
				mostY = (mostY / 100 + 1) * 100;
			}

		}
		while (mostY < population.getBest()) mostY += 100;
		delta = (size.height - 2 * edge) / 3;
		int widthDelta = (size.width - 2 * edge) / 4;
		if (delta > widthDelta) delta = widthDelta;
		beginX = size.width / 2 - 2 * delta;
		beginY = (int) (size.height / 2 + 1.5 * delta);

		//Axis X
		paintLine(g2d, beginX, beginY, beginX + 4 * delta, beginY, Color.black);
		paintLine(g2d, beginX + 4 * delta, beginY, beginX + 4 * delta - arrowLength, beginY - arrowLength, Color.black);
		paintLine(g2d, beginX + 4 * delta, beginY, beginX + 4 * delta - arrowLength, beginY + arrowLength, Color.black);

		//Axis Y
		paintLine(g2d, beginX, beginY, beginX, beginY - 3 * delta, Color.black);
		paintLine(g2d, beginX, beginY - 3 * delta, beginX - arrowLength, beginY - 3 * delta + arrowLength, Color.black);
		paintLine(g2d, beginX, beginY - 3 * delta, beginX + arrowLength, beginY - 3 * delta + arrowLength, Color.black);

		int lineSize = 50;
		col = Color.black;

		int yStep = (3 * delta - 20) / lineSize;
		int xStep = (4 * delta - 20) / lineSize;
		int yStepNum = 10 * (mostY) / lineSize;
		int xStepNum = 10 * (mostX) / lineSize;
		int xbeginNum = 0;
		int ybeginNum = 0;

		int XcurrX = beginX;
		int XcurrY = beginY;
		int YcurrX = beginX;
		int YcurrY = beginY;
		for (int i = 0; i <= lineSize; i++) {
			if (i % 10 == 0) {
				paintLine( g2d, XcurrX, XcurrY, XcurrX, XcurrY - 10, col);
				g2d.drawString("" + xbeginNum, XcurrX -10, XcurrY + 15);

				paintLine(g2d, YcurrX, YcurrY, YcurrX + 10, YcurrY,col);
				g2d.drawString("" + ybeginNum, YcurrX -40, YcurrY);

				xbeginNum += xStepNum;
				ybeginNum += yStepNum;
			} else {
				paintLine( g2d, XcurrX, XcurrY, XcurrX, XcurrY - 5, col);
				paintLine(g2d, YcurrX, YcurrY, YcurrX + 5, YcurrY,col);

			}
			XcurrX += xStep;
			YcurrY -= yStep;
		}
	}


	public void drawGraph(Graphics g) {
		log.info("Drawing Graph " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		if (population == null) {
			drawBG(g);
			return;
		}

		generation = population.getGenCount();
		drawCoordinate(g2d);
		double[] newGeneration = {population.getAverage(), population.getBest()};
		history[generation] = newGeneration;
		int step = 1;
		String dec = "0.";
		for (int i = 0; i < Individual.decimal; i++) {
			dec+=0;
		}
		DecimalFormat df = new DecimalFormat(dec);
		String bestValue = "      Best value:" + df.format(history[generation][1]);
		String aveValue = "Average value:" + df.format(history[generation][0]);
		String[] xValue = new String[Individual.range.length];
		for (int i = 0; i < xValue.length; i++) {
			xValue[i] = "x" + i + ": " + df.format(population.getIndividuals()[0].getValue()[i]);
			g2d.drawString(xValue[i], beginX + 700, beginY - 160 + i * 10);
		}


		g2d.drawString(bestValue, beginX + 500, beginY - 160);
		g2d.drawString(aveValue, beginX + 500, beginY - 150);



		for (int i = 0; i <= population.getGenCount(); i+=step) {
			g2d.setColor(Color.RED);
			g2d.drawString("*", beginX + i * (4 * delta - 20) / mostX, beginY - (float) (history[i][0]) * (3 * delta - 50) / mostY);
			g2d.setColor(Color.BLUE);
			g2d.drawString("#", beginX + i * (4 * delta - 20) / mostX, beginY - (float) (history[i][1]) * (3 * delta - 50) / mostY);

		}

	}


	/**
	 * A local routine to ensure that the color value is in the 0 to 255 range.
	 */
	private int validColor(int colorVal) {
		if (colorVal > 255)
			colorVal = 255;
		if (colorVal < 0)
			colorVal = 0;
		return colorVal;
	}


	/**
	 * A convenience routine to set the color and draw a line
	 * @param g2d the 2D Graphics context
	 * @param startx the line start position on the x-Axis
	 * @param starty the line start position on the y-Axis
	 * @param endx the line end position on the x-Axis
	 * @param endy the line end position on the y-Axis
	 * @param color the line color
	 */
	private void paintLine(Graphics2D g2d, int startx, int starty, int endx, int endy, Color color) {
		g2d.setColor(color);
		g2d.drawLine(startx, starty, endx, endy);
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Population) {
			this.population = (Population) arg;
			this.repaint();
		}
	}
}
