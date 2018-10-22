package MinesweeperLogic;

public interface FieldInterface {

    void setId(int id);
    int getId();
    void setValue(char character);
    char getValue();
    int getFieldImgType(boolean endGame);
    void setShown();
    boolean isShown();
    boolean isFlagSet();
    void setFlagSet(boolean flagSet);
    boolean isPicked();
    void setPicked(boolean picked);

}
