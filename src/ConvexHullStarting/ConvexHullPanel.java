package ConvexHullStarting;

import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

/**
 * Worked on with Grant */
public class ConvexHullPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	private List<Point2D> thePoints;

	private List<Point2D> theHull;

	private Line2D line;

	private Line2D top, bot;

	private LinkedList<List<Point2D>> prevs;
	private List<Point2D> cur_left, cur_right;


	// Default constructor

	public ConvexHullPanel() {
		this.thePoints = new ArrayList<>();
		this.theHull = new ArrayList<>();
		this.prevs = new LinkedList<>();
		this.cur_left = new ArrayList<>();
		this.cur_right = new ArrayList<>();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // To draw the background color, if set

		// Draw the list of points in blue
		g.setColor(Color.blue);
		for (int i = 0; i < thePoints.size(); i++) {
			Point2D currentPoint = thePoints.get(i);
			g.fillOval((int) currentPoint.getX() - 2, (int) currentPoint.getY() - 2, 4, 4);
		}

		// Draw the convex hull in red
		renderHull(g, Color.RED, theHull);

		for (List<Point2D> points: prevs) renderHull(g, Color.BLUE, points);

		renderHull(g, Color.GREEN, cur_left);
		renderHull(g, Color.GREEN, cur_right);


		if (Objects.nonNull(top)) {
			g.setColor(Color.GREEN);
			g.drawLine((int)top.getX1(), (int)top.getY1(), (int)top.getX2(), (int)top.getY2());
		}

		if (Objects.nonNull(bot)) {
			g.setColor(Color.GREEN);
			g.drawLine((int)bot.getX1(), (int)bot.getY1(), (int)bot.getX2(), (int)bot.getY2());
		}

		if (Objects.nonNull(line)) {
			g.setColor(Color.YELLOW);
			g.drawLine((int)line.getX1(), (int)line.getY1(), (int)line.getX2(), (int)line.getY2());
		}

		line = null;
	}

	public void renderHull(Graphics g, Color c, List<Point2D> points) {
		g.setColor(c);
		for (int i = 0; i < points.size(); i++) {
			Point2D firstPoint = points.get(i);
			Point2D secondPoint = points.get((i + 1) % points.size());

			g.drawLine((int) firstPoint.getX(), (int) firstPoint.getY(), (int) secondPoint.getX(), (int) secondPoint.getY());
			g.fillOval((int) firstPoint.getX() - 2, (int) firstPoint.getY() - 2, 4, 4);
			g.fillOval((int) secondPoint.getX() - 2, (int) secondPoint.getY() - 2, 4, 4);
		}
	}

	public void push(List<Point2D> points) {
		prevs.push(points);
		repaint();
	}

	public void pop() {
		if (prevs.size() > 0) prevs.pop();
		repaint();
	}

	public void line(Line2D line) {
		this.line = line;
		repaint();
	}

	public void top(Line2D top) {
		this.top = top;
		repaint();
	}

	public void bot(Line2D bot) {
		this.bot = bot;
		repaint();
	}



	public void nextLeftRight(List<Point2D> left, List<Point2D> right) {
		this.cur_left = left;
		this.cur_right = right;
		repaint();
	}

	public void reset() {
		this.top = null;
		this.bot = null;
		this.cur_left = new ArrayList<>();
		this.cur_right = new ArrayList<>();
		repaint();
	}

	public void clear() {
		this.top = null;
		this.bot = null;
		this.cur_left = new ArrayList<>();
		this.cur_right = new ArrayList<>();
		this.prevs = new LinkedList<>();
		this.theHull = new ArrayList<>();
		repaint();
	}

	public void setHull(List<Point2D> theHull) {
		this.theHull = theHull;
		this.prevs = new LinkedList<>();
		reset();
		repaint();
	}

	public void setPoints(List<Point2D> thePoints) {
		this.thePoints = thePoints;
		repaint();
	}
}
