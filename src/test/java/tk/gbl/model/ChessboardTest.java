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

    @Test
    public void isGameOver双炮擒王() {
        String str =
                "　　　　　將　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　炮　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　炮　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　帥　　　　";
        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.red);

        int gameOver = chessboard.isGameOver();
        System.out.println(gameOver);

        chessboard.setCurrent(GameConstant.black);

        int gameOver2 = chessboard.isGameOver();
        System.out.println(gameOver2);
    }
}