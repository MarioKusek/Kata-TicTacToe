package hr.fer.tictactoe.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;

public class TicTacToe implements Board {
	public static Pattern POSITION_PATTERN = Pattern.compile("\\A[abc][123]\\z");
	
	private String currentPlayer = "o";
	private Map<String, String> board = new HashMap<>();
	private View view;

	public TicTacToe(View view) {
		this.view = view;
	}
		
	public TicTacToe(String stringBoard, View view) {
		this.view = view;
		int numberOfPlayedPositions = 0;
		for (int i = 0; i < stringBoard.length(); i++) {
			if(stringBoard.charAt(i) != ' ') {
				int column = i % 3;
				int row = i / 3;
				
				String position = "" + (char)(row + 'a') + (char)(column + '1');
				
				board.put(position, Character.toString(stringBoard.charAt(i)));
				numberOfPlayedPositions++;
			}
		}
		
		currentPlayer = numberOfPlayedPositions%2 == 0 ? "o": "x";
	}

	public String getFieldValue(String position) {
		String value = board.get(position);
		if(value == null)
			return " ";
		else 
			return value;
	}

	public void play(String position) {
		if(!isPositionInRange(position)) {
			view.displayInvalidPosition(position);
		} else if (isPositionOccupied(position)) {
			view.displayPositionAlreadyTaken(position);
		} else {
			board.put(position, currentPlayer);
			if(detectWinner()) {
				view.displayWinner(currentPlayer);
				return;
			} else {
				if(detectWithNoWinnerEnd()) {
					view.displayEndOfGameWithNoWinner();
					return;
				} else {
					changePlayer();
				}
			}
		}
		
		view.displayBoard(this);
		view.displayPlayerTurn(currentPlayer);
	}

	private boolean detectWithNoWinnerEnd() {
		return board.size() == 9;
	}

	private boolean detectWinner() {
		if(areSameInPositions("a1", "a2", "a3") 
				|| areSameInPositions("b1", "b2", "b3")
				|| areSameInPositions("c1", "c2", "c3")
				|| areSameInPositions("a1", "b1", "c1")
				|| areSameInPositions("a2", "b2", "c2")
				|| areSameInPositions("a3", "b3", "c3")
				|| areSameInPositions("a1", "b2", "c3")
				|| areSameInPositions("a3", "b2", "c1")
		) {
			return true;
		} else {
			return false;
		}
	}

	private boolean areSameInPositions(String position1, String position2, String position3) {
		String value = board.get(position1);
		return value != null &&
				Objects.equals(value, board.get(position2)) &&
				Objects.equals(value, board.get(position3));
	}

	private boolean isPositionOccupied(String position) {
		return board.containsKey(position);
	}

	private boolean isPositionInRange(String position) {
		return POSITION_PATTERN.matcher(position).lookingAt();
	}

	private void changePlayer() {
		if(currentPlayer.equals("o"))
			currentPlayer = "x";
		else
			currentPlayer = "o";
	}

	public void start() {
		view.displayBoard(this);
		view.displayPlayerTurn(currentPlayer);
	}

	public boolean isFinishedGame() {
		return detectWithNoWinnerEnd();
	}
}
