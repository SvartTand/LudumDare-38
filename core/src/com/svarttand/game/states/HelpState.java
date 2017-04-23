package com.svarttand.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;
import com.svarttand.game.huds.HelpHud;

public class HelpState extends State{
	
	private Viewport viewport;
	private HelpHud hud;
	private TextureRegion background;
	
	public HelpState(GameStateManager gsm) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		hud = new HelpHud(cam);
		background = textures.getTextureRegion("MainMenuScreen");
		hud.initialize(textures);
	}

	@Override
	protected void handleInput(float delta) {
		if (hud.isPressed() == 1) {
			gsm.set(new MenuState(gsm));
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
		batch.draw(textures.getTextureRegion("Dome1"),0,Application.V_HEIGHT*0.5f);
		batch.end();
		hud.stage.draw();
	}

	@Override
	public void dispose() {
		hud.dispose();
		textures.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		hud.getViewport().update(width, height);
		
	}

}
