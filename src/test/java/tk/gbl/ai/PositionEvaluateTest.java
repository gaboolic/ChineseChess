package tk.gbl.ai;

import org.junit.Test;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.util.SaveReadUtil;

/**
 * Date: 2023-09-22
 * Time: 5:23 PM
 *
 * @author
 */
public class PositionEvaluateTest {

    @Test
    public void getPositionValue() {
        Chessman[][] chessmans = SaveReadUtil.read("gamestart.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        Chessman chessman = chessboard.getChessman(new Point(1, 2));

        int value = PositionEvaluate.getPositionValue(chessman, chessboard);
        System.out.println(value);

        Chessman chessman2 = chessboard.getChessman(new Point(1, 7));

        int value2 = PositionEvaluate.getPositionValue(chessman2, chessboard);
        System.out.println(value2);
    }
}