package com.svarttand.game.sprites.weapons;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.misc.Audio;
import com.svarttand.game.misc.InvaderSpawner;
import com.svarttand.game.misc.Textures;
import com.svarttand.game.misc.Weapons;
import com.svarttand.game.sprites.Dome;


public class Weapon implements Weapons{
	
	private float force;
	private String textureName;
	private Vector2 position;
	private static final int dmg = 15;
	private static final float COOLDOWN = 1f;
	private Circle blast;
	
	private boolean released;
	private float detonationTime;
	
	private float velocity;
	private ArrayList<Weapons> list;
	
	private InvaderSpawner invaders;
	private Dome dome;
	
	private Textures textures;
	
	private float width;
	private float height;
	
	public Weapon(ArrayList<Weapons> list, InvaderSpawner invaders, Dome dome, Textures textures){
		textureName = "Bomb";
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
		this.textures = textures;
		
		
	}
	@Override
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
	@Override
	public void release(){
		released = true;
	}
	@Override
	public void detonate(){
		invaders.explosion(blast, dmg, force);
		if (!blast.overlaps(dome.getBounds())) {
			dome.takeDamage(dmg);
		}
		textures.getSound(Audio.BOMB_EXPLOSION).play();
		dispose();
	}
	@Override
	public Circle getBlast(){
		return blast;
	}
	@Override
	public Vector2 getPosition(){
		return position;
	}
	@Override
	public String getTextureName(){
		return textureName;
	}
	@Override
	public void dispose() {
		list.remove(this);
		
		
	}
	public float getCooldown() {
		return COOLDOWN;
	}

}
