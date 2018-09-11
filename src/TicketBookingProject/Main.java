package TicketBookingProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main extends Application{

	private Stage stage;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		stage = primaryStage;
		//switchScene("BookTicket1.0.fxml");
		
		Controller control = new Controller(this);
		
		TicketSearch tS = new TicketSearch();
		tS.setController(control);
		
		primaryStage.setTitle("TicketBookingApp");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(tS, 640, 480));
		primaryStage.show();
	}
	
	protected static void iniGrid(GridPane gridPane ,int column, int row) {
		final double PERCENT_WIDTH = 100 / column;
		final double PERCENT_HEIGHT = 100 / row;

		ObservableList<ColumnConstraints>  columnConstrains = gridPane.getColumnConstraints();
		for(int i = 0; i < column; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(PERCENT_WIDTH);
			columnConstrains.add(colConst);
		}

		ObservableList<RowConstraints>  rowConstrains = gridPane.getRowConstraints();
		for(int i = 0; i < row; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(PERCENT_HEIGHT);
			rowConstrains.add(rowConst);
		}
	}

	
	
	public void setScene(Scene scene) {
		stage.setScene(scene);
		
	}

/*	public void switchScene(String fxml) throws IOException {
		Parent gPane = new FXMLLoader().load(getClass().getResource(fxml));
		stage.setScene(new Scene(gPane, 640, 480));	
	}*/
	
	

}
