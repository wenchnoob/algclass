package SeamCarving;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.*;


public class CSBSJUImage {
	private BufferedImage im;
	private ImagePanel theDisplay;
	
	

	// Make an image from the given filename
	public CSBSJUImage(String filename) {
		File f = new File(filename);

		try {
			this.im = ImageIO.read(f);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	* Make a blank image given the size
	* @param x pixel width of new image
	* @param y pixel height of new image
	*/
	public CSBSJUImage(int x, int y) {
		this.im = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);

		// Create a graphics context for the new BufferedImage
		Graphics2D g2 = this.im.createGraphics();

		// Fill in the image with black
		g2.setColor(Color.black);
		g2.fillRect(0, 0, x, y);
	}
	/**@return width in pixels*/
	public int getWidth() {
		return im.getWidth();
	}
	/**@return height in pixels*/
	public int getHeight() {
		return im.getHeight();
	}
	/**
	 * @param x x-coordinate of pixel in image
	 * @param y y-coordinate of pixel in image
	 * @return the integer red value of pixel at given x and y coordinate
	 */
	public int getRed(int x, int y) {
		int value = 0;

		if ((x >= 0) && (y >= 0) && (x < im.getWidth()) && (y < im.getHeight())) {
			value = ((im.getRGB(x, y) >> 16) & 255);
		}

		return value;
	}
	/**
	 * @param x x-coordinate of pixel in image
	 * @param y y-coordinate of pixel in image
	 * @return the integer green value of pixel at given x and y coordinate
	 */
	public int getGreen(int x, int y) {
		int value = 0;

		if ((x >= 0) && (y >= 0) && (x < im.getWidth()) && (y < im.getHeight())) {
			value = ((im.getRGB(x, y) >> 8) & 255);
		}

		return value;
	}
	/**
	 * @param x x-coordinate of pixel in image
	 * @param y y-coordinate of pixel in image
	 * @return the integer blue value of pixel at given x and y coordinate
	 */
	public int getBlue(int x, int y) {
		int value = 0;

		if ((x >= 0) && (y >= 0) && (x < im.getWidth()) && (y < im.getHeight())) {
			value = ((im.getRGB(x, y)) & 255);
		}

		return value;
	}
	/**
	* @param x x-coordinate of pixel to set
	* @param y y-coordinate of pixel to set
	* @param red new red value for pixel
	* @param green new green value for pixel
	* @param blue new blue value for pixel
	*/
	public void setRGB(int x, int y, int red, int green, int blue) {
		int pixel = (red << 16) + (green << 8) + (blue);

		im.setRGB(x, y, pixel);
	}
	/** draw the given image */
	public void draw(Graphics g) {
		g.drawImage(im, 0, 0, null);
	}
	/**
	 * write the image to the given file name
	 */
	public void write(String filename) {
		if (filename.endsWith(".png")) {
			writePNG(filename);
		} else {
			System.out.println("Filetype not supported");
		}
	}
	/**
	 * write the image to the given file name as png
	 */
	public void writePNG(String filename) {

		File f = new File(filename);
		try {
			// Write out the image to the file as a png
			ImageIO.write(this.im, "png", f);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	* This mutates the current CSBSJU image to be the newWidth and newHeight if you need to resize
	*/
	public void scaleImage(int newWidth, int newHeight) {

		// First create a new buffered image that we will fill
		// It doesn't need to set the values to black (0) since we are about to
		// fill them with actual info
		// We need a new one due to sampling issues
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

		double scaleX = (double) im.getWidth() / (double) newWidth;
		double scaleY = (double) im.getHeight() / (double) newHeight;

		for (int x = 0; x < newWidth; x++) {
			for (int y = 0; y < newHeight; y++) {
				// x, y - the coordinates of the pixel from which to get the RGB
				// color model and sRGB color space
				// This does a nearest neighbor interpolation
				// Rescales an image to the given width and height.
				// The aspect ratio is not maintained if width and height are
				// different from the original
				int pixelSample = im.getRGB((int) (x * scaleX), (int) (y * scaleY));
				newImage.setRGB(x, y, pixelSample);
			}
		}

		// Once we have the new buffered image, set it to the UWEC images's
		// buffered image
		im = newImage;
	}

	/**
	* transpose of the current image - mutates the image - flips image on side
	*/
	public void transpose() {
		
		BufferedImage newImage = new BufferedImage(im.getHeight(), im.getWidth(), BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < im.getWidth(); x++) {
			for (int y = 0; y < im.getHeight(); y++) {
				newImage.setRGB(y, x, im.getRGB(x, y));
			}
		}
		im = newImage;  // mutate the original
	}

	/**
	* To pop up a new window for this image
	*/
	public void openNewDisplayWindow() {
		this.theDisplay = new ImagePanel(this);
	}
	
	/**
	* To mutate the existing window to fit the image's current dimensions and pixels
	*/
	public void repaintCurrentDisplayWindow() {
		this.theDisplay.changeImage(this);
		this.theDisplay.repaint();		
	}
	/** 
	* reassigns image reference to given image
	*/
	public void switchImage(CSBSJUImage theNewImage) {
		this.im = theNewImage.im;
	}
	
	/**
	* Nested class for display purposes
	*/
	private class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		CSBSJUImage im;
		JFrame frame;

		public ImagePanel(CSBSJUImage im) {
			this.im = im;  // Reference to the image - not a copy
						   // So if you call repaint() on this class it will redraw the updated pixels

			this.setSize(im.getWidth(), im.getHeight());

			// Setup the frame that I belong in
			frame = new JFrame("Image Viewer");
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(1);
				}
			});
			frame.getContentPane().add(this);
			
			// Set a fake size for now to get it up - we will never see this show up in these dimensions
			frame.setSize(im.getWidth()+10, im.getHeight()+10);
			frame.setVisible(true);
			
			// Get the insets now that it is visible
			// The insets will be 0 until it is visible
			Insets fInsets = frame.getInsets();
			
			// Resize the image based on the visible insets
			frame.setSize(im.getWidth() + fInsets.left + fInsets.right, im.getHeight() + fInsets.top + fInsets.bottom);
		}
		
		/**
		* Resets the image being displayed and redoes the frame to fit it
		*/
		public void changeImage(CSBSJUImage im) {
			this.im = im;
			
			Insets fInsets = frame.getInsets();
			
			// Resize the image based on the visible insets
			frame.setSize(im.getWidth() + fInsets.left + fInsets.right, im.getHeight() + fInsets.top + fInsets.bottom);
			
			this.repaint();
		}
		/** draws the image
		*/
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			im.draw(g);
		}
	}
}