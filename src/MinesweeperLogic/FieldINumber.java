package MinesweeperLogic;

public class FieldINumber extends Field implements FieldInterface {

    private int fieldNumber;

    public FieldINumber(int fieldNumber, int fieldId) {
        this.fieldNumber = fieldNumber;

        this.fieldcharacter = (char) (fieldNumber + '0');
        setId(fieldId);
    }


    @Override
    public int getFieldImgType() {
        // If its shown, return the numeric number
        if (this.isShown()){
            return fieldNumber;
        }else if(this.isFlagSet()) {
            return 11;
        }else{
            // If the field is not shown and the flag is not set
            // Return the std. img for a field
            return 10;
        }
    }
}
