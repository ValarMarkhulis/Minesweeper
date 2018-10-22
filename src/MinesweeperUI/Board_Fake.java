package MinesweeperUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board_Fake extends JPanel {

    private final int CELL_SIZE = 15;
    private final int N_ROWS = 16;
    private final int N_COLS = 10;
    private final Image img;
    public int chosenField = -1;
    private final GUI gui;

    /*
    This code has been inspired by https://github.com/janbodnar/Java-Minesweeper-Game
     */
    public Board_Fake(GUI gui){
        this.gui = gui;

        int BOARD_HEIGHT = N_COLS * CELL_SIZE + 10;
        int BOARD_WIDTH = N_ROWS * CELL_SIZE + 10;
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT +90));

        img = (new ImageIcon("src/resources/" + 10 + ".png")).getImage();

        repaint();

        addMouseListener(new MinesAdapter());
    }

    class MinesAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            int cCol = x / CELL_SIZE;
            int cRow = y / CELL_SIZE;

            if ((x < N_COLS * CELL_SIZE) && (y < N_ROWS * CELL_SIZE)) {
                int fieldID = 10*cRow+cCol;

                if (e.getButton() == MouseEvent.BUTTON3) {

                } else {
                    chosenField = fieldID;
                    gui.test();
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < N_ROWS; i++) {
            for (int j = 0; j < N_COLS; j++) {

                g.drawImage(img, (j * CELL_SIZE),
                        (i * CELL_SIZE), this);
            }
        }
    }
}
