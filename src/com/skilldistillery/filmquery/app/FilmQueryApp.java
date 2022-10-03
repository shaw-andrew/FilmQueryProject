package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() throws SQLException {
//		Film film = db.findFilmById(2);
//		System.out.println(film);
//	}

	private void launch() throws SQLException {
		startUserInterface();
	}

	private void startUserInterface() throws SQLException {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to the Film Query App.");
		printMenu();

		while (true) {
			switch (kb.nextInt()) {
			
			case 1:
				System.out.println("Please input your film's ID number.");
				Film outputId = dao.findFilmById(kb.nextInt());
				if (outputId != null) {
					System.out.println();
					System.out.println(outputId);
					System.out.print("Starring: ");
					System.out.println(outputId.getActors().toString().replace("[", "").replace("]", ""));
					System.out.println();
					printMenu();
				} else {
					System.out.println("There is no film with that ID number.");
					printMenu();
				}
				break;

			case 2:
				System.out.println("Please input the keyword you would like to search by.");
				List<Film> outputKey = dao.findFilmByKeyword(kb.next());
				if (outputKey.size() > 0) {
					for (Film film : outputKey) {
						System.out.println();
						System.out.println(film);
						System.out.print("Starring: ");
						System.out.println(film.getActors().toString().replace("[", "").replace("]", ""));
						System.out.println();
						System.out.println();
					}
					printMenu();
				} else {
					System.out.println("No film was found using your selected keyword. Please try again.");
					System.out.println();
					printMenu();
				}
				break;

			case 3:
				System.out.println("You chose to exit. Goodbye.");
				kb.close();
				System.exit(1);
				break;
				
			default:
				System.out.println("Invalid selection, please try again.");
			}
		}
	}

	private void printMenu() {
		System.out.println("What would you like to do?");
		System.out.println("Press 1 to look up a film by it's ID number.");
		System.out.println("Press 2 to search for a film using a keyword.");
		System.out.println("Press 3 to exit this application.");
	}

}