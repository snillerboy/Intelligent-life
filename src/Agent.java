
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Agent extends GameObject implements Cloneable{
    
    Brain brain = new Brain(10,10,10,2, true);
    float health = 100;
    Color color = new Color(0,0,0);
    
    int foodsToRight = 0;
    int foodsToLeft = 0;
    int foodsUp = 0;
    int foodsDown = 0;
    int agentsRight = 0;
    int agentsLeft = 0;
    int agentsUp = 0;
    int agentsDown = 0;
    public Agent(float x2, float y2, float width2, float height2,ID id,Color color) {
        super(x2,y2,width2,height2, id);
      
        this.color = color;
        IntelligentLife.handler.agents++;

    }

    
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int)x, (int)y, (int)width, (int)height);
        g.setColor(Color.black);
        g.drawOval((int)x, (int)y, (int)width, (int)height);
        g.setColor(Color.green);
        g.fillRect((int)x+4, (int)y-12, (int)health/4, 7);
        rect = new Rectangle((int)x,(int)y, (int)width, (int)height);
    }

    public void tick() {
        
        if(health <= 0) {
        
            IntelligentLife.handler.agents--;
            IntelligentLife.handler.removeObject(this);
            
        }
        brain.updateNeurons();
        
        //Show the neurons(algebraic)
        LinkedList<LinkedList<Integer>> list = new  LinkedList<LinkedList<Integer>>();
        for(int i = 0; i < brain.neuronAmmount; i++) {
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            list2.add(brain.neurons.get(i).mode);
            list2.add(brain.neurons.get(i).input1);
            list2.add(brain.neurons.get(i).input2);
            list2.add(brain.neurons.get(i).value);

            list.add(list2);
           // System.out.println(brain.neurons.get(i).value);
        }
         //System.out.println(list);
         //brain.mutate(10);
        
       float velX = (float)brain.neurons.get(brain.neuronAmmount -1).value / 50;
       float velY = (float)brain.neurons.get(brain.neuronAmmount -2).value / 50;
        x += velX;
       y += velY;
       //System.out.println(velX);
       //System.out.println(velY);

      
      brain.neurons.get(0).value = (int) health;
       
        x %= 1000;
        y %= 700;
        
        if(x < 0)
            x = 1000;
        if(y<0)
            y = 700;
        
        health -= 0.1;
         
        int foodInArea = 0;
        int foodInArea2 = 0;
        int agentsInArea = 0;
        int agentsInArea2 = 0;
        for(int i = 0; i<IntelligentLife.handler.GameObjects.size();i++) {
        
            GameObject tempObject = IntelligentLife.handler.GameObjects.get(i);
            if(rect != null && tempObject.rect != null) {
            if(rect.intersects(tempObject.rect)) {
            
                if(tempObject.id == ID.Food) {
                    Food food = (Food) tempObject;
                    health += 0.4;
                    food.health++;
                
                }
            }
                
            
            }
            
            
            if(health > 200) {
            
                health = 100;
               // System.out.println(this.brain.neurons.get(13).input1);
                Agent agent;
                    Random rand = new Random();
                    if(color.getRed() < 250 && color.getGreen() < 250 && color.getBlue() < 250 && color.getRed() > 5 && color.getGreen() > 5 && color.getBlue() > 5) {
                        agent = new Agent(x + 10,y + 10,width,height,ID.Agent, new Color(color.getRed() + rand.nextInt(10) - 5,color.getGreen()+ rand.nextInt(10) - 5, color.getBlue()+ rand.nextInt(10) - 5));
                    } else {
                    
                         agent = new Agent(x + 10,y + 10,width,height,ID.Agent, color);

                        
                    }
                    agent.brain = brain.cloneBrain(brain);
                    
                    IntelligentLife.handler.addObject(agent);
               
            }
            
           if(tempObject.id == ID.Food) {
               if(tempObject.x -this.x > 0) {
               
                   foodsToRight++;
               
               } else {
               
                   foodsToLeft++;
               
               }
           
               if(tempObject.y -this.y > 0) {
               
                    foodsDown++;
               
               } else {
               
                   foodsUp++;
               
               }
               
               if(tempObject.x - this.x <200 && tempObject.x - this.x > -200 && tempObject.y -this.y <50 && tempObject.y -this.y > -50) {
               
                   brain.neurons.get(1).value = ((int)tempObject.x - (int)this.x)/2;
                   foodInArea++;
                   
               }
                if(tempObject.x - this.x <50 && tempObject.x - this.x > -50 && tempObject.y -this.y <200 && tempObject.y -this.y > -200) {
               
                   brain.neurons.get(2).value = ((int)tempObject.y - (int)this.y)/2;
                   foodInArea2++;
                   
               }
               if(foodInArea == 0) {
                   brain.neurons.get(1).value = 0;
               }
               if(foodInArea2 == 0) {
               
                   brain.neurons.get(2).value = 0;
               }
           
           }
           
            
          if(tempObject.id == ID.Agent && tempObject != this) {
              
                  if(tempObject.x -this.x > 0) {
               
                   agentsRight++;
               
               } else {
               
                   agentsLeft++;
               
               }
           
               if(tempObject.y -this.y > 0) {
               
                   agentsDown++;
               
               } else {
               
                   agentsUp++;
               
               }
           
               if(tempObject.x - this.x <200 && tempObject.x - this.x > -200 && tempObject.y -this.y <50 && tempObject.y -this.y > -50) {
               
                   brain.neurons.get(3).value = ((int)tempObject.x - (int)this.x)/2;
                   agentsInArea++;
                   
               }
                 if(tempObject.x - this.x <50 && tempObject.x - this.x > -50 && tempObject.y -this.y <200 && tempObject.y -this.y > -200) {
               
                   brain.neurons.get(4).value = ((int)tempObject.y - (int)this.y)/2;
                   agentsInArea2++;
                   
               }
                  if(agentsInArea == 0) {
                   brain.neurons.get(3).value = 0;
               }
               if(agentsInArea2 == 0) {
               
                   brain.neurons.get(4).value = 0;
               }
           
           }  
        }
        
        if(foodsToLeft > foodsToRight) {
        
            brain.neurons.get(5).value = -100;
            foodsToLeft = 0;
            foodsToRight = 0;
        } else {
        
             brain.neurons.get(5).value = 100;
             foodsToLeft = 0;
             foodsToRight = 0;
        }
        
          
        if(foodsUp > foodsDown) {
        
            brain.neurons.get(6).value = -100;
            foodsUp = 0;
            foodsDown = 0;
        } else {
        
             brain.neurons.get(6).value = 100;
             foodsUp = 0;
             foodsDown = 0;
        }
        
        
        
        if(agentsLeft > agentsRight) {
        
            brain.neurons.get(7).value = -100;
            agentsRight = 0;
            agentsLeft = 0;
        } else {
            
             brain.neurons.get(7).value = 100;
             agentsRight = 0;
            agentsLeft = 0;
        }
        
          
        if(agentsUp > agentsDown) {
        
            brain.neurons.get(8).value = -100;
             agentsRight = 0;
            agentsLeft = 0;
        } else {
        
             brain.neurons.get(8).value = 100;
            agentsRight = 0;
            agentsLeft = 0;
        }
    }
    

         @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
