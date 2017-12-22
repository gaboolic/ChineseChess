package tk.gbl.ui;

import tk.gbl.model.Chessboard;

import javax.swing.*;
import java.awt.*;

/**
 * Date: 2017/11/28
 * Time: 16:15
 *
 * @author Tian.Dong
 */
public class MainFrame extends JFrame {

    Chessboard chessboard;

    public MainFrame() {
        this.setBounds(366, 28, 580, 690);

        this.setTitle("象棋");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

        this.setLayout(null);
        BoardPanel boardPanel = new BoardPanel(chessboard);
        this.add(boardPanel, BorderLayout.CENTER);
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
}
