package ms.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

//Klasa s³u¿aca do ³adowania tekstur, nic wiêcej nie robi, przyda siê :)

public class TsP {
	public static Texture loadTexture(String key){
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+key+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Ta klasa ³aduje pliki JPEG, a poprzednia ³aduje pliki PNG
	public class TsJ {
		public Texture loadTexture(String key){
			try {
				return TextureLoader.getTexture("JPEG", new FileInputStream(new File("res/"+key+".jpeg")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
	}			
}
