package com.svarttand.game.misc;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.sprites.Invader;
import com.svarttand.game.sprites.World;

public class InvaderSpawner {
	
	
	
	private ArrayList<Invader> invaders;
	private Random random;
	private float counter;
	
	public InvaderSpawner(){
		invaders = new ArrayList<Invader>();
		random = new Random();
		counter = Constants.SPAWN_FREQENCY;
	}
	
	public void update(float delta, World world, Textures textures){
		counter -= delta;
		if (counter <= 0) {
			boolean direction = random.nextBoolean();
			if (direction) {
				invaders.add(new Invader(direction, -20, 100,textures, Constants.NORMAL_TYPE));
			}else{
				invaders.add(new Invader(direction, Application.V_WIDTH + 20, 100,textures, Constants.NORMAL_TYPE));
			}
			counter = Constants.SPAWN_FREQENCY;
		}
		for (int i = 0; i < invaders.size(); i++) {
			invaders.get(i).update(delta, world);
			if (invaders.get(i).getHitpoints() <= 0) {
				invaders.get(i).dispose();
				invaders.remove(i);
			}
		}
	}
	
	public void render(SpriteBatch batch){
		for (int i = 0; i < invaders.size(); i++) {
			batch.draw(invaders.get(i).getTexture(), invaders.get(i).getPosition().x, invaders.get(i).getPosition().y);
		}
	}
	
	public void explosion(Circle blast, int dmg, float force){
		for (int i = 0; i < invaders.size(); i++) {
			if (blast.overlaps(invaders.get(i).getBounds())) {
				System.out.println("HITS");
				float distanceX = Math.abs((blast.x + blast.radius) - (invaders.get(i).getBounds().x + invaders.get(i).getBounds().radius));
				float distanceY = Math.abs((blast.y+ blast.radius) - (invaders.get(i).getBounds().y + invaders.get(i).getBounds().radius));		
				invaders.get(i).hit(dmg, distanceX*force, distanceY*force);
			}
		}
	}

	public void dispose() {
		for (int i = 0; i < invaders.size(); i++) {
			invaders.get(i).dispose();
		}
		
	}

}
