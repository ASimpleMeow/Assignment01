package view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;


import controllers.Driver;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.AutoComplete;
import models.QuickAutocomplete;

/**
 * 
 * A Controller for the MainView, handles all interaction with the MainView window
 * as well as handling the input and output data for the AutoComplete
 * 
 * @author Oleksandr Kononov
 *
 */
public class MainViewController {
	
	@FXML
	private Button helpBtn;
	@FXML
	private Button exitBtn;
	@FXML
	private ComboBox<String> cmbBox;
	@FXML
	private TextField kField;
	@FXML
	private TextField weightOfField;
	@FXML
	private TextField weightOfResult;
	@FXML
	private TextField bestMatchField;
	@FXML
	private TextField bestMatchResult;
	private String url = "https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt";
	private AutoComplete autoComplete;
	private int k = 5;
	
	/**
	 * This method will be the first to run and will set the initial values 
	 * and update the appBox ComboBox.
	 */
	@FXML
	private void initialize()
	{
		try {
			autoComplete = new QuickAutocomplete(url);
		} catch (MalformedURLException e) {
			System.err.println("Error Navigating To The URL");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error While Taking In Data From URL Please Check You Internet Connection");
			e.printStackTrace();
		}
		
		//Changes the amount of rows to be displayed by k
		cmbBox.setVisibleRowCount(k);
		
		//Adds a listener to when the user focuses out of the kField in order to change the
		//k value when it has been detected that the value has changed in the TextField
		kField.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(!newValue) {
		        	try
		        	{
		        		k = new Integer(kField.getText()).intValue();
		        		cmbBox.setVisibleRowCount(k);
		        	}catch(Exception e)
		        	{
		        		Driver.showErrorMessage("ERROR", "IllegalArgumentException", "'k' Value Must Be An Integer");
		        		k = 5;
		        		kField.setText(Integer.toString(k));
		        	}
		        }
		    }
		});
		
		//Sets an EventHandler which will execute when a key is released
		//This will fill the ComboBox items with results from the matches method of AutoComplete
		cmbBox.setOnKeyReleased(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event)
        	{
        		cmbBox.hide();
    			cmbBox.getItems().clear();
        		String input = cmbBox.getEditor().getText().trim().toLowerCase();
        		String[] inputTokens = input.split("\\s+");
        		Iterator<String> it;
        		try
        		{
        			it = autoComplete.matches(inputTokens[inputTokens.length-1], k).iterator();
        			while(it.hasNext())
        			{
        				cmbBox.getItems().add(it.next());
        			}
            		cmbBox.show();
        		}catch (NullPointerException e) //Exception due to null results from matches
        		{
        			cmbBox.getItems().clear();
        		}catch (IllegalArgumentException e)
        		{
        			Driver.showErrorMessage("ERROR", "IllegalArgumentException", "The 'k' Value Must Be A Positive Integer!");
        		}
        	}
        });
		
		//Adds EventFilter of events involving release of key.
		//This is executed before the EventHandler so here I can catch special keys that
		//I want to perform a special function. E.g ENTER key and CONTROL Key
		cmbBox.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event) {
				//If ENETER is released, opens a browser and Goolge searches the input
				if(event.getCode() == KeyCode.ENTER)
				{
					String url = "https://www.google.ie/search?q=";
					String[] searchTokens = cmbBox.getEditor().getText().toString().split("\\s+");
					
					for(int i=0;i<searchTokens.length;i++)
						url+=searchTokens[i]+"+";
					
	    			try {
						Desktop.getDesktop().browse(new URI(url));
					} catch (IOException e) {
						Driver.showErrorMessage("ERROR", "IOException", "Problem taking in I/O, please check access to your computer");
						e.printStackTrace();
					} catch (URISyntaxException e) {
						Driver.showErrorMessage("ERROR", "URISyntaxException", "Internal Error of handling the URI has occured!");
						e.printStackTrace();
					}
				}
				//If CONTROL Key is released, then auto-completes the word from highest result in matches
				else if(event.getCode() == KeyCode.CONTROL)
				{
					String[] searchTokens = cmbBox.getEditor().getText().split("\\s+");
					String input = cmbBox.getEditor().getText();
					try{
						input = input.replaceFirst(searchTokens[searchTokens.length-1], cmbBox.getItems().get(0));
						cmbBox.getEditor().setText(input+" ");
						cmbBox.getEditor().end();
					}catch (IndexOutOfBoundsException e)
					{
						System.err.println("IndexOutOfBoundsException - Could Not AutoComplete Through CONTROL Key Due To There Being No Valid Input To AutoComplete");
					}
				}
			}
		});
        
		//Adds an EventHandler to handle any events involving releasing keys
		//This will allow near real-time output of results for the weightOf method
        weightOfField.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
        	@Override
        	public void handle(KeyEvent event)
        	{
        		
        		String input = weightOfField.getText().trim().toLowerCase();
        		weightOfResult.setText(Double.toString(autoComplete.weightOf(input)));
        		if(event.getCode().isWhitespaceKey())
        			weightOfField.setText("");
        		if(weightOfField.getText().equals(""))
        			weightOfResult.setText("");
        	}
        });
        
        //Adds an EventHandler to handle any events involving releasing keys
        //This will allow near real-time output of results for the bestMatch method
        bestMatchField.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
        	@Override
        	public void handle(KeyEvent event)
        	{
        		
        		String input = bestMatchField.getText().trim().toLowerCase();
        		bestMatchResult.setText(autoComplete.bestMatch(input));
        		if(event.getCode().isWhitespaceKey())
        			bestMatchField.setText("");
        		if(bestMatchField.getText().equals(""))
        			bestMatchResult.setText("");
        	}
        });

	}
	
	
	/**
	 * When the user clicks the help button, this method will call the static 
	 * showHelpMessage in the Driver class to display help.
	 */
	@FXML
	public void handleHelp()
	{
		Driver.showHelpMessage("Help Information","Olkesandr Kononov - Quick Auto-Complete",
				"This is my assignment of Auto-Complete. This is the Quick Auto-Complete "
				+ "implementation (can be changed to BruteForce in the code).\n\n\n"
				+ "Type in the k value to get k results for the matches of AutoComplete.\n\n"
				+ "Type in a prefix into the TextField\n\n"
				+ "Hit the CONTROL Key and the highest match from the list will replace your word.\n\n"
				+ "Hit the ENTER Key and the input in the TextField will be Google searched on you browser.\n\n"
				+ "Thank You For Reading.");
	}
	
	/**
	 * When the user clicks the exit button, this method will 
	 * close the program.
	 */
	@FXML
	private void handleExit()
	{
		Platform.exit();
		System.exit(0);//closes program.
	}
}
