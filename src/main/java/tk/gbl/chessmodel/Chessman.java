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
public abstract class Chessman implements Serializable, Cloneable {
    Point point;

    String type;
    int color;

    double score;

    public static Chessman getInstance(String chessNumberStr) {
        Chessman chessman = ChessTypeMapping.getChess(chessNumberStr);
        if (chessman == null) {
            throw new RuntimeException("棋子类型不正确");
        }
        return chessman;
    }

    public static Chessman getInstanceByChineseName(String chineseName) {
        Chessman chessman = ChessTypeMapping.getChessByChineseName(chineseName);
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
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public boolean judgeKingFace(Chessboard chessboard) {
        Chessman king1 = chessboard.getKing(GameConstant.red);
        Chessman king2 = chessboard.getKing(GameConstant.black);
        if (king1.getPoint().getX() == king2.getPoint().getX() && getPoint().getX() == king1.getPoint().getX()) {
            int x = getPoint().getX();
            int y1 = Math.min(king1.getPoint().getY(), king2.getPoint().getY());
            int y2 = Math.max(king1.getPoint().getY(), king2.getPoint().getY());
            int chessmanCount = 0;

            Chessman chessman = null;
            for (int i = y1 + 1; i < y2; i++) {
                if (chessboard.getChessman(x, i) != null) {
                    chessmanCount++;
                    chessman = chessboard.getChessman(x, i);
                }
            }
            if (chessmanCount == 1 && chessman.getPoint().equals(getPoint())) {
                return true;
            }
        }
        return false;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
