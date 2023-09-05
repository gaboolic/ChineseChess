package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;

/**
 * 车
 *
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author Tian.Dong
 */
public class Rook extends Chessman {

    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "俥";
        }
        return "車";
    }
}
