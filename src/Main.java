import java.util.Random;
import java.util.Scanner;

public class Main {

	// designing and creating necessary data structures and variables
	static SingleLinkedList playerCards = new SingleLinkedList();
	static SingleLinkedList computerCards = new SingleLinkedList();
	static SingleLinkedList tableCards = new SingleLinkedList();

	static int one_counter = 0;
	static int two_counter = 0;
	static int three_counter = 0;
	static int four_counter = 0;
	static int five_counter = 0;
	static int six_counter = 0;
	static int playerBook = 0;
	static int computerBook = 0;

	public static void main(String[] args) throws InterruptedException {

		addNumbersTo(playerCards);
		addNumbersTo(computerCards);
		addRemainingNumbersToTable();

		int turn = 1;

		// if playersTurn = true, player's turn; if playersTurn = false, computer's turn
		boolean playersTurn = true;

		Scanner scan = new Scanner(System.in);

		// continue until one of the players (player or computer) finishes all the cards
		// in his hand
		while (playerCards.size() > 0 && computerCards.size() > 0) {

			System.out.println("Turn " + turn++ + "\n");
			printCards();

			if (playersTurn) {

				// player asks the computer for a particular rank that he/she has
				int askToComputer;

				// if player doesn' t have that card, give error message
				// and take number(card) again
				do {

					System.out.print("You ask : ");
					askToComputer = scan.nextInt();

					if (playerCards.search(askToComputer) == 0)
						System.err.println("You cant ask for that card!");

				} while (playerCards.search(askToComputer) == 0);

				// counter shows that how many cards computer have
				int counter = computerCards.search(askToComputer);

				// If the computer has cards that you ask for, computer gives them to player.
				// Player gets another turn and asks again.
				if (counter > 0) {

					computerCards.remove(askToComputer);

					for (int i = 0; i < counter; i++) {
						playerCards.add(askToComputer);
					}

				}
				// If the computer doesn't have cards that you ask for, then it tells you to Go
				// Fish
				// which means that you will draw one card from the pile on the table.
				else {

					System.out.println("Computer says “Go Fish” ");

					int takeFromTable;
					// contains shows that how many cards are there on the table
					int contains;
					do {

						takeFromTable = (int) (Math.random() * 6 + 1);
						contains = tableCards.search(takeFromTable);

					} while (contains == 0);

					// remove method deletes all numbers in same rank
					// remove x cards from table and add x - 1 cards again
					tableCards.remove(takeFromTable);
					for (int i = 0; i < contains - 1; i++) {
						tableCards.add(takeFromTable);
					}
					playerCards.add(takeFromTable);

					playersTurn = false;

				}

				// If player has 4 of the same rank then he/she shows the cards to the other
				// player,
				// and then places the four cards in a pile next to him/her. This is called a book.
				for (int i = 1; i <= 6; i++) {

					// contains shows that how many cards player have in same rank
					int contains = playerCards.search(i);
					if (contains >= 4) {

						System.out.println("\n" + i + " " + i + " " + i + " " + i);

						// remove all cards in same rank and add again
						playerCards.remove(i);
						for (int j = 0; j < contains - 4; j++) {
							playerCards.add(i);
						}
						playerBook++;

					}
				}

				System.out.println("---------------------------------------------------");

			} else {

				// computer asks the player for a particular rank that he/she has
				int askToPlayer;

				// if computer doesn't have that card, generate a number(card) again
				do {

					askToPlayer = (int) (Math.random() * 6 + 1);

				} while (computerCards.search(askToPlayer) == 0);
				System.out.println("Computer asks : " + askToPlayer);

				// counter shows that how many cards player has
				int counter = playerCards.search(askToPlayer);

				// If player has cards that computer ask for, player gives them to computer.
				// Computer gets another turn and asks again.
				if (counter > 0) {

					playerCards.remove(askToPlayer);

					for (int i = 0; i < counter; i++) {
						computerCards.add(askToPlayer);
					}

				}
				// If player doesn't have cards that computer asks for, then player tells
				// computer Go Fish
				// which means that computer will draw one card from the pile on the table.
				else {

					System.out.println("You say “Go Fish” ");

					int takeFromTable;
					// contains shows that how many cards are there on the table
					int contains;
					do {

						takeFromTable = (int) (Math.random() * 6 + 1);
						contains = tableCards.search(takeFromTable);

					} while (contains == 0);

					// remove method deletes all numbers in same rank
					// remove x cards from table and add x - 1 cards again
					tableCards.remove(takeFromTable);
					for (int i = 0; i < contains - 1; i++) {
						tableCards.add(takeFromTable);
					}
					computerCards.add(takeFromTable);

					playersTurn = true;

				}

				// If computer has 4 of the same rank then he/she shows the cards to the other
				// player,
				// and then places the four cards in a pile next to him/her. This is called a book.
				for (int i = 1; i <= 6; i++) {

					// contains shows that how many cards player have in same rank
					int contains = computerCards.search(i);
					if (contains >= 4) {

						System.out.println("\n" + i + " " + i + " " + i + " " + i);

						// remove all cards in same rank and add again
						computerCards.remove(i);
						for (int j = 0; j < contains - 4; j++) {
							computerCards.add(i);
						}
						computerBook++;

					}
				}

				System.out.println("---------------------------------------------------");

				Thread.sleep(1000);

			}

		}
		System.out.println("Turn " + turn++ + "\n");
		printCards();

		// The player with the most books at the end of the game wins.
		if (playerBook > computerBook)
			System.out.println("You win!");
		else if (playerBook < computerBook)
			System.out.println("Computer win!");
		else
			System.out.println("It's a draw!");

	}

	// add random numbers(cards) to player or computer's cards
	public static void addNumbersTo(SingleLinkedList sll) {

		Random rnd = new Random();

		while (sll.size() < 7) {

			int number = rnd.nextInt(6) + 1;

			if (number == 1 && one_counter < 4) {

				sll.add(number);
				one_counter++;

			}
			if (number == 2 && two_counter < 4) {

				sll.add(number);
				two_counter++;

			}
			if (number == 3 && three_counter < 4) {

				sll.add(number);
				three_counter++;

			}
			if (number == 4 && four_counter < 4) {

				sll.add(number);
				four_counter++;

			}
			if (number == 5 && five_counter < 4) {

				sll.add(number);
				five_counter++;

			}
			if (number == 6 && six_counter < 4) {

				sll.add(number);
				six_counter++;

			}

		}

	}

	// add remaining numbers(cards) to table
	public static void addRemainingNumbersToTable() {

		for (int i = 0; i < 4 - one_counter; i++) {
			tableCards.add(1);
		}
		for (int i = 0; i < 4 - two_counter; i++) {
			tableCards.add(2);
		}
		for (int i = 0; i < 4 - three_counter; i++) {
			tableCards.add(3);
		}
		for (int i = 0; i < 4 - four_counter; i++) {
			tableCards.add(4);
		}
		for (int i = 0; i < 4 - five_counter; i++) {
			tableCards.add(5);
		}
		for (int i = 0; i < 4 - six_counter; i++) {
			tableCards.add(6);
		}

	}

	// print method for player, computer and table
	public static void printCards() {

		System.out.println("Table  : " + tableCards.display());

		System.out.print("\nYou       : " + playerCards.display());

		for (int i = 0; i < 15 - playerCards.size(); i++)
			System.out.print("  ");

		System.out.println("Book : " + playerBook);

		System.out.print("Computer  : " + computerCards.display());

		for (int i = 0; i < 15 - computerCards.size(); i++)
			System.out.print("  ");

		System.out.println("Book : " + computerBook);

		System.out.println();

	}

}
