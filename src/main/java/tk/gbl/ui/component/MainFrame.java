package tk.gbl.ui.component;

import tk.gbl.model.Chessboard;
import tk.gbl.ui.listener.ChessClickController;

import javax.swing.*;
import java.awt.*;

/**
 * Date: 2017/11/28
 * Time: 16:15
 *
 * @author Tian.Dong
 */
public class MainFrame extends JFrame {

    private Chessboard chessboard;

    public MainFrame(Chessboard chessboard) {
        this.setBounds(366, 28, 580, 690);

        this.setTitle("象棋");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

        this.setLayout(null);
        BoardPanel boardPanel = new BoardPanel(chessboard);
        boardPanel.setVisible(true);
        boardPanel.addMouseListener(new ChessClickController(chessboard));
        this.add(boardPanel, BorderLayout.CENTER);
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
}
