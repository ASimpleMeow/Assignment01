package controllers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * Driver is the class that will be the first to run and will setup our
 * MainView window and manage pop-up windows for error's or information.
 * 
 * @author Oleksandr Kononov
 *
 */
public class Driver extends Application{
	
	//The window
	private Stage primaryStage;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Auto-Complete");
		this.primaryStage.setResizable(false);
		
		showMainView();
	}
	
	/**
	 * This method will setup our scene(everything thats inside a window) for the window.
	 */
	private void showMainView()
	{
		try
		{
			//Load Layout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Driver.class.getResource("../view/MainView.fxml"));
			AnchorPane mainLayout = (AnchorPane) loader.load();
			
			//Load Scene
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		//Making sure that the program closes once the window is closed
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);//Exit the program
            }
        });
	}
	
	/**
	 * This method will display a message to tell the user about the program.
	 * 
	 * @param title
	 * @param header
	 * @param message
	 */
	public static void showHelpMessage(String title, String header, String message)
	{
		 // Show the confirmation message.
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
	}
	
	
	/**
	 * This method will display a message to tell the user the operation resulted in an
	 * error.
	 * 
	 * @param title
	 * @param header
	 * @param message
	 */
	public static void showErrorMessage(String title, String header, String message)
	{
		 // Show the confirmation message.
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
	}
}
