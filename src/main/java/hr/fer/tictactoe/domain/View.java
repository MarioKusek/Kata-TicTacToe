package hr.fer.tictactoe.domain;

public interface View {

	public void displayInvalidPosition(String position);

	public void displayPositionAlreadyTaken(String position);

	public void displayPlayerTurn(String nextPlayer);

	public void displayBoard(Board board);

	public void displayWinner(String winner);

	public void displayEndOfGameWithNoWinner();

}
