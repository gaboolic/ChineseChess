package tk.gbl.chessmodel;

import org.junit.Test;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.util.SaveReadUtil;

import java.util.List;

import static org.junit.Assert.*;

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
        Chessman king = chessboard.getChessman(new Point(4,0));
        System.out.println(king);
        List<Point> movePoints = king.getMovePoints(chessboard);
        System.out.println(movePoints);
    }
}