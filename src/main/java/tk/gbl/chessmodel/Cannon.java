package tk.gbl.chessmodel;

import tk.gbl.ai.EvaluateRule;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 炮
 * <p>
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Cannon extends Chessman {
    @Override
    public String getChineseName() {
        if (getColor() == GameConstant.red) {
            return "炮";
        }
        return "砲";
    }

    @Override
    public int getEvalValue() {
        return EvaluateRule.CANNON_VALUE;
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

            if (targetX != startX) {
                if (super.judgeKingFace(chessboard)) {
                    continue;
                }
            }

            while (chessboard.isInsideBoard(targetX, targetY)) {
                Chessman targetChessman = chessboard.getChessman(targetX, targetY);

                if (targetChessman == null) {
                    // 空位置，加入移动点
                    movePoints.add(new Point(targetX, targetY));
                } else {
                    // 遇到第一个棋子，跳过
                    targetX += dx;
                    targetY += dy;

                    while (chessboard.isInsideBoard(targetX, targetY)) {
                        Chessman nextChessman = chessboard.getChessman(targetX, targetY);

                        if (nextChessman != null) {
                            // 遇到第二个棋子，若颜色不同则加入吃子点
                            if (nextChessman.getColor() != getColor()) {
                                movePoints.add(new Point(targetX, targetY));
                            }
                            break;
                        }

                        targetX += dx;
                        targetY += dy;
                    }

                    break;
                }

                targetX += dx;
                targetY += dy;
            }
        }

        return movePoints;
    }

}
