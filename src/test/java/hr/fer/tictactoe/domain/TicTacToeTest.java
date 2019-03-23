package hr.fer.tictactoe.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TicTacToeTest {

	private TicTacToe game;
	private View view;

	@BeforeEach
	void newGame() {
		view = Mockito.mock(View.class);
		game = new TicTacToe(view);
	}
	
	@Nested
	class ChackingEnteringValidPositions {

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
	
	@Test
	void invalidPositionOutOfRange() throws Exception {

		game.play("a4");
		
		verify(view).displayInvalidPosition("a4");
	}
}
