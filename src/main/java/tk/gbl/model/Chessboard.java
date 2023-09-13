package tk.gbl.model;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.chessmodel.King;
import tk.gbl.constant.GameConstant;
import tk.gbl.util.CopyUtil;

/**
 * 棋盘
 * <p>
 * Date: 2017/11/27
 * Time: 16:17
 *
 * @author gaboolic
 */
public class Chessboard {

    public static final int X_SIZE = 9;
    public static final int Y_SIZE = 10;
    private Chessman currentChessman;
    private Chessman[][] chessmans;

    private int current = GameConstant.red;


    public Chessman[][] getChessmans() {
        return chessmans;
    }

    public void setChessmans(Chessman[][] chessmans) {
        this.chessmans = chessmans;
    }

    public Chessman getChessman(Point point) {
        return chessmans[point.getY()][point.getX()];
    }

    public Chessman getChessman(int x, int y) {
        Point point = new Point(x, y);
        return chessmans[point.getY()][point.getX()];
    }

    public void setChessman(Chessman chessman) {
        Point point = chessman.getPoint();
        chessmans[point.getY()][point.getX()] = chessman;
    }

    public void setCurrentChessman(Chessman currentChessman) {
        this.currentChessman = currentChessman;
    }

    public Chessman getCurrentChessman() {
        return currentChessman;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void moveChessMan(Point toPoint) {
        Point fromPoint = currentChessman.getPoint();

        currentChessman.setPoint(toPoint);
        setChessman(currentChessman);
        chessmans[fromPoint.getY()][fromPoint.getX()] = null;
        currentChessman = null;
        //当前方
        if (current == GameConstant.red) {
            current = GameConstant.black;
        } else {
            current = GameConstant.red;
        }
    }

    public boolean isInsideBoard(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= X_SIZE || y >= Y_SIZE) {
            return false;
        }
        return true;
    }

    public boolean isGameOver() {
        int kingCount = 0;
        for (int row = 0; row < Chessboard.Y_SIZE; row++) {
            for (int column = 0; column < Chessboard.X_SIZE; column++) {
                Chessman chessman = this.getChessmans()[row][column];
                if (chessman instanceof King) {
                    kingCount++;
                }
            }
        }
        if (kingCount != 2) {
            return false;
        }
        return true;
    }

    public void applyStep(Step step) {

    }
}
