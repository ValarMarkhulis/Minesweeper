package MinesweeperLogic;

public class FieldINumber extends Field implements FieldInterface {
    private int fieldNumber;

    public FieldINumber(int fieldNumber, int fieldId) {
        this.fieldNumber = fieldNumber;
        setId(fieldId);
    }

    void setTal(int number){
        fieldNumber = number;
    }
    int getTal(){
        return fieldNumber;
    }
}
