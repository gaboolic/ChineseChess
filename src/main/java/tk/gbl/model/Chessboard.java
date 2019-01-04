package tk.gbl.model;

import tk.gbl.chessmodel.Chessman;

/**
 * 棋盘
 * <p>
 * Date: 2017/11/27
 * Time: 16:17
 *
 * @author Tian.Dong
 */
public class Chessboard {
    private Chessman[][] chessmans;

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

    public void moveChessMan(Chessman currentChessman, Point toPoint) {
        Point fromPoint = currentChessman.getPoint();

        currentChessman.setPoint(toPoint);
        setChessman(currentChessman);
        chessmans[fromPoint.getY()][fromPoint.getX()] = null;
    }
}
