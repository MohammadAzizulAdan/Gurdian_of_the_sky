import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Setup implements Runnable{
    private String title;
    private int height;
    private int width;
    private Thread thread;
    private boolean running;
    private BufferStrategy buffer;
    private Graphics g;
    private int y;
    private boolean start;
    public static final  int gameWidth=400;
    public static final  int gameHeight=400;
    
    private Manage manage;
    private FrameDisplay display;
 
    
    
    public Setup(){
        
    }
    public Setup(String title,int height,int width){
        this.title= title;
        this.height=height;
        this.width=width;
    }
    public void init(){
        display = new FrameDisplay(title,height,width);
        Image.init();
        manage = new Manage();
        manage.init();
        start=true;
       
    }
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        if(thread == null){
            Thread thread = new Thread (this);
            thread.start();
        }
    }
    public synchronized void  stop(){
        if(!(running)){
            return;
        }
        running  = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            
        }  
    }
   public void tick(){
        manage.tick();
    }
    public void render(){
        buffer= display.getCanvas().getBufferStrategy();
        if(buffer == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        
        g.drawImage(Image.image,50,50,gameWidth,gameHeight, null);
        
        manage.render(g);
        buffer.show();
        g.dispose();
    }
    public void run() {
        init();
        int fps = 50;
        double timePerTick = 1000000000/fps;
        double delta =0;
        long current = System.nanoTime();
        
        while(running){
            delta = delta + (System.nanoTime()- current)/timePerTick;
            current = System.nanoTime();
            if(delta>=1){
                tick();
                render();
                delta --;
            }
        }
        
    }
    
}
