package MinesweeperMain;

import MinesweeperLogic.FieldBomb;
import MinesweeperLogic.FieldEmpty;
import MinesweeperLogic.FieldINumber;
import MinesweeperLogic.FieldInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Main {
    static ArrayList<FieldInterface> gameBoard;
    //static int Array[][];
    static FieldINumber testNr;
    static FieldEmpty testEm;
    static FieldBomb testBo;
    enum direction{RIGHT,LEFT,DOWN,UP,NON, RIGHTUP,RIGHTDOWN, LEFTUP, LEFTDOWN };


    public static void main(String[] args) {
    //TODO: Get input though the UI instead of commandline
	//Get input
    java.util.Scanner tastatur = new java.util.Scanner(System.in);  // forbered



    //TODO: Implement gameboard initialisation
        /*
        int[][] Array = new int[10][16];
        int id = 0;
        for (int i = 0; i < Array.length; i++) {
            for (int j = 0; j < 16; j++) {
                Array[i][j] = id++;
            }
        }
        */
        gameBoard = new ArrayList<>();

        //TODO: Generate random fields
        generate();
        drawAsciiBoard();


        testNr = new FieldINumber(1, -1);
        testEm = new FieldEmpty(-1);
        testBo = new FieldBomb(-1);

        while(true){
            System.out.println("\n\n");

            System.out.println("Skriv kolonne 1-10");
            int x = tastatur.nextInt();
            tastatur.nextLine();
            System.out.println("Skriv række 1-16");
            int y = tastatur.nextInt();
            tastatur.nextLine();

            int number = (x  + (y-1) * 10)-1;
            System.out.println(""+number);

            try{
                //gameBoard.get(number).setShown();
                if(gameBoard.get(number).getClass().equals(testNr.getClass())){
                    System.out.println("It was a Number");
                    //gameBoard.get(number).setShown();
                    try{
                        visitField(number, direction.NON);
                    }catch (Exception ex){

                    }

                }else if(gameBoard.get(number).getClass().equals(testEm.getClass())){
                    System.out.println("It was Empty");
                        try{
                            visitField(number, direction.NON);
                        }catch (Exception ex){

                        }

                }else if(gameBoard.get(number).getClass().equals(testBo.getClass())){
                    System.err.println("It was Bomb");
                    System.exit(-1);

                }

            }catch (Exception ex){

            }

            drawAsciiBoard();
        }
    }
    private static void visitField(int number, direction direction){
        //Check if its already been shown
        if(gameBoard.get(number).isShown()){
            return;
        }

        //RIGHT
        //Testing if the number just went from one row to row++
        if(direction == direction.RIGHT){
            if(gameBoard.get(number).getClass().equals(testBo.getClass()) ||
                    number % 10 == 0 && number != 0){
                return;
            }
        }else if(direction == direction.LEFT){
            //LEFT
            //Testing if the number just went from one row to row--
            if(gameBoard.get(number).getClass().equals(testBo.getClass()) ||
                    number % 10 == 9){
                return;
            }
        }else if(direction == direction.UP){
            //UP
            //Testing if the number just went from one row to row--
            if(gameBoard.get(number).getClass().equals(testBo.getClass())){
                return;
            }
        }else if(direction == direction.DOWN){
            //DOWN
            //Testing if the number just went from one row to row++
            if(gameBoard.get(number).getClass().equals(testBo.getClass())){
                return;
            }
        }

        //They all need to check for these
        if(gameBoard.get(number).getClass().equals(testNr.getClass())){
            //If the field is a number, show it and return
            gameBoard.get(number).setShown();
            return;
        }else if(gameBoard.get(number).getClass().equals(testEm.getClass())){
            gameBoard.get(number).setShown();
        }

        //Go right
        if(direction == direction.LEFT){

        }else{
            try{
                visitField(number+1,direction.RIGHT);
            }catch(Exception ex){

            }
        }


        //Go left
        if(direction == direction.RIGHT){

        }else{
            try{
                visitField(number-1,direction.LEFT);
            }catch(Exception ex){

            }
        }

        //Go up
        if(direction == direction.DOWN){

        }else{
            try{
                visitField(number-10,direction.UP);
            }catch(Exception ex){

            }
        }
        //Go down
        try{
            visitField(number+10,direction.DOWN);
        }catch(Exception ex){

        }

    }

    private static void drawAsciiBoard() {
        FieldINumber testNr = new FieldINumber(1, -1);
        FieldEmpty testEm = new FieldEmpty(-1);
        FieldBomb testBo = new FieldBomb(-1);
        int testId = 0;
        for ( FieldInterface gameBoard:  gameBoard){
            if(testId%10 == 0 && testId != 0){
                System.out.println(" |"+(testId-1));
            }
            if(gameBoard.getClass().equals(testNr.getClass())){
                System.out.print(" "+gameBoard.getValue());
                //System.out.println("Det matchede number");
            }else if(gameBoard.getClass().equals(testEm.getClass())){
                System.out.print(" "+gameBoard.getValue());
                //.out.println("Det matchede Empty");
            }else if(gameBoard.getClass().equals(testBo.getClass())){
                System.out.print(" "+gameBoard.getValue());
                //System.out.println("Det matchede Bomb");
            }
            testId++;
        }
        System.out.println(" |"+(testId-1));
    }

    private static void generate() {
        int id = 0;
        //Række 0
        gameBoard.add(new FieldEmpty(id));        id++;
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(1,id));            id++;
        }
        for(int i = 0; i<2;i++){
            gameBoard.add(new FieldEmpty(id));            id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        // Række 1
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        for(int i = 0; i<2;i++){
            gameBoard.add(new FieldEmpty(id));            id++;
        }
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(1,id));            id++;
        }
        gameBoard.add(new FieldEmpty(id));        id++;
        // Række 2
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        for(int i = 0; i<2;i++){
            gameBoard.add(new FieldINumber(2,id));            id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(1,id));            id++;
        }
        gameBoard.add(new FieldEmpty(id));        id++;
        // Række 3
        for(int i = 0; i<2;i++){
            gameBoard.add(new FieldEmpty(id));            id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        // Række 4
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(1,id));            id++;
        }
        gameBoard.add(new FieldEmpty(id));        id++;
        // Række 5
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        for(int i = 0; i<5;i++){
            gameBoard.add(new FieldINumber(1,id));            id++;
        }
        // Række 6
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        // Række 7
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(4,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        // Række 8
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        // Række 9
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        // Række 10
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        // Række 11
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        // Række 12
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        // Række 13
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(4,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        // Række 14
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        // Række 15
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));
    }
}
