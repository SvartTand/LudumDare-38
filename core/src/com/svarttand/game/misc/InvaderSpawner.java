package com.svarttand.game.misc;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.sprites.Invader;
import com.svarttand.game.sprites.World;

public class InvaderSpawner {
	
	
	
	private ArrayList<Invader> invaders;
	private Random random;
	private float counter;
	private ArrayList<Explosion> explosions;
	private Textures textures;
	private int score;
	private float freqency;

	
	public InvaderSpawner(){
		invaders = new ArrayList<Invader>();
		random = new Random();
		freqency = 4;
		counter = freqency;
		explosions = new ArrayList<Explosion>();
		score = 0;
	}
	
	public void update(float delta, World world, Textures textures){
		this.textures = textures;
		counter -= delta;
		if (counter <= 0) {
			int type = random.nextInt(3);
			boolean direction = random.nextBoolean();
			if (direction) {
				invaders.add(new Invader(direction, -50, 100,textures, type));
				
			}else{
				invaders.add(new Invader(direction, Application.V_WIDTH + 20, 100,textures, type));
			}
			counter = freqency;
		}
		for (int i = 0; i < invaders.size(); i++) {
			invaders.get(i).update(delta, world);
			if (invaders.get(i).getHitpoints() <= 0) {
				if (invaders.get(i).getType() == Constants.GIANT) {
					addExplosion(new Explosion(invaders.get(i).getPosition(), "SlimeEffect", textures, 6, invaders.get(i).getTexture().getRegionWidth(), invaders.get(i).getTexture().getRegionHeight(),false));
				}else if(Constants.NORMAL_TYPE == invaders.get(i).getType()){
					addExplosion(new Explosion(invaders.get(i).getPosition(), "SnailMobEffect", textures, 6, invaders.get(i).getTexture().getRegionWidth(), invaders.get(i).getTexture().getRegionHeight(),false));
				}else{
					addExplosion(new Explosion(invaders.get(i).getPosition(), "FlyingMonsterEffect", textures, 6, invaders.get(i).getTexture().getRegionWidth(), invaders.get(i).getTexture().getRegionHeight(),false));
				}
				
				invaders.get(i).dispose();
				invaders.remove(i);
				score++;
			}
		}
		for (int i = 0; i < explosions.size(); i++) {
			if (!explosions.get(i).update(delta)) {
				explosions.remove(i);
			}
		}
	}
	
	public void render(SpriteBatch batch){
		for (int i = 0; i < invaders.size(); i++) {
			batch.draw(invaders.get(i).getTexture(), invaders.get(i).getPosition().x, invaders.get(i).getPosition().y);
		}
		for (int i = 0; i < explosions.size(); i++) {
			batch.draw(textures.getTextureRegion(explosions.get(i).getTextureName()),explosions.get(i).getPosition().x ,explosions.get(i).getPosition().y);
		}
	}
	
	public void explosion(Circle blast, int dmg, float force){
		for (int i = 0; i < invaders.size(); i++) {
			if (blast.overlaps(invaders.get(i).getBounds())) {
				
				float distanceX = ((blast.x) - (invaders.get(i).getBounds().x));
				float distanceY = Math.abs((blast.y+ blast.radius) - (invaders.get(i).getBounds().y + invaders.get(i).getBounds().radius));
				invaders.get(i).hit(dmg, -distanceX*force, distanceY*force);
				
				
			}
		}
	}

	public void dispose() {
		for (int i = 0; i < invaders.size(); i++) {
			invaders.get(i).dispose();
		}
		
	}

	public void addExplosion(Explosion explosion) {
		explosions.add(explosion);
		
	}

	public void shapeRender(ShapeRenderer renderer) {
		renderer.setColor(Color.RED);
		for (int i = 0; i < invaders.size(); i++) {
			invaders.get(i).render(renderer);
		}
	}
	
	public int getScore(){
		return score;
	}
	
	public void setFrequency(float freqency){
		this.freqency = freqency;
	}

}
