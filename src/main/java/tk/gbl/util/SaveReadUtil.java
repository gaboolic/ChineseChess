package tk.gbl.util;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Point;

import java.io.*;

/**
 * Date: 2017/11/28
 * Time: 15:34
 *
 * @author gaboolic
 */
public class SaveReadUtil {
    public static Chessman[][] read(String filename) {
        Chessman[][] chessmans = new Chessman[10][9];
        InputStream is = SaveReadUtil.class.getClassLoader().getResourceAsStream("chessmanual/" + filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        for (int i = 0; i < 10; i++) {
            String line = null;
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                continue;
            }
            String[] chessNumbers = line.split(" ");
            for (int j = 0; j < 9; j++) {
                String chessNumberStr = chessNumbers[j];
                if (chessNumberStr.equals("0")) {
                    continue;
                }
                Chessman chessman = Chessman.getInstance(chessNumberStr);
                chessman.setPoint(new Point(j, i));
                chessmans[i][j] = chessman;
            }
        }
        return chessmans;
    }

    public static Chessman[][] readStr(String str) {
        Chessman[][] chessmans = new Chessman[10][9];
        int i = 0;
        for (String line : str.split("\n")) {
            if (line == null) {
                continue;
            }
            String[] chessNumbers = line.split(" ");
            for (int j = 0; j < 9; j++) {
                String chessNumberStr = chessNumbers[j];
                if (chessNumberStr.equals("0")) {
                    continue;
                }
                Chessman chessman = Chessman.getInstance(chessNumberStr);
                chessman.setPoint(new Point(j, i));
                chessmans[i][j] = chessman;
            }
            i++;
        }
        return chessmans;
    }

    public static void save(Chessman[][] chessmans, String file) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Chessman[] chessmansLine : chessmans) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < chessmansLine.length; i++) {
                    Chessman chessman = chessmansLine[i];
                    if (chessman != null) {
                        String type = chessman.getType();
                        line.append(type);
                        line.append(" ");
                    } else {
                        line.append(0);
                        line.append(" ");
                    }
                }
                line.deleteCharAt(line.length() - 1);
                bufferedWriter.write(line.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
