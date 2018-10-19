package MinesweeperLogic;

import java.util.ArrayList;



public class Game {

    public ArrayList<FieldInterface> gameBoard;
    //static int Array[][];
    static FieldINumber testNr;
    static FieldEmpty testEm;
    static FieldBomb testBo;
    static int Boombsleft = 24;
    enum direction{RIGHT,LEFT,DOWN,UP,NON, RIGHTUP,RIGHTDOWN, LEFTUP, LEFTDOWN };

    public Game (){

        //TODO: Get input though the UI instead of commandline
        //Get input
        java.util.Scanner tastatur = new java.util.Scanner(System.in);



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
        gameBoard.get(12).setFlagSet(true); Boombsleft--;
        //gameBoard.get(37).setFlagSet(true);
        drawAsciiBoard();
        testNr = new FieldINumber(1, -1);
        testEm = new FieldEmpty(-1);
        testBo = new FieldBomb(-1);

        /*
        while(Boombsleft != 0){
            System.out.println("\n\n");
            System.out.println("Antal bomber: "+Boombsleft);

            System.out.println("Write 1 for flag and 0 for guess");
            int flagOrNot = tastatur.nextInt();
            System.out.println("Which column: 1-10");
            int x = tastatur.nextInt();
            tastatur.nextLine();
            System.out.println("Which row: 1-16");
            int y = tastatur.nextInt();
            tastatur.nextLine();

            int number = (x  + (y-1) * 10)-1;
            System.out.println(""+number);


        */
    }
    public int toggleFlag(int fieldID){
        boolean flagstatus = !gameBoard.get(fieldID).isFlagSet();
        gameBoard.get(fieldID).setFlagSet(flagstatus);
        if(flagstatus && gameBoard.get(fieldID).getClass().equals(testBo.getClass())){
            Boombsleft--;
        }else if(!flagstatus && gameBoard.get(fieldID).getClass().equals(testBo.getClass())){
            Boombsleft++;
        }
        System.out.println(Boombsleft);
        if(Boombsleft == 0){
            return 0;
        }else{
            return 1;
        }
    }

    public int guess(int fieldID){
        try{
            if(gameBoard.get(fieldID).isFlagSet()){
                System.out.println("You cant guess on a field with a flag on it!");
            }else if(gameBoard.get(fieldID).getClass().equals(testNr.getClass()) ){
                try{
                    if(gameBoard.get(fieldID).isShown()){
                        visitShownField(fieldID);
                    }else{
                        System.out.println("It was a Number was CALLED!");
                        visitField(fieldID, direction.NON);
                    }
                }catch (Exception ex){

                }

            }else if(gameBoard.get(fieldID).getClass().equals(testEm.getClass())){
                try{
                    if(gameBoard.get(fieldID).isShown()){
                        System.out.println("You can't press a empty field!");
                    }else{
                        System.out.println("It was a Empty");
                        visitField(fieldID, direction.NON);
                    }

                }catch (Exception ex){

                }

            }else if(gameBoard.get(fieldID).getClass().equals(testBo.getClass())){
                gameBoard.get(fieldID).setShown();
                System.err.println("It was a Bomb");
                return -1;
            }

        }catch (Exception ex){

        }
        return 0;

    }

    /**
     * This function will be called if the player presses a number which is shown. The function
     * will then visit all surrounding fields and shown them if they are number or empty, but
     * the player will lose if one of the fields is a bomb.
     * @param number
     */
    private void visitShownField(int number) {

        //Visit RIGHT
        if((number+1) % 10 != 0 && (number+1) != 0){
            testField(number+1);
        }

        //Visit LEFT
        if((number-1) % 10 != 9){
            testField(number-1);
        }

        //Visit UP
        System.out.println("Visit up blev kaldt");
        testField(number-10);

        //Visit DOWN
        System.out.println("Visit downn blev kaldt");
        testField(number+10);

        //Visit UP RIGHT
        System.out.println("Visit UP RIGHT blev kaldt");
        if((number-9) % 10 != 0){
            testField(number-9);
        }

        //Visit UP LEFT
        System.out.println("Visit UP LEFT blev kaldt");
        if((number-11) % 10 != 9){
            testField(number-11);
        }

        //Visit DOWN RIGHT
        System.out.println("Visit DOWN RIGHT blev kaldt");
        if((number+11) % 10 != 0){
            testField(number+11);
        }

        //Visit DOWN LEFT
        System.out.println("Visit DOWN LEFT blev kaldt");
        if((number+9) % 10 != 9){
            testField(number+9);
        }


    }

    private void testField(int number) {
        try{
            //If the flag has been set skip it
            if(gameBoard.get(number).isFlagSet()){
                return;
            }else if(gameBoard.get(number).getClass().equals(testBo.getClass())){
                gameBoard.get(number).setShown();
                System.err.println("It was a Bomb");
                //System.exit(-1);
            }else if(gameBoard.get(number).getClass().equals(testEm.getClass())){
                System.out.println("Test kaldt");
                visitField(number, direction.NON);
            }else if(gameBoard.get(number).getClass().equals(testNr.getClass())){
                System.out.println("A number field has been reveald!");
                gameBoard.get(number).setShown();
            }
        }catch (Exception ex){

        }
    }

    private void visitField(int number, direction direction){
        //Check if its already been shown or a flag has been set
        if( gameBoard.get(number).isFlagSet() ||
                gameBoard.get(number).isShown() ){
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
        }else if(direction == direction.RIGHTUP){
            //RIGHT UP
            if(gameBoard.get(number).getClass().equals(testBo.getClass())||
                    number % 10 == 0){
                return;
            }
        }else if(direction == direction.LEFTUP){
            //LEFT UP
            if(gameBoard.get(number).getClass().equals(testBo.getClass()) ||
                    number % 10 == 9){
                return;
            }
        }else if(direction == direction.RIGHTDOWN){
            //LEFT UP
            if(gameBoard.get(number).getClass().equals(testBo.getClass()) ||
                    number % 10 == 0){
                return;
            }
        }else if(direction == direction.LEFTDOWN){
            //LEFT UP
            if(gameBoard.get(number).getClass().equals(testBo.getClass()) ||
                    number % 10 == 9){
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

        if(direction == direction.UP){
        }else {
            //Go down
            try{
                visitField(number+10,direction.DOWN);
            }catch(Exception ex){

            }
        }

        //Go up and right
        if(direction == direction.LEFTDOWN){
        }else{
            try{
                visitField((number-10)+1,direction.RIGHTUP);
            }catch(Exception ex){

            }
        }

        //Go up and left
        if(direction == direction.RIGHTDOWN){
        }else {

            try {
                visitField((number - 10) - 1, direction.LEFTUP);
            } catch (Exception ex) {

            }
        }

        //Go down and right
        if(direction == direction.LEFTUP){
        }else {

            try {
                visitField((number + 10) + 1, direction.RIGHTDOWN);
            } catch (Exception ex) {

            }
        }

        //Go down and left
        if(direction == direction.RIGHTUP){
        }else {

            try {
                visitField((number + 10) - 1, direction.LEFTDOWN);
            } catch (Exception ex) {

            }
        }
    }

    private void drawAsciiBoard() {
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

    private void generate() {
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
    private void generate2() {
        int id = 0;
        //Række 0
        for(int i = 0; i<4;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(1,id));        id++;
        }
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }

        // Række 1
        for(int i = 0; i<4;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }

        // Række 2
        for(int i = 0; i<4;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(1,id));        id++;
        }

        // Række 3
        for(int i = 0; i<5;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;

        // Række 4
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        for(int i = 0; i<4;i++){
            gameBoard.add(new FieldINumber(1,id));        id++;
        }

        // Række 5
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        for(int i = 0; i<4;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }

        // Række 6
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;

        // Række 7
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(4,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;

        // Række 8
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(4,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;

        // Række 9
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(1,id));        id++;
        }

        // Række 10
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(1,id));        id++;
        }

        // Række 11
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldEmpty(id));        id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;

        // Række 12
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;

        // Række 13
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        for(int i = 0; i<4;i++){
            gameBoard.add(new FieldINumber(1,id));        id++;
        }
        for(int i = 0; i<3;i++){
            gameBoard.add(new FieldINumber(2,id));        id++;
        }
        gameBoard.add(new FieldINumber(1,id));        id++;

        // Række 14
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(3,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;

        // Række 15
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldEmpty(id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;
        gameBoard.add(new FieldBomb(id));        id++;
        gameBoard.add(new FieldINumber(2,id));        id++;
        gameBoard.add(new FieldINumber(1,id));        id++;

    }
}
