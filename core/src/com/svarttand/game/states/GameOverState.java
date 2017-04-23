package com.svarttand.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;
import com.svarttand.game.huds.GameOverHud;

public class GameOverState extends State{

	private Viewport viewport;
	private GameOverHud hud;
	private TextureRegion background;
	
	public GameOverState(GameStateManager gsm, int score) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		hud = new GameOverHud(cam, score);
		hud.initialize(textures);
		background = textures.getTextureRegion("MainMenuBackground");
	}

	@Override
	protected void handleInput(float delta) {
		if (hud.getButtonPressed() == 1) {
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
		batch.draw(background,0,0);
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
