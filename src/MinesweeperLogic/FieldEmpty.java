package MinesweeperLogic;

public class FieldEmpty extends Field implements FieldInterface{

    public FieldEmpty(int fieldId) {
        this.fieldcharacter = '_';
        setId(fieldId);
    }


}
