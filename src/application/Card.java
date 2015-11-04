package application;

import javafx.scene.image.Image;

public class Card {

	/**
	 * Creates cards and reads images.
	 * 
	 *@param value the value of the image. 
	 *@param size the size of the image.
	 *
	 */
	
	Image front;
	int size;
	int value;
	
	public Card(int value, int size) {
	
		this.size = size;
		this.front = new Image("/images/" + Integer.toString(value) + ".png",size,size, true, true);
		this.value = value;		
	}
	
	public Image getFront() {
		return front;
	}
	
	public int getValue() {
		return value;
	}
}
