package tk.gbl.model;

import tk.gbl.chessmodel.Chessman;

/**
 * 棋盘
 *
 * Date: 2017/11/27
 * Time: 16:17
 *
 * @author Tian.Dong
 */
public class Chessboard {
    Chessman[][] chessmans;

    public Chessman[][] getChessmans() {
        return chessmans;
    }

    public void setChessmans(Chessman[][] chessmans) {
        this.chessmans = chessmans;
    }
}
