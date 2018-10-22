package MinesweeperMain;

import MinesweeperLogic.Game;
import MinesweeperUI.GUI;


public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        GUI gui = new GUI(game);
        gui.setVisible(true);

    }
}
