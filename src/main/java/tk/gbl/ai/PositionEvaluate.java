package tk.gbl.ai;

import tk.gbl.chessmodel.*;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.List;

/**
 * Date: 2023-09-22
 * Time: 3:33 PM
 *
 * @author gabooilc
 */
public class PositionEvaluate {

    // 获取棋子的位置评估值
    public int getPositionValue(Chessman chessman, Chessboard chessboard) {
        // 在这里计算棋子的位置评估值
        // 可以考虑棋子的位置优势、威胁等因素
        // 返回一个位置评估值
        if (chessman instanceof Rook) {
            List<Point> movePoints = chessman.getMovePoints(chessboard);
            int sumValue = movePoints.size() * 1;

            if (chessman.getPoint().getX() == 4 && chessman.getPoint().getY() == 1) {
                sumValue += 50;
            }
            if (chessman.getPoint().getX() == 4 && chessman.getPoint().getY() == 8) {
                sumValue += 50;
            }
            return sumValue;
        }
        if (chessman instanceof Horse) {
            List<Point> movePoints = chessman.getMovePoints(chessboard);
            int sumValue = movePoints.size() * 10;

            //卧槽马
            if (chessman.getPoint().getX() == 2 && (chessman.getPoint().getY() == 1 || chessman.getPoint().getY() == 8)) {
                sumValue += 50;
            }
            if (chessman.getPoint().getX() == 6 && (chessman.getPoint().getY() == 1 || chessman.getPoint().getY() == 8)) {
                sumValue += 50;
            }

            //八角马
            if (chessman.getPoint().getX() == 3 && (chessman.getPoint().getY() == 2 || chessman.getPoint().getY() == 7)) {
                sumValue += 50;
            }
            if (chessman.getPoint().getX() == 5 && (chessman.getPoint().getY() == 2 || chessman.getPoint().getY() == 7)) {
                sumValue += 50;
            }
            return sumValue;
        }

        if (chessman instanceof Cannon) {
            //天地炮
            if (chessman.getPoint().getX() == 4) {
                return 50;
            }
            if (chessman.getColor() == GameConstant.red && chessman.getPoint().getY() == 0) {
                return 50;
            }
            if (chessman.getColor() == GameConstant.black && chessman.getPoint().getY() == 9) {
                return 50;
            }
        }

        if (chessman instanceof Guard || chessman instanceof King) {
            //士在九宫格位置中心时位置评估值最高
            if (chessman.getPoint().getX() == 4) {
                return 50;
            }
        }
        if (chessman instanceof Pawn) {
            List<Point> movePoints = chessman.getMovePoints(chessboard);
            int sumValue = movePoints.size() * 10;

            if (chessman.getPoint().getX() == 3 || chessman.getPoint().getX() == 5) {
                sumValue += 30;
            }
            if (chessman.getPoint().getY() == 1 || chessman.getPoint().getY() == 8) {
                sumValue += 30;
            }
            if (chessman.getPoint().getX() == 4 && chessman.getPoint().getY() == 1) {
                sumValue += 50;
            }
            if (chessman.getPoint().getX() == 4 && chessman.getPoint().getY() == 8) {
                sumValue += 50;
            }
            return sumValue;
        }

        return 0;
    }
}
