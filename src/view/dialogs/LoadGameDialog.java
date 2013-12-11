package view.dialogs;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.ClickHandler;

public class LoadGameDialog extends JDialog implements ClickHandler {

	private static final long serialVersionUID = 4351947229344581937L;
	private LoadGamePanel lgp;

	public LoadGameDialog( JFrame parent ) {
		this.lgp = new LoadGamePanel( this );
		super.setSize( this.lgp.getWidth(), this.lgp.getHeight() );
		super.setResizable( false );
		super.setUndecorated( true );
		super.setBackground( new Color( 0, 0, 0, 0) );
		super.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		super.setVisible( true );
		super.setLocationRelativeTo( parent );
		
		super.setContentPane( this.lgp );
	}

	@Override public void handleClick(Component component) {
		System.out.println( component.getName() );
		if( component.getName() == LoadGamePanel.GO_BACK)
			dispose();
		else if( component.getName() == LoadGamePanel.LOAD )
			System.out.println( "Load Game" );
	}
}
