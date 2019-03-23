package hr.fer.tictactoe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {

	private TicTacToe game;
	
	@BeforeEach
	void newGame() {
		game = new TicTacToe();
	}

	@Test
	void emptyFieldPositionInNewGame() throws Exception {
		assertThat(game.getFieldValue("a1")).isEqualTo("");
	}

	@Test
	void enterFieldPosition() throws Exception {
		game.play("a1");
		
		assertThat(game.getFieldValue("a1")).isEqualTo("o");
	}

	@Test
	void enterPositionForTwoPlayers() throws Exception {
		game.play("a1");
		game.play("b1");
		
		assertThat(game.getFieldValue("a1")).isEqualTo("o");
		assertThat(game.getFieldValue("b1")).isEqualTo("x");
	}

	@Test
	void enterPositionsForMultipleMoves() throws Exception {
		game.play("a1");
		game.play("b1");
		game.play("a2");
		game.play("b3");
		
		assertThat(game.getFieldValue("a1")).isEqualTo("o");
		assertThat(game.getFieldValue("b1")).isEqualTo("x");
		assertThat(game.getFieldValue("a2")).isEqualTo("o");
		assertThat(game.getFieldValue("b3")).isEqualTo("x");
	}
}
