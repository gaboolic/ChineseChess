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
    private static final int MAX_DEPTH = 6; // 最大搜索深度

    // Alpha-Beta搜索函数
    public Step alphaBetaSearch(Chessboard chessboard) {
        long startTime = System.currentTimeMillis();

        int color = chessboard.getCurrent();
        int depth = 0;
        double alpha = Integer.MIN_VALUE;
        double beta = Integer.MAX_VALUE;

        // 获取当前可行的移动
        List<Step> steps = generateSteps(chessboard);
//        List<Step> steps = new ArrayList<>();
//
//        if (chessboard.getRound() == 0) {
//            steps.add(steps_old.get(steps_old.size() - 1));
//        } else {
//            steps = steps_old;
//        }

        Step bestStep = null;
        double maxScore = Integer.MIN_VALUE;
        int maxDepth = getMaxDepth(chessboard);

        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            double score = minValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);

//            System.out.println(step + "---- " + score);
            if (score > maxScore) {
                maxScore = score;
                bestStep = step;
            }

            alpha = Math.max(alpha, maxScore);

            if (maxScore >= beta) {
                break; // Beta剪枝
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
        System.out.println("color:" + color + " bestStep:" + bestStep + " maxScore:" + maxScore);
        return bestStep;
    }

    private int getMaxDepth(Chessboard newChessboard) {
        int maxDepth = MAX_DEPTH;
//        if (newChessboard.getSituation().equals(SituationEnum.START)) {
//            maxDepth = 3;
//        } else if (newChessboard.getSituation().equals(SituationEnum.ENDING)) {
//            maxDepth = 5;
//        }
//        System.out.println("getMaxDepth:" + maxDepth);
        return maxDepth;
    }

    // 极大层级
    private double maxValue(int color, Chessboard chessboard, int depth, double alpha, double beta, int maxDepth) {
        if (depth >= maxDepth) {
            return evaluate(chessboard, color);
        }
        //todo
        int gameOver = chessboard.isGameOver();
        if (gameOver >= 0) {
            if(gameOver == color){
                return 99999;
            } else {
                return -99999;
            }
        }

        double maxScore = Integer.MIN_VALUE;

        List<Step> steps = generateSteps(chessboard);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            double score = minValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);

            maxScore = Math.max(maxScore, score);

            alpha = Math.max(alpha, maxScore);

            if (maxScore >= beta) {
                break; // Beta剪枝
            }
        }

        return maxScore;
    }

    // 极小层级
    private double minValue(int color, Chessboard chessboard, int depth, double alpha, double beta, int maxDepth) {
        if (depth >= maxDepth) {
            return evaluate(chessboard, color);
        }
        //todo
        int gameOver = chessboard.isGameOver();
        if (gameOver >= 0) {
            if(gameOver == color){
                return 99999;
            } else {
                return -99999;
            }
        }

        double minScore = Integer.MAX_VALUE;

        List<Step> steps = generateSteps(chessboard);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            double score = maxValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);

            minScore = Math.min(minScore, score);

            beta = Math.min(beta, minScore);

            if (minScore <= alpha) {
                break; // Alpha剪枝
            }
        }

        return minScore;
    }

    // 评估函数，用于评估当前棋局的得分
    private double evaluate(Chessboard chessboard, int color) {
        // 在这里计算当前棋局的得分并返回
        return new EvaluateRule().evaluatePosition(chessboard, color);
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