package ConvexHullStarting;

import java.util.*;
import java.awt.geom.*;

/**
 * Worked on with Grant */
public class MergeHull {

	private ConvexHullPanel chp;

	public MergeHull(ConvexHullPanel chp) {
		this.chp = chp;
	}
	
	/**
	 *	This entire algorithm assumes that no two points line on the same
	 *  vertical line
	 *  That is, no two points have the same x value
	 *
	 * @param originalPoints is a List(Vector) of Point2Ds
	 * @return  List(Vector) is a list of Point2Ds, a subset of those in originalPoints, 
	 * that represent the convex hull. These points are in ccw order.
	 */
	public List<Point2D> computeHull(List<Point2D> originalPoints) {
		// Make a copy of the points so we don't destroy the original
		List<Point2D> points = new ArrayList<Point2D>(originalPoints);

		// And note that Point2D doesn't implement Comparable so we have to provide an
		// external Comparator
		Collections.sort(points, (p0, p1) -> {

			// We care about sorting in the x-coord so this is our first tie-breaker
			int result = (int)(p0.getX() - p1.getX());
			if (result == 0) {
				// Use the y-coord as the 2nd (and last tie-breaker)
				result = (int)(p0.getY() - p1.getY());
			}

			return result;
		});
		
		chp.clear();
		chp.addFrame(chp.prevFrame().copy().setThePoints(points));
		chp.next();
		List<Point2D> result = recursiveMergeHull(points);
		chp.addFrame(chp.prevFrame().copy().setTheHull(result));
		for(int i = 0; i <result.size(); i ++){
			System.out.print( result.get(i) + "  ");
		}
		System.out.println();
		return result;
	}

	/**
	 * Returns true if the given tangent line is the lower tangent to the hull.
	 * @param tangent is a lower tangent if both the neighboring points are higher (ccw) to the tangent.
	 * Note that this method can also be used to find the upper tangent by reversing the direction
	 * of the tangent line.
	 * @param hull List of points that make the hull
	 * @param pointIndex of where the lowerTangent connects to the hull is given
	 * because it is unknown which end of the tangent line is connected to the hull (one
	 * could figure it out, but that would require work).
	 */

	private boolean isCWTangentAtPoint(Line2D tangent, List<Point2D> hull, int pointIndex) {

		return ((tangent.relativeCCW(hull.get((pointIndex + hull.size() - 1) % hull.size())) >= 0) && (tangent.relativeCCW(hull.get((pointIndex + 1) % hull.size())) >= 0));

	}
	/**
	* find leftmost point in list of points
	* @param points list of points
	* @return index of the leftmost point
	*/
	private int leftMostIndex(List<Point2D> points) {
		int result = 0;//this is as far left as can go - no negative values for coordinates for pixels

		for (int i = 0; i < points.size(); i++) {
			if ((points.get(i)).getX() < (points.get(result)).getX()) {
				result = i;
			}
		}

		return result;
	}
	/**
	* find rightmost point in list of points
	* @param points list of points
	* @return index of the rightmost point
	*/
	private int rightMostIndex(List<Point2D> points) {
		int result = 0;

		for (int i = 0; i < points.size(); i++) {
			if ((points.get(i)).getX() > (points.get(result)).getX()) {
				result = i;
			}
		}

		return result;
	}
	
	/** 
	 * This takes in a List of Point2Ds, sorted by their X-coordinate
	 * This outputs a List of hull points (a subset of points) in ccw order
	 * @param points list of poits in sorted x-coordinate order
	 * @return a list of point in the hull in ccw order
	 */
	private List<Point2D> recursiveMergeHull(List<Point2D> points) {

		List<Point2D> result = new ArrayList<Point2D>();//to be returned

		// Test for the base case -- a single point -- see if list size is one - easy base case
		// if size is one 
			//return the single point that was passed in b/c a single point is its own convex hull
		if (points.size() <= 1) {
			chp.addFrame(chp.prevFrame().copy().push(points));
			return points;
		}

		// else recursive step and it is long!
			// Divide the points into two sets leftPoints and rightPoints,
			// each containing 1/2 of the points -- to the left and right of a vertical line down the middle
			// can use addAll (fromIndex is inclusive, toIndex is exclusive (up to but not including))
			// note: subList returns a List, but a List is an Interface that Vector implements

			
			// Recursively call recursiveMergeHull each of the sublists
		int l = points.size();
		int m = l / 2;
		List<Point2D> left = recursiveMergeHull(points.subList(0, m));
		// mem_left(left);
		List<Point2D> right = recursiveMergeHull(points.subList(m, l));
		// mem_right(right);
		// chp.reset();
		// chp.addFrame(chp.prevFrame().copy());
		next(left, right);
			
			/*Next we need to merge the two hull together -- this is where all the work takes place
				Note: everything in this comment is just informative.
				 This step must be a O(n) algorithm here to make the overall
				 performance of mergeHull be O(n log(n))
				 That is, we can't just try all possible pairs of points -- this
				 would be a O(n^2) algorithm
				 So you need to find the two tangent lines
				 The idea is to start with the rightmost point in the leftHull and
				 the leftmost point in the rightHull. Then "walk" this line down (up for top tangent),
				 alternating until the lower (or upper) tangent is reached.
				 For this we can assume that each hull is in ccw order
			*/

			// First: Find the starting points for the walking

		int lp = rightMostIndex(left);
		int rp = leftMostIndex(right);
			

			// Find the bottom tangent line first use walkTangent
		Line2D bottom = walkTangent(lp, rp, left, right);
		chp.addFrame(chp.prevFrame().copy().setLine(null).setBot(bottom));

		// Find the upper tangent line next use walkTangent
		Line2D top = walkTangent(rp, lp, right, left);
		chp.addFrame(chp.prevFrame().copy().setLine(null).setTop(top));
			

			// Generate the complete Hull -- watch that zero break!
			// Remember that the complete hull needs to go ccw. Each subhull already goes ccw.
			// Grab the indices from the two returned hulls since we want to work
			// with indices into the hull vectors rather than with points when we
			// actually put the hulls together
			// you need the 4 points from your two tangent lines.
			// can use getP1() and getP2() see:
			// https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Line2D.html

		Point2D leftbot = bottom.getP1();
		Point2D rightbot = bottom.getP2();

		Point2D lefttop = top.getP2();
		Point2D righttop = top.getP1();
			
			
			// Grab all the points from the right hull from bottom to top and add to result
			// you are starting at an index location and need to be able to handle wrap around to the front!
		int rightbot_idx = right.indexOf(rightbot);
		int righttop_idx = right.indexOf(righttop);
		for (int i = rightbot_idx;;) {
			result.add(right.get(i));
			if (i == righttop_idx) break;
			i = (i + 1) % right.size();
		}
			
			
			// Need to add the last one out of the loop so I don't get an infinite
			// loop in the case where the hull consists of a single point
		int leftbot_idx = left.indexOf(leftbot);
		int lefttop_idx = left.indexOf(lefttop);
		for (int i = lefttop_idx;;) {
			result.add(left.get(i));
			if (i == leftbot_idx) break;
			i = (i + 1) % left.size();
		}
			
			// Next Grab all the points from the left hull from bottom to top and add to result
			// you are starting at an index location and need to be able to handle wrap around to the front!
			
			// Need to add the last one out of the loop so I don't get an infinite
			// loop in the case where the hull consists of a single point

			// end of if recursive step


		chp.addFrame(chp.prevFrame().copy().setTop(null).setBot(null).pop().pop().push(result));
		return result;
	}

	

	/**
	*  This takes the initial tangent line indices and the two hulls and walks.
	*  It walks the tangent down so it is a lower tangent
	*  Can be used to find the upper tangent by reversing the input tangent line
	*  and hulls
	* @param leftTangentPointIndex leftmost point in left hull
	* @param rightTangentPointIndex rightmost point in right hull
	* @param leftHull the list of points of the left hull ccw
	* @param rightHull the list of points of the right hull ccw
	* @return Line2D that is the tangent line
	*/
	private Line2D walkTangent(int leftTangentPointIndex, int rightTangentPointIndex, List<Point2D> leftHull, List<Point2D> rightHull) {
		Line2D tangentLine = new Line2D.Double(leftHull.get(leftTangentPointIndex), rightHull.get(rightTangentPointIndex));
		tempLine(tangentLine);

		while (!(isCWTangentAtPoint(tangentLine, leftHull, leftTangentPointIndex) && isCWTangentAtPoint(tangentLine, rightHull, rightTangentPointIndex))) {

			// Walk the left point lower
			while (!(isCWTangentAtPoint(tangentLine, leftHull, leftTangentPointIndex))) {
				leftTangentPointIndex = (leftTangentPointIndex + leftHull.size() - 1) % leftHull.size();
				tangentLine = new Line2D.Double(leftHull.get(leftTangentPointIndex), rightHull.get(rightTangentPointIndex));
				tempLine(tangentLine);
			}

			// Walk the right point lower
			while (!(isCWTangentAtPoint(tangentLine, rightHull, rightTangentPointIndex))) {
				rightTangentPointIndex = (rightTangentPointIndex + 1) % rightHull.size();
				tangentLine = new Line2D.Double(leftHull.get(leftTangentPointIndex), rightHull.get(rightTangentPointIndex));
				tempLine(tangentLine);
			}

		}

		return tangentLine;
	}

	private void next(List<Point2D> left, List<Point2D> right) {
		chp.addFrame(chp.prevFrame().copy().setCur_left(left));
		chp.addFrame(chp.prevFrame().copy().setCur_right(right));
	}
	private void tempLine(Line2D line) {
		chp.addFrame(chp.prevFrame().copy().setLine(line));
	}
}
