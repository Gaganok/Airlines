package TicketBookingProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
	private DatabaseConnector dbConnector;
	private String cabinClass, travellerNumber, departure, source, destination, flight_id;
	private ResultSet rs;
	private static int rows;

	Database(){
		dbConnector = new DatabaseConnector("localhost:3306","root","");
		dbConnector.openDatabase("flight_database");
		rs = dbConnector.query("SELECT COUNT(*) AS count FROM booking");
		try {
			rs.next();
			rows = rs.getInt("count");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}


	public ArrayList<String[]> findFlight(String source, String destination, String cabinClass, String travellerNumber, String departure) {

		this.cabinClass = cabinClass;
		this.travellerNumber = travellerNumber;
		this.departure = departure;
		this.source = source;
		this.destination = destination;

		dbConnector.openDatabase("flight_database");
		String s =  "SELECT air.name, a.iata, a.city, b.iata, b.city, f.price, f.flight_id " + 
				"FROM airport a, airport b, flight f, airline air " + 
				"WHERE " + 
				"f.airline_id = air.airline_id AND " + 
				"a.AIRPORT_ID = f.source_airport_id AND a.city = '" + source + "' AND " + 
				"b.AIRPORT_ID = f.destination_airport_id AND b.city = '" + destination + "'";

		rs = dbConnector.query(s);

		return parseList( 7, rs); // Default 5 flights per list
	}

	private ArrayList<String[]> parseList(int rows, ResultSet rs) {

		ArrayList<String[]> list = new ArrayList<String[]>();

		try {
			while(rs.next()) {
				String[] a = new String[rows];

				for(int i = 0; i < rows; i++) 
					a[i] = rs.getString(i + 1);

				list.add(a);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return list;
	}


	public void setFlightId(String flight_id) {
		this.flight_id = flight_id;
	}


	public void addBooking(String name, String surname, String phone, String email, String gender) {
		String value = ""
				+ ++rows +", "
				+ Integer.parseInt(flight_id) + ", '"
				+ cabinClass + "', '"
				+ departure + "', "
				+ Integer.parseInt(travellerNumber) + ", '"
				+ name + "', '"
				+ surname + "', '"
				+ gender + "', '"
				+ phone +"', '"
				+ email + "'";

		dbConnector.insertInto("booking", value);
	}


	public ArrayList<String[]> findBooking(String bookingId) {
		dbConnector.openDatabase("flight_database");

		String query = ""
				+ "SELECT a.iata, a.city, b.iata, b.city, book.booking_id, book.cabinClass, book.departure, book.traveller, book.name, book.surname, book.email, book.phone, air.name "
				+ "FROM airport a, airport b, flight f, booking book, airline air "
				+ "WHERE book.booking_id = " + bookingId + " AND book.flight_id = f.flight_id AND "
				+ "a.AIRPORT_ID = f.source_airport_id AND b.AIRPORT_ID = f.destination_airport_id AND "
				+ "air.airline_id = f.airline_id";

		rs = dbConnector.query(query);

		return parseList(12, rs);
	}


	public void deleteRow(String id) {
		dbConnector.deleteRow("booking", "booking_id = " + id);
	}

}
