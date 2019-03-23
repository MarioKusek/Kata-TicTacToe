package hr.fer.tictactoe.domain;

import java.util.HashMap;
import java.util.Map;

public class TicTacToe {
	String currentPlayer = "o";
	Map<String, String> board = new HashMap<>();

	public String getFieldValue(String position) {
		String value = board.get(position);
		if(value == null)
			return "";
		else 
			return value;
	}

	public void play(String position) {
		board.put(position, currentPlayer);
		changePlayer();
	}

	private void changePlayer() {
		if(currentPlayer.equals("o"))
			currentPlayer = "x";
		else
			currentPlayer = "o";
	}
}
