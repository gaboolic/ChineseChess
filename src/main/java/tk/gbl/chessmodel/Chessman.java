package tk.gbl.chessmodel;

import tk.gbl.constant.ChessTypeMapping;
import tk.gbl.constant.GameConstant;
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
public abstract class Chessman implements Serializable {
    Point point;

    int type;
    int color;

    public static Chessman getInstance(int number) {
        int color = GameConstant.red;
        if (number < 0) {
            color = GameConstant.black;
            number = -number;
        }
        int type = number;
        Chessman chessman = ChessTypeMapping.getChess(type);
        if (chessman == null) {
            throw new RuntimeException("棋子类型不正确");
        }
        chessman.setColor(color);
        chessman.setType(type);
        return chessman;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
        return "Chessman{" +
                "point=" + point +
                ", class=" + getClass().getSimpleName() +
                ", color=" + color +
                '}';
    }
}
