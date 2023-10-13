package tk.gbl.ai;

import tk.gbl.model.Step;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Date: 2023-10-11
 * Time: 2:25 PM
 *
 * @author gaboolic
 */
public class ScoreDepth {
    double score;

    int depth;

    LinkedList<Step> steps = new LinkedList<>();

    public ScoreDepth(double score, int depth) {
        this.score = score;
        this.depth = depth;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public LinkedList<Step> getSteps() {
        return steps;
    }

    public void setSteps(LinkedList<Step> steps) {
        this.steps = steps;
    }
}
