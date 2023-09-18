package tk.gbl.ai;

import org.junit.Test;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.model.Step;
import tk.gbl.util.CopyUtil;
import tk.gbl.util.SaveReadUtil;

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
        System.out.println(from);
        System.out.println(to);

        chessboard = CopyUtil.makeStep(chessboard, step);
        Step step2 = alphaBetaSearch.alphaBetaSearch(chessboard);
        System.out.println(step2);
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
}