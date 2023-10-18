package tk.gbl.ui.component;

import tk.gbl.ai.AlphaBetaSearch;
import tk.gbl.constant.GameConstant;
import tk.gbl.model.Chessboard;
import tk.gbl.model.Point;
import tk.gbl.model.Step;
import tk.gbl.ui.constant.ColorConstant;
import tk.gbl.ui.listener.ChessClickController;
import tk.gbl.util.SaveReadUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Date: 2023/9/13
 * Time: 19:49
 *
 * @author gaboolic
 */
public class ControlPanel extends JPanel {
    Chessboard chessboard;

    public ControlPanel(Chessboard chessboard, ChessClickController chessClickController) {
        this.chessboard = chessboard;
        this.setBackground(ColorConstant.gray);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // 设置按钮之间的间距

        // 添加按钮
        JButton button1 = new JButton("重新开始");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("重新开始 actionPerformed");
                System.out.println(e);
                chessboard.setCurrentChessman(null);
                chessboard.setCurrent(GameConstant.red);
                chessboard.setChessmans(SaveReadUtil.read("gamestart.txt"));
                chessClickController.repaint();
                System.out.println("重新开始 end");
            }
        });
        this.add(button1, gbc);

        JButton button2 = new JButton("走一步");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();
                Step step = alphaBetaSearch.alphaBetaSearch(chessboard);
                Point start = step.getStart();
                chessClickController.clickPoint(start);
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    Point end = step.getEnd();
                    chessClickController.clickPoint(end);
                }).start();
            }
        });
        this.add(button2, gbc);

        JButton button3 = new JButton("悔棋");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessClickController.withdraw();
            }
        });
        this.add(button2, gbc);


        // 在最后添加一个垂直填充的空组件，以铺满剩余空间
        gbc.weighty = 1.0;
        this.add(Box.createVerticalGlue(), gbc);
    }
}
