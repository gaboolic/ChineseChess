package tk.gbl.ai;

import tk.gbl.chessmodel.*;
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

    public int evaluatePosition(Chessboard chessboard) {
        int evaluation = 0;

        // 遍历棋盘上的每个位置
        for (int x = 0; x < Chessboard.X_SIZE; x++) {
            for (int y = 0; y < Chessboard.Y_SIZE; y++) {
                Chessman chessman = chessboard.getChessman(x, y);
                if (chessman != null) {
                    // 根据棋子类型进行评估
                    int pieceValue = getPieceValue(chessman);

                    // 根据棋子的控制力进行评估
                    int controlValue = getControlValue(chessman, chessboard);

                    // 根据棋子的位置进行评估
                    int positionValue = getPositionValue(chessman, chessboard);

                    // 加权求和评估值
                    evaluation += pieceValue + controlValue + positionValue;
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
            if (targetChessman != null) {
                sumValue += targetChessman.getEvalValue();
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
            int sumValue = movePoints.size() * 5;
            return sumValue;
        }
        if (chessman instanceof Horse) {
            List<Point> movePoints = chessman.getMovePoints(chessboard);
            int sumValue = movePoints.size() * 20;
            return sumValue;
        }

        if (chessman instanceof Guard || chessman instanceof King) {
            //士在九宫格位置中心时位置评估值最高
            if (chessman.getPoint().getX() == 5) {
                return 50;
            }
        }

        return 0;
    }
}
