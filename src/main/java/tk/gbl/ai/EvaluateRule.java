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
    public static final int HORSE_VALUE = 300;  // 马的价值
    public static final int ROOK_VALUE = 900;  // 车的价值
    public static final int CANNON_VALUE = 500;  // 炮的价值
    public static final int SOLDIER_VALUE = 100;  // 兵的价值
    public static final int CROSS_SOLDIER_VALUE = 200;  // 兵的价值

    public double evaluatePosition(Chessboard chessboard, int color) {
        int gameOver = chessboard.isGameOver();
        if (gameOver >= 0) {
            if (gameOver == color) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
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
                    int positionValue = getPositionValue(chessman, chessboard);

                    // 加权求和评估值
                    score = pieceValue + 0.5 * controlValue + Math.sqrt(positionValue);
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
            //todo 保护力
            if (targetChessman != null && targetChessman.getColor() == chessman.getColor()) {
                sumValue += 0.5 * targetChessman.getEvalValue();
            }
        }
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

    // 获取棋子的位置评估值
    private int getPositionValue(Chessman chessman, Chessboard chessboard) {
        // 在这里计算棋子的位置评估值
        // 可以考虑棋子的位置优势、威胁等因素
        // 返回一个位置评估值
        if (chessman instanceof Rook) {
            List<Point> movePoints = chessman.getMovePoints(chessboard);
            int sumValue = movePoints.size() * 1;

//            if (chessman.getPoint().getX() == 3 || chessman.getPoint().getX() == 5) {
//                sumValue += 20;
//            }
//            if (chessman.getPoint().getY() == 1 || chessman.getPoint().getY() == 8) {
//                sumValue += 20;
//            }
            return sumValue;
        }
        if (chessman instanceof Horse) {
            List<Point> movePoints = chessman.getMovePoints(chessboard);
            int sumValue = movePoints.size() * 10;
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
            return sumValue;
        }

        return 0;
    }
}
