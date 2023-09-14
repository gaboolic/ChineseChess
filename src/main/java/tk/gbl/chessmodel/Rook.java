package tk.gbl.chessmodel;

import tk.gbl.ai.EvaluateRule;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 车
 * <p>
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Rook extends Chessman {

    @Override
    public String getChineseName() {
        if (getColor() == GameConstant.red) {
            return "俥";
        }
        return "車";
    }

    @Override
    public int getEvalValue() {
        return EvaluateRule.ROOK_VALUE;
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        List<Point> movePoints = new ArrayList<>();
        int startX = getPoint().getX();
        int startY = getPoint().getY();

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 四个直线方向

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int targetX = startX + dx;
            int targetY = startY + dy;

            while (chessboard.isInsideBoard(targetX, targetY)) {
                Chessman targetChessman = chessboard.getChessman(targetX, targetY);

                if (targetChessman == null) {
                    // 空位置，加入移动点
                    movePoints.add(new Point(targetX, targetY));
                } else {
                    Chessman nextChessman = chessboard.getChessman(targetX, targetY);

                    if (nextChessman != null) {
                        // 遇到第二个棋子，若颜色不同则加入吃子点
                        if (nextChessman.getColor() != getColor()) {
                            movePoints.add(new Point(targetX, targetY));
                        }
                    }
                    break;
                }

                targetX += dx;
                targetY += dy;
            }
        }

        return movePoints;
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
