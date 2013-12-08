package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * A transparent JPanel that gains a white border when moused over. When clicked, it will
 * send a message to it's handler.
 * @author Christopher
 *
 */
public class InvisibleButton extends JPanel implements MouseListener {
	private static final long serialVersionUID = -6894259703329191579L;
	private Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE, 5);
	private ClickHandler handler;

	public InvisibleButton( ClickHandler handler ) {
		super.addMouseListener( this );
		super.setOpaque(false);
		this.handler = handler;
	}

	@Override public void mouseEntered(MouseEvent e) {
		this.setBorder( whiteBorder );
	}


	@Override public void mouseExited(MouseEvent e) {
		this.setBorder( null );
	}

	@Override public void mouseClicked(MouseEvent e) { 
		if( this.handler != null ) this.handler.handleClick(this);
	}

	/* Unused inherited methods */
	@Override public void mousePressed(MouseEvent e) { }
	@Override public void mouseReleased(MouseEvent e) { }

}