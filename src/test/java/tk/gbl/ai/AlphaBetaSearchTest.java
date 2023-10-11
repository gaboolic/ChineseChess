package tk.gbl.ai;

import org.junit.Test;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.model.Step;
import tk.gbl.util.CopyUtil;
import tk.gbl.util.SaveReadUtil;
import tk.gbl.util.ShowStepUtil;

/**
 * Date: 2023-09-13
 * Time: 3:44 PM
 *
 * @author gaboolic
 */
public class AlphaBetaSearchTest {

    @Test
    public void alphaBetaSearch() {
        Chessman[][] chessmans = SaveReadUtil.read("gamestart.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();
        Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
        System.out.println(step);

        Point start = step.getStart();
        Point end = step.getEnd();

        Chessman from = chessboard.getChessman(start);
        Chessman to = chessboard.getChessman(end);
        System.out.println("from "+from);
        System.out.println("to "+to);

//        chessboard = CopyUtil.makeStep(chessboard, step);
//        Step step2 = alphaBetaSearch.alphaBetaSearch(chessboard);
//        System.out.println(step2);
    }

    @Test
    public void test() {
        Chessman[][] chessmans = SaveReadUtil.readStr(
                "! @ # $ % $ # @ !\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 1 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "7 0 7 0 7 0 7 0 7\n" +
                        "0 6 0 0 0 0 0 6 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 2 3 4 5 4 3 2 1");

        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);


        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();
        Step step = alphaBetaSearch.alphaBetaSearch(chessboard);

        System.out.println(step);
        System.out.println(chessboard.getChessman(step.getStart()));

        System.out.println(chessboard.getChessman(step.getStart()).getMovePoints(chessboard));

        System.out.println(chessboard.getChessman(step.getEnd()));
    }

    @Test
    public void testSearch() {
        //被将军时，躲帥
        String str =
                "　　象將　士俥　　\n" +
                "　　　　　　　　車\n" +
                "　砲　　　　　　　\n" +
                "卒　傌　卒　卒　卒\n" +
                "　　　　　　　　　\n" +
                "　　卒　　　　　　\n" +
                "兵　　　兵　兵　兵\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "俥　相車帥仕相　　";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.red);

        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();
        Step step = alphaBetaSearch.alphaBetaSearch(chessboard);

        System.out.println(step);
        System.out.println(chessboard.getChessman(step.getStart()));

        System.out.println(chessboard.getChessman(step.getStart()).getMovePoints(chessboard));

        System.out.println(chessboard.getChessman(step.getEnd()));
    }

    @Test
    public void test双炮擒王() {
        String str2 =
                "　　　　　　　　　\n" +
                "　　　將　　　　　\n" +
                "　　炮　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　炮　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　帥　　　　\n";

        String str =
                "　　　　　　　　　\n" +
                "　　炮將　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　炮　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　帥　　　　\n";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str2);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.black);
        chessboard.setRound(50);

        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();
        Step step = alphaBetaSearch.alphaBetaSearch(chessboard);

        System.out.println(step);

        System.out.println(chessboard.getChessman(step.getStart()) + "---" + chessboard.getChessman(step.getEnd()));
        System.out.println(ShowStepUtil.showStep(step, chessboard));
        chessboard = CopyUtil.makeStep(chessboard, step);

        String result = SaveReadUtil.outputStr(chessboard.getChessmans());
        System.out.println(result);
    }
}