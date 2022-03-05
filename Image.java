import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image{
	public static BufferedImage image;
	public static BufferedImage player,enemy,fire;
	
	public static void init() {
		image=imgaeLoader("/res/white.png");
		player=imgaeLoader("/res/plr.png");
		enemy=imgaeLoader("/res/enmy.png");
		fire=imgaeLoader("/res/fire.png");
	}
	
	public static BufferedImage imgaeLoader(String path) {
		try {
			return ImageIO.read(Image.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}