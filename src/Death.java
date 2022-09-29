public class Death extends Square {
    public Death(int pos, Board b){
        super(pos, b);
    }

    @Override
    public boolean isDeath(){
        return true;
    }
}
