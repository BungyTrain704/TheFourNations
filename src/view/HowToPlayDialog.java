package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JDialog;
import javax.swing.JFrame;

import model.GameImageLoader;

public class HowToPlayDialog extends JDialog implements ClickHandler {
	private static final long serialVersionUID = -1885482032550659943L;
	private BufferedImage howToPlay = GameImageLoader.getImage(  GameImageLoader.imagesFolder + "HowToPlay.png" );

	public HowToPlayDialog( JFrame parent ) {
		super( parent, "How to Play", true );
		super.setSize( this.howToPlay.getWidth(), this.howToPlay.getHeight() + 25 );
		super.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		super.setResizable( false );
		super.setLayout( null );
		super.setLocationRelativeTo( parent );
		super.setVisible( true );
	}

	@Override public void paint( Graphics g ) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage( this.howToPlay, 0, 25, null );
	}

	public static void main( String[] args ) {
		new HowToPlayDialog( null );
	}

	@Override public void handleClick(Component component) {
		dispose();
	}
}
