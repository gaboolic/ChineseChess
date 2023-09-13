package tk.gbl.ui.component;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.ui.constant.ColorConstant;
import tk.gbl.ui.constant.SizeConstant;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Date: 2017/11/28
 * Time: 16:19
 *
 * @author gaboolic
 */
public class BoardPanel extends JPanel {

    Chessboard chessboard;
    int pos = 50;

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
        for (int i = 0; i < Chessboard.Y_SIZE; i++) {
            drawLine(g, 0, i, 8, i);
        }
        for (int i = 0; i < Chessboard.X_SIZE; i++) {
            drawLine(g, i, 0, i, 9);
        }
        g.setColor(ColorConstant.woodBoard);
        fillRect(g, 0, 4, 8, 5);
        g.setColor(ColorConstant.gray);
        drawLine(g, 3, 0, 5, 2);
        drawLine(g, 5, 0, 3, 2);
        drawLine(g, 3, 7, 5, 9);
        drawLine(g, 5, 7, 3, 9);

        //绘制棋子
        for (int row = 0; row < Chessboard.Y_SIZE; row++) {
            for (int column = 0; column < Chessboard.X_SIZE; column++) {
                Chessman chessman = chessboard.getChessmans()[row][column];
                drawChess(g, column, row, chessman);
            }
        }

        //绘制当前选中棋子的标志
        drawCurrentChessman(g);

        //绘制当前行动方
        if (chessboard.getCurrent() == GameConstant.red) {
            g.drawString("红", 0, 50);
        } else {
            g.drawString("黑", 0, 50);
        }
    }

    private void drawCurrentChessman(Graphics g) {
        Color oldColor = g.getColor();
        Chessman currentChessman = chessboard.getCurrentChessman();
        if (currentChessman != null) {
            int ovalSize = 6;
            g.setColor(Color.GREEN);
            g.fillOval(pos + currentChessman.getPoint().getX() * SizeConstant.gridSize - ovalSize / 2, pos + currentChessman.getPoint().getY() * SizeConstant.gridSize - ovalSize / 2, ovalSize, ovalSize);
            List<Point> movePoints = currentChessman.getMovePoints(chessboard);

            ovalSize = 10;
            g.setColor(Color.GREEN);
            for (Point point : movePoints) {
                g.fillOval(pos + point.getX() * SizeConstant.gridSize - ovalSize / 2, pos + point.getY() * SizeConstant.gridSize - ovalSize / 2, ovalSize, ovalSize);
            }
        }
        g.setColor(oldColor);
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
        int ovalSize = SizeConstant.gridSize - 10;
        g.fillOval(pos + x * SizeConstant.gridSize - ovalSize / 2, pos + y * SizeConstant.gridSize - ovalSize / 2, ovalSize, ovalSize);
        if (chessman.getColor() == GameConstant.red) {
            g.setColor(ColorConstant.redChessWord);
        } else {
            g.setColor(ColorConstant.blackChessWord);
        }
        g.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        g.drawString(chessman.getChineseName(), pos + x * SizeConstant.gridSize - 10, pos + y * SizeConstant.gridSize + 5);
        g.setColor(oldColor);
    }

    public void drawLine(Graphics g, int sx, int sy, int ex, int ey) {
        g.drawLine(pos + sx * SizeConstant.gridSize, pos + sy * SizeConstant.gridSize, pos + ex * SizeConstant.gridSize, pos + ey * SizeConstant.gridSize);
    }

    public void fillRect(Graphics g, int sx, int sy, int ex, int ey) {
        int width = (ex - sx) * SizeConstant.gridSize - 1;
        int height = (ey - sy) * SizeConstant.gridSize - 1;
        g.fillRect(pos + 1 + sx * SizeConstant.gridSize, pos + 1 + sy * SizeConstant.gridSize, width, height);
    }
}
