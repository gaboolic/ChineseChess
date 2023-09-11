package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.List;

/**
 * 车
 *
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Rook extends Chessman {

    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "俥";
        }
        return "車";
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        return null;
    }

}
