package tk.gbl.util;

import tk.gbl.ai.EvaluateRule;
import tk.gbl.chessmodel.Bishop;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.chessmodel.Guard;
import tk.gbl.chessmodel.Horse;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.model.Step;

import java.util.LinkedList;

/**
 * Date: 2023-09-26
 * Time: 4:30 PM
 *
 * @author gaboolic
 */
public class ShowStepUtil {

    /**
     * 马、象、士
     */
    public static String showStep(Step step, Chessboard chessboard) {
        Point start = step.getStart();
        Point end = step.getEnd();
        Chessman chessman = chessboard.getChessman(step.getStart());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chessman.getChineseName());
        stringBuilder.append(getFlatEndNumber(chessman, start.getX()));
        if (start.getY() == end.getY()) {
            stringBuilder.append("平");
            stringBuilder.append(getFlatEndNumber(chessman, end.getX()));
        } else {
            stringBuilder.append(getAdvance(chessman, start, end));
            stringBuilder.append(getVerticalEndNumber(chessman, Math.abs(end.getY() - start.getY()), end));
        }
        return stringBuilder.toString();
    }

    public static void showFlowSteps(LinkedList<Step> flowSteps, Chessboard chessboard) {
        int color = chessboard.getCurrent();
        EvaluateRule evaluateRule = new EvaluateRule();
        Chessboard next = chessboard;
        while (!flowSteps.isEmpty()) {
            Step step = flowSteps.pop();

            System.out.println(step);
            System.out.println(next.getChessman(step.getStart()) + "---" + next.getChessman(step.getEnd()));
            System.out.println(ShowStepUtil.showStep(step, next));
            next = CopyUtil.makeStep(next, step);
            System.out.println(SaveReadUtil.outputStr(next.getChessmans()));

            System.out.println("估值：" + evaluateRule.evaluatePosition(next, color));
        }
    }

    public static String numberToChinese(int number) {
        String[] chineseNums = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        return chineseNums[number];
    }

    private static String getFlatEndNumber(Chessman chessman, int x) {
        if (chessman.getColor() == GameConstant.red) {
            return numberToChinese(Chessboard.X_SIZE - x - 1);
        } else {
            return String.valueOf(x + 1);
        }
    }

    private static String getVerticalEndNumber(Chessman chessman, int y, Point end) {
        if (chessman instanceof Horse || chessman instanceof Guard || chessman instanceof Bishop) {
            return getFlatEndNumber(chessman, end.getX());
        }
        if (chessman.getColor() == GameConstant.red) {
            return numberToChinese(y - 1);
        } else {
            return String.valueOf(y);
        }
    }

    private static String getAdvance(Chessman chessman, Point start, Point end) {
        if (start.getY() < end.getY()) {
            return chessman.getColor() == GameConstant.black ? "进" : "退";
        } else {
            return chessman.getColor() == GameConstant.black ? "退" : "进";
        }
    }
}
