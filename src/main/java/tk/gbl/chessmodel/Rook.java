package tk.gbl.chessmodel;

import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 车
 *
 * Date: 2017/11/15
 * Time: 15:36
 *
 * @author gaboolic
 */
public class Rook extends Chessman {

    @Override
    public String getChineseName() {
        if(getColor() == GameConstant.red) {
            return "俥";
        }
        return "車";
    }

    @Override
    public List<Point> getMovePoints(Chessboard chessboard) {
        List<Point> movePoints = new ArrayList<>();
        int startX = getPoint().getX();
        int startY = getPoint().getY();

        // 向上移动
        for (int x = startX - 1; x >= 0; x--) {
            if (isValidMove(x, startY, chessboard)) {
                movePoints.add(new Point(x, startY));
            } else {
                break; // 遇到障碍停止
            }
        }

        // 向下移动
        for (int x = startX + 1; x < 10; x++) {
            if (isValidMove(x, startY, chessboard)) {
                movePoints.add(new Point(x, startY));
            } else {
                break; // 遇到障碍停止
            }
        }

        // 向左移动
        for (int y = startY - 1; y >= 0; y--) {
            if (isValidMove(startX, y, chessboard)) {
                movePoints.add(new Point(startX, y));
            } else {
                break; // 遇到障碍停止
            }
        }

        // 向右移动
        for (int y = startY + 1; y < 11; y++) {
            if (isValidMove(startX, y, chessboard)) {
                movePoints.add(new Point(startX, y));
            } else {
                break; // 遇到障碍停止
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
