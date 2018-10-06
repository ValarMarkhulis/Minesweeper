package MinesweeperLogic;

public class FieldINumber extends Field implements FieldInterface {

    public FieldINumber(int fieldNumber, int fieldId) {
        this.fieldcharacter = (char) (fieldNumber + '0');
        setId(fieldId);
    }


}
