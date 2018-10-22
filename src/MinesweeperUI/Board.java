package MinesweeperUI;

import MinesweeperLogic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

public class Board extends JPanel {

    private final int CELL_SIZE = 15;
    private final int N_ROWS = 16;
    private final int N_COLS = 10;
    private final Image[] img;
    private final Game game;
    private final JLabel flagSetStatusbar;
    private final Timer timer;
    private final JLabel timeLabel;
    boolean endGame = false;

/*
This code has been inspired by https://github.com/janbodnar/Java-Minesweeper-Game
 */
    Board(Game game, JLabel flagSetStatusbar, Timer timer, JLabel timeLabel){
        this.game = game;
        this.flagSetStatusbar = flagSetStatusbar;
        this.timer = timer;
        this.timeLabel = timeLabel;

        int BOARD_WIDTH = N_ROWS * CELL_SIZE + 10;
        int BOARD_HEIGHT = N_COLS * CELL_SIZE + 10;
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT +90));

        int NUM_IMAGES = 14;
        img = new Image[NUM_IMAGES];

        for (int i = 0; i < NUM_IMAGES; i++) {
            img[i] = (new ImageIcon("src/resources/" + i + ".png")).getImage();
        }
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

                boolean debug = false;
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if(debug) {
                        System.out.println("Right click");
                        System.out.println("cCol = " + cCol + "  cRow = " + cRow);
                        System.out.println("Field "+fieldID);
                    }
                    //Toggle the flag
                    int status = game.toggleFlag(fieldID);
                    if(status == 0){
                        repaint();
                        timer.cancel();
                        displayMsg(true);
                        System.exit(-1);
                    }

                } else {
                    if(debug) {
                        System.out.println("Left click");
                        System.out.println("cCol = " + cCol + "  cRow = " + cRow);
                        System.out.println("Field "+fieldID);
                    }
                    //Guess on a field
                    int status = game.guess(fieldID);
                    if(status == -1){
                        //TODO: Show all fields!
                        game.showAllFields(game.bombWhichKilledYou);
                        endGame = true;
                        repaint();
                        timer.cancel();
                        displayMsg(false);
                        System.exit(-1);
                    }else if(status == 0){
                        repaint();
                        timer.cancel();
                        displayMsg(true);
                        System.exit(-1);
                    }
                }
            }
            flagSetStatusbar.setText("Flags set: "+(Integer.toString(game.flagsSet))+"        ");
            repaint();
        }
    }

    private void displayMsg(boolean win) {
        int seconds = Integer.valueOf(timeLabel.getText().replaceAll("Time: ",""));
        int minutes = Math.floorDiv(seconds,60);
        if(win){
            if(minutes != 0){
                seconds = seconds-(minutes*60);
                if(minutes > 1)
                    JOptionPane.showMessageDialog(null, "You have won! And you did it in "+minutes+" minutes and "+seconds+" seconds");
                else
                    JOptionPane.showMessageDialog(null, "You have won! And you did it in "+minutes+" minute and "+seconds+" seconds");
            }else{
                JOptionPane.showMessageDialog(null, "You have won! And you did it in "+seconds+" seconds");
            }
        }else{
            if(minutes != 0){
                seconds = seconds-(minutes*60);
                if(minutes > 1)
                    JOptionPane.showMessageDialog(null, "Oh no, you have hit a bomb! Time was "+minutes+" minutes and "+seconds+" seconds");
                else
                    JOptionPane.showMessageDialog(null, "Oh no, you have hit a bomb! Time was "+minutes+" minute and "+seconds+" seconds");
            }else{
                JOptionPane.showMessageDialog(null, "Oh no, you have hit a bomb! Time was "+seconds+" seconds");
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < N_ROWS; i++) {
            for (int j = 0; j < N_COLS; j++) {
                int cell = game.gameBoard.get(10*i+j).getFieldImgType(endGame);

                boolean debug = false;
                if(debug){
                    int test= 10*i+j;
                    System.out.println("Nr. "+test+" is a "+cell);
                }

                g.drawImage(img[cell], (j * CELL_SIZE),
                        (i * CELL_SIZE), this);
            }
        }
    }
}
