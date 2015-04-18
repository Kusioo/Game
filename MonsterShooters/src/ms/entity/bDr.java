package ms.entity;

import ms.main.Gm;

import org.lwjgl.input.Keyboard;

public class bDr {
	public bDr(int i, double speed){
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			Gm.bullets.get(i).setDY(-speed);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			Gm.bullets.get(i).setDY(speed);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			Gm.bullets.get(i).setDX(-speed);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			Gm.bullets.get(i).setDX(speed);
		}
		
	}

}
