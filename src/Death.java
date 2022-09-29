public class Death extends Square {
    public Death(int pos, Board b){
        super(pos, b);
        System.out.println("Death square added at postion: " + pos);
    }

    @Override
    public boolean isDeath(){
        return true;
    }
}
