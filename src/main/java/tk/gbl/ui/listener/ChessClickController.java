package tk.gbl.ui.listener;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.ui.constant.SizeConstant;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Date: 2019/1/3
 * Time: 下午2:37
 *
 * @author dongtian
 */
public class ChessClickController extends MouseAdapter {

    private Chessboard chessboard;

    private Chessman currentChessman;

    public ChessClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void mouseClicked(MouseEvent e) {
        int x = getX(e);
        int y = getY(e);
        Point point = new Point(x, y);
        if (currentChessman == null) {
            Chessman chessman = chessboard.getChessman(point);
            if (chessman == null) {
                return;
            }
            currentChessman = chessman;
        } else {
            chessboard.moveChessMan(currentChessman, point);
            currentChessman = null;
        }
    }

    private int getY(MouseEvent e) {
        return e.getY() / SizeConstant.gridSize;
    }

    private int getX(MouseEvent e) {
        return e.getX() / SizeConstant.gridSize;
    }
}
