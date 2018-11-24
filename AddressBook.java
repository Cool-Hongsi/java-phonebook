package Task1;

import javafx.scene.layout.BorderPane; // in order to divide grid
import javafx.scene.layout.FlowPane; // in order to control text field
import javafx.scene.layout.HBox; // in order to control button field
import javafx.scene.control.Button; // in order to create button objects
import javafx.scene.control.Label; // in order to create label objects
import javafx.scene.control.TextField; // in order to create text field objects
import javafx.geometry.Insets; // in order to use setPadding method in FlowPane
import javafx.geometry.Pos; // in order to use setAlignment method in HBox

public class AddressBook extends BorderPane { // AddressBook class (Design) has to inherit BorderPane Class

	/* Create FlowPane Object */
	FlowPane fp = new FlowPane(5, 5); // Set up the gap in text field
	
	/* Create TextField Object */
	TextField textName = new TextField();
	TextField textStreet = new TextField();
	TextField textCity = new TextField();
	TextField textState = new TextField();
	TextField textZip = new TextField();
	
	/* Create HBox Object */
	HBox hb = new HBox(5); // Set up the gap in button field
	
	/* Create Button Object */
	Button buttonAdd = new Button("Add"); // As soon as create button, the name of button is ' Add '
	Button buttonFirst = new Button("First"); // As soon as create button, the name of button is ' First '
	Button buttonNext = new Button("Next"); // As soon as create button, the name of button is ' Next '
	Button buttonPrevious = new Button("Previous"); // As soon as create button, the name of button is ' Previous '
	Button buttonLast = new Button("Last"); // As soon as create button, the name of button is ' Last '
	Button buttonUpdate = new Button("Update"); // As soon as create button, the name of button is ' Update '
	
	public AddressBook() { // This default method could be called 
						   // when AddressBook object (without parameter) is created in Contact.java
		setTextField();
		setButtonField();
	}
	
	public String getName() {
		return this.textName.getText();
	}
	
	public byte[] getNameByte() {
		return this.textName.getText().getBytes();
	}
	
	public String getStreet() {
		return this.textStreet.getText();
	}
	
	public byte[] getStreetByte() {
		return this.textStreet.getText().getBytes();
	}
	
	public String getCity() {
		return this.textCity.getText();
	}
	
	public byte[] getCityByte() {
		return this.textCity.getText().getBytes();
	}
	
	public String getState() {
		return this.textState.getText();
	}
	
	public byte[] getStateByte() {
		return this.textState.getText().getBytes();
	}
	
	public String getZip() {
		return this.textZip.getText();
	}
	
	public byte[] getZipByte() {
		return this.textZip.getText().getBytes();
	}
	
	public void setTextField() {

		fp.setPadding(new Insets(10, 10, 0, 10)); // By importing ' javafx.geometry.Insets ', it can control the padding
		fp.getChildren().addAll(
				new Label("Name"), textName,
				new Label("Street"), textStreet,
				new Label("City   "), textCity,
				new Label("State"), textState,
				new Label("Zip"), textZip); // set up label and text field in scene
		
		textName.setPrefColumnCount(25); // set up the length of text field
		textStreet.setPrefColumnCount(25); // set up the length of text field
		textCity.setPrefColumnCount(8); // set up the length of text field
		textState.setPrefColumnCount(2); // set up the length of text field
		textZip.setPrefColumnCount(5); // set up the length of text field
		
		setCenter(fp); // show the text field in scene
	}
	
	public void setButtonField() {

		hb.getChildren().addAll(
				buttonAdd,
				buttonFirst,
				buttonNext,
				buttonPrevious,
				buttonLast,
				buttonUpdate); // set up button field in scene
		
		hb.setAlignment(Pos.CENTER); // By importing ' javafx.geometry.Pos ', it can control the alignment
		
		setBottom(hb); // show the button field in scene
	}
}
