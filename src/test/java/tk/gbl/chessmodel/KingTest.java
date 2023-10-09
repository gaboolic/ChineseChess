package tk.gbl.chessmodel;

import org.junit.Test;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.util.SaveReadUtil;

import java.util.List;

/**
 * Date: 2023-09-12
 * Time: 9:53 AM
 *
 * @author gaboolic
 */
public class KingTest {

    @Test
    public void getMovePoints() {
        Chessman[][] chessmans = SaveReadUtil.read("gamestart.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        Chessman king = chessboard.getChessman(new Point(4, 0));
        System.out.println(king);
        List<Point> movePoints = king.getMovePoints(chessboard);
        System.out.println(movePoints);
    }

    @Test
    public void getMovePoints2() {
        String str =
                "　　　將　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　炮炮　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　帥　　　　";
        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        Chessman king = chessboard.getChessman(new Point(3, 0));
        System.out.println(king);
        List<Point> movePoints = king.getMovePoints(chessboard);
        System.out.println(movePoints);
    }

    @Test
    public void getMovePoints3() {
        //todo 修复
        String str =
                "　　　將　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　炮　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　炮　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　帥　　　　";
        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        Chessman king = chessboard.getChessman(new Point(4, 9));
        System.out.println(king);
        List<Point> movePoints = king.getMovePoints(chessboard);
        System.out.println(movePoints);
    }
}