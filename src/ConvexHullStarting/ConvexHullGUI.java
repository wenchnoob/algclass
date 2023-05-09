package ConvexHullStarting;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.*;

public class ConvexHullGUI {
	
	List<Point2D> points;
	
	MergeHull theConvexHullFinder;
	public ConvexHullGUI() {
		
		points = new ArrayList<>();
		
		JFrame f = new JFrame("Convex Hull Finder");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setSize(new Dimension(600, 500));
		
		// Add the components
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setLayout(new BorderLayout());
		
		
		
		
		// On the center we add the convex hull panel and add the even listener to it
		final ConvexHullPanel chp = new ConvexHullPanel();
		chp.setPreferredSize(new Dimension(400, 400));
		chp.setBackground(Color.WHITE);
		chp.addMouseListener(new MouseAdapter() {//you can add more dots

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);

				if (Objects.isNull(chp.prevFrame())) {
					chp.addFrame(new Frame().setThePoints(points));
				} else {
					chp.prevFrame().setThePoints(points);
				}
				points.add(e.getPoint());
				chp.repaint();
			}
		});
		
		mainPanel.add(chp, BorderLayout.CENTER);
		
		
		
		
		// On the left we add another panel that contains the controls
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setSize(new Dimension(100, 500));
		controlPanel.setLayout(new GridLayout(6, 1));
		//controlPanel.setLayout(new FlowLayout());
		
		JLabel numPointsLab = new JLabel("     # of points     ");
		controlPanel.add(numPointsLab);
		
		final JTextField numPoints = new JTextField(10);
		controlPanel.add(numPoints);
		
		
		JButton genPoints = new JButton("Generate Points");
		controlPanel.add(genPoints);
		//makes random points
		genPoints.addActionListener(arg0 -> {
			// Assume the text entered is an actual number
			int numberOfPoints = Integer.parseInt(numPoints.getText());

			// Make the List of Point2Ds
			points = new ArrayList<>();

			int width = chp.getWidth();
			int height = chp.getHeight();

			for (int i = 0; i < numberOfPoints; i++) {
				// Generate 2 random numbers in the range set by the hull panel
				// and not too close to the edges so it looks good
				int x = (int) (Math.random() * (width - 50)) + 25;
				int y = (int) (Math.random() * (height - 50)) + 25;

				points.add(new Point2D.Double(x, y));
			}

			// Display them on the panel
			chp.clear();
			chp.prevFrame().setThePoints(points);
			chp.repaint();
		});


		theConvexHullFinder = new MergeHull(chp);
	
		JButton genHull = new JButton("Generate Hull");
		genHull.addActionListener(e -> {
			Runnable r = () -> {
				// Generate the hull
				List<Point2D> hullPoints = theConvexHullFinder.computeHull(points);
			};
			Thread solver = new Thread(r);
			solver.start();
		});
		controlPanel.add(genHull);
		
		
		mainPanel.add(controlPanel, BorderLayout.WEST);


		JPanel animationPanel = new JPanel();

		JButton clear = new JButton("Clear");
		clear.addActionListener(action -> chp.clear());
		JButton reset = new JButton("Reset");
		reset.addActionListener(action -> chp.reset());
		JButton back = new JButton("Back");
		back.addActionListener(action -> chp.back());
		JButton play = new JButton("Play");
		play.addActionListener(action -> new Thread(() -> {
			while(chp.next()) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start());
		JButton next = new JButton("Next");
		next.addActionListener(action -> chp.next());
		JButton skip = new JButton("Skip");
		skip.addActionListener(action -> chp.skip());

		// animationPanel.add(clear);
		animationPanel.add(reset);
		animationPanel.add(back);
		animationPanel.add(play);
		animationPanel.add(next);
		animationPanel.add(skip);


		mainPanel.add(animationPanel, BorderLayout.SOUTH);
		
		
		
	
		// Replace the content pane of the frame with the main panel we built
		f.setContentPane(mainPanel);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ConvexHullGUI();
	}

}
