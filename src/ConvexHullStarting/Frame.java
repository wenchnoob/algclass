package ConvexHullStarting;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Frame {
    public List<Point2D> thePoints;
    public List<Point2D> theHull;
    public Line2D line;
    public Line2D top, bot;
    public LinkedList<List<Point2D>> prevs;
    public List<Point2D> cur_left, cur_right;

    public Frame() {
        this.thePoints = new ArrayList<>();
        this.theHull = new LinkedList<>();
        this.prevs = new LinkedList<>();
        this.cur_left = new LinkedList<>();
        this.cur_right = new LinkedList<>();
    }

    public Frame(List<Point2D> points, List<Point2D> theHull, LinkedList<List<Point2D>> prevs, List<Point2D> cur_left, List<Point2D> cur_right, Line2D line,
                 Line2D top, Line2D bot) {
        this.thePoints = points;
        this.theHull = theHull;
        this.prevs = new LinkedList<>(prevs);
        this.cur_left = new LinkedList<>(cur_left);
        this.cur_right = new LinkedList<>(cur_right);
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
        if (!this.prevs.isEmpty()) this.prevs.pop();
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

    @Override
    public boolean equals(Object other) {
        if (other instanceof Frame) {
            return this.thePoints.equals(((Frame) other).thePoints)
                    && this.theHull.equals(((Frame) other).theHull)
                    && this.prevs.equals(((Frame) other).prevs)
                    && this.cur_left.equals(((Frame) other).cur_left)
                    && this.cur_right.equals(((Frame) other).cur_right)
                    && (Objects.nonNull(this.line) ? this.line.equals(((Frame) other).line) : Objects.isNull(((Frame) other).line))
                    && (Objects.nonNull(this.top) ? this.top.equals(((Frame) other).top) : Objects.isNull(((Frame) other).top))
                    && (Objects.nonNull(this.bot) ? this.bot.equals(((Frame) other).bot) : Objects.isNull(((Frame) other).bot));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.thePoints, this.theHull, this.prevs, this.cur_left, this.cur_right, this.line, this.top, this.bot);
    }

    @Override
    public String toString() {
        return String.format("The points: %s%nThe hull: %s%nThe line: %s%nThe top: %s%nThe bot:%s%nThe prevs: %s%nCur Left: %s%nCur Right: %s%n%n",
                this.thePoints, this.theHull, this.line, this.top, this.bot, this.prevs, this.cur_left, this.cur_right);
    }
}
