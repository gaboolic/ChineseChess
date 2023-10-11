package tk.gbl;

import org.junit.Test;
import tk.gbl.ai.AlphaBetaSearch;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Step;
import tk.gbl.util.CopyUtil;
import tk.gbl.util.SaveReadUtil;
import tk.gbl.util.ShowStepUtil;

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

        for (int i = 0; i < 200; i++) {
            Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
            System.out.println(step);
            System.out.println(chessboard.getChessman(step.getStart()) + "---" + chessboard.getChessman(step.getEnd()));
            System.out.println(ShowStepUtil.showStep(step, chessboard));
            chessboard = CopyUtil.makeStep(chessboard, step);

            String result = SaveReadUtil.outputStr(chessboard.getChessmans());
            System.out.println(result);
        }
        String result = SaveReadUtil.outputStr(chessboard.getChessmans());
        System.out.println(result);
    }

    @Test
    public void testTest() {
        Chessman[][] chessmans = SaveReadUtil.read("ending/test.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setRound(100);
        chessboard.setCurrent(GameConstant.black);

        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 100; i++) {
            if (chessboard.isGameOver() != -1) {
                System.out.println("结束");
                break;
            }
            Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
            System.out.println(step);
            System.out.println(chessboard.getChessman(step.getStart()) + "---" + chessboard.getChessman(step.getEnd()));
            System.out.println(ShowStepUtil.showStep(step, chessboard));
            chessboard = CopyUtil.makeStep(chessboard, step);

            String result = SaveReadUtil.outputStr(chessboard.getChessmans());
            System.out.println(result);
        }
    }


    @Test
    public void test单兵擒王() {
        Chessman[][] chessmans = SaveReadUtil.read("ending/单兵擒王.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 100; i++) {
            if (chessboard.isGameOver() != -1) {
                System.out.println("结束");
                break;
            }
            Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
            System.out.println(step);
            System.out.println(chessboard.getChessman(step.getStart()) + "---" + chessboard.getChessman(step.getEnd()));
            System.out.println(ShowStepUtil.showStep(step, chessboard));
            chessboard = CopyUtil.makeStep(chessboard, step);

            String result = SaveReadUtil.outputStr(chessboard.getChessmans());
            System.out.println(result);
        }
    }

    @Test
    public void test双炮擒王() {
        //todo 无法计算出结果
        Chessman[][] chessmans = SaveReadUtil.read("ending/双炮擒王.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.red);
        chessboard.setRound(50);
        System.out.println(SaveReadUtil.outputStr(chessboard.getChessmans()));

        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 100; i++) {
            if (chessboard.isGameOver() != -1) {
                System.out.println("结束");
                break;
            }
            Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
            System.out.println(step);
            System.out.println(chessboard.getChessman(step.getStart()) + "---" + chessboard.getChessman(step.getEnd()));
            System.out.println(ShowStepUtil.showStep(step, chessboard));
            chessboard = CopyUtil.makeStep(chessboard, step);

            String result = SaveReadUtil.outputStr(chessboard.getChessmans());
            System.out.println(result);
        }
    }

    @Test
    public void test_jj象棋_沙场点兵_双兵对双士and卒() {
        Chessman[][] chessmans = SaveReadUtil.read("ending/双兵对双士+卒.txt");
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 100; i++) {
            if (chessboard.isGameOver() != -1) {
                System.out.println("结束");
                break;
            }
            Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
            System.out.println(step);
            System.out.println(chessboard.getChessman(step.getStart()) + "---" + chessboard.getChessman(step.getEnd()));
            System.out.println(ShowStepUtil.showStep(step, chessboard));
            chessboard = CopyUtil.makeStep(chessboard, step);

            String result = SaveReadUtil.outputStr(chessboard.getChessmans());
            System.out.println(result);
        }
    }
}
