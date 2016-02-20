
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected float x;
    protected float y;
    protected float height;
    protected float width;
    protected ID id;
    protected Rectangle rect;
    
    public GameObject(float x2, float y2, float width2, float height2, ID id) {
    
        this.x = x2;
        this.y = y2;
        this.width = width2;
        this.height = height2;
        this.id = id;
    
    }
    
    public abstract void render(Graphics g);
    public abstract void tick();
    
}
