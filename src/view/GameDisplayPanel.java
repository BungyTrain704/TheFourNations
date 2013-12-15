package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import model.Civilization;
import model.CivilizationState;
import model.GameImageLoader;
import model.SaveLoadManager;
import model.Tribe;
import model.exceptions.DisallowedTaskException;
import model.map.Cell;
import model.map.Map;
import model.map.Resource;
import model.map.ResourceType;
import model.map.Terrain;
import model.structures.Bed;
import model.structures.Table;
import model.structures.Well;
import model.tasks.BuildStructureTask;
import model.tasks.CollectResourceTask;
import model.tasks.PlantResourceTask;

public class GameDisplayPanel extends JPanel {

	private static final long serialVersionUID = 2343561989439374616L;

	//Images
	private static BufferedImage grassImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "grass.png");
	private static BufferedImage waterImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "water.png");
	private static BufferedImage snowImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "snow.png");
	private static BufferedImage desertImg = GameImageLoader.getImage(GameImageLoader.imagesFolder+ "desert.png");
	private static BufferedImage cloudImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "clouds.png");
	private static BufferedImage coastImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "coast.png");
	private static BufferedImage snowCoastImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "snowCoast.png");
	private static BufferedImage desertCoastImg = GameImageLoader.getImage(GameImageLoader.imagesFolder+ "desertCoast.png");
	private static BufferedImage cloudCoastImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "cloudCoast.png");
	private static BufferedImage snowTreeImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "snowTree.png");
	private static BufferedImage treeImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "tree.png");
	private static BufferedImage bareTreeImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "earthTree.png");
	private static BufferedImage stoneImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "stones.png");
	private static BufferedImage snowStoneImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "snowStones.png");
	private static BufferedImage earthStoneImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "earthStones.png");
	private static BufferedImage waterDude1 = GameImageLoader.getImage(GameImageLoader.imagesFolder + "waterDude1.png");
	private static BufferedImage fireDude1 = GameImageLoader.getImage(GameImageLoader.imagesFolder + "fireDude1.png");
	private static BufferedImage earthDude1 = GameImageLoader.getImage(GameImageLoader.imagesFolder + "earthDude1.png");
	private static BufferedImage airDude1 = GameImageLoader.getImage(GameImageLoader.imagesFolder + "airDude1.png");
	private static BufferedImage kitchenImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "kitchenTile.png");
	private static BufferedImage barracksImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "woodenFloor.png");

	//Sub-panels
	private JPanel gamePanel;
	private JPanel mapView;
	private JPanel miniMapView;
	private JPanel statsPanel;
	private JPanel commandPanel;
	private JPanel infoPanel;
	private JScrollPane gameView;
	
	// Viewport variables
	private Point viewPosition;
	private JViewport viewport;
	private Point endPoint;

	//Game components
	private Map map = Civilization.getInstance().getMap();
	
	//Four nation frame
	private JFrame parent;
	
	//Tile selection
	private int currentlySelectedLocation = new Random().nextInt( Civilization.getInstance().getMap().getMapSize() ); //TODO: Uhh

	public GameDisplayPanel( JFrame parent ) {
		this.parent = parent;
		initializeGameView();
	}

	public void initializeGameView() {
		// Set up the game panel
		super.setLayout(null);
		super.setLocation(0, 0);
		super.setSize(1030, 735);
		super.setBackground( new Color(0, 0, 0, 0 ) );
		
		// Set up map view
		mapView = new MainMapPanel();
		mapView.setLayout(null);
		mapView.setSize(map.getCols() * 16, map.getRows() * 16);
		mapView.setVisible(true);
		mapView.setBackground(Color.BLACK);

		// Set up panel for gameView
		gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setLocation(0, 60);
		gamePanel.setSize(775, 550);
		gamePanel.setVisible(true);
		super.add(gamePanel);
		
		// gameView displays mapView
		gameView = new JScrollPane(mapView);
		ClickDragListener cdl = new ClickDragListener(mapView);
		gameView.getViewport().addMouseMotionListener(cdl);
		gameView.getViewport().addMouseListener(cdl);
		viewport = gameView.getViewport();
		gameView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		gameView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		gameView.setBackground(Color.GRAY);
        gameView.setSize(775, 550);
        gamePanel.add(gameView);
		
		// Set up mini map
		miniMapView = new MiniMapPanel();
		MiniClickDragListener mcdl = new MiniClickDragListener(mapView);
		miniMapView.addMouseMotionListener(mcdl);
		miniMapView.addMouseListener(mcdl);
		miniMapView.setLayout(null);
		miniMapView.setLocation(775, 360);
		miniMapView.setSize(255, 250);
		miniMapView.setVisible(true);
		miniMapView.setBackground(Color.DARK_GRAY);
		miniMapView.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		super.add(miniMapView);

		// Set up command panel
		commandPanel = new JPanel();
		commandPanel.setLayout(null);
		commandPanel.setLocation(0, 610);
		commandPanel.setSize(1030, 125);
		commandPanel.setVisible(true);
		commandPanel.setBackground(Color.DARK_GRAY);
		commandPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		super.add(commandPanel);

		// Set up stats panel
		statsPanel = new JPanel();
		statsPanel.setLayout(null);
		statsPanel.setLocation(0, 0);
		statsPanel.setSize(775, 60);
		statsPanel.setVisible(true);
		statsPanel.setBackground(Color.DARK_GRAY);
		statsPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		super.add(statsPanel);

		// Set up info panel
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setLocation(775, 0);
		infoPanel.setSize(255, 360);
		infoPanel.setVisible(true);
		infoPanel.setBackground(Color.DARK_GRAY);
		infoPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		super.add(infoPanel);
	}
	
	// moves mapView with click & drag
		private class ClickDragListener extends MouseAdapter {
		
		    private final Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		    private final Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		    private final Point pointClicked = new Point();
		    private JPanel panel;

		    public ClickDragListener(JPanel panel) {
		        this.panel = panel;
		        viewPosition = new Point(0,0);
		    }

		    public void mouseDragged(final MouseEvent e) {
		        endPoint = e.getPoint();
		        viewPosition = viewport.getViewPosition();
		        viewPosition.translate(pointClicked.x-endPoint.x, pointClicked.y-endPoint.y);
		        panel.scrollRectToVisible(new Rectangle(viewPosition, viewport.getSize()));
		        pointClicked.setLocation(endPoint);
		    }

		    public void mousePressed(MouseEvent e) {
		        panel.setCursor(handCursor);
		        pointClicked.setLocation(e.getPoint());
		    }

		    public void mouseReleased(MouseEvent e) {
		        panel.setCursor(defaultCursor);
//		        panel.repaint();
		    }
		}
		
		
		private class MiniClickDragListener extends MouseAdapter{
			
			 private final Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
			    private final Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
			    private final Point pointClicked = new Point();
			    private JPanel panel;

			    public MiniClickDragListener(JPanel panel) {
			        this.panel = panel;
			        viewPosition = new Point(0,0);
			    }

			    public void mouseDragged(final MouseEvent e) {
			        endPoint = e.getPoint();
			        viewPosition = viewport.getViewPosition();
			        viewPosition.translate(-4*(pointClicked.x-endPoint.x), -4*(pointClicked.y-endPoint.y));
			        panel.scrollRectToVisible(new Rectangle(viewPosition, viewport.getSize()));
			        pointClicked.setLocation(endPoint);
			    }

			    public void mousePressed(MouseEvent e) {
			        panel.setCursor(handCursor);
			        pointClicked.setLocation(e.getPoint());
			    }

			    public void mouseReleased(MouseEvent e) {
			        panel.setCursor(defaultCursor);
//			        panel.repaint();
			    }
		}
	
	private class CommandsPanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = 138601043040511594L;
		private JButton plantFood, plantTree, collectResource, buildWell, buildTable, buildBed;
		
		public CommandsPanel() {
			super.setLayout( new FlowLayout() );
			
			//Buttons
			this.plantFood = new JButton( "Plant food" );
			this.plantFood.addActionListener( this );
			
			this.plantTree = new JButton( "Plant tree" );
			this.plantTree.addActionListener( this );
			
			this.collectResource = new JButton( "Collect resource" );
			this.collectResource.addActionListener( this );
			
			this.buildBed = new JButton( "Build bed" );
			this.buildBed.addActionListener( this );
			
			this.buildTable = new JButton( "Build table" );
			this.buildTable.addActionListener( this );
			
			this.buildWell = new JButton( "Build well" );
			this.buildWell.addActionListener( this );
			
			super.add( this.plantFood );
			super.add( this.plantTree );
			super.add( this.collectResource );
			super.add( this.buildBed );
			super.add( this.buildWell );
			super.add( this.buildTable );
		}
		
		@Override public void actionPerformed(ActionEvent e) {
			Civilization civ = Civilization.getInstance();
			Map m = civ.getMap();
			Cell c = m.getCell(currentlySelectedLocation);
			
			
			if( e.getSource() == this.plantFood ) {
				try {
					Civilization.getInstance().addTaskToQueue( new PlantResourceTask(currentlySelectedLocation, m, Resource.garden ) );
				}
				catch( DisallowedTaskException dte ) {
					JOptionPane.showMessageDialog( parent, dte.getMessage(), "Invalid Task!", JOptionPane.ERROR_MESSAGE );
				}
			}
			
			else if( e.getSource() == this.plantTree ) {
				try {
					Civilization.getInstance().addTaskToQueue( new PlantResourceTask(currentlySelectedLocation, m, Resource.tree ) );
				}
				catch( DisallowedTaskException dte ) {
					JOptionPane.showMessageDialog( parent, dte.getMessage(), "Invalid Task!", JOptionPane.ERROR_MESSAGE );
				}
			}
			
			else if( e.getSource() == this.collectResource ) {
				if( c.hasResource() ) {
					try {
						Civilization.getInstance().addTaskToQueue( new CollectResourceTask( 7, currentlySelectedLocation, m ) );
					}
					catch( DisallowedTaskException dte ) {
						JOptionPane.showMessageDialog( parent, dte.getMessage(), "Invalid Task!", JOptionPane.ERROR_MESSAGE );
					}
				}
				else {
					JOptionPane.showMessageDialog( parent, "There is no resource to collect" , "Invalid Task!", JOptionPane.ERROR_MESSAGE );
				}
			}
		
			else if( e.getSource() == this.buildBed ) {
				try {
					Civilization.getInstance().addTaskToQueue( new BuildStructureTask( 5, m, 
							new Bed(currentlySelectedLocation, "Bed", ResourceType.wood ) ) );
				}
				catch( DisallowedTaskException dte ) {
					JOptionPane.showMessageDialog( parent, dte.getMessage(), "Invalid Task!", JOptionPane.ERROR_MESSAGE );
				}
			}
			
			else if( e.getSource() == this.buildTable ) {
				try {
					Civilization.getInstance().addTaskToQueue( new BuildStructureTask( 10, m, 
							new Table(currentlySelectedLocation, "Table", ResourceType.wood ) ) );
				}
				catch( DisallowedTaskException dte ) {
					JOptionPane.showMessageDialog( parent, dte.getMessage(), "Invalid Task!", JOptionPane.ERROR_MESSAGE );
				}
			}
			
			else if( e.getSource() == this.buildWell ) {
				try {
					Civilization.getInstance().addTaskToQueue( new BuildStructureTask( 20, m, 
							new Well(currentlySelectedLocation, "Well", ResourceType.stone ) ) );
				}
				catch( DisallowedTaskException dte ) {
					JOptionPane.showMessageDialog( parent, dte.getMessage(), "Invalid Task!", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
	}
	
	private class GameControlPanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = -8816383781883960549L;
		private JButton pauseResumeButton, exit;
		private final String PAUSE_GAME_TEXT = "Pause Game";
		private final String RESUME_GAME_TEXT = "Resume Game";
		
		public GameControlPanel() {
			super.setLayout( new FlowLayout() );
			
			this.pauseResumeButton = new JButton( PAUSE_GAME_TEXT );
			this.pauseResumeButton.addActionListener( this );
			
			this.exit = new JButton( "Exit" );
			this.exit.addActionListener( this );
			
			super.add( this.pauseResumeButton );
			super.add( this.exit );
		}
		
		@Override public void actionPerformed(ActionEvent ae) {
			if( ae.getSource() == this.pauseResumeButton ) {
				switch( this.pauseResumeButton.getText() ) {
				case PAUSE_GAME_TEXT:
					this.pauseResumeButton.setText( RESUME_GAME_TEXT );
					((FourNationsFrame)parent).pause();
					break;
				case RESUME_GAME_TEXT:
					this.pauseResumeButton.setText( PAUSE_GAME_TEXT );
					((FourNationsFrame)parent).resume();
					break;
				default:
					System.out.println( "In GameControlPanel.actionPerformed, unexpected button text" );
					break;
				}
			}
			
			else if ( ae.getSource() == this.exit ) {
				int saveGame = JOptionPane.showConfirmDialog( this, "Would you like to save your game?",
						"Save game?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE );
				switch( saveGame ) {
				case JOptionPane.CANCEL_OPTION: break;
				case JOptionPane.YES_OPTION:
					CivilizationState cs = new CivilizationState(Civilization.getInstance() );
					SaveLoadManager.saveGame( cs.getGameName(), cs );
					System.exit(0);
					break;
				case JOptionPane.NO_OPTION:
					System.exit(0);
					break;
				}
			}
		}
	}

	// Special panel for drawing the main map
	private class MainMapPanel extends JPanel {
		private static final long serialVersionUID = 3447252446251327666L;

		public MainMapPanel() {
			setPreferredSize(new Dimension(map.getCols() * 16, map.getRows() * 16)); 
		}

		@Override protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			drawMap(g2, true );
		}

	}

	// Special panel for drawing the mini map and navigating the main map
		private class MiniMapPanel extends JPanel {
			private static final long serialVersionUID = 2911016188722752273L;
			private BufferedImage mapImg = new BufferedImage(mapView.getWidth(), mapView.getHeight(),
					BufferedImage.TYPE_INT_ARGB);

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				
				mapImg = new BufferedImage(mapView.getWidth(), mapView.getHeight(),
						BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d = mapImg.createGraphics();
				g2d.scale(.24, .24); 
				drawMap( g2d, false );
				g2d.dispose();
				
				g2.drawImage(mapImg, 0, 0, null);
//				g2.setPaint(Color.RED);
//				g2.drawRect(2, 2, (int) (gamePanel.getWidth() * .24),
//						(int) (gamePanel.getHeight() * .24));
			}

		}

		public void drawMap( Graphics2D g2, boolean drawGridlines ) {
			Tribe t = Civilization.getInstance().getTribe();

			for (int i = 0; i < map.getRows(); i++) {
				for (int j = 0; j < map.getCols(); j++) {
					// Draw plains of the map
					if (Civilization.getInstance().getMap().getCell(i, j).getTerrain()
							.equals(Terrain.plains)) {
						switch( t ) {
						case WATER: g2.drawImage(snowImg, j * 16, i * 16, null); break;
						case EARTH: g2.drawImage(desertImg, j * 16, i * 16, null); break;
						default: 	g2.drawImage(grassImg, j * 16, i * 16, null); break;
						}
					}
					// Draw coast
					else if (Civilization.getInstance().getMap().getCell(i, j).getTerrain()
							.equals(Terrain.coast)) {
						switch( t ) {
						case AIR: g2.drawImage(cloudCoastImg, j * 16, i * 16, null); break;
						case WATER: g2.drawImage(snowCoastImg, j * 16, i * 16, null); break;
						case EARTH: g2.drawImage(desertCoastImg, j * 16, i * 16, null); break;
						default:  g2.drawImage(coastImg, j * 16, i * 16, null); break;
						
						}
					}
					// Draw water
					else if (Civilization.getInstance().getMap().getCell(i, j).getTerrain()
							.equals(Terrain.water)) {
						switch( t ) {
						case AIR: g2.drawImage(cloudImg, j * 16, i * 16, null); break;
						default:  g2.drawImage(waterImg, j * 16, i * 16, null); break;
						
						}
					}
					
					for (int k = 0; k < Civilization.getInstance().getUnits().size(); k++) {
						int location = Civilization.getInstance().getUnits().get(k).getLocation();
						int row = location/map.getCols();
						int col = location%map.getCols();
						switch( t ) {
						case WATER: g2.drawImage(waterDude1, col * 16 - 8, row * 15 + 4, null); break;
						case FIRE: g2.drawImage(fireDude1, col * 16 - 8, row * 15 + 4, null); break;
						case EARTH: g2.drawImage(earthDude1, col * 16 - 8, row * 15 + 4, null); break;
						case AIR: g2.drawImage(airDude1, col * 16 - 8, row * 15 + 4, null); break;
						}
					}
					
					// Overlay resources
					if (Civilization.getInstance().getMap().getCell(i, j).hasResource()) {
						// Draw trees
						if (Civilization.getInstance().getMap().getCell(i, j).getResource()
								.equals(Resource.tree)) {
							switch( t ) {
							case WATER: g2.drawImage( snowTreeImg.getSubimage(0, 16, 16, 16), j * 16, i * 16, null); break;
							case EARTH:g2.drawImage( bareTreeImg.getSubimage(0, 16, 16, 16), j * 16, i * 16, null); break;
							default: g2.drawImage(treeImg.getSubimage(0, 16, 16, 16), j * 16, i * 16, null); break;
							}
						}
						// Draw stones
						else if (Civilization.getInstance().getMap().getCell(i, j).getResource()
								.equals(Resource.stone)) {
							switch( t ) {
							case WATER: g2.drawImage(snowStoneImg, j * 16, i * 16, null); break; //snow covered stones
							case EARTH: g2.drawImage(earthStoneImg, j * 16, i * 16, null); break; //darker stones for earth
							default: g2.drawImage(stoneImg, j * 16, i * 16, null); break; //light stones for fire/air
							}
						}
					}
					// Draw tops of trees
					if (i + 1 < map.getRows()
							&& Civilization.getInstance().getMap().getCell(i+1, j).hasResource()) {
						if (Civilization.getInstance().getMap().getCell(i+1, j).getResource()
								.equals(Resource.tree)) {
							switch( t ) {
							case WATER: g2.drawImage(snowTreeImg.getSubimage(0, 0, 16, 16), j * 16, i * 16, null); break; //snow-covered
							case EARTH: g2.drawImage(bareTreeImg.getSubimage(0, 0, 16, 16), j * 16, i * 16, null); break; //less-bushy
							default: g2.drawImage(treeImg.getSubimage(0, 0, 16, 16), j * 16, i * 16, null); break; //bushy trees
							}
						}
					}
					
					//Draw grid lines
					if(drawGridlines) {
//						int cols = Civilization.getInstance().getMap().getCols();
//						int rows = Civilization.getInstance().getMap().getRows();
//						
//						for( int k = 0; k < cols; k++ ) {
//							Color previous = g2.getColor();
//							g2.setColor( Color.black );
//							g2.drawLine( 16 * k, 0, 16 * k, (int) this.getVisibleRect().getHeight() );
//							g2.setColor( previous );
//						}
//						
//						for( int g = 0; g < rows; g ++ ) {
//							Color previous = g2.getColor();
//							g2.setColor( Color.black );
//							g2.setColor( new Color(0,0,0) );
//							g2.drawLine( 0, 16 * g, this.getVisibleRect().width, 16 * g );
//							g2.setColor( previous );
//						}
					}		
				}
			}
			// Draw rectangle
			g2.setPaint(Color.RED);
			g2.drawRect(viewPosition.x-2, viewPosition.y-2, (int) (gamePanel.getWidth()+10),
					(int) (gamePanel.getHeight()+10));
		}
	}
