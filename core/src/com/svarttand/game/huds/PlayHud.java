package com.svarttand.game.huds;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.game.Application;

public class PlayHud {

	private Camera camera;
	private Viewport viewport;
	
	public Stage stage;
	private Texture buttonTexture;
	
	private ArrayList<Button> buttonList;
	
	private int currentPressed;

	public PlayHud(){
		camera = new OrthographicCamera();
		camera.position.set(Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f,0);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT,camera);
		buttonTexture = new Texture("Button.png");
		buttonList = new ArrayList<Button>();
		
	}
	
	public void initialize(){
		stage = new Stage(viewport);
		for (int i = 0; i < 12; i++) {
			final int buttonType = i;
			Button button = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonTexture)));
	        button.setPosition(3 + (buttonTexture.getWidth()+1) * i, 3);
	        
	        button.addListener( new ClickListener() {              
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                currentPressed = buttonType;
	            };
	        });
	        buttonList.add(button);
	        stage.addActor(button);
	        Gdx.input.setInputProcessor(stage);
		}
		
	}
	public int getCurrentPressed(){
		return currentPressed;
	}
	
	public Viewport getViewport(){
		return viewport;
	}
	
	public void dispose(){
		stage.dispose();
	}

}
