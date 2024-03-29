package tk.gbl.model;

import tk.gbl.chessmodel.*;
import tk.gbl.constant.GameConstant;
import tk.gbl.constant.SituationEnum;
import tk.gbl.util.CacheUtil;
import tk.gbl.util.SaveReadUtil;

import java.util.*;

/**
 * 棋盘
 * <p>
 * Date: 2017/11/27
 * Time: 16:17
 *
 * @author gaboolic
 */
public class Chessboard {

    public static final int X_SIZE = 9;
    public static final int Y_SIZE = 10;
    private Chessman currentChessman;
    private Chessman lastChessman;
    private Chessman[][] chessmans;

    private int current = GameConstant.red;

    /**
     * 回合数
     */
    private int round = 0;

    private int noEatRound = 0;

    private Stack<Step> moveStepStack = new Stack<>();


    public Chessman[][] getChessmans() {
        return chessmans;
    }

    public void setChessmans(Chessman[][] chessmans) {
        this.chessmans = chessmans;
    }

    public Chessman getKing(int color) {
        for (int row = 0; row < Chessboard.Y_SIZE; row++) {
            for (int column = 0; column < Chessboard.X_SIZE; column++) {
                Chessman chessman = this.getChessmans()[row][column];
                if (chessman instanceof King) {
                    if (chessman.getColor() == color) {
                        return chessman;
                    }
                }
            }
        }
        return null;
    }

    public Chessman getChessman(Point point) {
        return chessmans[point.getY()][point.getX()];
    }

    public Chessman getChessman(int x, int y) {
        Point point = new Point(x, y);
        return chessmans[point.getY()][point.getX()];
    }

    public void setChessman(Chessman chessman) {
        Point point = chessman.getPoint();
        chessmans[point.getY()][point.getX()] = chessman;
    }

    public void setCurrentChessman(Chessman currentChessman) {
        this.currentChessman = currentChessman;
    }

    public Chessman getCurrentChessman() {
        return currentChessman;
    }

    public Chessman getLastChessman() {
        return lastChessman;
    }

    public void setLastChessman(Chessman lastChessman) {
        this.lastChessman = lastChessman;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void moveChessMan(Point toPoint) {
        if (getChessman(toPoint) != null) {
            noEatRound = 0;
        } else {
            noEatRound++;
        }
        //移动步数
        Point fromPoint = currentChessman.getPoint();
        Step moveStep = new Step(fromPoint, toPoint);
        moveStep.setEndChessman(getChessman(toPoint));
        moveStepStack.push(moveStep);

        currentChessman.setPoint(toPoint);
        setChessman(currentChessman);
        chessmans[fromPoint.getY()][fromPoint.getX()] = null;
        lastChessman = currentChessman;
        currentChessman = null;
        //当前方
        current = current ^ 1;
        round++;
    }

    public void withdraw() {
        if (round == 0) {
            return;
        }
        Step step = moveStepStack.pop();
        Chessman lastChessman = getChessman(step.getEnd());
        lastChessman.setPoint(step.getStart());
        setChessman(lastChessman);

        // 恢复棋子
        Chessman endChessman = step.getEndChessman();
        if (endChessman != null) {
            setChessman(endChessman);
        } else {
            chessmans[step.getEnd().getY()][step.getEnd().getX()] = null;
        }
        //当前方
        current = current ^ 1;
        round--;
    }

    public void makeStep(Step step) {
        Chessman currentChessman = this.getChessman(step.getStart());
        this.setCurrentChessman(currentChessman);
        this.moveChessMan(step.getEnd());
    }

    public boolean isInsideBoard(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= X_SIZE || y >= Y_SIZE) {
            return false;
        }
        return true;
    }

    public int isGameOverByCache() {
        Integer cacheResult = CacheUtil.getGameOver(SaveReadUtil.outputStr(chessmans), getCurrent());
        if (cacheResult != null) {
            return cacheResult;
        }
        int gameOver = isGameOver();
        CacheUtil.putGameOver(SaveReadUtil.outputStr(chessmans), getCurrent(), gameOver);
        return gameOver;
    }

    /**
     * 返回胜利方
     */
    public int isGameOver() {
        //和棋
        if (noEatRound > 50) {
            return 2;
        }
        int redKingCount = 0;
        int blackKingCount = 0;
        int attackCount = 0;
        Chessman redKing = null;
        Chessman blackKing = null;
        for (int row = 0; row < Chessboard.Y_SIZE; row++) {
            for (int column = 0; column < Chessboard.X_SIZE; column++) {
                Chessman chessman = this.getChessmans()[row][column];
                if (chessman instanceof King) {
                    if (chessman.getColor() == GameConstant.red) {
                        redKingCount++;
                        redKing = chessman;
                    } else {
                        blackKingCount++;
                        blackKing = chessman;
                    }
                }
                if (chessman instanceof Rook || chessman instanceof Horse || chessman instanceof Cannon || chessman instanceof Pawn) {
                    attackCount++;
                }
            }
        }

        if (redKingCount == 0) {
            return GameConstant.black;
        }
        if (blackKingCount == 0) {
            return GameConstant.red;
        }
        //和棋
        if (attackCount == 0) {
            return 2;
        }

        //困毙
        Map<Integer, List<Point>> colorMoveMap = new HashMap<>();
        for (int row = 0; row < Chessboard.Y_SIZE; row++) {
            for (int column = 0; column < Chessboard.X_SIZE; column++) {
                Chessman chessman = this.getChessmans()[row][column];
                if (chessman == null) {
                    continue;
                }
                List<Point> moves = chessman.getMovePointsByCache(this);
                colorMoveMap.computeIfAbsent(chessman.getColor(), k -> new ArrayList<>());
                colorMoveMap.get(chessman.getColor()).addAll(moves);
            }
        }
        if (colorMoveMap.get(GameConstant.red).size() == 0) {
            return GameConstant.black;
        }
        if (colorMoveMap.get(GameConstant.black).size() == 0) {
            return GameConstant.red;
        }
        if (getCurrent() == GameConstant.red && colorMoveMap.get(GameConstant.red).contains(blackKing.getPoint())) {
            return GameConstant.red;
        }
        if (getCurrent() == GameConstant.black && colorMoveMap.get(GameConstant.black).contains(redKing.getPoint())) {
            return GameConstant.black;
        }
        //todo 判断下一回合是否被绝杀
        return -1;
    }

    public SituationEnum getSituation() {
        if (round <= 3) {
            return SituationEnum.START;
        }
        int bigCount = 0;
        for (int row = 0; row < Chessboard.Y_SIZE; row++) {
            for (int column = 0; column < Chessboard.X_SIZE; column++) {
                Chessman chessman = this.getChessmans()[row][column];
                if (chessman instanceof Rook || chessman instanceof Horse || chessman instanceof Cannon) {
                    bigCount++;
                }
            }
        }
        if (bigCount <= 3) {
            return SituationEnum.FINAL;
        }
        if (bigCount <= 6) {
            return SituationEnum.ENDING;
        }
        return SituationEnum.MIDDLE;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getNoEatRound() {
        return noEatRound;
    }

    public void setNoEatRound(int noEatRound) {
        this.noEatRound = noEatRound;
    }

    // 生成当前棋局的所有合法移动
    public List<Step> generateStepsByCache(int current) {
//        List<Step> cacheSteps = CacheUtil.getAllSteps(SaveReadUtil.outputStr(chessmans), current);
//        if (cacheSteps != null) {
//            return cacheSteps;
//        }
        List<Step> result = generateSteps(current);
//        CacheUtil.putAllSteps(SaveReadUtil.outputStr(chessmans), current, result);
        return result;
    }

    public List<Step> generateSteps(int current) {
        // 在这里生成当前棋局的合法移动列表
        List<Step> stepList = new ArrayList<>();
        Chessman[][] chessmans = this.getChessmans();
        for (Chessman[] list : chessmans) {
            for (Chessman chessman : list) {
                if (chessman == null) {
                    continue;
                }
                if (current != chessman.getColor()) {
                    continue;
                }
                List<Point> movePoints = chessman.getMovePointsByCache(this);
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
