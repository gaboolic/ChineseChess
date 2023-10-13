package tk.gbl.model;

import tk.gbl.ai.ScoreDepth;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(start, step.start) && Objects.equals(end, step.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Step{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
