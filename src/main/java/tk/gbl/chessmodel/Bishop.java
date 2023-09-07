package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;

/**
 * 相
 * <p/>
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Bishop extends Chessman {
    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "相";
        }
        return "象";
    }
}
