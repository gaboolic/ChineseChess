package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 士
 * <p>
 * Date: 2017/11/15
 * Time: 15:33
 *
 * @author gaboolic
 */
public class Guard extends Chessman {

    @Override
    public String getChineseName() {
        if (getColor() == GameConstant.red) {
            return "仕";
        }
        return "士";
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        List<Point> movePoints = new ArrayList<>();
        int startX = getPoint().getX();
        int startY = getPoint().getY();
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; // 四个斜线方向

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
