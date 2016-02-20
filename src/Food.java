import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends GameObject {

     float health = 0;

    
      public Food(float x2, float y2, float width2, float height2,ID id) {
        super(x2,y2,width2,height2, id);
        
    }
    
        
    public void render(Graphics g) {
   
        if(health < 255 && health > 0){
         g.setColor(new Color((int)health,(int)(health), (int)(health)));
        }else {
        
            g.setColor(Color.BLACK);
            
        }
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        rect = new Rectangle((int)x,(int)y, (int)width, (int)height);
        
    }

   
    public void tick() {

        if(health > 254) {
        
            IntelligentLife.handler.removeObject(this);
            
        }
        
        
    }
    
}
