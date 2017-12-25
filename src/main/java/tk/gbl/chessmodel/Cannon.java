package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;

/**
 * 炮
 *
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author Tian.Dong
 */
public class Cannon extends Chessman {
    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "炮";
        }
        return "砲";
    }
}
