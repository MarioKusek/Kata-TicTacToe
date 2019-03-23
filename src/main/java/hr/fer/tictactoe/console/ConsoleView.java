package hr.fer.tictactoe.console;

import java.io.PrintWriter;

import hr.fer.tictactoe.domain.Board;

public class ConsoleView {

	private PrintWriter out;

	public ConsoleView(PrintWriter out) {
		this.out = out;
	}

	public void displayInvalidPosition(String position) {
		out.printf("Wrong position '%s'. You can enter values from 'a1' to 'c3'\n", position);
	}
	
	public void displayPlayerTurn(String nextPlayer) {
		out.printf("Next player is '%s'.\n", nextPlayer);
	}

	public void displayBoard(Board board) {
		out.println("  1 2 3");
		out.printf("a %s|%s|%s\n", board.getFieldValue("a1"), board.getFieldValue("a2"), board.getFieldValue("a3"));
		out.println("  -----");
		out.printf("b %s|%s|%s\n", board.getFieldValue("b1"), board.getFieldValue("b2"), board.getFieldValue("b3"));
		out.println("  -----");
		out.printf("c %s|%s|%s\n", board.getFieldValue("c1"), board.getFieldValue("c2"), board.getFieldValue("c3"));
	}
	
	public void displayWinner(String winner) {
		out.printf("The winner is player '%s'!!!!\n", winner);
	}
	
	public void displayEndOfGameWithNoWinner() {
		out.println("End of game!!! No winners!!!");
	}

}
