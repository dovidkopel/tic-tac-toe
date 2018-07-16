package com.dovidkopel.tictactoe.functional;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

public class FunctionalTicTacToeTest {
	private FunctionalTicTacToe ttt = new FunctionalTicTacToe();

	private static char empty = FunctionalTicTacToe.empty;

	@Test
	public void getWinnerEmpty() {
		ttt.clearBoard();
		Assert.assertEquals(FunctionalTicTacToe.empty, ttt.getWinner());
	}

	@Test
	public void getRowTest() {
		ttt.clearBoard();
		Assert.assertArrayEquals(ttt.getEmpty(), ttt.getRow(0));
		Assert.assertEquals(empty, ttt.getWinner());

		ttt.makeMove('x', 0);
		ttt.makeMove('x', 1);
		ttt.makeMove('x', 2);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x'}, ttt.getRow(0));
		Assert.assertEquals('x', ttt.getWinner());

		ttt.clearBoard();
		ttt.makeMove('o', 3);
		ttt.makeMove('o', 4);
		ttt.makeMove('o', 5);
		Assert.assertArrayEquals(new char[] {'o', 'o', 'o'}, ttt.getRow(1));
		Assert.assertEquals('o', ttt.getWinner());

		ttt.clearBoard();
		ttt.makeMove('x', 6);
		ttt.makeMove('x', 7);
		ttt.makeMove('x', 8);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x'}, ttt.getRow(2));
		Assert.assertEquals('x', ttt.getWinner());
	}

	@Test
	public void getColumnTest() {
		ttt.clearBoard();
		Assert.assertArrayEquals(ttt.getEmpty(), ttt.getColumn(0));
		Assert.assertEquals(empty, ttt.getWinner());

		ttt.makeMove('x', 0);
		ttt.makeMove('x', 3);
		ttt.makeMove('x', 6);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x'}, ttt.getColumn(0));
		Assert.assertEquals('x', ttt.getWinner());

		ttt.clearBoard();
		ttt.makeMove('o', 1);
		ttt.makeMove('o', 4);
		ttt.makeMove('o', 7);
		Assert.assertArrayEquals(new char[] {'o', 'o', 'o'}, ttt.getColumn(1));
		Assert.assertEquals('o', ttt.getWinner());

		ttt.clearBoard();
		ttt.makeMove('x', 2);
		ttt.makeMove('x', 5);
		ttt.makeMove('x', 8);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x'}, ttt.getColumn(2));
		Assert.assertEquals('x', ttt.getWinner());
	}

	@Test
	public void diagonolTest() {
		ttt.clearBoard();
		Assert.assertEquals(empty, ttt.getWinner());

		/***
		 * 0 1 2
		 * 3 4 5
		 * 6 7 8
		 */
		ttt.makeMove('x', 0);
		ttt.makeMove('x', 4);
		ttt.makeMove('x', 8);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x'}, ttt.getDiagonal1());
		Assert.assertEquals('x', ttt.getWinner());

		ttt.clearBoard();
		ttt.makeMove('x', 2);
		ttt.makeMove('x', 4);
		ttt.makeMove('x', 6);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x'}, ttt.getDiagonal2());
		Assert.assertEquals('x', ttt.getWinner());

		/**
		 * 0  1  2  3
		 * 4  5  6  7
		 * 8  9  10 11
		 * 12 13 14 15
		 */
		FunctionalTicTacToe ttt4 = new FunctionalTicTacToe(4, 'x');
		ttt4.makeMove('x', 0);
		ttt4.makeMove('x', 5);
		ttt4.makeMove('x', 10);
		ttt4.makeMove('x', 15);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x', 'x'}, ttt4.getDiagonal1());

		ttt4.clearBoard();
		ttt4.makeMove('x', 3);
		ttt4.makeMove('x', 6);
		ttt4.makeMove('x', 9);
		ttt4.makeMove('x', 12);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x', 'x'}, ttt4.getDiagonal2());

		FunctionalTicTacToe ttt5 = new FunctionalTicTacToe(5, 'x');
		ttt5.makeMove('x', 0);
		ttt5.makeMove('x', 6);
		ttt5.makeMove('x', 12);
		ttt5.makeMove('x', 18);
		ttt5.makeMove('x', 24);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x', 'x', 'x'}, ttt5.getDiagonal1());
	}

	@Test
	public void charsSameTest() {
		Assert.assertEquals('x', ttt.getCharIfAllSame(new char[]{'x', 'x', 'x'}));
		Assert.assertEquals('o', ttt.getCharIfAllSame(new char[]{'o', 'o', 'o'}));
		Assert.assertEquals(FunctionalTicTacToe.empty, ttt.getCharIfAllSame(new char[]{}));
		Assert.assertEquals(FunctionalTicTacToe.empty, ttt.getCharIfAllSame(new char[]{FunctionalTicTacToe.empty, FunctionalTicTacToe.empty, FunctionalTicTacToe.empty}));
		Assert.assertEquals(FunctionalTicTacToe.empty, ttt.getCharIfAllSame(new char[]{'o', 'o', 'x'}));
		Assert.assertEquals(FunctionalTicTacToe.empty, ttt.getCharIfAllSame(new char[]{'o', 'x', 'x'}));
		Assert.assertEquals(FunctionalTicTacToe.empty, ttt.getCharIfAllSame(new char[]{'x', 'x', 'x', 'o'}));
	}

	@Test
	public void simpleTurnTest() {
		FunctionalTicTacToe mttt = Mockito.spy(FunctionalTicTacToe.class);

		final int[] c = new int[]{0};

		final int[] moves = new int[] {
			0, // x
			1, // o
			1, // o
			3, // x
			8, // o
			6  // x
		};

		Answer<Integer> answer = m -> {
			int a = c[0];
			c[0]++;

			return moves[a];
		};
		Mockito.doAnswer(answer).when(mttt).getNextInt();
		Mockito.doNothing().when(mttt).done();
		Mockito.doAnswer(i -> {
			int a = c[0];
			c[0]++;

			if(a == moves.length) {
				return "s";
			} else {
				return "d";
			}
		}).when(mttt).getNextString();

		mttt.moveInput();
		Mockito.verify(mttt).winner('x');
	}

	@Test
	public void drawTest() {
		FunctionalTicTacToe mttt = Mockito.spy(FunctionalTicTacToe.class);

		final int[] c = new int[]{0};

		final int[] moves = new int[] {
			0, // x
			1, // o
			2, // x
			5, // o
			3, // x
			8, // o
			4, // x
			6, // o
			7  // x
		};

		Answer<Integer> answer = m -> {
			int a = c[0];
			c[0]++;

			return moves[a];
		};
		Mockito.doAnswer(answer).when(mttt).getNextInt();
		Mockito.doNothing().when(mttt).done();
		Mockito.doAnswer(i -> "d").when(mttt).getNextString();

		mttt.moveInput();

		Mockito.verify(mttt, Mockito.times(0)).winner(Mockito.anyChar());
		Mockito.verify(mttt).draw();
		Mockito.verify(mttt).done();
	}
}