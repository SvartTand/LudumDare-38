package com.svarttand.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.svarttand.game.Application;
import com.svarttand.game.misc.Textures;

public abstract class State {
	
	protected OrthographicCamera cam;
    protected Vector2 mouse;
    protected GameStateManager gsm;
    protected Textures textures;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.position.set(Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f,0);
        mouse = new Vector2();
        textures = new Textures();
        
    }

    protected abstract void handleInput(float delta);
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
    public abstract void resize(int width, int height);

}
