package TicketBookingProject;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TicketSearch extends GridPane{

	RadioButton singleRad, returnRad;
	TextField source, destination, departureField, returnField;
	Label from, to, departureLabel, returnLabel, directFlightLabel, travellersLabel, travellerNumber;
	Button plus, minus, search,	findMyBooking, swap;
	Text logo;
	ComboBox<String> classBox;
	VBox fromVBox, toVBox, departureVBox, returnVBox, travellerVBox, cabinVBox;
	HBox radButHBox, sourceDestinationHBox, departureReturnHBox, travellerClassHBox;
	Controller controller;
	
	TicketSearch(){
		int VSpacing = 3, HSpacing = 15;

		//RadioButtons
		singleRad = new RadioButton("Single");
		returnRad = new RadioButton("Return");
		singleRad.setSelected(true);
		

		//TextFields 
		source = new TextField();
		destination = new TextField();
		departureField = new TextField();
		returnField = new TextField();
		
		source.setPromptText("Dublin");
		destination.setPromptText("London");
		departureField.setPromptText("14/04/2018");
		returnField.setPromptText("25/06/2050");
		
		//No Return, Simplification
		returnRad.setDisable(true);  		
		returnField.setDisable(true);

		//Label
		from = new Label("From");
		to = new Label("To");
		departureLabel = new Label("Departure");
		returnLabel = new Label("Return");
		directFlightLabel = new Label("*Direct Flights Only");
		travellersLabel = new Label("Travellers");
		travellerNumber = new Label("1");

		//Button
		plus = new Button("+");
		minus = new Button("-");
		search = new Button("Search");
		findMyBooking = new Button("Find My Booking");
		swap = new Button("<>");

		//ButtonActionHandlers
		plus.setOnAction(e -> addPassenger());
		minus.setOnAction(e -> removePassenger());
		swap.setOnAction(e -> swap());
		findMyBooking.setOnAction(e -> controller.findBooking());
		search.setOnAction(e ->  controller.search(source, destination, classBox, travellerNumber, departureField));

		//Logo
		logo = new Text("TicketBookingApp");

		//ComboBox
		classBox = new ComboBox<String>();
		classBox.setPromptText("Cabin Class");
		//classBox.getItems().addAll("Econom", "Business");
		classBox.getItems().add("Econom"); // Simplified

		//VBox
		fromVBox = new VBox(VSpacing, from, source);
		toVBox = new VBox(VSpacing, to, destination);
		departureVBox = new VBox(VSpacing, departureLabel, departureField);
		returnVBox = new VBox(VSpacing, returnLabel, returnField);
		travellerVBox = new VBox(VSpacing, travellersLabel, travellerNumber);
		cabinVBox = new VBox(VSpacing, classBox, directFlightLabel);

		//HBox
		radButHBox = new HBox(singleRad, returnRad);
		sourceDestinationHBox = new HBox(HSpacing, fromVBox, toVBox);
		departureReturnHBox = new HBox(HSpacing, departureVBox, returnVBox);
		travellerClassHBox = new HBox(HSpacing, minus, travellerVBox, plus, cabinVBox );

		//GridIni
		this.setPadding(new Insets(20, 20, 20, 20));
		Main.iniGrid(this, 2, 4);
		add(logo, 0, 0);
		add(radButHBox, 0, 1);
		add(sourceDestinationHBox, 0, 2);
		add(travellerClassHBox, 0, 3);
		add(findMyBooking, 1, 0);
		add(departureReturnHBox, 1, 2);
		add(search, 1, 3);

		this.getStylesheets().add(getClass().getResource("styleSheet.css").toString());

	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	private void addPassenger() {
		travellerNumber.setText((Integer.parseInt(travellerNumber.getText()) + 1) + "");

	}

	private void removePassenger() {
		int i = Integer.parseInt(travellerNumber.getText());

		if(i > 1)
			travellerNumber.setText((i - 1) + "");
	}

	public void swap() {
		String s = destination.getText();
		destination.setText(source.getText());
		source.setText(s);
	}

}
