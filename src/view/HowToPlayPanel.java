package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import model.GameImageLoader;

/**
 * A panel containing instructions on how to play the game
 * @author Christopher
 *
 */
public class HowToPlayPanel extends JPanel{

	private static final long serialVersionUID = -6401990241674052192L;
	
	//Images
	private BufferedImage howToPlay = GameImageLoader.getImage( GameImageLoader.imagesFolder + "HowToPlayScroll.png" );
	
	//Components
	private InvisibleButton exitButtonHandler;

	public HowToPlayPanel( ClickHandler handler) {
		//Panel settings
		super.setLayout( null );
		super.setSize( new Dimension( howToPlay.getWidth(), howToPlay.getHeight() ) );
		super.setBackground( new Color( 0, 0, 0, 0 ) );
		super.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED ) );
		
		//Invisible button
		this.exitButtonHandler = new InvisibleButton( handler, Color.white, 3 );
		this.exitButtonHandler.setSize( 105, 45 );
		this.exitButtonHandler.setLocation( 240, 630 );
		
		//Add button
		super.add( exitButtonHandler );
	}
	
	@Override public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage( howToPlay, 0, 0, null );
	}
}
