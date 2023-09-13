package tk.gbl.util;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.io.IOException;

/**
 * Date: 2023-09-13
 * Time: 4:14 PM
 *
 * @author gaboolic
 */
public class CopyUtil {
    public static Chessboard copyChessboard(Chessboard origin) {
        Chessboard newBoard = new Chessboard();
        newBoard.setCurrent(origin.getCurrent());
        Chessman[][] copyChessmans = new Chessman[10][9];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                copyChessmans[i][j] = origin.getChessmans()[i][j];
            }
        }
        newBoard.setChessmans(copyChessmans);
        return newBoard;
    }
}
