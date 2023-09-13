package tk.gbl.ai;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.model.Step;
import tk.gbl.util.CopyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * αβ搜索
 * Date: 2023-09-11
 * Time: 8:14 PM
 *
 * @author gaboolic
 */
public class AlphaBetaSearch {
    private static final int MAX_DEPTH = 4; // 最大搜索深度

    // Alpha-Beta搜索函数
    public Step alphaBetaSearch(Chessboard chessboard) {
        int depth = 0;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        // 获取当前可行的移动
        List<Step> Steps = generateSteps(chessboard);

        Step bestStep = null;
        int maxScore = Integer.MIN_VALUE;

        for (Step step : Steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            int score = minValue(newChessboard, depth + 1, alpha, beta);

            if (score > maxScore) {
                maxScore = score;
                bestStep = step;
            }

            alpha = Math.max(alpha, maxScore);

            if (maxScore >= beta) {
                break; // Beta剪枝
            }
        }

        return bestStep;
    }

    // 极大层级
    private int maxValue(Chessboard chessboard, int depth, int alpha, int beta) {
        if (depth >= MAX_DEPTH || chessboard.isGameOver()) {
            return evaluate(chessboard);
        }

        int maxScore = Integer.MIN_VALUE;

        List<Step> steps = generateSteps(chessboard);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            int score = minValue(newChessboard, depth + 1, alpha, beta);

            maxScore = Math.max(maxScore, score);

            alpha = Math.max(alpha, maxScore);

            if (maxScore >= beta) {
                break; // Beta剪枝
            }
        }

        return maxScore;
    }

    // 极小层级
    private int minValue(Chessboard chessboard, int depth, int alpha, int beta) {
        if (depth >= MAX_DEPTH || chessboard.isGameOver()) {
            return evaluate(chessboard);
        }

        int minScore = Integer.MAX_VALUE;

        List<Step> steps = generateSteps(chessboard);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            int score = maxValue(newChessboard, depth + 1, alpha, beta);

            minScore = Math.min(minScore, score);

            beta = Math.min(beta, minScore);

            if (minScore <= alpha) {
                break; // Alpha剪枝
            }
        }

        return minScore;
    }

    // 评估函数，用于评估当前棋局的得分
    private int evaluate(Chessboard chessboard) {
        // 在这里计算当前棋局的得分并返回
        return new EvaluateRule().evaluatePosition(chessboard);
    }

    // 生成当前棋局的所有合法移动
    private List<Step> generateSteps(Chessboard chessboard) {
        // 在这里生成当前棋局的合法移动列表
        List<Step> stepList = new ArrayList<>();
        Chessman[][] chessmans = chessboard.getChessmans();
        for (Chessman[] list : chessmans) {
            for (Chessman chessman : list) {
                if (chessman == null) {
                    continue;
                }
                if (chessboard.getCurrent() != chessman.getColor()) {
                    continue;
                }
                List<Point> movePoints = chessman.getMovePoints(chessboard);
                for (Point to : movePoints) {
                    Step step = new Step();
                    step.setStart(chessman.getPoint());
                    step.setEnd(to);
                    stepList.add(step);
                }
            }
        }
        return stepList;
    }
}