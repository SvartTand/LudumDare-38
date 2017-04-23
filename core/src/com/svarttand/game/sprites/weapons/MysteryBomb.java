package com.svarttand.game.sprites.weapons;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.misc.Audio;
import com.svarttand.game.misc.InvaderSpawner;
import com.svarttand.game.misc.Textures;
import com.svarttand.game.misc.Weapons;
import com.svarttand.game.sprites.Dome;

public class MysteryBomb implements Weapons {

	private float force;
	private String textureName;
	private Vector2 position;
	private static final int dmg = 30;
	private static final float COOLDOWN = 0.3f;
	private Circle blast;
	
	private boolean released;
	private float detonationTime;
	
	private float velocity;
	private ArrayList<Weapons> list;
	
	private ArrayList<Weapons> bombs;
	
	private InvaderSpawner invaders;
	private Dome dome;
	
	private Textures textures;
	
	private float width;
	private float height;
	private boolean detonated;
	
	public MysteryBomb(ArrayList<Weapons> list, InvaderSpawner invaders, Dome dome, Textures textures){
		textureName = "MysteryBox";
		width = textures.getTextureRegion(textureName).getRegionWidth();
		height = textures.getTextureRegion(textureName).getRegionHeight();
		position = new Vector2();
		blast = new Circle(position, textures.getTextureRegion("MysteryBox").getRegionWidth()*0.5f);
		released = false;
		detonationTime = 100f;
		this.list = list;
		this.invaders = invaders;
		this.dome = dome;
		force = 0.01f;
		this.textures = textures;
		bombs = new ArrayList<Weapons>();
		detonated = false;
	}
	

	@Override
	public void update(float mousePositionX, float mousePositionY, float delta) {
		if (!detonated) {
			if (released) {
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
		}
		
		if (detonated) {
			for (int i = 0; i < bombs.size(); i++) {
				bombs.get(i).update(100, position.y, delta);
			}
		}
		if (detonated && bombs.isEmpty()) {
			dispose();
		}
		
	}


	@Override
	public void release() {
		released = true;
		
	}

	@Override
	public void detonate() {
		detonated = true;
		invaders.explosion(blast, dmg, force);
		if (!blast.overlaps(dome.getBounds())) {
			dome.takeDamage(dmg);
		}
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			Granade placeholder = new Granade(list, invaders, dome, textures);
			placeholder.release();
			placeholder.setVelocity(random.nextInt((2 - 0) + 1));
			bombs.add(placeholder);
		}
		
		textures.getSound(Audio.GRANADE_EXPLOSION).play();
		
		
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

}
