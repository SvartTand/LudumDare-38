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
import com.svarttand.game.sprites.Weapon;
import com.svarttand.game.sprites.World;

public class PlayState extends State{
	
	private Viewport viewport;
	private Texture background;
	private World world;
	private PlayHud hud;
	
	private boolean canChange;
	
	private ArrayList<Weapon> weapons;
	
	private InvaderSpawner invaders;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		background = new Texture("BackgroundPlaceholder.png");
		world = new World();
		hud = new PlayHud();
		hud.initialize();
		canChange = true;
		weapons = new ArrayList<Weapon>();
		invaders = new InvaderSpawner();
		
	}

	@Override
	protected void handleInput(float delta) {
		
		if (Gdx.input.isTouched()) {
			
			if (hud.getCurrentPressed() == Constants.BOMB && canChange) {
				
				Weapon weapon = new Weapon(weapons, invaders);
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
		for (int i = 0; i < weapons.size(); i++) {
			weapons.get(i).update(mouse.x, mouse.y, delta);
		}
		invaders.update(delta);
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(background, 0,0);
		batch.draw(world.getTexture(), (float) (Application.V_WIDTH*0.5 - world.getTexture().getWidth()*0.5), Application.V_HEIGHT/4);
		for (int i = 0; i < weapons.size(); i++) {
			batch.draw(weapons.get(i).getTexture(), weapons.get(i).getPosition().x,weapons.get(i).getPosition().y);
		}
		invaders.render(batch);
		batch.end();
		hud.stage.draw();
		
	}

	@Override
	public void dispose() {
		background.dispose();
		hud.dispose();
		world.dispose();
		for (int i = 0; i < weapons.size(); i++) {
			weapons.get(i).dispose();
		}
		invaders.dispose();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		hud.getViewport().update(width, height);
		
		
		
	}

}
