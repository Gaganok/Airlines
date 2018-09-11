package TicketBookingProject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller{

	private static Main view;
	private Scene currentScene;
	private Database db;

	Controller(Main view){
		this.view = view;
		db = new Database();

	}

	public void search(TextField source, TextField destination, ComboBox<String> classBox, Label travellerNumber, TextField departureField) {
		TextField[] fieldArray = {source, destination, departureField};
		
		if(isValid(fieldArray, classBox)) {
			ArrayList<String[]> searchResult = db.findFlight(source.getText(), destination.getText(), classBox.getValue(), travellerNumber.getText(), departureField.getText());
			
			ListComponent lc = new ListComponent(10, 15, searchResult);
			lc.setController(this);

			TicketList tL = new TicketList(lc);
			tL.setController(this);

			view.setScene(new Scene(tL, 640, 480));
		}
	}

	public void findBooking() {
		
		TicketFind tF = new TicketFind();
		tF.setController(this);
		
		view.setScene(new Scene(tF, 640, 480));
	}

	private boolean isValid(TextField[] fieldArray, ComboBox<String> box) {
		Boolean ok = true;

		for(TextField t : fieldArray) {
			if(t.getText().equals("")) {
				t.getStyleClass().add("error");
				ok = false;
			} else 
				t.getStyleClass().removeAll("error");
		}

		if(box.getValue() == null) {
			box.getStyleClass().add("error");
			ok = false;
		} else 
			box.getStyleClass().removeAll("error");

		return ok;
	}

	private boolean checkDateFormat(String date) {
		int dateNumber = 3;

		String[] dateArray = date.split("/");

		if(dateArray.length != dateNumber - 1) {

			int[] dateIntegerArray = new int[dateNumber];

			try {

				for(int i = 0; i < dateNumber; i++)
					dateIntegerArray[i] = Integer.parseInt(dateArray[i]);

			}catch(NumberFormatException e) {
				return false;
			}

			return     (dateIntegerArray[0] > 0 && dateIntegerArray[0] <= 31) 
					&& (dateIntegerArray[1] > 0 && dateIntegerArray[1] <= 12) 
					&& (dateIntegerArray[2] >= 2018 && dateIntegerArray[2] <= 2180);
		}

		return false;
	}

	public void book(String flight_id) {
		db.setFlightId(flight_id);

		TicketBooking tB = new TicketBooking();
		tB.setController(this);

		view.setScene(new Scene(tB, 640, 480));
	}

	public void searchFlight() {
		TicketSearch tS = new TicketSearch();
		tS.setController(this);

		view.setScene(new Scene(tS, 640, 480));
	}

	public void submit(TextField name, TextField surname, TextField phone, TextField email, ComboBox<String> gender) {
		TextField[] fieldArray = {name, surname, phone, email};

		if(isValid(fieldArray, gender)) {
			db.addBooking(name.getText(), surname.getText(), phone.getText(), email.getText(), gender.getValue());
			TicketSearch tS = new TicketSearch();
			tS.setController(this);

			view.setScene(new Scene(tS, 640, 480));
		}
	}

	public void displayBooking(TextField bookingID, Label errorLabel) {
		String s = bookingID.getText();
		if(!s.equals("")) {
			
			ArrayList<String[]> tick = db.findBooking(bookingID.getText());
			
			if( tick.size() != 0) {
				
				String[] ticketSet = tick.get(0);
						
				TicketDisplay tD = new TicketDisplay(ticketSet);
				tD.setController(this);
				
				view.setScene(new Scene(tD, 640, 480));
			} else 
				errorLabel.setText("No booking found");
		}
	}

	public void deleteBooking(String id) {
		db.deleteRow(id);
		
		TicketSearch tS = new TicketSearch();
		tS.setController(this);
		
		view.setScene(new Scene(tS, 640, 480));
	}

}
