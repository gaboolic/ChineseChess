package tk.gbl.model;

import org.junit.Test;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.GameConstant;
import tk.gbl.util.SaveReadUtil;

import static org.junit.Assert.*;

/**
 * Date: 2023-09-20
 * Time: 9:58 AM
 *
 * @author gaboolic
 */
public class ChessboardTest {

    @Test
    public void isGameOver() {
        Chessman[][] chessmans = SaveReadUtil.read("gameover.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.black);

        int gameOver = chessboard.isGameOver();
        System.out.println(gameOver);
    }
}