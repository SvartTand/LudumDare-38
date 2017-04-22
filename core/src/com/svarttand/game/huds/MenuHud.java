package com.svarttand.game.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;
import com.svarttand.game.states.GameStateManager;
import com.svarttand.game.states.PlayState;

public class MenuHud {
	
	private Camera camera;
	private Viewport viewport;
	public Stage stage;
	
	private Texture buttonTexture;
	private Label playLabel;
	
	private GameStateManager gsm;
	
	private int isPressed;
	
	public MenuHud(Camera camera){
		this.camera = camera;
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,camera);
		buttonTexture = new Texture("Button.png");
		isPressed = 0;
	}
	
	public void initialize(){
		stage = new Stage(viewport);
		Button button = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonTexture)));
        button.setPosition(Application.V_WIDTH*0.5f - buttonTexture.getWidth()*0.5f, Application.V_HEIGHT*0.25f - buttonTexture.getHeight()*0.5f);
        button.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPressed = 1;
            };
        });
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
        
	}
	public int isPressed(){
		return isPressed;
	}
	
	public Viewport getViewport(){
		return viewport;
	}
	
	public void dispose(){
		stage.dispose();
	}


}
