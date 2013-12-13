package view;

import static model.GameImageLoader.getImage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.Timer;
import javax.swing.border.Border;

import model.Civilization;
import model.map.Map;
import model.map.Resource;
import model.map.Terrain;
import model.tasks.CollectResourceTask;

public class Game extends JFrame {

	private Map map;
	private Civilization civ;

	// Main JPanel
	private JPanel mainPanel;

	// Menu JPanels
	private JPanel menuView;
	private JPanel fireNation;
	private JPanel waterTribe;
	private JPanel earthKingdom;
	private JPanel airTemple;

	// Game JPanels
	private JPanel gamePanel;
	private JViewport gameView;
	private JPanel mapView;
	private JPanel miniMapView;
	private JPanel statsPanel;
	private JPanel commandPanel;
	private JPanel infoPanel;

	// Map scroll pane -- not yet used
	private JScrollPane scrollPane;

	// Resource locations
	private static String fileSep = File.separator;
	private static String baseDirectory = System.getProperty("user.dir");
	private static String imagesFolder = baseDirectory + fileSep + "images"
			+ fileSep;

	// BufferedImage set
	private static BufferedImage grassImg = getImage(imagesFolder + "grass.png");
	private static BufferedImage waterImg = getImage(imagesFolder + "water.png");
	private static BufferedImage snowImg = getImage(imagesFolder + "snow.png");
	private static BufferedImage desertImg = getImage(imagesFolder
			+ "desert.png");
	private static BufferedImage cloudImg = getImage(imagesFolder
			+ "clouds.png");
	private static BufferedImage snowTreeImg = getImage(imagesFolder
			+ "snowTree.png");
	private static BufferedImage treeImg = getImage(imagesFolder + "tree.png");
	private static BufferedImage bareTreeImg = getImage(imagesFolder
			+ "earthTree.png");
	private static BufferedImage stoneImg = getImage(imagesFolder
			+ "stones.png");
	private static BufferedImage snowStoneImg = getImage(imagesFolder
			+ "snowStones.png");
	private static BufferedImage earthStoneImg = getImage(imagesFolder
			+ "earthStones.png");
	private static BufferedImage menuImg = getImage(imagesFolder + "bkg.png");
	private static BufferedImage waterDude1 = getImage(imagesFolder
			+ "waterDude1.png");
	private static BufferedImage fireDude1 = getImage(imagesFolder
			+ "fireDude1.png");
	private static BufferedImage earthDude1 = getImage(imagesFolder
			+ "earthDude1.png");
	private static BufferedImage airDude1 = getImage(imagesFolder
			+ "airDude1.png");

	// Play type booleans
	private boolean playWater;
	private boolean playFire;
	private boolean playEarth;
	private boolean playAir;
	private boolean playing;

	public static void main(String[] args) {
		JFrame window = new Game();
		window.setVisible(true);
	}

	public Game() {

		map = new Map();
		civ = Civilization.getInstance();
		civ.setMap(map);

		playing = false;

		setupWindow();

		mainPanel = new JPanel(null);
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLocation(0, 0);
		this.add(mainPanel);
		initializeMenuView();
	}

	private void setupWindow() {
		this.setSize(1030, 735);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("The Four Nations");
	}

	private void initializeMenuView() {

		// Set up menu background
		menuView = new MenuPanel();
		menuView.setLayout(null);
		menuView.setLocation(0, 0);
		menuView.setSize(1030, 735);
		menuView.setVisible(true);
		menuView.setBackground(Color.BLACK);
		mainPanel.add(menuView);

		// Set up fire nation panel
		fireNation = new NationPanel();
		fireNation.setSize(138, 138);
		fireNation.setLocation(110, 476);
		fireNation.setToolTipText("Fire Nation");
		menuView.add(fireNation);

		// Set up water tribe panel
		waterTribe = new NationPanel();
		waterTribe.setSize(138, 138);
		waterTribe.setLocation(110, 98);
		waterTribe.setToolTipText("Water Tribe");
		menuView.add(waterTribe);

		// Set up earth kingdom panel
		earthKingdom = new NationPanel();
		earthKingdom.setSize(136, 138);
		earthKingdom.setLocation(780, 98);
		earthKingdom.setToolTipText("Earth Kingdom");
		menuView.add(earthKingdom);

		// Set up air temple panel
		airTemple = new NationPanel();
		airTemple.setSize(136, 138);
		airTemple.setLocation(780, 476);
		airTemple.setToolTipText("Air Temple");
		menuView.add(airTemple);
	}

	public void initializeGameView() {

		// Set up the game panel
		gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setLocation(0, 0);
		gamePanel.setSize(1030, 735);
		gamePanel.setVisible(false);
		mainPanel.add(gamePanel);
		
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
		gamePanel.add(gameView);
		
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
		gamePanel.add(miniMapView);

		// Set up command panel
		commandPanel = new JPanel();
		commandPanel.setLayout(null);
		commandPanel.setLocation(0, 610);
		commandPanel.setSize(1030, 125);
		commandPanel.setVisible(true);
		commandPanel.setBackground(Color.DARK_GRAY);
		commandPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		gamePanel.add(commandPanel);

		// Set up stats panel
		statsPanel = new JPanel();
		statsPanel.setLayout(null);
		statsPanel.setLocation(0, 0);
		statsPanel.setSize(775, 60);
		statsPanel.setVisible(true);
		statsPanel.setBackground(Color.DARK_GRAY);
		statsPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		gamePanel.add(statsPanel);

		// Set up info panel
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setLocation(775, 0);
		infoPanel.setSize(255, 360);
		infoPanel.setVisible(true);
		infoPanel.setBackground(Color.DARK_GRAY);
		infoPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		gamePanel.add(infoPanel);

	}

	// Special Menu Panel for drawing Four Nation Map background
	public class MenuPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(menuImg, 0, 0, null);
		}
	}

	// Special panel with mouse listener for selecting a nation from the Four
	// Nation Map background
	private class NationPanel extends JPanel implements MouseListener {
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE, 5);

		NationPanel() {
			addMouseListener(this);
			setFocusable(true);
			setOpaque(false);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Component c = (Component) e.getSource();
			if (c.equals(waterTribe)) {
				playWater = true;
			} else if (c.equals(fireNation)) {
				playFire = true;
			} else if (c.equals(earthKingdom)) {
				playEarth = true;
			} else {
				playAir = true;
			}
			playing = true;
			initializeGameView();
			menuView.setVisible(!playing);
			gamePanel.setVisible(playing);

		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			setBorder(whiteBorder);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setBorder(null);
		}
	}

	// Special panel for drawing the main map
	public class MainMapPanel extends JPanel implements MouseListener {

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
			timer.start();
		}

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			drawMap(g2);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	// Special panel for drawing the mini map and navigating the main map
	public class MiniMapPanel extends JPanel implements MouseListener {

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
//			g2d.scale(.175, .175);
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

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	public void drawMap(Graphics2D g2) {
		for (int i = 0; i < map.getRows(); i++) {
			for (int j = 0; j < map.getCols(); j++) {
				// Draw plains of the map
				if (civ.getMap().getCell(i, j).getTerrain()
						.equals(Terrain.plains)) {
					if (playFire || playAir) { // for fire and air, draw grass
												// plains
						g2.drawImage(grassImg, j * 16, i * 16, null);
					} else if (playWater) { // for water, draw snow plains
						g2.drawImage(snowImg, j * 16, i * 16, null);
					} else { // for earth, draw sand/desert plains
						g2.drawImage(desertImg, j * 16, i * 16, null);
					}
				}
				// Draw water
				else if (civ.getMap().getCell(i, j).getTerrain()
						.equals(Terrain.water)) {
					if (playFire || playEarth || playWater) { // for non-air,
																// draw water
						g2.drawImage(waterImg, j * 16, i * 16, null);
					} else { // for air, draw clouds
						g2.drawImage(cloudImg, j * 16, i * 16, null);
					}
				}
				// Overlay resources
				if (civ.getMap().getCell(i, j).hasResource()) {
					// Draw trees
					if (civ.getMap().getCell(i, j).getResource()
							.equals(Resource.tree)) {
						if (playWater) { // for water, draw snow-covered trees
							g2.drawImage(
									snowTreeImg.getSubimage(0, 16, 16, 16),
									j * 16, i * 16, null);
						} else if (playEarth) { // for earth, draw less bushy
												// trees
							g2.drawImage(
									bareTreeImg.getSubimage(0, 16, 16, 16),
									j * 16, i * 16, null);
						} else { // for fire and air, draw bushy trees
							g2.drawImage(treeImg.getSubimage(0, 16, 16, 16),
									j * 16, i * 16, null);
						}
					}
					// Draw stones
					else if (civ.getMap().getCell(i, j).getResource()
							.equals(Resource.stone)) {
						if (playWater) { // for water, draw snow-covered stones
							g2.drawImage(snowStoneImg, j * 16, i * 16, null);
						} else if (playEarth) { // for earth, draw darker stones
							g2.drawImage(earthStoneImg, j * 16, i * 16, null);
						} else { // for fire and air, draw lighter stones
							g2.drawImage(stoneImg, j * 16, i * 16, null);
						}
					}
				}
				// Draw tops of trees
				if (i + 1 < map.getRows()
						&& civ.getMap().getCell(i+1, j).hasResource()) {
					if (civ.getMap().getCell(i+1, j).getResource()
							.equals(Resource.tree)) {
						if (playWater) { // for water, draw snow-covered trees
							g2.drawImage(snowTreeImg.getSubimage(0, 0, 16, 16),
									j * 16, i * 16, null);
						} else if (playEarth) { // for earth, draw less bushy
												// trees
							g2.drawImage(bareTreeImg.getSubimage(0, 0, 16, 16),
									j * 16, i * 16, null);
						} else { // for fire and air, draw bushy trees
							g2.drawImage(treeImg.getSubimage(0, 0, 16, 16),
									j * 16, i * 16, null);
						}
					}
				}
				for (int k = 0; k < civ.getUnits().size(); k++) {
					int location = civ.getUnits().get(k).getLocation();
					int row = location/map.getCols();
					int col = location%map.getCols();
					if (playWater) {
						g2.drawImage(waterDude1, col * 16, row * 16, null);
					} else if (playFire) {
						g2.drawImage(fireDude1, col * 16, row * 16, null);		
					} else if (playEarth) {
						g2.drawImage(earthDude1, col * 16, row * 16, null);
					} else {
						g2.drawImage(airDude1, col * 16, row * 16, null);
					}
				}
			}
		}
	}

}