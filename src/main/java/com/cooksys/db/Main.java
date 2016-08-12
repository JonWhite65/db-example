package com.cooksys.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
private static final Logger log =LoggerFactory.getLogger(Main.class);
	public static void main(String[] args){
		try {
			Main m = new Main();
			m.findActor(1);
		} catch (Exception e) {
			log.error("Failed run querry",e);
		}
	}

	void findActor(int id) throws Exception {
		String query = "select first_name, last_name from public.actor" ;

		Class.forName("org.postgresql.Driver");
		try (Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/postgres","postgres", "bondstone");
				Statement state = connection.createStatement();
				ResultSet result = state.executeQuery(query)) {
			while (result.next()) {

				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				log.info("Found user; " +firstName+" "+lastName);
			}
		}
	}
}
