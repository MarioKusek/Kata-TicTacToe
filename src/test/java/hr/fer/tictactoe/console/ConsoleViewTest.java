package hr.fer.tictactoe.console;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hr.fer.tictactoe.domain.TicTacToe;

public class ConsoleViewTest {
	private StringWriter outBuffer;
	private ConsoleView view;
	
	@BeforeEach
	void setupOutputBuffer() {
		outBuffer = new StringWriter();		
		view = new ConsoleView(new PrintWriter(outBuffer));
	}

	@Test
	void displayInvalidPosition() throws Exception {
		view.displayInvalidPosition("a6");
		
		assertThat(outBuffer.toString()).isEqualTo("Wrong position 'a6'. You can enter values from 'a1' to 'c3'\n");
	}

	@Test
	void displayPlayerTurn() throws Exception {
		view.displayPlayerTurn("x");
		
		assertThat(outBuffer.toString()).isEqualTo("Next player is 'x'.\n");
	}

	@Test
	void displayBoard() throws Exception {
		view.displayBoard(new TicTacToe("oo xx   o", null));

		assertThat(outBuffer.toString()).isEqualTo(
				"  1 2 3\n" + 
				"a o|o| \n" + 
				"  -----\n" + 
				"b x|x| \n" + 
				"  -----\n" + 
				"c  | |o\n");
	}
	
	@Test
	void displayWinner() throws Exception {
		view.displayWinner("o");

		assertThat(outBuffer.toString()).isEqualTo("The winner is player 'o'!!!!\n");
	}

	@Test
	void displayEndOfGameWithNoWinner() throws Exception {
		view.displayEndOfGameWithNoWinner();
		
		assertThat(outBuffer.toString()).isEqualTo("End of game!!! No winners!!!\n");
	}

	@Test
	void displayPositionAlreadyTaken() throws Exception {
		view.displayPositionAlreadyTaken("a1");
		
		assertThat(outBuffer.toString()).isEqualTo("Position 'a1' is already taken!\n");
	}
}

