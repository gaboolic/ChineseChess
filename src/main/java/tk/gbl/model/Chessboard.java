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
        return chessmans[point.getX()][point.getY()];
    }

    public void setChessman(Chessman chessman) {
        Point point = chessman.getPoint();
        chessmans[point.getX()][point.getY()] = chessman;
    }

    public void moveChessMan(Chessman currentChessman, Point toPoint) {
        Point fromPoint = currentChessman.getPoint();

        currentChessman.setPoint(toPoint);
        setChessman(currentChessman);
        chessmans[fromPoint.getX()][fromPoint.getY()] = null;
    }
}
