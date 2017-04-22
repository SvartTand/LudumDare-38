package com.svarttand.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.misc.Animation;
import com.svarttand.game.misc.Textures;

public class Invader implements Disposable{
	
	private float speed;
	
	private Vector2 velocity;
	private Vector2 position;

	
	private int dmg;
	private int hitpoints;
	private Circle bounds;
	
	//true = wlaking right, false = walking left
	private boolean direction;
	
	private Animation animation;
	private String name;
	
	
	public Invader(boolean direction, int posX, int posY, Textures textures, int type){
		if (type == Constants.NORMAL_TYPE) {
			name = "SnailMob";
			dmg = 20;
			speed = 0.5f;
			hitpoints = 20;
		}else if (type == Constants.GIANT) {
			name = "SnailMob";
			dmg = 10;
			speed = 0.2f;
			hitpoints = 200;
		}else{
			name = "SnailMob";
			dmg = 20;
			speed = 0.5f;
			hitpoints = 20;
		}
		
		
		this.direction = direction;
		if (direction) {
			velocity = new Vector2(speed,0);
			name = name + "R";
		}else{
			velocity = new Vector2(-speed, 0);
		}
		animation = new Animation(name, 4, 0.5f, textures, 1);
		position = new Vector2(posX,posY);
		bounds = new Circle(position, textures.getTextureRegion(name + 1).getRegionWidth()*0.5f);
		
	}
	
	public void update(float delta, World world){
		animation.update(delta);
		if (direction) {
			if (velocity.x >= speed) {
				velocity.x = speed;
			}else{
				velocity.add(speed*delta,0);
			}
			
		}else{
			if (velocity.x <= -speed) {
				velocity.x = -speed;
			}else{
				velocity.add(-speed*delta,0);
			}
		}
		if (position.y <= Application.V_HEIGHT/4) {
			position.y = Application.V_HEIGHT/4;
			
		}else{
			velocity.y += -Constants.GRAVITY*delta;
		}
		
		if (position.x <= Application.V_WIDTH*0.5&& !direction) {
			direction = true;
			if (position.y <= Application.V_HEIGHT/4) {
				world.takeDmg(dmg);
			}
		}else if (direction && position.x >= Application.V_WIDTH*0.5) {
			direction = false;
			if (position.y <= Application.V_HEIGHT/4) {
				world.takeDmg(dmg);
			}
		}
		position.add(velocity);
		
		bounds.setPosition(position);
		
	}
	
	public void hit(int dmg, float forceX, float forceY){
		velocity.set(forceX, forceY);
		hitpoints -= dmg;
	}
	
	public TextureRegion getTexture(){
		if (position.y > 100) {
			return animation.getFrame(4);
		}else{
			return animation.getFrame();
		}
		
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
		
		
	}
	
	

}
