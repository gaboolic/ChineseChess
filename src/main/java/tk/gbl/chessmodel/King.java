package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;

/**
 * 将帅
 *
 * Date: 2017/11/15
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class King extends Chessman {
    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "帅";
        }
        return "将";
    }
}
