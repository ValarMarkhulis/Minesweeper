package MinesweeperUI;

import MinesweeperLogic.Game;

import javax.swing.*;
import java.awt.*;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;


public class GUI extends JFrame {

    private final JLabel flagSetStatusbar;
    private JLabel statusbar;
    private Game game;
    static int flagSet = 0;

    public Timer timer = new Timer();
    private JLabel timeLabel = new JLabel(" ");


    private class UpdateUITask extends TimerTask {

        int nSeconds = 0;

        @Override
        public void run() {
            EventQueue.invokeLater(() -> timeLabel.setText("Time: "+String.valueOf(nSeconds++)));
        }
    }


    public GUI(Game game){
        this.game = game;
        statusbar = new JLabel("Minesweeper");
        add(statusbar, BorderLayout.SOUTH);

        flagSetStatusbar = new JLabel("Flags set: "+flagSet+"        ");
        BorderLayout BL = new BorderLayout();
        JPanel p = new JPanel(BL);
        p.add(flagSetStatusbar,BorderLayout.WEST);
        p.add(timeLabel,BorderLayout.EAST);
        p.setSize(50,50);
        add(p,BorderLayout.EAST);

        add(new Board(game,flagSetStatusbar,timer,timeLabel));



        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setResizable(false);
        pack();
        timer.schedule(new UpdateUITask(), 0, 1000);

        EventQueue.invokeLater(() -> {
            final Clock clock = new Clock() {
                @Override
                public ZoneId getZone() {
                    return null;
                }

                @Override
                public Clock withZone(ZoneId zoneId) {
                    return null;
                }

                @Override
                public Instant instant() {
                    return null;
                }
            };
        });
    }

}
