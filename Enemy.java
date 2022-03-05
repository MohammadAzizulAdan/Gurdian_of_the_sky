import java.awt.Color;
import java.awt.Graphics;
public class Enemy {
    private int x;
    private int y;
    public  Enemy (int x,int y){
        this.x=x;
        this.y=y;
    }
    public void tick(){
        y=y+5;
    }
    public void render(Graphics g){
        g.drawImage(Image.enemy,x,y,60,30,null);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
}
