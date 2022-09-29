public class SnakeOrLadder extends Square {
	private final int transport;
	private boolean isSnake;
	public SnakeOrLadder(int pos, Board b, int trans, boolean isSnake) {
		super(pos, b);
		assert trans > 0;
		transport = trans;
        this.isSnake = isSnake;
	}

	@Override
	protected Square landHereOrGoHome() {
		System.out.println((isSnake ? "snake" : "ladder") + "from " + (position+1) + " to "
						+ (destination().getPosition()+1));
		return destination().landHereOrGoHome();
	}

	private Square destination() {
		return findRelativeSquare(transport);
	}
}
