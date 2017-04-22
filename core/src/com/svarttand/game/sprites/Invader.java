package com.svarttand.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;

public class Invader implements Disposable{
	
	private static final float SPEED = 0.5f;
	
	private Vector2 velocity;
	private Vector2 position;
	
	private Texture texture;
	
	private int hitpoints;
	private Circle bounds;
	
	//true = wlaking right, false = walking left
	private boolean direction;
	
	
	public Invader(boolean direction, int posX, int posY){
		texture = new Texture("MobPlaceholder.png");
		this.direction = direction;
		if (direction) {
			velocity = new Vector2(SPEED,0);
		}else{
			velocity = new Vector2(-SPEED, 0);
		}
		position = new Vector2(posX,posY);
		hitpoints = 20;
		bounds = new Circle(position, texture.getWidth()*0.5f);
		
	}
	
	public void update(float delta){
		if (direction) {
			if (velocity.x >= SPEED) {
				velocity.x = SPEED;
			}else{
				velocity.add(SPEED*delta,0);
			}
			
		}else{
			if (velocity.x <= -SPEED) {
				velocity.x = -SPEED;
			}else{
				velocity.add(-SPEED*delta,0);
			}
		}
		if (position.y <= 100) {
			position.y = 100;
			
		}else{
			velocity.y += -Constants.GRAVITY*delta;
		}
		
		if (position.x <= Application.V_WIDTH*0.5) {
			direction = true;
		}else{
			direction = false;
		}
		position.add(velocity);
		
		bounds.setPosition(position);
		
	}
	
	public void hit(int dmg, float forceX, float forceY){
		velocity.set(forceX, forceY);
		hitpoints -= dmg;
		System.out.println(hitpoints + "/" + 20);
		System.out.println(forceX + ", " + forceY);
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Circle getBounds(){
		return bounds;
	}
	
	public int getHitpoints() {
		return hitpoints;
	}

	@Override
	public void dispose() {
		texture.dispose();
		
	}
	
	

}
