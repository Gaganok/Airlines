package TicketBookingProject;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ListComponent extends VBox{
	
	Item header;
	int HSpacing, VSpacing;
	Controller controller;
	
	ListComponent(int H, int V, ArrayList<String[]> result){
		super(V);
		
		VSpacing = V;
		HSpacing = H;
		
		setPadding(new Insets(20, 20, 20, 20));
		
		header = new Item(H, "Airline", "", "From", "", "To", "Price", "-1");
		header.setSelectVisible(false);
		
		addItem(header);
		
		addList(result);
	}

	private void addList(ArrayList<String[]> result) {
		for(String[] s : result)
			addItem(s[0], s[1], s[2], s[3], s[4], "$"+s[5], s[6]);
		
	}

	public void addItem(String airline, String sourceName, String source, String destinationName, String destination, String price, String flight_id) {
		getChildren().add(new Item(HSpacing, airline, sourceName, source, destinationName, destination, price, flight_id));
	}
	
	public void addItem(Item item) {
		getChildren().add(item);
	}
	
	public void removeItem(int id) {
		getChildren().remove(id);
	}
	
	public void clear() {
		getChildren().clear();
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	private class Item extends HBox {
		private Button select;
		//private Controller controller;
		private String flight_id;
		Item(int spacing, String airline, String sourceName, String source, String destinationName, String destination, String price, String flight_id){
			
			
			Label[] label = {new Label(airline), new Label(sourceName), new Label(source), 
					new Label(destinationName), new Label(destination), new Label(price)};
			
			this.flight_id = flight_id;
			/*for(int i = 0 ; i < label.length; i++) {
				label[i].setTextAlignment(TextAlignment.CENTER);
				label[i].setPrefSize(80, 20);
				label[i].setMaxSize(80, 20);
			}*/
			label[0].setPrefSize(100, 20);
			label[0].setMaxSize(100, 20);
			
			label[1].setPrefSize(30, 20);
			label[1].setMaxSize(30, 20);
			
			label[2].setPrefSize(80, 20);
			label[2].setMaxSize(80, 20);
			
			label[3].setPrefSize(30, 20);
			label[3].setMaxSize(30, 20);
			
			label[4].setPrefSize(80, 20);
			label[4].setMaxSize(80, 20);
			
			label[5].setPrefSize(50, 20);
			label[5].setMaxSize(50, 20);
			
			select = new Button("Select");
			select.setOnAction(e -> controller.book(flight_id));
			
			getChildren().addAll(label[0], label[1], label[2], label[3], label[4], label[5], select);
			setSpacing(spacing);
		}
		
		public void setSelectVisible(Boolean b) {
			select.setVisible(b);
		}
	}
}


