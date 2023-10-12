package tk.gbl.util;

import tk.gbl.model.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2023-10-12
 * Time: 4:20 PM
 *
 * @author gaboolic
 */
public class CacheUtil {
    static Map<String, Integer> gameOverMap = new HashMap<>();
    static Map<String, Double> evaluateMap = new HashMap<>();
    static Map<String, List<Point>> moveMap = new HashMap<>();

    public static Integer getGameOver(String str, int color) {
        Integer result = gameOverMap.get(color + "-" + str);
        return result;
    }

    public static void putGameOver(String str, int color, int result) {
        gameOverMap.put(color + "-" + str, result);
    }

    public static Double getEvaluatePosition(String str, int color) {
        Double result = evaluateMap.get(color + "-" + str);
        return result;
    }

    public static void putEvaluatePosition(String str, int color, double result) {
        evaluateMap.put(color + "-" + str, result);
    }

    public static List<Point> getMovePoints(String str, Point point) {
        List<Point> result = moveMap.get(point + "-" + str);
        return result;
    }

    public static void putMovePoints(String str, Point point, List<Point> result) {
        moveMap.put(point + "-" + str, result);
    }
}
