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

	private int cur_frame = 0;
	private List<Frame> frames;


	// Default constructor

	public ConvexHullPanel() {
		frames = new LinkedList<>();
		frames.add(new Frame());
	}

	int i = 0;
	public void paintComponent(Graphics g) {
		if (cur_frame >= frames.size() || cur_frame < 0) return;

		super.paintComponent(g); // To draw the background color, if set

		Frame frame = frames.get(cur_frame);
		// System.out.println(frame);

		// Draw the list of points in blue
		g.setColor(Color.blue);
		for (int i = 0; i < frame.getThePoints().size(); i++) {
			Point2D currentPoint = frame.getThePoints().get(i);
			g.fillOval((int) currentPoint.getX() - 2, (int) currentPoint.getY() - 2, 4, 4);
		}

		// Draw the convex hull in red
		if (Objects.nonNull(frame.getTheHull()) && frame.getTheHull().size() > 0) {
			renderHull(g, Color.RED, frame.getTheHull());
		} else {

			for (List<Point2D> points : frame.getPrevs()) renderHull(g, Color.BLUE, points);

			renderHull(g, Color.BLUE, frame.getCur_left());
			renderHull(g, Color.BLUE, frame.getCur_right());

			if (Objects.nonNull(frame.getLine())) {
				g.setColor(Color.YELLOW);
				g.drawLine((int) frame.getLine().getX1(), (int) frame.getLine().getY1(), (int) frame.getLine().getX2(), (int) frame.getLine().getY2());
			}

			if (Objects.nonNull(frame.getTop())) {
				g.setColor(Color.GREEN);
				g.drawLine((int) frame.getTop().getX1(), (int) frame.getTop().getY1(), (int) frame.getTop().getX2(), (int) frame.getTop().getY2());
			}

			if (Objects.nonNull(frame.getBot())) {
				g.setColor(Color.GREEN);
				g.drawLine((int) frame.getBot().getX1(), (int) frame.getBot().getY1(), (int) frame.getBot().getX2(), (int) frame.getBot().getY2());
			}


		}
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

	public List<Frame> frames() {
		return this.frames;
	}

	public void addFrame(Frame frame) {

		if (prevFrame().equals(frame)) {
//			Frame other = frame;
//			System.out.println(prevFrame().equals(frame));
//			System.out.println(prevFrame().thePoints.equals(((Frame) other).thePoints));
//			System.out.println(prevFrame().theHull.equals(((Frame) other).theHull));
//			System.out.println(prevFrame().prevs.equals(((Frame) other).prevs));
//			System.out.println(prevFrame().cur_left.equals(((Frame) other).cur_left));
//			System.out.println(prevFrame().cur_right.equals(((Frame) other).cur_right));
//			System.out.println(Objects.nonNull(prevFrame().line) ? prevFrame().line.equals(((Frame) other).line) : Objects.isNull(((Frame) other).line));
//			System.out.println(Objects.nonNull(prevFrame().top) ? prevFrame().top.equals(((Frame) other).top) : Objects.isNull(((Frame) other).top));
//			System.out.println(Objects.nonNull(this.prevFrame().bot) ? this.prevFrame().equals(((Frame) other).bot) : Objects.isNull(((Frame) other).bot));
//			System.out.println(this.hashCode() + " " + frame.hashCode());
//			System.out.println(String.format("Prev: %s %n", this.frames.get(this.frames.size() - 1)) + "Ignored " + frame);
			return;
		}
		this.frames.add(frame);
	}

	public void clear() {
		this.cur_frame = 0;
		this.frames = new ArrayList<>();
		this.frames.add(new Frame());
		this.repaint();
	}

	public Frame prevFrame() {
		if (frames.size() == 0) return null;
		return frames.get(frames.size() - 1);
	}

	public void reset() {
		this.cur_frame = 0;
		// next();
		this.repaint();
	}

	public void back() {
		this.cur_frame -= 1;
		if (this.cur_frame < 0) this.cur_frame = 0;
		this.repaint();
	}

	public boolean next() {
		boolean res = true;
		this.cur_frame += 1;
		if (this.cur_frame >= frames.size()) {
			this.cur_frame = frames.size() - 1;
			res = false;
		}
		this.repaint();
		return res;
	}

	public void skip() {
		this.cur_frame = frames.size() - 1;
		this.repaint();
	}





//	public void push(List<Point2D> points) {
//		prevs.push(points);
//		repaint();
//	}
//
//	public void pop() {
//		if (prevs.size() > 0) prevs.pop();
//		repaint();
//	}
//
//	public void line(Line2D line) {
//		this.line = line;
//		repaint();
//	}
//
//	public void top(Line2D top) {
//		this.top = top;
//		repaint();
//	}
//
//	public void bot(Line2D bot) {
//		this.bot = bot;
//		repaint();
//	}
//
//
//
//	public void nextLeftRight(List<Point2D> left, List<Point2D> right) {
//		this.cur_left = left;
//		this.cur_right = right;
//		repaint();
//	}
//
//	public void reset() {
//		this.top = null;
//		this.bot = null;
//		this.cur_left = new ArrayList<>();
//		this.cur_right = new ArrayList<>();
//		repaint();
//	}
//
//	public void clear() {
//		this.top = null;
//		this.bot = null;
//		this.cur_left = new ArrayList<>();
//		this.cur_right = new ArrayList<>();
//		this.prevs = new LinkedList<>();
//		this.theHull = new ArrayList<>();
//		repaint();
//	}
//
//	public void setHull(List<Point2D> theHull) {
//		this.theHull = theHull;
//		this.prevs = new LinkedList<>();
//		reset();
//		repaint();
//	}
//
//	public void setPoints(List<Point2D> thePoints) {
//		this.thePoints = thePoints;
//		repaint();
//	}
}
