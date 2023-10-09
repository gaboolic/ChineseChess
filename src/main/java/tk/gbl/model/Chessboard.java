package tk.gbl.model;

import tk.gbl.chessmodel.*;
import tk.gbl.constant.GameConstant;
import tk.gbl.constant.SituationEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Chessman[][] getChessmans() {
        return chessmans;
    }

    public void setChessmans(Chessman[][] chessmans) {
        this.chessmans = chessmans;
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
        Point fromPoint = currentChessman.getPoint();

        currentChessman.setPoint(toPoint);
        setChessman(currentChessman);
        chessmans[fromPoint.getY()][fromPoint.getX()] = null;
        lastChessman = currentChessman;
        currentChessman = null;
        //当前方
        current = current ^ 1;
        round++;
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

    /**
     * 返回胜利方
     */
    public int isGameOver() {
        int redKingCount = 0;
        int blackKingCount = 0;
        for (int row = 0; row < Chessboard.Y_SIZE; row++) {
            for (int column = 0; column < Chessboard.X_SIZE; column++) {
                Chessman chessman = this.getChessmans()[row][column];
                if (chessman instanceof King) {
                    if (chessman.getColor() == GameConstant.red) {
                        redKingCount++;
                    } else {
                        blackKingCount++;
                    }
                }
            }
        }
        if (redKingCount == 0) {
            return GameConstant.black;
        }
        if (blackKingCount == 0) {
            return GameConstant.red;
        }

        //困毙
        Map<Integer, List<Point>> colorMoveMap = new HashMap<>();
        for (int row = 0; row < Chessboard.Y_SIZE; row++) {
            for (int column = 0; column < Chessboard.X_SIZE; column++) {
                Chessman chessman = this.getChessmans()[row][column];
                if (chessman == null) {
                    continue;
                }
                List<Point> moves = chessman.getMovePoints(this);
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
}
