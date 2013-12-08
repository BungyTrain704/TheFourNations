package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.GameImageLoader;

public class MainMenuPanel extends JPanel implements ClickHandler {

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
	
	private JFrame parent;


	//TODO: REMOVE - FOR TESTING
	public static void main( String[] args ) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jf.setVisible( true );
		jf.setResizable( false );
		jf.setSize( new Dimension( background.getWidth() + 10, background.getHeight() + 20 ) );
		jf.setLocationRelativeTo( null );
		jf.add( new MainMenuPanel( jf ) );
	}
	
	public MainMenuPanel( JFrame parent ) {
		super.setLayout( null );
		
		this.parent = parent;
		
		//Create buttons
		this.newGame = new InvisibleButton( this );
		this.newGame.setLocation( 394, 167 );
		this.newGame.setSize( new Dimension( BUTTON_WIDTH, BUTTON_HEIGHT ) );
		
		this.loadGame = new InvisibleButton( this );
		this.loadGame.setLocation( 394, 267 );
		this.loadGame.setSize( new Dimension( BUTTON_WIDTH, BUTTON_HEIGHT ) );
		
		this.howToPlay = new InvisibleButton( this );
		this.howToPlay.setLocation( 394, 367 );
		this.howToPlay.setSize( new Dimension( BUTTON_WIDTH, BUTTON_HEIGHT ) );
		
		this.quit = new InvisibleButton( this );
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
	@Override public void handleClick( Component component ) {
		if( component == this.newGame ) {
			System.out.println( "New game " );
		}
		
		else if( component == this.loadGame ) {
			System.out.println( "Load game" );
		}
		
		else if( component == this.howToPlay ) {
			System.out.println( "How to play" );
			new HowToPlayDialog( this.parent );
		}
		
		else if( component == this.quit ) {
			System.out.println( "Quit" );
			int confirm = JOptionPane.showConfirmDialog( this, "Do you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION );
			if( confirm == JOptionPane.YES_OPTION ) System.exit(0);
		}
	}
}