package com.dovidkopel.tictactoe.functional;


import java.util.*;

public class FunctionalTicTacToe {
	private final int size;

	private char[] board;

	final static char empty = '\0';

	final static int emptyInt = Byte.toUnsignedInt("\0".getBytes()[0]);

	private int gamesPlayed = 0;

	private int movesMade = 0;

	private int scoreX = 0;

	private int scoreO = 0;

	private Scanner scanner = new Scanner(System.in);

	private char turn;

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	enum WinningPath {
		HORIZONTAL,
		VERTICAL,
		DIAGONOL
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

	public void menu() {
		System.out.println("Input 'n' for new game");
		System.out.println("Input 's' for scores");
		System.out.println("Input 'q' to quit");
		String i = getNextString();

		if(i.equalsIgnoreCase("n")) {
			clearBoard();
			moveInput();
		} else if(i.equalsIgnoreCase("s")) {
			printScoreBoard();
		} else if(i.equalsIgnoreCase("d")) {
			done();
		} else if(i.equalsIgnoreCase("q")) {
			System.exit(0);
		} else {
			menu();
		}
	}

	public void clearBoard() {
		movesMade = 0;
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

	public String getNextString() {
		return getScanner().next("n|q|s");
	}

	public void moveInput() {
		printBoard();
		System.out.println(String.format("Player %s input your next move: ", turn));
		int s = getNextInt();

		try {
			if(s >= 0 && s < size*size) {
				makeMove(turn, s);
				System.out.println(String.format("Player %s just went.", turn));
				printBoard();

				char winnerResult = getWinner();
				if(winnerResult != empty) {
					winner(winnerResult);
				} else if(movesMade == size*size) {
					draw();
				} else {
					nextPlayer();
				}
			}
		} catch(IllegalArgumentException e) {
			System.err.println(String.format("%s%s%s", ANSI_RED, "Invalid move!", ANSI_RESET));
			moveInput();
		}
	}

	public void makeMove(char player, int x) {
		System.out.println(String.format("Player %c wants to move to %d.", player, x));
		if(x < size*size && board[x] == empty) {
			board[x] = player;
			movesMade++;
		} else {
			throw new IllegalArgumentException("Invalid move!");
		}
	}

	public void done() {
		System.exit(0);
	}

	public void printScoreBoard() {
		System.out.println(String.format("o: %d", scoreO));
		System.out.println(String.format("x: %d", scoreX));
		System.out.println("\n\n");
		menu();
	}

	public void draw() {
		System.out.println(String.format("%sDraw.%s", ANSI_GREEN, ANSI_RESET));
		System.out.println("\n\n");
		menu();
	}

	public void winner(char player) {
		if(player == 'x') {
			scoreX++;
		} else if(player == 'o') {
			scoreO++;
		}

		System.out.println(String.format("%sPlayer %c is the winner.%s", ANSI_GREEN, player, ANSI_RESET));
		System.out.println("\n\n");
		menu();
	}

	public void printBoard() {
		StringBuilder sb = new StringBuilder();
		for(int x=0; x < size*size; x++) {
			String r;
			if(board[x] == empty) {
				r = String.format("%s%d%s", ANSI_WHITE, x, ANSI_RESET);
			} else {
				char p = board[x];
				String color;
				if(p == 'x') {
					color = ANSI_CYAN;
				} else {
					color = ANSI_YELLOW;
				}
				r = String.format("%s%c%s", color, p, ANSI_RESET);
			}

			sb.append(String.format("%s  ", r));

			if(x != 0 && (x+1) % size == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
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
				case DIAGONOL:
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

	public char[] getDiagonol1() {
		return getCells(WinningPath.DIAGONOL, 0);
	}

	public char[] getDiagonol2() {
		return getCells(WinningPath.DIAGONOL, 1);
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

			chars = getCells(WinningPath.VERTICAL, x);
			c = getCharIfAllSame(chars);

			if(c != empty) {
				return c;
			}
		}

		char[] chars = getDiagonol1();
		char c = getCharIfAllSame(chars);
		if(c != empty) {
			return c;
		}

		chars = getDiagonol2();
		c = getCharIfAllSame(chars);
		if(c != empty) {
			return c;
		}

		return empty;
	}
}
