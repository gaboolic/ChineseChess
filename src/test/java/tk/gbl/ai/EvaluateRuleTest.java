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
    public void evaluatePositionGameStart_pawn() {
        Chessman[][] chessmans = SaveReadUtil.readStr(
                "! @ # $ % $ # @ !\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 ^ 0 0 0 0 0 ^ 0\n" +
                        "& 0 & 0 & 0 & 0 &\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 7 0 0\n" +
                        "7 0 7 0 7 0 0 0 7\n" +
                        "0 6 0 0 0 0 0 6 0\n" +
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

    @Test
    public void evaluatePositionGameStart_gameover() {
        Chessman[][] chessmans = SaveReadUtil.readStr(
                "0 0 0 % 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 1 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "7 0 7 0 0 0 7 0 7\n" +
                        "0 6 0 0 0 0 0 6 0\n" +
                        "0 0 0 0 0 0 0 0 0\n" +
                        "0 0 3 4 5 4 3 2 1");

        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.black);

        double result = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result);
    }


    @Test
    public void testEvaluatePosition() {
        String str = "車炮象士將士象馬車\n" +
                "　　　　　　　　　\n" +
                "　砲　　　　　砲　\n" +
                "卒　卒　卒　卒　卒\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "兵　兵　兵　兵　兵\n" +
                "　　　　　　　炮　\n" +
                "　　　　　　　　　\n" +
                "俥傌相仕帥仕相傌俥";
        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.black);

        double result = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result);

        String str2 =
                "　車象士將士象馬車\n" +
                        "　　　　　　　　　\n" +
                        "　砲　　　　　砲　\n" +
                        "卒　卒　卒　卒　卒\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "兵　兵　兵　兵　兵\n" +
                        "　　　　　　　炮　\n" +
                        "　　　　　　　　　\n" +
                        "俥傌相仕帥仕相傌俥";

        Chessman[][] chessmans2 = SaveReadUtil.readChineseStr(str2);
        Chessboard chessboard2 = new Chessboard();
        chessboard2.setChessmans(chessmans2);
        chessboard2.setCurrent(GameConstant.red);

        double result2 = new EvaluateRule().evaluatePosition(chessboard2, GameConstant.red);
        System.out.println(result2);

        String str3 =
                "　車象士將士象馬車\n" +
                        "　　　　　　　　　\n" +
                        "　砲　　　　　砲　\n" +
                        "卒　卒　卒　卒　卒\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "兵　兵　兵　兵　兵\n" +
                        "　　傌　　　　炮　\n" +
                        "　　　　　　　　　\n" +
                        "俥　相仕帥仕相傌俥";

        Chessman[][] chessmans3 = SaveReadUtil.readChineseStr(str3);
        Chessboard chessboard3 = new Chessboard();
        chessboard3.setChessmans(chessmans3);
        chessboard3.setCurrent(GameConstant.black);

        double result3 = new EvaluateRule().evaluatePosition(chessboard3, GameConstant.red);
        System.out.println(result3);

        String str4 =
                "　車象士將士象馬車\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　砲　\n" +
                        "卒　卒　卒　卒　卒\n" +
                        "　砲　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "兵　兵　兵　兵　兵\n" +
                        "　　傌　　　　炮　\n" +
                        "　　　　　　　　　\n" +
                        "俥　相仕帥仕相傌俥";
        Chessman[][] chessmans4 = SaveReadUtil.readChineseStr(str4);
        Chessboard chessboard4 = new Chessboard();
        chessboard4.setChessmans(chessmans4);
        chessboard4.setCurrent(GameConstant.red);

        double result4 = new EvaluateRule().evaluatePosition(chessboard4, GameConstant.red);
        System.out.println(result4);
    }

    @Test
    public void test() {
        String str =
                "車　象士將士象　車\n" +
                "　　　　　　　　　\n" +
                "　砲　　　　馬　　\n" +
                "卒　　　卒　卒　卒\n" +
                "　馬卒　　　　　　\n" +
                "　　　　　　兵　　\n" +
                "兵　兵　兵　　　兵\n" +
                "　　傌　　　傌炮　\n" +
                "俥　　　　　　　　\n" +
                "　　相仕帥仕相　俥";
        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.red);

        double result = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result);

        String str2 =
                "　　　　將士　　　\n" +
                        "　　　　士　　　　\n" +
                        "　砲傌　象　　　象\n" +
                        "卒　　　卒　　車卒\n" +
                        "　馬卒　　　　　　\n" +
                        "　　　馬　　兵　　\n" +
                        "兵　兵　兵　　　兵\n" +
                        "　　傌炮　　　　俥\n" +
                        "　　　　　　　　　\n" +
                        "　　相仕帥仕相　　";
        Chessman[][] chessmans2 = SaveReadUtil.readChineseStr(str2);
        Chessboard chessboard2 = new Chessboard();
        chessboard2.setChessmans(chessmans2);
        chessboard2.setCurrent(GameConstant.black);

        double result2 = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result2);

        String str3 =
                "　　象　　將　　　\n" +
                        "　　　　砲　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　傌　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　馬炮　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　帥　　　　";
        Chessman[][] chessmans3 = SaveReadUtil.readChineseStr(str3);
        Chessboard chessboard3 = new Chessboard();
        chessboard3.setChessmans(chessmans3);
        chessboard3.setCurrent(GameConstant.black);

        double result3 = new EvaluateRule().evaluatePosition(chessboard, GameConstant.red);
        System.out.println(result3);
    }

    @Test
    public void test双炮擒王() {
//        String str1 =
//                    "　　　　　將　　　\n" +
//                    "　　　　　　　　　\n" +
//                    "　　　　炮　　　　\n" +
//                    "　　　　　　　　　\n" +
//                    "　　　　　　　　　\n" +
//                    "　　　　　　　　　\n" +
//                    "　　　炮　　　　　\n" +
//                    "　　　　　　　　　\n" +
//                    "　　　　　　　　　\n" +
//                    "　　　　帥　　　　";
//        Chessman[][] chessmans1 = SaveReadUtil.readChineseStr(str1);
//        Chessboard chessboard1 = new Chessboard();
//        chessboard1.setChessmans(chessmans1);
//        chessboard1.setCurrent(GameConstant.black);
//        chessboard1.setRound(50);
//
//        double result1= new EvaluateRule().evaluatePosition(chessboard1, GameConstant.black);
//        System.out.println(result1);
//
//        String str2 =
//                "　　　　　　　　　\n" +
//                "　　　　將　　　　\n" +
//                "　　　　炮　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　炮　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　帥　　　　";
//        Chessman[][] chessmans2 = SaveReadUtil.readChineseStr(str2);
//        Chessboard chessboard2 = new Chessboard();
//        chessboard2.setChessmans(chessmans2);
//        chessboard2.setCurrent(GameConstant.black);
//        chessboard2.setRound(50);
//
//        double result2 = new EvaluateRule().evaluatePosition(chessboard2, GameConstant.black);
//        System.out.println(result2);
//
//        String str3 =
//                "　　　將　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　炮　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　炮　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "　　　　帥　　　　";
//        Chessman[][] chessmans3 = SaveReadUtil.readChineseStr(str3);
//        Chessboard chessboard3 = new Chessboard();
//        chessboard3.setChessmans(chessmans3);
//        chessboard3.setCurrent(GameConstant.black);
//        chessboard3.setRound(50);
//
//        double result3 = new EvaluateRule().evaluatePosition(chessboard3, GameConstant.black);
//        System.out.println(result3);

        String str4 =
                "　　　將　　　　　\n" +
                "　　　炮　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　炮　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　　　　　　\n" +
                "　　　　帥　　　　";
        Chessman[][] chessmans4 = SaveReadUtil.readChineseStr(str4);
        Chessboard chessboard4 = new Chessboard();
        chessboard4.setChessmans(chessmans4);
        chessboard4.setCurrent(GameConstant.black);
        chessboard4.setRound(50);

        double result4 = new EvaluateRule().evaluatePosition(chessboard4, GameConstant.black);
        System.out.println(result4);
    }

    @Test
    public void test1() {
        String str =
                "　車象　將士　　　\n" +
                "　　俥　士　　　　\n" +
                "　　　　象　　　馬\n" +
                "卒　卒　炮　卒　卒\n" +
                "　　　　　　　　　\n" +
                "　　炮　　　　　　\n" +
                "兵　兵　兵　兵　兵\n" +
                "　　　　相　　　　\n" +
                "　　　車　　　　俥\n" +
                "　　相仕帥仕　傌　";
        Chessman[][] chessmans4 = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard4 = new Chessboard();
        chessboard4.setChessmans(chessmans4);
        chessboard4.setCurrent(GameConstant.black);
        chessboard4.setRound(50);

        double result4 = new EvaluateRule().evaluatePosition(chessboard4, GameConstant.red);
        System.out.println(result4);
    }

    @Test
    public void test将军() {
        String str =
                "車　象將　士象　車\n" +
                "　　　俥士　　　　\n" +
                "馬　　　　　　砲馬\n" +
                "　　卒　卒　卒　卒\n" +
                "卒　　　　　　　　\n" +
                "炮　　　　　　　　\n" +
                "兵　兵　兵　兵　兵\n" +
                "　　　　炮　　　　\n" +
                "　　　　　　　　　\n" +
                "　　相仕帥仕相傌俥";

        Chessman[][] chessmans4 = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard4 = new Chessboard();
        chessboard4.setChessmans(chessmans4);
        chessboard4.setCurrent(GameConstant.black);
        chessboard4.setRound(50);

        double result4 = new EvaluateRule().evaluatePosition(chessboard4, GameConstant.black);
        System.out.println(result4);
    }

    @Test
    public void test炮怎么不跑() {
        String str1=
                "車　　士將士　馬　\n" +
                "　俥　　　　　　　\n" +
                "　　　　象　　　象\n" +
                "卒　　　　　卒　卒\n" +
                "砲　卒　卒　　　　\n" +
                "兵　　　兵　　　　\n" +
                "　　兵　　　兵　兵\n" +
                "　炮傌　　　傌　　\n" +
                "　　　　　　　　　\n" +
                "　　相仕帥仕相　　";

        String str2 =
                "車　　士將　　馬　\n" +
                "　　　　士　　　　\n" +
                "　　　　象　　　象\n" +
                "　　　　　　卒　卒\n" +
                "卒　卒　卒　　　　\n" +
                "　　兵　　　　　　\n" +
                "兵俥　　兵　兵　兵\n" +
                "　炮傌　　　傌　　\n" +
                "　　　　　　　　　\n" +
                "　　相仕帥仕相　　";

        Chessman[][] chessmans1 = SaveReadUtil.readChineseStr(str1);
        Chessboard chessboard1 = new Chessboard();
        chessboard1.setChessmans(chessmans1);
        chessboard1.setCurrent(GameConstant.black);
        chessboard1.setRound(50);

        double result1 = new EvaluateRule().evaluatePosition(chessboard1, GameConstant.black);
        System.out.println(result1);

        Chessman[][] chessmans2 = SaveReadUtil.readChineseStr(str2);
        Chessboard chessboard2 = new Chessboard();
        chessboard2.setChessmans(chessmans2);
        chessboard2.setCurrent(GameConstant.black);
        chessboard2.setRound(50);

        double result2 = new EvaluateRule().evaluatePosition(chessboard2, GameConstant.black);
        System.out.println(result2);
    }
}