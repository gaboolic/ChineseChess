package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;

/**
 * 卒
 *
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Pawn extends Chessman {
    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "兵";
        }
        return "卒";
    }
}
