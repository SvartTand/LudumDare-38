package com.svarttand.game.sprites;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.misc.InvaderSpawner;
import com.svarttand.game.misc.Textures;


public class Weapon implements Disposable{
	
	private float force;
	private String textureName;
	private Vector2 position;
	private static final int dmg = 15;
	private Circle blast;
	
	private boolean released;
	private float detonationTime;
	
	private float velocity;
	private ArrayList<Weapon> list;
	
	private InvaderSpawner invaders;
	private Dome dome;
	
	private float width;
	private float height;
	
	public Weapon(ArrayList<Weapon> list, InvaderSpawner invaders, Dome dome, Textures textures){
		textureName = "BombPlaceholder";
		width = textures.getTextureRegion(textureName).getRegionWidth();
		height = textures.getTextureRegion(textureName).getRegionHeight();
		position = new Vector2();
		blast = new Circle(position, 40);
		released = false;
		detonationTime = 3;
		this.list = list;
		this.invaders = invaders;
		this.dome = dome;
		force = 0.1f;
		
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
			position.x = (float) (mousePositionX - width* 0.5);
			position.y = (float) (mousePositionY - height* 0.5);
		}
		blast.setPosition(position);
		
		
	}
	public void release(){
		released = true;
	}
	
	public void detonate(){
		invaders.explosion(blast, dmg, force);
		if (!blast.overlaps(dome.getBounds())) {
			dome.takeDamage(dmg);
		}
		dispose();
	}
	
	public Circle getBlast(){
		return blast;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public String getTextureName(){
		return textureName;
	}
	@Override
	public void dispose() {
		list.remove(this);
		
	}

}
