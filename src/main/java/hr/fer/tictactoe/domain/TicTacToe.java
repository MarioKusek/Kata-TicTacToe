package hr.fer.tictactoe.domain;

import java.util.HashMap;
import java.util.Map;

public class TicTacToe {
	Map<String, String> board = new HashMap<>();

	public String getFieldValue(String position) {
		String value = board.get(position);
		if(value == null)
			return "";
		else 
			return "o";
	}

	public void play(String position) {
		board.put(position, "o");
	}
}
