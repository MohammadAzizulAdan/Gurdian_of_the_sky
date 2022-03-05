import java.awt.Color;
import java.awt.Graphics;
public class Fire {
    private int x;
    private int y;
    private int speed;
    public Fire(int x , int y){
        this.x=x;
        this.y=y;
        speed = 10;
    }
    public void tick(){
        y = y - speed;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void render(Graphics g){
        g.drawImage(Image.fire,x,y,12,16,null);
    }
}
