package hr.fer.tictactoe.console;

import java.io.PrintWriter;

public class ConsoleView {

	private PrintWriter out;

	public ConsoleView(PrintWriter out) {
		this.out = out;
	}

	public void displayInvalidPosition(String position) {
		out.printf("Wrong position '%s'. You can enter values from 'a1' to 'c3'", position);
	}
}
