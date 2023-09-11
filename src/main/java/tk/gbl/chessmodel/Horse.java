package tk.gbl.chessmodel;

import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 马
 *
 * Date: 2017/11/15
 * Time: 15:25
 *
 * @author gaboolic
 */
public class Horse extends Chessman {
    @Override
    public String getChineseName() {
        return "马";
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        List<Point> movePoints = new ArrayList<>();
        int startX = getPoint().getX();
        int startY = getPoint().getY();
        int[][] knightMoves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}}; // 马的八个跳跃方向

        for (int[] knightMove : knightMoves) {
            int dx = knightMove[0];
            int dy = knightMove[1];
            int targetX = startX + dx;
            int targetY = startY + dy;

            if (isValidMove(targetX, targetY, chessboard)) {
                movePoints.add(new Point(targetX, targetY));
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
            // 判断是否有拌马腿的情况
            int blockX = x - (x - getPoint().getX()) / 2; // 马腿所在位置的x坐标
            int blockY = y - (y - getPoint().getY()) / 2; // 马腿所在位置的y坐标

            if (chessboard.getChessman(blockX, blockY) == null) {
                return true;
            }
        }

        return false;
    }

}
