import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Player implements KeyListener {
    private int x;
    private int y;
    private boolean right,left,up,down;
    private boolean shot;
    private long current;
    private long delay;
    private int health;
    
    public Player(int x,int y){
        this.x= x;
        this.y=y;
    }
    public void init(){
        FrameDisplay.frame.addKeyListener(this);
        current = System.nanoTime();
        delay = 100;
        health = 3;
    }
    public void tick(){
        if(!(health<=0)){
        	if(up) {
        		y=y-4;
        	}
        	if(down) {
        		y=y+4;
        	}
            if(left){
                if(x>50){
                    x=x-5;
                }
            }
            if(right){
                if(x<=450-76){
                    x= x+5;
                }
            }
            if(shot){
                long breaks = (System.nanoTime()-current )/ 1000000;
                if(breaks > delay ){
                    Manage.fire.add(new Fire(x+30,y));
                }
                current = System.nanoTime();
            }
        }
    }
    public void render(Graphics g){
        if(!(health<=0)){
            g.drawImage(Image.player,x,y,72,35,null);
        }
    }
    public void keyPressed(KeyEvent e){
        int source = e.getKeyCode();
        if(source == KeyEvent.VK_LEFT){
            left = true;
        }
        if(source == KeyEvent.VK_RIGHT){
            right = true;
        }
        if(source == KeyEvent.VK_UP){
            up = true;
        }
        if(source == KeyEvent.VK_DOWN){
            down = true;
        }
        if(source == KeyEvent.VK_F){
            shot = true;
        }
        
    }
    public void keyReleased(KeyEvent e) {
        int source = e.getKeyCode();
        if(source == KeyEvent.VK_LEFT ){
            left = false;
        }
        if(source == KeyEvent.VK_RIGHT){
            right = false;
        }
        if(source == KeyEvent.VK_F){
            shot = false;
        }
        if(source == KeyEvent.VK_UP){
            up = false;
        }
        if(source == KeyEvent.VK_DOWN){
            down = false;
        }
    }
    public void keyTyped(KeyEvent e){
        
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health=health;
    }
}
