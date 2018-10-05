package MinesweeperLogic;

public class Field {
    private boolean flagSet = false;
    private boolean isShown = false;
    private int fieldId = 0;

    public void setId(int id) {
        fieldId = id;
    }

    public int getId() {
        return fieldId;
    }
}
