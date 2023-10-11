package tk.gbl.model;

import tk.gbl.ai.ScoreDepth;

/**
 * Date: 2017/11/27
 * Time: 16:14
 *
 * @author gaboolic
 */
public class Step {
    Point start;
    Point end;

    ScoreDepth scoreDepth;
    double evaluateValue;

    public Step() {
    }

    public Step(Point start, Point end) {
        this.start = start;
        this.end = end;
    }


    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public ScoreDepth getScoreDepth() {
        return scoreDepth;
    }

    public void setScoreDepth(ScoreDepth scoreDepth) {
        this.scoreDepth = scoreDepth;
    }

    public double getEvaluateValue() {
        return evaluateValue;
    }

    public void setEvaluateValue(double evaluateValue) {
        this.evaluateValue = evaluateValue;
    }

    @Override
    public String toString() {
        return "Step{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
