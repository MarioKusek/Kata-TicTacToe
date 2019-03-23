package hr.fer.tictactoe.console;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

import hr.fer.tictactoe.domain.Board;
import hr.fer.tictactoe.domain.TicTacToe;

public class ConsoleViewTest {
	@Test
	void displayInvalidPosition() throws Exception {
		StringWriter outBuffer = new StringWriter();
		ConsoleView view = new ConsoleView(new PrintWriter(outBuffer));
		
		view.displayInvalidPosition("a6");
		
		assertThat(outBuffer.toString()).isEqualTo("Wrong position 'a6'. You can enter values from 'a1' to 'c3'");
	}

	@Test
	void displayPlayerTurn() throws Exception {
		StringWriter outBuffer = new StringWriter();
		ConsoleView view = new ConsoleView(new PrintWriter(outBuffer));
		
		view.displayPlayerTurn("x");
		
		assertThat(outBuffer.toString()).isEqualTo("Next player is 'x'.");
	}

	@Test
	void displayBoard() throws Exception {
		StringWriter outBuffer = new StringWriter();
		ConsoleView view = new ConsoleView(new PrintWriter(outBuffer));
		
		view.displayBoard(new TicTacToe("oo xx   o", null));

		assertThat(outBuffer.toString()).isEqualTo(
				"  1 2 3\n" + 
				"a o|o| \n" + 
				"  -----\n" + 
				"b x|x| \n" + 
				"  -----\n" + 
				"c  | |o\n");
	}
	
	
}
