package tk.gbl.chessmodel;

import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.List;

/**
 * 马
 *
 * Date: 2017/11/15
 * Time: 15:25
 *
 * @author gaboolic
 */
public class Horse extends Chessman {
    @Override
    public String getChineseName() {
        return "马";
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        return null;
    }

}
