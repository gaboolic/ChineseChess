package tk.gbl.chessmodel;

import tk.gbl.ai.EvaluateRule;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 卒
 * <p>
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Pawn extends Chessman {
    @Override
    public String getChineseName() {
        if (getColor() == GameConstant.red) {
            return "兵";
        }
        return "卒";
    }

    @Override
    public int getEvalValue() {
        if (crossedRiver()) {
            return EvaluateRule.CROSS_SOLDIER_VALUE;
        }
        return EvaluateRule.SOLDIER_VALUE;
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        List<Point> movePoints = new ArrayList<>();
        int startX = getPoint().getX();
        int startY = getPoint().getY();
        int forwardDirection = getColor() == GameConstant.red ? -1 : 1; // 兵的前进方向

        // 判断兵是否可以前进一步
        int targetX = startX;
        int targetY = startY + forwardDirection;
        if (isValidMove(targetX, targetY, chessboard)) {
            movePoints.add(new Point(targetX, targetY));
        }

        // 判断兵是否过河

        // 如果兵过河了，则判断是否可以左右移动
        if (crossedRiver()) {
            if (isValidMove(startX - 1, startY, chessboard)) {
                movePoints.add(new Point(startX - 1, startY));
            }
            if (isValidMove(startX + 1, startY, chessboard)) {
                movePoints.add(new Point(startX + 1, startY));
            }
        }

        return movePoints;
    }

    private boolean crossedRiver() {
        int startY = getPoint().getY();
        boolean isCrossedRiver = getColor() == GameConstant.black ? startY >= 5 : startY <= 4;
        return isCrossedRiver;
    }

    private boolean isValidMove(int x, int y, Chessboard chessboard) {
        // 判断目标位置是否在棋盘范围内
        if (!chessboard.isInsideBoard(x, y)) {
            return false;
        }

        // 判断目标位置是否为空或者有敌方棋子
        Chessman targetChessman = chessboard.getChessman(x, y);
        if (targetChessman == null || targetChessman.getColor() != getColor()) {
            return true;
        }

        return false;
    }
}
