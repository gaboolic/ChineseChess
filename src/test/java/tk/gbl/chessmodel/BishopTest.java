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
public class BishopTest {

    @Test
    public void getMovePoints() {
        Chessman[][] chessmans = SaveReadUtil.read("gamestart.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        Chessman chessman = chessboard.getChessman(new Point(2,0));
        System.out.println(chessman);
        List<Point> movePoints = chessman.getMovePoints(chessboard);
        System.out.println(movePoints);

        Chessman chessman2 = chessboard.getChessman(new Point(6,0));
        System.out.println(chessman2);
        List<Point> movePoints2 = chessman2.getMovePoints(chessboard);
        System.out.println(movePoints2);

        // 测试飞象不能过河
        chessboard.setCurrentChessman(chessman2);
        chessboard.moveChessMan(new Point(4, 2));
        System.out.println(chessman2.getMovePoints(chessboard));
    }
}