package tk.gbl;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.ui.component.MainFrame;
import tk.gbl.util.SaveReadUtil;

/**
 * Date: 2017/11/27
 * Time: 16:09
 *
 * @author gaboolic
 */
public class MainClass {
    public static void main(String[] args){
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    private static void createAndShowGUI() {
        Chessboard chessboard = new Chessboard();
        Chessman[][] chessmans = SaveReadUtil.read("gamestart.txt");
        chessboard.setChessmans(chessmans);
        MainFrame mainFrame = new MainFrame(chessboard);
    }
}
