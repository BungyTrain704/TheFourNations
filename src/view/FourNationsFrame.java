package view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FourNationsFrame extends JFrame {

	private static final long serialVersionUID = 3775114448785000079L;
	
	//Panels
	private Map<String, JPanel> panels;
	private MainMenuPanel mainMenuPanel;
	private NewGamePanel newGamePanel;
	
	//Panel labels
	public final static String mainMenu = "MAIN_MENU";
	public final static String newGame = "NEW_GAME";
	
	public FourNationsFrame() {
		initComponents();
		initFrame();
	}
	
	private void initFrame() {
		super.setSize( this.mainMenuPanel.getSize() );
		super.setLayout( null );
		super.setDefaultCloseOperation( EXIT_ON_CLOSE );
		super.setLocationRelativeTo( null );
		super.setUndecorated( true );
		super.setBackground( new Color( 0, 0, 0, 0 ) );
		super.setResizable( false );
		super.setVisible( true );
	}
	
	private void initComponents() {
		//Main menu
		this.mainMenuPanel = new MainMenuPanel( this );
		this.mainMenuPanel.setLocation( 0, 0 );
		
		//New game menu
		this.newGamePanel = new NewGamePanel( this );
		this.newGamePanel.setLocation( 0, 0 );
		this.newGamePanel.setSize( this.mainMenuPanel.getSize() );
		
		//Cards
		this.panels = new HashMap<String, JPanel>();
		this.panels.put( mainMenu, mainMenuPanel );
		this.panels.put( newGame, newGamePanel );
		
		//Show main menu at startup
		showPanel( mainMenu );
	}
	
	public static void main( String[] args ) {
		new FourNationsFrame();
	}
	
	public void showPanel( String panelName ) {
		super.setContentPane( this.panels.get( panelName ) );
	}
}
