package ConvexHullStarting;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Frame {
    private List<Point2D> thePoints;
    private List<Point2D> theHull;
    private Line2D line;
    private Line2D top, bot;
    private LinkedList<List<Point2D>> prevs;
    private List<Point2D> cur_left, cur_right;

    public Frame() {
        this.thePoints = new ArrayList<>();
        this.theHull = new ArrayList<>();
        this.prevs = new LinkedList<>();
        this.cur_left = new ArrayList<>();
        this.cur_right = new ArrayList<>();
    }

    public Frame(List<Point2D> points, List<Point2D> theHull, LinkedList<List<Point2D>> prevs, List<Point2D> cur_left, List<Point2D> cur_right, Line2D line,
                 Line2D top, Line2D bot) {
        this.thePoints = points;
        this.theHull = theHull;
        LinkedList<List<Point2D>> tmp = new LinkedList<>();
        tmp.addAll(prevs);
        this.prevs = tmp;
        this.cur_left = cur_left;
        this.cur_right = cur_right;
        this.line = line;
        this.top = top;
        this.bot = bot;
    }

    public Frame copy() {
        return new Frame(thePoints, theHull, prevs, cur_left, cur_right, line, top, bot);
    }

    public List<Point2D> getThePoints() {
        return thePoints;
    }

    public Frame setThePoints(List<Point2D> thePoints) {
        this.thePoints = thePoints;
        return this;
    }

    public List<Point2D> getTheHull() {
        return theHull;
    }

    public Frame setTheHull(List<Point2D> theHull) {
        this.theHull = theHull;
        return this;
    }

    public Line2D getLine() {
        return line;
    }

    public Frame setLine(Line2D line) {
        this.line = line;
        return this;
    }

    public Line2D getTop() {
        return top;
    }

    public Frame setTop(Line2D top) {
        this.top = top;
        return this;
    }

    public Line2D getBot() {
        return bot;
    }

    public Frame setBot(Line2D bot) {
        this.bot = bot;
        return this;
    }

    public LinkedList<List<Point2D>> getPrevs() {
        return prevs;
    }

    public Frame setPrevs(LinkedList<List<Point2D>> prevs) {
        this.prevs = prevs;
        return this;
    }

    public Frame push(List<Point2D> prev) {
        this.prevs.push(prev);
        return this;
    }

    public Frame pop() {
        this.prevs.pop();
        return this;
    }

    public List<Point2D> getCur_left() {
        return cur_left;
    }

    public Frame setCur_left(List<Point2D> cur_left) {
        this.cur_left = cur_left;
        return this;
    }

    public List<Point2D> getCur_right() {
        return cur_right;
    }

    public Frame setCur_right(List<Point2D> cur_right) {
        this.cur_right = cur_right;
        return this;
    }
}
