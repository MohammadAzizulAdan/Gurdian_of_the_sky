import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
public class FrameDisplay {
    private String title;
    private int height;
    private int width;
    public static  JFrame frame;
    public static  Canvas canvas;
    public FrameDisplay(){
    }
    public FrameDisplay(String title,int height ,int width){
        this.title=title;
        this.height=height;
        this.width=width;
        createFrame();
    }
    public void createFrame(){
        frame = new JFrame(title);
        frame.setSize(height,width);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas();
        frame.setResizable(false);
        canvas.setPreferredSize(new Dimension(height ,width));
        canvas.setBackground(Color.GRAY);
        frame.add(canvas);
        frame.pack();
    }
    public Canvas getCanvas() {
    	return canvas;
    }
    
     
}