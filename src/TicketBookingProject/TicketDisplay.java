package TicketBookingProject;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TicketDisplay extends GridPane {
	Button searchFlight, delete;
	Controller controller;
	Ticket ticket;

	TicketDisplay(String[] ticketSet){
		
		if(ticketSet != null)
			ticket = new Ticket(ticketSet);
		else
			ticket = new Ticket();
		
		searchFlight = new Button("Search Flight");
		searchFlight.setOnAction(e -> controller.searchFlight());
		
		delete = new Button("Delete");
		delete.setOnAction(e -> controller.deleteBooking(ticket.getID()));
		
		
		//GridIni
		Main.iniGrid(this, 2, 4);
		add(searchFlight, 1, 0);
		add(ticket, 0, 1, 2, 3);
		add(delete, 1, 3);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void showTicket(String[] ticketSet) {
		if(!(ticketSet == null)) {
			ticket = new Ticket(ticketSet);
			System.out.println("Here");
		}
	}

	private void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	private class Ticket extends VBox{
		HBox[] hboxArray = new HBox[8];
		Label[] lArr;
		String id;
		
		Ticket() {

		}

		Ticket(String[] ticketSet){
			//this.setStyle("-fx-padding: 0, 0, 0, 20");
			setPadding(new Insets(0, 0, 0, 50));
			
			if(ticketSet != null) {
				lArr = new Label[12];
				
				String [] a = ticketSet;
				
				for(int i = 0; i < lArr.length; i++)
					lArr[i] = new Label(ticketSet[i]);
				
				id = lArr[4].getText();
				
				int spacing = 15;

				hboxArray[0] = new HBox(spacing, new Label("Flight: "), lArr[0], lArr[1], new Label("-->"), lArr[3], lArr[2]);
				hboxArray[1] = new HBox(spacing, new Label("Flight_id: "), lArr[4]);
				
				hboxArray[2] = new HBox(spacing, new Label("Class: "), lArr[5]);
				hboxArray[3] = new HBox(spacing, new Label("Departure Date: "),lArr[6]);
				hboxArray[4] = new HBox(spacing, new Label("Travellers Booked: "),lArr[7]);
				
				hboxArray[5] = new HBox(spacing, new Label("Name: "), lArr[8], lArr[9]);
				hboxArray[6] = new HBox(spacing, new Label("Email: "),lArr[10]);
				hboxArray[7] = new HBox(spacing, new Label("Phone: "), lArr[11]);

				getChildren().addAll(hboxArray);

				setSpacing(10);
			}
		}
		
		public String getID() {
			return id;
		}
	}
}

