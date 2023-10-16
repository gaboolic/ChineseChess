package tk.gbl.chessmodel;

import tk.gbl.ai.EvaluateRule;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 相
 * <p/>
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Bishop extends Chessman {
    @Override
    public String getChineseName() {
        if (getColor() == GameConstant.red) {
            return "相";
        }
        return "象";
    }

    @Override
    public boolean canRemove(Chessboard chessboard, Point target) {
        if (getMovePoints(chessboard).contains(target)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        List<Point> movePoints = new ArrayList<>();
        int startX = getPoint().getX();
        int startY = getPoint().getY();
        int[][] directions = {{-2, -2}, {-2, 2}, {2, -2}, {2, 2}}; // 四个斜线方向

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int targetX = startX + dx;
            int targetY = startY + dy;

            if (isValidMove(targetX, targetY, chessboard)) {
                movePoints.add(new Point(targetX, targetY));
            }
        }

        return movePoints;
    }

    @Override
    public int getEvalValue() {
        return EvaluateRule.BISHOP_VALUE;
    }

    private boolean isValidMove(int x, int y, Chessboard chessboard) {
        // 判断目标位置是否在棋盘范围内
        if (!chessboard.isInsideBoard(x, y)) {
            return false;
        }
        if (super.judgeKingFace(chessboard)) {
            return false;
        }

        boolean isCrossedRiver = getColor() == GameConstant.black ? y >= 5 : y <= 4;
        if (isCrossedRiver) {
            return false;
        }

        // 判断目标位置是否为空或者有敌方棋子
        Chessman targetChessman = chessboard.getChessman(new Point(x, y));
        if (targetChessman != null && targetChessman.getColor() == getColor()) {
            return false;
        }
        // 判断是否堵象眼
        int startX = getPoint().getX();
        int startY = getPoint().getY();
        int midX = (startX + x) / 2;
        int midY = (startY + y) / 2;

        Chessman middleChessman = chessboard.getChessman(new Point(midX, midY));
        return middleChessman == null;
    }

}
