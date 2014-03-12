
public class Card {

	private final Suit suit;
	private final Face face;
	private final String imageFile = "";
	
	public Card(Suit suit, Face face)
	{
		this.suit = suit;
		this.face = face;
		setImageFile();
	}
	
	private void setImageFile()
	{
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
}
