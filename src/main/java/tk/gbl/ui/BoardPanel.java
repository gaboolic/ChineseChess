package tk.gbl.ui;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.GameConstant;
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
    int pos = 50;
    int size = 52;

    public BoardPanel(Chessboard chessboard) {
        this.chessboard = chessboard;
        this.setBackground(ColorConstant.gray);
        this.setLayout(null);
        this.setBounds(5, 20, 558, 620);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(ColorConstant.woodBoard);
        g.fillRect(0, 0, 558, 620);
        g.setColor(ColorConstant.gray);
        for (int i = 0; i < 10; i++) {
            drawLine(g, 0, i, 8, i);
        }
        for (int i = 0; i < 9; i++) {
            drawLine(g, i, 0, i, 9);
        }
        g.setColor(ColorConstant.woodBoard);
        fillRect(g, 0, 4, 8, 5);
        g.setColor(ColorConstant.gray);
        drawLine(g, 3, 0, 5, 2);
        drawLine(g, 5, 0, 3, 2);
        drawLine(g, 3, 7, 5, 9);
        drawLine(g, 5, 7, 3, 9);

        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 9; column++) {
                Chessman chessman = chessboard.getChessmans()[row][column];
                drawChess(g, column, row, chessman);
            }
        }
    }

    private void drawChess(Graphics g, int x, int y, Chessman chessman) {
        Color oldColor = g.getColor();
        if (chessman == null) {
            return;
        }
        if (chessman.getColor() == GameConstant.red) {
            g.setColor(ColorConstant.redChess);
        } else {
            g.setColor(ColorConstant.blackChess);
        }
        int ovalSize = size - 10;
        g.fillOval(pos + x * size - ovalSize / 2, pos + y * size - ovalSize / 2, ovalSize, ovalSize);
        if (chessman.getColor() == GameConstant.red) {
            g.setColor(ColorConstant.redChessWord);
        } else {
            g.setColor(ColorConstant.blackChessWord);
        }
        g.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        g.drawString(chessman.getChineseName(), pos + x * size - 10, pos + y * size + 5);
        g.setColor(oldColor);
    }

    public void drawLine(Graphics g, int sx, int sy, int ex, int ey) {
        g.drawLine(pos + sx * size, pos + sy * size, pos + ex * size, pos + ey * size);
    }

    public void fillRect(Graphics g, int sx, int sy, int ex, int ey) {
        int width = (ex - sx) * size - 1;
        int height = (ey - sy) * size - 1;
        g.fillRect(pos + 1 + sx * size, pos + 1 + sy * size, width, height);
    }
}
