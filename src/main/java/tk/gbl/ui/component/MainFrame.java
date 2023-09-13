package tk.gbl.ui.component;

import tk.gbl.model.Chessboard;
import tk.gbl.ui.listener.ChessClickController;

import javax.swing.*;
import java.awt.*;

/**
 * Date: 2017/11/28
 * Time: 16:15
 *
 * @author gaboolic
 */
public class MainFrame extends JFrame {

    private Chessboard chessboard;

    public MainFrame(Chessboard chessboard) {
        this.setBounds(366, 28, 680, 690);

        this.setTitle("中国象棋");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

        this.setLayout(new BorderLayout());
        BoardPanel boardPanel = new BoardPanel(chessboard);
        boardPanel.setVisible(true);
        ChessClickController chessClickController = new ChessClickController(chessboard, boardPanel);
        boardPanel.addMouseListener(chessClickController);
        this.add(boardPanel, BorderLayout.CENTER);

        ControlPanel controlPanel = new ControlPanel(chessboard, chessClickController);
        controlPanel.setVisible(true);
        this.add(controlPanel, BorderLayout.EAST);
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
}
