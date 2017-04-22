package com.svarttand.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;
import com.svarttand.game.constants.Constants;
import com.svarttand.game.huds.PlayHud;
import com.svarttand.game.sprites.Weapon;
import com.svarttand.game.sprites.World;

public class PlayState extends State{
	
	private Viewport viewport;
	private Texture background;
	private World world;
	private PlayHud hud;
	private Weapon weapon;
	
	private boolean canChange;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		background = new Texture("BackgroundPlaceholder.png");
		world = new World();
		hud = new PlayHud();
		hud.initialize();
		canChange = true;
		
	}

	@Override
	protected void handleInput(float delta) {
		
		System.out.println(hud.getCurrentPressed());
		
		if (Gdx.input.isTouched()) {
			System.out.println("yo1");
			if (hud.getCurrentPressed() == Constants.BOMB && canChange) {
				System.out.println("yo2");
				weapon = new Weapon();
				canChange = false;
			}
		}else if (weapon != null) {
			weapon.detonate();
			weapon = null;
			canChange = true;
		}		
		
	}
			


	@Override
	public void update(float delta) {
		handleInput(delta);
		mouse.set(((float)Gdx.input.getX()/Gdx.graphics.getWidth())* Application.V_WIDTH,Application.V_HEIGHT - ((float)Gdx.input.getY()/Gdx.graphics.getHeight())*Application.V_HEIGHT);
		world.update(delta);
		if (weapon != null) {
			weapon.update(mouse.x, mouse.y);
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(background, 0,0);
		batch.draw(world.getTexture(), (float) (Application.V_WIDTH*0.5 - world.getTexture().getWidth()*0.5), Application.V_HEIGHT/4);
		if (weapon != null) {
			batch.draw(weapon.getTexture(), weapon.getPosition().x,weapon.getPosition().y);
		}
		batch.end();
		hud.stage.draw();
	}

	@Override
	public void dispose() {
		background.dispose();
		hud.dispose();
		world.dispose();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		hud.getViewport().update(width, height);
		
	}

}
