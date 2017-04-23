package com.svarttand.game.sprites;

import com.badlogic.gdx.math.Circle;
import com.svarttand.game.Application;
import com.svarttand.game.misc.InvaderSpawner;

public class Dome {
	
	private static final int MAX_HP = 1500;
	
	private float hitPoints;
	private String texture;
	private Circle bounds;
	
	public Dome(){
		hitPoints = MAX_HP;
		texture = "Dome1";
		bounds = new Circle(Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f, Application.V_WIDTH*0.4f);
		
	}
	public void update(float delta, InvaderSpawner invaderSpawner){
		if (getHitPoints()<0.9) {
			texture = "Dome2";
			invaderSpawner.setFrequency(3.5f);
		}
		if (getHitPoints()<0.8) {
			texture = "Dome3";
			invaderSpawner.setFrequency(3f);
		}if (getHitPoints()<0.7) {
			texture = "Dome4";
			invaderSpawner.setFrequency(2.5f);
		}if (getHitPoints()<0.6) {
			texture = "Dome5";
			invaderSpawner.setFrequency(2f);
		}if (getHitPoints()<0.5) {
			texture = "Dome6";
			invaderSpawner.setFrequency(1.5f);
		}if (getHitPoints()<0.4) {
			texture = "Dome7";
			invaderSpawner.setFrequency(1.5f);
		}if (getHitPoints()<0.3) {
			texture = "Dome8";
			invaderSpawner.setFrequency(1f);
		}if (getHitPoints()<0.2) {
			texture = "Dome9";
			invaderSpawner.setFrequency(0.5f);
		}if (getHitPoints()<0.1) {
			texture = "Dome10";
			invaderSpawner.setFrequency(0.25f);
		}
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
