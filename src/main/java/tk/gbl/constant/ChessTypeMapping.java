package tk.gbl.constant;

import tk.gbl.chessmodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2017/11/28
 * Time: 15:46
 *
 * @author gaboolic
 */
public class ChessTypeMapping {
    static Map<String, Class<? extends Chessman>> mapping = new HashMap<>();
    static List<Class<? extends Chessman>> chessModelList = new ArrayList<>();

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

        chessModelList.add(Rook.class);
        chessModelList.add(Horse.class);
        chessModelList.add(Bishop.class);
        chessModelList.add(Guard.class);
        chessModelList.add(King.class);
        chessModelList.add(Cannon.class);
        chessModelList.add(Pawn.class);
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

    public static Chessman getChessByChineseName(String chineseName) {
        for (Class<? extends Chessman> clz : chessModelList) {
            Chessman chessman = null;
            try {
                chessman = clz.newInstance();
            } catch (Exception e) {
                return null;
            }
            chessman.setColor(GameConstant.black);
            if (chessman.getChineseName().equals(chineseName)) {
                return chessman;
            }
            chessman.setColor(GameConstant.red);
            if (chessman.getChineseName().equals(chineseName)) {
                return chessman;
            }
        }
        return null;
    }
}
