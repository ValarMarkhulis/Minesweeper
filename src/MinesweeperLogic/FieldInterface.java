package MinesweeperLogic;

public interface FieldInterface {

    void setId(int id);
    int getId();
    void setValue(char character);
    char getValue();
    void setShown();
    boolean isShown();
    boolean isFlagSet();
    void setFlagSet(boolean flagSet);

}
