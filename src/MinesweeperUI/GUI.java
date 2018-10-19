package MinesweeperUI;

import MinesweeperLogic.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class GUI extends JFrame {

    private final JLabel flagSetStatusbar;
    private JLabel statusbar;
    private Game game;
    static int flagSet = 0;


    public GUI(Game game){
        this.game = game;
        statusbar = new JLabel("Minesweeper");
        add(statusbar, BorderLayout.SOUTH);

        flagSetStatusbar = new JLabel("Flags set: "+flagSet+"        ");
        add(flagSetStatusbar, BorderLayout.EAST);


        add(new Board(game,flagSetStatusbar));

        setResizable(false);
        pack();

        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
