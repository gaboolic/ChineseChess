package tk.gbl;

import tk.gbl.ai.AlphaBetaSearch;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Step;
import tk.gbl.util.CopyUtil;
import tk.gbl.util.SaveReadUtil;

/**
 * Date: 2023-09-18
 * Time: 7:49 PM
 *
 * @author gaboolic
 */
public class FlowTest {
    public static void main(String[] args) {
        Chessman[][] chessmans = SaveReadUtil.read("gamestart.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 10; i++) {
            Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
            System.out.println(step);
            chessboard = CopyUtil.makeStep(chessboard, step);
        }
        String result = SaveReadUtil.outputStr(chessboard.getChessmans());
        System.out.println(result);
    }
}
