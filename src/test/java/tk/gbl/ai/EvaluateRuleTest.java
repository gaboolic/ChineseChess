package tk.gbl.ai;

import org.junit.Test;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.model.Step;
import tk.gbl.util.CopyUtil;
import tk.gbl.util.SaveReadUtil;

/**
 * Date: 2023-09-18
 * Time: 5:34 PM
 *
 * @author gaboolic
 */
public class EvaluateRuleTest {

    @Test
    public void evaluatePositionGameStart() {
        //todo
        Chessman[][] chessmans = SaveReadUtil.readStr(
                        "! @ # $ % $ # @ !\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 ^ 0 0 0 0 0 ^ 0\n" +
                        "& 0 & 0 & 0 & 0 &\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "7 0 7 0 7 0 7 0 7\n" +
                        "0 6 0 0 0 0 0 6 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "1 2 3 4 5 4 3 2 1");

        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        double result = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result);
    }

    @Test
    public void evaluatePositionGameStart_horse() {
        Chessman[][] chessmans = SaveReadUtil.readStr(
                "! @ # $ % $ # @ !\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 ^ 0 0 0 0 0 ^ 0\n" +
                        "& 0 & 0 & 0 & 0 &\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "7 0 7 0 7 0 7 0 7\n" +
                        "0 6 2 0 0 0 0 6 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "1 0 3 4 5 4 3 2 1");

        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        double result = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result);
    }

    @Test
    public void evaluatePositionGameStart_rook() {
        //todo
        Chessman[][] chessmans = SaveReadUtil.readStr(
                "! @ # $ % $ # @ !\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 ^ 0 0 0 0 0 ^ 0\n" +
                        "& 0 & 0 & 0 & 0 &\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "7 0 7 0 7 0 7 0 7\n" +
                        "0 6 0 0 0 0 0 6 0\n" +
                        "1 0 0 0 0 0 0 0 0\n" +
                        "0 2 3 4 5 4 3 2 1");

        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        double result = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result);
    }

    @Test
    public void evaluatePositionGameStart_cannon() {
        Chessman[][] chessmans = SaveReadUtil.readStr(
                "! @ # $ % $ # @ !\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 ^ 0 0 0 0 0 ^ 0\n" +
                        "& 0 & 0 & 0 & 0 &\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "7 0 7 0 7 0 7 0 7\n" +
                        "0 0 0 0 6 0 0 6 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "1 2 3 4 5 4 3 2 1");

        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);

        double result = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result);
    }

    @Test
    public void evaluatePosition() {
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

        double result = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result);

        //Step{start=Point{x=4, y=3}, end=Point{x=4, y=1}}
        Step step = new Step(new Point(4, 3), new Point(4, 1));
        Chessboard chessboard2 = CopyUtil.makeStep(chessboard, step);
        double result2 = new EvaluateRule().evaluatePosition(chessboard2, GameConstant.red);
        System.out.println(result2);

        Step step3 = new Step(new Point(4, 3), new Point(4, 0));
        Chessboard chessboard3 = CopyUtil.makeStep(chessboard, step3);
        double result3 = new EvaluateRule().evaluatePosition(chessboard3, GameConstant.red);
        System.out.println(result3);
    }
}