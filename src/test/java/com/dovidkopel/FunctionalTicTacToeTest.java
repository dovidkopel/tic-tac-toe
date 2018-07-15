package com.dovidkopel;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.ByteArrayInputStream;

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
	public void diagnolTest() {
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
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x'}, ttt.getDiagnol1());
		Assert.assertEquals('x', ttt.getWinner());

		ttt.clearBoard();
		ttt.makeMove('x', 2);
		ttt.makeMove('x', 4);
		ttt.makeMove('x', 6);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x'}, ttt.getDiagnol2());
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
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x', 'x'}, ttt4.getDiagnol1());

		ttt4.clearBoard();
		ttt4.makeMove('x', 3);
		ttt4.makeMove('x', 6);
		ttt4.makeMove('x', 9);
		ttt4.makeMove('x', 12);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x', 'x'}, ttt4.getDiagnol2());

		FunctionalTicTacToe ttt5 = new FunctionalTicTacToe(5, 'x');
		ttt5.makeMove('x', 0);
		ttt5.makeMove('x', 6);
		ttt5.makeMove('x', 12);
		ttt5.makeMove('x', 18);
		ttt5.makeMove('x', 24);
		Assert.assertArrayEquals(new char[] {'x', 'x', 'x', 'x', 'x'}, ttt5.getDiagnol1());

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
		System.out.println("Pre");
		FunctionalTicTacToe mttt = Mockito.spy(FunctionalTicTacToe.class);
		System.out.println("After init");

		final int[] c = new int[]{0};

		Answer<Integer> answer = new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
				c[0]++;
				return c[0];
			}
		};
		Mockito.doAnswer(answer).when(mttt).getNextInt();

		mttt.moveInput();

		System.out.println("After 1");

		mttt.moveInput();

		System.out.println("After 2");

		mttt.moveInput();

		System.out.println("After 3");
	}
}