package tk.gbl.util;

import org.junit.Test;
import tk.gbl.chessmodel.Chessman;

import java.net.URL;

/**
 * Date: 2023-09-18
 * Time: 11:40 AM
 *
 * @author
 */
public class SaveReadUtilTest {

    @Test
    public void save() {
        Chessman[][] chessmans = SaveReadUtil.read("gamestart.txt");
        System.out.println(chessmans);

        URL resourceUrl = SaveReadUtilTest.class.getClassLoader().getResource("chessmanual/temp.txt");
        String resourcePath = resourceUrl.getPath();

        SaveReadUtil.save(chessmans, resourcePath);
    }

    @Test
    public void readStr() {
        Chessman[][] chessmans = SaveReadUtil.readStr("! @ # $ % $ # @ !\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 ^ 0 0 0 0 0 ^ 0\n" +
                "& 0 & 0 & 0 & 0 &\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "7 0 7 0 7 0 7 0 7\n" +
                "0 6 0 0 0 0 0 6 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "1 2 3 4 5 4 3 2 1");
        System.out.println(chessmans);
    }
}