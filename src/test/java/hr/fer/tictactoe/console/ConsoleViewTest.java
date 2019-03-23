package hr.fer.tictactoe.console;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

public class ConsoleViewTest {
	@Test
	void displayInvalidPosition() throws Exception {
		StringWriter outBuffer = new StringWriter();
		ConsoleView view = new ConsoleView(new PrintWriter(outBuffer));
		
		view.displayInvalidPosition("a6");
		
		assertThat(outBuffer.toString()).isEqualTo("Wrong position 'a6'. You can enter values from 'a1' to 'c3'");
	}
}
