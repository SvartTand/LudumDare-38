package com.svarttand.game.sprites;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.svarttand.game.constants.Constants;

import javafx.scene.shape.Circle;

public class Weapon implements Disposable{
	
	private Texture texture;
	private Vector2 position;
	private int dmg;
	private Circle blast;
	
	private boolean released;
	private float detonationTime;
	
	private float velocity;
	private ArrayList<Weapon> list;
	
	public Weapon(ArrayList<Weapon> list){
		texture = new Texture("BombPlaceholder.png");
		position = new Vector2();
		blast = new Circle(10);
		released = false;
		detonationTime = 3;
		this.list = list;
	}
	
	public void update(float mousePositionX, float mousePositionY, float delta){
		
		if (released) {
			velocity += Constants.GRAVITY * delta;
			if (position.y <= 100) {
				position.y = 100; 
			}else{
				position.add(0, 0 - velocity);
			}
			detonationTime -= delta;
			if (detonationTime <= 0) {
				detonate();
			}
			
		}else{
			position.x = (float) (mousePositionX - texture.getWidth()* 0.5);
			position.y = (float) (mousePositionY - texture.getHeight()* 0.5);
		}
		
	}
	public void release(){
		released = true;
	}
	
	public void detonate(){
		System.out.println("BOOOM");
		dispose();
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
	@Override
	public void dispose() {
		list.remove(this);
		texture.dispose();
		
	}

}
