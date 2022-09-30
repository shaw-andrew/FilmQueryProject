package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		// update for film
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);

		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(filmResult.getInt("id"), filmResult.getString("title"), filmResult.getString("description"),
					filmResult.getInt("releaseYear"), filmResult.getString("languageId"),
					filmResult.getInt("rentalDuration"), filmResult.getDouble("rentalRate"),
					filmResult.getInt("length"), filmResult.getDouble("replacementCost"),
					filmResult.getString("rating"), filmResult.getString("specialFeatures"));
		}
		filmResult.close();
		stmt.close();
		conn.close();
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);

		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
					actorResult.getString("last_name"));
		}
		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actorList = null;
		Actor newActor;

		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT actor.first_name, actor.last_name, film.title       \n"
				+ "      FROM actor JOIN film_actor fa ON actor.id = fa.actor_id \n"
				+ "                   JOIN film ON fa.film_id = film.id          \n"
				+ "                   WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);

		ResultSet actorFilmResult = stmt.executeQuery();
		if (actorFilmResult.next()) {
			newActor = new Actor(actorFilmResult.getInt("id"), actorFilmResult.getString("first_name"),
					actorFilmResult.getString("last_name"));
			actorList.add(newActor);
		}
		actorFilmResult.close();
		stmt.close();
		conn.close();
		if (actorList == null) {
			System.out.println("There weren't any actors in this one.");
			actorFilmResult.close();
			stmt.close();
			conn.close();
			return actorList;
		} else
			return actorList;
	}
}
