package tk.gbl.ai;

import tk.gbl.chessmodel.Cannon;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.chessmodel.Horse;
import tk.gbl.chessmodel.Rook;
import tk.gbl.constant.SituationEnum;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.model.Step;
import tk.gbl.util.CacheUtil;
import tk.gbl.util.CopyUtil;
import tk.gbl.util.SaveReadUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * αβ搜索
 * Date: 2023-09-11
 * Time: 8:14 PM
 *
 * @author gaboolic
 */
public class AlphaBetaSearch {
    private static final int MAX_DEPTH = 4; // 最大搜索深度

    EvaluateRule evaluateRule = new EvaluateRule();

    // Alpha-Beta搜索函数
    public Step alphaBetaSearch(Chessboard chessboard) {
        long startTime = System.currentTimeMillis();

        int color = chessboard.getCurrent();
        int depth = 0;
        double alpha = Integer.MIN_VALUE;
        double beta = Integer.MAX_VALUE;

        Step cacheBestStep = CacheUtil.getBestStep(SaveReadUtil.outputStr(chessboard.getChessmans()), chessboard.getCurrent());
        if (cacheBestStep != null) {
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime) + "ms");
            System.out.println("color:" + color + "cache- bestStep:" + cacheBestStep + " maxScore:" + cacheBestStep.getScoreDepth().getScore() + " minSearchDepth:" + cacheBestStep.getScoreDepth().getDepth());
            return cacheBestStep;
        }

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

        SituationEnum situationEnum = chessboard.getSituation();
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);
            double value = evaluate(newChessboard, color);
            step.setEvaluateValue(value);
        }
        steps.sort(new Comparator<Step>() {
            @Override
            public int compare(Step o1, Step o2) {
                if (o1.getEvaluateValue() == o2.getEvaluateValue()) {
                    return 0;
                }
                return o1.getEvaluateValue() < o2.getEvaluateValue() ? 1 : -1;
            }
        });

        //todo 有bug 需要更好剪枝
//        if (steps.size() > 10) {
//            steps = steps.subList(0, steps.size() / 2);
//        }

        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);
            int maxDepth = getMaxDepth(situationEnum, chessboard.getChessman(step.getStart()));
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
        Random random = new Random();
        int randomNumber = random.nextInt(bestSteps.size());
        bestStep = bestSteps.get(randomNumber);

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
        System.out.println("color:" + color + " bestStep:" + bestStep + " maxScore:" + maxScore + " minSearchDepth:" + minSearchDepth);
        CacheUtil.putBestStep(SaveReadUtil.outputStr(chessboard.getChessmans()), chessboard.getCurrent(), bestStep);
        return bestStep;
    }

    private int getMaxDepth(SituationEnum situationEnum, Chessman chessman) {
        int maxDepth = MAX_DEPTH;
        if (chessman instanceof Rook || chessman instanceof Horse || chessman instanceof Cannon) {
            if (situationEnum.equals(SituationEnum.ENDING)) {
                maxDepth = 4;
            } else if (situationEnum.equals(SituationEnum.FINAL)) {
                maxDepth = 4;
            }
        }

        return maxDepth;
    }

    // 极大层级
    private ScoreDepth maxValue(int color, Chessboard chessboard, int depth, double alpha, double beta, int maxDepth) {
        if (depth >= maxDepth) {
            double score = evaluate(chessboard, color);
            return new ScoreDepth(score, depth);
        }
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
        return evaluateRule.evaluatePositionByCache(chessboard, color);
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
                List<Point> movePoints = chessman.getMovePointsByCache(chessboard);
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