package TicketBookingProject;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TicketFind extends GridPane {
	Button find, searchFlight;
	TextField bookingID;
	Label bookingIDLabel, errorLabel;
	Controller controller;
	
	TicketFind(){
		errorLabel = new Label();
		
		searchFlight = new Button("Search Flight");
		searchFlight.setOnAction(e -> controller.searchFlight());
		
		find = new Button("Find");
		find.setOnAction(e -> controller.displayBooking(bookingID, errorLabel));
		
		bookingIDLabel = new Label("Booking ID");
		
		bookingID = new TextField();
		bookingID.setPromptText("123");
		
		this.setPadding(new Insets(20, 20, 20, 20));
		
		//GridIni
		Main.iniGrid(this, 4, 3);
		add(searchFlight, 3, 0);
		add(bookingIDLabel, 1, 1);
		add(bookingID, 2, 1);
		add(errorLabel, 2, 2);
		add(find, 1, 2);
		
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
