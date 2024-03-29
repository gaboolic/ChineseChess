package tk.gbl.ai;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.SituationEnum;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Step;
import tk.gbl.util.CacheUtil;
import tk.gbl.util.CopyUtil;
import tk.gbl.util.SaveReadUtil;
import tk.gbl.util.ShowStepUtil;

import java.util.*;

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

    public Step alphaBetaSearchByCache(Chessboard chessboard) {
        Step cacheBestStep = CacheUtil.getBestStep(SaveReadUtil.outputStr(chessboard.getChessmans()), chessboard.getCurrent());
        if (cacheBestStep != null) {
            return cacheBestStep;
        }
        Step bestStep = alphaBetaSearch(chessboard);
        CacheUtil.putBestStep(SaveReadUtil.outputStr(chessboard.getChessmans()), chessboard.getCurrent(), bestStep);
        return bestStep;
    }

    // Alpha-Beta搜索函数
    public Step alphaBetaSearch(Chessboard chessboard) {
        long startTime = System.currentTimeMillis();

        int color = chessboard.getCurrent();
        int depth = 0;
        double alpha = Integer.MIN_VALUE;
        double beta = Integer.MAX_VALUE;

        // 获取当前可行的移动
        List<Step> steps = chessboard.generateStepsByCache(chessboard.getCurrent());

        List<Step> bestSteps = new ArrayList<>();
        double maxScore = Integer.MIN_VALUE;
        int minSearchDepth = Integer.MIN_VALUE;

        SituationEnum situationEnum = chessboard.getSituation();
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);
//            double value = evaluate(newChessboard, color);
//            step.setEvaluateValue(value);

            ScoreDepth scoreDepth = minValue(color, newChessboard, depth + 1, alpha, beta, 3);
            step.setEvaluateValue(scoreDepth.getScore());
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
//        if (steps.size() > 20) {
//            steps = steps.subList(0, steps.size() / 2);
//        }
        int maxDepth = getMaxDepth(situationEnum);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);
            ScoreDepth scoreDepth = minValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);
            scoreDepth.getSteps().push(step);
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

        LinkedList<Step> flowSteps = null;
        flowSteps = bestSteps.get(0).getScoreDepth().getSteps();
        flowSteps = steps.get(0).getScoreDepth().getSteps();
//        ShowStepUtil.showFlowSteps(flowSteps, chessboard);

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
        System.out.println("color:" + color + " bestStep:" + bestStep + " maxScore:" + maxScore + " minSearchDepth:" + minSearchDepth);
        return bestStep;
    }

    private int getMaxDepth(SituationEnum situationEnum) {
        int maxDepth = MAX_DEPTH;
//        if (chessman instanceof Rook || chessman instanceof Horse || chessman instanceof Cannon) {
//            if (situationEnum.equals(SituationEnum.ENDING)) {
//                maxDepth = 6;
//            } else if (situationEnum.equals(SituationEnum.FINAL)) {
//                maxDepth = 6;
//            }
//        }
        if (situationEnum.equals(SituationEnum.START)) {
            maxDepth = 3;
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

        List<Step> steps = chessboard.generateStepsByCache(chessboard.getCurrent());
        Collections.shuffle(steps);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            ScoreDepth scoreDepth = minValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);
            scoreDepth.getSteps().push(step);
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

        return sd;
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

        List<Step> steps = chessboard.generateStepsByCache(chessboard.getCurrent());
        Collections.shuffle(steps);
        for (Step step : steps) {
            Chessboard newChessboard = CopyUtil.makeStep(chessboard, step);

            ScoreDepth scoreDepth = maxValue(color, newChessboard, depth + 1, alpha, beta, maxDepth);
            scoreDepth.getSteps().push(step);
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

        return sd;
    }

    // 评估函数，用于评估当前棋局的得分
    private double evaluate(Chessboard chessboard, int color) {
        // 在这里计算当前棋局的得分并返回
        return evaluateRule.evaluatePositionByCache(chessboard, color);
    }


}