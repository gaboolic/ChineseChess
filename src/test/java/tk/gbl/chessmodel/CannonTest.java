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
public class CannonTest {

    @Test
    public void getMovePoints() {
        Chessman[][] chessmans = SaveReadUtil.read("gamestart.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        Chessman chessman = chessboard.getChessman(new Point(1,2));
        System.out.println(chessman);
        List<Point> movePoints = chessman.getMovePoints(chessboard);
        System.out.println(movePoints);

        Chessman chessman2 = chessboard.getChessman(new Point(7,2));
        System.out.println(chessman2);
        List<Point> movePoints2 = chessman2.getMovePoints(chessboard);
        System.out.println(movePoints2);
    }

    @Test
    public void test() {
        String str =
                "車　　士將　　馬　\n" +
                "　俥　　士　　　　\n" +
                "　　　　象　　　象\n" +
                "卒　　　　　卒　卒\n" +
                "　砲卒　卒　　　　\n" +
                "　　　　　　　　　\n" +
                "兵　兵　兵　兵　兵\n" +
                "　炮傌　　　傌　　\n" +
                "　　　　　　　　　\n" +
                "　　相仕帥仕相　　";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        Chessman chessman = chessboard.getChessman(new Point(1,4));
        System.out.println(chessman);
        List<Point> movePoints = chessman.getMovePoints(chessboard);
        System.out.println(movePoints);
    }
}