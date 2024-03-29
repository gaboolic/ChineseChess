package tk.gbl.util;

import tk.gbl.model.Step;

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
    static Map<String, Step> bestStepMap = new HashMap<>();
    static Map<String, Integer> gameOverMap = new HashMap<>();
    static Map<String, Double> evaluateMap = new HashMap<>();

    //某一方的所有可能走的步
    static Map<String, List<Step>> moveMap = new HashMap<>();

    public static Step getBestStep(String str, int color) {
        Step result = bestStepMap.get(color + "-" + str);
        return result;
    }

    public static void putBestStep(String str, int color, Step result) {
        bestStepMap.put(color + "-" + str, result);
    }

    public static Integer getGameOver(String str, int color) {
        Integer result = gameOverMap.get(color + "-" + str);
        return result;
    }

    public static void putGameOver(String str, int color, int result) {
        gameOverMap.put(color + "-" + str, result);
    }

    public static Double getEvaluatePosition(String str, int current, int color) {
        Double result = evaluateMap.get(current + "-" + color + "-" + str);
        return result;
    }

    public static void putEvaluatePosition(String str, int current, int color, double result) {
        evaluateMap.put(current + "-" + color + "-" + str, result);
    }

    public static List<Step> getAllSteps(String str, int current) {
        List<Step> result = moveMap.get(current + "-" + str);
        return result;
    }

    public static void putAllSteps(String str, int current, List<Step> result) {
        moveMap.put(current + "-" + str, result);
    }
}
