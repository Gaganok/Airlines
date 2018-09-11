package TicketBookingProject;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class TicketList extends GridPane{
	
	Text logo;
	Button searchFlight;
	ListComponent lComponent;
	ScrollPane sPane;
	Controller controller;
	
	TicketList(ListComponent lc){
		
		searchFlight = new Button("Search Flight");
		logo = new Text("TicketBookingApp");
		
		searchFlight.setOnAction(e -> controller.searchFlight());
		
		lComponent = lc;
		
		sPane = new ScrollPane(lComponent);
		
		//GridIni
		Main.iniGrid(this, 2, 4);
		this.add(logo, 0, 0);
		this.add(searchFlight, 0, 1);
		this.add(sPane, 0, 1, 2, 3);
	}
	
	public void setListComponent(ListComponent lc) {
		lComponent = lc;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
