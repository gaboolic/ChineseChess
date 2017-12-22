package tk.gbl.ui;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.ui.constant.ColorConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Date: 2017/11/28
 * Time: 16:19
 *
 * @author Tian.Dong
 */
public class BoardPanel extends JPanel {

    Chessboard chessboard;

    public BoardPanel(Chessboard chessboard) {
        this.chessboard = chessboard;
        this.setBackground(ColorConstant.gray);
        this.setLayout(null);
        this.setBounds(5, 20, 558, 620);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(ColorConstant.gray);
        g.fillRect(0, 0, 558, 620);
        g.setColor(ColorConstant.dark);
        for (int i = 0; i < 10; i++) {
            drawLine(g, 0, i, 8, i);
        }
        for (int i = 0; i < 9; i++) {
            drawLine(g, i, 0, i, 9);
        }
        g.setColor(ColorConstant.gray);
        fillRect(g, 0, 4, 8, 5);
        g.setColor(ColorConstant.dark);
        drawLine(g, 3, 0, 5, 2);
        drawLine(g, 5, 0, 3, 2);
        drawLine(g, 3, 7, 5, 9);
        drawLine(g, 5, 7, 3, 9);

        Chessman chessman = chessboard.getChessmans()[0][0];
        drawChess(g,5,7,chessman);
    }

    private void drawChess(Graphics g, int x, int y, Chessman chessman) {

    }

    public void drawLine(Graphics g, int sx, int sy, int ex, int ey) {
        int pos = 50;
        int size = 52;
        g.drawLine(pos + sx * size, pos + sy * size, pos + ex * size, pos + ey * size);
    }

    public void fillRect(Graphics g, int sx, int sy, int ex, int ey) {
        int pos = 51;
        int size = 52;
        int width = (ex - sx) * size - 1;
        int height = (ey - sy) * size - 1;
        g.fillRect(pos + sx * size, pos + sy * size, width, height);
    }
}
