package MinesweeperLogic;

public class FieldEmpty extends Field implements FieldInterface{

    public FieldEmpty(int fieldId) {
        this.fieldcharacter = '_';
        setId(fieldId);
    }


    @Override
    public int getFieldImgType() {
        // If its shown, return the img for empty
        if (this.isShown()){
            return 0;
        }else if(this.isFlagSet()) {
            return 11;
        }else{
            // If the field is not shown and the flag is not set
            // Return the std. img for a field
            return 10;
        }
    }
}
