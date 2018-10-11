package MinesweeperLogic;

public class Field {
    private boolean flagSet = false;
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
        }else if((flagSet)){
            return '\u2691';
        }else{
            return '\u25A0';
        }
    }
    public void setValue(char character) {fieldcharacter = character;}

    public void setShown() {isShown = true;}
    public boolean isShown() {return isShown; }

    public boolean isFlagSet() {return flagSet;}
    public void setFlagSet(boolean flagSet) {this.flagSet = flagSet;}
}
