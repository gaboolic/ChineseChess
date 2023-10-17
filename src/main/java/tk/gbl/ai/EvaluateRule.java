package tk.gbl.ai;

import tk.gbl.chessmodel.*;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.model.Step;

import java.util.*;

/**
 * 局面估值
 * 子力 攻击性 位置 灵活性 控制 保护
 * 战术：捉双 闪击
 * Date: 2023-09-11
 * Time: 7:24 PM
 *
 * @author gaboolic
 */
public class EvaluateRule {
    public static final int KING_VALUE = 9999;  // 将帅的价值
    public static final int GUARD_VALUE = 210;  // 士的价值
    public static final int BISHOP_VALUE = 200;  // 象的价值
    public static final int HORSE_VALUE = 400;  // 马的价值
    public static final int ROOK_VALUE = 900;  // 车的价值
    public static final int CANNON_VALUE = 450;  // 炮的价值
    public static final int SOLDIER_VALUE = 50;  // 兵的价值
    public static final int CROSS_SOLDIER_VALUE = 150;  // 兵的价值

    public double evaluatePositionByCache(Chessboard chessboard, int color) {
//        Double cacheResult = CacheUtil.getEvaluatePosition(SaveReadUtil.outputStr(chessboard.getChessmans()), chessboard.getCurrent(), color);
//        if (cacheResult != null) {
//            return cacheResult;
//        }
        double result = evaluatePosition(chessboard, color);
//        CacheUtil.putEvaluatePosition(SaveReadUtil.outputStr(chessboard.getChessmans()), chessboard.getCurrent(), color, result);
        return result;
    }

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

        double selfValue = 0;
        double enemyValue = 0;
        // 遍历棋盘上的每个位置
        for (int y = 0; y < Chessboard.Y_SIZE; y++) {
            for (int x = 0; x < Chessboard.X_SIZE; x++) {
                Chessman chessman = chessboard.getChessman(x, y);

                double score = 0;
                if (chessman != null) {
                    // 根据棋子类型进行评估
                    int pieceValue = getPieceValue(chessman);

                    // 根据棋子的位置进行评估
                    int positionValue = PositionEvaluate.getPositionValue(chessman, chessboard);

                    //灵活度
                    double flexibleValue = getFlexibleValue(chessman, chessboard);

                    // 根据棋子的控制力进行评估
//                    int controlValue = getControlValue(chessman, chessboard);

                    // 加权求和评估值
//                    score = pieceValue + positionValue + Math.sqrt(controlValue);
//                    score = pieceValue + positionValue + flexibleValue + controlValue;
                    score = pieceValue + positionValue + flexibleValue;
                    chessman.setScore(score);
                    if (chessman.getColor() == color) {
                        selfValue += score;
                    } else {
                        enemyValue += score;
                    }
                }
            }
        }

//        double a = selfValue / (selfValue + enemyValue);
//        evaluation = a * selfValue - (1 - a) * enemyValue;
        evaluation = selfValue - enemyValue;
        return evaluation;
    }

    // 获取棋子的价值
    private int getPieceValue(Chessman chessman) {
        return chessman.getEvalValue();
    }

    private boolean eat(Chessboard chessboard, Point point) {
        List<Step> enemySteps = chessboard.generateStepsByCache(chessboard.getCurrent());
        Set<Point> enemySet = new HashSet<>();
        for (Step enemyStep : enemySteps) {
            enemySet.add(enemyStep.getEnd());
        }
        if (enemySet.contains(point)) {
            return true;
        }
        return false;
    }

    /**
     * 获取棋子的控制力评估值
     * 捉双 牵制 闪击 串打
     */
    private int getControlValue(Chessman chessman, Chessboard chessboard) {
        List<Step> enemySteps = chessboard.generateStepsByCache(chessman.getColor() ^ 1);
        Set<Point> enemySet = new HashSet<>();
        for (Step enemyStep : enemySteps) {
            enemySet.add(enemyStep.getEnd());
        }
        if (enemySet.contains(chessman.getPoint())) {
            return 0;
        }
        // 在这里计算棋子的控制力评估值
        // 可以考虑棋子的攻击范围、威胁等因素
        // 返回一个控制力评估值
        List<Point> movePoints = chessman.getMovePointsByCache(chessboard);
        int sumValue = 0;
        // 判断同时攻击两个大子
        List<Chessman> targetChessmanList = new ArrayList<>();
        for (Point point : movePoints) {
            Chessman targetChessman = chessboard.getChessman(point);
            if (targetChessman == null) {
                continue;
            }
            if (targetChessman.getColor() == chessman.getColor()) {
                continue;
            }
            if (!targetChessman.isBig()) {
                continue;
            }
//            Chessboard newChessboard = CopyUtil.makeStep(chessboard, new Step(chessman.getPoint(), point));
//            if (eat(newChessboard, point)) {
//                continue;
//            }
            targetChessmanList.add(targetChessman);
        }

        if (targetChessmanList.size() >= 2) {
            targetChessmanList.sort(new Comparator<Chessman>() {
                @Override
                public int compare(Chessman o1, Chessman o2) {
                    if (o1.getEvalValue() == o2.getEvalValue()) {
                        return 0;
                    }
                    return o1.getEvalValue() < o2.getEvalValue() ? 1 : -1;
                }
            });
            sumValue += targetChessmanList.get(1).getEvalValue();
        }

//        if (chessman instanceof Cannon) {
//            //判断炮打帅
//            for (int endY = 0; endY < Chessboard.Y_SIZE; endY++) {
//                Chessman targetChessman = chessboard.getChessman(chessman.getPoint().getX(), endY);
//                if (targetChessman != null) {
//                    if (targetChessman instanceof King && targetChessman.getColor() != chessman.getColor()) {
//                        sumValue += 100;
//                    }
//                }
//            }
//            for (int endX = 0; endX < Chessboard.X_SIZE; endX++) {
//                Chessman targetChessman = chessboard.getChessman(endX, chessman.getPoint().getY());
//                if (targetChessman != null) {
//                    if (targetChessman instanceof King && targetChessman.getColor() != chessman.getColor()) {
//                        sumValue += 100;
//                    }
//                }
//            }
//        }
        return sumValue;
    }

    /**
     * 灵活度评估
     * 可移动步数/理论最大可移动步数
     */
    private double getFlexibleValue(Chessman chessman, Chessboard chessboard) {
        if (chessman.getEvalValue() < 300) {
            return 0;
        }
        List<Point> movePoints = chessman.getMovePointsByCache(chessboard);

        double f = 0;
        if (chessman instanceof Cannon || chessman instanceof Rook) {
            f = movePoints.size() / 19.0;
        }
        if (chessman instanceof Horse) {
            f = movePoints.size() / 8.0;
        }
        if (chessman instanceof King) {
            if (movePoints.size() == 0) {
                return -100;
            }
        }

        return 10 * f;
    }

}
