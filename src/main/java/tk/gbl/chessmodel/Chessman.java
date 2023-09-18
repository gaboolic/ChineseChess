package tk.gbl.chessmodel;

import tk.gbl.constant.ChessTypeMapping;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.io.Serializable;
import java.util.List;

/**
 * 棋子
 * <p/>
 * Date: 2017/11/15
 * Time: 15:39
 *
 * @author gaboolic
 */
public abstract class Chessman implements Serializable, Cloneable {
    Point point;

    String type;
    int color;

    public static Chessman getInstance(String chessNumberStr) {
        Chessman chessman = ChessTypeMapping.getChess(chessNumberStr);
        if (chessman == null) {
            throw new RuntimeException("棋子类型不正确");
        }
        return chessman;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public abstract String getChineseName();

    public boolean canRemove(Chessboard chessboard, Point target) {
        if (getMovePoints(chessboard).contains(target)) {
            return true;
        }
        return false;
    }

    public abstract List<Point> getMovePoints(Chessboard chessboard);

    public abstract int getEvalValue();

    @Override
    public String toString() {
        return "Chessman{" + "point=" + point + ", class=" + getClass().getSimpleName() + ", color=" + color + '}';
    }

    @Override
    public Chessman clone() {
        try {
            Chessman clone = (Chessman) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
