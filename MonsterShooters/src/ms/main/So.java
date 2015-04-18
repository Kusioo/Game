package ms.main;

import static org.lwjgl.opengl.GL11.*;

import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
public class So {
	
	
	TsP tsp = new TsP();  //importujemy metod� kt�ra �aduje tekstury PNG
	private Texture logo; 
	static int a = 1280; //szeroko�� ekranu
	static int b = 720; // wysoko�� ekranu
	static double time = 0;
	Timer timer;
	private static enum State{ //R�ne enumy naszej gry, np: G��wne menu, sama gra, itd.
		INTRO, MAIN_MENU, GAME;
	}
	private State state = State.INTRO; 
	public So() { 
		
		//Tworzymy display, wystarczy �e b�dzie tutaj, nie mo�e by� nigdzie indziej bo scrashuje nam program
		try {
			Display.setDisplayMode(new DisplayMode(a, b));
			Display.setTitle("MonsterShooter");
			Display.create();
		} catch(LWJGLException e) {
			e.printStackTrace();	
			Display.destroy();
			System.exit(1);
			
			}
		
		//wpisujemy nazw� tekstury kt�r� ma wczyta� program
		logo = TsP.loadTexture("logo");
			
		{

		}
		
		//Opcje ,,wizualne'' naszej gry
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 1280, 720, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		
		//g��wna p�tla, tutaj si� wszystko dzieje :)
		while (!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT); //czy�ci piksele!
			
			render(); //to metoda kt�ra jest zinicjonowana poni�ej
			checkInput();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		
}
	private void render(){
		switch(state){
		
		//To si� wy�wietla pierwsze, potem wy�wietla si� nam kolejny Enum
		
		case INTRO:
			logo.bind();
			glClear(GL_COLOR_BUFFER_BIT);
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(0, 0);
			glTexCoord2f(0, 1);
			glVertex2i(0, b);
			glTexCoord2f(1, 1);
			glVertex2i(a, b);
			glTexCoord2f(1, 0);
			glVertex2i(a, 0);
			glEnd();
		
			
		
		
			break;
		case GAME:
			Gm.Ekraniki();
		case MAIN_MENU:
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(a/2-200, b/8-35);
			glTexCoord2f(0, 1);
			glVertex2i(a/2-200, b/8+35);
			glTexCoord2f(1, 1);
			glVertex2i(a/2+200, b/8+35);
			glTexCoord2f(1, 0);
			glVertex2i(a/2+200, b/8-35);
			glEnd();
		
		}
	}
	private void checkInput(){
		switch(state){
		case INTRO:
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				state = state.GAME;
			}
			
			break;
		case GAME:
			break;
		case MAIN_MENU:
			break;
		}
		
		
	}


		

	}
	


