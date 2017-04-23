package com.svarttand.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.svarttand.game.states.GameOverState;
import com.svarttand.game.states.GameStateManager;
import com.svarttand.game.states.MenuState;
import com.svarttand.game.states.PlayState;

public class Application extends ApplicationAdapter {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 400;
	
	SpriteBatch batch;
	GameStateManager gsm;
	
	@Override
	public void create () {
		gsm = new GameStateManager();
		batch = new SpriteBatch();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
	
	@Override
	public void dispose(){
		gsm.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		gsm.resize(width, height);
		
	}
}
