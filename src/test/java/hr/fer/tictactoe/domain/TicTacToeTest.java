package hr.fer.tictactoe.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class TicTacToeTest {

	private TicTacToe game;
	private View view;

	@BeforeEach
	void newGame() {
		view = Mockito.mock(View.class);
		game = new TicTacToe(view);
	}
	
	@Test
	void checkEmptyFieldPositionInNewGame() throws Exception {
		assertThat(game.getFieldValue("a1")).isEqualTo(" ");
	}
	
	@Nested
	class EnteringValidPosition {
		@Test
		void once() throws Exception {
			game.play("a1");
			
			assertThat(game.getFieldValue("a1")).isEqualTo("o");
		}
	
		@Test
		void forTwoPlayers() throws Exception {
			game.play("a1");
			game.play("b1");
			
			assertThat(game.getFieldValue("a1")).isEqualTo("o");
			assertThat(game.getFieldValue("b1")).isEqualTo("x");
		}
	
		@Test
		void multipleTimes() throws Exception {
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
	
	@Nested
	class EnteringInvalidPositions {
		@Test
		void outOfRange() throws Exception {
			game.play("a4");
			
			verify(view).displayInvalidPosition("a4");
		}
	
		@Test
		void samePositionTwice() throws Exception {
			game.play("a1");
			game.play("a1");
			
			verify(view).displayPositionAlreadyTaken("a1");
		}
	}
	
	@Nested
	class DisplayNextPlayer {
		@Test
		void inTheBeginningOfGame() throws Exception {
			game.start();
			
			verify(view).displayPlayerTurn("o");
		}
	
		@Test
		void afterOnePlay() throws Exception {
			game.play("a1");
			
			verify(view).displayPlayerTurn("x");
		}
	
		@Test
		void afterEachPlay() throws Exception {
			game.play("a1");
			game.play("a2");
			game.play("a3");
			
			InOrder inOrder = inOrder(view);
			inOrder.verify(view).displayPlayerTurn("x");
			inOrder.verify(view).displayPlayerTurn("o");
			inOrder.verify(view).displayPlayerTurn("x");
		}
	
		@Test
		void afterInvalidPlay() throws Exception {
			game.play("invalid position");
			
			verify(view).displayPlayerTurn("o");
		}
	}
	
	@Nested
	class DisplayBoard {
		@Test
		void inTheBeginningOfGame() throws Exception {
			game.start();
			
			verify(view).displayBoard(game);
		}

		@Test
		void afterOnePlay() throws Exception {
			game.play("a1");
			
			verify(view).displayBoard(game);
		}

		@Test
		void afterEachPlay() throws Exception {
			game.play("a1");
			game.play("a2");
			game.play("a3");
			
			verify(view, times(3)).displayBoard(game);
		}

		@Test
		void afterInvalidPlay() throws Exception {
			game.play("invalid position");
			
			verify(view).displayBoard(game);
		}
	}
	
	@Nested
	class NewGameBoardInitialization {
		@Test
		void checkPositions() throws Exception {
			//   o x
			// x o  
			//     o
			game = new TicTacToe(" oxxo   o", view);
			
			assertThat(game.getFieldValue("a1")).isEqualTo(" ");
			assertThat(game.getFieldValue("a2")).isEqualTo("o");
			assertThat(game.getFieldValue("a3")).isEqualTo("x");
			assertThat(game.getFieldValue("b1")).isEqualTo("x");
			assertThat(game.getFieldValue("b2")).isEqualTo("o");
			assertThat(game.getFieldValue("b3")).isEqualTo(" ");
			assertThat(game.getFieldValue("c1")).isEqualTo(" ");
			assertThat(game.getFieldValue("c2")).isEqualTo(" ");
			assertThat(game.getFieldValue("c3")).isEqualTo("o");
		}

		@Test
		void checkNextPlayerWithEmptyBoard() throws Exception {
			game = new TicTacToe("", view);
			game.start();
			
			verify(view).displayPlayerTurn("o");
		}

		@Test
		void checkNextPlayerX() throws Exception {
			game = new TicTacToe("o", view);
			game.start();
			
			verify(view).displayPlayerTurn("x");
		}

		@Test
		void checkNextPlayerO() throws Exception {
			game = new TicTacToe("ox", view);
			game.start();
			
			verify(view).displayPlayerTurn("o");
		}
	}
	
	@Nested
	class DetectingEndOfGame {
		@Test
		void detectingWinnerInFirstRow() throws Exception {
			game = new TicTacToe("oo xx", view);
			game.play("a3");
			
			verify(view).displayWinner("o");
		}

		@Test
		void detectingWinnerInSecondRow() throws Exception {
			game = new TicTacToe("oo "
					           + "xx "
					           + "o", view);
			game.play("b3");
			
			verify(view).displayWinner("x");
		}

		@Test
		void detectingWinnerInThirdRow() throws Exception {
			game = new TicTacToe("   "
					           + "xx "
					           + "oo", view);
			game.play("c3");
			
			verify(view).displayWinner("o");
		}

		@Test
		void detectingWinnerInFirstColumn() throws Exception {
			game = new TicTacToe("ox "
					           + "ox "
					           + "   ", view);
			game.play("c1");
			
			verify(view).displayWinner("o");
		}

		@Test
		void detectingWinnerInSecondColumn() throws Exception {
			game = new TicTacToe("ox "
					           + "oxo"
					           + "   ", view);
			game.play("c2");
			
			verify(view).displayWinner("x");
		}

		@Test
		void detectingWinnerInThirdColumn() throws Exception {
			game = new TicTacToe(" xo"
					           + " xo"
					           + "   ", view);
			game.play("c3");
			
			verify(view).displayWinner("o");
		}

		@Test
		void detectingWinnerInDiagonal1() throws Exception {
			game = new TicTacToe("ox "
					           + " ox"
					           + "   ", view);
			game.play("c3");
			
			verify(view).displayWinner("o");
		}

		@Test
		void detectingWinnerInDiagonal2() throws Exception {
			game = new TicTacToe(" ox"
					           + "oxo"
					           + "   ", view);
			game.play("c1");
			
			verify(view).displayWinner("x");
		}
	}
	
	@Test
	void detectEndOfGameWithNoWinner() throws Exception {
		game = new TicTacToe("oxo"
		                   + "xxo"
		                   + " ox", view);
		game.play("c1");

		verify(view).displayEndOfGameWithNoWinner();
	}

	@Test
	void checkNotEndOfGameInNewGame() throws Exception {
		assertThat(game.isFinishedGame()).isFalse();
	}
	
	@Test
	void checkEndOfGameWithoutWinner() throws Exception {
		game = new TicTacToe("oxo"
                           + "xxo"
                           + "oox", view);
		
		assertThat(game.isFinishedGame()).isTrue();		
	}

	@Test
	void checkEndOfGameWithWinner() throws Exception {
		game = new TicTacToe("ooo"
                           + "xx "
                           + "   ", view);
		
		assertThat(game.isFinishedGame()).isTrue();		
	}
}
