
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class IntelligentLife implements Runnable{
   
   final int WIDTH = 1000;
   final int HEIGHT = 700;
   
   JFrame frame;
   Canvas canvas;
   BufferStrategy bufferStrategy;
   public static Handler handler;
   
   public IntelligentLife(){
      frame = new JFrame("Intelligent Life");
      
      handler = new Handler();
      
      JPanel panel = (JPanel) frame.getContentPane();
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      panel.setLayout(null);
      
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
      
      panel.add(canvas);
      
      canvas.addMouseListener(new MouseControl());
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setResizable(false);
      frame.setVisible(true);
      
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      
      canvas.requestFocus();
      
      Random rand = new Random();
     // handler.addObject(new Food(181,170,32,32,ID.Food));
      for(int i = 0; i<20; i++) {
        handler.addObject(new Food(rand.nextInt(WIDTH),rand.nextInt(HEIGHT),32,32, ID.Food));
      }
        for(int i = 0; i<1; i++) {
            handler.addObject(new Agent(rand.nextInt(WIDTH),rand.nextInt(HEIGHT),32,32,ID.Agent,new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255))));
        }

   }
   
        
   private class MouseControl extends MouseAdapter{
      
   }
   
   long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
   boolean running = true;
   
   public void run(){
      
      long beginLoopTime;
      long endLoopTime;
      long currentUpdateTime = System.nanoTime();
      long lastUpdateTime;
      long deltaLoop;
      
      while(running){
         beginLoopTime = System.nanoTime();
         
         render();
         
         lastUpdateTime = currentUpdateTime;
         currentUpdateTime = System.nanoTime();
         update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
         
         endLoopTime = System.nanoTime();
         deltaLoop = endLoopTime - beginLoopTime;
           
           if(deltaLoop > desiredDeltaLoop){
               //Do nothing. We are already late.
           }else{
               try{
                   Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
               }catch(InterruptedException e){
                   //Do nothing
               }
           }
      }
   }
   
   private void render() {
      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
      g.clearRect(0, 0, WIDTH, HEIGHT);
      g.setColor(Color.white);
      g.fillRect(0,0,WIDTH + 100,HEIGHT + 100);
      render(g);
      g.dispose();
      bufferStrategy.show();
   }
   
   //TESTING
   private double x = 0;
   
   /**
    * Rewrite this method for your game
    */
   int time =0;
   
   protected void update(int deltaTime){
      handler.tick();
      time++;
   
      
      if(handler.agents < 11) {
      
          
        if(time > 60 * 0.2) {
          Random rand = new Random();
          handler.addObject(new Food(rand.nextInt(WIDTH),rand.nextInt(HEIGHT),32,32, ID.Food));
          
          time=0;
      
         }          
      } else    if(time > 60 * 0.6) {
          Random rand = new Random();
          handler.addObject(new Food(rand.nextInt(WIDTH),rand.nextInt(HEIGHT),32,32, ID.Food));
          
          time=0;
      
      }
      
   }
   
   /**
    * Rewrite this method for your game
    */
   protected void render(Graphics2D g){
      handler.render(g);
   }
   
   public static void main(String [] args){
      IntelligentLife ex = new IntelligentLife();
      new Thread(ex).start();
   }

}