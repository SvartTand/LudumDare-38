package com.svarttand.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;
import com.svarttand.game.huds.MenuHud;

public class MenuState extends State{

	private Viewport viewport;
	private MenuHud hud;
	private Texture background;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		hud = new MenuHud(cam);
		background = new Texture("MainMenuBackground.png");
		hud.initialize();
	}

	@Override
	protected void handleInput(float delta) {
		if (hud.isPressed() == 1) {
			gsm.set(new PlayState(gsm));
		}
		
	}

	@Override
	public void update(float delta) {
		handleInput(delta);
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();
		hud.stage.draw();
	}

	@Override
	public void dispose() {
		background.dispose();
		hud.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		hud.getViewport().update(width, height);
		
	}

	
	

}
