package tk.gbl.ui.listener;

import tk.gbl.chessmodel.Chessman;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.ui.component.BoardPanel;
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
    //view
    private BoardPanel boardPanel;

    private Chessboard chessboard;

    private Chessman currentChessman;

    public ChessClickController(Chessboard chessboard, BoardPanel boardPanel) {
        this.chessboard = chessboard;
        this.boardPanel = boardPanel;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(e);
        int x = getX(e);
        int y = getY(e);
        Point point = new Point(x, y);
        System.out.println(point);
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
        System.out.println(currentChessman);
        boardPanel.repaint();
    }

    private int getY(MouseEvent e) {
        return (e.getY() + 25) / SizeConstant.gridSize -1 ;
    }

    private int getX(MouseEvent e) {
        return (e.getX()+25) / SizeConstant.gridSize -1;
    }
}
