package tk.gbl.ai;

/**
 * Date: 2023-10-11
 * Time: 2:25 PM
 *
 * @author gaboolic
 */
public class ScoreDepth {
    double score;

    int depth;

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
}
