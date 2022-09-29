import java.util.ArrayList;

public final class Board {
	private ArrayList<Square> squares = new ArrayList<>();
	private static final int MIN_NUM_SQUARES = 10;

	public Board(int numSquares, int[][] ladders, int[][] snakes) {
		assert numSquares > MIN_NUM_SQUARES : "There must be at least " + MIN_NUM_SQUARES + " squares";
		makeSquares(numSquares);
		// makeLadders(ladders);
		// makeSnakes(snakes);

		makeSnakesOrLadders(ladders, false);
		makeSnakesOrLadders(ladders, true);
	}

	public Square firstSquare() {
		return squares.get(0);
	}

	public Square lastSquare() {
		return squares.get(squares.size()-1);
	}

	public Square findSquare(int position) {
		assert (position>0) && (position<numberOfSquares()) : "inexistent square";
		return squares.get(position);
	}

	public int numberOfSquares() {
		assert !squares.isEmpty();
		return squares.size();
	}

	private void makeSquares(int numSquares) {
		System.out.println("There are " + numSquares + " squares");
		squares.add(new FirstSquare(this));
		for (int position=1 ; position<numSquares ; position++) {
			Square square = new Square(position, this);
			squares.add(square);
		}
		assert squares.get(numSquares-1).isLastSquare();
	}

    private void makeSnakes(int[][] snakes) {
		for (int i=0; i<snakes.length ; i++) {
			assert snakes[i].length == 2;
			
			int fromPosition = snakes[i][0]-1;
			int toPosition = snakes[i][1]-1;
			int transport = toPosition - fromPosition;
			
			assert transport<0 : "In snake, destination equal or after origin";
			assert (toPosition > 0) && (toPosition<numberOfSquares()-1);
			assert (fromPosition < numberOfSquares()-1) && (fromPosition>0);

			System.out.println("snake from " + (fromPosition+1) + " to " + (toPosition+1));
			squares.set(fromPosition, new Snake(fromPosition,this, transport));
		}
    }
	
    private void makeLadders(int[][] ladders) {
		for (int i=0; i<ladders.length; i++) {
			assert ladders[i].length == 2;
			
			int fromPosition = ladders[i][0]-1;
			int toPosition = ladders[i][1]-1;
			int transport = toPosition - fromPosition;
			
			assert transport>0 : "In ladder, origin equal or after destination";
			assert (toPosition < numberOfSquares()) && (toPosition > 0);
			assert (fromPosition > 0) && (fromPosition < numberOfSquares());

			System.out.println("ladder from " + (fromPosition+1) + " to " + (toPosition+1));
			squares.set(fromPosition, new Ladder(fromPosition,this, transport));
		}
    }    

    private void makeSnakesOrLadders(int[][] ladders, boolean isSnake) {
		for (int i=0; i<ladders.length; i++) {
			assert ladders[i].length == 2;
			
			int fromPosition = ladders[i][0]-1;
			int toPosition = ladders[i][1]-1;
			int transport = toPosition - fromPosition;
			if(isSnake){
				assert transport<0 : "In snake, destination equal or after origin";
				assert (toPosition > 0) && (toPosition<numberOfSquares()-1);
				assert (fromPosition < numberOfSquares()-1) && (fromPosition>0);
			}else{
				assert transport>0 : "In ladder, origin equal or after destination";
				assert (toPosition < numberOfSquares()) && (toPosition > 0);
				assert (fromPosition > 0) && (fromPosition < numberOfSquares());
			}

			System.out.println((isSnake ? "snake" : "ladder") + "from " + (fromPosition+1) + " to " + (toPosition+1));
			squares.set(fromPosition, new SnakeOrLadder(fromPosition,this, transport, isSnake));
		}
	}
}
