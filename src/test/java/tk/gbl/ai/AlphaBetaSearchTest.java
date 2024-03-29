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
        System.out.println("from " + from);
        System.out.println("to " + to);

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

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
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

    @Test
    public void test将军() {
        //
        String str =
                "　　　士將士象　　\n" +
                        "　　　　　　　車　\n" +
                        "　　馬　　　馬　　\n" +
                        "卒　傌　卒　卒　卒\n" +
                        "　　俥　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "兵　　　車　　　兵\n" +
                        "　　　　　　　　相\n" +
                        "　　　　仕　　　　\n" +
                        "　俥相仕帥　　　　";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.red);
        chessboard.setRound(50);
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

//        Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
//
//        System.out.println(step);
//
//        System.out.println(chessboard.getChessman(step.getStart()) + "---" + chessboard.getChessman(step.getEnd()));
//        System.out.println(ShowStepUtil.showStep(step, chessboard));
//        chessboard = CopyUtil.makeStep(chessboard, step);
//
//        String result = SaveReadUtil.outputStr(chessboard.getChessmans());
//        System.out.println(result);
    }

    @Test
    public void test送炮() {
        String str = "車　象　將士象　車\n" +
                "　　　　士　　　　\n" +
                "馬　　　　　　砲馬\n" +
                "卒　卒　卒　卒　卒\n" +
                "　　　　　　　　　\n" +
                "炮　　　　　　　　\n" +
                "兵　兵　兵　兵　兵\n" +
                "　　　　炮　　　　\n" +
                "　　　　　　　　　\n" +
                "　俥相仕帥仕相傌俥";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.black);
        chessboard.setRound(50);
        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 1; i++) {
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
    public void test为什么炮不跑() {
        //todo
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
        chessboard.setCurrent(GameConstant.black);
        chessboard.setRound(50);
        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 1; i++) {
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
    public void test残局_卒() {
        String str =
                "　　　　車將　　　\n" +
                        "　　　兵　　　　　\n" +
                        "　　　　象兵　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　　　　　　\n" +
                        "　　　卒炮卒　　　\n" +
                        "　　　　帥　俥俥　";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.black);
        chessboard.setRound(50);
        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 1; i++) {
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
    public void test为什么車不吃炮() {
        String str =
                "車馬象士將士象車　\n" +
                        "　　　　　　　　　\n" +
                        "　　　　砲　馬　　\n" +
                        "卒　卒　卒　卒　卒\n" +
                        "　　　　　　砲　　\n" +
                        "　　　　　　炮　　\n" +
                        "兵　兵　兵傌兵　兵\n" +
                        "　　　　　　　炮　\n" +
                        "　　　　　　　　　\n" +
                        "俥傌相仕帥仕相　俥";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.black);
        chessboard.setRound(50);
        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 1; i++) {
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
    public void test为什么俥不吃砲() {
        //
        String str =
                "　　象　將士象　炮\n" +
                "　　　　士　　　　\n" +
                "　砲　　俥　　砲　\n" +
                "卒　卒　炮　卒　卒\n" +
                "　　　　　　　　　\n" +
                "　　　　　　馬　　\n" +
                "兵　兵　兵　　　兵\n" +
                "　　傌　　　　　車\n" +
                "　　　　仕　　　　\n" +
                "　　相　帥仕相　　\n";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.red);
        chessboard.setRound(50);
        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 1; i++) {
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
    public void test为什么俥不吃士() {
        //
        String str =
                "　　象　將士象　炮\n" +
                "　　　　　　　　　\n" +
                "　俥　士　　　砲　\n" +
                "卒　卒　炮　卒　卒\n" +
                "　　　　　　　　　\n" +
                "　　　　　　馬　　\n" +
                "兵　兵　兵　　　兵\n" +
                "　　傌　　　　　車\n" +
                "　　　　仕　　　　\n" +
                "　　相　帥仕相　　";
        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.red);
        chessboard.setRound(50);
        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 1; i++) {
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
    public void test为什么馬5进6不吃炮() {
        //todo
        String str =
                "　　象士將士象　炮\n" +
                "　　　　馬　　砲　\n" +
                "　砲　　　　馬　　\n" +
                "卒　卒　卒　卒　卒\n" +
                "　　　俥　　　　　\n" +
                "　　　　　　　　　\n" +
                "兵　兵　兵　　　兵\n" +
                "　　傌　　　車炮　\n" +
                "　　　　　　　　　\n" +
                "　　相仕帥仕相　俥\n";

        //馬5进6后
//        String str2 = "　　象士將士象　炮\n" +
//                "　　　　　　　砲　\n" +
//                "　砲　　　　馬　　\n" +
//                "卒　卒　卒馬卒　卒\n" +
//                "　　　俥　　　　　\n" +
//                "　　　　　　　　　\n" +
//                "兵　兵　兵　　　兵\n" +
//                "　　傌　　　車炮　\n" +
//                "　　　　　　　　　\n" +
//                "　　相仕帥仕相　俥";

        Chessman[][] chessmans = SaveReadUtil.readChineseStr(str);
        Chessboard chessboard = new Chessboard();
        chessboard.setChessmans(chessmans);
        chessboard.setCurrent(GameConstant.black);
        chessboard.setRound(50);
        AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();

        for (int i = 0; i < 1; i++) {
            Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
            System.out.println(step);
            System.out.println("step评分 " + step.getEvaluateValue() + "   " + step.getScoreDepth().getScore());
            System.out.println(chessboard.getChessman(step.getStart()) + "---" + chessboard.getChessman(step.getEnd()));
            System.out.println(ShowStepUtil.showStep(step, chessboard));
            chessboard = CopyUtil.makeStep(chessboard, step);

            String result = SaveReadUtil.outputStr(chessboard.getChessmans());
            System.out.println(result);
        }
    }

}