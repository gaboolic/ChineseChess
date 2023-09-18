package tk.gbl.model;

/**
 * Date: 2017/11/27
 * Time: 16:14
 *
 * @author gaboolic
 */
public class Step {
    Point start;
    Point end;

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

    @Override
    public String toString() {
        return "Step{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
