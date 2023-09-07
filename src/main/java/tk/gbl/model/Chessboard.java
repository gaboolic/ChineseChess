package tk.gbl.model;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.GameConstant;

/**
 * 棋盘
 * <p>
 * Date: 2017/11/27
 * Time: 16:17
 *
 * @author gaboolic
 */
public class Chessboard {

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

    public void moveChessMan(Point toPoint) {
        Point fromPoint = currentChessman.getPoint();

        currentChessman.setPoint(toPoint);
        setChessman(currentChessman);
        chessmans[fromPoint.getY()][fromPoint.getX()] = null;

        //当前方
        if (current == GameConstant.red) {
            current = GameConstant.black;
        } else {
            current = GameConstant.red;
        }
    }
}
