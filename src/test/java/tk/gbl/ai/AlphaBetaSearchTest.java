package tk.gbl.ai;

import org.junit.Test;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Step;
import tk.gbl.util.SaveReadUtil;

import static org.junit.Assert.*;

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
    }
}