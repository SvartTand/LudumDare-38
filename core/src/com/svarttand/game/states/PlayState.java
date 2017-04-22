package com.svarttand.game.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.huds.PlayHud;
import com.svarttand.game.misc.InvaderSpawner;
import com.svarttand.game.misc.Textures;
import com.svarttand.game.sprites.Dome;
import com.svarttand.game.sprites.Weapon;
import com.svarttand.game.sprites.World;

public class PlayState extends State{
	
	private Viewport viewport;
	private Texture background;
	private World world;
	private Dome dome;
	private PlayHud hud;
	
	private boolean canChange;
	private ArrayList<Weapon> weapons;
	private InvaderSpawner invaders;
	
	private Textures textures;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		textures = new Textures();
		background = new Texture("BackgroundPlaceholder.png");
		world = new World(textures);
		dome = new Dome();
		hud = new PlayHud(cam);
		hud.initialize();
		canChange = true;
		weapons = new ArrayList<Weapon>();
		invaders = new InvaderSpawner();
		
	}

	@Override
	protected void handleInput(float delta) {
		
		if (Gdx.input.isTouched()) {
			
			if (hud.getCurrentPressed() == Constants.BOMB && canChange) {
				
				Weapon weapon = new Weapon(weapons, invaders,dome);
				weapons.add(weapon);
				canChange = false;
			}
		}else if (weapons.size() > 0 && weapons.get(weapons.size()-1) != null) {
			weapons.get(weapons.size()-1).release();
			canChange = true;
			
		}		
		
	}
			


	@Override
	public void update(float delta) {
		handleInput(delta);
		mouse.set(((float)Gdx.input.getX()/Gdx.graphics.getWidth())* Application.V_WIDTH,Application.V_HEIGHT - ((float)Gdx.input.getY()/Gdx.graphics.getHeight())*Application.V_HEIGHT);
		world.update(delta);
		dome.update(delta);
		for (int i = 0; i < weapons.size(); i++) {
			weapons.get(i).update(mouse.x, mouse.y, delta);
		}
		invaders.update(delta, world);
		hud.update(world.getHitPoints(), dome.getHitPoints());
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(background, 0,0);
		batch.draw(textures.getTextureRegion(world.getTextureName()), Application.V_WIDTH*0.5f - world.getWidth()*0.5f, Application.V_HEIGHT/4);
		batch.draw(textures.getTextureRegion(dome.getTexture()),0,0);
		for (int i = 0; i < weapons.size(); i++) {
			batch.draw(weapons.get(i).getTexture(), weapons.get(i).getPosition().x,weapons.get(i).getPosition().y);
		}
		invaders.render(batch);
		batch.end();
		hud.render();
		hud.stage.draw();
		
	}

	@Override
	public void dispose() {
		background.dispose();
		hud.dispose();
		for (int i = 0; i < weapons.size(); i++) {
			weapons.get(i).dispose();
		}
		invaders.dispose();
		textures.dispose();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		hud.getViewport().update(width, height);
		
		
		
	}

}
