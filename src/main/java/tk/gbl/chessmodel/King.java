package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 将帅
 * <p>
 * Date: 2017/11/15
 * Time: 15:32
 *
 * @author gaboolic
 */
public class King extends Chessman {
    @Override
    public String getChineseName() {
        if (getColor() == GameConstant.red) {
            return "帅";
        }
        return "将";
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        List<Point> movePoints = new ArrayList<>();
        int startX = getPoint().getX();
        int startY = getPoint().getY();
        int[][] kingMoves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 帅的四个直线方向

        for (int[] kingMove : kingMoves) {
            int dx = kingMove[0];
            int dy = kingMove[1];
            int targetX = startX + dx;
            int targetY = startY + dy;

            if (isValidMove(targetX, targetY, chessboard)) {
                movePoints.add(new Point(targetX, targetY));
            }
        }

        return movePoints;
    }

    private boolean isValidMove(int x, int y, Chessboard chessboard) {
        // 判断目标位置是否在九宫格内
        if (!isInPalace(x, y)) {
            return false;
        }

        // 判断目标位置是否为空或者有敌方棋子
        Chessman targetChessman = chessboard.getChessman(x, y);
        if (targetChessman == null || targetChessman.getColor() != getColor()) {
            return true;
        }

        return false;
    }

    private boolean isInPalace(int x, int y) {
        if (getColor() == GameConstant.red) {
            return x >= 3 && x <= 5 && y >= 7 && y <= 9;
        } else {
            return x >= 3 && x <= 5 && y >= 0 && y <= 2;
        }
    }

}
