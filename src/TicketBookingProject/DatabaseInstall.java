package TicketBookingProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseInstall {

	static DatabaseConnector dbConnector = new DatabaseConnector("localhost:3306", "root", "");

	public static void main(String[] args) {

		parseAirportDatabase();
		parseAirlinesDatabase();
		parseRouteDatabase();
		
		dbConnector.openDatabase("flight_database");
		dbConnector.deleteTable("booking");
		dbConnector.createTable("booking", ""
				+ "booking_id Integer not NULL, "
				+ "flight_id Integer not NULL, "
				+ "cabinClass varchar(255), "
				+ "departure varchar(255), "
				+ "traveller int, "
				+ "name varchar(255), "
				+ "surname varchar(255), "
				+ "gender varchar(255), "
				+ "phone varchar(255), "
				+ "email varchar(255)");
	}

	private static void parseAirportDatabase() {

		dbConnector.openDatabase("flight_database");
		dbConnector.deleteTable("airport");
		dbConnector.createTable("airport", ""
				+ "AIRPORT_ID Integer not NULL, "
				+ "name varchar(255), "
				+ "city varchar(255), "
				+ "country varchar(255), "
				+ "iata varchar(10) not NULL, "
				+ "latitude float(255, 16), "
				+ "longitude float(255, 16),"
				+ "PRIMARY KEY (AIRPORT_ID)");


		try {
			Scanner key = new Scanner(new File("Airport_Database.txt"));

			int id = 0;

			while(key.hasNext()) {
				String[] s = key.nextLine().split(",");

				for(int i = 1; i < 6; i++) 
					s[i] = s[i].replaceAll("\"", "'");

				if(!(s[2].equals("''") || s[2].equals("'Null'") || s[4].equals("''") || s[4].equals("'Null'"))) {
					dbConnector.insertInto("AIRPORT", ""
							+ s[0] + ", " //id
							+ s[1] + ", " //name
							+ s[2] + ", " //city
							+ s[3] + ", " //country
							+ s[4] + ", " //IATA
							+ s[6] + ", " //latitude
							+ s[7]); 	  //longitude
				}

			}
		} catch (FileNotFoundException e) {
			System.out.println("No File!");
		}
	}

	private static void parseAirlinesDatabase() {

		dbConnector.openDatabase("flight_database");
		dbConnector.deleteTable("airline");
		dbConnector.createTable("airline", ""
				+ "airline_id Integer not NULL, "
				+ "name varchar(255), "
				+ "country varchar(255), "
				+ "rate float(255, 2), "
				+ "PRIMARY KEY (airline_id)");


		try {
			Scanner key = new Scanner(new File("Airlines.txt"));



			while(key.hasNext()) {
				String[] s = key.nextLine().split(",");

				s[1] = s[1].replaceAll("\"", "'"); 
				s[6] = s[6].replaceAll("\"", "'");

				if(!(s[7].equals("\"N\"") || s[6].equals("''"))) {

					double d = Math.random() + 0.5;
					d *= 100;
					int a = (int) (d / 1);
					d = (double)a / 100;

					dbConnector.insertInto("airline", ""
							+ s[0] + ", " //id
							+ s[1] + ", " //name
							+ s[6] + ", " //country
							+ d);         //rate
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No File!");
		}
	}

	private static void parseRouteDatabase() {
		dbConnector.openDatabase("flight_database");
		dbConnector.deleteTable("flight");
		dbConnector.createTable("flight", ""
				+ "flight_id Integer not NULL, "
				+ "airline_id smallint, "
				+ "source_airport_id smallint, "
				+ "destination_airport_id smallint, "
				+ "stops tinyint, "
				+ "equipment varchar(255), "
				+ "price int, "
				+ "PRIMARY KEY (flight_id)");


		try {
			Scanner key = new Scanner(new File("Routes.txt"));

			int id = 0;

			while(key.hasNext()) {
				String[] s = key.nextLine().split(",");

				if(s.length == 9) {

					s[8] = "'" + s[8] + "'";

					ResultSet airSet = dbConnector.query("SELECT * FROM airport WHERE airport_id = '" + s[3] + "'");

					try {
						airSet.next();
						double latitude1 = airSet.getDouble("latitude");
						double longitude1 = airSet.getDouble("longitude");

						airSet = dbConnector.query("SELECT * FROM airport WHERE airport_id = '" + s[5] + "'");

						airSet.next();
						double latitude2 = airSet.getDouble("latitude");
						double longitude2 = airSet.getDouble("longitude");

						airSet = dbConnector.query("SELECT * FROM airline WHERE airline_id = '" + s[1] + "'");

						airSet.next();
						double rate = airSet.getDouble("rate");
							
						int price = (int) (distance(latitude1, latitude2, longitude1, longitude2) * rate);
						
						dbConnector.insertInto("flight", ""
								+ id++ + ", " //id
								+ s[1] + ", " //airlines_id
								+ s[3] + ", " //source_id
								+ s[5] + ", " //destination_id
								+ s[7] + ", " //stops
								+ s[8] + ", " //equipment
								+ price); 	  //price
						
						System.out.println("Success");
					} catch (SQLException e) {
						System.out.println("Failed to find airport/airline");
					}


				}


			}
		} catch (FileNotFoundException e) {
			System.out.println("No File!");
		}
	}

	private static double distance(double lat1, double lat2, double lon1,
			double lon2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c; // convert to meters

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);
	}
}
