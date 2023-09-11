package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.List;

/**
 * 炮
 *
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Cannon extends Chessman {
    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "炮";
        }
        return "砲";
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        return null;
    }

}
