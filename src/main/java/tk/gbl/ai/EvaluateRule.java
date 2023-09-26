package tk.gbl.ai;

import tk.gbl.chessmodel.*;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;

import java.util.List;

/**
 * 局面估值
 * Date: 2023-09-11
 * Time: 7:24 PM
 *
 * @author gaboolic
 */
public class EvaluateRule {
    public static final int KING_VALUE = 99999;  // 将帅的价值
    public static final int GUARD_VALUE = 260;  // 士的价值
    public static final int BISHOP_VALUE = 250;  // 象的价值
    public static final int HORSE_VALUE = 500;  // 马的价值
    public static final int ROOK_VALUE = 1000;  // 车的价值
    public static final int CANNON_VALUE = 600;  // 炮的价值
    public static final int SOLDIER_VALUE = 50;  // 兵的价值
    public static final int CROSS_SOLDIER_VALUE = 150;  // 兵的价值

    public double evaluatePosition(Chessboard chessboard, int color) {
        int gameOver = chessboard.isGameOver();
        if (gameOver >= 0) {
            if (gameOver == color) {
                return 999999;
            } else {
                return -999999;
            }
        }
        double evaluation = 0;

        // 遍历棋盘上的每个位置
        for (int x = 0; x < Chessboard.X_SIZE; x++) {
            for (int y = 0; y < Chessboard.Y_SIZE; y++) {
                Chessman chessman = chessboard.getChessman(x, y);

                double score = 0;
                if (chessman != null) {
                    // 根据棋子类型进行评估
                    int pieceValue = getPieceValue(chessman);
                    // 根据棋子的控制力进行评估
                    int controlValue = getControlValue(chessman, chessboard);
                    // 根据棋子的位置进行评估
                    int positionValue = PositionEvaluate.getPositionValue(chessman, chessboard);

                    // 加权求和评估值
                    score = pieceValue + positionValue + Math.sqrt(controlValue);
                    if (chessman.getColor() == color) {
                        evaluation += score;
                    } else {
                        evaluation -= score;
                    }
//                    System.out.println("evaluatePosition " + chessman.toString() + "---" + score);
                }
            }
        }

        return evaluation;
    }

    // 获取棋子的价值
    private int getPieceValue(Chessman chessman) {
        return chessman.getEvalValue();
    }

    // 获取棋子的控制力评估值
    private int getControlValue(Chessman chessman, Chessboard chessboard) {
        // 在这里计算棋子的控制力评估值
        // 可以考虑棋子的攻击范围、威胁等因素
        // 返回一个控制力评估值
        List<Point> movePoints = chessman.getMovePoints(chessboard);
        int sumValue = 0;
        for (Point point : movePoints) {
            Chessman targetChessman = chessboard.getChessman(point);
            //威胁力
            if (targetChessman != null && targetChessman.getColor() != chessman.getColor()) {
                sumValue += targetChessman.getEvalValue();
            }
        }
        //todo 保护力

        if (chessman instanceof Cannon) {
            //判断炮打帅
            for (int endY = 0; endY < Chessboard.Y_SIZE; endY++) {
                Chessman targetChessman = chessboard.getChessman(chessman.getPoint().getX(), endY);
                if (targetChessman != null) {
                    if (targetChessman instanceof King && targetChessman.getColor() != chessman.getColor()) {
                        sumValue += 100;
                    }
                }
            }
            for (int endX = 0; endX < Chessboard.X_SIZE; endX++) {
                Chessman targetChessman = chessboard.getChessman(endX, chessman.getPoint().getY());
                if (targetChessman != null) {
                    if (targetChessman instanceof King && targetChessman.getColor() != chessman.getColor()) {
                        sumValue += 100;
                    }
                }
            }
        }

        return sumValue;
    }

}
