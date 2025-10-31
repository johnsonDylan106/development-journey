/*
 * Author: Dylan Johnson
 * Date: 9/27/22
 * Class: CSS222
 * Assignment: Program 3
 * Description: Friendly game of Tic-Tac-Toe
*/

import java.util.Scanner;

public class JohnsonProgram3 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		// initializing board
		char[] row1 = { ' ', ' ', ' ' };
		char[] row2 = { ' ', ' ', ' ' };
		char[] row3 = { ' ', ' ', ' ' };

		System.out.println("Welcome to TicTaxToe\n");

		displayBoard(row1, row2, row3); // prints board

		String playAgain = "y";
		while (playAgain.equalsIgnoreCase("y")) {
			playGame(row1, row2, row3, input); // Starts the game
			System.out.print("Do you want to play another game? (Y/N): ");
			playAgain = input.nextLine();
			if (playAgain.equalsIgnoreCase("y")) {// decides if game will be played again
				clearBoard(row1, row2, row3);
				displayBoard(row1, row2, row3);
			}
		}
		System.out.println("GoodBye!");

		input.close();

	}

	private static void playGame(char[] row1, char[] row2, char[] row3, Scanner input) {
		char symbol = 'O'; // initializes game character
		String userInput; // makes user input useful in the entire method

		// will loop through so long as no winning combination is available-----------
		while (checkWinner(row1, row2, row3, symbol) == false) {
			symbol = rotatePlayer(symbol);// switches player
			// loops until valid move is found--------------------------------
			while (true) {
				System.out.print("Player " + symbol + " turn: ");
				userInput = input.nextLine();
				if (isTaken(row1, row2, row3, userInput)) {
					break;
				} else {
					System.out.println(userInput + " is not a valid move.");
				}
			}
			// ----------------------------------------------------------------

			placeMove(row1, row2, row3, symbol, userInput);// places the move designated by the user
			displayBoard(row1, row2, row3); // prints the updated board
		}
		// ----------------------------------------------------------------------------

	}

	private static boolean checkWinner(char[] row1, char[] row2, char[] row3, char symbol) {
		// checks all possible winning scenarios-------------------
		if (checkWinningRows(row1, row2, row3, symbol)) {
			System.out.println("Player " + symbol + " WINS!!!");
			return true;
		}
		if (checkWinningCols(row1, row2, row3, symbol)) {
			System.out.println("Player " + symbol + " WINS!!!");
			return true;
		}
		if (checkWinningDiagnals(row1, row2, row3, symbol)) {
			System.out.println("Player " + symbol + " WINS!!!");
			return true;
		}
		// --------------------------------------------------------
		// checks for a cat game (all board pieces are full)-------
		if (isTie(row1, row2, row3)) {
			return true;
		}
		// --------------------------------------------------------

		return false; // if none of these are true, the game goes on

	}

	// methods for checking if the game is over
	// ------------------------------------------------------------
	private static boolean isTie(char[] row1, char[] row2, char[] row3) {
		for (int i = 0; i < row1.length; i++) {
			if (row1[i] == ' ' || row2[i] == ' ' || row3[i] == ' ') {
				return false;
			}
		}
		System.out.println("It's a cat game!");
		return true;
	}

	private static boolean checkWinningRows(char[] row1, char[] row2, char[] row3, char symbol) {
		if ((row1[0] == symbol && row1[1] == symbol && row1[2] == symbol)
				|| (row2[0] == symbol && row2[1] == symbol && row2[2] == symbol)
				|| (row3[0] == symbol && row3[1] == symbol && row3[2] == symbol)) {
			return true;
		}
		return false;
	}

	private static boolean checkWinningCols(char[] row1, char[] row2, char[] row3, char symbol) {
		if ((row1[0] == symbol && row2[0] == symbol && row3[0] == symbol)
				|| (row1[1] == symbol && row2[1] == symbol && row3[1] == symbol)
				|| (row1[2] == symbol && row2[2] == symbol && row3[2] == symbol)) {
			return true;
		}
		return false;
	}

	private static boolean checkWinningDiagnals(char[] row1, char[] row2, char[] row3, char symbol) {
		if ((row1[0] == symbol && row2[1] == symbol && row3[2] == symbol)
				|| (row1[2] == symbol && row2[1] == symbol && row3[0] == symbol)) {
			return true;
		}
		return false;
	}

	// ------------------------------------------------------------------------------------------------------
	// changes the player of in the game ---------------
	private static char rotatePlayer(char symbol) {
		if (symbol == 'O') {
			symbol = 'X';
		} else if (symbol == 'X') {
			symbol = 'O';
		}
		return symbol;
	}

	// -------------------------------------------------
	// Places a valid move on the game board----------------------------------------------------------------
	private static void placeMove(char[] row1, char[] row2, char[] row3, char symbol, String userInput) {
		switch (userInput) {
		case "1 1":
			row1[0] = symbol;
			break;
		case "1 2":
			row1[1] = symbol;
			break;
		case "1 3":
			row1[2] = symbol;
			break;
		case "2 1":
			row2[0] = symbol;
			break;
		case "2 2":
			row2[1] = symbol;
			break;
		case "2 3":
			row2[2] = symbol;
			break;
		case "3 1":
			row3[0] = symbol;
			break;
		case "3 2":
			row3[1] = symbol;
			break;
		case "3 3":
			row3[2] = symbol;
			break;
		default:
			System.out.println("Not valid");
		}
	}
	// -------------------------------------------------------------------------------------------------------
	// Checks if the attempted move is already taken----------------------------------------------
	private static boolean isTaken(char[] row1, char[] row2, char[] row3, String userInput) {
		switch (userInput) {
		case "1 1":
			return (row1[0] == ' ');
		case "1 2":
			return (row1[1] == ' ');
		case "1 3":
			return (row1[2] == ' ');
		case "2 1":
			return (row2[0] == ' ');
		case "2 2":
			return (row2[1] == ' ');
		case "2 3":
			return (row2[2] == ' ');
		case "3 1":
			return (row3[0] == ' ');
		case "3 2":
			return (row3[1] == ' ');
		case "3 3":
			return (row3[2] == ' ');
		default:
			return false;
		}
	}
	// ------------------------------------------------------------------------------------------
	//Loops through all board arrays and clears them to start game over -------
	private static void clearBoard(char[] row1, char[] row2, char[] row3) {
		for (int i = 0; i < row1.length; i++) {
			row1[i] = ' ';
		}
		for (int i = 0; i < row1.length; i++) {
			row2[i] = ' ';
		}
		for (int i = 0; i < row1.length; i++) {
			row2[i] = ' ';
		}
	}

	//------------------------------------------------------------------------
	//Prints the game board in its current state when called----------------------
	private static void displayBoard(char[] row1, char[] row2, char[] row3) {
		System.out.println("\n" + row1[0] + "|" + row1[1] + "|" + row1[2]);
		System.out.println("-+-+-");
		System.out.println(row2[0] + "|" + row2[1] + "|" + row2[2]);
		System.out.println("-+-+-");
		System.out.println(row3[0] + "|" + row3[1] + "|" + row3[2] + "\n");
	}
	//----------------------------------------------------------------------------

}
