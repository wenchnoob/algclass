package SeamCarving;

/**Driver for your code**/


public class SeamDemo {

	public static void main(String[] args) {

		int delay = 1;
		if(args.length > 1){
			//via commandline pass it a path to an image file
			CSBSJUImage im = new CSBSJUImage(args[0] + "/" + args[1]);
			int width = im.getWidth();
			int height = im.getHeight();
			int divisor = 3;//this can be altered - this will reduce the width and height by a third
			im.openNewDisplayWindow();	

			Seam s = new Seam();
			//watch it shrink vertically!
			for (int i = 0; i < width/divisor; i++) {// this is 
				s.verticalSeamShrink(im);
				im.repaintCurrentDisplayWindow();	
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			}
			//now horizontally!
			for (int i = 0; i < height/divisor; i++) {
				s.horizontalSeamShrink(im);
			
				im.repaintCurrentDisplayWindow();	
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			//save your altered image
			String newFilename = args[0] + "/altered/" + args[1];
			im.writePNG(newFilename);
	
		}
		else{
			System.out.println("path to an image is required from commandline");
			System.out.println("SeamDemo <path to image file>");
		}
	
	}
}
