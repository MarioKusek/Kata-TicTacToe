package hr.fer.tictactoe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import static hr.fer.tictactoe.domain.TicTacToe.POSITION_PATTERN;

import org.junit.jupiter.api.Test;

public class PositionFormatTests {	
	@Test
	void validPositions() throws Exception {
		assertThat(POSITION_PATTERN.matcher("a1").lookingAt()).isTrue();
		assertThat(POSITION_PATTERN.matcher("b2").lookingAt()).isTrue();
		assertThat(POSITION_PATTERN.matcher("c3").lookingAt()).isTrue();
	}

	@Test
	void invalidPositions() throws Exception {
		assertThat(POSITION_PATTERN.matcher("d1").lookingAt()).isFalse();
		assertThat(POSITION_PATTERN.matcher("b4").lookingAt()).isFalse();
		assertThat(POSITION_PATTERN.matcher("c0").lookingAt()).isFalse();
	}

	@Test
	void invalidPositionsWithExtraCharacters() throws Exception {
		assertThat(POSITION_PATTERN.matcher("a1 ").lookingAt()).isFalse();
		assertThat(POSITION_PATTERN.matcher("a1\n").lookingAt()).isFalse();
		assertThat(POSITION_PATTERN.matcher(" a1").lookingAt()).isFalse();
	}
}
