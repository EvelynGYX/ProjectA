package ProjectA;
/*
	COMP90041 Programming and Software Development
	Semester 1, 2018
	Author's Name: Yuxin Guo
	User Name: yuxing4
	ID: 875698
	ProjectB, 19/04/2018
	This class represents the process of playing the game, Nim.
*/
import java.util.Scanner;

public class NimGame {

	private static final int MIN_NUMBER_OF_STONE_AVALIBALE = 1;
	private static final int NUMBER_OF_STONE_ZERO = 0;
	private static final int NUMBER_OF_PLAYER = 2;
	private static final int PLAYER_ONE_TURN = 1;
	private int currentStoneCount;
	private int upperBound;
	private NimPlayer player1;
	private NimPlayer player2;

	// Constructor and initialization
	NimGame(int initialNumberOfStones, int upperBound, NimPlayer player1, NimPlayer player2) {
		this.currentStoneCount = initialNumberOfStones;
		this.upperBound = upperBound;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public int getCurrentStoneCount() {
		return currentStoneCount;
	}

	public void setCurrentStoneCount(int currentStoneCount) {
		this.currentStoneCount = currentStoneCount;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public NimPlayer getPlayer1() {
		return player1;
	}

	public void setPlayer1(NimPlayer player1) {
		this.player1 = player1;
	}

	public NimPlayer getPlayer2() {
		return player2;
	}

	public void setPlayer2(NimPlayer player2) {
		this.player2 = player2;
	}

	// the main process of game Nim
	public void startNim(Scanner keyboard) {
		int numberOfTurns = 1;
		int numberToRemove = 0;

		printInitialFigures();

		// players take turns to play Nim
		while (currentStoneCount > NUMBER_OF_STONE_ZERO) {
			printStones(currentStoneCount);

			// decide which player removes stones and update the number of stones left after
			// removal
			if (numberOfTurns % NUMBER_OF_PLAYER == PLAYER_ONE_TURN) {
				numberToRemove = player1.removeStone(keyboard);
				if (checkNumberToRemove(numberToRemove)) {
					currentStoneCount -= numberToRemove;
					++numberOfTurns;
				}
			} else {
				numberToRemove = player2.removeStone(keyboard);
				if (checkNumberToRemove(numberToRemove)) {
					currentStoneCount -= numberToRemove;
					++numberOfTurns;
				}
			}
		}
		printWinner(numberOfTurns, player1, player2);
	}

	// method to print initial information
	private void printInitialFigures() {
		System.out.print("\nInitial stone count: " + currentStoneCount + "\n");
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + player1.getFullName());
		System.out.println("Player 2: " + player2.getFullName());
	}

	// method to display the stones
	private void printStones(int numberOfStones) {
		System.out.printf("\n" + numberOfStones + " stones left:");
		for (int i = numberOfStones; i > 0; i--) {
			System.out.print(" *");
		}
		System.out.printf("\n");
	}

	// method to display the end and the name of the winner
	private void printWinner(int numberOfTurns, NimPlayer player1, NimPlayer player2) {
		System.out.printf("\nGame Over\n");
		if (numberOfTurns % NUMBER_OF_PLAYER == PLAYER_ONE_TURN) {
			System.out.println(player1.getFullName() + " wins!");
			player1.addNumberOfGamesWon();
		} else {
			System.out.println(player2.getFullName() + " wins!");
			player2.addNumberOfGamesWon();
		}
		player1.addNumberOfGamesPlayed();
		player2.addNumberOfGamesPlayed();
	}

	// method to check if the number of removal is valid
	private boolean checkNumberToRemove(int numberToRemove) {
		if (numberToRemove < MIN_NUMBER_OF_STONE_AVALIBALE || numberToRemove > upperBound) {
			System.out.println("\nInvalid move. You must remove between 1 and " + upperBound + " stones.");
			return false;
		} else if (numberToRemove > currentStoneCount) {
			System.out.println("\nInvalid move. You must remove between 1 and " + currentStoneCount + " stones.");
			return false;
		} else
			return true;
	}
}
