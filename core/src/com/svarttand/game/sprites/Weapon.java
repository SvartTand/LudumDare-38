package com.svarttand.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import javafx.scene.shape.Circle;

public class Weapon {
	
	private Texture texture;
	private Vector2 position;
	private int dmg;
	private Circle blast;
	
	public Weapon(){
		texture = new Texture("BombPlaceholder.png");
		position = new Vector2();
		blast = new Circle(10);
	}
	
	public void update(float mousePositionX, float mousePositionY){
		position.x = mousePositionX;
		position.y = mousePositionY;
	}
	public void release(){
		
	}
	
	public void detonate(){
		
	}
	
	public Circle getBlast(){
		return blast;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public void dispose(){
		texture.dispose();
	}
	

}
