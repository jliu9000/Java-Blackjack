import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Card {

	private final Suit suit;
	private final Face face;
	private String imageFile;
	public BufferedImage cardImage = null;

	public Card(Suit suit, Face face)
	{
		this.suit = suit;
		this.face = face;
		setImageFile(suit, face);
		
		try {
		    cardImage = ImageIO.read(new File(imageFile));
		} catch (IOException e) {
		}

	}
	
	public String toString(){
		String s = "";
		s = this.face +" of " +this.suit;
		
		return s;
		
	}
	
	private void setImageFile(Suit s, Face f)
	{
		switch (s){
		case CLUBS:
			switch(f){
				case TWO:
					imageFile = "images/49.png";
					break;
				case THREE:
					imageFile = "images/45.png";
					break;
				case FOUR:
					imageFile = "images/41.png";
					break;
						
				case FIVE:
					imageFile = "images/37.png";
					break;
						
				case SIX:
					imageFile = "images/33.png";
					break;
						
				case SEVEN:
					imageFile = "images/29.png";
					break;
						
				case EIGHT:
					imageFile = "images/25.png";
					break;
						
				case NINE:
					imageFile = "images/21.png";
					break;
						
				case TEN:
					imageFile = "images/17.png";
					break;
						
				case JACK:
					imageFile = "images/13.png";
					break;
						
				case QUEEN:
					imageFile = "images/9.png";
					break;
						
				case KING:
					imageFile = "images/5.png";
					break;

						
				case ACE:
					imageFile = "images/1.png";
					break;
						
				default:
					System.out.println("image link generator failed, wrong card face input");

				
			}
			
			break;
		case DIAMONDS:
			switch(f){
				case TWO:
					imageFile = "images/52.png";
					break;
				case THREE:
					imageFile = "images/48.png";
					break;
				case FOUR:
					imageFile = "images/44.png";
					break;
					
				case FIVE:
					imageFile = "images/40.png";
					break;
					
				case SIX:
					imageFile = "images/36.png";
					break;
					
				case SEVEN:
					imageFile = "images/32.png";
					break;
					
				case EIGHT:
					imageFile = "images/28.png";
					break;
					
				case NINE:
					imageFile = "images/24.png";
					break;
					
				case TEN:
					imageFile = "images/20.png";
					break;
					
				case JACK:
					imageFile = "images/16.png";
					break;
					
				case QUEEN:
					imageFile = "images/12.png";
					break;
					
				case KING:
					imageFile = "images/8.png";
					break;

					
				case ACE:
					imageFile = "images/4.png";
					break;
				default:
					System.out.println("image link generator failed, wrong card face input");
		}
			
			break;
		case HEARTS:
			switch(f){
			
			case TWO:
				imageFile = "images/51.png";
				break;
			case THREE:
				imageFile = "images/47.png";
				break;
			case FOUR:
				imageFile = "images/43.png";
				break;
				
			case FIVE:
				imageFile = "images/39.png";
				break;
				
			case SIX:
				imageFile = "images/35.png";
				break;
				
			case SEVEN:
				imageFile = "images/31.png";
				break;
				
			case EIGHT:
				imageFile = "images/27.png";
				break;
				
			case NINE:
				imageFile = "images/23.png";
				break;
				
			case TEN:
				imageFile = "images/19.png";
				break;
				
			case JACK:
				imageFile = "images/15.png";
				break;
				
			case QUEEN:
				imageFile = "images/11.png";
				break;
				
			case KING:
				imageFile = "images/7.png";
				break;

				
			case ACE:
				imageFile = "images/3.png";
				break;
			default:
				System.out.println("image link generator failed, wrong card face input");
	}
			
			break;
		case SPADES:
			switch(f){
			case TWO:
				imageFile = "images/50.png";
				break;
			case THREE:
				imageFile = "images/46.png";
				break;
			case FOUR:
				imageFile = "images/42.png";
				break;
				
			case FIVE:
				imageFile = "images/38.png";
				break;
				
			case SIX:
				imageFile = "images/34.png";
				break;
				
			case SEVEN:
				imageFile = "images/30.png";
				break;
				
			case EIGHT:
				imageFile = "images/26.png";
				break;
				
			case NINE:
				imageFile = "images/22.png";
				break;
				
			case TEN:
				imageFile = "images/18.png";
				break;
				
			case JACK:
				imageFile = "images/14.png";
				break;
				
			case QUEEN:
				imageFile = "images/10.png";
				break;
				
			case KING:
				imageFile = "images/6.png";
				break;

				
			case ACE:
				imageFile = "images/2.png";
				break;
			default:
				System.out.println("image link generator failed, wrong card face input");
	}
			break;
		
		default:
			System.out.println("image link generator failed, wrong suit input");
	
	
	}
	
	}

	public Face getFace()
	{
		return this.face;
	}
	
	public String getSuit()
	{
		return this.suit.toString();
	}
	
	public String getImageFile()
	{
		return this.imageFile;
	}
	
	public BufferedImage getImage(){
		return this.cardImage;
	}
}
