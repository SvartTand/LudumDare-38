package com.svarttand.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
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
	private int maxHP;
	private Circle bounds;
	
	//true = wlaking right, false = walking left
	private boolean direction;
	
	private Animation animation;
	private String name;
	
	private Rectangle HPBar;
	private float HPBarPositionY;
	private float HPBarLength;
	
	private boolean extras;
	
	private int type;
	
	private float ground;
	
	
	public Invader(boolean direction, int posX, int posY, Textures textures, int type){
		if (type == Constants.NORMAL_TYPE) {
			name = "SnailMob";
			dmg = 20;
			speed = 0.5f;
			maxHP = 15;
			hitpoints = maxHP;
			ground = Application.V_HEIGHT*0.25f;
			this.direction = direction;
			if (direction) {
				velocity = new Vector2(speed,0);
				name = name + "R";
			}else{
				velocity = new Vector2(-speed, 0);
			}
			animation = new Animation(name, 4, 0.5f, textures, 1);
			extras = true;
		}else if (type == Constants.GIANT) {
			name = "Slime";
			dmg = 10;
			speed = 0.2f;
			maxHP = 75;
			hitpoints = maxHP;
			ground = Application.V_HEIGHT*0.25f;
			this.direction = direction;
			if (direction) {
				velocity = new Vector2(speed,0);
				name = name + "R";
			}else{
				velocity = new Vector2(-speed, 0);
			}
			animation = new Animation(name, 4, 0.5f, textures);
			extras = false;
		}else if (type == Constants.FLYING) {
			name = "FlyingMonster";
			dmg = 5;
			speed = 0.7f;
			maxHP = 5;
			hitpoints = maxHP;
			ground = Application.V_HEIGHT*0.5f;
			this.direction = direction;
			if (direction) {
				velocity = new Vector2(speed,0);
				name = name + "R";
			}else{
				velocity = new Vector2(-speed, 0);
			}
			animation = new Animation(name, 4, 0.5f, textures);
			extras = false;
		}else{
			name = "SnailMob";
			dmg = 20;
			speed = 0.5f;
			maxHP = 20;
			hitpoints = maxHP;
			ground = Application.V_HEIGHT*0.25f;
			this.direction = direction;
			if (direction) {
				velocity = new Vector2(speed,0);
				name = name + "R";
			}else{
				velocity = new Vector2(-speed, 0);
			}
			animation = new Animation(name, 4, 0.5f, textures, 1);
			extras = true;
		}
		this.type = type;
		
		position = new Vector2(posX,posY);
		bounds = new Circle(position, textures.getTextureRegion(name + 1).getRegionWidth()*0.5f);
		HPBarPositionY =textures.getTextureRegion(name + 1).getRegionHeight() + 5;
		HPBarLength = textures.getTextureRegion(name + 1).getRegionWidth();
		HPBar = new Rectangle(posX, posY + HPBarPositionY ,HPBarLength ,3);
		
	}
	
	public void update(float delta, World world){
		animation.update(delta);
		if (direction) {
			if (velocity.x >= speed) {
				velocity.add(-speed*delta, 0);
			}else{
				velocity.add(speed*delta,0);
			}
			
		}else{
			if (velocity.x <= -speed) {
				velocity.add(speed* delta, 0);
			}else{
				velocity.add(-speed*delta,0);
			}
		}
		if (position.y <= ground) {
			position.y = ground;
			
		}else{
			velocity.y += -Constants.GRAVITY*delta;
		}
		
		if (position.x <= Application.V_WIDTH*0.5&& !direction) {
			direction = true;
			if (position.y <= ground) {
				world.takeDmg(dmg);
			}
		}else if (direction && position.x >= Application.V_WIDTH*0.5) {
			direction = false;
			if (position.y <= ground) {
				world.takeDmg(dmg);
			}
		}
		position.add(velocity);
		HPBar.setPosition(position.x, position.y + HPBarPositionY);
		HPBar.setWidth(HPBarLength* (float)(hitpoints/(float)maxHP));
		bounds.setPosition(position);
		
	}
	
	public void hit(int dmg, float forceX, float forceY){
		velocity.set(forceX, forceY);
		hitpoints -= dmg;
	}
	
	public int getType(){
		return type;
	}
	
	public TextureRegion getTexture(){
		if (extras) {
			if (position.y > 100) {
				return animation.getFrame(4);
			}
		}
		return animation.getFrame();
		
		
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
	
	public void render(ShapeRenderer renderer){
		renderer.rect(HPBar.getX(), HPBar.getY(), HPBar.getWidth(), HPBar.getHeight());
	}

	@Override
	public void dispose() {
		
		
	}
	
	

}
