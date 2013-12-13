package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.Timer;

import model.Civilization;
import model.GameImageLoader;
import model.Tribe;
import model.map.Map;
import model.map.Resource;
import model.map.Terrain;
import model.tasks.CollectResourceTask;

public class GameDisplayPanel extends JPanel {

	private static final long serialVersionUID = 2343561989439374616L;

	//Images
	private static BufferedImage grassImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "grass.png");
	private static BufferedImage waterImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "water.png");
	private static BufferedImage snowImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "snow.png");
	private static BufferedImage desertImg = GameImageLoader.getImage(GameImageLoader.imagesFolder+ "desert.png");
	private static BufferedImage cloudImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "clouds.png");
	private static BufferedImage snowTreeImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "snowTree.png");
	private static BufferedImage treeImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "tree.png");
	private static BufferedImage bareTreeImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "earthTree.png");
	private static BufferedImage stoneImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "stones.png");
	private static BufferedImage snowStoneImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "snowStones.png");
	private static BufferedImage earthStoneImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "earthStones.png");
	private static BufferedImage menuImg = GameImageLoader.getImage(GameImageLoader.imagesFolder + "bkg.png");
	private static BufferedImage waterDude1 = GameImageLoader.getImage(GameImageLoader.imagesFolder + "waterDude1.png");
	private static BufferedImage fireDude1 = GameImageLoader.getImage(GameImageLoader.imagesFolder + "fireDude1.png");
	private static BufferedImage earthDude1 = GameImageLoader.getImage(GameImageLoader.imagesFolder + "earthDude1.png");
	private static BufferedImage airDude1 = GameImageLoader.getImage(GameImageLoader.imagesFolder + "airDude1.png");

	//Sub-panels
	private JViewport gameView;
	private JPanel mapView;
	private JPanel miniMapView;
	private JPanel statsPanel;
	private JPanel commandPanel;
	private JPanel infoPanel;

	//Game components
	private Civilization civ = Civilization.getInstance();
	private Map map = civ.getMap();

	public GameDisplayPanel() {
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
//		mapView.setLocation(0, 0);
		mapView.setSize(map.getCols() * 16, map.getRows() * 16);
		mapView.setVisible(true);
		mapView.setBackground(Color.BLACK);
//		gameView.add(mapView);

//		// Set up the game view containing the main map
//		gameView = new JPanel();
//		gameView.setLayout(null);
//		gameView.setLocation(0, 60);
//		gameView.setSize(775, 550);
//		gameView.setVisible(true);
//		gamePanel.add(gameView);
		
		gameView = new JViewport();
		gameView.setSize(775, 550);
		gameView.setLocation(0, 60);
		gameView.setView(mapView);
		gameView.setViewPosition(new Point(500,500));
		super.add(gameView);
		
//        scrollPane = new JScrollPane();
////        scrollpane.setSize(775, 550);
////        scrollpane.setLocation(0, 60);
//        scrollPane.setViewportView(mapView);
//        gamePanel.add(scrollPane);

		// Set up mini map
		miniMapView = new MiniMapPanel();
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

	// Special panel for drawing the main map
	private class MainMapPanel extends JPanel implements MouseListener {
		private static final long serialVersionUID = 3447252446251327666L;
		Timer timer;

		public MainMapPanel() {
			ActionListener timeListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					civ.update();
					repaint();
				}
			};

			civ.getMap().getCell(1730).setResource(Resource.tree);
			civ.getMap().getCell(1910).setTerrain(Terrain.stockpile);
			civ.addTaskToQueue(new CollectResourceTask(5, 1730, civ
					.getMap()));
			// Start timer
			timer = new Timer(300, timeListener);
//			timer.start();
		}


		@Override protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			drawMap(g2);
		}

		/* Unused inherited methods */
		@Override public void mouseClicked(MouseEvent e) { }
		@Override public void mouseEntered(MouseEvent e) { }
		@Override public void mouseExited(MouseEvent e) { }
		@Override public void mousePressed(MouseEvent e) { }
		@Override public void mouseReleased(MouseEvent e) { }
	}

	// Special panel for drawing the mini map and navigating the main map
	private class MiniMapPanel extends JPanel implements MouseListener {
		private static final long serialVersionUID = 2911016188722752273L;
		private BufferedImage mapImg;

		public MiniMapPanel() {

			mapImg = new BufferedImage(mapView.getWidth(), mapView.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = mapImg.createGraphics();
			/*
			 * Why are these two scale values being commented in and out lol?
			 * I keep changing it to .35x.35 because the .175x.175 scale values
			 * make the minimap on my screen only take up a 4th of the panel. 
			 * 
			 * What gives :P
			 */
			//				g2d.scale(.175, .175);
			g2d.scale(.35, .35); 
			drawMap(g2d);
			g2d.dispose();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(mapImg, 0, 0, null);
			g2.setPaint(Color.RED);
			g2.drawRect(3, 3, (int) (gameView.getWidth() * .175),
					(int) (gameView.getHeight() * .175));
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {

		}

		/* Unused inherited methods */
		@Override public void mouseEntered(MouseEvent arg0) { }
		@Override public void mouseExited(MouseEvent arg0) { }
		@Override public void mousePressed(MouseEvent arg0) { }
		@Override public void mouseReleased(MouseEvent arg0) { }
	}

	public void drawMap(Graphics2D g2) {
		Tribe t = civ.getTribe();
		for (int i = 0; i < map.getRows(); i++) {
			for (int j = 0; j < map.getCols(); j++) {
				// Draw plains of the map
				if (civ.getMap().getCell(i, j).getTerrain()
						.equals(Terrain.plains)) {
					switch( t ) {
					case WATER: g2.drawImage(snowImg, j * 16, i * 16, null); break;
					case EARTH: g2.drawImage(desertImg, j * 16, i * 16, null); break;
					default: 	g2.drawImage(grassImg, j * 16, i * 16, null); break;
					}
				}
				// Draw water
				else if (civ.getMap().getCell(i, j).getTerrain()
						.equals(Terrain.water)) {
					switch( t ) {
					case AIR: g2.drawImage(cloudImg, j * 16, i * 16, null); break;
					default:  g2.drawImage(waterImg, j * 16, i * 16, null); break;
					
					}
				}
				// Overlay resources
				if (civ.getMap().getCell(i, j).hasResource()) {
					// Draw trees
					if (civ.getMap().getCell(i, j).getResource()
							.equals(Resource.tree)) {
						switch( t ) {
						case WATER: g2.drawImage( snowTreeImg.getSubimage(0, 16, 16, 16), j * 16, i * 16, null); break;
						case EARTH:g2.drawImage( bareTreeImg.getSubimage(0, 16, 16, 16), j * 16, i * 16, null); break;
						default: g2.drawImage(treeImg.getSubimage(0, 16, 16, 16), j * 16, i * 16, null); break;
						}
					}
					// Draw stones
					else if (civ.getMap().getCell(i, j).getResource()
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
						&& civ.getMap().getCell(i+1, j).hasResource()) {
					if (civ.getMap().getCell(i+1, j).getResource()
							.equals(Resource.tree)) {
						switch( t ) {
						case WATER: g2.drawImage(snowTreeImg.getSubimage(0, 0, 16, 16), j * 16, i * 16, null); break; //snow-covered
						case EARTH: g2.drawImage(bareTreeImg.getSubimage(0, 0, 16, 16), j * 16, i * 16, null); break; //less-bushy
						default: g2.drawImage(treeImg.getSubimage(0, 0, 16, 16), j * 16, i * 16, null); break; //bushy trees
						}
					}
				}
				for (int k = 0; k < civ.getUnits().size(); k++) {
					int location = civ.getUnits().get(k).getLocation();
					int row = location/map.getCols();
					int col = location%map.getCols();
					switch( t ) {
					case WATER: g2.drawImage(waterDude1, col * 16, row * 16, null); break;
					case FIRE: g2.drawImage(fireDude1, col * 16, row * 16, null); break;
					case EARTH: g2.drawImage(earthDude1, col * 16, row * 16, null); break;
					case AIR: g2.drawImage(airDude1, col * 16, row * 16, null); break;
					}
				}
			}
		}
	}
}