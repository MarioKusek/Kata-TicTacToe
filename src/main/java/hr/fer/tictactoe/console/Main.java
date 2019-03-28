package hr.fer.tictactoe.console;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import hr.fer.tictactoe.domain.TicTacToe;

public class Main {

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe(
				new ConsoleView(new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true)));
		
		game.start();
		try(Scanner scanner = new Scanner(System.in)) {
			while(!game.isFinishedGame()) {
				System.out.println("Enter your move:");
				String line = scanner.nextLine();
				game.play(line);
			}
		}
	}
}
