package MinesweeperUI;

import MinesweeperLogic.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class GUI extends JFrame {

    private JLabel statusbar;
    private Game game;


    public GUI(Game game){
        this.game = game;
        statusbar = new JLabel("Minesweeper");
        add(statusbar, BorderLayout.SOUTH);
        add(new Board(game));

        setResizable(false);
        pack();

        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
