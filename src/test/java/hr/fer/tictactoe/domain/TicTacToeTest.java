package hr.fer.tictactoe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TicTacToeTest {

	@Test
	void emptyFieldPositionInNewGame() throws Exception {
		TicTacToe game = new TicTacToe();
		
		assertThat(game.getFieldValue("a1")).isEqualTo("");
	}

	@Test
	void enterFieldPosition() throws Exception {
		TicTacToe game = new TicTacToe();
		
		game.play("a1");
		
		assertThat(game.getFieldValue("a1")).isEqualTo("o");
	}
}
