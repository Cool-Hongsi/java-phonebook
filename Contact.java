package Task1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

public class Contact extends Application { // Contact class (File) has to inherit Application Class

	/* Main Class */
	public static void main(String[] args) {
		Application.launch(args); // Not needed for running from the command line
	}
	
	AddressBook obj = new AddressBook(); // ' obj ' is set up all of design by AddressBook.java
	
	byte sizeName = 32, sizeStreet = 32, sizeCity = 20, sizeState = 2, sizeZip = 5; // set up the size of each field
	byte[] sizeNameArr, sizeStreetArr, sizeCityArr, sizeStateArr, sizeZipArr,
	       dataNameArr, dataStreetArr, dataCityArr, dataStateArr, dataZipArr;
	int count = 0; // in order to find index of Contact information
	
	@Override // from Application class
	public void start(Stage primaryStage) { // Always has to get ' Stage primaryStage ' as parameter
		
        // **** I can access FlowPane & HBox class by using ' obj ' which is the object of AddressBook class 
		// since FlowPane & HBox class are declared in AddressBook class ****
		
		Scene scene = new Scene(obj, 350, 130);
		primaryStage.setTitle("Address Book"); // The title of Application
		primaryStage.setScene(scene); // put the scene in stage
		primaryStage.show(); // show the stage
		
		obj.buttonAdd.setOnAction(e -> add()); // This is same as ' onClick Handler ' in javascript , Once I click the ' Add ' button, it will call add function
		obj.buttonFirst.setOnAction(e -> first()); // This is same as ' onClick Handler ' in javascript , Once I click the ' First ' button, it will call first function
		obj.buttonNext.setOnAction(e -> next()); // This is same as ' onClick Handler ' in javascript , Once I click the ' Next ' button, it will call next function
		obj.buttonPrevious.setOnAction(e -> previous()); // This is same as ' onClick Handler ' in javascript , Once I click the ' Previous ' button, it will call previous function
		obj.buttonLast.setOnAction(e -> last()); // This is same as ' onClick Handler ' in javascript , Once I click the ' Last ' button, it will call last function
		obj.buttonUpdate.setOnAction(e -> update()); // This is same as ' onClick Handler ' in javascript , Once I click the ' Update ' button, it will call update function
	}
	
	public void write(RandomAccessFile raf) throws IOException { // write input values in Address.dat file
		
		/* Error : Typing more than length */
		if(obj.getName().length() > 32 || obj.getStreet().length() > 32 || obj.getCity().length() > 20 
				|| obj.getState().length() > 2 || obj.getZip().length() > 5) {
			System.out.println("Invalid Input (Error : typing more than length)");
		}
		
		/* Error : Typing 0 */
		else if(obj.getName().length() == 0 || obj.getStreet().length() == 0 || obj.getCity().length() == 0 
				|| obj.getState().length() == 0 || obj.getZip().length() == 0) {
			System.out.println("Invalid Input (Error : typing 0)");
		}
		
		/* write dataByte in Address.dat */
		else {
			sizeNameArr = new byte[sizeName];
			sizeStreetArr = new byte[sizeStreet];
			sizeCityArr = new byte[sizeCity];
			sizeStateArr = new byte[sizeState];
			sizeZipArr = new byte[sizeZip];
			
			dataNameArr = obj.getNameByte();
			dataStreetArr = obj.getStreetByte();
			dataCityArr = obj.getCityByte();
			dataStateArr = obj.getStateByte();
			dataZipArr = obj.getZipByte();
			
			for(int i=0; i<obj.getNameByte().length; i++) { // As much as the length of Name, insert data into sizeNameArr
				sizeNameArr[i] = dataNameArr[i];
			}
			
			for(int i=0; i<obj.getStreetByte().length; i++) { // As much as the length of Street, insert data into sizeStreetArr
				sizeStreetArr[i] = dataStreetArr[i];
			}
			
			for(int i=0; i<obj.getCityByte().length; i++) { // As much as the length of City, insert data into sizeCityArr
				sizeCityArr[i] = dataCityArr[i];
			}
			
			for(int i=0; i<obj.getStateByte().length; i++) { // As much as the length of State, insert data into sizeStateArr
				sizeStateArr[i] = dataStateArr[i];
			}
			
			for(int i=0; i<obj.getZipByte().length; i++) { // As much as the length of Zip, insert data into sizeZipArr
				sizeZipArr[i] = dataZipArr[i];
			}
			raf.write(sizeNameArr);
			raf.write(sizeStreetArr); 
			raf.write(sizeCityArr);
			raf.write(sizeStateArr);
			raf.write(sizeZipArr);
			raf.writeBytes(System.getProperty("line.separator")); // NEW LINE ADD !!!!
		}
	}

	public void read(RandomAccessFile raf) throws IOException {
		@SuppressWarnings("unused")
		int pos;
		
		byte[] name = new byte[sizeName];	
		pos = raf.read(name);
		obj.textName.setText(new String(name)); // the value in textName insert into name array

		byte[] street = new byte[sizeStreet];	
		pos += raf.read(street);
		obj.textStreet.setText(new String(street)); // the value in textStreet insert into street array

		byte[] city = new byte[sizeCity];	
		pos += raf.read(city);
		obj.textCity.setText(new String(city)); // the value in textCity insert into city array

		byte[] state = new byte[sizeState];	
		pos += raf.read(state);
		obj.textState.setText(new String(state)); // the value in textState insert into state array

		byte[] zip = new byte[sizeZip];	
		pos += raf.read(zip);
		obj.textZip.setText(new String(zip)); // the value in textZip insert into zip array
	}

	public void add() {
		// in order to use RandomAccessFile, has to use try - catch
		try (RandomAccessFile raf = new RandomAccessFile("Address.dat", "rw")) // Create RandomAccessFile object
		{
			raf.seek(raf.length()); // find index in raf file
			                        // # 0 -> start from 0
			                        // # 1 -> start from 93 (32 + 32 + 20 + 2 + 5 + '\n')
			                        // # 2 -> start from 186 (32 + 32 + 20 + 2 + 5 + '\n') * 2
			                        // # 3 -> start from 279 (32 + 32 + 20 + 2 + 5 + '\n') * 3
		 	write(raf); // store the contact information at the above position
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch (IndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}

	public void first() {
		try (RandomAccessFile raf = new RandomAccessFile("Address.dat", "rw")) // Create RandomAccessFile object
		{
			raf.seek(0); // find first index
			read(raf); // read information about first index
			count = 1; // in order to use next() , previous() , last() , update () (set the position)
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void next() {
		try (RandomAccessFile raf = new RandomAccessFile("Address.dat", "rw")) // Create RandomAccessFile object
		{
			if (count * 93 < raf.length()) { // For example
				                             // I stored three contact information -> raf.length() = 279
				                             // the position is #1 index -> count * 93 = 93 (count = 1)
				                             // clicking one more -> the position is #2 index -> count * 93 = 186 (count = 2)
				raf.seek(count * 93); // find 93 position index in raf file
				                      // find 186 position index in raf file
				read(raf); // read information about corresponding index
				count++; // Important !!! to retrieve next information
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void previous() {
		try (RandomAccessFile raf = new RandomAccessFile("Address.dat", "rw")) // Create RandomAccessFile object
		{
			if (count > 1) { // the position is more than #1 index
				count--; // Important !!! to retrieve previous information
			}
			else { // the position is #0 index
				count = 1;
			}
			raf.seek((count * 93) - 93); // 93 means that the length of one contact information (32 + 32 + 20 + 2 + 5 + '\n')
			                             // raf.seek((count * 93) - 93);
			                             // count * 93 -> find current position of contact information
			                             // - 93       -> find previous position by subtracting the length of one contact information
			read(raf); // read information about corresponding index
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void last() {
		try (RandomAccessFile raf = new RandomAccessFile("Address.dat", "rw")) // Create RandomAccessFile object
		{
			count = ((int)raf.length()) / 93; // in order to divide 93 (93 means the length of one contact information)
			                                  // I need to cast raf.length() as (int)
			raf.seek((count * 93) - 93); // 93 means that the length of one contact information (32 + 32 + 20 + 2 + 5 + '\n')
								         // raf.seek((count * 93) - 93);
			                             // count * 93 -> as long as count is 1, find the # 1 index
			                             // count * 93 -> as long as count is 2, find the # 2 index
								         // - 93       -> find previous position by subtracting the length of one contact information
			                             // as long as count is 1, find the # 0 index (0 position)
			                             // as long as count is 2, find the # 1 index (93 position)
			read(raf); // read information about corresponding index
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void update() {
		try (RandomAccessFile raf = new RandomAccessFile("Address.dat", "rw")) // Create RandomAccessFile object
		{
			raf.seek(count * 93 - 93); // in order to find # 0 index, count should be 1 -> 0 position (0 - 92)
			                           // in order to find # 1 index, count should be 2 -> 91 position (93 - 185)
			                           // in order to find # 2 index, count should be 3 -> 182 position (186 - 278)
		 	write(raf); // store contact information again
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
