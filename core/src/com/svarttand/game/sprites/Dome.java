package com.svarttand.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.svarttand.game.Application;

public class Dome {
	
	private static final int MAX_HP = 10000;
	
	private float hitPoints;
	private Texture texture;
	private Circle bounds;
	
	public Dome(){
		hitPoints = MAX_HP;
		texture = new Texture("DomePlaceholder.png");
		bounds = new Circle(Application.V_WIDTH*0.7f, Application.V_HEIGHT*0.7f, Application.V_WIDTH*0.7f);
		
	}
	public void update(float delta){
		
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public Circle getBounds(){
		return bounds;
	}
	
	public void takeDamage(float damage){
		hitPoints -= damage;
	}
	
	public float getHitPoints(){
		return hitPoints/MAX_HP;
	}
	public void dispose() {
		texture.dispose();
		
	}

}
