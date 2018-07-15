package com.dovidkopel;


import java.util.*;

public class FunctionalTicTacToe {
	private final int size;

	private char[] board;

	final static char empty = '\0';

	private Scanner scanner = new Scanner(System.in);

	private char turn;

	enum WinningPath {
		HORIZONTAL,
		VERTICAL,
		DIAGNOL
	}

	public FunctionalTicTacToe(int size, char firstPlayer) {
		this.size = size;
		this.turn = firstPlayer;
		this.board = new char[size*size];
		clearBoard();
	}

	public FunctionalTicTacToe() {
		this(3, 'x');
	}

	public char[] getEmpty() {
		char[] t = new char[size];
		for(int x=0; x < size; x++) {
			t[x] = empty;
		}
		return t;
	}

	public char getCharIfAllSame(char[] chars) {
		// Make sure all of the chars are present
		if(chars.length != size) {
			return empty;
		}
		// Make sure all of the chars are not empty
		// Make sure all of the chars are the same
		char t = empty;
		for(int x = 0; x < size; x++) {
			if(chars[x] == empty) {
				return empty;
			}

			if(t != empty && t != chars[x]) {
				return empty;
			}

			t = chars[x];
		}

		return t;
	}

	public void clearBoard() {
		for(int x=0; x < size*size; x++) {
			board[x] = empty;
		}
	}

	public void nextPlayer() {
		if(turn == 'x') {
			turn = 'o';
		} else {
			turn = 'x';
		}
		moveInput();
	}

	public Scanner getScanner() {
		return scanner;
	}

	public int getNextInt() {
		return getScanner().nextInt();
	}

	public void moveInput() {
		printBoard();
		System.out.println(String.format("Player %s turn: ", turn));
		int s = getNextInt();

		if(s >= 0 && s < size*size) {
			makeMove(turn, s);
			printBoard();

			char winnerResult = getWinner();
			if(winnerResult != empty) {
				winner(winnerResult);
			} else {
				nextPlayer();
			}
		}

	}

	public void makeMove(char player, int x) {
		System.out.println(String.format("Player %c wants to move to %d", player, x));
		if(x < size*size && board[x] == empty) {
			board[x] = player;
		} else {
			throw new IllegalArgumentException("Invalid move!");
		}
	}

	public void winner(char player) {
		System.out.println(String.format("Player %c is the winner.", player));
		clearBoard();
	}

	public void printBoard() {
		StringBuilder sb = new StringBuilder();
		for(int x=0; x < size; x++) {
			String r;
			if(board[x] == empty) {
				r = String.format("#%d", x);
			} else {
				r = ((Character) board[x]).toString();
			}

			sb.append(String.format("%s  ", r));
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public void printCounts(String label, List<Character> cs) {
		StringBuilder sb = new StringBuilder();
		Iterator i = cs.iterator();
		while(i.hasNext()) {
			sb.append(i.next()+", ");
		}
		System.out.println(label+": "+sb.toString());
	}

	/***
	 * 0 1 2
	 * 3 4 5
	 * 6 7 8
	 */

	// Row 0 -> 0 1 2
	// Row 1 -> 3 4 5
	// Row 2 -> 6 7 8
	public int getRow(int row, int offset) {
		return offset + (row * size);
	}

	// Column 0 -> 0 3 6
	// Column 1 -> 1 4 7
	// Column 2 -> 2 5 8
	public int getColumn(int column, int offset) {
		return column + (size * offset);
	}

	public int getDiagnol(int diag, int offset) {
		if(diag == 0) {
			if(offset == 0) {
				return 0;
			} else {
				return (size+1) * offset;
			}
		} else {
			if(offset == 0) {
				return size - 1;
			} else {
				return (size - 1) * (offset + 1);
			}
		}
	}

	public char[] getCells(WinningPath wp, int... args) {
		char[] t = new char[size];
		for(int x=0; x < size; x++) {
			int c = 0;
			switch(wp) {
				case HORIZONTAL:
					c = getRow(args[0], x);
					break;
				case VERTICAL:
					c = getColumn(args[0], x);
					break;
				case DIAGNOL:
					c = getDiagnol(args[0], x);
					break;
			}
			t[x] = board[c];
		}
		return t;
	}

	public char[] getColumn(int column) {
		return getCells(WinningPath.VERTICAL, column);
	}

	public char[] getRow(int row) {
		return getCells(WinningPath.HORIZONTAL, row);
	}

	public char[] getDiagnol1() {
		return getCells(WinningPath.DIAGNOL, 0);
	}

	public char[] getDiagnol2() {
		return getCells(WinningPath.DIAGNOL, 1);
	}

	public char getWinner() {
		// Iterate through each winning algorithm
		// For most of them then iterate for each row
		for(int x=0; x < size; x++) {
			char[] chars = getCells(WinningPath.HORIZONTAL, x);
			char c = getCharIfAllSame(chars);
			if(c != empty) {
				return c;
			}
		}

		for(int x=0; x < size; x++) {
			char[] chars = getCells(WinningPath.VERTICAL, x);
			char c = getCharIfAllSame(chars);
			if(c != empty) {
				return c;
			}
		}

		char[] chars = getDiagnol1();
		char c = getCharIfAllSame(chars);
		if(c != empty) {
			return c;
		}

		chars = getDiagnol2();
		c = getCharIfAllSame(chars);
		if(c != empty) {
			return c;
		}

		return empty;
	}
}
