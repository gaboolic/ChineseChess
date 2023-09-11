package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.List;

/**
 * 士
 *
 * Date: 2017/11/15
 * Time: 15:33
 *
 * @author gaboolic
 */
public class Guard extends Chessman {

    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "仕";
        }
        return "士";
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        return null;
    }

}
