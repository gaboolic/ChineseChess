package tk.gbl.util;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Step;

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
                Chessman chessman = origin.getChessmans()[i][j];
                if (chessman != null) {
                    Chessman copyChessman = copyChessman(chessman);
                    copyChessmans[i][j] = copyChessman;
                }
            }
        }
        newBoard.setChessmans(copyChessmans);
        return newBoard;
    }

    private static Chessman copyChessman(Chessman origin) {
        return origin.clone();
    }

    public static Chessboard makeStep(Chessboard origin, Step step) {
        Chessboard newBoard = copyChessboard(origin);
        if (origin.getChessman(step.getEnd()) != null) {
            newBoard.setNoEatRound(0);
        } else {
            newBoard.setNoEatRound(origin.getNoEatRound() + 1);
        }
        Chessman currentChessman = newBoard.getChessman(step.getStart());
        newBoard.setCurrentChessman(currentChessman);
        newBoard.moveChessMan(step.getEnd());
        newBoard.setCurrent(origin.getCurrent() ^ 1);
        newBoard.setRound(origin.getRound() + 1);
        return newBoard;
    }
}
