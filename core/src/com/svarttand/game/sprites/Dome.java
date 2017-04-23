package com.svarttand.game.sprites;

import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.svarttand.game.Application;

public class Dome {
	
	private static final int MAX_HP = 5000;
	
	private float hitPoints;
	private String texture;
	private Circle bounds;
	
	public Dome(){
		hitPoints = MAX_HP;
		texture = "Dome";
		bounds = new Circle(Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f, Application.V_WIDTH*0.4f);
		
	}
	public void update(float delta){
		
	}
	
	public String getTexture(){
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


}
