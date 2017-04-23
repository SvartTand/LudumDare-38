package com.svarttand.game.sprites.weapons;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.misc.Animation;
import com.svarttand.game.misc.Audio;
import com.svarttand.game.misc.Explosion;
import com.svarttand.game.misc.InvaderSpawner;
import com.svarttand.game.misc.Textures;
import com.svarttand.game.misc.Weapons;
import com.svarttand.game.sprites.Dome;

public class Napalm implements Weapons{
	
	private ArrayList<Vector2> positions;
	
	private static final float COOLDOWN = 2;
	private String textureName;
	private float width;
	private float height;
	private Vector2 position;
	private Circle blast;
	private boolean released;
	private float detonationTime;
	private ArrayList<Weapons> list;
	private InvaderSpawner invaders;
	private float force;
	private Textures textures;
	private int dmg;
	private float velocity;
	private float velocityX;
	
	private Animation animation;
	
	private boolean direction;
	
	public Napalm(ArrayList<Weapons> list, InvaderSpawner invaders, Dome dome, Textures textures){
		textureName = "Fire";
		width = textures.getTextureRegion(textureName).getRegionWidth();
		height = textures.getTextureRegion(textureName).getRegionHeight();
		position = new Vector2();
		blast = new Circle(position, textures.getTextureRegion("Rock").getRegionWidth()*0.5f);
		released = false;
		detonationTime = 100f;
		this.list = list;
		this.invaders = invaders;
		force = 0.01f;
		this.textures = textures;
		positions = new ArrayList<Vector2>();
		dmg = 8;
		Random random = new Random();
		direction = random.nextBoolean();
		if (direction) {
			velocityX = 30;
		}else{
			velocityX = -30;
		}
		animation = new Animation("Fire", 4, 0.5f, textures);
	}

	@Override
	public void update(float mousePositionX, float mousePositionY, float delta) {
		if (released) {
			animation.update(delta);
			velocity += Constants.GRAVITY * delta;
			if (position.y <= 100) {
				detonate();
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
		for (int i = 0; i < positions.size(); i++) {
			positions.get(i).add(velocityX*delta,-velocity);
		}
	}


	@Override
	public void release() {
		if (!released) {
			for (int i = -3; i < 4; i++) {
				positions.add(new Vector2(position.x + i * width, position.y));
			}
		}
		released = true;
		
		
		
	}

	@Override
	public void detonate() {
		for (int i = 0; i < positions.size(); i++) {
			invaders.explosion(new Circle(positions.get(i), width*0.5f), dmg, force);
			invaders.addExplosion(new Explosion(positions.get(i), "Fire", textures, 5, width, height,false));
			
		}
		textures.getSound(Audio.FIRE).play();
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

	@Override
	public float getCooldown() {
		return COOLDOWN;
	}

	@Override
	public TextureRegion getTextureRegion() {
		return textures.getTextureRegion(textureName);
	}

	@Override
	public void render(SpriteBatch batch) {
		if (!released) {
			batch.draw(textures.getTextureRegion(textureName),position.x,position.y);
		}else{
			for (int i = 0; i < positions.size(); i++) {
				batch.draw(animation.getFrame(),positions.get(i).x,positions.get(i).y);
			}
		}
		
		
		
	}
	
}
