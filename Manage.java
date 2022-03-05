import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
public class Manage {
    private Player player;
    public static ArrayList<Fire> fire;
    private ArrayList<Enemy> enemy;
    private long current;
    private long delay;
    private int health;
    private int score=0;
    public Manage(){
    }
    public void init(){
        player = new Player((Setup.gameWidth/2)+20,(Setup.gameHeight-35)+50);
        player.init();
        fire= new ArrayList<Fire>();
        enemy=new  ArrayList<Enemy>();
        current= System.nanoTime();
        delay = 1000;
        health = player.getHealth();
    }
    public void tick(){
        player.tick();
        for(int i=0 ;i<fire.size();i++){
            fire.get(i).tick();
            
        }
        long breaks = (System.nanoTime() - current)/1000000;
        if(breaks >delay){
            for (int i=0;i<1;i++){
                Random rand = new Random();
                int randx = rand.nextInt(400);
                int randy = rand.nextInt(450);
                if(health>0){
                    enemy.add(new Enemy(randx,-randy));
                }
            }
            current = System.nanoTime();
        }
        for(int i=0;i<enemy.size();i++){
            enemy.get(i).tick();
        }
    }
    public void render(Graphics g){
        player.render(g);        
        g.setColor(Color.orange);
        g.setFont(new Font("arial",Font.BOLD,30));
        g.drawString("Score : "+score, 80, 500);
        if(health == 0) {
        	g.setColor(Color.orange);
        	g.drawString("Game Over!", 160, 230);
        	g.drawString("Your Score: "+score,145,300);
        	
        }
        for(int i=0;i<fire.size();i++){
            fire.get(i).render(g);
        }
        
        for (int i = 0;i< fire.size();i++){
             if(fire.get(i).getY()<=55){
                 fire.remove(i);
                 i--;
             }
        }
        for(int i=0;i<enemy.size();i++){
            if(!(enemy.get(i).getX()<=50 || enemy.get(i).getX()>=450-60||enemy.get(i).getY()>=450-60)){
                if(enemy.get(i).getY()>=50){
                    enemy.get(i).render(g);
                }
            }
        }
        for(int i=0;i<enemy.size();i++){
            int ex = enemy.get(i).getX();
            int ey = enemy.get(i).getY();
            int px = player.getX();
            int py = player.getY(); 
            if(px < ex +60 && px + 72> ex && py < ey +30 && py +35 > ey){
                enemy.remove(i);
                i--;
                health--;
                System.out.println(health);
                if(health <=0){
                    enemy.removeAll(enemy);
                    player.setHealth(0);
                }
            }
            for(int j=0;j<fire.size();j++){
                int fx = fire.get(j).getX();
                int fy = fire.get(j).getY();
                
                if(ex < fx + 12 && ex + 60 >fx && ey < fy + 12 && ey + 30 >fy ){
                    enemy.remove(i);
                    i--;
                    fire.remove(j);
                    j--;
                    score = score +1;
                }
            }
        }
        
    }
} 

