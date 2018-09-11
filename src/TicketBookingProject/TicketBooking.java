package TicketBookingProject;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class TicketBooking extends GridPane{
	
	TextField name, surname, phone, email;
	Label nameLabel, surnameLabel, phoneLabel, emailLabel;
	Button submit, searchFlight;
	Text logo;
	ComboBox<String> gender;
	Controller controller;
	
	TicketBooking(){
		nameLabel = new Label("Name");
		surnameLabel = new Label("Surname");
		phoneLabel = new Label("Phone Number");
		emailLabel = new Label("Email");
		
		name = new TextField();
		surname = new TextField();
		phone = new TextField();
		email = new TextField();
		
		name.setPromptText("John");
		surname.setPromptText("Black");
		phone.setPromptText("0860452642");
		email.setPromptText("Email@mail.ie");
		
		gender = new ComboBox<String>();
		gender.setPromptText("Select Gender");
		gender.getItems().addAll("Male", "Female");
		
		submit = new Button("Submit");
		searchFlight = new Button("Search Flight");
		
		searchFlight.setOnAction(e -> controller.searchFlight());
		submit.setOnAction(e -> controller.submit(name, surname, phone, email, gender));
		
		logo = new Text("TicketBookingApp");
		
		//GridINI
		this.setPadding(new Insets(20, 20, 20, 20));

		Main.iniGrid(this, 4, 7);
		add(logo, 1, 0);
		add(nameLabel, 1, 1);
		add(surnameLabel, 1, 2);
		add(gender, 1, 3);
		add(phoneLabel, 1, 4);
		add(emailLabel, 1, 5);
		add(searchFlight, 2, 0);
		add(name, 2, 1);
		add(surname, 2, 2);
		add(phone, 2, 4);
		add(email, 2, 5);
		add(submit, 2, 6);
		
		
		this.getStylesheets().add(getClass().getResource("styleSheet.css").toString());

	}
	
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
