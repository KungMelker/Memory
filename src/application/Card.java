package application;

import javafx.scene.image.Image;

public class Card {

	Image front;
	int size;
	int value;
	
	public Card(int value, int size) {
	
		this.size = size;
		this.front = new Image("/abstract/" + Integer.toString(value) + ".png",size,size, true, true);
		this.value = value;		
	}
	
	public Image getFront() {
		return front;
	}
	
	public int getValue() {
		return value;
	}
}
