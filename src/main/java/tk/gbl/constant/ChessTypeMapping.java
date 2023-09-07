package tk.gbl.constant;

import tk.gbl.chessmodel.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/11/28
 * Time: 15:46
 *
 * @author gaboolic
 */
public class ChessTypeMapping {
    static Map<Integer, Class<? extends Chessman>> mapping = new HashMap<>();

    static {
        mapping.put(1, Rook.class);
        mapping.put(2, Horse.class);
        mapping.put(3, Bishop.class);
        mapping.put(4, Guard.class);
        mapping.put(5, King.class);
        mapping.put(6, Cannon.class);
        mapping.put(7, Pawn.class);
    }

    public static Chessman getChess(int type) {
        Class<? extends Chessman> clz = mapping.get(type);
        if(clz == null) {
            return null;
        }
        try {
            return clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
