package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.GameImageLoader;

public class MainMenuPanel extends JPanel {

	private static final long serialVersionUID = 3507196282474890639L;

	//Resource locations
	private static String fileSep = File.separator;
	private static String baseDirectory = System.getProperty("user.dir");
	private static String imagesFolder = baseDirectory + fileSep + "images" + fileSep;

	//Images
	private static BufferedImage background = GameImageLoader.getImage(imagesFolder + "MainMenu.png");

	//Components
	private final int BUTTON_WIDTH = 237;
	private final int BUTTON_HEIGHT = 75;
	private InvisibleButton newGame, loadGame, howToPlay, quit;


	//TODO: REMOVE - FOR TESTING
	public static void main( String[] args ) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jf.setVisible( true );
		jf.setSize( new Dimension( background.getWidth() + 10, background.getHeight() + 20 ) );
		jf.add( new MainMenuPanel() );
	}
	
	public MainMenuPanel() {
		super.setLayout( null );
		
		//Create buttons
		this.newGame = new InvisibleButton();
		this.newGame.setLocation( 394, 167 );
		this.newGame.setSize( new Dimension( BUTTON_WIDTH, BUTTON_HEIGHT ) );
		
		this.loadGame = new InvisibleButton();
		this.loadGame.setLocation( 394, 267 );
		this.loadGame.setSize( new Dimension( BUTTON_WIDTH, BUTTON_HEIGHT ) );
		
		this.howToPlay = new InvisibleButton();
		this.howToPlay.setLocation( 394, 367 );
		this.howToPlay.setSize( new Dimension( BUTTON_WIDTH, BUTTON_HEIGHT ) );
		
		this.quit = new InvisibleButton();
		this.quit.setLocation(394, 467 );
		this.quit.setSize( new Dimension( BUTTON_WIDTH, BUTTON_HEIGHT ) );
		
		//Add to frame
		super.add( this.newGame );
		super.add( this.loadGame );
		super.add( this.howToPlay );
		super.add( this.quit );
	}

	/**
	 * Draws the main menu background
	 */
	@Override public void paintComponent( Graphics g ) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage( background, 0, 0, null );
	}
	
	/**
	 * Performs an action based on which component was clicked. Called within
	 * the inner class InvisibleButton
	 */
	private void handleClick( Component clickedComponent ) {
		if( clickedComponent == this.newGame ) {
			System.out.println( "New game " );
		}
		
		else if( clickedComponent == this.loadGame ) {
			System.out.println( "Load game" );
		}
		
		else if( clickedComponent == this.howToPlay ) {
			System.out.println( "How to play" );
		}
		
		else if( clickedComponent == this.quit ) {
			System.out.println( "Quit" );
			int confirm = JOptionPane.showConfirmDialog( this, "Do you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION );
			if( confirm == JOptionPane.YES_OPTION ) System.exit(0);
		}
	}

	/**
	 * A transparent JPanel that gains a white border when moused over. It will
	 * also pass itself to MainMenuPanel when clicked.
	 * @author Christopher
	 *
	 */
	private class InvisibleButton extends JPanel implements MouseListener {
		private static final long serialVersionUID = -6894259703329191579L;
		private Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE, 5);
		
		public InvisibleButton() {
			super.addMouseListener( this );
			super.setOpaque(false);
		}
		
		@Override public void mouseEntered(MouseEvent e) {
			this.setBorder( whiteBorder );
		}

		
		@Override public void mouseExited(MouseEvent e) {
			this.setBorder( null );
		}
		
		@Override public void mouseClicked(MouseEvent e) { 
			handleClick(this);
		}
		
		/* Unused inherited methods */
		@Override public void mousePressed(MouseEvent e) { }
		@Override public void mouseReleased(MouseEvent e) { }
		
	}
}
