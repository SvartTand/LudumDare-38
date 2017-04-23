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
	
	private boolean win;
	
	public GameOverState(GameStateManager gsm, int score, boolean win) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		hud = new GameOverHud(cam, score);
		hud.initialize(textures, win);
		background = textures.getTextureRegion("MainMenuScreen");
		this.win = win;
	}

	@Override
	protected void handleInput(float delta) {
		if (hud.getButtonPressed() == 1) {
			gsm.set(new PlayState(gsm));
		}
		if (hud.getButtonPressed() == 2) {
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
		batch.draw(background,0,0);
		if (!win) {
			batch.draw(textures.getTextureRegion("Dome1"),0,Application.V_HEIGHT*0.5f);
			batch.draw(textures.getTextureRegion("City3"),Application.V_WIDTH*0.5f - textures.getTextureRegion("City3").getRegionWidth()*0.5f, Application.V_HEIGHT/4-9);
		}
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
