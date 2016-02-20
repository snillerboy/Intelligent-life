
import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {

    LinkedList<GameObject> GameObjects = new LinkedList<GameObject>();
    public int agents = 0;
    
    public void render(Graphics g) {
    
        for(int i = 0; i < GameObjects.size(); i++) {
        
            GameObject tempObject = GameObjects.get(i);
            tempObject.render(g);
        
        } 
    
    }
    
    public void tick() {
    
          for(int i = 0; i < GameObjects.size(); i++) {
        
            GameObject tempObject = GameObjects.get(i);
            tempObject.tick();
        
        } 
    
    }
    
    public void addObject(GameObject gameObject) {
    
        GameObjects.add(gameObject);
    
    }
    
    public void removeObject(GameObject gameObject) {
    
        GameObjects.remove(gameObject);
        
    }

}
