package MinesweeperLogic;

public class FieldBomb extends Field implements FieldInterface  {

    public FieldBomb(int fieldId) {
        this.fieldcharacter = '*';
        setId(fieldId);
    }


}
