package MinesweeperLogic;

public class FieldBomb extends Field implements FieldInterface  {

    public FieldBomb(int fieldId) {
        this.fieldcharacter = '*';
        setId(fieldId);
    }


    @Override
    public int getFieldImgType() {
        // If its shown, return the img for bomb
        if (this.isShown()){
            return 9;
        }else if(this.isFlagSet()) {
            return 11;
        }else{
            // If the field is not shown and the flag is not set
            // Return the std. img for a field
            return 10;
        }
    }
}
