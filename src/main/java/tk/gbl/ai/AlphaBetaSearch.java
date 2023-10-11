package tk.gbl.ai;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.SituationEnum;
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
        long startTime = System.currentTimeMillis();

        int color = chessboard.getCurrent();
        int depth = 0;
        double alpha = Integer.MIN_VALUE;
        double beta = Integer.MAX_VALUE;
        int maxDepth = getMaxDepth(chessboard);

        // 获取当前可行的移动
        List<Step> steps = generateSteps(chessboard);
//        List<Step> steps = new ArrayList<>();
//
//        if (chessboard.getRound() == 0) {
//            steps.add(steps_old.get(steps_old.size() - 1));
//        } else {
//            steps = steps_old;
//        }

        List<Step> bestSteps = new ArrayList<>();
        double maxScore = Integer.MIN_VALUE;
        int minSearchDepth = Integer.MIN_VALUE;

        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            ScoreDepth scoreDepth = minValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);
            step.setScoreDepth(scoreDepth);

            double score = scoreDepth.getScore();
            int searchDepth = scoreDepth.getDepth();
//            System.out.println(step + "---- " + score);
            if (score > maxScore || (score == maxScore && minSearchDepth > searchDepth)) {
                minSearchDepth = searchDepth;
                maxScore = score;
                if (bestSteps.size() > 0 && bestSteps.get(0).getScoreDepth().getScore() < score) {
                    bestSteps = new ArrayList<>();
                }
                bestSteps.add(step);
            }
//            if (maxScore == 999999) {
//                break;
//            }

            alpha = Math.max(alpha, maxScore);
            if (maxScore >= beta) {
                break; // Beta剪枝
            }
        }

        Step bestStep = null;
        double finalScore = Integer.MIN_VALUE;
        for (Step step : bestSteps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);
            double value = evaluate(newChessboard, color);
            step.setEvaluateValue(value);
            if (value > finalScore) {
                finalScore = value;
                bestStep = step;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
        System.out.println("color:" + color + " bestStep:" + bestStep + " maxScore:" + maxScore + " minSearchDepth:" + minSearchDepth);
        return bestStep;
    }

    private int getMaxDepth(Chessboard newChessboard) {
        int maxDepth = MAX_DEPTH;
        if (newChessboard.getSituation().equals(SituationEnum.START)) {
            maxDepth = 3;
        } else if (newChessboard.getSituation().equals(SituationEnum.ENDING)) {
            maxDepth = 6;
        } else if (newChessboard.getSituation().equals(SituationEnum.FINAL)) {
            maxDepth = 6;
        }
//        System.out.println("getMaxDepth:" + maxDepth);
        return maxDepth;
    }

    // 极大层级
    private ScoreDepth maxValue(int color, Chessboard chessboard, int depth, double alpha, double beta, int maxDepth) {
        if (depth >= maxDepth) {
            double score = evaluate(chessboard, color);
            return new ScoreDepth(score, depth);
        }
        //todo
        int gameOver = chessboard.isGameOver();
        if (gameOver >= 0) {
            double score = evaluate(chessboard, color);
            return new ScoreDepth(score, depth);
        }

        double maxScore = Integer.MIN_VALUE;
        ScoreDepth sd = null;

        List<Step> steps = generateSteps(chessboard);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            ScoreDepth scoreDepth = minValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);
            double score = scoreDepth.getScore();
            if (score > maxScore) {
                maxScore = score;
                sd = scoreDepth;
            }

            alpha = Math.max(alpha, maxScore);
            if (maxScore == 999999) {
                break;
            }
            if (maxScore >= beta) {
                break; // Beta剪枝
            }
        }

        return new ScoreDepth(maxScore, sd.getDepth());
    }

    // 极小层级
    private ScoreDepth minValue(int color, Chessboard chessboard, int depth, double alpha, double beta, int maxDepth) {
        if (depth >= maxDepth) {
            double score = evaluate(chessboard, color);
            return new ScoreDepth(score, depth);
        }
        //todo
        int gameOver = chessboard.isGameOver();
        if (gameOver >= 0) {
            double score = evaluate(chessboard, color);
            return new ScoreDepth(score, depth);
        }

        double minScore = Integer.MAX_VALUE;
        ScoreDepth sd = null;

        List<Step> steps = generateSteps(chessboard);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            ScoreDepth scoreDepth = maxValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);
            double score = scoreDepth.getScore();

            if (score < minScore) {
                minScore = score;
                sd = scoreDepth;
            }
            beta = Math.min(beta, minScore);
            if (minScore == -999999) {
                break;
            }
            if (minScore <= alpha) {
                break; // Alpha剪枝
            }
        }

        return new ScoreDepth(minScore, sd.getDepth());
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