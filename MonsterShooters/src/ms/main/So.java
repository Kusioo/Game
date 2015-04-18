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
	
	
	TsP tsp = new TsP();  //importujemy metodê która ³aduje tekstury PNG
	private Texture logo; 
	static int a = 1280; //szerokoœæ ekranu
	static int b = 720; // wysokoœæ ekranu
	static double time = 0;
	Timer timer;
	private static enum State{ //Ró¿ne enumy naszej gry, np: G³ówne menu, sama gra, itd.
		INTRO, MAIN_MENU, GAME;
	}
	private State state = State.INTRO; 
	public So() { 
		
		//Tworzymy display, wystarczy ¿e bêdzie tutaj, nie mo¿e byæ nigdzie indziej bo scrashuje nam program
		try {
			Display.setDisplayMode(new DisplayMode(a, b));
			Display.setTitle("MonsterShooter");
			Display.create();
		} catch(LWJGLException e) {
			e.printStackTrace();	
			Display.destroy();
			System.exit(1);
			
			}
		
		//wpisujemy nazwê tekstury któr¹ ma wczytaæ program
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
		
		
		//g³ówna pêtla, tutaj siê wszystko dzieje :)
		while (!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT); //czyœci piksele!
			
			render(); //to metoda która jest zinicjonowana poni¿ej
			checkInput();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		
}
	private void render(){
		switch(state){
		
		//To siê wyœwietla pierwsze, potem wyœwietla siê nam kolejny Enum
		
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
	


