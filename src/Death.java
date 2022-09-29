public class Death extends Square {
    public Death(int pos, Board b){
        super(pos, b);
        System.out.println("Death square added at postion: " + (pos + 1));
    }

    @Override
    public boolean isDeath(){
        return true;
    }
}
