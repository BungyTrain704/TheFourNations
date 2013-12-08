package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Flyweight implmentation to handle game loading
 * @author Christopher
 *
 */
public class GameImageLoader {

	private static HashMap<String, BufferedImage> gameImages = new HashMap<>();
	
	/**
	 * Returns the image if it has already been loaded, otherwise it loads and stores it
	 * @param locationOnDisk The location on the disk that this image is located at.
	 * @return The BufferedImage if it is in the map or can be loaded. Will return null
	 * 			if the resource is unable to be loaded.
	 */
	public static BufferedImage getImage( String locationOnDisk ) {
		if( gameImages.containsKey( locationOnDisk) )
			return gameImages.get(locationOnDisk);
		else return loadImageAt( locationOnDisk );
	}
	
	private static BufferedImage loadImageAt( String locationOnDisk ) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read( new File( locationOnDisk ) );
			gameImages.put(locationOnDisk, image);
		}
		catch( IOException ioe ) {
			System.out.println( "Could not load image at: " + locationOnDisk );
			ioe.printStackTrace();
		}
		
		return image;
	}
}
