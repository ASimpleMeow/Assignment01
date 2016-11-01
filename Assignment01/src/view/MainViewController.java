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
		cmbBox.setVisibleRowCount(k);
		//Adds a listener to when the user focuses out of the kField
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
		        		Driver.showErrorMessage("ERROR", "Invalid Integer", "Please insert a valid integer into the k text field.");
		        		k = 5;
		        		kField.setText(Integer.toString(k));
		        	}
		        }
		    }
		});
		
		cmbBox.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER)
				{
					String url = "https://www.google.ie/search?q=";
					String[] searchTokens = cmbBox.getEditor().getText().toString().split("\\s+");
					
					for(int i=0;i<searchTokens.length;i++)
						url+=searchTokens[i]+"+";
					
	    			try {
						Desktop.getDesktop().browse(new URI(url));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		});
		
		
        cmbBox.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
        	@Override
        	public void handle(KeyEvent event)
        	{
        		if(event.getCode() != KeyCode.DOWN)
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
                			cmbBox.getItems().add(it.next());
                		cmbBox.show();
            		}catch (NullPointerException e)
            		{
            			cmbBox.getItems().clear();
            		}
        		}
        		//if (event.getCode() == KeyCode.SPACE)
        			//cmbBox.getItems().clear();
        	}
        });
        
        weightOfField.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
        	@Override
        	public void handle(KeyEvent event)
        	{
        		
        		String input = weightOfField.getText().trim().toLowerCase();
        		//System.out.println(autoComplete.bestMatch(input));
        		weightOfResult.setText(Double.toString(autoComplete.weightOf(input)));
        		if(event.getCode().isWhitespaceKey())
        			weightOfField.setText("");
        		if(weightOfField.getText().equals(""))
        			weightOfResult.setText("");
        	}
        });
        
        bestMatchField.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
        	@Override
        	public void handle(KeyEvent event)
        	{
        		
        		String input = bestMatchField.getText().trim().toLowerCase();
        		//System.out.println(autoComplete.bestMatch(input));
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
				+ "implementation. Type in the k value to get k result for the search");
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
	
	@FXML 
	private void handleSearch()
	{
		try {
			Desktop.getDesktop().browse(new URI("http://www.google.com"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
