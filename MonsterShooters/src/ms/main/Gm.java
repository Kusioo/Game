package ms.main;

import java.util.ArrayList;
import java.util.LinkedList;

import ms.entity.AbstractMoveableEntity;
import ms.entity.bDr;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
public class Gm {
	
	//To ma byæ lista pocisków
	/** Trzeba stworzyæ metodê, która bêdzie dodawa³a pociski do list **/
	/** done **/
	//Mo¿emy stworzyæ tak du¿o pocisków jak chcemy, ale nie mo¿emy nimi poruszaæ :I
	/** YES, SHOOTING WORKING FINE, 10/10 FOR ME **/
	
	
    public static LinkedList<Bullet> bullets = new LinkedList<Bullet>();   
    
    //boolean i inty do strzelania
    static boolean fR = false;
    static boolean bH = true;
    static int bM = -1;
    
    //teksturki
	private static Texture plr1;
	private static Texture plr2;
	private static Texture plr3;
	private static Texture ch1;
	private static Texture kappa;
	//U¿ywa³em tych intów wczeœniej, w obecnej chwili s¹ chyba niepotrzebne.
	static int zombieR = 1;
	static int PlayerR = 1;
	static int BulletR = 1;
	static int zombieZ = 2;
	static int PlayerZ = 2;
	
	//A to jest potrzebne do oznaczenia bulleta podczas jego renderowania
   
    
    private static long lastFrame;

    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private static int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
    
    
    
    private static class Player extends AbstractMoveableEntity {

    public Player(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void draw() {
        	//tutaj bierzemy tekstury
        	plr1 = TsP.loadTexture("plr1");
        	plr2 = TsP.loadTexture("plr2");
        	plr3 = TsP.loadTexture("plr3");
        	ch1 = TsP.loadTexture("ch1");
        	kappa = TsP.loadTexture("kappa");
       
        	ch1.bind();
        	glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y);
			glTexCoord2f(0, 1);
        	glVertex2d(x, y + width);
			glTexCoord2f(1, 1);
        	glVertex2d(x + width, y + width);
			glTexCoord2f(1, 0);
        	glVertex2d(x + width, y);
        	glEnd();
        	       	        	
        }

	
    }
    
    
    //To ma byæ pocisk, który nie ³adzia :I
    public static class Bullet extends AbstractMoveableEntity {


    public Bullet(double x, double y, double width, double height) {
			super(x, y, width, height);
		}

	@Override
	public  void draw() {
		

		kappa.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
        glVertex2d(x, y);
        glTexCoord2f(0, 1);
        glVertex2d(x, y + width);
        glTexCoord2f(1, 1);
        glVertex2d(x + width, y + width);
        glTexCoord2f(1, 0);
        glVertex2d(x + width, y);
        glEnd();
		
	}
    }

    //To jest ta ¿aba która nas goni :I
	private static class Zombie extends AbstractMoveableEntity {

        public Zombie(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void draw() {
        	

        	if(Keyboard.isKeyDown(Keyboard.KEY_A)){
        		plr2.bind();
        	}
            	else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            		plr3.bind();
            	}
            	else if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            		plr2.bind();
            	}
            		
            	else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            		plr3.bind();
                    	
                    	}
                	
				
        	else{
        		plr1.bind();
        	}
        	glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
        	glVertex2d(x, y);
			glTexCoord2f(0, 1);
        	glVertex2d(x, y + width);
			glTexCoord2f(1, 1);
        	glVertex2d(x + width, y + width);
			glTexCoord2f(1, 0);
        	glVertex2d(x + width, y);
        	glEnd();
        	       	        	
        }

    }
	  //Ta metoda dodaje Bullety do listy, ale najpierw trzeba te bullety stworzyæ
	  public static void aBaL(double pX, double pY){
		  Bullet bult = new Bullet(pX, pY, 10, 10); //nie wa¿ne jakie liczby ustawimy, i tak siê spawni tak jak jest to zapisane w klasie Bullet w metodzie draw
		  bullets.add(bult);
		  bM++;/** ju¿ widzê b³¹d, w zombie i player by³y x i y, a ja wpisa³em liczby, przez co zmiana pozycji bulletow by³a niemo¿liwa **/
	  }

	  
	  

    public static void Ekraniki(){
        
    

    	//Tutaj jest tworzone Entity, mo¿e by to wykorszytsaæ do robienia bulletów? 
        Player Player = new Player(100, 100, 50, 50);
        Zombie Zombie = new Zombie(10, 10, 40, 40);
       

       //Ustawinia
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, So.a, So.b, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
    	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    	 glClearColor(0,0,0,1);
         glDisable(GL_DEPTH_TEST);
        Keyboard.enableRepeatEvents(false);

        lastFrame = getTime();
        
        //Tutaj jest to co siê w grze dzieje
        while (!Display.isCloseRequested()) {
        	  glClear(GL_COLOR_BUFFER_BIT);
              int delta = getDelta();
              
              
              
              
             //usuwanie pocisków które s¹ poza ekranem 
              //for (int i = 0; i < bullets.size(); ++i) {
            	  //if (bullets.get(i).getX()>So.a) {
            	    	//bullets.remove(i);
            	  //  }
            	  //  else if(bullets.get(i).getX()<0){
            	 //   	 bullets.remove(i);
            	 //   }
            	//    if(bullets.get(i).getY()>So.b){
          	//  	     bullets.remove(i);
    			//    }
            	//    else if(bullets.get(i).getY()<0){
            	//    	  bullets.remove(i);
            	//    }
            	//    }
            	//Tutaj jest kilka problemów, potem je naprawimy    
            	    
            	
              
              //draw bullety, ale nie  wiadomo jak nimi poruszaæ :I //ju¿ wiadomo :D
              for (int i = 0; i < bullets.size(); i++) {
            	  if(bH == true){
            		  bullets.get(i).draw();
            		  bullets.get(i).update(delta);
            	  }
            	  
            	  new bDr(bM, 0.4);
            
                   
            	  //Usuwanie bulletów, teraz do testów, i dzia³a, potem trochê to zmienimy  
            	  if(Keyboard.isKeyDown(Keyboard.KEY_C)){
            		  bullets.remove(i);
            		  i--;
            	  }
            	  
              }
        	  
        //¯aba porusza siê za nami
           
             if (Player.getX()>Zombie.getX()) {
            	Zombie.setDX(0.2);
            }
            else if(Player.getX()<Zombie.getX()){
            	Zombie.setDX(-0.2);
            }
            if(Player.getY()>Zombie.getY()){
            	Zombie.setDY(0.2);
            }
            else if(Player.getY()<Zombie.getY()){
            	Zombie.setDY(-0.2);
            }
            
            
            
           //Ruch postaci¹
        	    if(Keyboard.isKeyDown(Keyboard.KEY_A)){
              	Player.setDX(-1);
              }
              else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
              	Player.setDX(1);
              }
              else{
              	Player.setDX(0);
              }
              if(Keyboard.isKeyDown(Keyboard.KEY_W)){
              	Player.setDY(-1);
              }
              else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
              	Player.setDY(1);
              }
              else{
              	Player.setDY(0);
              }
           
            //Jeœli zombie dotknie naszej postaci to gra siê wy³¹cza, potem zrobimy ¿e bêdzie odejmowa³o hp
            if(Player.intersects(Zombie)){
            	System.exit(1);
            }
            
            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            	fR = true;
            }
            else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            	fR = true;
            }
            else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            	fR = true;
            }
            else if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            	fR = true;
            }
            else{
            	fR = false;            
            	}
            if(fR == true){
              	aBaL(Player.getX(), Player.getY());
              }
         
          
     
           
           

            System.out.println(bullets.size());
            
            Player.draw();
            Player.update(delta);
            Zombie.draw();
            Zombie.update(delta);
            
            
            
       
            Display.update();
            Display.sync(60);
            
           
        }
        Display.destroy();
        System.exit(0);
    }
}



//Mamy listê która przechowywuje bullety,

    