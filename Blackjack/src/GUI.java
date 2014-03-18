import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


public class GUI extends Applet implements MouseListener {
	
	private static final int WIDTH = 720;
	private static final int HEIGHT = 450;
	private Image background;
	private URL base;
	private int mouseX, mouseY;


	
    @Override
    public void init() {

		setSize(WIDTH, HEIGHT);
		setFocusable(true);
		setBackground(Color.BLACK);
		addMouseListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Blackjack");
		
		try {
			base = getDocumentBase();
			System.out.println(base);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
		
		background = getImage(base, "images/board.png");

	}
    
    

	@Override
	public void start() {
		repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, this);

	}
	

	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (mouseX>570 && mouseX<710){
			if(mouseY>140 && mouseY<180){
				//hit
				System.out.println("hit");
			} else if(mouseY>183 && mouseY<220){
				System.out.println("stand");

				//stand
			} else if(mouseY>225 && mouseY<260){
				System.out.println("double down");

				
				//double down
			} else if(mouseY>260 && mouseY<300){
				System.out.println("split");

				//split
			} else if(mouseY>400 && mouseY<438){
				System.exit(0);
			}
			
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		//dont need
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//dont need
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//dont need
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//dont need
		
	}
		

}
	
	
	
	
	

	


