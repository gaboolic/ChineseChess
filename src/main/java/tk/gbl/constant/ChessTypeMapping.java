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
    static Map<String, Class<? extends Chessman>> mapping = new HashMap<>();

    static {
        mapping.put("1", Rook.class);
        mapping.put("2", Horse.class);
        mapping.put("3", Bishop.class);
        mapping.put("4", Guard.class);
        mapping.put("5", King.class);
        mapping.put("6", Cannon.class);
        mapping.put("7", Pawn.class);

        mapping.put("!", Rook.class);
        mapping.put("@", Horse.class);
        mapping.put("#", Bishop.class);
        mapping.put("$", Guard.class);
        mapping.put("%", King.class);
        mapping.put("^", Cannon.class);
        mapping.put("&", Pawn.class);
    }

    public static Chessman getChess(String type) {
        Class<? extends Chessman> clz = mapping.get(type);
        if (clz == null) {
            return null;
        }
        Chessman chessman = null;
        try {
            chessman = clz.newInstance();
        } catch (Exception e) {
            return null;
        }
        char ch = type.charAt(0);
        if (ch >= '1' && ch <= '9') {
            chessman.setColor(GameConstant.red);
        } else {
            chessman.setColor(GameConstant.black);
        }
        chessman.setType(type);
        return chessman;
    }
}
