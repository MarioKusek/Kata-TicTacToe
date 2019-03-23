package hr.fer.tictactoe.domain;

public interface View {

	public void displayInvalidPosition(String position);

	public void displayPositionAlreadyTaken(String position);

}
