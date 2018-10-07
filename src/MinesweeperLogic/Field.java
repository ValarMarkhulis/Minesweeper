package MinesweeperLogic;

public class Field {
    private boolean flagSet = false;

    public boolean isShown() {
        return isShown;
    }

    private boolean isShown = false;
    private int fieldId = 0;
    char fieldcharacter = '?';

    public void setId(int id) {
        fieldId = id;
    }

    public int getId() {
        return fieldId;
    }

    public char getValue() {
        if (isShown){
            return fieldcharacter;
        }else{
            return '\u25A0';
        }
    }
    public void setValue(char character) {
        fieldcharacter = character;
    }

    public void setShown() {
        isShown = true;
    }
}
