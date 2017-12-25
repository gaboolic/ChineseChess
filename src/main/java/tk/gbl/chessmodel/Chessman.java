package tk.gbl.chessmodel;

import tk.gbl.constant.ChessTypeMapping;
import tk.gbl.constant.GameConstant;

/**
 * 棋子
 * <p/>
 * Date: 2017/11/15
 * Time: 15:39
 *
 * @author Tian.Dong
 */
public abstract class Chessman {
    int type;
    int color;

    public static Chessman getInstance(int number) {
        int color = GameConstant.red;
        if (number < 0) {
            color = GameConstant.black;
            number = -number;
        }
        int type = number;
        Chessman chessman = ChessTypeMapping.getChess(type);
        if(chessman == null) {
            return null;
        }
        chessman.setColor(color);
        chessman.setType(type);
        return chessman;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public abstract String getChineseName();
}
