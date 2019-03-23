package hr.fer.tictactoe.domain;

import java.util.HashMap;
import java.util.Map;
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
		for (int i = 0; i < stringBoard.length(); i++) {
			if(stringBoard.charAt(i) != ' ') {
				int column = i % 3;
				int row = i / 3;
				
				String position = "" + (char)(row + 'a') + (char)(column + '1');
				
				board.put(position, Character.toString(stringBoard.charAt(i)));
			}
		}
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
			changePlayer();
		}
		
		view.displayBoard(this);
		view.displayPlayerTurn(currentPlayer);
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
}
