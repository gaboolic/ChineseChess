package tk.gbl.ai;

import tk.gbl.chessmodel.Bishop;
import tk.gbl.chessmodel.Chessman;
import tk.gbl.chessmodel.Guard;
import tk.gbl.constant.GameConstant;
import tk.gbl.constant.SituationEnum;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.util.SaveReadUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2023-09-22
 * Time: 3:33 PM
 *
 * @author gabooilc
 */
public class PositionEvaluate {

    static Map<String, Map<String, int[][]>> situationChessValueMap = new HashMap<>();

    static {
        String[] situations = new String[]{"start", "middle", "ending","final"};
        String[] chessNames = new String[]{"Cannon", "Horse", "Pawn", "Rook", "King"};

        for (String situation : situations) {
            Map<String, int[][]> chessValueMap = new HashMap<>();
            situationChessValueMap.put(situation, chessValueMap);
            for (String chessName : chessNames) {
                int[][] values = init(situation, chessName);
                chessValueMap.put(chessName, values);
            }
        }
    }

    public static int[][] init(String situation, String chessName) {
        int[][] chessmans = new int[10][9];
        InputStream is = SaveReadUtil.class.getClassLoader().getResourceAsStream("config/positionvalue/" + situation + "/" + chessName + ".txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        for (int i = 0; ; ) {
            String line = null;
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (line.trim().length() == 0) {
                continue;
            }
            String[] chessNumbers = line.split(",");
            for (int j = 0; j < 9; j++) {
                String chessNumberStr = chessNumbers[j];
                chessmans[i][j] = Integer.parseInt(chessNumberStr.trim());
            }
            i++;
        }
        return chessmans;
    }


    // 获取棋子的位置评估值
    public static int getPositionValue(Chessman chessman, Chessboard chessboard) {
        // 在这里计算棋子的位置评估值
        // 可以考虑棋子的位置优势、威胁等因素
        // 返回一个位置评估值

        if (chessman instanceof Bishop || chessman instanceof Guard) {
            //士在九宫格位置中心时位置评估值最高
            if (chessman.getPoint().getX() == 4) {
                return 25;
            }
        }
        SituationEnum situationEnum = chessboard.getSituation();
        String chessName = chessman.getClass().getSimpleName();
        int[][] value = situationChessValueMap.get(situationEnum.name().toLowerCase()).get(chessName);
        if (value == null) {
            return 0;
        }
        Point point = chessman.getPoint();
        if (chessman.getColor() == GameConstant.black) {
            return value[point.getY()][point.getX()];
        }
        return value[Chessboard.Y_SIZE - 1 - point.getY()][Chessboard.X_SIZE - 1 - point.getX()];
    }
}
