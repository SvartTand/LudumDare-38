package com.svarttand.game.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.huds.PlayHud;
import com.svarttand.game.misc.InvaderSpawner;
import com.svarttand.game.misc.Weapons;
import com.svarttand.game.sprites.Dome;
import com.svarttand.game.sprites.World;
import com.svarttand.game.sprites.weapons.Granade;
import com.svarttand.game.sprites.weapons.MysteryBomb;
import com.svarttand.game.sprites.weapons.Napalm;
import com.svarttand.game.sprites.weapons.Nuke;
import com.svarttand.game.sprites.weapons.Rock;
import com.svarttand.game.sprites.weapons.Weapon;

public class PlayState extends State{
	
	private Viewport viewport;
	private TextureRegion background;
	private World world;
	private Dome dome;
	private PlayHud hud;
	
	private boolean canChange;
	private ArrayList<Weapons> weapons;
	private InvaderSpawner invaders;
	
	private float cooldown;
	private float maxCooldown;
	private ShapeRenderer renderer;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		background = textures.getTextureRegion("MainMenuScreen");
		world = new World(textures);
		dome = new Dome();
		hud = new PlayHud(cam);
		hud.initialize(textures);
		canChange = true;
		weapons = new ArrayList<Weapons>();
		invaders = new InvaderSpawner();
		renderer = new ShapeRenderer();
		
	}

	@Override
	protected void handleInput(float delta) {
		
		if (Gdx.input.isTouched() && mouse.y >= 100) {
			
			if (hud.getCurrentPressed() == Constants.BOMB && canChange) {
				
				Weapon weapon = new Weapon(weapons, invaders, dome, textures);
				weapons.add(weapon);
				cooldown = weapon.getCooldown();
				maxCooldown = cooldown;
				canChange = false;
			}else if (hud.getCurrentPressed() == Constants.GRANADE && canChange) {
				Granade granade = new Granade(weapons, invaders, dome, textures);
				cooldown = granade.getCooldown();
				maxCooldown = cooldown;
				weapons.add(granade);
				canChange = false;
			}else if (hud.getCurrentPressed() == Constants.STONE && canChange) {
				Rock stone = new Rock(weapons, invaders, dome, textures);
				cooldown = stone.getCooldown();
				maxCooldown = cooldown;
				weapons.add(stone);
				canChange = false;
			}else if (hud.getCurrentPressed() == Constants.NUKE && canChange) {
				Nuke nuke = new Nuke(weapons, invaders, dome, textures);
				cooldown = nuke.getCooldown();
				maxCooldown = cooldown;
				weapons.add(nuke);
				canChange = false;
			}
			else if (hud.getCurrentPressed() == Constants.MYSTERYBOX && canChange) {
				MysteryBomb box = new MysteryBomb(weapons, invaders, dome, textures);
				cooldown = box.getCooldown();
				maxCooldown = cooldown;
				weapons.add(box);
				canChange = false;
			}
			else if (hud.getCurrentPressed() == Constants.NAPALM && canChange) {
				Napalm napalm = new Napalm(weapons, invaders, dome, textures);
				cooldown = napalm.getCooldown();
				maxCooldown = cooldown;
				weapons.add(napalm);
				canChange = false;
			}
		}else if (weapons.size() > 0) {
			weapons.get(weapons.size()-1).release();
			
		}	
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && weapons.size() > 0 && !weapons.get(weapons.size()-1).getTextureName().equals("Rock")) {
			weapons.get(weapons.size()-1).detonate();;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.set(new MenuState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			hud.setCurrentpressed(0);
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			hud.setCurrentpressed(1);
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
			hud.setCurrentpressed(2);
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
			hud.setCurrentpressed(3);
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
			hud.setCurrentpressed(4);
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_6)) {
			hud.setCurrentpressed(5);
		}
		
		
	}
			


	@Override
	public void update(float delta) {
		handleInput(delta);
		mouse.set(((float)Gdx.input.getX()/Gdx.graphics.getWidth())* Application.V_WIDTH,Application.V_HEIGHT - ((float)Gdx.input.getY()/Gdx.graphics.getHeight())*Application.V_HEIGHT);
		world.update(delta);
		dome.update(delta,invaders);
		if (world.getHitPoints()<= 0) {
			gsm.set(new GameOverState(gsm, invaders.getScore(),false));
		}
		if (dome.getHitPoints() <= 0) {
			gsm.set(new GameOverState(gsm, invaders.getScore(),true));
		}
		
		cooldown -= delta;
		if (cooldown <= 0) {
			canChange = true;
		}
		
		for (int i = 0; i < weapons.size(); i++) {
			weapons.get(i).update(mouse.x, mouse.y, delta);
		}
		invaders.update(delta, world, textures);
		hud.update(world.getHitPoints(), dome.getHitPoints(), invaders.getScore(), cooldown/maxCooldown);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(background, 0,0);
		batch.draw(textures.getTextureRegion(world.getTextureName()), Application.V_WIDTH*0.5f - world.getWidth()*0.5f, Application.V_HEIGHT/4-9);
		batch.draw(textures.getTextureRegion(dome.getTexture()),0,Application.V_HEIGHT*0.5f);
		for (int i = 0; i < weapons.size(); i++) {
			weapons.get(i).render(batch);
		}
		invaders.render(batch);
		batch.end();
		renderer.setProjectionMatrix(cam.combined);
		renderer.begin(ShapeType.Filled);
		hud.render(renderer);
		invaders.shapeRender(renderer);
		renderer.end();
		hud.stage.draw();
	}

	@Override
	public void dispose() {
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
